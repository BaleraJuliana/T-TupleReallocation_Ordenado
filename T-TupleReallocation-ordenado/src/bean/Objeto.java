package bean;

import java.util.LinkedList;

import dicionarios.ListaNiveis;

public class Objeto{
	
	public ListaNiveis lista_niveis;
	public Integer indice;
	public int força;

	public Objeto(){
		
		lista_niveis = new ListaNiveis();
		indice = 0;
		int força = 0;
	}
	
	public int getForça(){
		return força;
	}
	
	public void setForça(int força){
		this.força = força;
	}

	public ListaNiveis getLista_Niveis() {
		return lista_niveis;
	}
	
	public void setLista_Niveis(ListaNiveis lista_niveis){
		this.lista_niveis = lista_niveis;
	}
	
	public Integer getIndice() {
		return indice; 
	} 
	
	public void setIndice(Integer indice){
		this.indice = indice;
	}
	
	public boolean compara(Objeto o){ 
		if(!o.getLista_Niveis().compara(lista_niveis)) return false;
		return true;
	}
	
	public Objeto clonar(){
		Objeto clone = new Objeto(); 
		for(Nivel n: this.getLista_Niveis().getNivel()){
			Nivel clone_nivel = new Nivel(n.getFator(), n.getValor());
			clone.getLista_Niveis().getNivel().add(clone_nivel);
		}
		clone.setIndice(this.indice);
		clone.setForça(this.força);
		return clone;
	}
	
	
	@SuppressWarnings("unchecked")  
	
	public void replace(int posicao, Nivel nivel){
		ListaNiveis aux = new ListaNiveis();
		int i=1;
		for(Nivel n: lista_niveis.getNivel()){
			if(i==posicao){
				aux.addNiveis(nivel);
				i = i + 1;
				continue; 
			} 
			aux.addNiveis(n); 
			i = i + 1;
		}
		lista_niveis.setNiveis((LinkedList<Nivel>)aux.getNivel().clone());
	}
	
}
 