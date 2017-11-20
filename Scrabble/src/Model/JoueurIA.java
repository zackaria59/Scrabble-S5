package Model;

import java.util.ArrayList;

public class JoueurIA extends Joueur{

	Plateau plateau;
	
	public JoueurIA(int id, String pseudo, int score) {
		super(id, pseudo, score);
		
	}
	
	public void joueTour(Plateau p)
	{
		this.plateau=p;
		
		ArrayList<ArrayList<Jeton>> listMots=getToutlesMotsPlateau();
		
		this.afficheToutLesMotsPlateau(getToutlesMotsPlateau());
		
	}
	
	public ArrayList<ArrayList<Jeton>> getToutlesMotsPlateau()
	{
		ArrayList<ArrayList<Jeton>> listMots=new ArrayList<ArrayList<Jeton>>();
		ArrayList<ArrayList<Jeton>> listMotsTamp;
		
		for(int x=0;x<15;x++)
		{
			listMotsTamp=getMotsParLigne(x);
			
			if(!listMotsTamp.isEmpty())
			{
				listMots.addAll(getMotsParLigne(x));
			}
		}
		
		for(int y=0;y<15;y++)
		{
			listMotsTamp=getMotsParColonne(y);
			
			if(!listMotsTamp.isEmpty())
			{
				listMots.addAll(getMotsParLigne(y));
			}
		}
		
		return listMots;
	}
	
	private ArrayList<ArrayList<Jeton>> getMotsParColonne(int colonne) {
		
		ArrayList<ArrayList<Jeton>> listMots=new ArrayList<ArrayList<Jeton>>();
		ArrayList<Jeton> mot=new ArrayList<Jeton>();
		
		int cpt;
		for(int x=0;x<15;x++)
		{
			if(plateau.getPlateau()[x][colonne].jetonValide())
			{
				cpt=x;
				
				while(plateau.getPlateau()[cpt][colonne].jetonValide())
				{
					mot.add(plateau.getPlateau()[cpt][colonne].getJ());
					cpt++;
				}
				
				listMots.add(mot);
				x=cpt;
			}
		}
		
		return listMots;
	}

	public ArrayList<ArrayList<Jeton>> getMotsParLigne(int ligne)
	{
		ArrayList<ArrayList<Jeton>> listMots=new ArrayList<ArrayList<Jeton>>();
		ArrayList<Jeton> mot=new ArrayList<Jeton>();
		
		int cpt;
		for(int y=0;y<15;y++)
		{
			if(plateau.getPlateau()[ligne][y].jetonValide())
			{
				cpt=y;
				
				while(plateau.getPlateau()[ligne][cpt].jetonValide())
				{
					mot.add(plateau.getPlateau()[ligne][cpt].getJ());
					cpt++;
				}
				
				listMots.add(mot);
				y=cpt;
			}
		}
		
		return listMots;
	}

	public void afficheToutLesMotsPlateau(ArrayList<ArrayList<Jeton>> mots)
	{
		for(ArrayList<Jeton> o: mots)
		{
			for(Jeton jt: o)
			{
				System.out.print(jt.getLettre()+" | ");
			}
			System.out.println("");
		}
	}
}
