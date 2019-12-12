import java.lang.Math;
class Seno extends OperadorUnario{
	Seno(Expressao e){
		super(e);
	}

	@Override
	public double calcular(){
		Operando value = new Operando(Math.sin(this.e.calcular()));
		notifyObservers(value);
		return value.getValor();
	}
}