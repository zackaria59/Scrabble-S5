package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

public class Plateau implements Serializable {

	private Case[][] plateau;
	private LinkedList<Jeton> jetonNoValide ;
	private LinkedList<Jeton> listTamp;
	
	public Plateau()
	{
		construirePlateau();
		jetonNoValide=new LinkedList<Jeton>();
		listTamp=new LinkedList<Jeton>();
	}
	
	public void construirePlateau(){
		
		setPlateau(new Case[15][15]);
		
		for(int i=0;i<15;i++)
		{
			for(int y=0;y<15;y++)
			{
				
				if(i==0)
				{
					switch (y){
					case 0: plateau[i][y]=new Case("MT");break;
					case 3: plateau[i][y]=new Case("LD");break;
					case 7: plateau[i][y]=new Case("MT");break;
					case 11: plateau[i][y]=new Case("LD");break;
					case 14: plateau[i][y]=new Case("MT");break;
					default:plateau[i][y]=new Case("");break;
					
					}
					
				}
				else if(i==1)
				{
					switch (y){
					case 1: plateau[i][y]=new Case("MD");break;
					case 5: plateau[i][y]=new Case("LT");break;
					case 9: plateau[i][y]=new Case("LT");break;
					case 13: plateau[i][y]=new Case("MD");break;
					default:plateau[i][y]=new Case("");break;
					
					}
				}
				else if(i==2)
				{
					switch (y){
					case 2: plateau[i][y]=new Case("MD");break;
					case 6: plateau[i][y]=new Case("LD");break;
					case 8: plateau[i][y]=new Case("LD");break;
					case 12: plateau[i][y]=new Case("MD");break;
					default:plateau[i][y]=new Case("");break;
					
					}
				}
				else if(i==3)
				{
					switch (y){
					case 0: plateau[i][y]=new Case("LD");break;
					case 3: plateau[i][y]=new Case("MD");break;
					case 7: plateau[i][y]=new Case("LD");break;
					case 11: plateau[i][y]=new Case("MD");break;
					case 14: plateau[i][y]=new Case("LD");break;
					default:plateau[i][y]=new Case("");break;
					
					}
				}
				else if(i==4)
				{
					switch (y){
					case 4: plateau[i][y]=new Case("MD");break;
					case 10: plateau[i][y]=new Case("MD");break;
					default:plateau[i][y]=new Case("");break;

					}
				}
				else if(i==5)
				{
					switch (y){
					case 1: plateau[i][y]=new Case("LT");break;
					case 5: plateau[i][y]=new Case("LT");break;
					case 9: plateau[i][y]=new Case("LT");break;
					case 13: plateau[i][y]=new Case("LT");break;
					default:plateau[i][y]=new Case("");break;

					}
				}
				else if(i==6)
				{
					switch (y){
					case 2: plateau[i][y]=new Case("LD");break;
					case 6: plateau[i][y]=new Case("LD");break;
					case 8: plateau[i][y]=new Case("LD");break;
					case 12: plateau[i][y]=new Case("LD");break;
					default:plateau[i][y]=new Case("");break;

					}
				}
				else if(i==7)
				{
					switch (y){
					case 0: plateau[i][y]=new Case("MT");break;
					case 3: plateau[i][y]=new Case("LD");break;
					case 7: plateau[i][y]=new Case("ETOILE");break;
					case 11: plateau[i][y]=new Case("LD");break;
					case 14: plateau[i][y]=new Case("MT");break;
					default:plateau[i][y]=new Case("");break;

					}
				}
				else if(i==8)
				{
					switch (y){
					case 2: plateau[i][y]=new Case("LD");break;
					case 6: plateau[i][y]=new Case("LD");break;
					case 8: plateau[i][y]=new Case("LD");break;
					case 12: plateau[i][y]=new Case("LD");break;
					default:plateau[i][y]=new Case("");break;

					}
				}
				else if(i==9)
				{
					switch (y){
					case 1: plateau[i][y]=new Case("LT");break;
					case 5: plateau[i][y]=new Case("LT");break;
					case 9: plateau[i][y]=new Case("LT");break;
					case 13: plateau[i][y]=new Case("LT");break;
					default:plateau[i][y]=new Case("");break;

					}
				}
				else if(i==10)
				{
					switch (y){
					case 4: plateau[i][y]=new Case("MD");break;
					case 10: plateau[i][y]=new Case("MD");break;
					default:plateau[i][y]=new Case("");break;

					}
				}
				else if(i==11)
				{
					switch (y){
					case 0: plateau[i][y]=new Case("LD");break;
					case 3: plateau[i][y]=new Case("MD");break;
					case 7: plateau[i][y]=new Case("LD");break;
					case 11: plateau[i][y]=new Case("MD");break;
					case 14: plateau[i][y]=new Case("LD");break;
					default:plateau[i][y]=new Case("");break;
					
					}
				}
				else if(i==12)
				{
					switch (y){
					case 2: plateau[i][y]=new Case("MD");break;
					case 6: plateau[i][y]=new Case("LD");break;
					case 8: plateau[i][y]=new Case("LD");break;
					case 12: plateau[i][y]=new Case("MD");break;
					default:plateau[i][y]=new Case("");break;
					
					}
				}
				else if(i==13)
				{
					switch (y){
					case 1: plateau[i][y]=new Case("MD");break;
					case 5: plateau[i][y]=new Case("LT");break;
					case 9: plateau[i][y]=new Case("LT");break;
					case 13: plateau[i][y]=new Case("MD");break;
					default:plateau[i][y]=new Case("");break;
					
					}
				}
				else if(i==14)
				{
					switch (y){
					case 0: plateau[i][y]=new Case("MT");break;
					case 3: plateau[i][y]=new Case("LD");break;
					case 7: plateau[i][y]=new Case("MT");break;
					case 11: plateau[i][y]=new Case("LD");break;
					case 14: plateau[i][y]=new Case("MT");break;
					default:plateau[i][y]=new Case("");break;
					
					}
				}
			
			}
		}
	}

