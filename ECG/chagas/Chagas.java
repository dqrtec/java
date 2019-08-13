
package chagas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import controller.*;


public class Chagas {
    
    static String caminhoPadrao = new String("C:\\Users\\Arida\\Desktop\\Demo ECG\\Chagas");
    
    public static void main(String[] args) {
        Connection connection = conexaoBancoRelacional();
        //Paciente p = new Paciente(connection);
        new ecg(connection);
        
        fecharConexao(connection);
    }
    
    
    
    static Connection conexaoBancoRelacional(){
        Connection conection = null;
        
        try{
            Class.forName("org.postgresql.Driver");
            conection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/relacional_ecg","postgres","admin");
        }catch(SQLException e) {
            System.out.println(" <--Erro no banco(conexao banco) "+e+" --> ");
	} catch (ClassNotFoundException ex) {
            System.out.println(" <--Erro no banco(classe nao encontrada) "+ex+"--> ");
        }
        return conection;
    }
    
    static private void fecharConexao(Connection conection){
        try {
            if(!conection.isClosed()) conection.close();
        } catch (SQLException ex) {
            System.err.println(" <--Erro no banco(fechar coneccao) "+ex+" --> ");
        }
    }
    
    
}
