class Soma extends OperadorBinario{

	Soma(Expressao esquerda, Expressao direita){
		super(esquerda, direita);
	}

	@Override
	public double calcular(){
		return esquerda.calcular() + direita.calcular();
	}
}