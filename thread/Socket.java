package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;

public class Socket {

    public static void main(String[] args) throws IOException {
        ServerSocket sock = new ServerSocket(6013);
        
        while(true){
            java.net.Socket cliente = sock.accept();
            
            InputStream in = cliente.getInputStream();
            
            BufferedReader bin = new BufferedReader( new InputStreamReader(in));
            
            String line;
            
            while( (line= bin.readLine()) != null){
                if(line.equals("exit")){
                    System.out.println("Servidor encerrado devido erro inesperado");
                    break;
                }
                System.out.print(line);
            }
            
            cliente.close();
            break;
        }
    }
    
}
