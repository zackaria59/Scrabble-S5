package View;

import java.awt.Dimension;
import java.util.ArrayList;

import Controller.ControllerPlateau;
import Model.Jeton;
import Model.Joueur;
import Model.Partie;
import Model.Plateau;
import Model.Sac;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class FenetreJeu extends StackPane{

	private int largeur,hauteur;
	private TextField motjoue;
	private InfoJoueur ij;
	private PlateauV p;
	private ControllerPlateau cp;
	

	public PlateauV getP() {
		return p;
	}

	public void setP(PlateauV p) {
		this.p = p;
	}
	

	public FenetreJeu() {
		
		// On récupère la résolution de l'écran
		
		
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int)dimension.getHeight();
		int width  = (int)dimension.getWidth();
		largeur=width;
		hauteur=height;
		
		
		p=new PlateauV(largeur*0.41);
		p.setTranslateX(largeur*0.08);
		p.setTranslateY(-hauteur*0.05);
		
		
		motjoue=new TextField("");
		motjoue.setMaxWidth(largeur*0.35);
		motjoue.setMaxHeight(hauteur*0.05);
		motjoue.setTranslateY(hauteur*0.43);
		motjoue.setFocusTraversable(false);
		motjoue.setPromptText("Entrée un mot");
		motjoue.autosize();
		
		
		MenuCommande mc=new MenuCommande(height,width);
		mc.setTranslateY(height*0.2);
		mc.setTranslateX(-width*0.4);
		
		StackPane layout=new StackPane();
		
		ij=new InfoJoueur();
		ij.setAlignment(Pos.TOP_LEFT);
		//ij.afficheInfoJetons();
		ij.afficheNom("Killua");
		this.getChildren().addAll(p,motjoue,mc,ij);
	
		
	
		
	
	}
	
	public InfoJoueur getIj() {
		return ij;
	}

	public void setIj(InfoJoueur ij) {
		this.ij = ij;
	}

	public void setInFoJoueur(ArrayList<JetonV> jv)
	{
		ij.afficheInfoJetons(jv);
	}
	
	public void afficheNomJoueur(String n)
	{
		ij.afficheNom(n);
	}
	
	public void ajoutController(EventHandler<MouseEvent> cp)
	{
		ij.setControllerJetonV(cp);
	}
	
	public static void main(String[] args)
	{
		
		
		
	}

}