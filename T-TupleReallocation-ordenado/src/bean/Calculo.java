package bean;

public class Calculo {
	
	// fun��es para a realiza��o de alguns c�culos :)
	
	public int calcularCombina��oSimples(int x, int y){	
		int a = this.fatorial(x);
		int b = this.fatorial(x-y)*this.fatorial(y);
		return (a/b);
	}
	
	public int fatorial(int x){
		if(x==0 || x==1){
			return 1;
		}
		return x*fatorial(x-1); 
	}
}

