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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
		
		
		MenuCommande mc=new MenuCommande(height,width);
		mc.setTranslateY(height*0.2);
		mc.setTranslateX(width*0.4);
		
		 Rectangle rec = new Rectangle();
	        rec.setWidth(800/15);
	        rec.setHeight(800/15);
	        rec.setStroke(Color.BLACK); 
	        rec.setFill(Color.TRANSPARENT);
	        rec.setStrokeWidth(2.5);
	        rec.setArcHeight(10);
	        rec.setArcWidth(10);
	        rec.setFill(Color.BEIGE);
	        rec.setOnMouseEntered(new EventHandler<MouseEvent>(){

				@Override
				public void handle(MouseEvent e) {
					System.out.println("Dedan");
					rec.setFill(Color.GREENYELLOW);
				}
	        	
	        });
	        StackPane spp=new StackPane();
	        spp.getChildren().add(rec);
	        
	       
	        
		StackPane layout=new StackPane();
		
		ij=new InfoJoueur();
		ij.setAlignment(Pos.TOP_LEFT);
		//ij.afficheInfoJetons();
		ij.afficheNom("Killua");
		this.getChildren().addAll(background,motjoue,mc,ij,spp,p);
	
		
	
		
	
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