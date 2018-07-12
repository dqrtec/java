abstract class OperadorUnario extends Operador{
	Expressao e;

	OperadorUnario(Expressao e){
		this.e = e;
	}

	@Override
	public double calcular(){
		return e.calcular();
	}
}