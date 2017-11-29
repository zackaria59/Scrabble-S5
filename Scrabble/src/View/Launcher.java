package View;

import java.awt.Dimension;
import java.util.ArrayList;

import Controller.ControllerPlateau;
import Model.Jeton;
import Model.Joueur;
import Model.JoueurIA;
import Model.Partie;
import Model.Plateau;
import Model.Sac;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Launcher extends Application{

	boolean echap=false;
	
	public static void main(String[] args)
	{
		
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int)dimension.getHeight();
		int width  = (int)dimension.getWidth();
		
		Joueur j1=new Joueur(1,"Cr7",0,false);
		JoueurIA j2=new JoueurIA(2,"Messi",0,4);
		JoueurIA j3=new JoueurIA(1,"Kaneki",0,4);
		JoueurIA j4=new JoueurIA(2,"IA",0,4);
		
		ArrayList<Joueur> joueurs=new ArrayList<Joueur>();
		joueurs.add(j1);
		joueurs.add(j2);
		joueurs.add(j3);
		joueurs.add(j4);
		
		
		Sac sac=new Sac();
		
		Plateau plateau=new Plateau();
		
		j1.piocherDebutPartie(sac);
		j2.piocherDebutPartie(sac);
		j3.piocherDebutPartie(sac);
		j4.piocherDebutPartie(sac);
		
		
		StackPane container=new StackPane();
		Partie p=new Partie(joueurs,sac,plateau,true);
		EchapMenu em=new EchapMenu(1920,1080,p);
		FenetreJeu fj=new FenetreJeu();
		em.setVisible(false);
		fj.getChildren().add(em);
		ControllerPlateau cp =new ControllerPlateau(p,fj);
		Accueil a = new Accueil(stage);
		
		container.getChildren().add(a);
		
		Scene scene=new Scene(container);
		
		scene.setOnKeyPressed(e->{
		
			if(e.getCode()==KeyCode.ESCAPE)
			{
				echap=!echap;
				em.setVisible(echap);
			}
		});
		
		stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
		stage.setScene(scene);
		stage.setHeight(height);
		stage.setWidth(width);
		stage.setFullScreen(true);
		stage.setMaximized(true);
		stage.show();
		
		
	}
	
}
