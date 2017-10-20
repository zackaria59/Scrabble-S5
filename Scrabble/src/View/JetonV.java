package View;

import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class JetonV extends StackPane {
	

	private final ImageView image;
	private  StackPane cas;
	private char lettre;
	 
	public JetonV(char lettre) {
		 
		 this.lettre=lettre;
		 image=new ImageView(getClass().getClassLoader().getResource("images/jetons/"+lettre+".png").toString());
		 image.setFitHeight(60);
	     image.setFitWidth(60);
		 this.getChildren().add(image);
   }

	
}