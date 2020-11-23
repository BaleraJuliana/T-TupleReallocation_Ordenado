package logica;


import java.util.Collections;
import java.util.LinkedList;

import dicionarios.Dominio;
import bean.Nivel;
import bean.Objeto;

public class ValorFactory {

	private Dominio dominio;
	private Integer fator;
	private Integer indice;
	private LinkedList<Integer> fatores;
	
	public ValorFactory(){
		
		dominio = Dominio.getInstance();
		fator = 1;
		indice = 0;
		fatores = new LinkedList<Integer>();
	}
	
	private void atualizarDominio(Integer valor) {
		
		LinkedList<Nivel> valores = new LinkedList<Nivel>();
		 
		for(int i=1; i<=valor; i++){
			 
			Nivel n = new Nivel(fator, i);
			valores.add(n);
			indice = indice + 1;
		}
		
		fator+=1;
		Objeto p = new Objeto();
		p.getLista_Niveis().setNiveis(valores);
		dominio.addFator(p);		
	}
	
	public void addNull(){
		Nivel n = new Nivel(fator, null);
		Objeto p = new Objeto();
		p.getLista_Niveis().addNiveis(n);
		dominio.addFator(p);			
	}
	
	public void recolherDados(Integer valor){
		fatores.add(valor);
	}
	
	public void construirDados(){
		Collections.sort(fatores);
		System.out.println(fatores);
		for(Integer valor : fatores){
			atualizarDominio(valor);
		}
	}
}
