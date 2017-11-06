package Model;

import java.io.Serializable;

//Cette classe repr�sente une lettre 

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
	
	public void intialisePoint()   // Initialise les points du jeton en fonction du caract�re
	{
		switch(lettre)
		{
		case 'A':points=1;
		case 'B':points=3;
		case 'C':points=3;
		case 'D':points=2;
		case 'E':points=1;
		case 'F':points=4;
		case 'G':points=2;
		case 'H':points=4;
		case 'I':points=1;
		case 'J':points=8;
		case 'K':points=10;
		case 'L':points=1;
		case 'M':points=2;
		case 'N':points=1;
		case 'O':points=1;
		case 'P':points=3;
		case 'Q':points=8;
		case 'R':points=1;
		case 'S':points=1;
		case 'T':points=1;
		case 'U':points=1;
		case 'V':points=4;
		case 'W':points=10;
		case 'X':points=10;
		case 'Y':points=10;
		case 'Z':points=10;
		case '^':points=0;
		
		
		
		
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
