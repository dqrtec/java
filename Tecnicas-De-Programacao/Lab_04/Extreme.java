//Nome: Franklyn Seabra Rogério Bezerra, Matricula: 397847
//Nome: Matheus Sousa Correia Matricula: 400501

//Polimorfismo de inclusão
public class Extreme extends Conta{

	//Sobrecarga do construtor
	Extreme(int numero_conta){
		this(numero_conta, 0.0);
	}

	//Sobrecarga do construtor
	Extreme(int numero_conta, double valor){
		super(numero_conta, valor);
	}

	void creditar(double valor){
		this.saldo += valor + (0.002*valor); //Rende um bônus de 0.2% do valor creditado
	}

	void debitar(double valor){
		this.saldo -=valor - (0.002*valor); //Restitui 0.2% do valor debitado
	}

	@Override
	public String toString(){
		return "Conta Extreme\nId:"+this.numero_conta+"\nSaldo da Conta:"+this.saldo;
	}
}