package Model;

import java.io.Serializable;

//Cette classe représente une case sur le plateau du Scrabble


public class Case implements Serializable{
	
	private Jeton j;
	private String type;
	private boolean jetonPresent;
	private int x,y;
	
	public Case(String t)
	{
		type=t;
		setJetonPresent(false);
	}

	public Jeton getJ() {
		return j;
	}

	public void setP(Jeton j) {
		this.j = j;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isJetonPresent() {
		return jetonPresent;
	}

	public void setJetonPresent(boolean jetonPresent) {
		this.jetonPresent = jetonPresent;
	}
	
	public boolean jetonValide()
	{
		if(this.jetonPresent)
		return j.isValide();
		else{return false;}
		
	}
	
	public void setJetonValide(boolean b)
	{
		j.setValide(b);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
}
