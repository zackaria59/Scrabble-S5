package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Joueur implements Serializable {
	
	protected int id;
	protected String pseudo;
	protected int score;
	protected ArrayList<Jeton> jetons;
	protected boolean joueTour;
	private int compteurAide;
	private boolean ia;
	
	public Joueur(int id, String pseudo,int score,boolean ia){
		this.id=id;
		this.pseudo=pseudo;
		this.score=score;
		this.joueTour=false;
		jetons=new ArrayList<Jeton>();
		this.setIa(ia);
		compteurAide=5;
	}
	
	public void piocher(Sac sac)    // Le joueur tire un jeton du sac 
	{
		Jeton jetonPiocher=piocherUnJeton(sac);
		
		if(jetonPiocher!=null){
			jetons.add(jetonPiocher); // on ajoute le jeton dans la liste de jeton du joueur
		}
								 
	}
	
	public Jeton piocherUnJeton(Sac sac)
	{
		Jeton jt=null;
		
		if(!sac.getJetons().isEmpty()){
			int nbAleatoire=(int) (Math.random() * ( sac.getJetons().size() - 0 ));
			jt=sac.getJetons().get(nbAleatoire);
			sac.getJetons().remove(nbAleatoire);
		}
		
		return jt;
	}
	
	public void piocherNbFois(int nb,Sac sac)
	{
		for(int i=0;i<nb;i++)
		{
			piocher(sac);
		}
	}
	
	public void piocherDebutPartie(Sac sac)
	{
		for(int i=0;i<7;i++)
		{
			piocher(sac);
		}
	}
	
	public ArrayList<Jeton> getJetons() {
		return jetons;
	}

	public void setJetons(ArrayList<Jeton> jetons) {
		this.jetons = jetons;
	}

	public void echangerJeton(ArrayList<Integer> id,Sac sac)      //On échange les jetons à partir d'un tableau contenant les id des jetons selectionné par le joueur
	{
		for(int i=0;i<id.size();i++)
		{
			sac.ajouteUnJeton(getJetonById(id.get(i)));       // On récupère le jeton de la liste du joueur avec son id et on lajoute au Sac
			removeJetonById(id.get(i));							// Et on supprime le jeton de la liste du joueur
			piocher(sac);                                        // Et on pioche un jeton dans le sac tout cela équivaut à un échange
		}
	}
	
	public Jeton getJetonById(int id)
	{
		for(Jeton jt: jetons)
		{
			if(jt.getId()==id)
			{
				return jt;
			}
		}
		
		return null;
	}
	
	public void removeJetonById(int id)
	{
		for(int i=0;i<jetons.size();i++)
		{
			if(jetons.get(i).getId()==id)
			{
				jetons.remove(i);
			}
		}
	}
	
	public void removeJetonByChar(char c)
	{
		for(int i=0;i<jetons.size();i++)
		{
			if(jetons.get(i).getLettre()==c)
			{
				jetons.remove(i);
				break;
			}
		}
	}
	
	public String getPseudo(){
		return pseudo;
	}
	
	public int getId(){
		return id;
	}
	
	public int getScore(){
		return score;
	}

	public boolean isJoueTour() {
		return joueTour;
	}

	public void setJoueTour(boolean joueTour) {
		this.joueTour = joueTour;
	}
	
	public void addPoints(int points)
	{
		
		System.out.println("\n\nScore + points = "+score+" + "+points+" = "+(score+points));
		this.score=this.score+points;
	}
	
	public void afficheJetonsEnMain()
	{
		System.out.println("Mes jetons : ");
		for(Jeton jt: jetons)
		{
			System.out.print(jt.getLettre()+" | ");
		}
		
		System.out.println("");
	}
	
	public Jeton getJetonByChar(char c)
	{
		for(Jeton jt:jetons)
		{
			if(jt.getLettre()==c)
			{
				return jt;
			}
		}
		
		return null;
	}

	public int getCompteurAide() {
		return compteurAide;
	}

	public void setCompteurAide(int compteurAide) {
		this.compteurAide = compteurAide;
	}

	public boolean isIa() {
		return ia;
	}

	public void setIa(boolean ia) {
		this.ia = ia;
	}

}
