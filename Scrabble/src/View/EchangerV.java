package View;

import java.util.ArrayList;

import Controller.ControllerPlateau;
import Model.Jeton;
import Model.Joueur;
import Model.Sac;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class EchangerV extends StackPane{

	private GridPane cadreJetonJoueur;
	private GridPane cadreToutLesJetons;
	private ArrayList<JetonV> toutLesJetons;
	private ArrayList<JetonV> jetonJoueur;

	
	private Rectangle background;
	private Rectangle fenetre,quitter,valider;
	
	private VBox contenuFenetre;
	private ControllerPlateau cp;
	
	
	private double largeur,hauteur;
	
	
	
	public EchangerV(double largeur,double hauteur)
	{
		
		this.largeur=largeur;
		this.hauteur=hauteur;
		
		cadreToutLesJetons=new GridPane();
		cadreToutLesJetons.setAlignment(Pos.CENTER);
		cadreToutLesJetons.setHgap(10);
		cadreToutLesJetons.setVgap(10);
		cadreToutLesJetons.setPickOnBounds(false);
		
		cadreJetonJoueur=new GridPane();
		cadreJetonJoueur.setAlignment(Pos.CENTER);
		cadreJetonJoueur.setHgap(10);
		cadreJetonJoueur.setVgap(10);
		cadreJetonJoueur.setPickOnBounds(false);
		
		toutLesJetons=new ArrayList<JetonV>();
		jetonJoueur=new ArrayList<JetonV>();
		
		contenuFenetre=new VBox();
		
		
		background=new Rectangle();
		background.setHeight(1080);
		background.setWidth(1920);
		background.setFill(Color.web("rgba(255,255,255,0.7)"));
		
		fenetre=new Rectangle();
		fenetre.setWidth(3*largeur/5);
		fenetre.setHeight(3*hauteur/5);
		fenetre.setArcHeight(60);
		fenetre.setArcWidth(60);
		fenetre.setStroke(Color.BLACK);
        fenetre.setStrokeWidth(10);
		fenetre.setFill(Color.web("rgba(255,255,255,1)"));
	
		Text t =new Text("Échanger les lettres");
		t.setFont(Font.loadFont("file:ressource/police/Munich.ttf",40));
		t.setUnderline(true);
		t.setPickOnBounds(false);
		
		Text t2 =new Text("Sélectionnez vos lettres à échanger");
		t2.setFont(Font.loadFont("file:ressource/police/Munich.ttf",40));
		t2.setPickOnBounds(false);
		
		/*Text t3 =new Text("L'échange comptera pour un tour joué");
		t3.setFont(Font.loadFont("file:ressource/police/Munich.ttf",28));
		t3.setPickOnBounds(false);*/
		
		contenuFenetre.getChildren().addAll(t,cadreToutLesJetons,t2,cadreJetonJoueur);
		contenuFenetre.setAlignment(Pos.CENTER);
		contenuFenetre.setSpacing(hauteur*0.05);
		//contenuFenetre.setTranslateY();
		
		quitter=new Rectangle();
		quitter.setWidth(largeur/6);
		quitter.setHeight(hauteur/10);
		quitter.setArcHeight(60);
		quitter.setArcWidth(60);
		quitter.setStroke(Color.BLACK);
        quitter.setStrokeWidth(7);
		quitter.setFill(Color.web("rgba(245,25,25,0.9)"));
		quitter.setOnMouseEntered(e->{
			DropShadow ds = new DropShadow();
            ds.setOffsetY(10.0);
            ds.setOffsetX(10.0);
            ds.setColor(Color.BLACK);
            
        	quitter.setEffect(ds);
			
		});
		
		quitter.setOnMouseExited(e->{
			quitter.setEffect(null);
		});
		
		quitter.setOnMouseClicked(e->{
			this.setVisible(false);
		});
		Text quit=new Text("Annuler");
		quit.setFont(Font.loadFont("file:ressource/police/Munich.ttf",32));
		quit.setPickOnBounds(false);
		
		StackPane spq=new StackPane();
		spq.setPickOnBounds(false);
		
		spq.getChildren().addAll(quitter,quit);
		spq.setTranslateX(0.1*largeur);
		spq.setTranslateY(0.4*hauteur);
		
		valider=new Rectangle();
		valider.setWidth(largeur/6);
		valider.setHeight(hauteur/10);
		valider.setArcHeight(60);
		valider.setArcWidth(60);
		valider.setStroke(Color.BLACK);
		valider.setStrokeWidth(7);
		valider.setFill(Color.web("rgba(25,245,25,0.9)"));
		valider.setOnMouseEntered(e->{
			DropShadow ds = new DropShadow();
            ds.setOffsetY(10.0);
            ds.setOffsetX(10.0);
            ds.setColor(Color.BLACK);
            
            valider.setEffect(ds);
			
		});
		
		valider.setOnMouseExited(e->{
			valider.setEffect(null);
		});
		
		valider.setOnMouseClicked(e->{
			cp.echanger();
			this.setVisible(false);
		});
		
		Text valid=new Text("Échanger");
		valid.setFont(Font.loadFont("file:ressource/police/Munich.ttf",32));
		valid.setPickOnBounds(false);
		
		 StackPane spq2=new StackPane();
		spq2.setPickOnBounds(false);
		
		spq2.getChildren().addAll(valider,valid);
		spq2.setTranslateX(-0.15*largeur);
		spq2.setTranslateY(0.4*hauteur);
		
		this.getChildren().addAll(background,fenetre,contenuFenetre,spq,spq2);
		
	}
	
	public void setInformation(Sac sac,Joueur j)
	{
		genereToutLesJetons();
		setJetonSac(sac);
		setJetonJoueur(j);
	}
	
	public void setJetonSac(Sac sac)
	{
		cadreToutLesJetons.getChildren().clear();
		
		for(int i=0;i<27;i++)
		{
			JetonV jt=toutLesJetons.get(i);
			
			ImageView img=new ImageView(getClass().getClassLoader().getResource("images/jetons/"+jt.getLettre()+".png").toString());
			img.setFitHeight(largeur*0.03);
			img.setFitWidth(largeur*0.03);
			
			HBox hb=new HBox();
				Label lb=new Label();
				lb.setText(""+sac.getNbOccurence(jt.getLettre()));
				lb.setFont(Font.loadFont("file:ressource/police/Munich.ttf",30));
			hb.getChildren().addAll(img,lb);
			hb.setPadding(new Insets(0,10,0,0));
		    hb.setSpacing(10);
			
			System.out.println("=> "+jt.getLettre()+"  "+sac.getNbOccurence(jt.getLettre()));
			if(i<9){
				GridPane.setRowIndex(hb,0);
				GridPane.setColumnIndex(hb,i);
				}
				else if(i<18){
					GridPane.setRowIndex(hb,1);
					GridPane.setColumnIndex(hb,i-9);
				}
				else{
					GridPane.setRowIndex(hb,2);
					GridPane.setColumnIndex(hb,i-18);
				}
				cadreToutLesJetons.getChildren().addAll(hb);
			
		}
		
	
	}
	
	public void setJetonJoueur(Joueur j)
	{
		ArrayList<Jeton> jetons=j.getJetons();
		jetonJoueur.clear();
		
		cadreJetonJoueur.getChildren().clear();
		cadreJetonJoueur.setMaxSize(300, 300);
		cadreJetonJoueur.setHgap(10);
		cadreJetonJoueur.setVgap(10);
		
		for(int i=0;i<jetons.size();i++)
		{
			JetonV jtv=new JetonV(jetons.get(i).getLettre());
			jtv.setJetonPourEchanger(true);
			
			jtv.addEventHandler(MouseEvent.MOUSE_CLICKED, cp);
			
			if(i<8){
			GridPane.setRowIndex(jtv,0);
			GridPane.setColumnIndex(jtv,i);
			}
			else{
				GridPane.setRowIndex(jtv,1);
				GridPane.setColumnIndex(jtv,i-8);
			}
			jetonJoueur.add(jtv);
			cadreJetonJoueur.getChildren().addAll(jtv);
			animUnJeton(jtv,i);
			
		}
		
		cadreJetonJoueur.setAlignment(Pos.CENTER_RIGHT);
		
	}
	
	public void animUnJeton(JetonV jtv,int i)
	{
		
		
		double x=jtv.getLayoutX();
		double y=jtv.getLayoutY();
		
		if(i==0){jtv.setTranslateX(-largeur);jtv.setTranslateY(-hauteur);}
		else if(i==1){jtv.setTranslateX(largeur);jtv.setTranslateY(-hauteur);}
		else if(i==2){jtv.setTranslateY(hauteur);jtv.setTranslateX(-largeur);}
		else if(i==3){jtv.setTranslateY(hauteur);jtv.setTranslateX(largeur);}
		else if(i==4){jtv.setTranslateX(largeur);}
		else if(i==5){jtv.setTranslateX(-largeur);}
		else if(i==6){jtv.setTranslateY(-hauteur);}
		
		
		TranslateTransition t=new TranslateTransition();
		t.setNode(jtv);
		t.setToX(x);
		t.setToY(y);
		t.setDuration(Duration.seconds(1.5));
		t.setByX(10);
		t.setByY(10);
		t.play();
			
	}
	
	public void genereToutLesJetons()
	{
		toutLesJetons.add(new JetonV('A'));
		toutLesJetons.add(new JetonV('B'));
		toutLesJetons.add(new JetonV('C'));
		toutLesJetons.add(new JetonV('D'));
		toutLesJetons.add(new JetonV('E'));
		toutLesJetons.add(new JetonV('F'));
		toutLesJetons.add(new JetonV('G'));
		toutLesJetons.add(new JetonV('H'));
		toutLesJetons.add(new JetonV('I'));
		toutLesJetons.add(new JetonV('J'));
		toutLesJetons.add(new JetonV('K'));
		toutLesJetons.add(new JetonV('L'));
		toutLesJetons.add(new JetonV('M'));
		toutLesJetons.add(new JetonV('N'));
		toutLesJetons.add(new JetonV('O'));
		toutLesJetons.add(new JetonV('P'));
		toutLesJetons.add(new JetonV('Q'));
		toutLesJetons.add(new JetonV('R'));
		toutLesJetons.add(new JetonV('S'));
		toutLesJetons.add(new JetonV('T'));
		toutLesJetons.add(new JetonV('U'));
		toutLesJetons.add(new JetonV('V'));
		toutLesJetons.add(new JetonV('W'));
		toutLesJetons.add(new JetonV('X'));
		toutLesJetons.add(new JetonV('Y'));
		toutLesJetons.add(new JetonV('Z'));
		toutLesJetons.add(new JetonV('^'));
		
		
	}
	
	public ArrayList<JetonV> getJetonSelectionne()
	{
		ArrayList<JetonV> jetonsSelec=new ArrayList<JetonV>();
		
		for(JetonV jtv: jetonJoueur)
		{
			if(jtv.isSelectionnePourEchange())
			{
				jetonsSelec.add(jtv);
			}
		}
		
		return jetonsSelec;
	}

	public ControllerPlateau getCp() {
		return cp;
	}

	public void setCp(ControllerPlateau cp) {
		this.cp = cp;
	}
}
