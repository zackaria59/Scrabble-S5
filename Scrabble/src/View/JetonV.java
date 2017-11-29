package View;

import java.awt.Dimension;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class JetonV extends StackPane {
	

	private ImageView image=null;
	private  StackPane cas;
	private char lettre;
	private Point2D point;
	double dragDeltaX,dragDeltaY,Xinitiale,Yinitiale;
	private boolean jetonPourJoker;
	private boolean jetonPourEchanger;
	
	private boolean selectionnePourEchange;
	
	
	public JetonV(char lettre) {
		 
		jetonPourJoker=false;
		setJetonPourEchanger(false);
		setSelectionnePourEchange(false);
		this.setLettre(lettre);
	    
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		image=new ImageView(getClass().getClassLoader().getResource("images/jetons/"+lettre+".png").toString()); 
		
		 image.setFitHeight(dimension.getHeight()*0.06);
	     image.setFitWidth(dimension.getHeight()*0.06);
	     this.setMaxSize(80, 80);
		 this.setPickOnBounds(false);
		 
		 DropShadow ds = new DropShadow();
         ds.setOffsetY(10.0);
         ds.setOffsetX(10.0);
         ds.setColor(Color.BLACK);
         image.setEffect(ds);
		 
		 //this.addEventFilter(MouseEvent.ANY,e -> System.out.println(e));
		 	this.setOnMousePressed(event -> {
		 	
		 		if(!this.jetonPourJoker && !this.jetonPourEchanger){
		 			image.setMouseTransparent(true);
		       // Xinitiale=image.getLayoutX();
		        //Yinitiale=image.getLayoutY();
		        
		        dragDeltaX = this.getLayoutX() + event.getX();
		        dragDeltaY = this.getLayoutY() + event.getY();
		        event.setDragDetect(true);}
		 		
		 		else{
		 			
		 		}
		    });

		    this.setOnMouseReleased(event -> {
		        
		    	image.setMouseTransparent(false);
		    	
		       
		    });

		    this.setOnMouseDragged(event -> {
		    	if(!this.jetonPourJoker && !this.jetonPourEchanger){
		    	this.setTranslateX(event.getSceneX() - dragDeltaX -30);
		        this.setTranslateY(event.getSceneY() - dragDeltaY -30);
		        event.setDragDetect(false);
		    	}
		    });

		    this.setOnDragDetected(new EventHandler<MouseEvent>(){

				@Override
				public void handle(MouseEvent arg0) {
					if(!jetonPourJoker && !jetonPourEchanger){
					image.startFullDrag();
					}

				}
		    	
		    });
		    this.setOnMouseEntered(event -> {
		        
		    	image.setCursor(Cursor.OPEN_HAND);
		    	image.setTranslateY(-8);
		        
		        
		    });
		    
		    this.setOnMouseExited(event -> {
		        
		    	image.setTranslateY(0);
		        
		        
		    });
			 
			
			 this.getChildren().addAll(image);
   }
	
	public void setImage(ImageView image) {
		this.image = image;
	}

	public double getXinitiale() {
		return Xinitiale;
	}

	public void setXinitiale(double xinitiale) {
		Xinitiale = xinitiale;
	}

	public double getYinitiale() {
		return Yinitiale;
	}

	public void setYinitiale(double yinitiale) {
		Yinitiale = yinitiale;
	}

	public void effectSelection()
	{
		this.setRotate(25);
	}
	
	public void RetirerEffectSelection()
	{
		this.setRotate(0);
	}

	public boolean isJetonPourJoker() {
		return jetonPourJoker;
	}

	public void setJetonPourJoker(boolean jetonPourJoker) {
		this.jetonPourJoker = jetonPourJoker;
	}

	public ImageView getImage() {
		return image;
	}

	public char getLettre() {
		return lettre;
	}

	public void setLettre(char lettre) {
		this.lettre = lettre;
	}

	public void aggrandir()
	{
		this.setMaxHeight(200);
		this.setMaxWidth(200);
		this.setWidth(200);
		this.setHeight(200);
	}

	public boolean isJetonPourEchanger() {
		return jetonPourEchanger;
	}

	public void setJetonPourEchanger(boolean jetonPourEchanger) {
		this.jetonPourEchanger = jetonPourEchanger;
	}

	public boolean isSelectionnePourEchange() {
		return selectionnePourEchange;
	}

	public void setSelectionnePourEchange(boolean selectionnePourEchange) {
		this.selectionnePourEchange = selectionnePourEchange;
	}
	
	
}