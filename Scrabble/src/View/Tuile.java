package View;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tuile extends StackPane {
	
	private ImageView img;
	private Rectangle rec;
	
	
	public Tuile(double taille)
	{
	
	    img=new ImageView(new Image(getClass().getClassLoader().getResource("images/jetons/jeton.png").toString(), true));
        img.setFitHeight(taille/17);
        img.setFitWidth(taille/17);
        
        
        rec = new Rectangle();
        rec.setWidth(taille/15);
        rec.setHeight(taille/15);
        rec.setStroke(Color.BLACK); 
        rec.setFill(Color.TRANSPARENT);
        rec.setStrokeWidth(2.5);
        rec.setArcHeight(10);
        rec.setArcWidth(10);
        rec.setFill(Color.BEIGE);
        
        this.getChildren().addAll(rec);
        
	}
	
	public void setCouleur(Color c)
	{
		rec.setStroke(c.darker());
		rec.setFill(c.brighter());
	}
	
	public void setImage(ImageView imgg)
	{
		this.getChildren().addAll(imgg);
	}
	
	public void setCouleurr(Color c)
	{
		System.out.println("okk");
		rec.setStroke(c);	
	}

	public Rectangle getRec() {
		return rec;
	}

	public void setRec(Rectangle rec) {
		this.rec = rec;
	}

	private void setBackground(Color beige) {
		// TODO Auto-generated method stub
		
	}
	
}