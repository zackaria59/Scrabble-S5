package Controller;

import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import Model.Jeton;
import Model.Joueur;
import Model.Partie;
import Model.Plateau;
import Model.Sac;
import View.BoutonCustom;
import View.Dictionnaire;
import View.FenetreJeu;
import View.JetonV;
import View.PlateauV;
import View.Tuile;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ControllerPlateau implements EventHandler<MouseEvent> {

	private Plateau plateau;
	private PlateauV plateauView;
	private Partie partie;
	private FenetreJeu fj;
	private JetonV jetonv;
	private final Point pos = new Point();
	private boolean premierTour, lettreJokerChoisi;
	private char lettrejokChoisi;
	private int tempRestant;

	private Tuile tuileJetonJoker;

	private ArrayList<Tuile> postionJetonPose = new ArrayList<Tuile>();

	public ControllerPlateau(Partie partie, FenetreJeu fj) {
		this.partie = partie;
		this.fj = fj;

		fj.getMc().addControllers(this);
		fj.getJcl().addControllerJetonV(this);
		fj.getEv().setCp(this);

		fj.setAfficheAutreJoueurs(partie.getNbJoueur() - 1);
		setJoueurQuijoue(partie.getJoueurs().get(0));

		premierTour = true;
		lettreJokerChoisi = false;

		if (partie.isOptionTimer()) {
			tempRestant = 60;
			chronoActif();
		}
		// DesignePremierJoueur();
	}

	public void DesignePremierJoueur() {
		ArrayList<Object> result = partie.designePremierJoueur();

		fj.getPj().affichePioche(result);
	}

	public Partie getPartie() {
		return partie;
	}

	public void setPartie(Partie partie) {
		this.partie = partie;
	}

	public Plateau getPlateau() {
		return plateau;
	}

	public void setPlateau(Plateau plateau) {
		this.plateau = plateau;
	}

	public void setJoueurQuijoue(Joueur j) {
		fj.setInFoJoueur(j);
		fj.ajoutController(this);
		partie.setJoueur(j);
		partie.setJoueurJoueTour(true);

		ArrayList<Joueur> js = new ArrayList<Joueur>();

		for (Joueur jj : partie.getJoueurs()) {
			js.add(jj);
		}
		js.remove(partie.getJoueurQuiJoue());
		System.out.println("joueurs =" + partie.getJoueurs().toString() + " js =" + js.toString());
		fj.getAfficheAutreJoueurs().setJoueurs(js);
	}

	public void passeTour() {
		tempRestant = 60;
		partie.videList();
		this.postionJetonPose.clear();
		this.setJoueurQuijoue(partie.nextJoueur());
		fj.getMc().activerBoutonMelanger();
		fj.getMc().activerBoutonPasser();
	}

	public void melanger() {
		// Collections.shuffle(partie.getJoueurQuiJoue().getJetons());
		// this.setJoueurQuijoue(partie.getJoueurQuiJoue());
		fj.getIj().animationReprendre();
	}

	public void reprendre() {
		for (Tuile t : this.postionJetonPose) {
			if (t.getX() == 7 && t.getY() == 7) {
				t.getContainer().getChildren().remove(2);
			} else {
				t.getContainer().getChildren().remove(1);
			}

			Jeton jt = new Jeton(t.getC());
			t.setC('1');
			System.out.println(jt.getLettre());
			partie.getJoueurQuiJoue().getJetons().add(jt);
		}

		this.postionJetonPose.clear();

		this.setJoueurQuijoue(partie.getJoueurQuiJoue());
		fj.getMc().activerBoutonMelanger();
		fj.getMc().activerBoutonPasser();
	}

	public void echanger() {
		ArrayList<JetonV> jetonsSelectionne = this.fj.getEv().getJetonSelectionne();

		for (JetonV jtv : jetonsSelectionne) {
			partie.getSac().getJetons().add(new Jeton(jtv.getLettre()));
			partie.getJoueurQuiJoue().removeJetonByChar(jtv.getLettre());
			partie.getJoueurQuiJoue().piocher(partie.getSac());

		}

		reprendre();
		passeTour();
	}

	public void chronoActif() {

		Timeline timer = new Timeline();
		timer.setCycleCount(Timeline.INDEFINITE);

		if (timer != null) {
			timer.stop();
		}

		KeyFrame kf = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				tempRestant--;

				fj.getChrono().setTime(tempRestant);

				if (tempRestant == 0) {
					passeTour();
				}

			}

		});

		timer.getKeyFrames().add(kf);
		timer.playFromStart();
	}

	@Override
	public void handle(MouseEvent e) {

		Object o = e.getSource();

		if (o instanceof BoutonCustom) {

			if (((BoutonCustom) o).getT().getText().equals("Melanger")) {
				this.melanger();
			} else if (((BoutonCustom) o).getT().getText().equals("Passer")) {
				passeTour();
			} else if (((BoutonCustom) o).getT().getText().equals("Jouer")) {
				if (this.premierTour) {
					try {
						if (partie.jouePremierTour()) {
							fj.getFm().afficheMessage(partie.getJoueurQuiJoue().getPseudo() + " marque "
									+ partie.getNbpointsCoupJoue() + " points !");
							partie.getJoueurQuiJoue().addPoints(partie.getNbpointsCoupJoue());
							partie.validJetonPose();
							passeTour();
							premierTour = false;
							partie.setNbpointsCoupJoue(0);
						} else {
							fj.getFm().setVisible(true);
							fj.getFm().afficheMessage(partie.getErreurMsg());

							this.partie.videList();
							this.reprendre();
						}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} else
					try {
						if (partie.joueUnTour()) {

							fj.getFm().afficheMessage(partie.getJoueurQuiJoue().getPseudo() + " marque "
									+ partie.getNbpointsCoupJoue() + " points !");
							partie.getJoueurQuiJoue().addPoints(partie.getNbpointsCoupJoue());
							partie.validJetonPose();
							passeTour();
							premierTour = false;
							partie.setNbpointsCoupJoue(0);
						} else {

							partie.setNbpointsCoupJoue(0);
							fj.getFm().afficheMessage(partie.getErreurMsg());

							this.partie.videList();
							this.reprendre();
						}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

			} else if (((BoutonCustom) o).getT().getText().equals("Reprendre")) {
				reprendre();
				partie.getPlateau().reprendre();

			} else if (((BoutonCustom) o).getT().getText().equals("Dictionnaire")) {
				fj.getDico().setVisible(true);
			} else if (((BoutonCustom) o).getT().getText().equals("Echanger")) {
				fj.getEv().setInformation(partie.getSac(), partie.getJoueurQuiJoue());
				fj.getEv().setVisible(true);
			}

		} else if (o instanceof JetonV) {

			fj.getP().addJetonDrag(((JetonV) o));

			if (e.getEventType().equals(MouseEvent.MOUSE_RELEASED)) {

				Object t = fj.getP().getPositionJeton();

				if (t instanceof Tuile) {

					((Tuile) t).setJetonPresent(true);

					JetonV jt = ((JetonV) o);

					if (jt.getLettre() == '^') {
						this.fj.getJcl().getSp().setVisible(true);
						this.fj.getJcl().animation();
						tuileJetonJoker = (Tuile) t;

					} else {

						ImageView img = new ImageView(getClass().getClassLoader()
								.getResource("images/jetons/" + jt.getLettre() + ".png").toString());
						img.setFitHeight(((Tuile) t).getRec().getHeight() - 1);
						img.setFitWidth(((Tuile) t).getRec().getWidth() - 1);
						((Tuile) t).getContainer().getChildren().add(img);

						((Tuile) t).setC(jt.getLettre());
						this.postionJetonPose.add((Tuile) t);
						partie.getJoueurQuiJoue().removeJetonByChar(jt.getLettre());
						partie.getPlateau().setJeton(((Tuile) t).getX(), ((Tuile) t).getY(), jt.getLettre());
					}

					fj.getMc().activerBoutonReprendre();
					jt.setVisible(false);
					fj.getMc().activerBoutonJouer();
				} else {
					System.out.println("======>  " + ((JetonV) o).getXinitiale());

					TranslateTransition tt = new TranslateTransition();
					tt.setToX(((JetonV) o).getXinitiale());
					tt.setToY(((JetonV) o).getYinitiale());
					tt.setByY(1);
					tt.setNode((JetonV) o);
					tt.play();
				}
			} else if (e.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
				System.out.println("Hi" + ((JetonV) o).isJetonPourEchanger());

				if (((JetonV) o).isJetonPourJoker()) {
					this.lettrejokChoisi = ((JetonV) o).getLettre();
					System.out.println("a clicke sur un jeton joker");
					this.lettreJokerChoisi = true;

					ImageView img = new ImageView(getClass().getClassLoader()
							.getResource("images/jetons/" + this.lettrejokChoisi + ".png").toString());
					img.setFitHeight(900 / 17);
					img.setFitWidth(900 / 17);
					tuileJetonJoker.getContainer().getChildren().add(img);

					tuileJetonJoker.setC('^');
					this.postionJetonPose.add((Tuile) tuileJetonJoker);

					partie.getJoueurQuiJoue().removeJetonByChar('^');
					partie.getPlateau().setJeton(tuileJetonJoker.getX(), tuileJetonJoker.getY(), lettrejokChoisi);

					this.fj.getJcl().getSp().setVisible(false);
				} else if (((JetonV) o).isJetonPourEchanger()) {

					if (((JetonV) o).isSelectionnePourEchange()) {
						((JetonV) o).setSelectionnePourEchange(false);
						((JetonV) o).setTranslateY(0);
					} else {
						((JetonV) o).setSelectionnePourEchange(true);
						((JetonV) o).setTranslateY(-60);
					}
				}
			} else if (e.getEventType().equals(MouseEvent.MOUSE_ENTERED)) {
				((JetonV) o).effectSelection();
			} else if (e.getEventType().equals(MouseEvent.MOUSE_EXITED)) {
				((JetonV) o).RetirerEffectSelection();
			}
		}

	}
}