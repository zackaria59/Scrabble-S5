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
	private String file;
	
	public Dictionnaire(String file) throws FileNotFoundException
	{
		this.file=file;
		dictionnaire = new BufferedReader(new FileReader(file));
	}
	
	public boolean verifieMotValide(String mot) throws IOException  // String v�rifie si un "mot" est pr�sent dans le dictionnaire 
	{
		String motDico;
		
		mot = mot.substring(0,mot.length()).toUpperCase(); // Transforme le "mot" en majuscule car tout les mots du dico sont en majuscule 
		System.out.print("Mot dans la fonction dico : |"+mot+"|\n");
		try {
				
				while ( (motDico = dictionnaire.readLine()) != null )
				{
					//System.out.println(mot +"="+ motDico+" ??" );
					
					if (mot.equals(motDico))
					{	
						System.out.println("MOT TROUVE DANS LE DICO !!!!!!!!!!!!");
						dictionnaire = new BufferedReader(new FileReader(file));
						return true;
					}

				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		dictionnaire = new BufferedReader(new FileReader(file));
			return false;
	}
	
	public static void main(String[] args) throws IOException
	{
		Dictionnaire dico=new Dictionnaire("ressource/dico.txt");
		
		Scanner sc=new Scanner(System.in);
		
		System.out.println("Entr�e un mot :");
		dico.verifieMotValide(sc.nextLine());
		
		
	}
}
