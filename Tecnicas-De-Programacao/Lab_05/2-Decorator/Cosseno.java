import java.lang.Math;
class Cosseno extends OperadorUnario{
	Cosseno(Expressao e){
		super(e);
	}

	@Override
	public double calcular(){
		return Math.cos(super.calcular());
	}
}