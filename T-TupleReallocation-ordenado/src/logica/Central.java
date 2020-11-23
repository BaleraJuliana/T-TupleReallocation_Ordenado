package logica;


import java.util.Scanner;

import bean.Calculo;
import bean.Combinação;
import bean.Nivel;
import bean.Objeto;
import dicionarios.ConjuntoTestes;
import dicionarios.ConjuntoTuplas;
import dicionarios.Dominio;
import dicionarios.Lambda;

public class Central {

	private ManipuladorObjeto manipulador;
	private ConjuntoTuplas conjunto_tupla;
	private Dominio dominio;
	private ConjuntoTestes conjunto_teste;
	private Crescimento crescimento;

	private ValorFactory factory;
	private Scanner leitor;
	private Impressora impressora;
	public Central(){
		
		manipulador = ManipuladorObjeto.getInstance();
		conjunto_tupla = ConjuntoTuplas.getInstance();
		dominio = Dominio.getInstance();
		conjunto_teste = ConjuntoTestes.getInstance();
		crescimento = Crescimento.getInstance();
		 
		leitor = new Scanner(System.in); 
		factory = new ValorFactory();
		impressora = new Impressora();
 		
	}  
	 
	public void entrada(){   
		
		Combinação combinação = Combinação.getInstance();
		
		System.out.println("T-Tuple Reallocation - Ordenado"); 
		System.out.println("Digite o valor do Strenght: ");
		int t = leitor.nextInt(); 
		System.out.println("Digite em cada linha o número de níveis de cada fator: ");
	
		Integer valor; 
		
		while(true){
			valor = leitor.nextInt();  
			if(valor!=0){ 
				factory.recolherDados(valor);
			}else{
				break;  
			}  
		}  
		
		factory.construirDados();
		dominio.setStrenght(t);
		
		long tempo_inicial = System.currentTimeMillis();
	 	
		combinação.enumerador(dominio.getDominio().size(), dominio.getStrenght());	
		factory.addNull();  
		manipulador.geracaoCov(); 
		conjunto_teste.geracaoTestesInicial();
		conjunto_tupla.inverter();
		int controle = 0;

		while(this.verificarContinuação() && controle<=combinação.getGuia().size()-1){

			for(Lambda l: conjunto_tupla.getLista_Lambda()){
				l.setStatus(false);
				for(Objeto o: l.getLista_Objeto()){
					o.setIndice(0);
				}
			}
			crescimento.init(controle);
			controle = controle + 1;

			impressora.imprimirCov(); /////////////////////

			if(controle > combinação.getGuia().size()-1){

				for(Lambda l: conjunto_tupla.getLista_Lambda()){
					l.setStatus(false);

					for(Objeto o: l.getLista_Objeto()){
						o.setIndice(0);
					}
				} 

				conjunto_teste.geracaoTestesInicial();
				controle = 0;
			}
			impressora.imprimirTestes();
		} 
		
		long tempo_final = System.currentTimeMillis();
		
		impressora.imprimirTestes();
		System.out.println("");
		
		System.out.println("Tempo de execução do algorimto: " + ((tempo_final - tempo_inicial))+" milisegundos");		
		System.out.println("Quantidade de casos de teste: "+conjunto_teste.getListaTeste().size());
	} 
	  
	public void verificarCobertura(int t){
		  
		for(Lambda l: conjunto_tupla.getLista_Lambda()){
			for(Objeto tupla: l.getLista_Objeto()){
				for(Objeto teste: conjunto_teste.getListaTeste()){
					if(verificarCombina(tupla, teste)){
						tupla.setIndice(255);
					}
				} 
			} 
		}
	}   
	
	public boolean verificarContinuação(){
		for(Lambda l: conjunto_tupla.getLista_Lambda()){
			if(l.getLista_Objeto().size()!=0){
				return true;
			} 
		}
		return false;
	}
	
	public boolean verificarCombina(Objeto tupla,Objeto teste){
		 
 		if(teste.getLista_Niveis().getNivel().size()==0) return false;
 		
 		int cont = 0; 
 		 
 		for(Nivel n: tupla.getLista_Niveis().getNivel()){
 			
 			if(teste.getLista_Niveis().getNivel().get(n.getFator()-1).compara(n)){
 				cont = cont + 1;
 			}
 		} 
 		 
 		if(cont==tupla.getLista_Niveis().getNivel().size()){
 			return true;
 		} 
	 
 		return false;
 	}
}


