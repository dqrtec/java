//Decorar com multiplicacao
class Multiplicacao extends Operador{
	int calcular(){
		return this.esquerda.calcular() * this.direita.calcular();
	}
}