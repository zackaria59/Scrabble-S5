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
	private DropShadow ds;
	private boolean verrouille; 

	public BoutonCustom(double hauteur, double largeur, String nom, Color c) {

		rec = new Rectangle();
		rec.setHeight(hauteur * 0.165);
		rec.setWidth(largeur * 0.48);
		rec.setArcHeight(80);
		rec.setArcWidth(80);
		rec.setFill(Color.WHITE);
		
		rec.setOnMouseEntered(event -> {

			rec.setEffect(ds);
			cir.setEffect(ds);

		});

		rec.setOnMouseExited(event -> {
			rec.setEffect(null);
			cir.setEffect(null);
		});

		cir = new Circle(hauteur * 0.115, c);
		cir.setOnMouseEntered(event -> {

			cir.setEffect(ds);
			rec.setEffect(ds);

		});

		cir.setOnMouseExited(event -> {
			cir.setEffect(null);
			rec.setEffect(null);
		});

		img = new ImageView(getClass().getClassLoader().getResource("images/" + nom + ".png").toString());
		img.setFitHeight(hauteur * 0.15);
		img.setFitWidth(hauteur * 0.15);
		img.setTranslateX(-largeur * 0.175);
		img.setTranslateY(hauteur * 0.038);

		t = new Text(nom);
		t.setTranslateY(hauteur * 0.063);
		t.setTranslateX(-hauteur * 0.117);
		t.setFill(c);
		if(nom.equals("Meilleur mot")){
			t.setFont(Font.loadFont("file:ressource/police/Munich.ttf", hauteur*0.07));
		}
		else{
			t.setFont(Font.loadFont("file:ressource/police/Munich.ttf", hauteur*0.08));
		}
		t.setPickOnBounds(false);
		img.setMouseTransparent(true);

		ds = new DropShadow();
		ds.setOffsetY(10.0);
		ds.setOffsetX(10.0);
		ds.setColor(Color.BLACK);

		this.setMargin(rec, new Insets(hauteur * 0.035, -largeur * 0.6, 0, 0));
		this.getChildren().addAll(rec, cir, img, t);
		this.setPickOnBounds(false);
		
		

	}

	public Text getT() {
		return t;
	}

	public void setT(Text t) {
		this.t = t;
	}
	
	public ImageView getImg() {
		return img;
	}

	public void setImg(ImageView img) {
		this.img = img;
	}

	public boolean isVerrouille() {
		return verrouille;
	}

	public void setVerrouille(boolean verrouille) {
		this.verrouille = verrouille;
	}

}
