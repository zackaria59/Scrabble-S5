package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Joueur implements Serializable {
	
	private int id;
	private String pseudo;
	private int score;
	private ArrayList<Jeton> jetons;
	private boolean joueTour;
	
	public Joueur(int id, String pseudo,int score){
		this.id=id;
		this.pseudo=pseudo;
		this.score=score;
		this.joueTour=false;
		jetons=new ArrayList<Jeton>();
	}
	
	public void piocher(Sac sac)    // Le joueur tire un jeton du sac 
	{
		int nbAleatoire=(int) (Math.random() * ( sac.getJetons().size() - 0 ));
		jetons.add(sac.getJetons().get(nbAleatoire));                // on ajoute le jeton dans la liste de jeton du joueur
		sac.getJetons().remove(nbAleatoire);						 // puis on supprime le jeton du sac 
	}
	
	public Jeton piocherUnJeton(Sac sac)
	{
		int nbAleatoire=(int) (Math.random() * ( sac.getJetons().size() - 0 ));
		return sac.getJetons().get(nbAleatoire);
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

	public void echangerJeton(ArrayList<Integer> id,Sac sac)      //On �change les jetons � partir d'un tableau contenant les id des jetons selectionn� par le joueur
	{
		for(int i=0;i<id.size();i++)
		{
			sac.ajouteUnJeton(getJetonById(id.get(i)));       // On r�cup�re le jeton de la liste du joueur avec son id et on lajoute au Sac
			removeJetonById(id.get(i));							// Et on supprime le jeton de la liste du joueur
			piocher(sac);                                        // Et on pioche un jeton dans le sac tout cela �quivaut � un �change
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

}
