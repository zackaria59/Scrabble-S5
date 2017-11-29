package View;

import java.awt.RenderingHints.Key;

import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
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
import javafx.util.Duration;

public class FenetreMessage extends StackPane {

	private Rectangle fenetre,background,valider;
	private Text msg;
	private VBox vb;
	private boolean fenetreFerme;
	
	private double largeur,hauteur,fenX,fenY,vbX,vbY;
	
	public FenetreMessage(double largeur,double hauteur)
	{
		this.largeur=largeur;
		this.hauteur=hauteur;
		
		msg=new Text("");
		msg.setFont(Font.loadFont("file:ressource/police/Munich.ttf",32));
		msg.setPickOnBounds(false);
		
		background=new Rectangle();
		background.setHeight(hauteur);
		background.setWidth(largeur);
		background.setFill(Color.web("rgba(255,255,255,0.7)"));
		
		fenetre=new Rectangle();
		fenetre.setWidth(largeur/2);
		fenetre.setHeight(hauteur/3);
		fenetre.setArcHeight(60);
		fenetre.setArcWidth(60);
		fenetre.setStroke(Color.BLACK);
        fenetre.setStrokeWidth(10);
		fenetre.setFill(Color.web("rgba(255,255,255,1)"));
		
		valider=new Rectangle();
		valider.setWidth(largeur/8);
		valider.setHeight(hauteur/12);
		valider.setArcHeight(60);
		valider.setArcWidth(60);
		valider.setStroke(Color.BLACK);
		valider.setStrokeWidth(7);
		valider.setFill(Color.web("rgba(25,245,25,0.9)"));
		valider.setOnMouseEntered(e->{
			DropShadow ds = new DropShadow();
            ds.setOffsetY(10.0);
            ds.setOffsetX(10.0);
            ds.setColor(Color.BLACK);
            
            valider.setEffect(ds);
			
		});
		
		valider.setOnMouseExited(e->{
			valider.setEffect(null);
		});
		
		valider.setOnMouseClicked(e->{
			setFenetreFerme(false);
			this.setVisible(false);
		});
		
		
		Text ok=new Text("Ok");
		ok.setFont(Font.loadFont("file:ressource/police/Munich.ttf",32));
		ok.setPickOnBounds(false);
		
		StackPane bouton=new StackPane();
		bouton.getChildren().addAll(valider,ok);
		bouton.setAlignment(Pos.CENTER);
		
		vb=new VBox();
		vb.getChildren().addAll(msg,bouton);
		vb.setAlignment(Pos.CENTER);
		vb.setSpacing(15);
		VBox.setMargin(bouton, new Insets(hauteur*0.08,0,0,0));
		
		fenX=fenetre.getLayoutX();
		fenY=fenetre.getLayoutY();
		
		vbX=vb.getLayoutX();
		vbY=vb.getLayoutY();
		
		fenetre.setOnKeyPressed(e ->{
			if(e.getCode().equals(KeyCode.ESCAPE) || e.getCode().equals(KeyCode.ENTER))
			{
				this.setVisible(false);
			}
		});
		this.getChildren().addAll(background,fenetre,vb);
	}
	
	public void afficheMessage(String msg)
	{
		this.setVisible(true);
		this.msg.setText(msg);
		animation();
		PauseTransition pause = new PauseTransition(Duration.seconds(2));
		pause.setOnFinished(event ->
		this.setVisible(false)
		);
		pause.play();
	
	}
	
	public void animation()
	{
		
		fenetre.setTranslateX(-largeur);
		TranslateTransition tt=new TranslateTransition();
		tt.setNode(fenetre);
		tt.setToX(fenX);
		tt.setToY(fenY);
		
		vb.setTranslateX(-largeur);
		TranslateTransition tt2=new TranslateTransition();
		tt2.setNode(vb);
		tt2.setToX(fenX);
		tt2.setToY(fenY);
		
		tt.play();
		tt2.play();
		
		
	}

	public boolean isFenetreFerme() {
		return fenetreFerme;
	}

	public void setFenetreFerme(boolean fenetreFerme) {
		this.fenetreFerme = fenetreFerme;
	}
}
