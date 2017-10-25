package Controller;

import java.awt.Point;
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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ControllerPlateau implements EventHandler<MouseEvent>{

	private Plateau plateau;
	private PlateauV plateauView;
	private Partie partie;
	private FenetreJeu fj;
	private JetonV jetonv;
	private final Point pos = new Point();
	
	
	public ControllerPlateau(Partie partie,FenetreJeu fj)
	{
		this.partie=partie;
		this.fj=fj;
		fj.getMc().addControllers(this);
		setJoueurQuijoue(partie.getJoueurs().get(0));
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

	public void setJoueurQuijoue(Joueur j)
	{
		fj.setInFoJoueur(j);
		fj.ajoutController(this);
		partie.setJoueur(j);
		partie.setJoueurJoueTour(true);
	}
	
	
	public void melanger()
	{
		Collections.shuffle(partie.getJoueurQuiJoue().getJetons());
		fj.melangeJetons(partie.getJoueurQuiJoue());
	}
	

	@Override
	public void handle(MouseEvent e) {
	
		Object o=e.getSource();
		
		if(o instanceof BoutonCustom)
		{
			
			if(((BoutonCustom) o).getT().getText().equals("Melanger"))
			{
				this.melanger();
			}
			else if(((BoutonCustom) o).getT().getText().equals("Passer"))
			{
				this.setJoueurQuijoue(partie.nextJoueur());
			}
			else if(((BoutonCustom) o).getT().getText().equals("Reprendre"))
			{
				
			}
			else if(((BoutonCustom) o).getT().getText().equals("Dictionnaire"))
			{
				fj.getChildren().add(new Dictionnaire(1920,1080));
			}
			
		}
		else if(o instanceof JetonV)
		{
			fj.getP().addJetonDrag(((JetonV)o));
			
			if(e.getEventType().equals(MouseEvent.MOUSE_RELEASED))
			{
				
				Object t=fj.getP().getPositionJeton();
				
				if( t instanceof Tuile)
				{
					System.out.println("Jeton dans tuile");
					JetonV jt=((JetonV) o);
					
					ImageView img=new ImageView(getClass().getClassLoader().getResource("images/jetons/"+jt.getLettre()+".png").toString());
					img.setFitHeight(900/17);
					img.setFitWidth(900/17);
					((Tuile)t).getContainer().getChildren().add(img);
					
					partie.getJoueurQuiJoue().removeJetonByChar(jt.getLettre());
					//partie.getPlateau().getPlateau()[((Tuile) t).getX()][((Tuile) t).getY()].setP(new Jeton(jt.getLettre()));
					
					
					fj.getMc().activerBoutonReprendre();
					jt.setVisible(false);
				}
			}
			
		}
		
		
	}
}