class Pessoa{
	int idade;

	Pessoa(int idade) throws AgeException{
		if(idade <= 0) throw new AgeException(idade);
		this.idade = idade;
	}

	void setIdade(int idade) throws AgeException{
		this.idade = idade;
	}
}