package Model;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

public class Mot implements Serializable {

	private String motS;
	private ArrayList<Jeton> mot;
	private ArrayList<String> motsCombinaison;
	private ArrayList<String> motsValide;
	private ArrayList<Object> motsPosable;
	
	private int espaceGauche,espaceDroite,espaceTotale,taille,x,y;
	private boolean motEnLigne,motEnColonne;
	
	public Mot(ArrayList<Jeton> mot,int espG,int espD)
	{
		this.setMot(mot);
		this.setEspaceDroite(espD);
		this.setEspaceGauche(espG);
		
		espaceTotale=espaceDroite+espaceGauche;
		setTaille(mot.size());
		motsCombinaison=new ArrayList<String>();
		motsValide=new ArrayList<String>();
		
		motS=".";
		x=mot.get(0).getX();
		y=mot.get(0).getY();
		
		for(int i=0;i<mot.size();i++)
		{
			motS+=mot.get(i).getLettre();
		}
		
		motEnLigne=false;
		motEnColonne=false;
	}

	public int getEspaceGauche() {
		return espaceGauche;
	}

	public void setEspaceGauche(int espaceGauche) {
		this.espaceGauche = espaceGauche;
	}

	public int getEspaceDroite() {
		return espaceDroite;
	}

	public void setEspaceDroite(int espaceDroite) {
		this.espaceDroite = espaceDroite;
	}

	public ArrayList<Jeton> getMot() {
		return mot;
	}

	public void setMot(ArrayList<Jeton> mot) {
		this.mot = mot;
	}

	public ArrayList<String> getMotsCombianaison() {
		return motsCombinaison;
	}

	public void setMotsCombianaison(ArrayList<String> motsCombianaison) {
		this.motsCombinaison = motsCombianaison;
	}

	public int getTaille() {
		return taille;
	}

	public void setTaille(int taille) {
		this.taille = taille;
	}
	
	public void initialiseMotCombinaisons(ArrayList<String> mots)
	{
		
		for(String s:mots)
		{
			
			if(espaceGauche>=s.length())
			{motsCombinaison.add(s+motS);}
			
			if(espaceDroite>=s.length())
			{motsCombinaison.add(motS+s);}
			
			if(s.length()<=espaceTotale)
			{
				for(int i=0;i<s.length()-1;i++)
				{
					if(s.substring(0, i+1).length()<=espaceGauche && s.substring(i+1).length()<=espaceDroite)
						motsCombinaison.add(s.substring(0, i+1)+motS+s.substring(i+1));
				}
			}
		}
		
		//System.out.println(motsCombinaison.toString());
		
		
		
	}
	
	public void filtreParMotValide(Dictionnaire dico)
	{
		for(String s:this.motsCombinaison)
		{
			if(dico.motExist(s))
			{
				motsValide.add(s);
			}
		}
		
		System.out.println(motsValide.toString());
	}
	
	public ArrayList<Object> filtreParMotPosable(Plateau plateau,Partie partie) throws IOException
	{
		int indiceDebutMot=0;
		String motTamp;
		this.motsPosable=new ArrayList<Object>();
		
		for(int i=0;i<motsValide.size();i++)
		{
			
			plateau.getJetonNoValide().clear();
			
			motTamp=motsValide.get(i);
			indiceDebutMot=this.getIndiceDebutMot(motTamp);
			motTamp=retireMarqueur(motTamp);
			//System.out.println("Mot teste = "+motTamp+ " à partir du mot : "+motS);
			
			
			for(int b=0;b<motTamp.length();b++)
			{
				if(b==indiceDebutMot)
				{
					b+=taille-1;
				}
				else{
						if(motEnColonne)
						{
							plateau.setMotEnColonne(true);
							plateau.setJeton((this.x-indiceDebutMot+b), this.y, motTamp.charAt(b));}
						else if(motEnLigne){
							plateau.setMotEnLigne(true);
							plateau.setJeton(this.x,(this.y-indiceDebutMot+b), motTamp.charAt(b));
						}
				}
				
			}
			
			//plateau.affichePlateauTousLesJetons();
			
			
			
			if(partie.verifMotVoisinValide() && partie.verifMotValide(plateau.getMotPose()))
			{
				//System.out.println("Mot ok :  "+motTamp+"\n");
				
				Object[] motEtPoint=new Object[3];
				motEtPoint[0]=motTamp;
				motEtPoint[1]=partie.getNbpointsCoupJoue();
				motEtPoint[2]=new LinkedList<Jeton>();
				((LinkedList<Jeton>) motEtPoint[2]).addAll(plateau.getJetonNoValide());
				
				System.out.println(motEtPoint[2].toString());
				this.motsPosable.add(motEtPoint);
				partie.setNbpointsCoupJoue(0);
				partie.getPlateau().videList();
				
			}
			
			plateau.setMotEnColonne(false);
			plateau.setMotEnLigne(false);
			plateau.retireJetonNonValide();
			partie.setNbpointsCoupJoue(0);
		}
		
		plateau.getJetonNoValide().clear();
		return this.motsPosable;
	}
	
	public ArrayList<Object> getMotsPosable() {
		return motsPosable;
	}

	public void setMotsPosable(ArrayList<Object> motsPosable) {
		this.motsPosable = motsPosable;
	}

	public int getIndiceDebutMot(String s)
	{
		for (int i=0;i<s.length();i++)
		{
			if(s.charAt(i)=='.')
			{
				return i;
			}
		}
		
		return 0;
	}

	public String retireMarqueur(String mot)
	{
		String nouveauMot="";
		
		for(int i=0;i<mot.length();i++)
		{
			if(mot.charAt(i)!='.')
			nouveauMot+=mot.charAt(i);
		}
		
		return nouveauMot;
	}
	
	public boolean isMotEnColonne() {
		return motEnColonne;
	}

	public void setMotEnColonne(boolean motEnColonne) {
		this.motEnColonne = motEnColonne;
	}

	public boolean isMotEnLigne() {
		return motEnLigne;
	}

	public void setMotEnLigne(boolean motEnLigne) {
		this.motEnLigne = motEnLigne;
	}
	
	public void videTouteLesList()
	{
		this.motsCombinaison.clear();
		this.motsPosable.clear();
		this.motsValide.clear();
	}
	
	
}
