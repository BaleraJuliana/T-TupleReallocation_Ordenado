package dicionarios;

import java.util.LinkedList;

import bean.Objeto;

public class Lambda{
	
	private LinkedList<Integer> guia;
	private LinkedList<Objeto> lista_objeto;
	private boolean status;
	
	public Lambda(){
		guia = new LinkedList<Integer>();
		lista_objeto = new LinkedList<Objeto>();
		status = false;
	} 
	
	public LinkedList<Integer> getGuia() {
		return guia;
	}
	
	public void setGuia(LinkedList<Integer> guia) {
		this.guia = guia;
	}
	
	public boolean getStatus() {
		return status;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
	public LinkedList<Objeto> getLista_Objeto() {
		return lista_objeto;
	} 
	
	public void setLista_Objeto(LinkedList<Objeto> conjunto_tuplas) {
		this.lista_objeto = conjunto_tuplas;
	}
	
	public boolean compara(Lambda l){
		int i = 0;
		
		if(l.getLista_Objeto().size()==0 || lista_objeto.size()==0){
			return false;
		}
		
		for(Integer elemento: guia){
			if(!(elemento==l.getGuia().get(i))){
				return false;
			} 
			i = i + 1; 
		} 
		return true;
	}
	
	public Lambda clonar(){
		
		Lambda clone = new Lambda();
		LinkedList<Integer> clone_guia = new LinkedList<Integer>();
		LinkedList<Objeto> clone_objeto = new LinkedList<Objeto>();
		
		for(Integer numero: guia){
			clone_guia.add(numero);
		}
		
		for(Objeto o: lista_objeto){
			clone_objeto.add(o.clonar());
		}
		
		clone.setLista_Objeto(clone_objeto);
		clone.setGuia(clone_guia);
		
		return clone;
	}
	
	public Integer getTamanho() {
		if(lista_objeto.size()!=0){
			return (lista_objeto.size());
		} 
		return (-1);
	}
	
	public void removerTupla(Objeto tupla){
		int i = 0;
		for(Objeto tu: lista_objeto){
			if(tupla.compara(tu)){
				lista_objeto.remove(i);
				break;
			}
			i = i + 1;
		}
	}
}
