import java.lang.Math;
class Circulo extends Figura{
	int diametro;

    Circulo(int diametro){
        this(0,0, diametro);
    }

	Circulo(int x, int y, int diametro){
        super(x,y);
        this.diametro = diametro;
	}

    double calcularArea(Figura f){
        return (Math.PI*(Math.pow(diametro/2,2)));
    }//Fim do método calcular area;

}//Fim da classe circulo