package logica;
import java.util.LinkedList;

import dicionarios.ConjuntoTestes;
import dicionarios.ConjuntoTuplas;
import dicionarios.Dominio;
import dicionarios.Lambda;
import bean.Nivel;
import bean.Objeto;

public class Impressora {
	
	private Dominio dominio;
	private ConjuntoTuplas cov;
	private ConjuntoTestes conjunto_testes;

	public Impressora(){
		this.dominio = Dominio.getInstance();
		this.cov = ConjuntoTuplas.getInstance();
		this.conjunto_testes = ConjuntoTestes.getInstance();
	}
	
	
	
	
	public void imprimirDominio(){ 
		
		System.out.print("Domínio de cobertura: {");
		System.out.println("");
		for(Objeto f: dominio.getDominio()){
			System.out.print("["); 
			for(Nivel n: f.getLista_Niveis().getNivel()){
				this.imprimirNivelSemIndice(n);
			}
			System.out.print("]"); 
			System.out.println("");
		} 
		System.out.print("}");
		System.out.println(""); 
	} 

	
	
	 
	public void imprimirNivelSemIndice(Nivel n){
		System.out.print("("+n.getFator()+","+n.getValor()+")");
	} 
	
	public void imprimirCov(){  
		  
		System.out.println("+--------------------------+");
		System.out.println("Matriz Cov");
		int cont = 0;
		for(Lambda l: cov.getLista_Lambda()){
			//if(l.getStatus()) continue;
			System.out.print("Combinação: "+l.getGuia());
			System.out.print(" Quantidade esperada: " + this.contar(l.getGuia()));
			System.out.print(" Quantidade computada: " + l.getLista_Objeto().size());
			System.out.println("");
			cont = cont + l.getLista_Objeto().size();
			for(Objeto t: l.getLista_Objeto()){ 
				 
				this.imprimirTupla(t);
				System.out.println("");
			}
			
			System.out.println("");  
		}
		System.out.println("");
	} 	
	
	public int contar(LinkedList<Integer> conf){
		int c = 1;
		for(Integer a: conf){
			c = c * dominio.getDominio().get(a-1).getLista_Niveis().getNivel().size();
		}
		return c;
	}
 
	public void imprimirTupla(Objeto tupla){
		for(Nivel n: tupla.getLista_Niveis().getNivel()){
			this.imprimirNivelSemIndice(n);
		}
		System.out.print("\t Status: "+tupla.getIndice());
	}
	
	public void imprimirTeste(Objeto teste){
		for(Nivel n: teste.getLista_Niveis().getNivel()){
			if(n.getValor()==null){
				System.out.print("_");
				System.out.print("   ");
				continue;
			}
			System.out.printf("%-3d", n.getValor()); 
			System.out.print(" ");
		}
		System.out.print("\t Força: "+teste.getForça());
		System.out.println("");
	}
	
	public void imprimirTestes(){ 
		System.out.println("Conjunto de testes completo: ");
		int i=1; 
		for(Objeto teste: conjunto_testes.getListaTeste()){
			System.out.printf("%-6d", i);
			System.out.print(" "+"|"+"\t ");
			for(Nivel n: teste.getLista_Niveis().getNivel()){
				if(n.getValor()==null){
					System.out.print("_");
					System.out.print("   ");
					continue;
				}
				System.out.printf("%-3d", n.getValor()); 
				System.out.print(" ");
			}
			System.out.print("\t Força: "+teste.getForça());
			i = i + 1;
			System.out.println("");
		
		}
	}

	public void imprimirTestesA(){ 
		System.out.println("Conjunto de testes completo: ");
		int i=1; 
		for(Objeto teste: conjunto_testes.getListaTeste()){
			for(Nivel n: teste.getLista_Niveis().getNivel()){
				if(n.getValor()==null){
					System.out.print("_");
					System.out.print("   ");
					continue;
				}
				System.out.printf("%-3d", n.getValor()); 
				System.out.print(" ");
			}
			i = i + 1;
			System.out.println("");
		
		}
	}
}
