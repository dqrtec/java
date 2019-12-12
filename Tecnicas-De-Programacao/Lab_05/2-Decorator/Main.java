class Main{
	public static void main(String args[]){
		Expressao op1, op2, op3, op4, op5;

		op1 = new Operando(45.0);
		op2 = new Seno(op1);

		System.out.println(op2.calcular());
		op2 = new Cosseno(op1);

		System.out.println(op2.calcular());

		op3 = new Operando(1);
		op4 = new Operando(2);
		op5 = new Soma(op3,op4);

		System.out.println(op5.calcular());
	}
}