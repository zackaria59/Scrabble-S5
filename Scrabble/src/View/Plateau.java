package View;

import Model.Case;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Plateau extends StackPane {
	
	private double taille;
	private Tuile[][] plateau;
	private GridPane grid=new GridPane();
	
	public Plateau(double t)
	{		
		
		while((int)t%15!=0)
		{
			t++;
		}
		
		grid.setMaxSize(t, t);
		taille=t;
		initialisePlateau();
	}
	
	public void initialisePlateau()
	{
		plateau =new Tuile[15][15];
		        
		for(int l=0; l<15; l++) {
            for(int col=0; col<15; col++) {
      
                plateau[l][col]=new Tuile((double)taille);
                
                GridPane.setRowIndex(plateau[l][col],l);
                GridPane.setColumnIndex(plateau[l][col],col);
                grid.getChildren().addAll(plateau[l][col]);
                 
            }
        }
		initialiseTypeCase();
		this.getChildren().add(grid);
		
	}
	
	public void initialiseTypeCase()
	{
		
		for(int i=0;i<15;i++)
		{
			
			for(int y=0;y<15;y++)
			{
				if(i==0)
				{
					switch (y){
					case 0: plateau[i][y].setCouleur(Color.RED);
					case 3: plateau[i][y].setCouleur(Color.CYAN);
					case 7: plateau[i][y].setCouleur(Color.RED);
					case 11: plateau[i][y].setCouleur(Color.CYAN);
					case 14: plateau[i][y].setCouleur(Color.RED);
					default:plateau[i][y].setCouleur(Color.BLACK);
					
					}
					
				}
				else if(i==1)
				{
					switch (y){
					case 1: plateau[i][y].setCouleur(Color.PINK);
					case 5: plateau[i][y].setCouleur(Color.BLUE);
					case 9: plateau[i][y].setCouleur(Color.BLUE);
					case 13: plateau[i][y].setCouleur(Color.PINK);
					default:plateau[i][y].setCouleur(Color.BLACK);
					
					}
				}
				else if(i==2)
				{
					switch (y){
					case 2: plateau[i][y].setCouleur(Color.PINK);
					case 6: plateau[i][y].setCouleur(Color.CYAN);
					case 8: plateau[i][y].setCouleur(Color.CYAN);
					case 12: plateau[i][y].setCouleur(Color.PINK);
					default:plateau[i][y].setCouleur(Color.BLACK);
					
					}
				}
				/*else if(i==3)
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
				}*/
	
	}
}
	}

}