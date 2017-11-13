package Model;

import java.io.Serializable;

//Cette classe représente une lettre 

public class Jeton implements Serializable {
	
	private int id,x,y;
	private char lettre;
	private int points;  // Le nombre de point que rapporte la lettre
	private boolean valide;
	
	
	public Jeton(char l)
	{
		lettre=l;
		valide=false;
		intialisePoint();
	}

	public char getLettre() {
		return lettre;
	}

	public void setLettre(char lettre) {
		this.lettre = lettre;
	}

	public int getPoint() {
		return points;
	}
	
	public void intialisePoint()   // Initialise les points du jeton en fonction du caractère
	{
		switch(lettre)
		{
		case 'A':points=1;break;
		case 'B':points=3;break;
		case 'C':points=3;break;
		case 'D':points=2;break;
		case 'E':points=1;break;
		case 'F':points=4;break;
		case 'G':points=2;break;
		case 'H':points=4;break;
		case 'I':points=1;break;
		case 'J':points=8;break;
		case 'K':points=10;break;
		case 'L':points=1;break;
		case 'M':points=2;break;
		case 'N':points=1;break;
		case 'O':points=1;break;
		case 'P':points=3;break;
		case 'Q':points=8;break;
		case 'R':points=1;break;
		case 'S':points=1;break;
		case 'T':points=1;break;
		case 'U':points=1;break;
		case 'V':points=4;break;
		case 'W':points=10;break;
		case 'X':points=10;break;
		case 'Y':points=10;break;
		case 'Z':points=10;break;
		case '^':points=0;break;
		
		
		
		
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isValide() {
		return valide;
	}

	public void setValide(boolean valide) {
		this.valide = valide;
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
