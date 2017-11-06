package View;

import java.util.ArrayList;

import Controller.ControllerPlateau;
import Model.Jeton;
import Model.Joueur;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class InfoJoueur extends StackPane{
	
	private GridPane cadreJetons;
	private Label labelNom;
	private ArrayList<JetonV> jetonsV;
	private ControllerPlateau cp;
	private Joueur j;
	
	
	public InfoJoueur()
	{
		cadreJetons=new GridPane();
		cadreJetons.setPickOnBounds(false);
		labelNom=new Label();
		this.setHeight(500);
		this.setWidth(500);
		this.getChildren().add(labelNom);
		this.getChildren().add(cadreJetons);
	}
	
	public void afficheNom(String nom)
	{
		labelNom.setAlignment(Pos.TOP_LEFT);
		labelNom.setFont(Font.font("Berlin Sans FB Demi", FontWeight.BOLD, 40));
		labelNom.setText(nom);
	}

	public void setInfoJoueur(Joueur j)
	{
		this.afficheNom(j.getPseudo());
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
		animation();
	}
	
	public void animation()
	{
		Thread daemonThread = new Thread(new Runnable() {
	
			@Override
			public void run() {
			try {
				int a=0;
				while (a<400) {
				
				cadreJetons.setTranslateY(1);
				a++;
					}
			
			
				} finally {
	
				
				}	
	
				}
			}, "Demon");
			
			daemonThread.start();
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
}