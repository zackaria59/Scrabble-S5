package View;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class MenuCommande extends StackPane {

	public MenuCommande(int h,int l)
	{
		
		double hauteur=h*0.45;
		double largeur=l*0.28;
		this.setHeight(hauteur);
		this.setWidth(largeur);
		
		Rectangle rec1=new Rectangle();
		rec1.setHeight(h*0.08);
		rec1.setWidth(l*0.13);
		rec1.setArcHeight(50);
        rec1.setArcWidth(50);
        rec1.setFill(Color.BISQUE);
        rec1.setOnMouseEntered(event ->{

        	DropShadow ds = new DropShadow();
            ds.setOffsetY(10.0);
            ds.setOffsetX(10.0);
            ds.setColor(Color.BLACK);
            
        	rec1.setEffect(ds);
        	
        });
     
        Circle cir1=new Circle(120,100,60,Color.RED);
        cir1.setOnMouseEntered(event ->{
        	DropShadow ds = new DropShadow();
            ds.setOffsetY(10.0);
            ds.setOffsetX(10.0);
            ds.setColor(Color.BLACK);
        	cir1.setEffect(ds);
        	
        });
        
        cir1.setOnMouseExited(event ->{
        	cir1.setEffect(null);
        });
        
        
       
       
        
        this.getChildren().addAll(rec1,cir1);
	}
}