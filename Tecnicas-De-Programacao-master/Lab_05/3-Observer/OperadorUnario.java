abstract class OperadorUnario extends Expressao{
	Expressao e;

	OperadorUnario(Expressao e){
		this.e = e;
	}

	public double calcular(){
		return e.calcular();
	}
}