package Controller;

import java.awt.Point;
import java.util.ArrayList;

import Model.Jeton;
import Model.Joueur;
import Model.Partie;
import Model.Plateau;
import Model.Sac;
import View.FenetreJeu;
import View.JetonV;
import View.PlateauV;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
	}
	
	
	
	public static void main(String[] args) throws Exception 
	{
		
		
	
		FenetreJeu fen=new FenetreJeu();
		fen.main(args);
		
		
	}
	

	@Override
	public void handle(MouseEvent e) {
	
		JetonV test=(JetonV) e.getSource();
		
	}
}