class Operando extends Expressao{
	private double valor;

	Operando(double valor){
		this.valor  = valor;
	}

	double getValor(){
		return valor;
	}

	void setValor(double valor){
		this.valor = valor;
		//notifyObservers();
	}

	public double calcular(){
		return valor;
	}
}