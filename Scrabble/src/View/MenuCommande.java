package View;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class MenuCommande extends VBox {

	private BoutonCustom b1,b2,b3,b4,b5;
	
	public MenuCommande(int h,int l)
	{
		
		double hauteur=h*0.45;
		double largeur=l*0.28;
		this.setHeight(hauteur);
		this.setWidth(largeur);
		
       
        b1=new BoutonCustom(hauteur,largeur,"Melanger",Color.web("rgb(19,152,255)"));
        b2=new BoutonCustom(hauteur,largeur,"Echanger",Color.web("rgb(255,112,10)"));
        b3=new BoutonCustom(hauteur,largeur,"Passer",Color.web("rgb(0,179,61)"));
        b5=new BoutonCustom(hauteur,largeur,"Dictionnaire",Color.GOLD);
        b4=new BoutonCustom(hauteur,largeur,"Meilleur mot",Color.RED);
       
        
        
        this.getChildren().addAll(b1,b2,b3,b5,b4);
        for(Node nd: this.getChildren()){
        	 this.setMargin(nd,new Insets(hauteur*0.04,0, 0, 0));
        }
        
	}
	
	public void addControllers(EventHandler ac)
	{
		
			b1.addEventHandler(MouseEvent.MOUSE_PRESSED, ac);
			b2.addEventHandler(MouseEvent.MOUSE_PRESSED, ac);
			b3.addEventHandler(MouseEvent.MOUSE_PRESSED, ac);
			b4.addEventHandler(MouseEvent.MOUSE_PRESSED, ac);
			b5.addEventFilter(MouseEvent.MOUSE_PRESSED, ac);
		
	}
	
	public void activerBoutonReprendre()
	{
		b1.getT().setText("Reprendre");
		//Mettre icone au bouton
	}
	
	public void activerBoutonMelanger()
	{
		b1.getT().setText("Melanger");
		//Mettre icone au bouton
	}
	
	public void activerBoutonJouer()
	{
		b3.getT().setText("Jouer");
		b3.setImg(new ImageView (getClass().getClassLoader().getResource("images/jouer.png").toString()));
	}
	
	public void activerBoutonPasser()
	{
		b3.getT().setText("Passer");
		b3.setImg(new ImageView (getClass().getClassLoader().getResource("images/passer.png").toString()));
	}
	
	public void verrouiller(){
		b1.setVerrouille(false);
		b2.setVerrouille(false);
		b3.setVerrouille(false);
		b4.setVerrouille(false);
		//b5.setVerrouille(false);

	}
	
	public void deverouiller(){
		b1.setVerrouille(true);
		b2.setVerrouille(true);
		b3.setVerrouille(true);
		b4.setVerrouille(true);
		b5.setVerrouille(true);
	}
}