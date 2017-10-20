package Model;

import java.io.Serializable;

//Cette classe représente une lettre 

public class Jeton implements Serializable {
	
	private int id;
	private char lettre;
	private int points;  // Le nombre de point que rapporte la lettre
	
	public Jeton(char l)
	{
		lettre=l;
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
	
}
