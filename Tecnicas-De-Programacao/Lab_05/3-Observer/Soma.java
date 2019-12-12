class Soma extends OperadorBinario{

	Soma(Expressao esquerda, Expressao direita){
		super(esquerda, direita);
	}

	@Override
	public double calcular(){
		Operando soma = new Operando(esquerda.calcular() + direita.calcular());
		notifyObservers(soma);
		return soma.getValor();
	}
}
