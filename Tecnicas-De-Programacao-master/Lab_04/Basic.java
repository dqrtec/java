//Nome: Franklyn Seabra Rogério Bezerra, Matricula: 397847
//Nome: Matheus Sousa Correia Matricula: 400501

//Polimorfismo de inclusão
public class Basic extends Conta{

	//Sobrecarga do construtor
	Basic(int numero_conta){
		this(numero_conta, 0.0);
	}

	
	Basic(int numero_conta, double valor){
		super(numero_conta, valor);
	}

	//Polimorfismo de Coerção, valor pode aceitar tanto double quanto int e float
	void creditar(double valor){
		this.saldo += valor;
	}

	//Polimorfismo de Coerção, valor pode aceitar tanto double quanto int e float
	void debitar(double valor){
		this.saldo -=valor;
	}

	@Override
	public String toString(){
		return "Conta Basic\nId:"+this.numero_conta+"\nSaldo da Conta:"+this.saldo;
	}

}
