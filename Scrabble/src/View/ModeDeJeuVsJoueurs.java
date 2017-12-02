package View;

import java.awt.Dimension;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import Controller.ControllerPlateau;
import Model.Joueur;
import Model.JoueurIA;
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
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ModeDeJeuVsJoueurs extends StackPane  {
	private int largeur, hauteur;
	private ImageView background;
	private Stage stage2;
	private boolean echap;
	private EchapMenu echapMenu;

	public ModeDeJeuVsJoueurs(Stage stage) {
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
		
		ImageView imageButtonJouer=new ImageView(getClass().getClassLoader().getResource("images/etoile.png").toString());
		imageButtonJouer.setFitHeight(height*0.10);
		imageButtonJouer.setFitWidth(width*0.25);	
		
		ImageView imageQuitterAccueil=new ImageView(getClass().getClassLoader().getResource("images/quitterAccueil.png").toString());
		imageQuitterAccueil.setFitHeight(height*0.10);
		imageQuitterAccueil.setFitWidth(width*0.25);	
		
		menu.getChildren().addAll(imageButtonJouer,imageQuitterAccueil);
		
		imageButtonJouer.setOnMouseEntered(e->{
			DropShadow ds = new DropShadow();
            ds.setOffsetY(10.0);
            ds.setOffsetX(10.0);
            ds.setColor(Color.BLACK);
            
            imageButtonJouer.setEffect(ds);
			
		});
		
		imageButtonJouer.setOnMouseExited(e->{
			imageButtonJouer.setEffect(null);
		});
		

		imageButtonJouer.setOnMouseClicked(e->{ 
			
			FenetreJeu fj =new FenetreJeu();
			fj.setVisible(true);
			Joueur j1=new Joueur(1,"J1",0,false);
			JoueurIA j2=new JoueurIA(2,"IA2",0,4);
			JoueurIA j3=new JoueurIA(2,"IA3",0,4);
			JoueurIA j4=new JoueurIA(2,"IA4",0,4);
			
			
			ArrayList<Joueur> joueurs=new ArrayList<Joueur>();
			joueurs.add(j1);
			joueurs.add(j2);
			joueurs.add(j3);
			joueurs.add(j4);
	
			
			Sac sac=new Sac();
			Plateau plateau=new Plateau();
			
			j1.piocherDebutPartie(sac);
			j2.piocherDebutPartie(sac);
			j3.piocherDebutPartie(sac);
			j4.piocherDebutPartie(sac);
			
			Partie p=null;
			try {
				p = new Partie(joueurs,sac,plateau,true);
				echapMenu=new EchapMenu(largeur,hauteur,p);
				echapMenu.setVisible(false);
				fj.getChildren().add(echapMenu);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
			try {
				ControllerPlateau cp =new ControllerPlateau(p,fj);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			Scene scene=new Scene(fj);
			
			scene.setOnKeyPressed(ee->{
				
				if(ee.getCode()==KeyCode.ESCAPE)
				{
					echap=!echap;
					this.echapMenu.setVisible(echap);
				}
			});	
			
			
			stage.setScene(scene);
			stage.setHeight(hauteur);
			stage.setWidth(largeur);
			stage.setFullScreen(true);
			stage.setMaximized(true);
			stage.show();
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
