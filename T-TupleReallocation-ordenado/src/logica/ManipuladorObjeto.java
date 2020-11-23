package logica;

import java.util.Collection;
import java.util.LinkedList;



import dicionarios.ConjuntoTuplas;
import dicionarios.Dominio;
import dicionarios.Lambda;
import bean.Combinação;
import bean.Nivel;
import bean.Objeto;

public class ManipuladorObjeto{
		
		private static ManipuladorObjeto uniqueInstance;
		private Dominio dominio;
		private ConjuntoTuplas cov;
		private LinkedList<Objeto> fonte;
		 
		private ManipuladorObjeto(){
			dominio = Dominio.getInstance();
			cov = ConjuntoTuplas.getInstance();
			fonte = new LinkedList<Objeto>();
		} 
		
		public static ManipuladorObjeto getInstance(){
			if(uniqueInstance == null){
				uniqueInstance = new ManipuladorObjeto();
			} 
			return uniqueInstance; 
		}   
		    
		@SuppressWarnings("unchecked")
		public void geracaoCov(){  
		 	
			Combinação combinação = Combinação.getInstance(); 
			  
			for(LinkedList<Integer> comb: combinação.getGuia()){
				  
				int i = 0; 
				
				for(Integer coluna: comb){ 
					if(i==0){
						this.geracaoInicial(coluna);
					} else {
						this.geracaoInicial2(coluna); 
					} 
					i = i + 1;  
				} 
				
				Lambda nova = new Lambda();
				nova.setGuia(comb);
				nova.getLista_Objeto().addAll((Collection<? extends Objeto>) fonte.clone());
				fonte.clear();	
				cov.getLista_Lambda().add(nova);
			} 
		}    
		   
		public void geracaoInicial(Integer coluna){
			
			for(Nivel n1: dominio.getDominio().get(coluna-1).getLista_Niveis().getNivel()){
				Objeto aux = new Objeto();
				aux.getLista_Niveis().addNiveis(n1.clonar()); 
				fonte.add(aux.clonar()); 
			} 
		}
		  
		public void geracaoInicial2(Integer coluna){	
			
			int tamanho_nessa_vez = (fonte.size())*(dominio.getDominio().get(coluna-1).getLista_Niveis().getNivel().size());
			LinkedList<Objeto> aux1 = new LinkedList<Objeto>();
			for(Objeto ln: fonte){  
				for(int j=0; j<dominio.getDominio().get(coluna-1).getLista_Niveis().getNivel().size(); j++){	
					aux1.add(ln.clonar()); 
				}
			} 
			fonte.clear(); 
			fonte.addAll(aux1);
			aux1.clear();
			int contador = 0;			
			while(true){ 
				for(Nivel n1: dominio.getDominio().get(coluna-1).getLista_Niveis().getNivel()){
					fonte.get(contador).getLista_Niveis().getNivel().add(n1);
					contador = contador + 1;
					if(contador==tamanho_nessa_vez){
						break;
					}
				}
				if(contador==tamanho_nessa_vez){
					break;
				} 
			}  
		} 
		
		public void removerTupla(Objeto tupla){
			for(Lambda l: cov.getLista_Lambda()){
				l.removerTupla(tupla);
			}
		} 
		
		public void adicionarTuplasTeste(Objeto teste){
			
			for(Lambda guia: cov.getLista_Lambda()){
				Objeto tupla = new Objeto();
				for(Integer posição: guia.getGuia()){
					tupla.getLista_Niveis().addNiveis(teste.getLista_Niveis().getNivel().get(posição-1).clonar());
				}
				guia.getLista_Objeto().add(tupla);
			}			
		}
} 
