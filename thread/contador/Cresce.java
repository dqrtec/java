/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contador;

/**
 *
 * @author 397595
 */
public class Cresce implements Runnable{
        int numero = 0;
        String nome;

    public Cresce(String nome) {
        this.nome = nome;
    }
        
        
        
        @Override
        public void run() {
            while (numero!=100){
                numero++;
                System.out.println(nome+": "+numero);
            }
        }
}
