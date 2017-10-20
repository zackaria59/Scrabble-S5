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
		labelNom=new Label();
		this.setHeight(500);
		this.setWidth(500);
		this.getChildren().add(labelNom);
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
		jetonsV=jetonV;
		cadreJetons.setMaxSize(300, 300);
		cadreJetons.setHgap(10);
		cadreJetons.setVgap(10);
		
		for(int i=0;i<jetonV.size();i++)
		{

			GridPane.setRowIndex(jetonV.get(i),0);
			GridPane.setColumnIndex(jetonV.get(i),i);

			cadreJetons.getChildren().addAll(jetonV.get(i));
			System.out.println("1");
		}
		
		cadreJetons.setAlignment(Pos.CENTER_RIGHT);
		this.getChildren().add(cadreJetons);
		
	}
	
	public void setControllerJetonV(EventHandler ac)
	{
		for(JetonV jt: jetonsV)
		{
			jt.addEventHandler(MouseEvent.MOUSE_CLICKED, ac);
		}
	}
	
}