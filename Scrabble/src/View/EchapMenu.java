package View;

import java.awt.RenderingHints.Key;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

import Controller.ControllerPlateau;
import Model.Partie;
import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.util.Duration;

public class EchapMenu extends StackPane {

	private Rectangle fenetre,background,valider,reprendreREC,sauvegarderREC,chargerREC,quitterREC;
	private Text msg;
	private VBox menu;

	private EchapMenu echapMenu;
	private boolean echap;
	public EchapMenu(double largeur,double hauteur,Partie partie,Stage stage)
	{
		
		msg=new Text("");
		msg.setFont(Font.loadFont("file:ressource/police/Munich.ttf",45));
		msg.setPickOnBounds(false);
		
		background=new Rectangle();
		background.setHeight(hauteur);
		background.setWidth(largeur);
		background.setFill(Color.web("rgba(0,0,0,0.8)"));
		
		fenetre=new Rectangle();
		fenetre.setWidth(largeur/4);
		fenetre.setHeight(hauteur*0.6);
		fenetre.setArcHeight(60);
		fenetre.setArcWidth(60);
		fenetre.setStroke(Color.CHOCOLATE);
        fenetre.setStrokeWidth(10);
		fenetre.setFill(Color.web("rgba(255,255,255,1)"));
		
		DropShadow ds = new DropShadow();
        ds.setOffsetY(10.0);
        ds.setOffsetX(10.0);
        ds.setColor(Color.BLACK);
		
		Text reprendre=new Text("Reprendre");
		reprendre.setFont(Font.loadFont("file:ressource/police/Munich.ttf",45));
		reprendre.setPickOnBounds(false);
		
		reprendreREC=new Rectangle();
		reprendreREC.setWidth(largeur/6);
		reprendreREC.setHeight(hauteur/12);
		reprendreREC.setArcHeight(60);
		reprendreREC.setArcWidth(60);
		reprendreREC.setStroke(Color.BLACK);
		reprendreREC.setStrokeWidth(7);
		reprendreREC.setFill(Color.web("rgba(25,245,25,0.9)"));
		reprendreREC.setOnMouseClicked(e->{
			this.setVisible(false);
		});
		StackPane bouton=new StackPane();
		bouton.getChildren().addAll(reprendreREC,reprendre);
		bouton.setAlignment(Pos.CENTER);
		
		
		Text sauvegarder=new Text("Sauvegarder");
		sauvegarder.setFont(Font.loadFont("file:ressource/police/Munich.ttf",45));
		sauvegarder.setPickOnBounds(false);
		
		sauvegarderREC=new Rectangle();
		sauvegarderREC.setWidth(largeur/6);
		sauvegarderREC.setHeight(hauteur/12);
		sauvegarderREC.setArcHeight(60);
		sauvegarderREC.setArcWidth(60);
		sauvegarderREC.setStroke(Color.BLACK);
		sauvegarderREC.setStrokeWidth(7);
		sauvegarderREC.setFill(Color.web("rgba(25,245,25,0.9)"));
		sauvegarderREC.setOnMouseClicked(e->{
			
			FileOutputStream fos=null;
			
			String format = "dd-MM-yy-H-mm"; 

			java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat( format ); 
			java.util.Date date = new java.util.Date(); 

			
			try {
				fos = new FileOutputStream("Save_"+formater.format( date )+".serial");
				//fos = new FileOutputStream("singleton.serial");
			} catch (FileNotFoundException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		
			ObjectOutputStream oos=null;
			try {
				oos = new ObjectOutputStream(fos);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				partie.sauvegardePlateau=partie.getPlateau().getPlateau();
				partie.getPlateau().affichePlateauJetonValide();
				partie.getPlateau().videList();
				oos.writeObject(partie);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		});
		
		StackPane bouton2=new StackPane();
		bouton2.getChildren().addAll(sauvegarderREC,sauvegarder);
		bouton2.setAlignment(Pos.CENTER);
		
		Text charger=new Text("Charger");
		charger.setFont(Font.loadFont("file:ressource/police/Munich.ttf",45));
		charger.setPickOnBounds(false);
		
		chargerREC=new Rectangle();
		chargerREC.setWidth(largeur/6);
		chargerREC.setHeight(hauteur/12);
		chargerREC.setArcHeight(60);
		chargerREC.setArcWidth(60);
		chargerREC.setStroke(Color.BLACK);
		chargerREC.setStrokeWidth(7);
		chargerREC.setFill(Color.web("rgba(25,245,25,0.9)"));
		
		StackPane bouton3=new StackPane();
		bouton3.getChildren().addAll(chargerREC,charger);
		bouton3.setAlignment(Pos.CENTER);
		
		Text quitter=new Text("Quitter");
		quitter.setFont(Font.loadFont("file:ressource/police/Munich.ttf",45));
		quitter.setPickOnBounds(false);
		
		quitterREC=new Rectangle();
		quitterREC.setWidth(largeur/6);
		quitterREC.setHeight(hauteur/12);
		quitterREC.setArcHeight(60);
		quitterREC.setArcWidth(60);
		quitterREC.setStroke(Color.BLACK);
		quitterREC.setStrokeWidth(7);
		quitterREC.setFill(Color.web("rgba(25,245,25,0.9)"));
		
		StackPane bouton4=new StackPane();
		bouton4.getChildren().addAll(quitterREC,quitter);
		bouton4.setAlignment(Pos.CENTER);
		
		menu=new VBox();
		menu.getChildren().addAll(bouton,bouton2,bouton3,bouton4);
		menu.setAlignment(Pos.CENTER);
		menu.setSpacing(15);
		VBox.setMargin(bouton, new Insets(hauteur*0.08,0,0,0));
		echap=false;
		
		reprendreREC.setOnMouseEntered(e->{
			reprendreREC.setEffect(ds);
		});
		
		reprendreREC.setOnMouseExited(e->{
			reprendreREC.setEffect(null);
		});
		
		sauvegarderREC.setOnMouseEntered(e->{
			sauvegarderREC.setEffect(ds);
		});
		
		sauvegarderREC.setOnMouseExited(e->{
			sauvegarderREC.setEffect(null);
		});
		
		quitterREC.setOnMouseEntered(e->{
			quitterREC.setEffect(ds);
		});
		
		quitterREC.setOnMouseExited(e->{
			quitterREC.setEffect(null);
		});
		
		quitterREC.setOnMousePressed(e->{
			System.exit(0);
		});
		
		chargerREC.setOnMouseEntered(e->{
			chargerREC.setEffect(ds);
		});
		
		chargerREC.setOnMouseExited(e->{
			chargerREC.setEffect(null);
		});
		
		chargerREC.setOnMousePressed(e->{
			
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
		
		

		this.getChildren().addAll(background,fenetre,menu);
	}
	

}
