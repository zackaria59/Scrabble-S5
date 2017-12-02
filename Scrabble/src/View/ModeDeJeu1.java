package View;

import java.awt.Dimension;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import Controller.ControllerPlateau;
import Model.Joueur;
import Model.Partie;
import Model.Plateau;
import Model.Sac;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ModeDeJeu1 extends StackPane  {
	private int largeur, hauteur;
	private ImageView background;
	private Stage stage2;
	

	public ModeDeJeu1(Stage stage) {
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int) dimension.getHeight();
		int width = (int) dimension.getWidth();
		largeur = width;
		hauteur = height;

		this.stage2=stage;
		
		background = new ImageView(getClass().getClassLoader().getResource("images/backgroundAccueil.jpg").toString());
		background.setFitHeight(hauteur);
		background.setFitWidth(largeur);
		background.setPickOnBounds(false);
		
		VBox menu=new VBox();
		
		
		ImageView imageButtonJoueur=new ImageView(getClass().getClassLoader().getResource("images/joueur.png").toString());
		imageButtonJoueur.setFitHeight(height*0.10);
		imageButtonJoueur.setFitWidth(width*0.25);	
		
		ImageView imageButtonOrdinateur=new ImageView(getClass().getClassLoader().getResource("images/ordinateur.png").toString());
		imageButtonOrdinateur.setFitHeight(height*0.10);
		imageButtonOrdinateur.setFitWidth(width*0.25);	
		
		menu.getChildren().addAll(imageButtonJoueur,imageButtonOrdinateur);
		
		imageButtonJoueur.setOnMouseEntered(e->{
			DropShadow ds = new DropShadow();
            ds.setOffsetY(10.0);
            ds.setOffsetX(10.0);
            ds.setColor(Color.BLACK);
            
            imageButtonJoueur.setEffect(ds);
			
		});
		
		imageButtonJoueur.setOnMouseExited(e->{
			imageButtonJoueur.setEffect(null);
		});
		

		imageButtonJoueur.setOnMouseClicked(e->{ 
			
			ModeDeJeuVsJoueurs mj2 = new ModeDeJeuVsJoueurs(stage);
			mj2.setVisible(true);
			Scene scene=new Scene(mj2);
			stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
			stage.setScene(scene);
			stage.setHeight(hauteur);
			stage.setWidth(largeur);
			stage.setFullScreen(true);
			stage.setMaximized(true);
			stage.show();
		});
		
		imageButtonOrdinateur.setOnMouseEntered(e->{
			DropShadow ds = new DropShadow();
            ds.setOffsetY(10.0);
            ds.setOffsetX(10.0);
            ds.setColor(Color.BLACK);
            
            imageButtonOrdinateur.setEffect(ds);
			
		});
		
		imageButtonOrdinateur.setOnMouseExited(e->{
			imageButtonOrdinateur.setEffect(null);
		});
		
		imageButtonOrdinateur.setOnMouseClicked(e->{
			ModeDeJeuVsIA mjia = new ModeDeJeuVsIA(stage);
			mjia.setVisible(true);
			Scene scene=new Scene(mjia);
			stage.setScene(scene);
			stage.setHeight(hauteur);
			stage.setWidth(largeur);
			stage.setFullScreen(true);
			stage.setMaximized(true);
			stage.show();
		});
	
		menu.setSpacing(hauteur*0.05);
		menu.setAlignment(Pos.CENTER);
		this.setPadding(new Insets(0.03 * hauteur, 0.03 * largeur, 0.03 * hauteur, 0.03 * largeur));
		this.getChildren().addAll(background,menu);
	
	}

}
