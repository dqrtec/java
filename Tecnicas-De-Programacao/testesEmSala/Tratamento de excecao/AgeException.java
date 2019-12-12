class AgeException extends Exception{
	String erro1 = "[0001] Idade menor que zero";
	String erro2 = "[0002] Idade igual à zero";
	int idade;

	AgeException(int idade){
		this.idade = idade;
	}

	public String toString(){
		if(idade < 0){
			return erro1 + " >> " + idade;	
		}else{
			return erro2 + " >> " + idade;
		}


		
	}

}