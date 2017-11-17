package View;

import java.awt.Dimension;
import java.util.ArrayList;

import Controller.ControllerPlateau;
import Model.Jeton;
import Model.Joueur;
import Model.Partie;
import Model.Plateau;
import Model.Sac;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Launcher extends Application{

	
	public static void main(String[] args)
	{
		
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int)dimension.getHeight();
		int width  = (int)dimension.getWidth();
		
		Joueur j1=new Joueur(1,"Cr7",0);
		Joueur j2=new Joueur(2,"Messi",0);
		Joueur j3=new Joueur(1,"Kaneki",0);
		Joueur j4=new Joueur(2,"Ehlmaire",0);
		
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
		
		
		Partie p=new Partie(joueurs,sac,plateau,true);
		FenetreJeu fj=new FenetreJeu();
		ControllerPlateau cp =new ControllerPlateau(p,fj);
		Accueil a = new Accueil();
		
		
		Scene scene=new Scene(fj);
		
		stage.setScene(scene);
		stage.setHeight(height);
		stage.setWidth(width);
		stage.setFullScreen(true);
		stage.setMaximized(true);
		stage.show();
		
		
	}
	
}
