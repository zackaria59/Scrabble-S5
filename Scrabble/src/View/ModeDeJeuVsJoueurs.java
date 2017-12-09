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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ModeDeJeuVsJoueurs extends StackPane  {
	private int largeur, hauteur;
	private boolean echap;
	private EchapMenu echapMenu;
	
	/***************/
	private Stage stage;
	private int nombreJoueur;
	private DropShadow ds;
	private Text titre,joueur[];
	private TextField nomJoueur[];

	private Label nbJoueurChoix;
	private ImageView flecheG,flecheD,containerChoixNbJ,backgroundNomJoueur[],buttonPrecedent,buttonLancer;
	
	private StackPane spChoixNbJoueur,spContainerNomJoueur[];
	private HBox containerChoixNbJoueur,containerInfoJoueur[];

	private VBox containerInfoToutLesJoueurs;
	
	private ToggleButtonSwitch buttonSwitch,buttonSwitch2;
	
	public ModeDeJeuVsJoueurs(Stage stage) {
		
		this.stage=stage;
		
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int) dimension.getHeight();
		int width = (int) dimension.getWidth();
		
		largeur = width;
		hauteur = height;
		echap=false;
		
		nombreJoueur=2;
		
		this.setStyle("-fx-background-color:#443522;"); //Couleur du fond du background
		
		
		 /******************************* La vue pour choisir le nombre de joueur *****************/
		
		titre=new Text("Nombre de joueur");
		titre.setFont(Font.loadFont("file:ressource/police/LONSDALE.OTF",hauteur*0.07));
		titre.setFill(Color.rgb(246 ,225 ,204 ));
		titre.setPickOnBounds(false);
        
		flecheG=new ImageView(getClass().getClassLoader().getResource("images/moins.png").toString());
		flecheG.setFitHeight(hauteur/23);
		flecheG.setFitWidth(largeur/23);
		flecheG.setTranslateY(hauteur*0.01);
		flecheG.setTranslateX(-largeur*0.01);
		
		
		flecheD=new ImageView(getClass().getClassLoader().getResource("images/plus.png").toString());
		flecheD.setFitHeight(hauteur/23);
		flecheD.setFitWidth(largeur/23);
		flecheD.setTranslateY(hauteur*0.01);
		flecheD.setTranslateX(largeur*0.01);
		
		nbJoueurChoix=new Label("2");
		nbJoueurChoix.setFont(Font.loadFont("file:ressource/police/LONSDALE.OTF",hauteur*0.05));
		nbJoueurChoix.setTextFill(Color.rgb(246 ,225 ,204 ));
		nbJoueurChoix.setPickOnBounds(false);
		
		
		containerChoixNbJ=new ImageView(getClass().getClassLoader().getResource("images/caseNbJoueur.png").toString());
		containerChoixNbJ.setFitHeight(hauteur/15);
		containerChoixNbJ.setFitWidth(largeur/15);
		containerChoixNbJ.setPickOnBounds(false);
		
		spChoixNbJoueur=new StackPane();
		spChoixNbJoueur.getChildren().addAll(containerChoixNbJ,nbJoueurChoix);
		spChoixNbJoueur.setAlignment(Pos.TOP_CENTER);
		spChoixNbJoueur.setPickOnBounds(false);
		
		 containerChoixNbJoueur=new HBox();
		 containerChoixNbJoueur.getChildren().addAll(titre,flecheG,spChoixNbJoueur,flecheD);
		
		containerChoixNbJoueur.setTranslateY(hauteur/5);
		containerChoixNbJoueur.setMargin(titre, new Insets(0,largeur/14,0,0));
		containerChoixNbJoueur.setPickOnBounds(false);
		
		/**********************************************************************************************/

		
		/************************* La vue qui affiche le nom des joueurs (dynamic en fonction du nombre selectionné **/
		
		containerInfoToutLesJoueurs=new VBox();
		joueur=new Text[4];
		nomJoueur=new TextField[4];
		backgroundNomJoueur=new ImageView[4];
		spContainerNomJoueur=new StackPane[4];
		containerInfoJoueur=new HBox[4];
		
		initialiseSelectionNomJoueurs();
		afficheSelectionNomJoueurs();
		
		/******************************************************************************************/

		Rectangle ligne=new Rectangle();
		ligne.setWidth(largeur*0.005);
		ligne.setHeight(hauteur*0.75);
		ligne.setArcHeight(20);
		ligne.setArcWidth(20);
		ligne.setFill(Color.rgb(246 ,225 ,204 ));
		ligne.setTranslateY(hauteur*0.1);
		ligne.setPickOnBounds(false);
		
		VBox option=new VBox();
		option.setAlignment(Pos.TOP_CENTER);
		option.setTranslateX(largeur*0.2);
		option.setTranslateY(hauteur*0.2);
		option.setSpacing(hauteur*0.075);
		option.setPickOnBounds(false);
		
		/***********************************     La vue pour l'option timer    ***********************/
		Text optionText=new Text("Options");
		optionText.setFont(Font.loadFont("file:ressource/police/LONSDALE.OTF",hauteur*0.07));
		optionText.setFill(Color.rgb(246 ,225 ,204 ));
		optionText.setPickOnBounds(false);
		
		HBox containerOption1=new HBox();
		containerOption1.setSpacing(largeur*0.03);
		containerOption1.setAlignment(Pos.CENTER);
		containerOption1.setTranslateX(-largeur*0.03);
		
		Text nomOption1=new Text("Timer");
		nomOption1.setFont(Font.loadFont("file:ressource/police/LONSDALE.OTF",hauteur*0.05));
		nomOption1.setFill(Color.rgb(246 ,225 ,204 ));
		nomOption1.setPickOnBounds(false);
		
		buttonSwitch=new ToggleButtonSwitch();
		buttonSwitch.setMaxWidth(largeur*0.06);
		
		containerOption1.getChildren().addAll(nomOption1,buttonSwitch);
		/**********************************************************************************************/
		
		/***********************************     La vue pour l'option professeur    ***********************/
		HBox containerOption2=new HBox();
		containerOption2.setSpacing(largeur*0.03);
		containerOption2.setAlignment(Pos.CENTER);
		containerOption2.setTranslateX(-largeur*0.07);
		
		Text nomOption2=new Text("Aide professeur");
		nomOption2.setFont(Font.loadFont("file:ressource/police/LONSDALE.OTF",hauteur*0.05));
		nomOption2.setFill(Color.rgb(246 ,225 ,204 ));
		nomOption2.setPickOnBounds(false);
		
		buttonSwitch2=new ToggleButtonSwitch();
		buttonSwitch2.setMaxWidth(largeur*0.06);
		
		containerOption2.getChildren().addAll(nomOption2,buttonSwitch2);

		/**********************************************************************************************/

		
		option.getChildren().addAll(optionText,containerOption1,containerOption2);
		
		buttonPrecedent=new ImageView(getClass().getClassLoader().getResource("images/precedent.png").toString());
		buttonPrecedent.setFitHeight(hauteur*0.08);
		buttonPrecedent.setFitWidth(largeur*0.06);
		
		
		buttonLancer=new ImageView(getClass().getClassLoader().getResource("images/buttonLancer.png").toString());
		buttonLancer.setFitHeight(hauteur*0.2);
		buttonLancer.setFitWidth(largeur*0.3);
		
		configurationEvent();
		
		this.setPadding(new Insets(0.03 * hauteur, 0.03 * largeur, 0.03 * hauteur, 0.03 * largeur));
		this.getChildren().addAll(containerChoixNbJoueur,containerInfoToutLesJoueurs,ligne,option,buttonPrecedent,buttonLancer);
		this.setAlignment(buttonPrecedent, Pos.TOP_LEFT);
		this.setAlignment(buttonLancer, Pos.BOTTOM_RIGHT);
	}

	
	private void lancerParti() {
		
		FenetreJeu fj =new FenetreJeu();
		fj.setVisible(true);
		
		boolean timer=false;
		
		if(buttonSwitch.isOn)timer=true;
		else{
			timer=false;
		}
		ArrayList<Joueur> joueurs=new ArrayList<Joueur>();
		
		for(int i=0;i<nombreJoueur;i++)
		{
			joueurs.add(new Joueur(1,nomJoueur[i].getText(),0,false));
		}
		
		Sac sac=new Sac();
		Plateau plateau=new Plateau();
		
		for(int i=0;i<nombreJoueur;i++)
		{
			joueurs.get(i).piocherDebutPartie(sac);
		}
		
		Partie p=null;
		try {
			p = new Partie(joueurs,sac,plateau,timer);
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
		
		
		
		stage.getScene().setOnKeyPressed(ee->{
			
			if(ee.getCode()==KeyCode.ESCAPE)
			{
				echap=!echap;
				this.echapMenu.setVisible(echap);
			}
		});	
		stage.getScene().setRoot(fj);
		stage.show();
		this.setVisible(false);
		
	}


	public void initialiseSelectionNomJoueurs()
	{
		for(int i=0;i<4;i++)
		{
			
			joueur[i]=new Text("Joueur "+(i+1)+"   ");
			joueur[i].setFont(Font.loadFont("file:ressource/police/LONSDALE.OTF",hauteur*0.05));
			joueur[i].setFill(Color.rgb(246 ,225 ,204 ));
			joueur[i].setTranslateY(hauteur*0.035);
			joueur[i].setPickOnBounds(false);
	
			backgroundNomJoueur[i]=new ImageView(getClass().getClassLoader().getResource("images/caseNomJoueur.png").toString());
			backgroundNomJoueur[i].setPickOnBounds(false);
			
			nomJoueur[i]=new TextField();
			nomJoueur[i].setFont(Font.loadFont("file:ressource/police/LONSDALE.OTF",hauteur*0.05));
			nomJoueur[i].setAlignment(Pos.CENTER);
			nomJoueur[i].setStyle("-fx-background-color:#443522; -fx-control-inner-background:#443522;-fx-text-inner-color: #f6e1cc;-fx-background-insets: 0;-fx-padding: 10 3 1 3;");
			nomJoueur[i].setPickOnBounds(false);
			nomJoueur[i].setPromptText("Nom du joueur "+(i+1)+"");
			
			//nomJoueur[1].setText(Color.rgb(246 ,225 ,204 ));
			
			spContainerNomJoueur[i]=new StackPane();
			spContainerNomJoueur[i].getChildren().addAll(backgroundNomJoueur[i],nomJoueur[i]);
			spContainerNomJoueur[i].setPickOnBounds(false);
			
			containerInfoJoueur[i]=new HBox();
			containerInfoJoueur[i].getChildren().addAll(joueur[i],spContainerNomJoueur[i]);
			containerInfoJoueur[i].setPickOnBounds(false);
		
		}
		
		containerInfoToutLesJoueurs.getChildren().addAll(containerInfoJoueur);
		containerInfoToutLesJoueurs.setSpacing(10);
		containerInfoToutLesJoueurs.setAlignment(Pos.BOTTOM_CENTER);
		containerInfoToutLesJoueurs.setPickOnBounds(false);
		
		
	}
	
	public void afficheSelectionNomJoueurs()
	{
		
		for(int i=0;i<4;i++)
		{
			if(i<this.nombreJoueur)
			{
				containerInfoJoueur[i].setVisible(true);
			}
			else{
				containerInfoJoueur[i].setVisible(false);
			}
		}
	}
	
	public void configurationEvent()
	{
		
		ds = new DropShadow();
        ds.setOffsetY(10.0);
        ds.setOffsetX(10.0);
        ds.setColor(Color.BLACK);
        
        
		buttonPrecedent.setOnMousePressed(e->{
			
			ModeDeJeu1 mj1 = new ModeDeJeu1(stage);
			mj1.setVisible(true);	
			stage.getScene().setRoot(mj1);
			stage.show();
	
		});
		
		buttonPrecedent.setOnMouseEntered(e->{
			
			
			DropShadow ds = new DropShadow();
            ds.setOffsetY(10.0);
            ds.setOffsetX(10.0);
            ds.setColor(Color.BLACK);
		
            buttonPrecedent.setEffect(ds);
            
		});
		
		buttonPrecedent.setOnMouseExited(e->{
			buttonPrecedent.setEffect(null);
		});
		
		/************************************/
		
		buttonLancer.setOnMousePressed(e->{
			
			lancerParti();
	
		});
		
		buttonLancer.setOnMouseEntered(e->{
		
            buttonLancer.setEffect(ds);
            
		});
		
		buttonLancer.setOnMouseExited(e->{
			buttonLancer.setEffect(null);
		});
		
		/***************************************/
		
		flecheG.setOnMousePressed(e->{
			
			if(2<nombreJoueur){
				nombreJoueur--;
			this.afficheSelectionNomJoueurs();
			nbJoueurChoix.setText(""+nombreJoueur);
			}
			
			System.out.println("OK");
		});
		
		/****************************************/
		
		flecheD.setOnMousePressed(e->{
			if(4>nombreJoueur){
				nombreJoueur++;
			this.afficheSelectionNomJoueurs();
			nbJoueurChoix.setText(""+nombreJoueur);
			}
		});
	}
}
