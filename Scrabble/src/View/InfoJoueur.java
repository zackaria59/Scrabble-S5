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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
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
	private ImageView containerNom;
	private ArrayList<JetonV> jetonsV;
	private ControllerPlateau cp;
	private Joueur j;
	private Label points;
	private HBox hb;
	
	public ArrayList<JetonV> getJetonsV() {
		return jetonsV;
	}

	public void setJetonsV(ArrayList<JetonV> jetonsV) {
		this.jetonsV = jetonsV;
	}

	public InfoJoueur(double largeur,double hauteur)
	{
			
		cadreJetons=new GridPane();
		cadreJetons.setPickOnBounds(false);
	
		labelNom=new Label("Test");
		labelNom.setTextFill(Color.WHITE);
		labelNom.setTranslateX(largeur*0.04);
		labelNom.setTranslateY(hauteur*0.01);
		
		containerNom=new ImageView(getClass().getClassLoader().getResource("images/panelScoreJ1.png").toString());
		containerNom.setFitHeight(hauteur*0.15);
		containerNom.setFitWidth(largeur*0.17);
		containerNom.setTranslateY(-hauteur*0.07);
		
		this.setHeight(500);
		this.setWidth(500);
		this.getChildren().addAll(containerNom,labelNom);
		this.getChildren().add(cadreJetons);
	}
	
	public void afficheNom(String nom)
	{
		labelNom.setFont(Font.loadFont("file:ressource/police/Munich.ttf",40));
		labelNom.setText(nom);
		
	}

	public void setInfoJoueur(Joueur j)
	{
		System.out.println(j);
		System.out.println("==================================================================>>> "+j.getScore()+"<<<<==================================================");
		this.afficheNom(j.getPseudo()+"   "+j.getScore());
		
		
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
			t.setDuration(Duration.seconds(0.3));
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
	
	public void removeJetonByChar(char c)
	{
		
		JetonV jetonAsupprimer = null;
		for (JetonV jt:this.jetonsV)
		{
			if(jt.getLettre()==c)
			{
				jetonAsupprimer=jt;
				break;
			}
		}
		
		jetonsV.remove(jetonAsupprimer);
		cadreJetons.getChildren().remove(jetonAsupprimer);
		
	}
	
	public double[] getCoordonneJeton(char c)
	{
		double[] coor=new double[2];
		
		for (JetonV jt:this.jetonsV)
		{
			if(jt.getLettre()==c)
			{
				coor[0]=jt.getLayoutX()+jt.getTranslateX();
				coor[1]=jt.getLayoutY()+jt.getTranslateY();
			}
		}
		System.out.println("X = "+coor[0]+ "Y = "+coor[1]);
		return coor;
	}
}