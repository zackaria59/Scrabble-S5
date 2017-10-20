package View;

import Model.Case;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PlateauV extends StackPane {
	
	private double taille;
	private Tuile[][] plateau;
	private GridPane grid=new GridPane();
	
	public PlateauV(double t)
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
		        
		for(int i=0; i<15; i++) {
            for(int y=0; y<15; y++) {
      
            	plateau[i][y]=new Tuile((double)taille);
                GridPane.setRowIndex(plateau[i][y],i);
                GridPane.setColumnIndex(plateau[i][y],y);
                plateau[i][y].getRec().setStroke(Color.GRAY);
               
                
            	grid.getChildren().add(plateau[i][y]);
            }
        }
		initialiseTypeCase();
		this.getChildren().add(grid);
		
		
		
	}
	
	public void initialiseTypeCase()
	{
		plateau[0][0].setCouleur(Color.RED);
		plateau[0][3].setCouleur(Color.CORNFLOWERBLUE);
		plateau[0][7].setCouleur(Color.RED);
		plateau[0][11].setCouleur(Color.CORNFLOWERBLUE);
		plateau[0][14].setCouleur(Color.RED);
		
		plateau[1][1].setCouleur(Color.CORAL);
		plateau[1][5].setCouleur(Color.BLUE);
		plateau[1][9].setCouleur(Color.BLUE);
		plateau[1][13].setCouleur(Color.CORAL);
		
		plateau[2][2].setCouleur(Color.CORAL);
		plateau[2][6].setCouleur(Color.CORNFLOWERBLUE);
		plateau[2][8].setCouleur(Color.CORNFLOWERBLUE);
		plateau[2][12].setCouleur(Color.CORAL);
		
		plateau[3][0].setCouleur(Color.CORNFLOWERBLUE);
		plateau[3][7].setCouleur(Color.CORNFLOWERBLUE);
		plateau[3][3].setCouleur(Color.CORAL);
		plateau[3][11].setCouleur(Color.CORAL);
		plateau[3][14].setCouleur(Color.CORNFLOWERBLUE);
		
		plateau[4][4].setCouleur(Color.CORAL);
		plateau[4][10].setCouleur(Color.CORAL);
		
		plateau[5][1].setCouleur(Color.BLUE);
		plateau[5][5].setCouleur(Color.BLUE);
		plateau[5][9].setCouleur(Color.BLUE);
		plateau[5][13].setCouleur(Color.BLUE);
		
		plateau[6][2].setCouleur(Color.CORNFLOWERBLUE);
		plateau[6][6].setCouleur(Color.CORNFLOWERBLUE);
		plateau[6][8].setCouleur(Color.CORNFLOWERBLUE);
		plateau[6][12].setCouleur(Color.CORNFLOWERBLUE);
		
		plateau[7][0].setCouleur(Color.RED);
		plateau[7][3].setCouleur(Color.CORNFLOWERBLUE);
		
		ImageView img =new ImageView(new Image(getClass().getClassLoader().getResource("images/Etoile.png").toString(), true));
		img.fitWidthProperty().bind(plateau[7][7].widthProperty());
		img.fitHeightProperty().bind(plateau[7][7].heightProperty());
		plateau[7][7].setImage(img);
		
		plateau[7][11].setCouleur(Color.CORNFLOWERBLUE);
		plateau[7][14].setCouleur(Color.RED);
		
		plateau[8][2].setCouleur(Color.CORNFLOWERBLUE);
		plateau[8][6].setCouleur(Color.CORNFLOWERBLUE);
		plateau[8][8].setCouleur(Color.CORNFLOWERBLUE);
		plateau[8][12].setCouleur(Color.CORNFLOWERBLUE);
	
		plateau[9][1].setCouleur(Color.BLUE);
		plateau[9][5].setCouleur(Color.BLUE);
		plateau[9][9].setCouleur(Color.BLUE);
		plateau[9][13].setCouleur(Color.BLUE);
	
		plateau[10][4].setCouleur(Color.CORAL);
		plateau[10][10].setCouleur(Color.CORAL);
	
		plateau[11][0].setCouleur(Color.CORNFLOWERBLUE);
		plateau[11][7].setCouleur(Color.CORNFLOWERBLUE);
		plateau[11][3].setCouleur(Color.CORAL);
		plateau[11][11].setCouleur(Color.CORAL);
		plateau[11][14].setCouleur(Color.CORNFLOWERBLUE);
	
		plateau[12][2].setCouleur(Color.CORAL);
		plateau[12][6].setCouleur(Color.CORNFLOWERBLUE);
		plateau[12][8].setCouleur(Color.CORNFLOWERBLUE);
		plateau[12][12].setCouleur(Color.CORAL);
	
		plateau[13][1].setCouleur(Color.CORAL);
		plateau[13][5].setCouleur(Color.BLUE);
		plateau[13][9].setCouleur(Color.BLUE);
		plateau[13][13].setCouleur(Color.CORAL);
		
		plateau[14][0].setCouleur(Color.RED);
		plateau[14][3].setCouleur(Color.CORNFLOWERBLUE);
		plateau[14][7].setCouleur(Color.RED);
		plateau[14][11].setCouleur(Color.CORNFLOWERBLUE);
		plateau[14][14].setCouleur(Color.RED);
	}

	

}