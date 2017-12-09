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
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class FenetreJeu extends StackPane{

	private int largeur,hauteur;
	private InfoJoueur ij;
	private PlateauV p;
	private ImageView background,legende;
	private MenuCommande mc;
	private PiocheJeton pj;
	private JokerChoixLettre jcl;
	private DictionnaireV dico;
	private EchangerV ev;
	private FenetreMessage fm;
	private Chronometre chrono;
	private AfficheScoresAutreJoueurs afficheAutreJoueurs;
	private FinPartie finPartie;
	




	public FenetreJeu() {
		
		// On récupère la résolution de l'écran
		
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int)dimension.getHeight();
		int width  = (int)dimension.getWidth();
		largeur=width;
		hauteur=height;
		
		background=new ImageView(getClass().getClassLoader().getResource("images/background.jpg").toString());
		background.setFitHeight(hauteur);
		background.setFitWidth(largeur);
		background.setPickOnBounds(false);
		
		legende=new ImageView(getClass().getClassLoader().getResource("images/legendes.png").toString());
		legende.setFitHeight(hauteur*0.30);
		legende.setFitWidth(largeur*0.12);
		legende.setTranslateX(largeur*0.42);
		legende.setTranslateY(hauteur*0.2);
		
		
		p=new PlateauV(largeur*0.47);
		p.setTranslateX(largeur*0.08);
		p.setTranslateY(0);
		p.setPickOnBounds(false);
		
		
		mc=new MenuCommande(height,width);
		//mc.setTranslateY(height*0.2);
		mc.setTranslateX(width*0.07);
		mc.setTranslateY(height*0.10);
		mc.setAlignment(Pos.CENTER_LEFT);
		mc.setPickOnBounds(false);
		
		
		 
		ij=new InfoJoueur(largeur,hauteur);
		ij.setAlignment(Pos.TOP_LEFT);
		ij.afficheNom("Killua");
		ij.setPickOnBounds(false);
		ij.setTranslateX(0);
		ij.setTranslateY(0);
		
		//pj=new PiocheJeton(1080,1900);
		
		
		jcl=new JokerChoixLettre(hauteur,largeur);
		jcl.getSp().setVisible(false);
		
		ev=new EchangerV(largeur,hauteur);
		ev.setVisible(false);
		
		dico=new DictionnaireV(largeur,hauteur);
		dico.setVisible(false);
		
		fm=new FenetreMessage(largeur,hauteur);
		fm.setVisible(false);
		
		chrono=new Chronometre(largeur,hauteur);
		
		finPartie=new FinPartie(largeur,hauteur);
		finPartie.setVisible(false);
		
		this.setPadding(new Insets(0.03*hauteur, 0.03*largeur,0.03*hauteur,0.03*largeur));
		this.getChildren().addAll(background,legende,p,ij,mc,jcl.getSp(),dico,ev,chrono,fm,finPartie);
	
	}
	
	public Chronometre getChrono() {
		return chrono;
	}

	public void setChrono(Chronometre chrono) {
		this.chrono = chrono;
	}

	public DictionnaireV getDico() {
		return dico;
	}

	public EchangerV getEv() {
		return ev;
	}

	public void setEv(EchangerV ev) {
		this.ev = ev;
	}

	public void setDictionnaireV(DictionnaireV DictionnaireV) {
		dico = DictionnaireV;
	}

	public JokerChoixLettre getJcl() {
		return jcl;
	}

	public void setJcl(JokerChoixLettre jcl) {
		this.jcl = jcl;
	}

	public MenuCommande getMc() {
		return mc;
	}

	public void setMc(MenuCommande mc) {
		this.mc = mc;
	}

	public PlateauV getPlateauV() {
		return p;
	}

	public void setP(PlateauV p) {
		this.p = p;
	}
	
	
	public FenetreMessage getFm() {
		return fm;
	}

	public PiocheJeton getPj() {
		return pj;
	}

	public void setPj(PiocheJeton pj) {
		this.pj = pj;
	}

	public InfoJoueur getIj() {
		return ij;
	}

	public void setIj(InfoJoueur ij) {
		this.ij = ij;
	}
	
	public DictionnaireV getDicoV()
	{
		return dico;
	}
	
	public FinPartie getFinPartie() {
		return finPartie;
	}

	public void setFinPartie(FinPartie finPartie) {
		this.finPartie = finPartie;
	}

	
	public ArrayList<JetonV> genereJetonV(ArrayList<Jeton> jetons)
	{
		ArrayList<JetonV> jetonv=new ArrayList<JetonV>();
		for(Jeton jt: jetons)
		{
			jetonv.add(new JetonV(jt.getLettre()));
		}
		
		return jetonv;
	}

	public void setInFoJoueur(Joueur j)
	{
		ij.setInfoJoueur(j);
		ij.afficheInfoJetons(genereJetonV(j.getJetons()));
	}
	
	public void afficheNomJoueur(String n)
	{
		ij.afficheNom(n);
	}
	
	public void ajoutController(EventHandler<MouseEvent> cp)
	{
		ij.setControllerJetonV(cp);
	}
	
	public void melangeJetons(Joueur j)
	{
		ij.viderJetons();
		ij.afficheInfoJetons(genereJetonV(j.getJetons()));
	}
	
	public void affichePremierJoueur(ArrayList ls)
	{
		this.pj.affichePioche(ls);
	}
	
	public AfficheScoresAutreJoueurs getAfficheAutreJoueurs() {
		return afficheAutreJoueurs;
	}

	public void setAfficheAutreJoueurs(int nbJoueur) {
		
		this.afficheAutreJoueurs = new AfficheScoresAutreJoueurs(largeur,hauteur);
		this.afficheAutreJoueurs.setBackgroundByNbJoueur(nbJoueur);
		this.getChildren().add(this.afficheAutreJoueurs);
		
	}
	

}