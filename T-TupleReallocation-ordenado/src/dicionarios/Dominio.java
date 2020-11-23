package dicionarios;

import java.util.LinkedList;

import bean.Objeto;

public class Dominio {
	
	private LinkedList<Objeto> dominio;
	
	private int strenght; 
 	
	private Dominio(){
		dominio = new LinkedList<Objeto>();
		strenght = 1;
	}
	
	public LinkedList<Objeto> getDominio() { 
		return dominio;
	}

	public void addFator(Objeto o) {
		this.dominio.add(o);
	}

	public void setStrenght(int s){
		this.strenght = s;
	}

	public int getStrenght() {
		return strenght;
	}
	
	private static Dominio uniqueInstance;
	public static Dominio getInstance(){
		if(uniqueInstance==null){
			uniqueInstance = new Dominio();
		} 
		return uniqueInstance;
	}
}
