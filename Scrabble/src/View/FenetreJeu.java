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
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class FenetreJeu extends StackPane{

	private int largeur,hauteur;
	private TextField motjoue;
	private InfoJoueur ij;
	private PlateauV p;
	private ControllerPlateau cp;
	private ImageView background;
	

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
		
		background=new ImageView(getClass().getClassLoader().getResource("images/background.jpeg").toString());
		background.setFitHeight(hauteur);
		background.setFitWidth(largeur);
		
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
		motjoue.setPickOnBounds(false);
		
		MenuCommande mc=new MenuCommande(height,width);
		//mc.setTranslateY(height*0.2);
		mc.setTranslateX(width*0.07);
		mc.setTranslateY(height*0.25);
		mc.setAlignment(Pos.CENTER_LEFT);
		mc.setPickOnBounds(false);
		
		
		 
		ij=new InfoJoueur();
		ij.setAlignment(Pos.TOP_LEFT);
		//ij.afficheInfoJetons();
		ij.afficheNom("Killua");
		ij.setPickOnBounds(false);
		ij.setTranslateX(-largeur*0.35);
		ij.setTranslateY(-hauteur*0.25);
		this.setPadding(new Insets(0.03*hauteur, 0.03*largeur,0.03*hauteur,0.03*largeur));
		this.getChildren().addAll(background,motjoue,p,ij,mc);
	
		
	
		
	
	}
	
	public InfoJoueur getIj() {
		return ij;
	}

	public void setIj(InfoJoueur ij) {
		this.ij = ij;
	}
	
	public ArrayList<JetonV> genereJetonV(ArrayList<Jeton> jetons)
	{
		ArrayList<JetonV> jetonv=new ArrayList<JetonV>();
		for(Jeton jt: jetons)
		{
			jetonv.add(new JetonV(jt.getLettre()));
		}
		
		return jetonv;
	}

	public void setInFoJoueur(Joueur j)
	{
		ij.afficheNom(j.getPseudo());
		ij.afficheInfoJetons(genereJetonV(j.getJetons()));
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