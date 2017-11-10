package Model;

import java.io.Serializable;
import java.util.ArrayList;

// Une pioche contitent toute les lettres au début d'une partie


public class Sac implements Serializable {
	
	private ArrayList<Jeton>  jetons;
	private int nbJetons;
	
	public Sac()
	{
		initialiseSac();
	}
	
	public void initialiseSac()
	{
		jetons=new ArrayList<Jeton>(102);    // Il y a 102 jetons dans le jeu donc 102 jetons dans le sac maximum
		
		ajouteJetonsAuSac('A',9); // On ajoute 9 jetons A au sac etc
		ajouteJetonsAuSac('B',2);
		ajouteJetonsAuSac('C',2);
		ajouteJetonsAuSac('D',3);
		ajouteJetonsAuSac('E',15);
		ajouteJetonsAuSac('F',2);
		ajouteJetonsAuSac('G',2);
		ajouteJetonsAuSac('H',2);
		ajouteJetonsAuSac('I',8);
		ajouteJetonsAuSac('J',1);
		ajouteJetonsAuSac('K',1);
		ajouteJetonsAuSac('L',5);
		ajouteJetonsAuSac('M',3);
		ajouteJetonsAuSac('N',6);
		ajouteJetonsAuSac('O',6);
		ajouteJetonsAuSac('P',2);
		ajouteJetonsAuSac('Q',1);
		ajouteJetonsAuSac('R',6);
		ajouteJetonsAuSac('S',6);
		ajouteJetonsAuSac('T',6);
		ajouteJetonsAuSac('U',6);
		ajouteJetonsAuSac('V',2);
		ajouteJetonsAuSac('W',1);
		ajouteJetonsAuSac('X',1);
		ajouteJetonsAuSac('Y',1);
		ajouteJetonsAuSac('Z',1);
		ajouteJetonsAuSac('^',2);
		
	}
	
	public void ajouteJetonsAuSac(char a,int nbJeton)
	{
		for(int i=0;i<nbJeton;i++)
		{
			jetons.add(new Jeton(a));
		}
	}

	public void ajouteUnJeton(Jeton jt)
	{
		this.jetons.add(jt);
	}
	
	public ArrayList<Jeton> getJetons() {
		return jetons;
	}

	public void setJetons(ArrayList<Jeton> jetons) {
		this.jetons = jetons;
	}

	public int getNbJetons() {
		return nbJetons;
	}

	public void setNbJetons(int nbJetons) {
		this.nbJetons = nbJetons;
	}
	

}
