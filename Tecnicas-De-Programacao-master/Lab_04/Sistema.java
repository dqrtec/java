//Nome: Franklyn Seabra Rogério Bezerra, Matricula: 397847
//Nome: Matheus Sousa Correia Matricula: 400501
public class Sistema{
	Conta[] contas = new Conta[10];
	int posicao = 0;

	void criarContaXPTOBasic(int numero_conta){
		if(!contaExiste(numero_conta)){
			contas[posicao] = new Basic(numero_conta); // Polimorfismo de coerção
			posicao++;
		}
	}

	void criarContaXPTOPlus(int numero_conta){
		if(!contaExiste(numero_conta) ){
			contas[posicao] = new Plus(numero_conta); // Polimorfismo de coerção
			posicao++;
		}
	}

	void criarContaXPTOExtreme(int numero_conta){
		if(!contaExiste(numero_conta) ){
			contas[posicao] = new Extreme(numero_conta); // Polimorfismo de coerção
			posicao++;
		}
	}

	//Esse método recebe o número da conta e o valor as ser creditado, então ele percorre o vetor contas até achar uma conta com o mesmo número,
	//depois de achada ele chama o método creditar da conta.
	void creditar(int numero_conta, double valor){
		for(int i = 0;i < posicao; i++){
			if(contas[i].getNumeroConta() == numero_conta){
				contas[i].creditar(valor);//Polimorfismo de sobrecarga da função creditar
			}
		}			
	}

	//Esse método recebe o número da conta e o valor as ser debitado, então ele percorre o vetor contas até a achar uma conta com o mesmo número,
	//depois de achada ele chama o método debitar da conta.
	void debitar(int numero_conta, double valor){
		for(int i = 0;i < posicao; i++){
			if(contas[i].getNumeroConta() == numero_conta){
				contas[i].debitar(valor);//Polimorfismo de sobrecarga da função debitar
			}
		}
	}


	//Esse método percorre o vetor de contas até achar uma conta com o número passad, então ele retorna o saldo daquela conta.
	//Caso contrário ele retorna -1.
	double consultarSaldo(int numero_conta){
		for(int i = 0;i < posicao; i++){
			if(contas[i].getNumeroConta() == numero_conta){
				return contas[i].consultarSaldo();
			}
		}
		return -1;
	}
	

	//Ele vai iterar sobre os elementos e comparar os numeros de cada conta que há no vetor contas. 
	//Retornará true se encontrar alguma conta com mesmo número que numero_conta e false caso contrário.
	boolean contaExiste(int numero_conta){
		if(contas.length > 0) return false;
		for(Conta c: contas){
			if(c.getNumeroConta() == numero_conta){
				return true;
			}
		}
		return false;
	}

}