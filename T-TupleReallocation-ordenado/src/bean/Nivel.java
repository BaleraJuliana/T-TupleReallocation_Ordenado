package bean;

public class Nivel{
	
	private Integer fator;
	private Integer valor;
	
	public Nivel(Integer fator, Integer valor){
		this.fator = fator;
		this.valor = valor;
	}

	public Integer getFator() {
		return fator;
	}

	public Integer getValor() {
		return valor;
	}

	public void setFator(Integer fator) {
		this.fator = fator;
	}

	public void setValor(Integer valor) {
		this.valor = valor;
	}
	
	public boolean compara(Nivel n){
		if(!(n.getFator()==this.fator))return false;
		if(!(n.getValor()==this.valor))return false;
		return true;
	}
	
	public Nivel clonar(){
		Nivel clone = new Nivel(this.fator, this.valor);
		return clone;
	}
} 
