package View;

import javafx.geometry.Insets;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class BoutonCustom extends HBox {

	private Rectangle rec;
	private Circle cir;
	private ImageView img;
	private Text t;
	
	public BoutonCustom(double hauteur,double largeur,String nom,Color c)
	{
		
		rec=new Rectangle();
		rec.setHeight(hauteur*0.165);
		rec.setWidth(largeur*0.48);
		rec.setArcHeight(80);
        rec.setArcWidth(80);
        rec.setFill(Color.WHITE);
        rec.setOnMouseEntered(event ->{

        	DropShadow ds = new DropShadow();
            ds.setOffsetY(10.0);
            ds.setOffsetX(10.0);
            ds.setColor(Color.BLACK);
            
        	rec.setEffect(ds);
        	
        });
        
        rec.setOnMouseExited(event ->{
        	rec.setEffect(null);
        });
     
       cir=new Circle(hauteur*0.115,c);
        cir.setOnMouseEntered(event ->{
        	DropShadow ds = new DropShadow();
            ds.setOffsetY(10.0);
            ds.setOffsetX(10.0);
            ds.setColor(Color.BLACK);
        	cir.setEffect(ds);
        	
        });
        
        cir.setOnMouseExited(event ->{
        	cir.setEffect(null);
        });
        
       img=new ImageView(getClass().getClassLoader().getResource("images/"+nom+".png").toString());
       img.setFitHeight(hauteur*0.15);
       img.setFitWidth(hauteur*0.15);
       img.setTranslateX(-largeur*0.175);
       img.setTranslateY(hauteur*0.038);
        
       t=new Text(nom);
       t.setFont(Font.loadFont("file:ressource/police/Munich.ttf",32));
       t.setTranslateY(hauteur*0.063);
       t.setTranslateX(-hauteur*0.117);
       t.setFill(c);
      
      t.setPickOnBounds(false);
      img.setPickOnBounds(false);
      
      this.setMargin(rec, new Insets(hauteur*0.035, -largeur*0.6, 0, 0));
      this.getChildren().addAll(rec,cir,img,t);
      this.setPickOnBounds(false);
		
	}

	public Text getT() {
		return t;
	}

	public void setT(Text t) {
		this.t = t;
	}
}
