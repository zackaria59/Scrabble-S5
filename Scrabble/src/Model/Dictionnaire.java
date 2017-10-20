package Model;
//a
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Dictionnaire {

	
	private BufferedReader dictionnaire;
	
	public Dictionnaire(String file) throws FileNotFoundException
	{
		dictionnaire = new BufferedReader(new FileReader(file));
	}
	
	public boolean verifieMotValide(String mot) throws IOException  // String vérifie si un "mot" est présent dans le dictionnaire 
	{
		String motDico;
		System.out.println(mot);
		mot = mot.substring(0,mot.length()).toUpperCase(); // Transforme le "mot" en majuscule car tout les mots du dico sont en majuscule 
		System.out.println(mot);
			
		try {
				
				while ( (motDico = dictionnaire.readLine()) != null )
				{
					System.out.println(motDico);
					if (mot.equals(motDico))
					{
						return true;
					}

				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return false;
	}
	
	public static void main(String[] args) throws IOException
	{
		Dictionnaire dico=new Dictionnaire("ressource/dico.txt");
		
		Scanner sc=new Scanner(System.in);
		
		System.out.println("Entrée un mot :");
		dico.verifieMotValide(sc.nextLine());
		
		
	}
}
