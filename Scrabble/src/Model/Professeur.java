package Model;

import java.util.ArrayList;

public class Professeur {

	private ArrayList<Object> toutLesMots;
	private ArrayList<Jeton> jetonEnMain;
	private Plateau plateau;
	
	
	public Professeur(Plateau plateau,ArrayList<Jeton> jetons)
	{
		this.jetonEnMain=jetons;
		this.plateau=plateau;
	}
	
	public void chercheToutLesMotsPose()
	{
		ArrayList<Jeton> mot=new ArrayList();
		
		for(int x=0;x<15;x++)
		{
			for(int y=0;y<15;y++)
			{
				if(x==0){
						
				}
			}
		}
	}
}
