package View;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import jfxtras.labs.util.event.MouseControlUtil;

public class Tuile  {

	private int x,y;
	private StackPane container;
	private Rectangle rec;
	private Color couleur;
	private JetonV jetonDrag;
	private ImageView img;
	private Boolean jetonPresent=false;
	private char c='1';
	
	
	public Tuile(double taille,int x,int y)
	{
		
	   /* img=new ImageView(new Image(getClass().getClassLoader().getResource("images/jetons/B.png").toString(), true));
        img.setFitHeight(taille/17);
        img.setFitWidth(taille/17);
        */
        
		this.setX(x);
		this.setY(y);
        rec = new Rectangle();
        rec.setWidth(taille/15);
        rec.setHeight(taille/15);
        rec.setStroke(Color.BLACK); 
        rec.setFill(Color.TRANSPARENT);
        rec.setStrokeWidth(2.5);
        rec.setArcHeight(10);
        rec.setArcWidth(10);
        rec.setFill(Color.web("rgb(255,255,255)"));
        
        couleur=Color.WHITE;
      //rec.addEventFilter(MouseDragEvent.ANY,e -> System.out.println(e));
        
		container=new StackPane();
        
        container.setOnMouseDragEntered(new EventHandler<MouseDragEvent>(){

			

			@Override
			public void handle(MouseDragEvent e) {
			//System.out.println(c+" JetonPresent="+jetonPresent);
				
			if(c!='1')
				{
					rec.setFill(Color.RED);
				}
				else{
				rec.setFill(Color.LIGHTGREEN);
				///// modificaton sur la taille de la case
				img=new ImageView(getClass().getClassLoader().getResource("images/jetons/"+jetonDrag.getLettre()+".png").toString());
				img.setFitHeight(taille/60);
				img.setFitWidth(taille/60);
				container.getChildren().add(img);
				
				jetonPresent=true;
				}
			}
        	
        });
        container.setOnMouseEntered(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent e) {
				rec.setFill(Color.LIGHTGREEN);
				
			}
        	
        });
        
        container.setOnMouseDragExited(new EventHandler<MouseDragEvent>(){

			@Override
			public void handle(MouseDragEvent e) {
						rec.setFill(couleur);
						container.getChildren().remove(img);
						jetonPresent=false;
			}
		
        	
        });
        
        container.setOnMouseExited(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent e) {
				
				rec.setFill(couleur);
			}
        	
        });
        
        container.getChildren().addAll(rec);
        
	}
	
	public void setCouleur(Color c)
	{
		couleur=c;
		rec.setStroke(c.darker());
		rec.setFill(c.brighter());
	}
	
	public void setImage(ImageView imgg)
	{
		container.getChildren().addAll(imgg);
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

	public JetonV getJetonDrag() {
		return jetonDrag;
	}

	public void setJetonDrag(JetonV jetonDrag) {
		this.jetonDrag = jetonDrag;
	}
	
	public boolean isJetonPresent()
	{
		return this.jetonPresent;
	}
	
	public StackPane getContainer() {
		return container;
	}

	public void setContainer(StackPane container) {
		this.container = container;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public Boolean getJetonPresent() {
		return jetonPresent;
	}

	public void setJetonPresent(Boolean jetonPresent) {
		this.jetonPresent = jetonPresent;
	}

	public void setX(int x) {
		this.x = x;
	}

	public char getC() {
		return c;
	}

	public void setC(char c) {
		this.c = c;
	}
	
	public ImageView getImg() {
		return img;
	}

	public void setImg(ImageView img) {
		this.img = img;
	}

	
}