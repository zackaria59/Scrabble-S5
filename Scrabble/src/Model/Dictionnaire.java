package Model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;

public class Dictionnaire implements Serializable{

	Noeud racine,courant;
	final BufferedReader dictionnaire;
	String pathdico="ressource/dico.txt";
	
	public Dictionnaire() throws IOException
	{
		racine=new Noeud();
		dictionnaire = new BufferedReader(new FileReader(pathdico));
		initialiseArbre();
	}
	
	public void insertMot(String mot)
	{
		if(mot!=null)
		{
			char lettre=mot.charAt(0);
			Noeud n=this.lettrePresentDansFils(lettre);
			
			if(n!=null)
			{
				courant=n;
				
				if(mot.length()!=1){
					mot=mot.substring(1);
					insertMot(mot);
					}
				else{
					Noeud finDeMot=new Noeud();
					finDeMot.c='0';
					 courant.fils.addFirst(finDeMot);
				}
				
				
			}
			else{
				Noeud nouveauNoeud=new Noeud();
				nouveauNoeud.c=lettre;
				courant.fils.addLast(nouveauNoeud);
				courant=nouveauNoeud;
				
				if(mot.length()!=1){
					mot=mot.substring(1);
					insertMot(mot);
					}
				else{
						Noeud finDeMot=new Noeud();
						finDeMot.c='0';
						 courant.fils.addFirst(finDeMot);
					}
			}
		}
	}
	
	public Noeud lettrePresentDansFils(char lettre)
	{
		for (Noeud n:courant.fils)
		{
			if(n.c==lettre)
			{
				return n;
			}
		}
		
		return null;
	}
	
	public static void afficheSousArbre(Noeud n)
	{
		for(Noeud noeud:n.fils)
		{
			System.out.print(noeud.c+" |");
			afficheSousArbre(noeud);
		}
		
		System.out.println();
	}
	
	public boolean motCaractereValide(String mot){
		
		if(mot!=null && mot!="")
		{
			for(int i=0;i<mot.length();i++)
			{
				
				if(mot.charAt(i)=='.')
				{
					
				}
				else if(mot.charAt(i)<65 || mot.charAt(i)>90)
				{
					return false;
				}
			}
			
			return true;
		}
		
		return false;
	}
	
	public boolean motExist(String mot){
		courant=racine;
		mot = mot.substring(0,mot.length()).toUpperCase();
		
		if(motCaractereValide(mot))
		{
			return motExist2(mot);
		}
		else{
			return false;
		}
		
		
	}
	
	private  boolean motExist2(String mot)
	{	
			char lettre=mot.charAt(0);
				
			if(lettre=='.')
				{
					lettre=mot.charAt(1);
					mot=mot.substring(1);
				}
				
			Noeud n=this.lettrePresentDansFils(lettre);
			
		if(n!=null)
		{
			if(mot.length()==1)
			{
				if(n.fils.getFirst().c=='0')
				return true;
				
				else{
					return false;
				}
			}
			else{
				courant=n;
				return motExist2(mot.substring(1));
			}
		}
		else{
			return false;
		}
			
		
	}
		
	
	public void initialiseArbre() throws IOException
	{
		String motDico;
		while ( (motDico = dictionnaire.readLine()) != null )
		{
			courant=this.racine;
			insertMot(motDico);
		}
	}
	
	
	public static void main(String args[]) throws IOException
	{
		long debut=System.currentTimeMillis();
		Dictionnaire dico=new Dictionnaire();
		System.out.println("Temps de chargement= "+(System.currentTimeMillis()-debut)+" ms");
	
		Scanner sc=new Scanner(System.in);
		
		while(true)
		System.out.println(dico.motExist(sc.nextLine()));
		
	}

	public boolean motExistPourCombinaison(String s) {
		
		
		return false;
	}
}
