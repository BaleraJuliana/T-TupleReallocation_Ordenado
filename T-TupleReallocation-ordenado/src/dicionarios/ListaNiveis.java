package dicionarios;


import java.util.LinkedList;

import bean.Nivel;

public class ListaNiveis {
	
	private LinkedList<Nivel> niveis;
	
	public ListaNiveis(){
		niveis = new LinkedList<Nivel>();
	}

	public LinkedList<Nivel> getNivel() {
		return niveis;
	}

	public void setNiveis(LinkedList<Nivel> n) {
		this.niveis = n;
	}	

	public void addNiveis(Nivel n) {
		this.niveis.add(n);
	}	
	
	public boolean compara(ListaNiveis ln){
		int i = 0;
		for(Nivel n: ln.getNivel()){
			if(!n.compara(niveis.get(i))) return false;
			i = i + 1;
		}	
		return true;
	}
	
	public ListaNiveis clonar(){
		ListaNiveis clone = new ListaNiveis();
		for(Nivel n: this.getNivel()){
			Nivel clone_nivel = new Nivel(n.getFator(), n.getValor());
			clone.getNivel().add(clone_nivel);
		}
		return clone;
	}
}
