package View;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;


public class JetonV extends StackPane {
	

	private final ImageView image;
	private  StackPane cas;
	private char lettre;
	private Point2D point;
	double dragDeltaX,dragDeltaY,Xinitiale,Yinitiale;
	
	public JetonV(char lettre) {
		 
		 this.setLettre(lettre);
		 image=new ImageView(getClass().getClassLoader().getResource("images/jetons/"+lettre+".png").toString());
		 image.setFitHeight(60);
	     image.setFitWidth(60);
	  
	     this.setMaxSize(60, 60);
		 this.getChildren().add(image);
		 this.setPickOnBounds(false);
		this.setStyle("-fx-border-color: blue;\n"
                + "-fx-border-insets: 5;\n"
                + "-fx-border-width: 3;\n"
                + "-fx-border-style: dashed;\n;");
		 
	
		 	this.setOnMousePressed(event -> {
		 	
		 		image.setMouseTransparent(true);
		        Xinitiale=this.getLayoutX();
		        Yinitiale=this.getLayoutY();
		        
		        dragDeltaX = this.getLayoutX() + event.getX();
		        dragDeltaY = this.getLayoutY() + event.getY();
		        event.setDragDetect(true);
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

	public ImageView getImage() {
		return image;
	}

	public char getLettre() {
		return lettre;
	}

	public void setLettre(char lettre) {
		this.lettre = lettre;
	}

	
}