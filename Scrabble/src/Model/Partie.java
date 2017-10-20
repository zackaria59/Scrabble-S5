package Model;

import java.util.ArrayList;

public class Partie {

	private ArrayList<Joueur> joueurs;
	private Sac sac;
	private Plateau plateau;
	private Dictionnaire dico;

	private boolean joueurJoueTour;
	
	public Partie(ArrayList<Joueur> joueurs, Sac sac, Plateau plateau) {
		this.joueurs = joueurs;
		this.sac = sac;
		this.plateau = plateau;
		setJoueurJoueTour(true);

	}

	public ArrayList<Joueur> getJoueurs() {
		return joueurs;
	}

	public void setJoueurs(ArrayList<Joueur> joueurs) {
		this.joueurs = joueurs;
	}

	public Joueur designePremierJoueur()
	{
		Jeton jt1=null,jt2=null,jt3=null,jt4=null;
		int nbjoueur=0;
		
		for (int i=0;i<joueurs.size();i++)
		{
			if(i==0)jt1=joueurs.get(i).piocherUnJeton(sac);nbjoueur++;
			
			if(i==1)jt2=joueurs.get(i).piocherUnJeton(sac);nbjoueur++;
			
			if(i==2)jt3=joueurs.get(i).piocherUnJeton(sac);nbjoueur++;
			
			if(i==3)jt4=joueurs.get(i).piocherUnJeton(sac);nbjoueur++;
		}
		
		if(nbjoueur==2)
		{
			if(jt1.getLettre()<jt2.getLettre())
			{
				return joueurs.get(0);
			}
			else if(jt1.getLettre()>jt2.getLettre())
			{
				return joueurs.get(1);
			}
		}
		else if(nbjoueur==3)
		{
			
		}
		
		
		
		return null;
		
		
	}

	public static void main(String[] args) {
		
	
	}

	public boolean isJoueurJoueTour() {
		return joueurJoueTour;
	}

	public void setJoueurJoueTour(boolean joueurJoueTour) {
		this.joueurJoueTour = joueurJoueTour;
	}
}
