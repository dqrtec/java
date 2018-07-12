class Operando extends Expression{
	int valor;

	Operando(int valor){
		this.valor = valor;
	}
	int calcular(){
		return valor;
	}
}