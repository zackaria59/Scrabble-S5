package View;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class JokerChoixLettre {

	private StackPane sp;
	private GridPane cadreJetons;
	private Rectangle background,rectangle;
	private VBox vb;
	private Text t;
	private int h,l;
	private double rectX,rectY,vbX,vbY;
	private ArrayList<JetonV> jts;
	
	
	public JokerChoixLettre(int h,int l)
	{
		this.h=h;
		this.l=l;
		sp=new StackPane();
		
		cadreJetons=new GridPane();
		cadreJetons.setPickOnBounds(false);
		
		vb=new VBox();
		
		t=new Text("Veuillez choisir une lettre");
		t.setFont(Font.loadFont("file:ressource/police/Munich.ttf",40));
		t.setUnderline(true);
		t.setPickOnBounds(false);
		background=new Rectangle();
		background.setHeight(h);
		background.setWidth(l);
		background.setFill(Color.web("rgba(255,255,255,0.7)"));
		
		rectangle=new Rectangle();
		rectangle.setWidth(l/2);
		rectangle.setHeight(h/2);
		rectangle.setArcHeight(60);
		rectangle.setArcWidth(60);
		rectangle.setStroke(Color.BLACK);
        rectangle.setStrokeWidth(10);
		rectangle.setFill(Color.web("rgba(255,255,255,1)"));

		afficheInfoJetons(this.genereJetons());
		
		vb.getChildren().addAll(t,cadreJetons);
		vb.setAlignment(Pos.CENTER);
		vb.setSpacing(h/6);
		
		sp.setMinHeight(h);
		sp.setMinWidth(l);
		sp.getChildren().addAll(background,rectangle,vb);
		
		rectX=rectangle.getLayoutX();
		rectY=rectangle.getLayoutY();
		vbX=vb.getLayoutX();
		vbY=vb.getLayoutY();
		
		vb.setPickOnBounds(false);
		rectangle.setPickOnBounds(false);
		sp.setPickOnBounds(false);
		background.setPickOnBounds(false);
		
		
		
	}
	
	public StackPane getSp() {
		return sp;
	}

	public void setSp(StackPane sp) {
		this.sp = sp;
	}

	public void afficheInfoJetons(ArrayList<JetonV> jetonV)
	{
		jts=jetonV;
		cadreJetons.getChildren().clear();
		cadreJetons.setMaxSize(300, 300);
		cadreJetons.setHgap(15);
		cadreJetons.setVgap(15);
		
		for(int i=0;i<jetonV.size();i++)
		{
			jetonV.get(i).setJetonPourJoker(true);
			
			if(i<9){
			GridPane.setRowIndex(jetonV.get(i),0);
			GridPane.setColumnIndex(jetonV.get(i),i);
			}
			else if(i<18){
				GridPane.setRowIndex(jetonV.get(i),1);
				GridPane.setColumnIndex(jetonV.get(i),i-9);
			}
			else{
				GridPane.setRowIndex(jetonV.get(i),2);
				GridPane.setColumnIndex(jetonV.get(i),i-18);
			}
			cadreJetons.getChildren().addAll(jetonV.get(i));
			
		}
		
		cadreJetons.setAlignment(Pos.CENTER_RIGHT);
		
	}
	
	public ArrayList<JetonV> genereJetons()
	{
		
		ArrayList<JetonV> jetons=new ArrayList<JetonV>();
		
		jetons.add(new JetonV('A'));
		jetons.add(new JetonV('B'));
		jetons.add(new JetonV('C'));
		jetons.add(new JetonV('D'));
		jetons.add(new JetonV('E'));
		jetons.add(new JetonV('F'));
		jetons.add(new JetonV('G'));
		jetons.add(new JetonV('H'));
		jetons.add(new JetonV('I'));
		jetons.add(new JetonV('J'));
		jetons.add(new JetonV('K'));
		jetons.add(new JetonV('L'));
		jetons.add(new JetonV('M'));
		jetons.add(new JetonV('N'));
		jetons.add(new JetonV('O'));
		jetons.add(new JetonV('P'));
		jetons.add(new JetonV('Q'));
		jetons.add(new JetonV('R'));
		jetons.add(new JetonV('S'));
		jetons.add(new JetonV('T'));
		jetons.add(new JetonV('U'));
		jetons.add(new JetonV('V'));
		jetons.add(new JetonV('W'));
		jetons.add(new JetonV('X'));
		jetons.add(new JetonV('Y'));
		jetons.add(new JetonV('Z'));
		
		return jetons;
		
	}
	
	public void animation()
	{
		rectangle.setTranslateY(-h);
		vb.setTranslateY(-h);
		
		TranslateTransition tt=new TranslateTransition();
		tt.setToX(rectX);
		tt.setToY(rectY);
		tt.setByY(1);
		tt.setNode(rectangle);
		
		TranslateTransition tt2=new TranslateTransition();
		tt2.setToX(vbX);
		tt2.setToY(vbY);
		tt2.setByY(1);
		tt2.setNode(vb);
		
		tt.play();
		tt2.play();
		
		
		
	}
	
	public void addControllerJetonV(EventHandler<MouseEvent> cp)
	{
		for(JetonV jt: jts)
		{
			jt.addEventHandler(MouseEvent.MOUSE_CLICKED, cp);
			jt.addEventHandler(MouseEvent.MOUSE_ENTERED, cp);
			jt.addEventHandler(MouseEvent.MOUSE_EXITED, cp);
			
		}
	}
}
