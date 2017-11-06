package View;

import java.util.ArrayList;

import Model.Jeton;
import Model.Joueur;
import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class PiocheJeton extends StackPane {
	
	private ArrayList<JetonV> listImgJeton;
	private ArrayList<StackPane> imgContainer;
	private AnimationTimer timer;
	private int cpt=0;
	private Label nom1,nom2,nom3,nom4;
	private VBox vb1,vb2,vb3,vb4;

	public PiocheJeton(double h,double l)
	{
		this.setMaxWidth(l);
		this.setMaxHeight(h);
		this.setWidth(l);
		this.setHeight(h);
		
		this.setAlignment(Pos.CENTER);
		this.setStyle("-fx-background-color: #FFFFFF;");
		
		setImgJeton(new ImageView());
		imgContainer=new ArrayList<StackPane>();
		imgContainer.add(new StackPane());
		imgContainer.add(new StackPane());
		imgContainer.add(new StackPane());
		imgContainer.add(new StackPane());
		
		listImgJeton=new ArrayList<JetonV>();
		listImgJeton.add(new JetonV('A'));
		listImgJeton.add(new JetonV('B'));
		listImgJeton.add(new JetonV('C'));
		listImgJeton.add(new JetonV('D'));
		listImgJeton.add(new JetonV('E'));
		listImgJeton.add(new JetonV('F'));
		listImgJeton.add(new JetonV('G'));
		listImgJeton.add(new JetonV('H'));
		listImgJeton.add(new JetonV('I'));
		listImgJeton.add(new JetonV('J'));
		listImgJeton.add(new JetonV('K'));
		listImgJeton.add(new JetonV('L'));
		listImgJeton.add(new JetonV('M'));
		listImgJeton.add(new JetonV('N'));
		listImgJeton.add(new JetonV('O'));
		listImgJeton.add(new JetonV('P'));
		listImgJeton.add(new JetonV('Q'));
		listImgJeton.add(new JetonV('R'));
		listImgJeton.add(new JetonV('S'));
		listImgJeton.add(new JetonV('T'));
		listImgJeton.add(new JetonV('U'));
		listImgJeton.add(new JetonV('V'));
		listImgJeton.add(new JetonV('W'));
		listImgJeton.add(new JetonV('X'));
		listImgJeton.add(new JetonV('Y'));
		listImgJeton.add(new JetonV('Z'));	
		
		vb1=new VBox();
		vb2=new VBox();
		vb3=new VBox();
		vb4=new VBox();
		
		nom1=new Label();
		nom2=new Label();
		nom3=new Label();
		nom4=new Label();
		
		//this.getChildren().add(imgContainer);
		
		this.aggrandirJeton();
	}
	
	public void aggrandirJeton()
	{
		for(JetonV jt: listImgJeton)
		{
			jt.aggrandir();
		}
		
	}
	
	public void affichePioche(ArrayList ls)
	{
		
		if(ls.size()==4)
		{
			vb1.getChildren().add(nom1);
			vb1.getChildren().add(imgContainer.get(0));
			
			vb2.getChildren().add(nom2);
			vb2.getChildren().add(imgContainer.get(1));
			
			nom1.setText(((Joueur) ls.get(0)).getPseudo());
			nom2.setText(((Joueur) ls.get(2)).getPseudo());
			
			this.getChildren().add(vb1);
			this.getChildren().add(vb2);
			
			animation(   ((Jeton) ls.get(1)).getLettre(),imgContainer.get(0));
			animation(   ((Jeton) ls.get(3)).getLettre(),imgContainer.get(1));
		}
	}
	
	public void animation(char c,StackPane sp)
	{

		timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
             
            	sp.getChildren().add(listImgJeton.get(cpt));
            	
            			if(listImgJeton.get(cpt).getLettre()==c)
            			{
            				this.stop();
            			}
            			else{
	            			sp.getChildren().clear();
			    			cpt++;
            			}
            			
			    		try {
							Thread.sleep(250);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			             	
            }
 
        };
       timer.start();
        
	}

	public ArrayList<JetonV> getImgJeton() {
		return listImgJeton;
	}

	public void setImgJeton(ImageView imgJeton) {
		this.listImgJeton = listImgJeton;
	}
}
