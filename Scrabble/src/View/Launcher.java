package View;

import java.awt.Dimension;
import java.util.ArrayList;

import Controller.ControllerPlateau;
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
		
		Joueur j1=new Joueur(1,"Joueur1",0);
		Joueur j2=new Joueur(2,"Joueur2",0);
		ArrayList<Joueur> joueurs=new ArrayList<Joueur>();
		
		Sac sac=new Sac();
		
		Plateau plateau=new Plateau();
		
		Partie p=new Partie(joueurs,sac,plateau);
		FenetreJeu fj=new FenetreJeu();
		ControllerPlateau cp =new ControllerPlateau(p,fj);
		
		Scene scene=new Scene(fj);
		stage.setScene(scene);
		stage.setHeight(height);
		stage.setWidth(width);
		stage.setFullScreen(true);
		stage.setMaximized(true);
		stage.show();
	}
	
}
