package View;

import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Dictionnaire extends StackPane{
	
	private TextField motRecherche;
	private Label lab,afficheResultat;
	private VBox hb;
	private Rectangle background;
	
	private Rectangle quitter;
	private StackPane spq;
	private Text quit;
	
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
		lab=new Label("Recherche...");
		afficheResultat=new Label();

		quitter=new Rectangle();
		quitter.setWidth(largeur/6);
		quitter.setHeight(hauteur/10);
		quitter.setArcHeight(60);
		quitter.setArcWidth(60);
		quitter.setStroke(Color.BLACK);
        quitter.setStrokeWidth(7);
		quitter.setFill(Color.web("rgba(245,25,25,0.9)"));
		quitter.setOnMouseEntered(e->{
			DropShadow ds = new DropShadow();
            ds.setOffsetY(10.0);
            ds.setOffsetX(10.0);
            ds.setColor(Color.BLACK);
            
        	quitter.setEffect(ds);
			
		});
		
		quitter.setOnMouseExited(e->{
			quitter.setEffect(null);
		});
		
		quitter.setOnMouseClicked(e->{
			this.setVisible(false);
		});
		quit=new Text("Annuler");
		quit.setFont(Font.loadFont("file:ressource/police/Munich.ttf",32));
		quit.setPickOnBounds(false);
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
						afficheResultat.setText("est un mot valide dans le dictionnaire français.");
					}
					else
					{
						afficheResultat.setTextFill(Color.RED);
						afficheResultat.setText("n'est pas un mot valide dans le dictionnaire français.");
					}
					
					
					lab.setText("Recherche...");
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
		lab.setFont(Font.loadFont("file:ressource/police/Munich.ttf",40));
		lab.setPadding(new Insets(0.03*hauteur, 0.03*largeur,0.03*hauteur,0.03*largeur));
		
		afficheResultat.setMaxHeight(hauteur*0.2);
		afficheResultat.setMaxWidth(largeur*0.4);
		afficheResultat.setTranslateX(largeur*0.1);
		afficheResultat.setTranslateY(hauteur*0.1);
		afficheResultat.setFont(Font.loadFont("file:ressource/police/Munich.ttf",40));
		afficheResultat.setPadding(new Insets(0.03*hauteur, 0.03*largeur,0.03*hauteur,0.03*largeur));
		
		background=new Rectangle();
		background.setFill(Color.web("rgba(255,255,255,0.7)"));
		background.setHeight(hauteur);
		background.setWidth(largeur);
		
		hb.getChildren().addAll(motRecherche,afficheResultat);

		spq=new StackPane();
		spq.setPickOnBounds(false);
		
		spq.getChildren().addAll(quitter,quit);
		spq.setTranslateX(0.4*largeur);
		spq.setTranslateY(0.4*hauteur);
		
		this.getChildren().addAll(background,lab,hb,spq);
		
		
	}

}
