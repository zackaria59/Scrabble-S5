package View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
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

	Circle cir1;
	public MenuCommande(int h,int l)
	{
		
		double hauteur=h*0.45;
		double largeur=l*0.28;
		this.setHeight(hauteur);
		this.setWidth(largeur);
		 	
		Rectangle rec1=new Rectangle();
		rec1.setHeight(h*0.076);
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
        
        rec1.setOnMouseExited(event ->{
        	rec1.setEffect(null);
        });
     
        cir1=new Circle(hauteur*0.115,Color.web("rgb(19,152,255)"));
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
       
        ImageView img1=new ImageView(getClass().getClassLoader().getResource("images/melanger.png").toString());
        img1.setFitHeight(hauteur*0.16);
        img1.setFitWidth(hauteur*0.16);
        img1.setTranslateX(-largeur*0.175);
        img1.setTranslateY(hauteur*0.038);
        
        Text t1=new Text("Mélanger");
        t1.setFont(Font.loadFont("file:ressource/police/Munich.ttf",32));
       t1.setTranslateY(hauteur*0.063);
       t1.setTranslateX(-hauteur*0.117);
       t1.setFill(Color.web("rgb(19,152,255)"));
       
        
        HBox sp1=new HBox();
        sp1.setMargin(rec1, new Insets(hauteur*0.035, -largeur*0.6, 0, 0));
        sp1.getChildren().addAll(rec1,cir1,img1,t1);
    
    	Rectangle rec2=new Rectangle();
		rec2.setHeight(h*0.076);
		rec2.setWidth(l*0.13);
		rec2.setArcHeight(50);
        rec2.setArcWidth(50);
        rec2.setFill(Color.BISQUE);
        rec2.setOnMouseEntered(event ->{

        	DropShadow ds = new DropShadow();
            ds.setOffsetY(10.0);
            ds.setOffsetX(10.0);
            ds.setColor(Color.BLACK);
            
        	rec2.setEffect(ds);
        	
        });
        
        rec2.setOnMouseExited(event ->{
        	rec2.setEffect(null);
        });
     
        Circle cir2=new Circle(hauteur*0.115,Color.web("rgb(255,112,10)"));
        cir2.setOnMouseEntered(event ->{
        	DropShadow ds = new DropShadow();
            ds.setOffsetY(10.0);
            ds.setOffsetX(10.0);
            ds.setColor(Color.BLACK);
        	cir2.setEffect(ds);
        	
        });
        
        cir2.setOnMouseExited(event ->{
        	cir2.setEffect(null);
        });
        
        ImageView img2=new ImageView(getClass().getClassLoader().getResource("images/echanger.png").toString());
       img2.setFitHeight(hauteur*0.15);
       img2.setFitWidth(hauteur*0.15);
       img2.setTranslateX(-largeur*0.175);
       img2.setTranslateY(hauteur*0.038);
        
       Text t2=new Text("Echanger");
       t2.setFont(Font.loadFont("file:ressource/police/Munich.ttf",32));
      t2.setTranslateY(hauteur*0.063);
      t2.setTranslateX(-hauteur*0.117);
      t2.setFill(Color.web("rgb(255,112,10)"));
      
        HBox sp2=new HBox();
        sp2.setMargin(rec2, new Insets(hauteur*0.035, -largeur*0.6, 0, 0));
        sp2.getChildren().addAll(rec2,cir2,img2,t2);
        
    	Rectangle rec3=new Rectangle();
		rec3.setHeight(h*0.076);
		rec3.setWidth(l*0.13);
		rec3.setArcHeight(50);
        rec3.setArcWidth(50);
        rec3.setFill(Color.BISQUE);
        rec3.setOnMouseEntered(event ->{

        	DropShadow ds = new DropShadow();
            ds.setOffsetY(10.0);
            ds.setOffsetX(10.0);
            ds.setColor(Color.BLACK);
            
        	rec3.setEffect(ds);
        	
        });
        
        rec3.setOnMouseExited(event ->{
        	rec3.setEffect(null);
        });
     
        Circle cir3=new Circle(hauteur*0.115,Color.web("rgb(0,179,61)"));
        cir3.setOnMouseEntered(event ->{
        	DropShadow ds = new DropShadow();
            ds.setOffsetY(10.0);
            ds.setOffsetX(10.0);
            ds.setColor(Color.BLACK);
        	cir3.setEffect(ds);
        	
        });
        
        cir3.setOnMouseExited(event ->{
        	cir3.setEffect(null);
        });
       
        ImageView img3=new ImageView(getClass().getClassLoader().getResource("images/passer.png").toString());
        img3.setFitHeight(hauteur*0.15);
        img3.setFitWidth(hauteur*0.15);
        img3.setTranslateX(-largeur*0.175);
        img3.setTranslateY(hauteur*0.038);
         
        Text t3=new Text("Echanger");
        t3.setFont(Font.loadFont("file:ressource/police/Munich.ttf",32));
        t3.setTranslateY(hauteur*0.063);
       	t3.setTranslateX(-hauteur*0.117);
       	t3.setFill(Color.web("rgb(0,179,61)"));  
        
       HBox sp3=new HBox();
        sp3.setMargin(rec3, new Insets(hauteur*0.035, -largeur*0.6, 0, 0));
        sp3.getChildren().addAll(rec3,cir3,img3,t3);
        
    	Rectangle rec4=new Rectangle();
		rec4.setHeight(h*0.076);
		rec4.setWidth(l*0.13);
		rec4.setArcHeight(50);
        rec4.setArcWidth(50);
        rec4.setFill(Color.BISQUE);
        rec4.setOnMouseEntered(event ->{

        	DropShadow ds = new DropShadow();
            ds.setOffsetY(10.0);
            ds.setOffsetX(10.0);
            ds.setColor(Color.BLACK);
            
        	rec4.setEffect(ds);
        	
        });
        
        rec4.setOnMouseExited(event ->{
        	rec4.setEffect(null);
        });
     
        Circle cir4=new Circle(hauteur*0.115,Color.RED);
        cir4.setOnMouseEntered(event ->{
        	DropShadow ds = new DropShadow();
            ds.setOffsetY(10.0);
            ds.setOffsetX(10.0);
            ds.setColor(Color.BLACK);
        	cir4.setEffect(ds);
        	
        });
        
        cir4.setOnMouseExited(event ->{
        	cir4.setEffect(null);
        });
       
        ImageView img4=new ImageView(getClass().getClassLoader().getResource("images/quitter.png").toString());
        img4.setFitHeight(hauteur*0.15);
        img4.setFitWidth(hauteur*0.15);
        img4.setTranslateX(-largeur*0.175);
        img4.setTranslateY(hauteur*0.038);
         
        Text t4=new Text("Quitter");
        t4.setFont(Font.loadFont("file:ressource/police/Munich.ttf",32));
        t4.setTranslateY(hauteur*0.063);
       	t4.setTranslateX(-hauteur*0.117);
       	t4.setFill(Color.RED); 
        
        HBox sp4=new HBox();
        sp4.setMargin(rec4, new Insets(hauteur*0.035, -largeur*0.6, 0, 0));
        sp4.getChildren().addAll(rec4,cir4,img4,t4);
       
        
        this.getChildren().addAll(sp1,sp2,sp3,sp4);
        for(Node nd: this.getChildren()){
        	 this.setMargin(nd,new Insets(hauteur*0.04,0, 0, 0));
        }
        
	}
}