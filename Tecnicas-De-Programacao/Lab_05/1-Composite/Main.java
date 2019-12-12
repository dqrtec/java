class Main{

	public static void main(String args[]){
		Expression op1, op2, op3, op4, op5;

		op1 = new Operando(2);
		op2 = new Multiplicacao();
		op3 = new Operando(3);
		op4 = new Operando(6);
		op5 = new Divisao();

		op2.add(op1, op5);
		op5.add(op4, op3);

		System.out.println(op2.calcular());
	}
}