package bean;

import java.util.LinkedList;

public class Combina��o {
	
	private LinkedList<LinkedList<Integer>> guia;
	
	private Combina��o(){
		guia = new LinkedList<LinkedList<Integer>>();
	}
	  
	private static Combina��o uniqueInstance;
	public static Combina��o getInstance(){
		if(uniqueInstance==null) 
			uniqueInstance = new Combina��o();
		return uniqueInstance;
	} 
	
	public void enumerador(int n, Integer t){
		   
		   LinkedList<Integer> s = new LinkedList<Integer>();
		   int k = 0;
		   s.add(0);
		    
		   while(true) {
			     
			   if(s.get(k) < n) {
				   s.add(s.get(k)+1);
				   k += 1; 
				} else {
				  s.set(k-1, s.get(k-1)+1); 
				  s.removeLast();
				  k -= 1; 
			   }
			   if(k == 0) break;
			   if((s.size())==t+1){
				   @SuppressWarnings("unchecked")
				LinkedList<Integer> aux = (LinkedList<Integer>) s.clone();
				   aux.removeFirst();
				   guia.add(aux);
				}
		   }  
	  }

	public LinkedList<LinkedList<Integer>> getGuia() {
		return guia;
	}  
	
	public Integer removerCombina��o(LinkedList<Integer> comb){
		for(LinkedList<Integer> c: guia){
			if(comb.equals(c)){
				guia.remove(c);
				return 1;
			}
		}
		return 0;
	}
}
