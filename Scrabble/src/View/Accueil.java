package View;

import java.awt.Dimension;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import Controller.ControllerPlateau;
import Model.Joueur;
import Model.Partie;
import Model.Plateau;
import Model.Sac;
import javafx.animation.FadeTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Accueil extends StackPane  {
	private int largeur, hauteur;
	private ImageView background;
	private Stage stage2;
	private boolean echap;
	private EchapMenu echapMenu;

	public Accueil(Stage stage) {
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int) dimension.getHeight();
		int width = (int) dimension.getWidth();
		largeur = width;
		hauteur = height;
		echap=false;
		this.stage2=stage;
		
		background = new ImageView(getClass().getClassLoader().getResource("images/backgroundAccueil.jpg").toString());
		background.setFitHeight(hauteur);
		background.setFitWidth(largeur);
		background.setPickOnBounds(false);
		
		VBox menu=new VBox();
		
		 
		
		ImageView imageButtonNewGame=new ImageView(getClass().getClassLoader().getResource("images/newGame.png").toString());
		imageButtonNewGame.setFitHeight(height*0.10);
		imageButtonNewGame.setFitWidth(width*0.25);	
		
		ImageView imageQuitterAccueil=new ImageView(getClass().getClassLoader().getResource("images/quitterAccueil.png").toString());
		imageQuitterAccueil.setFitHeight(height*0.10);
		imageQuitterAccueil.setFitWidth(width*0.25);	
		
		ImageView imageChargerPartie=new ImageView(getClass().getClassLoader().getResource("images/chargerPartie.png").toString());
		imageChargerPartie.setFitHeight(height*0.10);
		imageChargerPartie.setFitWidth(width*0.25);	
		
		
		
		menu.getChildren().addAll(imageButtonNewGame,imageChargerPartie,imageQuitterAccueil);
		
		imageButtonNewGame.setOnMouseEntered(e->{
			DropShadow ds = new DropShadow();
            ds.setOffsetY(10.0);
            ds.setOffsetX(10.0);
            ds.setColor(Color.BLACK);
           
            imageButtonNewGame.setEffect(ds);
			
		});
		
		imageButtonNewGame.setOnMouseExited(e->{
			imageButtonNewGame.setEffect(null);
		});
		

		imageButtonNewGame.setOnMouseClicked(e->{ 
		
		      
			ModeDeJeu1 mj1 = new ModeDeJeu1(stage);
			mj1.setVisible(true);	
			stage.getScene().setRoot(mj1);
			stage.show();
			this.setVisible(false);
		});
			
		imageChargerPartie.setOnMouseEntered(e->{
			DropShadow ds = new DropShadow();
            ds.setOffsetY(10.0);
            ds.setOffsetX(10.0);
            ds.setColor(Color.BLACK);
           
            imageChargerPartie.setEffect(ds);
			
		});
		
		imageChargerPartie.setOnMouseExited(e->{
			imageChargerPartie.setEffect(null);
		});
		

		imageChargerPartie.setOnMouseClicked(e->{ 

			FileChooser fileChooser = new FileChooser();
			 fileChooser.setTitle("Open Resource File");
			 //fileChooser.setInitialDirectory(new File(getClass().getResource("sauvegarde").toString()));
			 
			        
			 File selectedFile = fileChooser.showOpenDialog(stage);
			 if (selectedFile != null) {
			    //stage.display(selectedFile);
			 }
			
			System.out.println(selectedFile);
			FileInputStream fis=null;
			try {
				fis = new FileInputStream(selectedFile);
				
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			ObjectInputStream ois = null;
			try {
				ois = new ObjectInputStream(fis);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			try {
				Partie p=(Partie) ois.readObject();
				System.out.println("Pseudo "+p.getJoueurs().get(0).getPseudo());
				
				FenetreJeu fj=new FenetreJeu(stage);
				ControllerPlateau cp=new ControllerPlateau(p,fj,true);
				echapMenu=new EchapMenu(largeur,hauteur,p,stage);
				echapMenu.setVisible(false);
				fj.getChildren().add(echapMenu);
				fj.setVisible(true);
				stage.getScene().setRoot(fj);
				
				stage.getScene().setOnKeyPressed(ee->{
					
					if(ee.getCode()==KeyCode.ESCAPE)
					{
						echap=!echap;
						this.echapMenu.setVisible(echap);
					}
				});	
				
			} catch (ClassNotFoundException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		


		imageQuitterAccueil.setOnMouseEntered(e->{
			DropShadow ds = new DropShadow();
            ds.setOffsetY(10.0);
            ds.setOffsetX(10.0);
            ds.setColor(Color.BLACK);
            
            imageQuitterAccueil.setEffect(ds);
			
		});
		
		imageQuitterAccueil.setOnMouseExited(e->{
			imageQuitterAccueil.setEffect(null);
		});
		
		imageQuitterAccueil.setOnMouseClicked(e->{
			System.exit(0);
		});
	
		menu.setSpacing(hauteur*0.05);
		menu.setAlignment(Pos.CENTER);
		this.setPadding(new Insets(0.03 * hauteur, 0.03 * largeur, 0.03 * hauteur, 0.03 * largeur));
		this.getChildren().addAll(background,menu);
	
	}

}
