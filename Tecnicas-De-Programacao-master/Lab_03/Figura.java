abstract class Figura{
	int x;
	int y;

	Figura(){
		this(0,0);
	}
	
	Figura(int x, int y){
		this.x = x;
		this.y = y;
	}

    String desenhar(){
    	return this.toString(); // figura.getClass().getName() + '@' + Integer.toHexString(hashCode())
    }//Fim do método desenhar

    void mover(float dx, float dy){//metodo mover de todas as figuras
		this.x += dx;
		this.y += dy;    	
    }//Fim do método mover

    abstract double calcularArea(Figura f);//Fim do método calcular area;

}//Fim da classe figura