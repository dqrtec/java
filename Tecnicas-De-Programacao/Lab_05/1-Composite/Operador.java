abstract class Operador extends Expression{
	Expression esquerda, direita;
	abstract int calcular();

	void add(Expression esquerda, Expression direita){
		this.esquerda = esquerda;
		this.direita = direita;
	}

}