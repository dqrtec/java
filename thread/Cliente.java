package cliente;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JOptionPane;

public class Cliente {

    public static void main(String[] args) throws IOException {
        Socket sock = new Socket("127.0.0.1",6013);
        
        PrintWriter pout = new PrintWriter(sock.getOutputStream(),true);
        /*
        pout.println("mensagem 1");
        pout.println("mensagem 2");
        pout.println("mensagem 3");
        pout.println("exit");
        pout.println("mensagem 4");
        */
        String enviar;
        do{
            enviar = JOptionPane.showInputDialog("digite o texto");
            pout.println(enviar);
            System.out.println(sock.isBound());
            System.out.println(sock.isClosed());
            System.out.println(sock.isConnected());
            System.out.println(sock.isInputShutdown());
            System.out.println(sock.isOutputShutdown());
            System.out.println("----------------");
        }while(sock.isConnected());
        
        
        JOptionPane.showMessageDialog(null, "MENSAGEM DE ERRO IDENTIFICADA");
        
        sock.close();
    }
    
}
