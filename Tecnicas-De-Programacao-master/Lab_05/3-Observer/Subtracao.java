class Subtracao extends OperadorBinario{

	Subtracao(Expressao esquerda, Expressao direita){
		super(esquerda, direita);
	}

	@Override
	public double calcular(){
		Operando subtracao = new Operando(esquerda.calcular() - direita.calcular());
		notifyObservers(subtracao);
		return subtracao.getValor();
	}
}