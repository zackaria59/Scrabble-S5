package View;

import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class Dictionnaire extends StackPane{
	
	private TextField motRecherche;
	private Label lab,afficheResultat;
	private VBox hb;
	private Rectangle background;
	private Button quitter;
	
	public Dictionnaire(double largeur,double hauteur)
	{
		
		this.setHeight(hauteur);
		this.setWidth(largeur);
		this.setAlignment(Pos.CENTER);
		
		hb=new VBox();
		hb.setAlignment(Pos.CENTER);
		hb.setMaxHeight(hauteur);
		hb.setMaxWidth(largeur);
		
		motRecherche=new TextField();
		lab=new Label("Veuillez entre un mot");
		afficheResultat=new Label();
		quitter=new Button("Quitter");
		quitter.setFont(Font.loadFont("file:ressource/police/Munich.ttf",32));
		
		motRecherche.setMaxWidth(largeur*0.45);
		motRecherche.setMaxHeight(hauteur*0.15);
		motRecherche.setAlignment(Pos.CENTER);
		motRecherche.setFont(Font.loadFont("file:ressource/police/Munich.ttf",32));
		motRecherche.setOnKeyPressed(e ->{
			
			if(e.getCode().equals(KeyCode.ENTER))
			{
				afficheResultat.setText("Chargement ...");
				try {
					
					
					Model.Dictionnaire dico=new Model.Dictionnaire("ressource/dico.txt");
					
					
					if(dico.verifieMotValide(motRecherche.getText()))
					{
						
						afficheResultat.setTextFill(Color.GREEN);
						afficheResultat.setText("Mot valide");
					}
					else
					{
						afficheResultat.setTextFill(Color.RED);
						afficheResultat.setText("Mot Inconnu");
					}
					
					
					lab.setText("Veuillez entrer un mot");
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		lab.setMaxHeight(hauteur*0.2);
		lab.setMaxWidth(largeur*0.4);
		lab.setFont(Font.loadFont("file:ressource/police/Munich.ttf",64));
		lab.setPadding(new Insets(0.03*hauteur, 0.03*largeur,0.03*hauteur,0.03*largeur));
		
		afficheResultat.setMaxHeight(hauteur*0.2);
		afficheResultat.setMaxWidth(largeur*0.4);
		afficheResultat.setTranslateX(largeur*0.1);
		afficheResultat.setTranslateY(hauteur*0.1);
		afficheResultat.setFont(Font.loadFont("file:ressource/police/Munich.ttf",64));
		afficheResultat.setPadding(new Insets(0.03*hauteur, 0.03*largeur,0.03*hauteur,0.03*largeur));
		
		background=new Rectangle();
		background.setFill(Color.web("rgba(255,255,255,0.7)"));
		background.setHeight(hauteur);
		background.setWidth(largeur);
		
		hb.getChildren().addAll(motRecherche,afficheResultat);
		
		this.getChildren().addAll(background,lab,hb);
		
		
	}

}
