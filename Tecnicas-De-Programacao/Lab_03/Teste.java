class Teste{
    public static void main(String[] args) {
    	Figura[] figuras = new Figura[10];

    	figuras[0] = new Quadrado(4,"verde");
        figuras[1] = new Circulo(5,5,5);
        figuras[2] = new Quadrado(4,"azul");
        figuras[3] = new Circulo(5,5,5);
    	figuras[4] = new Quadrado(4,"vermelho");
        figuras[5] = new Circulo(5,5,5);
        figuras[6] = new Quadrado(4,"amarelo");
        figuras[7] = new Circulo(5,5,5);
        figuras[8] = new Circulo(5,5,5);
        figuras[9] = new Quadrado(4,"cinza");
        
        for (Figura f: figuras){
        	if(f instanceof FiguraColorida){
        		System.out.println(f.desenhar());
        	}
        }

        
    }//Fim do método main
}//Fim da classe teste