	public Case[][] getPlateau() {
		return plateau;
	}

	public void setPlateau(Case[][] plateau) {
		this.plateau = plateau;
	}
	
	public void setJeton(int x,int y,char c)
	{
		Jeton jt=new Jeton(c);
		plateau[x][y].setP(jt);
		plateau[x][y].setJetonPresent(true);
		jt.setX(x);
		jt.setY(y);
		
		this.jetonNoValide.add(jt);
	}
	
	public void reprendre()
	{
		this.jetonNoValide.clear();
		
		for(int i=0;i<15;i++)
		{
			for(int y=0;y<15;y++)
			{
				if(plateau[i][y].isJetonPresent() && !plateau[i][y].jetonValide())
				{
					plateau[i][y].setP(null);
					plateau[i][y].setJetonPresent(false);
				}
			}
			
		}
	}
	
	public void valideJeton()
	{
		this.afficheJetonNoValide();
		
		for (Jeton jt: this.jetonNoValide)
		{
			jt.setValide(true);
		}
		
		this.affichePlateauJetonValide();
	}
	
	public boolean motMemeLigne()
	{
		int tampX=this.jetonNoValide.get(0).getX();
		for(int i=1;i<this.jetonNoValide.size();i++)
		{
			if((this.jetonNoValide.get(i).getX()!=tampX))
			{
				return false;
			}
		}
		
		return true;
	}
	
	public boolean motMemeColonne()
	{
		int tampY=this.jetonNoValide.get(0).getY();
		for(int i=1;i<this.jetonNoValide.size();i++)
		{
			if((this.jetonNoValide.get(i).getY()!=tampY))
			{
				return false;
			}
		}
		
		return true;
	}
	
	public void triParY()
	{
		Jeton jtamp;
		
		for(int b=0;b<this.jetonNoValide.size();b++)
		{
		
			for(int i=0;i<this.jetonNoValide.size();i++)
			{
				if(i<this.jetonNoValide.size()-1)
				{
					if(jetonNoValide.get(i).getY() > jetonNoValide.get(i+1).getY())
					{
						jtamp=jetonNoValide.get(i);
						jetonNoValide.set(i,jetonNoValide.get(i+1));
						jetonNoValide.set(i+1,jtamp);
					}
				}
			}
		
		}
		System.out.println("\n");
	}
	
	public void triParX()
	{
		Jeton jtamp;
		
		for(int b=0;b<this.jetonNoValide.size();b++)
		{
		
			for(int i=0;i<this.jetonNoValide.size();i++)
			{
				if(i<this.jetonNoValide.size()-1)
				{
					if(jetonNoValide.get(i).getX() > jetonNoValide.get(i+1).getX())
					{
						jtamp=jetonNoValide.get(i);
						jetonNoValide.set(i,jetonNoValide.get(i+1));
						jetonNoValide.set(i+1,jtamp);
					}
				}
			}
		
		}
		System.out.println("\n");
	}
	
