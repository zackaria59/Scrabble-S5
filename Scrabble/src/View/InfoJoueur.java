package View;

import java.util.ArrayList;
import java.util.Random;

import Controller.ControllerPlateau;
import Model.Jeton;
import Model.Joueur;
import javafx.animation.PathTransition;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

public class InfoJoueur extends StackPane{
	
	private GridPane cadreJetons;
	private Label labelNom;
	private ArrayList<JetonV> jetonsV;
	private ControllerPlateau cp;
	private Joueur j;
	private Label points;
	private HBox hb;
	
	public InfoJoueur()
	{
			
		cadreJetons=new GridPane();
		cadreJetons.setPickOnBounds(false);
		labelNom=new Label("Test");
	
		
		this.setHeight(500);
		this.setWidth(500);
		this.getChildren().addAll(labelNom);
		this.getChildren().add(cadreJetons);
	}
	
	public void afficheNom(String nom)
	{
		labelNom.setFont(Font.loadFont("file:ressource/police/Munich.ttf",40));
		labelNom.setText(nom);
		
	}

	public void setInfoJoueur(Joueur j)
	{
		
		System.out.println("==================================================================>>> "+j.getScore()+"<<<<==================================================");
		this.afficheNom(j.getPseudo()+"   "+j.getScore()+"  pts");
		
		
	}
	
	
	public void afficheInfoJetons(ArrayList<JetonV> jetonV)
	{
		cadreJetons.getChildren().clear();
		jetonsV=jetonV;
		cadreJetons.setMaxSize(300, 300);
		cadreJetons.setHgap(10);
		cadreJetons.setVgap(10);
		
		for(int i=0;i<jetonV.size();i++)
		{
			if(i<8){
			GridPane.setRowIndex(jetonV.get(i),0);
			GridPane.setColumnIndex(jetonV.get(i),i);
			}
			else{
				GridPane.setRowIndex(jetonV.get(i),1);
				GridPane.setColumnIndex(jetonV.get(i),i-8);
			}
			cadreJetons.getChildren().addAll(jetonV.get(i));
			
		}
		
		cadreJetons.setAlignment(Pos.CENTER_RIGHT);
	
	}
	
	public void animationReprendre()
	{
		Random r=new Random();
		ArrayList<Integer> listIndex=new ArrayList<Integer>();
		
		for(int i=0;i<cadreJetons.getChildren().size();i++)
		{
			Node node = cadreJetons.getChildren().get(i);
			int rand;
			
			while(listIndex.contains((rand=r.nextInt(7)))){
				rand=r.nextInt(7);
			}
		
			TranslateTransition t=new TranslateTransition();
			t.setNode(node);
			t.setToX(cadreJetons.getChildren().get(rand).getLayoutX()-node.getLayoutX());
			t.setDuration(Duration.seconds(0.7));
			t.play();
			
			((JetonV)node).setXinitiale(cadreJetons.getChildren().get(rand).getLayoutX()-node.getLayoutX());
			listIndex.add(rand);
			
		}
		
		System.out.println(listIndex.toString());
	}
	
	public void setControllerJetonV(EventHandler ac)
	{
		for(JetonV jt: jetonsV)
		{
			jt.addEventHandler(MouseEvent.MOUSE_PRESSED, ac);
			jt.addEventHandler(MouseEvent.MOUSE_RELEASED, ac);
		}
	}
	
	public void viderJetons()
	{
		cadreJetons.getChildren().clear();
	}
	
	public void setPoints(int nbp)
	{
		points.setText(""+nbp);
	}
}