
package corrida;

import java.util.logging.Level;
import java.util.logging.Logger;


public class Corredor extends Thread{
    String nome;
    int percurso;
    int descanso;

    public Corredor(String nome, int percurso, int descanso) {
        this.nome = nome;
        this.percurso = percurso;
        this.descanso = descanso;
    }
    
    @Override
    public void run(){
        while(percurso!=0){
            try {
                if(Math.random()>0.8){
                    System.out.println("Corredor "+nome+" está descansando");
                    sleep(descanso*1000);
                }
                System.out.println("Faltam "+ percurso-- +" metros para o corredor "+nome);
            } catch (InterruptedException ex) {
                Logger.getLogger(Corredor.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("O corredor "+nome+" chegou");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
    }
}
