package Controller;

import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

import Model.Dictionnaire;
import Model.Jeton;
import Model.Joueur;
import Model.JoueurIA;
import Model.Partie;
import Model.Plateau;
import Model.Sac;
import View.BoutonCustom;
import View.DictionnaireV;
import View.FenetreJeu;
import View.JetonV;
import View.Tuile;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.PauseTransition;
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
	private Partie partie;
	private FenetreJeu fj;
	private final Point pos = new Point();
	private boolean premierTour, lettreJokerChoisi;
	private char lettrejokChoisi;
	private int tempRestant;
	private Model.Dictionnaire dico;

	private Tuile tuileJetonJoker;

	private ArrayList<Tuile> postionJetonPose = new ArrayList<Tuile>();
	private JoueurIA professeur;
	
	public ControllerPlateau(Partie partie, FenetreJeu fj) throws IOException {
		
		dico=new Model.Dictionnaire();
		this.partie = partie;
		this.partie.setDico(dico);
		
		this.fj = fj;
		this.fj.getDicoV().setDictionnaire(dico);
		
		fj.getMc().addControllers(this);
		fj.getJcl().addControllerJetonV(this);
		fj.getEv().setCp(this);

		fj.setAfficheAutreJoueurs(partie.getNbJoueur() - 1);
		setJoueurQuijoue(partie.getJoueurs().get(0));

		premierTour = true;
		lettreJokerChoisi = false;

		professeur=new JoueurIA(4,"ordi",0,4);
		professeur.piocherDebutPartie(partie.getSac());
		professeur.setDico(dico);
		
		this.insertDicoIA(dico);
		
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
	
	public void insertDicoIA(Dictionnaire dico)
	{
		for(Joueur j:partie.getJoueurs())
		{
			if(j.isIa())
				((JoueurIA)j).setDico(dico);
		}
	}

	public void poseMotIA(LinkedList<Jeton> linkedList)
	{
				
		int x,y;
		for(Jeton jt:linkedList)
		{
			x=-500;
			y=-500;
			
			Tuile t=fj.getPlateauV().getPlateau()[jt.getX()][jt.getY()];
			
			
			ImageView img = new ImageView(getClass().getClassLoader()
					.getResource("images/jetons/" + jt.getLettre() + ".png").toString());
			img.setFitHeight(((Tuile) t).getRec().getHeight() - 1);
			img.setFitWidth(((Tuile) t).getRec().getWidth() - 1);
		    t.getContainer().getChildren().add(img);

			 t.setC(jt.getLettre());
			this.postionJetonPose.add((Tuile) t);
			partie.getJoueurQuiJoue().removeJetonByChar(jt.getLettre());
			partie.getPlateau().setJeton(((Tuile) t).getX(), ((Tuile) t).getY(), jt.getLettre());
			
			fj.getIj().removeJetonByChar(jt.getLettre());
			System.out.println("x="+jt.getX()+"  y="+jt.getY());
			
			img.setTranslateX((-960));
			img.setTranslateY(-520);
			
			TranslateTransition tt = new TranslateTransition();
			tt.setToX(img.getLayoutX());
			tt.setToY(img.getLayoutY());
			
			
			tt.setByY(1);
			tt.setNode(img);
			tt.setDuration(Duration.seconds(1.2));
			tt.play();
		}
	
		Timeline timer2 = new Timeline();
		timer2.setCycleCount(1);

		if (timer2 != null) {
			timer2.stop();
		}
			KeyFrame kf = new KeyFrame(Duration.seconds(1.4), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				jouer();
			}});

		timer2.getKeyFrames().add(kf);
		timer2.playFromStart();
		
		
	}
	
	public void aideProfesseur() throws IOException
	{
		professeur.setJetons(partie.getJoueurQuiJoue().getJetons());
		LinkedList<Jeton> meilleurMot=professeur.geMeilleurMot(partie);
		ImageView imgJeton;
		int x,y;
		int cpt=0;
		for(Jeton jt:meilleurMot)
		{
			double[] coordonnee=new double[2];
			
			Tuile t=fj.getPlateauV().getPlateau()[jt.getX()][jt.getY()];
			
			ImageView img = new ImageView(getClass().getClassLoader()
					.getResource("images/jetons/" + jt.getLettre() + ".png").toString());
			img.setFitHeight(((Tuile) t).getRec().getHeight() - 1);
			img.setFitWidth(((Tuile) t).getRec().getWidth() - 1);
		    t.getContainer().getChildren().add(img);

			 t.setC(jt.getLettre());
			this.postionJetonPose.add((Tuile) t);
			partie.getJoueurQuiJoue().removeJetonByChar(jt.getLettre());
			partie.getPlateau().setJeton(((Tuile) t).getX(), ((Tuile) t).getY(), jt.getLettre());
			
			coordonnee=fj.getIj().getCoordonneJeton(jt.getLettre());
			fj.getIj().removeJetonByChar(jt.getLettre());
			System.out.println("x="+jt.getX()+"  y="+jt.getY());
			
			img.setTranslateX(-1000);
			img.setTranslateY(0);
			
			TranslateTransition tt = new TranslateTransition();
			tt.setToX(img.getLayoutX());
			tt.setToY(img.getLayoutY());
			
			
			tt.setByY(1);
			tt.setNode(img);
			tt.setDuration(Duration.seconds(2));
			tt.play();
			
			fj.getMc().activerBoutonJouer();
			fj.getMc().activerBoutonReprendre();
			
		}
	}
	
	public void setJoueurQuijoue(Joueur j) throws IOException {
		fj.setInFoJoueur(j);
		fj.ajoutController(this);
		partie.setJoueur(j);
		partie.setJoueurJoueTour(true);

		ArrayList<Joueur> js = new ArrayList<Joueur>();

		for (Joueur jj : partie.getJoueurs()) {
			js.add(jj);
		}
		js.remove(partie.getJoueurQuiJoue());
		
		
		fj.getAfficheAutreJoueurs().setJoueurs(js);
	
		if(j.isIa()){
			PauseTransition pause = new PauseTransition(Duration.seconds(2));
			pause.setOnFinished(event ->
				{
					try {
						
						LinkedList<Jeton> meilleurMot=((JoueurIA)j).joueTour(partie);
						fj.getMc().verrouiller();
						if(meilleurMot!=null){
							poseMotIA(meilleurMot);
							
						}
						else{
							passeTour();
							
						}
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			);
			pause.play();
						
		}
		else{
			fj.getMc().deverouiller();
		}
	}

	public void passeTour() throws IOException {
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

	public void reprendre() throws IOException {
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

	public void echanger() throws IOException {
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
					try {
						passeTour();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}

		});

		timer.getKeyFrames().add(kf);
		timer.playFromStart();
	}
	
	public boolean finPartie(){
		if(partie.getSac().getJetons().isEmpty() && partie.getJoueurQuiJoue().getJetons().isEmpty()){
			Joueur vainqueur=partie.getVainqueurs();
			fj.getFinPartie().afficheVainqueur(vainqueur);
			return true;
		}
		else{
			return false;
		}
		
	}
	
	public void jouer()
	{
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
					if(!finPartie()){
						passeTour();
						premierTour = false;
						partie.setNbpointsCoupJoue(0);
					}
					
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
	}

	@Override
	public void handle(MouseEvent e) {

		Object o = e.getSource();

		if (o instanceof BoutonCustom) {

			if (((BoutonCustom) o).getT().getText().equals("Melanger") && ((BoutonCustom) o).isVerrouille()) {
				this.melanger();
									
			} else if (((BoutonCustom) o).getT().getText().equals("Passer") && ((BoutonCustom) o).isVerrouille()) {
				try {
					passeTour();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			 else if (((BoutonCustom) o).getT().getText().equals("Meilleur mot") && ((BoutonCustom) o).isVerrouille()) {
				try {
					
					int cptNbAide=partie.getJoueurQuiJoue().getCompteurAide();
					if(cptNbAide>0){
						aideProfesseur();
						cptNbAide--;
						fj.getFm().afficheMessage(""+cptNbAide);
						partie.getJoueurQuiJoue().setCompteurAide(cptNbAide);
					}
					else{
						fj.getFm().afficheMessage("Vous ne pouvez plus utiliser l'aide du Professeur !");
						}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				 //System.exit(0);
				}
			 else if (((BoutonCustom) o).getT().getText().equals("Jouer") && ((BoutonCustom) o).isVerrouille()) {
				jouer();

			} else if (((BoutonCustom) o).getT().getText().equals("Reprendre") && ((BoutonCustom) o).isVerrouille()) {
				try {
					reprendre();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				partie.getPlateau().reprendre();

			} else if (((BoutonCustom) o).getT().getText().equals("Dictionnaire") && ((BoutonCustom) o).isVerrouille()) {
				fj.getDico().setVisible(true);
			} else if (((BoutonCustom) o).getT().getText().equals("Echanger") && ((BoutonCustom) o).isVerrouille()) {
				fj.getEv().setInformation(partie.getSac(), partie.getJoueurQuiJoue());
				fj.getEv().setVisible(true);
			}

		} else if (o instanceof JetonV) {

			fj.getPlateauV().addJetonDrag(((JetonV) o));

			if (e.getEventType().equals(MouseEvent.MOUSE_RELEASED)) {

				Object t = fj.getPlateauV().getPositionJeton();

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

				if (((JetonV) o).isJetonPourJoker()) 
				{
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
				} 
				else if (((JetonV) o).isJetonPourEchanger()) {

					if (((JetonV) o).isSelectionnePourEchange()) {
						((JetonV) o).setSelectionnePourEchange(false);
						((JetonV) o).setTranslateY(0);
					} else {
						((JetonV) o).setSelectionnePourEchange(true);
						((JetonV) o).setTranslateY(-30);
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