package bean;

import java.util.LinkedList;

public class Combinação {
	
	private LinkedList<LinkedList<Integer>> guia;
	
	private Combinação(){
		guia = new LinkedList<LinkedList<Integer>>();
	}
	  
	private static Combinação uniqueInstance;
	public static Combinação getInstance(){
		if(uniqueInstance==null) 
			uniqueInstance = new Combinação();
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
	
	public Integer removerCombinação(LinkedList<Integer> comb){
		for(LinkedList<Integer> c: guia){
			if(comb.equals(c)){
				guia.remove(c);
				return 1;
			}
		}
		return 0;
	}
}
