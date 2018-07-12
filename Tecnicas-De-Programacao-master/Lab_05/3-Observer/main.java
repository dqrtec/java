class main{
	public static void main(String args[]){
		Expressao e1, e2, e3, e4, e5;
		Observer positivo, negativoOuZero;
		e1 = new Operando(45.0);
		positivo = new Observer_positivo();
		negativoOuZero = new Observer_zero_ou_negativo();
		e1.addObserver(positivo);
		e1.addObserver(negativoOuZero);
		e1 = new Seno(e1);
		System.out.println(e1.calcular());
		
	}
}