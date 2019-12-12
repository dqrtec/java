import java.lang.Math;
class Quadrado extends FiguraColorida{
	int lado; //tamanho dos lados do quadrado;

	Quadrado(int lado, String cor){
		this(0,0,lado, cor);
	}
	
	Quadrado(int x, int y, int lado, String cor){
		super(x, y, cor);
		this.lado = lado;
	}

    double calcularArea(Figura f){
    	return Math.pow(lado,2);
    }//Fim do método calcular area;

}//Fim da classe Quadrado