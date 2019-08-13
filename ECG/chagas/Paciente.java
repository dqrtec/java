package chagas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Statement;

public class Paciente {
    
    String nomePaciente;
    String naturalidadePaciente;
    String dataNascimento;
    String dataObito = null;
    int generoPaciente;
    int cpfPaciente = 123456789;
    
    Paciente(Connection conection){
        LerCSVPaciente("",conection);
        cadastrarPaciente(conection);
    }
    
    void LerCSVPaciente(String caminhoCsvArgumento,Connection conection){
        
        String nomeArquivoCSV = "pacientes.csv";
        String pathCSV = "C:\\Users\\Arida\\Desktop\\" + nomeArquivoCSV;
        
        BufferedReader br = null;
        String line = "";
        String csvSplitBy = ",";
        
        
        try{
           br = new BufferedReader(new FileReader(pathCSV));
           
           br.readLine();
           br.readLine(); //descatando os dois primeiros
           while((line = br.readLine()) != null){
               String[] pacients = line.split(csvSplitBy);
               nomePaciente = pacients[1];
               dataNascimento = pacients[4];
               dataNascimento = consertaFormatoData(dataNascimento);
               dataObito = pacients[3];
               dataObito = consertaFormatoData(dataObito);
               generoPaciente = Integer.parseInt(pacients[5]);
               naturalidadePaciente = pacients[6];
               
               System.out.println(" Nome: " + nomePaciente + " data-nasc: " + dataNascimento + " sexo:" + generoPaciente + " nat: " + naturalidadePaciente);
               
               if( !jaExisteEstePaciente(conection)){
                   cadastrarPaciente(conection);
               }
               // NAO EXCLUA ESSE COMENTARIO DE BAIXO
               //verificar de o paciente morreu
               if(! pacients[60].equals("0")){
                   cadastrarDataObito(conection);
               }
           }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    
    void cadastrarPaciente(Connection conection){
        try{
            PreparedStatement stm = conection.prepareStatement("INSERT INTO paciente (cpf, data_nasc, naturalidade, nome, genero_id) values (?,?,?,?,?)");
            stm.setInt(1, cpfPaciente);
            stm.setDate(2, java.sql.Date.valueOf(dataNascimento));
            stm.setString(3, naturalidadePaciente);
            stm.setString(4, nomePaciente);
            stm.setInt(5, generoPaciente);            

            stm.execute();
            stm.close();
        }catch(Exception e){
            System.err.println("Erro <inserção paciente>\n"+e);
        }
    }
    
    boolean jaExisteEstePaciente(Connection conection){
        String SQLconsulta = "SELECT nome FROM paciente where nome='"+nomePaciente+"'"+" and naturalidade ='"+naturalidadePaciente+"'";
        try{
            Statement stmt = conection.createStatement();
            ResultSet resultado = stmt.executeQuery(SQLconsulta);
            if (resultado.next()){
                return true;
            }
        }catch(Exception e){
            System.err.println("Erro <verificacao paciente existente>\n"+e);
        }
        return false;
    }
    
    String consertaFormatoData(String data){
        if(!data.equals("")){
            String[] partesTexto = data.split("/");
            return partesTexto[2]+"-"+partesTexto[1]+"-"+partesTexto[0];
        }
        return "";
    }
    
    void cadastrarDataObito(Connection conection){
        String SQLupdate = "UPDATE paciente SET data_obito = '"+dataObito+"' where nome='"+nomePaciente+"'"+" and naturalidade ='"+naturalidadePaciente+"'";
        try{
            Statement stmt = conection.createStatement();
            int resultado = stmt.executeUpdate(SQLupdate);
        }catch(Exception e){
            System.err.println("Erro <Cadastro Obito>\n"+e);
        }
    }
    
    
}


/*
    INSERT INTO public.genero(
	genero_id, genero_descricao)
	VALUES (1, 'masculino'),
	(0, 'feminino')
*/
