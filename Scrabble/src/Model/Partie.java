package Model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class Partie {

	private ArrayList<Joueur> joueurs;
	private Joueur joueurQuiJoue;
	private Sac sac;
	private Plateau plateau;
	private Dictionnaire dico;
	private String erreurMsg;
	private boolean joueurJoueTour;
	private boolean optionTimer;
	private int nbJoueur;
	private int nbpointsCoupJoue=0;
	
	public String getErreurMsg() {
		return erreurMsg;
	}

	public boolean isOptionTimer() {
		return optionTimer;
	}

	public void setOptionTimer(boolean optionTimer) {
		this.optionTimer = optionTimer;
	}

	public void setErreurMsg(String erreurMsg) {
		this.erreurMsg = erreurMsg;
	}

	public Partie(ArrayList<Joueur> joueurs, Sac sac, Plateau plateau,boolean activeTimer) throws IOException {
		this.joueurs = joueurs;
		this.sac = sac;
		this.setPlateau(plateau);
		setJoueurJoueTour(true);
	//	optionTimer=activeTimer;
		setNbJoueur(joueurs.size());

	}

	public Dictionnaire getDico() {
		return dico;
	}

	public void setDico(Dictionnaire dico) {
		this.dico = dico;
	}

	public Sac getSac() {
		return sac;
	}

	public void setSac(Sac sac) {
		this.sac = sac;
	}

	public ArrayList<Joueur> getJoueurs() {
		return joueurs;
	}

	public void setJoueurs(ArrayList<Joueur> joueurs) {
		this.joueurs = joueurs;
	}

	public ArrayList designePremierJoueur()
	{
		ArrayList<Object> result=new ArrayList<Object>();
		
		Jeton jt1=null,jt2=null,jt3=null,jt4=null;
		int nbjoueur=0;
		
		for (int i=0;i<joueurs.size();i++)
		{
			if(i==0){jt1=joueurs.get(i).piocherUnJeton(sac);nbjoueur++;}
			
			if(i==1){jt2=joueurs.get(i).piocherUnJeton(sac);nbjoueur++;}
			
			if(i==2){jt3=joueurs.get(i).piocherUnJeton(sac);nbjoueur++;}
			
			if(i==3){jt4=joueurs.get(i).piocherUnJeton(sac);nbjoueur++;}
		}
		
		
		
		if(nbjoueur==2)
		{
			if(jt1.getLettre()<jt2.getLettre())
			{
				result.add(joueurs.get(0));
				result.add(jt1);
				
				result.add(joueurs.get(1));
				result.add(jt2);
				return result;
			}
			else if(jt1.getLettre()>jt2.getLettre())
			{
				result.add(joueurs.get(1));
				result.add(jt2);
				
				result.add(joueurs.get(0));
				result.add(jt1);
				
				return result; 
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
	
	public void setJoueur(Joueur j)
	{
		this.joueurQuiJoue=j;
	}

	public Joueur getJoueurQuiJoue() {
		return joueurQuiJoue;
	}

	public void setJoueurQuiJoue(Joueur joueurQuiJoue) {
		this.joueurQuiJoue = joueurQuiJoue;
	}
	
	public Joueur nextJoueur()
	{
		for(int i=0;i<joueurs.size();i++)
		{
			if(joueurs.get(i).equals(joueurQuiJoue))
			{
				if(i!=joueurs.size()-1)
				{
					return joueurs.get(i+1);
				}
				else{
					return joueurs.get(0);
				}
			}
		}
		
		return null;
	}
	
	
	public String transformEnMot(LinkedList<Jeton> listChar)
	{
		
		String s="";
		for( Jeton jt:listChar)
		{
			s+=((char)jt.getLettre());
		}
		
		return s;
	}
	
	public String transformEnMot2(LinkedList<Jeton> listChar)
	{
		
		String s="";
		
		while(!listChar.isEmpty())
		{			s+=((char)listChar.getLast().getLettre());
					listChar.removeLast();
		}
		
		
		return s;
	}
	
	public boolean verifMotValide(LinkedList<Jeton> listChar) throws IOException
	{
		LinkedList<Jeton> mot=listChar;
		
		if(listChar==null)
		{
			return  false;
		}
		
		String mot1=transformEnMot(listChar);
		System.out.println("mot1 = "+mot1+" taille="+mot1.length());
		
		if(dico.motExist(mot1))
		{
			this.nbpointsCoupJoue+=this.CalculPoint(mot);
			System.out.println("mot Impec");
			return true;
		}
		else{
			this.erreurMsg="Le mot "+mot1+" n'existe pas ";
			return false;}
		
	}
	
	public int getNbpointsCoupJoue() {
		return nbpointsCoupJoue;
	}

	public void setNbpointsCoupJoue(int nbpointsCoupJoue) {
		this.nbpointsCoupJoue = nbpointsCoupJoue;
	}

	public boolean verifMotVoisinValide() throws IOException
	{

		LinkedList<Object> mots =this.getPlateau().chercheToutLesMotsVoisin();
		LinkedList<Jeton> mot=new LinkedList<Jeton>();
		for(int i=0;i<mots.size();i++)
		{
			mot=((LinkedList<Jeton>)mots.get(i));
			System.out.print(this.getMotByList(mot));
			
			if(!verifMotValide(mot))
			{
				erreurMsg="Le mot suivant n'est pas valide : "+this.getMotByList(mot);
				System.out.println(erreurMsg);
				return false;
			}
		}
		
		return true;
	}

public int CalculPoint(LinkedList<Jeton> jet) throws FileNotFoundException {
		
		int nbpl =0;
		int cptMT=0; int cptMD=0;
		
		String type;
		System.out.println("Dans la fonction calcul"+jet.size());
		for( int i=0; i<jet.size();i++){
			
			type=this.getPlateau().getPlateau()[jet.get(i).getX()][jet.get(i).getY()].getType();
			
		//	System.out.print(jet.get(i).getX()+"  "+jet.get(i).getY()+"  "+type+"  -   ");
			System.out.println("Jeton est valide = "+jet.get(i).isValide()+ " et type = "+type+" et points = "+jet.get(i).getPoint());
			
			 if(type==("LT")  && !jet.get(i).isValide())
				{nbpl+=(jet.get(i).getPoint()*3);
				System.out.println("A");}
			 
			 else if(type==("LD")&& !jet.get(i).isValide())
				{nbpl+=(jet.get(i).getPoint()*2);System.out.println("B");}
			 
			 else if(type.equals("MT") &&  !jet.get(i).isValide()) 
			 {cptMT++;nbpl+=(jet.get(i).getPoint());}	
			
			 else if(type.equals("MD")  && !jet.get(i).isValide())
				{cptMD++;nbpl+=(jet.get(i).getPoint());}
			 
			 else if(type==("X") || jet.get(i).isValide())
				{nbpl+=(jet.get(i).getPoint());System.out.println("C + "+jet.get(i).getPoint());}
			
			
			 
			 System.out.println("nbpl = "+nbpl);
			
		}
		
		
		
		for(int j=0;j<cptMT;j++) {
			 nbpl= nbpl*3;
		}
		for(int j=0;j<cptMD;j++) {
			 nbpl= nbpl*2;
		} 
		
		// dans le cas où le mot est composé de 7 lettre (Joueur a fait un Scrabble)
		if(getPlateau().getJetonNoValide().size()==7)
			nbpl=nbpl+50;
		
		return nbpl;
		
	}

	public String inverse(String s){ 
	        	        
	        int lg=s.length(); 
	        char []tab=new char[lg] ; 
	        int i ; 
	        for(i=0;i<lg;i++){ 
	            if (s.indexOf(s.charAt(lg-1-i))==-1) 
	                i++ ; 
	            
	            else if (s.indexOf(s.charAt(lg-1-i))!=-1) 
	            tab[i]=s.charAt(lg-1-i); 
	            
	            
	        } 
	        s=new String(tab); 
	        return s; 
	    }
	 
	public Plateau getPlateau() {
		return plateau;
	}

	public void setPlateau(Plateau plateau) {
		this.plateau = plateau;
	}

	public boolean jouePremierTour() throws IOException {
		
		if(verifMotValide(getPlateau().jouePremierTour()))
		{
			int nbJetonPose=this.getPlateau().getJetonNoValide().size();
			this.getJoueurQuiJoue().piocherNbFois(nbJetonPose,sac);
			return true;
		}
		else{
			return false;
		}
		
	
	}
	
	public boolean joueUnTour() throws IOException
	{
		if(verifMotValide(this.getPlateau().getMotPose()) && verifMotVoisinValide())
			{
				
			int nbJetonPose=this.getPlateau().getJetonNoValide().size();
			this.getJoueurQuiJoue().piocherNbFois(nbJetonPose,sac);
			
			System.out.print(" ============================================= >>>> MOT OK <<<<< =======================================");
				return true;
			}
		else{
			
			System.out.print(" ============================================= >>>> MOT INVALIDE <<<<< =======================================");
				return false;
		}
	}
	
	public String getMotByList(LinkedList<Jeton> motList)
	{
		String mot="";
		for(int i=0;i<motList.size();i++)
		{
			mot+=motList.get(i).getLettre();
		}
		
		return mot;
	}
	
	public void validJetonPose()
	{
		this.getPlateau().valideJeton();
	}
	
	public void videList()
	{
		this.plateau.videList();
	}


	public int getNbJoueur() {
		return nbJoueur;
	}

	public void setNbJoueur(int nbJoueur) {
		this.nbJoueur = nbJoueur;
	}
}
