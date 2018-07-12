abstract class FiguraColorida extends Figura{
	String cor;

	FiguraColorida(String cor){
		this(0,0,cor);
	}

	FiguraColorida(int x, int y, String cor){
		super(x,y);
		this.cor = cor;
	}

	String desenhar(){
    	return this.toString() + " Cor: " + this.cor; // figura.getClass().getName() + '@' + Integer.toHexString(hashCode())
    }//Fim do método desenhar

	String desenhar(String cor){
    	return this.toString() + " Cor: " + cor; // figura.getClass().getName() + '@' + Integer.toHexString(hashCode())
    }//Fim do método desenhar
	
}