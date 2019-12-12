package contador;


public class Contador {

    
    public static void main(String[] args) {
        
        Thread t1 = new Thread(new Cresce("t1"));
        Thread t2 = new Thread(new Cresce("t2"));
        decresce t3 = new decresce("t3");
        decresce t4 = new decresce("t4");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
    
}
