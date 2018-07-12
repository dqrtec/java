abstract class OperadorBinario extends Operador{
	Expressao esquerda, direita;

	OperadorBinario(Expressao esquerda, Expressao direita){
		this.esquerda = esquerda;
		this.direita = direita;
	}
}