	public boolean verifMotContinuLigne()
	{
		triParY();
		int tampY=jetonNoValide.get(0).getY()+1;
		listTamp.addLast(jetonNoValide.get(0));
		
		this.afficheJetonNoValide();
		for(int i=1;i<this.jetonNoValide.size();i++)
		{
			if(jetonNoValide.get(i).getY()==tampY)
			{
				listTamp.addLast(jetonNoValide.get(i));
				tampY++;
			}
			else{
				return false;
			}
		}
		
		return true;
	}
	
	public boolean verifMotContinuColonne()
	{
		triParX();
		int tampX=jetonNoValide.get(0).getX()+1;
		listTamp.addLast(jetonNoValide.get(0));
		
		for(int i=1;i<this.jetonNoValide.size();i++)
		{
			if(jetonNoValide.get(i).getX()==tampX)
			{
				listTamp.addLast(jetonNoValide.get(i));
				tampX++;
			}
			else{
				return false;
			}
		}
		
		return true;
	}
	
	public boolean verifieMotEnLigne(){
	
		triParY();
		int tampY=jetonNoValide.get(0).getY()+1;
		
		this.afficheJetonNoValide();
		for(int i=1;i<this.jetonNoValide.size();i++)
		{
			if(this.jetonNoValide.get(i).getY()!=tampY)
			{
				return false;
			}
			
			tampY++;
		}
		
		return true;
	}
	
	public boolean verifieMotEnColonne(){
		
		triParX();
		int tampX=jetonNoValide.get(0).getX()+1;
		
		for(int i=1;i<this.jetonNoValide.size();i++)
		{
			if(this.jetonNoValide.get(i).getX()!=tampX)
			{
				return false;
			}
			
			tampX++;
		}
		
		return true;
	}
	
	public boolean motContinuEnLigne(int ligne)
	{
		triParY();
		int tampY = 0;
		
		int extremMinY=jetonNoValide.get(0).getY();
		int extremMaxY=jetonNoValide.get(jetonNoValide.size()-1).getY();
		
		boolean passeParJetonPose=false;
		
		for(int i=0;i<this.jetonNoValide.size();i++)
		{
			if(i==0)
			{tampY=jetonNoValide.get(i).getY()+1;
			 listTamp.addFirst(jetonNoValide.get(i));
			}
			
			if(i<this.jetonNoValide.size()-1)
			{
				if(tampY!=jetonNoValide.get(i+1).getY())
				{
					
					if(!plateau[ligne][tampY].jetonValide())
					{
						return false;
					}
					else{
						listTamp.addFirst(plateau[ligne][tampY].getJ());
						passeParJetonPose=true;
						tampY++;
						i--;
					}
				}
				else{
					listTamp.addLast(jetonNoValide.get(i+1));
					tampY++;
				}
			}
		}
		
			if(!passeParJetonPose && extremMinY!=0)
			{
				passeParJetonPose=plateau[extremMinY-1][ligne].jetonValide();
			}
			
			System.out.println("test = "+plateau[ligne][extremMinY-1].jetonValide());
			
			while(plateau[ligne][extremMinY-1].jetonValide() && extremMinY>0)
			{
				listTamp.addFirst(plateau[ligne][extremMinY-1].getJ());
				extremMinY--;
			}
			
			if(!passeParJetonPose && extremMaxY!=14)
			{
				passeParJetonPose=plateau[extremMaxY+1][ligne].jetonValide();
			}
			
			while(plateau[ligne][extremMaxY+1].jetonValide() && extremMinY<14)
			{
				listTamp.addLast(plateau[ligne][extremMaxY+1].getJ());
				extremMaxY++;
			}
		
		
		return passeParJetonPose;
	}
	
	public boolean motContinuEnColonne(int col)
	{
		ArrayList<Jeton> jts=new ArrayList<Jeton>();
		
		triParX();
		int tampX = 0;
		int extremMinX=jetonNoValide.get(0).getX();
		int extremMaxX=jetonNoValide.get(jetonNoValide.size()-1).getX();
		
		boolean passeParJetonPose=false;
		
		tampX=jetonNoValide.get(0).getX()+1;
		listTamp.addFirst(jetonNoValide.get(0));
		
		for(int i=0;i<this.jetonNoValide.size();i++)
		{
			
			if(i<this.jetonNoValide.size()-1)
			{
				if(tampX!=jetonNoValide.get(i+1).getX())
				{
					
					if(!plateau[tampX][col].jetonValide())
					{
						return false;
					}
					else{
						listTamp.addLast(plateau[tampX][col].getJ());
						passeParJetonPose=true;
						tampX++;
						i--;
					}
				}
				else{
					listTamp.addLast(jetonNoValide.get(i+1));
					tampX++;
				}
			}
		}
		
		if(!passeParJetonPose && extremMinX!=0)
		{
			passeParJetonPose=plateau[extremMinX-1][col].jetonValide();
		}
		
		while(plateau[extremMinX-1][col].jetonValide() && extremMinX>0)
		{
			listTamp.addFirst(plateau[extremMinX-1][col].getJ());
			extremMinX--;
		}
		
		if(!passeParJetonPose && extremMaxX!=14)
		{
			passeParJetonPose=plateau[extremMaxX+1][col].jetonValide();
		}
		
		this.affichePlateauJetonValide();
		
		while(plateau[extremMaxX+1][col].jetonValide() && extremMaxX<14)
		{
			listTamp.addFirst(plateau[extremMaxX+1][col].getJ());
			extremMaxX++;
		}
		
		
		return true;
	}
	
	
	
