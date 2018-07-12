//Nome: Franklyn Seabra Rogério Bezerra, Matricula: 397847
//Nome: Matheus Sousa Correia Matricula: 400501

//Polimorfismo de inclusão
public class Plus extends Conta{

	//Sobrecarga do construtor
	Plus(int numero_conta){
		this(numero_conta, 0.0);
	}
	
	//Sobrecarga do construtor
	Plus(int numero_conta, double saldo){
		super(numero_conta, saldo);
	}

	void creditar(double valor){
		this.saldo += valor +(0.005*valor); //Rende um bônus de 0.5% do valor creditado
	}

	void debitar(double valor){
		this.saldo -=valor;
	}

	@Override
	public String toString(){
		return "Conta Plus\nId:"+this.numero_conta+"\nSaldo da Conta:"+this.saldo;
	}
}