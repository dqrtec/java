package escalonador;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Escalonador {

    public static void main(String[] args) throws Exception{
        int quantum = (int) (Math.random()*400)+100;
        System.out.println("quantum = "+quantum+" ms");

        
        // Cria lista aleatoria
        FilaCircular fila = new FilaCircular();
        
        Set<Integer> jaEscolhidos; 
        jaEscolhidos = new HashSet<Integer>();
        Random rand = new Random();
        int numero;
        
        while(fila.quantidadeElementos != 5){
            numero = rand.nextInt(100) % 5 ;
            if(jaEscolhidos.contains(numero)){
                continue;
            }else{
                Contador contador = new Contador((numero+1)*100);
                fila.adicionarElemento(contador);
                jaEscolhidos.add(numero);
            }
        }
        
//        RR(fila,quantum);
        FCFS_NPreempitivo(fila);
        
        
    }
    
    static void FCFS_NPreempitivo(FilaCircular fila) throws Exception{
        Contador e;
        
        do{
            e = (Contador) fila.remover();
            e.start();
            while(e.isAlive()){
                continue;
            }
            
        }while(fila.quantidadeElementos != 0);
    }
    
    static void RR(FilaCircular fila, int quantum) throws Exception{
        Contador e;
        while(fila.quantidadeElementos != 0){
            e = (Contador) fila.remover();
            if(e.isAlive()){
                e.resume();
            }else{
                e.start();
            }
            
            Thread.sleep(quantum);
            e.yield();
        }
    }
}