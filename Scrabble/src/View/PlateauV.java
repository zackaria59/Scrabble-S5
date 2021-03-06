package View;

import Model.Case;
import javafx.event.EventHandler;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PlateauV extends StackPane {
	
	private double taille;
	private Tuile[][] plateau;
	private GridPane grid=new GridPane();
	private JetonV jetonDrag;
	
	public PlateauV(double t)
	{		
		
		while((int)t%15!=0)
		{
			t++;
		}
		
		
		grid.setMaxSize(t, t);
		taille=t;
		initialisePlateau();
		this.setPickOnBounds(false);
		grid.setPickOnBounds(false);

		//this.addEventFilter(MouseDragEvent.ANY,e -> System.out.println(e));
		this.setOnMouseDragEntered(new EventHandler<MouseDragEvent>(){

			@Override
			public void handle(MouseDragEvent e) {
				
				//jetonDrag.setVisible(false);
			}
			
		});
		
		this.setOnMouseDragExited(new EventHandler<MouseDragEvent>(){

			@Override
			public void handle(MouseDragEvent e) {
				
				//jetonDrag.setVisible(true);
			}
			
		});
	}
	
	public void initialisePlateau()
	{
		plateau =new Tuile[15][15];
		        
		for(int i=0; i<15; i++) {
            for(int y=0; y<15; y++) {
      
            	
            	plateau[i][y]=new Tuile((double)taille,i,y);
               GridPane.setRowIndex(plateau[i][y].getContainer(),i);
                GridPane.setColumnIndex(plateau[i][y].getContainer(),y);
                plateau[i][y].getRec().setStroke(Color.GRAY);
               
                
            	grid.getChildren().add(plateau[i][y].getContainer());
            }
        }
		initialiseTypeCase();
		this.getChildren().add(grid);
		
		
		
	}
	
	public void addJetonDrag(JetonV jt)
	{
		jetonDrag=jt;
		for(int i=0;i<15;i++)
		{
			for(int y=0;y<15;y++)
			{
				plateau[i][y].setJetonDrag(jt);
			}
		}
	}
	
	public void initialiseTypeCase()
	{
		
		
		
		plateau[0][0].setCouleur(Color.rgb(165, 4, 4));
		plateau[0][3].setCouleur(Color.rgb(96, 215, 195));
		plateau[0][7].setCouleur(Color.rgb(165, 4, 4));
		plateau[0][11].setCouleur(Color.rgb(96, 215, 195));
		plateau[0][14].setCouleur(Color.rgb(165, 4, 4));
		
		plateau[1][1].setCouleur(Color.rgb(255, 133, 35));
		plateau[1][5].setCouleur(Color.rgb(43, 43, 116));
		plateau[1][9].setCouleur(Color.rgb(43, 43, 116));
		plateau[1][13].setCouleur(Color.rgb(255, 133, 35));
		
		plateau[2][2].setCouleur(Color.rgb(255, 133, 35));
		plateau[2][6].setCouleur(Color.rgb(96, 215, 195));
		plateau[2][8].setCouleur(Color.rgb(96, 215, 195));
		plateau[2][12].setCouleur(Color.rgb(255, 133, 35));
		
		plateau[3][0].setCouleur(Color.rgb(96, 215, 195));
		plateau[3][7].setCouleur(Color.rgb(96, 215, 195));
		plateau[3][3].setCouleur(Color.rgb(255, 133, 35));
		plateau[3][11].setCouleur(Color.rgb(255, 133, 35));
		plateau[3][14].setCouleur(Color.rgb(96, 215, 195));
		
		plateau[4][4].setCouleur(Color.rgb(255, 133, 35));
		plateau[4][10].setCouleur(Color.rgb(255, 133, 35));
		
		plateau[5][1].setCouleur(Color.rgb(43, 43, 116));
		plateau[5][5].setCouleur(Color.rgb(43, 43, 116));
		plateau[5][9].setCouleur(Color.rgb(43, 43, 116));
		plateau[5][13].setCouleur(Color.rgb(43, 43, 116));
		
		plateau[6][2].setCouleur(Color.rgb(96, 215, 195));
		plateau[6][6].setCouleur(Color.rgb(96, 215, 195));
		plateau[6][8].setCouleur(Color.rgb(96, 215, 195));
		plateau[6][12].setCouleur(Color.rgb(96, 215, 195));
		
		plateau[7][0].setCouleur(Color.rgb(165, 4, 4));
		plateau[7][3].setCouleur(Color.rgb(96, 215, 195));
		
		ImageView img =new ImageView(new Image(getClass().getClassLoader().getResource("images/etoile.png").toString(), true));
		img.fitWidthProperty().bind(plateau[7][7].getContainer().widthProperty());
		img.fitHeightProperty().bind(plateau[7][7].getContainer().heightProperty());
		plateau[7][7].setImage(img);
		
		plateau[7][11].setCouleur(Color.rgb(96, 215, 195));
		plateau[7][14].setCouleur(Color.rgb(165, 4, 4));
		
		plateau[8][2].setCouleur(Color.rgb(96, 215, 195));
		plateau[8][6].setCouleur(Color.rgb(96, 215, 195));
		plateau[8][8].setCouleur(Color.rgb(96, 215, 195));
		plateau[8][12].setCouleur(Color.rgb(96, 215, 195));
	
		plateau[9][1].setCouleur(Color.rgb(43, 43, 116));
		plateau[9][5].setCouleur(Color.rgb(43, 43, 116));
		plateau[9][9].setCouleur(Color.rgb(43, 43, 116));
		plateau[9][13].setCouleur(Color.rgb(43, 43, 116));
	
		plateau[10][4].setCouleur(Color.rgb(255, 133, 35));
		plateau[10][10].setCouleur(Color.rgb(255, 133, 35));
	
		plateau[11][0].setCouleur(Color.rgb(96, 215, 195));
		plateau[11][7].setCouleur(Color.rgb(96, 215, 195));
		plateau[11][3].setCouleur(Color.rgb(255, 133, 35));
		plateau[11][11].setCouleur(Color.rgb(255, 133, 35));
		plateau[11][14].setCouleur(Color.rgb(96, 215, 195));
	
		plateau[12][2].setCouleur(Color.rgb(255, 133, 35));
		plateau[12][6].setCouleur(Color.rgb(96, 215, 195));
		plateau[12][8].setCouleur(Color.rgb(96, 215, 195));
		plateau[12][12].setCouleur(Color.rgb(255, 133, 35));
	
		plateau[13][1].setCouleur(Color.rgb(255, 133, 35));
		plateau[13][5].setCouleur(Color.rgb(43, 43, 116));
		plateau[13][9].setCouleur(Color.rgb(43, 43, 116));
		plateau[13][13].setCouleur(Color.rgb(255, 133, 35));
		
		plateau[14][0].setCouleur(Color.rgb(165, 4, 4));
		plateau[14][3].setCouleur(Color.rgb(96, 215, 195));
		plateau[14][7].setCouleur(Color.rgb(165, 4, 4));
		plateau[14][11].setCouleur(Color.rgb(96, 215, 195));
		plateau[14][14].setCouleur(Color.rgb(165, 4, 4));
	}

	
	public Tuile[][] getPlateau() {
		return plateau;
	}

	public void setPlateau(Tuile[][] plateau) {
		this.plateau = plateau;
	}

	public Tuile getPositionJeton()
	{
		for(int i=0;i<15;i++)
		{
			for(int y=0;y<15;y++)
			{
				if(plateau[i][y].isJetonPresent())
				{
					return plateau[i][y];
					
					
				}
			}
		}
		return null;
	}

}