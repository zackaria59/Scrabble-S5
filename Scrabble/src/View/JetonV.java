package View;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;


public class JetonV extends StackPane {
	

	private ImageView image=null;
	private  StackPane cas;
	private char lettre;
	private Point2D point;
	double dragDeltaX,dragDeltaY,Xinitiale,Yinitiale;
	private boolean jetonPourJoker;
	
	public JetonV(char lettre) {
		 
		jetonPourJoker=false;
		 this.setLettre(lettre);
			 image=new ImageView(getClass().getClassLoader().getResource("images/jetons/"+lettre+".png").toString());
		 
		 image.setFitHeight(60);
	     image.setFitWidth(60);
	     
	  
	     this.setMaxSize(80, 80);
		 this.getChildren().add(image);
		 this.setPickOnBounds(false);
		 
		 
		 //this.addEventFilter(MouseEvent.ANY,e -> System.out.println(e));
		 	this.setOnMousePressed(event -> {
		 	
		 		if(!this.jetonPourJoker){
		 			image.setMouseTransparent(true);
		        Xinitiale=this.getLayoutX();
		        Yinitiale=this.getLayoutY();
		        
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
		    	
		    	image.setTranslateX(event.getSceneX() - dragDeltaX -30);
		        image.setTranslateY(event.getSceneY() - dragDeltaY -30);
		        event.setDragDetect(false);
		    });

		    this.setOnDragDetected(new EventHandler<MouseEvent>(){

				@Override
				public void handle(MouseEvent arg0) {
					image.startFullDrag();

				}
		    	
		    });
		    this.setOnMouseEntered(event -> {
		        image.setCursor(Cursor.OPEN_HAND);
		    });
			 
			
		 
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
	
}