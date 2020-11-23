package logica;

import java.util.LinkedList;

import bean.Calculo;
import bean.Combinação;
import bean.Nivel;
import bean.Objeto;
import dicionarios.ConjuntoTestes;
import dicionarios.ConjuntoTuplas;
import dicionarios.Dominio;
import dicionarios.Lambda;

public class Crescimento {
	
	private ConjuntoTuplas conjunto_tupla;
 	private ConjuntoTestes conjunto_teste;
 	private ManipuladorObjeto mc; 
 	private Impressora impressora;
 	 
 	private Crescimento(){ 
 		conjunto_tupla = ConjuntoTuplas.getInstance();
 		conjunto_teste = ConjuntoTestes.getInstance();
 		mc = ManipuladorObjeto.getInstance();
 		impressora = new Impressora(); 
 	}  
 	 
 	public static Crescimento uniqueInstance;
	public static Crescimento getInstance(){
 		if(uniqueInstance==null) return new Crescimento();  
 		return uniqueInstance;
 	}    

	public void init(int loop){  
 			 
 		int parada = Combinação.getInstance().getGuia().size()-1; 
 		Lambda aux2 = new Lambda(); 
			
 		while(parada!=0){
 			
 			aux2 = conjunto_tupla.acharMaior();
 			aux2.setStatus(true);
 			 
 			
 			int posicao_teste=0;
 			int controle = 0; 
 			int posição_tupla = 0;
 			 
 			//while(!aux2.getLista_Objeto().isEmpty()){	 	 
 			while(!this.verificar(aux2.getLista_Objeto())){
 				
 				/**
 					for(Objeto o: aux2.getLista_Objeto()){
 						impressora.imprimirTupla(o);
						System.out.println("");
 					}
 					System.out.println("");
 				**/	
 	 				controle = 0;   
 					Objeto teste = new Objeto();
 					posição_tupla = 0;
 					
 					while(aux2.getLista_Objeto().get(posição_tupla).getIndice()==1){
 						posição_tupla = posição_tupla + 1;
 					} 
 					 
 					while(controle<2){ 
 						
 						if(posicao_teste > conjunto_teste.getListaTeste().size()-1){
 							posicao_teste = 0;
 							controle = controle + 1;
 						
 							if(controle==2){    
 								
 								aux2.getLista_Objeto().get(posição_tupla).setIndice(1);
 								break;  
 							}  
 						}   
 						  
 						teste = conjunto_teste.getListaTeste().get(posicao_teste).clonar(); 
 						 
 				
 						if(this.verificarCombina(aux2.getLista_Objeto().get(posição_tupla), teste)){
 							
 							this.atualizarTeste(teste, aux2.getLista_Objeto().get(posição_tupla));
 							
 							if(this.verificarMeta(teste, loop)){
 							
 								this.atualizarTeste(conjunto_teste.getListaTeste().get(posicao_teste), aux2.getLista_Objeto().get(posição_tupla));	
 								teste = conjunto_teste.getListaTeste().get(posicao_teste);
 								posicao_teste = posicao_teste + 1;
 								break; 	 						
 							}
 							
 							teste = conjunto_teste.getListaTeste().get(posicao_teste);
 							posicao_teste = posicao_teste + 1;
 							continue;
 						
 						}
 						posicao_teste = posicao_teste + 1;
 					} 
 					
 					posição_tupla = posição_tupla + 1; 
 					this.acharRemover(teste);	 
 			}
 			parada = parada - 1;
 		}  		
 	} 
  
	public boolean verificar(LinkedList<Objeto> tuplas){
		for(Objeto o: tuplas){
			if(o.getIndice()==0){
				return false;
			}
		}
		return true;
	}
	
 	public boolean verificarCombina(Objeto tupla, Objeto teste){ 
 		
 		int cont = 0;  
 		 
 		for(Nivel n: tupla.getLista_Niveis().getNivel()){ 
 			
 			if(teste.getLista_Niveis().getNivel().get(n.getFator()-1).getValor()==n.getValor() || teste.getLista_Niveis().getNivel().get(n.getFator()-1).getValor()==null){
 				cont = cont + 1;
 			}
 		}  
 		
 		if(cont==tupla.getLista_Niveis().getNivel().size()){
 			return true;
 		}  
 		  
 		return false;
 	} 
 	
 	public boolean verificarCombina1(Objeto tupla, Objeto teste){
		 
 		if(teste.getLista_Niveis().getNivel().size()==0) return false;
 		
 		int cont = 0;
 		 
 		for(Nivel n: tupla.getLista_Niveis().getNivel()){
 			
 			if(teste.getLista_Niveis().getNivel().get(n.getFator()-1).getValor()==n.getValor()){
 				cont = cont + 1;
 			}
 		} 
 		 
 		if(cont==tupla.getLista_Niveis().getNivel().size()){
 			return true;
 		} 
	  
 		return false; 
 	}
 	
 	public void atualizarTeste(Objeto teste,Objeto tupla){
 			for(Nivel n: tupla.getLista_Niveis().getNivel()){
 				if(teste.getLista_Niveis().getNivel().get(n.getFator()-1).getValor()==n.getValor() || 
 					teste.getLista_Niveis().getNivel().get(n.getFator()-1).getValor()==null){
 					teste.replace(n.getFator(), n.clonar());
 				}
 			}
 	}
 
 	public void acharRemover(Objeto teste){
 		for(Lambda l: conjunto_tupla.getLista_Lambda()){
 			for(Objeto tupla: l.getLista_Objeto()){		
 				if(verificarCombina1(tupla, teste)){
 					teste.setForça(teste.getForça() +1);
 					mc.removerTupla(tupla);
 					break;
 				}
 			}
 		}		
 	} 	
 	
 	public boolean verificarMeta(Objeto teste, int loop){

 		Calculo calculo = new Calculo();
 		
 		int numeroFatores = 0;
 		
 		for(Nivel n: teste.getLista_Niveis().getNivel()){
 			if(n.getValor()!=null){
 				numeroFatores = numeroFatores + 1;
 			} 
 		} 
 	
 		int meta = calculo.calcularCombinaçãoSimples(numeroFatores, Dominio.getInstance().getStrenght())-loop;
 		
 		//System.out.print("Teste: "); 
 		//impressora.imprimirTeste(teste);
 	/**
 		System.out.println("Fatores cobertos: "+numeroFatores);
 		System.out.println("Meta: "+meta);
 	**/
 		int real = teste.getForça();  
 		
 		//System.out.println("começa aqui");
 		
 		for(Lambda l: conjunto_tupla.getLista_Lambda()){ 
 			for(Objeto tupla: l.getLista_Objeto()){	
 				if(verificarCombina1(tupla, teste)){
 					//impressora.imprimirTupla(tupla);
 					//System.out.println(""); 
 					real = real + 1;
 					break; 
 				} 
 			} 
 		}
 		//System.out.println("termina aqui");
 		
 	//	System.out.println("Real: "+real);
 		if(meta==real){
 			return true;
 		}
 		return false;
 	} 
}

