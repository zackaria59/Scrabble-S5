package Model;

import java.io.Serializable;
import java.util.LinkedList;

public class Noeud implements Serializable {

	LinkedList<Noeud> fils;
	char c;
	
	public Noeud()
	{
		fils=new LinkedList<Noeud>();
	}
}
