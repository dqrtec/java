package escalonador;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Contador extends Thread{
    int numero;
    int inicio = 0;

    public Contador(int numero) {
        this.numero = numero;
    }
    
    @Override
    public void run(){
        while (numero > inicio++) {
            try{Thread.sleep(5);}catch (Exception e){System.out.println(e);}
            System.out.println(inicio);
        }
        System.out.println("______-----Terminou------______");
    }       
}