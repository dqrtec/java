
package corrida;


public class Corrida {


    public static void main(String[] args) {
        Corredor c1 = new Corredor("A", 10, 1);
        Corredor c2 = new Corredor("B", 10, 2);
        Corredor c3 = new Corredor("C", 10, 3);
        Corredor c4 = new Corredor("D", 10, 4);
        Corredor c5 = new Corredor("E", 10, 5);
        
        c1.start();
        c2.start();
        c3.start();
        c4.start();
        c5.start();
    }
    
}
