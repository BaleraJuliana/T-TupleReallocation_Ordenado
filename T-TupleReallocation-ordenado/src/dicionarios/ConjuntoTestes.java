package dicionarios;

import java.util.LinkedList;

import bean.Nivel;
import bean.Objeto;

public class ConjuntoTestes {
	
	private LinkedList<Objeto> lista_teste;
 	private ConjuntoTuplas conjunto_tuplas;
 	
	private ConjuntoTestes() {
		lista_teste = new LinkedList<Objeto>();
		conjunto_tuplas = ConjuntoTuplas.getInstance();
	}
	
	public LinkedList<Objeto> getListaTeste(){
		return lista_teste;
	}
	public void setListaTeste(LinkedList<Objeto> lista_testes){
		 this.lista_teste = lista_testes;
	}  
	
	private static ConjuntoTestes uniqueInstance;
	public static ConjuntoTestes getInstance(){
		if(uniqueInstance == null){
			uniqueInstance = new ConjuntoTestes();
		}
		return uniqueInstance;
	} 
		 
	public void geracaoTestesInicial(){  
		
		int i = 0;    
		 
		Lambda maior = new Lambda();  
		maior = conjunto_tuplas.acharMaior();
		for(Objeto o: maior.getLista_Objeto()){
			
			Objeto teste = new Objeto(); 	
			teste = this.adicionarValoresSemImportancia(o);
			teste.setIndice(i);
			teste.setForça(teste.getForça()+1);
			lista_teste.add(teste);			
			i = i + 1;
		}
		i = 0;
		for(Lambda l: conjunto_tuplas.getLista_Lambda()){	
			if(l.compara(maior)){
				conjunto_tuplas.getLista_Lambda().remove(i);
				break;
			} 
			i = i + 1;
		}
		
		Lambda novo = new Lambda();
		novo.setGuia(maior.getGuia());
		conjunto_tuplas.getLista_Lambda().add(novo);
	} 
	 
	public Objeto adicionarValoresSemImportancia(Objeto o){
		
		Objeto teste = new Objeto(); 
		
		int i=1;  
		
		for(Nivel n: o.getLista_Niveis().getNivel()){
			
			if(i==n.getFator()){
				teste.getLista_Niveis().addNiveis(n);
				i = i + 1;
				continue;
			} 
			 
			if(i<n.getFator()){
				int k = i;
				for(int j=k; j<n.getFator(); j++){
					Nivel nivel = new Nivel(i, null);
					teste.getLista_Niveis().addNiveis(nivel);
					i = i + 1;
				}
			}
			teste.getLista_Niveis().addNiveis(n);
			i = i + 1;
		}
		
		while(i<Dominio.getInstance().getDominio().size()){
			Nivel nivel = new Nivel(i, null);
			teste.getLista_Niveis().addNiveis(nivel);
			i = i + 1;
		}
		return teste;				
	}
}	