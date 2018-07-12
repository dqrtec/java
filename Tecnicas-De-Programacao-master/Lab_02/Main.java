/*Gustavo Antonio Sousa P.M 399611 
Este foi feito inicialmente por ele e recebeu acréscimos meus
*/
class Main{
	public static void main(String args[]){
		int k = 5,l = 0;

		/*Criação*/
		Conjunto conj1 = new Conjunto(10);
		Conjunto conj2 = new Conjunto(5);
		Conjunto conj3 = new Conjunto(5);
		Conjunto conj4 = new Conjunto(10);

		/*Inserção*/		
		for(int i = 0;i<10;i++){ 
			conj1.adicionar(i);
		}
		for(int z = 0;z<5;z+=2){
			conj2.adicionar(z);
		}
		for(int y = 1;y<6;y+=2){
			conj3.adicionar(y);
		}
		for(int j = 1;j<11;j++){
			if(j<4){ conj4.adicionar(j); } // Conjunto 4:Dez primeiros primos
			else{
				if(l == 0){
					conj4.adicionar(k);
					k += 2;
					l += 1;
				}else{
					conj4.adicionar(k);
					k += 4;
					l -= 1;
				}
			}
		}


		/*Teste iniciado*/
		//Teste A
		System.out.println("=========TESTE A========="); 
		if(conj4.conjuntoPertence(conj4)){
			System.out.println("Sim");
		}else{
			System.out.println("Nao");
		}
		
		//Teste B
		System.out.println("=========TESTE B=========");
		int teste1 = 0, teste2 = 0, teste3 = 0,teste4 =0;

		if(conj1.conjuntoPertence(conj2)){ //pertinencia conjunto 2
			teste1 = 1;
			System.out.println("O conjunto 2 eh pertinente no conjunto 1");
		}else{
			System.out.println("O conjunto 2 nao eh pertinente no conjunto 1");
		}

		if(conj1.conjuntoPertence(conj3)){ //pertinencia conjutno 3
			teste2 = 1;
			System.out.println("O conjunto 3 eh pertinente no conjunto 1");
		}else{
			System.out.println("O conjunto 3 nao eh pertinente no conjunto 1");
		}

		if(conj1.conjuntoPertence(conj4)){ //pertinencia conjunto 4
			teste3 = 1;
			System.out.println("O conjunto 4 eh pertinente no conjunto 1");
		}else{ 
			System.out.println("O conjunto 4 nao eh pertinente no conjunto 1");
		}

		if(teste1 == 1 && teste2 == 1 && teste3 == 1){ //pertinencia final
			System.out.println("Todos estao pertinentes");
		}else{
			System.out.println("Nem todos estao pertinentes");
		}
		
		//Teste C
		System.out.println("=========TESTE C=========");
		Conjunto uniao = conj2.uniao(conj3);
		int igual = 1;
		for(int m = 0;m<10;m++){
			if(!(conj1.elementoPertence(uniao.array[m]))){ 
				System.out.println("Nao eh igual");
				igual = 0;
			}
			break;
		}
		if(igual == 1){ System.out.println("Iguais"); }
		//Teste D
		System.out.println("=========TESTE D=========");
		Conjunto inter = conj1.interseccao(conj2);
		boolean vazio = true;
		for(int n = 0;n<(inter.getTamanho());n++){
			if( (inter.getElemento(l)) != null ){
				vazio = false;
			}
		}
		if(vazio){
			System.out.println("Interseccao vazia");
		}else{
			System.out.println("Interseccao nao vazia");
		}
		//Teste E
		System.out.println("=========TESTE E=========");
		Conjunto diferenca = conj1.diference(conj2);
		for(int m = 0;m<(conj1.getTamanho() - conj2.getTamanho()); m++){
			System.out.println("Elemento "+ m + "eh" + diferenca.getElemento(m));
		}

		//Teste da Lei de Morgan - Questão 5
		System.out.println("=========Teste da Lei de Morgan - Questão 5=========");
		Conjunto universo = new Conjunto(50);


		for(int f = 1; f<=universo.getTamanho(); f++){//Preenchendo conjunto universo
			universo.adicionar(i);
			//System.out.println(universo.array[i]);
		}
		//Ida da Lei de Morgan ~(AuB) = ~A i ~B, onde A = Conj1 , B = conj2, u = uniao e i = interseccao
		Conjunto u = conj1.uniao(conj2); //AuB
		Conjunto idaTeste1 = universo.diference(u); //~(AuB)

		Conjunto bA = universo.diference(conj1); //bA = ~A
		Conjunto bB = universo.diference(conj2); //bB = ~B
		Conjunto idaTeste2 = bA.interseccao(bA); //~A i ~B

		if(idaTeste1.conjuntoPertence(idaTeste2) && idaTeste2.conjuntoPertence(idaTeste1))// verifica se ~(AuB) = ~A i ~B
			System.out.println("Ida provada!");

		//Volta da Lei de Morgan ~(AiB) = ~A u ~B, onde A = Conj1 , B = conj2, u = uniao e i = interseccao
		Conjunto i = conj1.interseccao(conj2); //AiB
		Conjunto voltaTeste1 = universo.diference(i);// ~(AiB)

		Conjunto voltaTeste2 = bA.uniao(bB); //~A u ~B

		if(voltaTeste2.conjuntoPertence(voltaTeste1) && voltaTeste1.conjuntoPertence(voltaTeste2))// verifica se ~(AiB) = ~A u ~B
			System.out.println("Volta provada!");
	}
}