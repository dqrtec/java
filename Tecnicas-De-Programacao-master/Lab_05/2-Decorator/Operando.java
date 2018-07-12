class Operando implements Expressao{
	double valor;

	Operando(double valor){
		this.valor = valor;
	}

	@Override
	public double calcular(){
		return valor;
	}
}