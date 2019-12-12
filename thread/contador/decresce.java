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
public class decresce extends Thread{
        int numero = 100;
        String nome;

    public decresce(String nome) {
        this.nome = nome;
    }

    
        
        @Override
        public void run() {
            while (numero!=0){
                numero--;
                System.out.println(nome+": "+numero);
            }
        }
    }
