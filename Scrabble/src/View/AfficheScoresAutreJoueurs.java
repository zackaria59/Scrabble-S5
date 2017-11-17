package View;

import java.awt.Dimension;
import java.util.ArrayList;

import Model.Joueur;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class AfficheScoresAutreJoueurs extends StackPane {

	private double largeur,hauteur;
	private ImageView background;
	private int nbjoueur;
	private Label[] labelNomScore;
	private double espace;
	private double taillePolice;
	
	public AfficheScoresAutreJoueurs(double largeur,double hauteur)
	{
		this.largeur=largeur;
		this.hauteur=hauteur;
		espace=hauteur*0.065;
		this.setHeight(hauteur/3);
		this.setWidth(largeur/5);
		this.setTranslateX(largeur*0.42);
		this.setTranslateY(-hauteur*0.42);
		this.setPickOnBounds(false);
		
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		taillePolice=dimension.height*0.035;
		
		
		
		background=new ImageView(getClass().getClassLoader().getResource("images/panelScoreJ4.png").toString());
		background.setFitHeight(hauteur*0.33);
		background.setFitWidth(largeur*0.12);
		background.setTranslateY(hauteur*0.1);
		background.setPickOnBounds(false);
		this.getChildren().add(background);
	}
	
	public void setBackgroundByNbJoueur(int nbjoueur)
	{	
		this.nbjoueur=nbjoueur;
		labelNomScore=new Label[nbjoueur];
		for(int i=0;i<nbjoueur;i++)
		{
			labelNomScore[i]=new Label();
			this.getChildren().add(labelNomScore[i]);
		}
	}
	
	public void setJoueurs(ArrayList<Joueur> joueurs)
	{
		for(int i=0;i<joueurs.size();i++)
		{
			labelNomScore[i].setText(joueurs.get(i).getPseudo()+"   "+joueurs.get(i).getScore());
			labelNomScore[i].setFont(Font.loadFont("file:ressource/police/Munich.ttf",taillePolice));
			labelNomScore[i].setTextFill(Color.WHITE);
			labelNomScore[i].setTranslateY((i+1)*espace);
			
			
		}
	}
}
