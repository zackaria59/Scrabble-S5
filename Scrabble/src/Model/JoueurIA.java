package Model;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class JoueurIA extends Joueur implements Serializable{

	Plateau plateau;
	ArrayList<String> combinaisonMot;
	ArrayList<Mot> motPoseValide;
	Dictionnaire dico;
	int nbpointsCoupJoue;
	ArrayList<Object> motEtPoint;
	int difficulte;
	
	public JoueurIA(int id, String pseudo, int score,int difficulte) {
		super(id, pseudo, score,true);
		this.combinaisonMot= new ArrayList<String>();
		motPoseValide=new ArrayList<Mot>();
		motEtPoint=new ArrayList<Object>();
		this.difficulte=difficulte;
		
	}
	
	public LinkedList<Jeton> joueTour(Partie partie) throws IOException{
		intialiseToutMotsPosable(partie.getPlateau(),partie);
		
		LinkedList<Jeton> motChoisi=choisiMotParDifficulte(this.difficulte);
		System.out.println(motChoisi);
		return motChoisi;
	}
	
	public LinkedList<Jeton> choisiMotParDifficulte(int difficulte)
	{
		Random rand=new Random();
		
		System.out.println("mot et point taille ="+motEtPoint.size());
		if(difficulte==1){
			ArrayList<LinkedList<Jeton>> listMots=new ArrayList<LinkedList<Jeton>>();
			
			for(Object m:motEtPoint)
			{
				Object[] tab=(Object[])m;
				if((int)tab[1]<15)
				{
					listMots.add((LinkedList<Jeton>) tab[2]);
				}
			}
			
			return listMots.get(rand.nextInt(listMots.size()-1));
		}
		else if(difficulte==2){
			ArrayList<LinkedList<Jeton>> listMots=new ArrayList<LinkedList<Jeton>>();
			
			for(Object m:motEtPoint)
			{
				Object[] tab=(Object[])m;
				
				if(10<(int)tab[1] && (int)tab[1]<24)
				{
					listMots.add((LinkedList<Jeton>) tab[2]);
				}
			}
			
			return listMots.get(rand.nextInt(listMots.size()-1));
		}
		else if(difficulte==3){
			ArrayList<LinkedList<Jeton>> listMots=new ArrayList<LinkedList<Jeton>>();
			
			for(Object m:motEtPoint)
			{
				Object[] tab=(Object[])m;
				
				if(19<(int)tab[1] && (int)tab[1]<75)
				{
					listMots.add((LinkedList<Jeton>) tab[2]);
				}
			}
			
			return listMots.get(rand.nextInt(listMots.size()-1));
		}
		else if(difficulte==4){
			
			
			return this.trouveMeilleurMot();
		}
		
		return null;
		
	}
	
	public Dictionnaire getDico() {
		return dico;
	}

	public void setDico(Dictionnaire dico) {
		this.dico = dico;
	}

	public void intialiseToutMotsPosable(Plateau p,Partie partie) throws IOException
	{
		this.plateau=p;
		this.combinaisonMot.clear();
		this.motEtPoint.clear();
		
		motPoseValide=getToutlesMotsPlateau();
		this.initialiseCombinaisonMotsEnMain();
		
		
		for(Mot mt: motPoseValide)
		{
			mt.initialiseMotCombinaisons(this.combinaisonMot);
			mt.filtreParMotValide(dico);
			mt.filtreParMotPosable(p, partie);
			motEtPoint.addAll(mt.getMotsPosable());
		}
		
	}
	
	public ArrayList<Mot> getToutlesMotsPlateau()
	{
		ArrayList<Mot> listMots=new ArrayList<Mot>();
		ArrayList<Mot> listMotsTamp;
		
		for(int x=0;x<15;x++)
		{
			
			listMotsTamp=getMotsParLigne(x);
			
			if(!listMotsTamp.isEmpty())
			{
				System.out.println("KLO");
				listMots.addAll(listMotsTamp);
			}
		}
		
		for(int y=0;y<15;y++)
		{
			listMotsTamp=getMotsParColonne(y);
			
			if(!listMotsTamp.isEmpty())
			{
				listMots.addAll(listMotsTamp);
			}
		}
		
		return listMots;
	}
	
	private ArrayList<Mot> getMotsParColonne(int colonne) {
		
		ArrayList<Mot> mots=new ArrayList<Mot>();
		
		ArrayList<ArrayList<Jeton>> listMots=new ArrayList<ArrayList<Jeton>>();
		ArrayList<Jeton> mot=new ArrayList<Jeton>();
		
		int espaceGauche=0,espaceDroite=0;
		int cpt;
		for(int x=0;x<15;x++)
		{
			if(plateau.getPlateau()[x][colonne].jetonValide())
			{
				cpt=x;
				mot=new ArrayList<Jeton>();
				boolean falg=true;
				
				while(plateau.getPlateau()[cpt][colonne].jetonValide())
				{
					mot.add(plateau.getPlateau()[cpt][colonne].getJ());
					cpt++;
					if(cpt==15)break;
						
				}
				
				listMots.add(mot);
				x=cpt;
			}
			
		}
		
		for(ArrayList<Jeton> mt:listMots)
		{
			int y=mt.get(0).getY();
			int xmin=mt.get(0).getX();
			int xmax=mt.get(mt.size()-1).getX();
			boolean flag=true;
			
			espaceGauche=0;
			espaceDroite=0;
			
			while(xmin!=0 && flag)
			{
				if(!plateau.getPlateau()[xmin-1][y].jetonValide())
				{
					espaceGauche++;
				}
				else{
					flag=false;
					espaceGauche--;
				}
				
				xmin--;
			}
			
			flag=true;
			
			while(xmax!=14 && flag)
			{
				if(!plateau.getPlateau()[xmax+1][y].jetonValide())
				{
					espaceDroite++;
				}
				else{
					flag=false;
					espaceDroite--;
				}
				
				xmax++;
			}
			
			Mot mott=new Mot(mt,espaceGauche,espaceDroite);
			mott.setMotEnColonne(true);
			
			mots.add(mott);
		}
		
		return mots;
	}

	public ArrayList<Mot> getMotsParLigne(int ligne)
	{
		ArrayList<Mot> mots=new ArrayList<Mot>();
		
		ArrayList<ArrayList<Jeton>> listMots=new ArrayList<ArrayList<Jeton>>();
		ArrayList<Jeton> mot=new ArrayList<Jeton>();
		
		int espaceGauche=0,espaceDroite=0;
		int cpt;
		
		for(int y=0;y<15;y++)
		{
			if(plateau.getPlateau()[ligne][y].jetonValide())
			{
				cpt=y;
				mot=new ArrayList<Jeton>();
					while(plateau.getPlateau()[ligne][cpt].jetonValide())
					{
						mot.add(plateau.getPlateau()[ligne][cpt].getJ());
						cpt++;
						if(cpt==15)break;
					}
				
				System.out.print("taille du mot = "+mot.size());
				
				for(Jeton jt: mot)
				{
					System.out.print("  "+jt.getLettre()+" |");
				}
				
				
				listMots.add(mot);
				y=cpt;
			}
			
			
			
		}
		
		for(ArrayList<Jeton> mt:listMots)
		{
			int x=mt.get(0).getX();
			int ymin=mt.get(0).getY();
			int ymax=mt.get(mt.size()-1).getY();
			boolean flag=true;
			espaceGauche=0;
			espaceDroite=0;
			
			while(ymin!=0 && flag)
			{
				if(!plateau.getPlateau()[x][ymin-1].jetonValide())
				{
					espaceGauche++;
				}
				else{
					flag=false;
					espaceGauche--;
				}
				
				ymin--;
			}
			
			flag=true;
			
			while(ymax!=14 && flag)
			{
				if(!plateau.getPlateau()[x][ymax+1].jetonValide())
				{
					espaceDroite++;
				}
				else{
					flag=false;
					espaceDroite--;
				}
				
				ymax++;
			}
			
			Mot mott=new Mot(mt,espaceGauche,espaceDroite);
			mott.setMotEnLigne(true);
			
			mots.add(mott);
		}
		
		return mots;
	}

	public void afficheToutLesMotsPlateau(ArrayList<Mot> mots)
	{
		for(Mot o: mots)
		{
			for(Jeton jt: o.getMot())
			{
				System.out.print(jt.getLettre()+" | ");
			}
			
			System.out.println("   espaceGauche = "+o.getEspaceGauche()+"   espaceDroite = "+o.getEspaceDroite());
		}
	}
	
	public void initialiseCombinaisonMotsEnMain()
	{
		for(int i=0;i<7;i++)
		{
			getCombinaisonMotsEnMain2(this.getJetons().get(i).getLettre());
		}
		
		
	}
	
	public void getCombinaisonMotsEnMain2(char c)
	{
		ArrayList<String> motsTamp=new ArrayList<String>();
		
		for(String s:this.combinaisonMot)
		{
			motsTamp.add(new String(c+s));
			motsTamp.add(new String(s+c));
			
			for(int i=0;i<s.length()-1;i++)
			{
				motsTamp.add(s.substring(0, i+1)+c+s.substring(i+1));
			}
		}
		this.combinaisonMot.addAll(motsTamp);
		this.combinaisonMot.add(""+c);
		
	}
	
	public LinkedList<Jeton> trouveMeilleurMot()
	{
		int points=0;
		String meilleurMot = null;
		LinkedList<Jeton> meilleurMotJetons = null;
		for(int i=0;i<this.motEtPoint.size();i++)
		{
			Object[] tab=(Object[]) motEtPoint.get(i);
			System.out.println(tab[0]+" "+tab[1]+" points");
			if(points<(int)tab[1])
			{
				meilleurMot=(String)tab[0];
				points=(int)tab[1];
				 meilleurMotJetons=(LinkedList<Jeton>) tab[2];
				 System.out.println(meilleurMotJetons.toString());
			}
				
		}
		
		System.out.println("======================================================>>>>>"+meilleurMot+"  "+points+"   points !!!!  x="+meilleurMotJetons.get(0).getX()+" y="+meilleurMotJetons.get(0).getY()+"<<<<<================================================");
		return meilleurMotJetons;
	}
	
		

	public LinkedList<Jeton> geMeilleurMot(Partie partie) throws IOException {
		
		intialiseToutMotsPosable(partie.getPlateau(),partie);
		
		
		return this.trouveMeilleurMot();
	}
}
