package dicionarios;

import java.util.LinkedList;

import bean.Objeto;

public class ConjuntoTuplas{
	
	private LinkedList<Lambda> lista_lambda;

	private ConjuntoTuplas() { 
		lista_lambda = new LinkedList<Lambda>();
	} 
	
	public LinkedList<Lambda> getLista_Lambda() { 
		return lista_lambda;
	} 

	public void setLista_Lambda(LinkedList<Lambda> lista_lambda) {
		this.lista_lambda = lista_lambda;
	}	  
	 
	private static ConjuntoTuplas uniqueInstance;
	public static ConjuntoTuplas getInstance(){
		if(uniqueInstance==null) 
			uniqueInstance = new ConjuntoTuplas();
		return uniqueInstance;
	}
	
	public Lambda acharMaior(){
		
		Lambda aux = new Lambda();  
		
		for(Lambda l: lista_lambda){
		
			if(l.getStatus()==true){
				continue;
			} else{
				if(l.getLista_Objeto().size()==0){
					l.setStatus(true);
					continue;
				}
			}
			if(l.getTamanho()>aux.getTamanho()){
				aux = l;
			} 
		}
		return aux;
	}

	public void inverter(){ 
		LinkedList<Lambda> invertida = new LinkedList<Lambda>();
		for(Lambda l: lista_lambda){
			Lambda novo = new Lambda();
			novo.setGuia(l.getGuia());	
			int tamanho = l.getLista_Objeto().size();
			
			for(int i=(tamanho-1); i>=0; i--){
				novo.getLista_Objeto().add(l.getLista_Objeto().get(i));
			}
			invertida.add(novo);
		}
		lista_lambda.clear();
		lista_lambda.addAll(invertida);
	} 
}
 