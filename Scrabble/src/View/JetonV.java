package View;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

public class JetonV extends StackPane {
	

	private final ImageView image;
	private  StackPane cas;
	private char lettre;
	private Point2D point;
	double X,Y;
	
	public JetonV(char lettre) {
		 
		 this.lettre=lettre;
		 image=new ImageView(getClass().getClassLoader().getResource("images/jetons/"+lettre+".png").toString());
		 image.setFitHeight(60);
	     image.setFitWidth(60);
		 this.getChildren().add(image);
		 this.setOnMouseDragged(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent me) {
				
				System.out.println("drag");
				double dragX = me.getSceneX() - point.getX();
                double dragY = me.getSceneY() - point.getY();
               
                double newXPosition = X + dragX;
                double newYPosition = Y + dragY;
               
                    	((Node) me.getSource()).setTranslateX(newXPosition);
                    ((Node) me.getSource()).setTranslateY(newYPosition);
                
			}
			 
		 });
		 
		 this.setOnMousePressed(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				
				
				X=((JetonV) event.getSource()).getLayoutX();
				Y=((JetonV) event.getSource()).getLayoutY();
				
				System.out.println("X="+X+"  Y="+Y);
				point =  new Point2D(event.getSceneX(),event.getSceneY());
				
			}
			 
		 });
		 
   }

	
}