	public LinkedList<Object> trouveMotPoseLigne(int ligne)
	{
		
		LinkedList<Object> mot=new LinkedList<Object>();
		
		for(int i=0;i<listTamp.size();i++)
		{
			mot.addFirst(listTamp.get(i).getLettre());
		}
		
		return mot;
	}
	
	public LinkedList<Object> trouveMotPoseColonne(int col)
	{
		
		LinkedList<Object> mot=new LinkedList<Object>();
		
		for(int i=0;i<listTamp.size();i++)
		{
			mot.addFirst(listTamp.get(i).getLettre());
		}
		
		return mot;
	}
	
	
	public LinkedList<Jeton> jouePremierTour()
	{
		
		if(plateau[7][7].isJetonPresent())
		{
			System.out.println("passe par le centre");
			if(verifieMotEnLigne())
			{
				
				System.out.println("Mot en ligne");
				if(this.verifMotContinuLigne())
				{
					System.out.println("Mot en continu");
					return listTamp;
				}
			}
			else if(verifieMotEnColonne())
			{
				
				if(this.verifMotContinuColonne())
				{
					return listTamp;
				}
			}
			else{
				
				System.out.print("Mot invalide");
			
			}
		
		}
		else{
			
			System.out.println("Il faut passer par le centre");
		}
		
		return  null;
	}
	
	
	public LinkedList<Jeton> getMotPose()
	{
		if(this.motMemeLigne())
		{
			System.out.println("Mot en ligne");
			if(this.motContinuEnLigne(this.jetonNoValide.get(0).getX()))
			{
				System.out.println("Mot continu en Ligne -->");
				this.affichePlateauJetonValide();
				this.afficheListTamp();
				return listTamp;
			}
		}
		else if(this.motMemeColonne())
		{
			System.out.println("Mot en colonne");
			if(this.motContinuEnColonne(this.jetonNoValide.get(0).getY()))
			{
				System.out.println("Mot continu en colonne -->");
				this.affichePlateauJetonValide();
				this.afficheListTamp();
				return listTamp;
			}
		}
		else{
			
			this.afficheJetonNoValide();
			this.afficheListTamp();
			
			System.out.print("Mot invalide");
		
		}
		
		if(this.jetonNoValide.size()==1)
		{
			this.listTamp.clear();
			
			if(this.motMemeColonne())
			{
				
				if(this.motContinuEnColonne(this.jetonNoValide.get(0).getY()))
				{
					this.affichePlateauJetonValide();
					this.afficheListTamp();
					return listTamp;
				}
			}
			else{
				
				this.afficheJetonNoValide();
				
				
				System.out.print("Mot invalide");
			
			}
		}
		
		return null;
		
	}
	
	public void afficheJetonNoValide()
	{
		System.out.print("Jeton non valide = ");
		for(Jeton jt: this.jetonNoValide)
		{
			System.out.print(jt.getLettre()+" | ");
		}
		
		System.out.println("");
	}
	
	public void afficheListTamp()
	{
		System.out.print("Liste tampon = ");
		for(Jeton jt: this.listTamp)
		{
			System.out.print(jt.getLettre()+" | ");
		}
		System.out.println("");
	}
	
	public void affichePlateauJetonValide()
	{
		
		for(int i=0;i<15;i++)
		{
			for(int y=0;y<15;y++)
			{
				if(plateau[i][y].jetonValide())
				{
					System.out.print("[ "+plateau[i][y].getJ().getLettre()+" ] ");
				}
				else{
					System.out.print("[ X ]");
				}
				
				if(y==14)
				{
					System.out.println("");
				}
				
				
			}
		}
	}

	public void videList() {
		
		this.listTamp.clear();
		this.jetonNoValide.clear();
		
	}
}
