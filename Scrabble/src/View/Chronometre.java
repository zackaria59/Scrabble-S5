package View;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Chronometre extends StackPane {

	private ImageView background;
	private Label timer;
	private int compteur;
	
	private boolean alternateur;
	
	public Chronometre(double largeur,double hauteur)
	{
		background=new ImageView(getClass().getClassLoader().getResource("images/chrono.png").toString());
		background.setFitWidth(hauteur*0.1);
		background.setFitHeight(hauteur*0.1);
		background.setPickOnBounds(false);
		
		timer=new Label("60");
		timer.setFont(Font.loadFont("file:ressource/police/Munich.ttf",(int)(hauteur*0.06)));
		timer.setTranslateX(-hauteur*0.02);
		timer.setTranslateY(-hauteur*0.01);
		timer.setPickOnBounds(false);
		
		compteur=0;
		alternateur=true;
		
		this.setAlignment(Pos.BOTTOM_RIGHT);
		this.setPickOnBounds(false);
		this.getChildren().addAll(background,timer);
	}
	
	public void setTime(int temp)
	{
		if(alternateur)
		{timer.setTextFill(Color.RED);}
		else{
		timer.setTextFill(Color.YELLOW);	
		}
		alternateur=(!alternateur);
		timer.setText(""+temp);
	}
}
