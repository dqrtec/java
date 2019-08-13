package com.example.demo;

import java.io.File;
import java.io.IOException;
import static java.lang.System.out;
import static java.lang.System.err;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.postgresql.*;

public class ExportSignalToDatabase {
	
    static int idUsuario;
    static int ultimoCicloCadastrado;
    static int minimo;

    public void mainECG(int id){//
        idUsuario = id;
        Connection conection = conexaoBancoRelacional();
        
        truncateBancoDados(conection);
        
       // String path = new String("C:\\Users\\Arida\\Desktop\\Demo ECG\\ECG\\Dados ECG\\teste\\");
         String path = new String("C:\\Users\\Arida\\Desktop\\ServerDemo\\exportData\\");
        String[] nomePastasEcgs = pegaNomePastas(path);
        String[] listaArquivos = new String[19];

        insereDadosBanco(conection, path, nomePastasEcgs, listaArquivos);

        fecharConexao(conection);
    }

    private static String[] criaDiretorio(String diretorio) {
        String nomeArquivo[] = new String[19];
		
        nomeArquivo[0] = diretorio + ".hea.txt";
        nomeArquivo[1] = "Sinal_" + diretorio + "_Canal1.txt";
        nomeArquivo[2] = "Pini_" + diretorio + "_Canal1.txt";
        nomeArquivo[3] = "Ppeak_" + diretorio + "_Canal1.txt";
        nomeArquivo[4] = "Pend_" + diretorio + "_Canal1.txt";
        nomeArquivo[5] = "QRS_ON_" + diretorio + ".txt";
        nomeArquivo[6] = "OndaR_" + diretorio + "_Canal1.txt";
        nomeArquivo[7] = "QRS_OFF_" + diretorio + ".txt";
        nomeArquivo[8] = "Tini_" + diretorio + "_Canal1.txt";
        nomeArquivo[9] = "Tpeak_" + diretorio + "_Canal1.txt";
        nomeArquivo[10] = "Tend_" + diretorio + "_Canal1.txt";
        nomeArquivo[11] = "Sinal_" + diretorio + "_Canal2.txt";
        nomeArquivo[12] = "Pini_" + diretorio + "_Canal2.txt";
        nomeArquivo[13] = "Ppeak_" + diretorio + "_Canal2.txt";
        nomeArquivo[14] = "Pend_" + diretorio + "_Canal2.txt";
        nomeArquivo[15] = "OndaR_" + diretorio + "_Canal2.txt";
        nomeArquivo[16] = "Tini_" + diretorio + "_Canal2.txt";
        nomeArquivo[17] = "Tpeak_" + diretorio + "_Canal2.txt";
        nomeArquivo[18] = "Tend_" + diretorio + "_Canal2.txt";

        return nomeArquivo;
    }
    
    private static String[] criaDiretorio2(String diretorio) {
        String nomeArquivo[] = new String[11];
		
        nomeArquivo[0] = diretorio + ".hea.txt";
        nomeArquivo[1] = "Canal_1_Exame_"+diretorio+".txt";
        nomeArquivo[2] = "Inicio_P_" + diretorio + "Canal1.txt";
        nomeArquivo[3] = "Pico_P_" + diretorio + "Canal1.txt";
        nomeArquivo[4] = "Fim_P_" + diretorio + "Canal1.txt";
        nomeArquivo[5] = "Inicio_QRS_" + diretorio + ".txt";
        nomeArquivo[6] = "Picos_R_" + diretorio + "Canal1.txt";
        nomeArquivo[7] = "Fim_QRS_" + diretorio + ".txt";
        nomeArquivo[8] = "Inicio_T_" + diretorio + "Canal1.txt";
        nomeArquivo[9] = "Pico_T_" + diretorio + "Canal1.txt";
        nomeArquivo[10] = "Fim_T_" + diretorio + "Canal1.txt";

        return nomeArquivo;
    }

    private static String[] criaDiretorio3(String diretorio) {
        String nomeArquivo[] = new String[11];
        nomeArquivo[0] = diretorio + ".hea";
        nomeArquivo[1] = "Exame_"+diretorio+".txt";
        nomeArquivo[2] = "Inicio_P_" + diretorio + ".txt";
        nomeArquivo[3] = "Pico_P_" + diretorio + ".txt";
        nomeArquivo[4] = "Fim_P_" + diretorio + ".txt";
        nomeArquivo[5] = "Inicio_QRS_" + diretorio + ".txt";
        nomeArquivo[6] = "Picos_R_" + diretorio + ".txt";
        nomeArquivo[7] = "Fim_QRS_" + diretorio + ".txt";
        nomeArquivo[8] = "Inicio_T_" + diretorio + ".txt";
        nomeArquivo[9] = "Pico_T_" + diretorio + ".txt";
        nomeArquivo[10] = "Fim_T_" + diretorio + ".txt";
        return nomeArquivo;
    }

    private static void inserePaciente(String variavelEcg, String path, String[] listaArquivos, Connection conection){
        int remedio_id = 0;
        
	try{
            byte[] txt = Files.readAllBytes(Paths.get(path + variavelEcg + "\\" + listaArquivos[0]));
            String texto = new String(txt, "UTF-8");
            String[] linhas = texto.split("\n");


            for(String linha : linhas) {
                if(linha.substring(0,1).equals("#")) {

                    String[] dadosDaLinha = linha.split(" ");
                    String info1 = dadosDaLinha[1];
                    String info2 = dadosDaLinha[2];

                    if(info1.matches("[0-9]+")) {

                        String idade = dadosDaLinha[1];
                        String sexo = dadosDaLinha[2];

                        PreparedStatement stm = conection.prepareStatement("insert into paciente (paciente_idade, paciente_sexo,paciente_id) values (?,?,?) RETURNING paciente_id");
                        stm.setInt(1, Integer.parseInt(idade));
                        stm.setString(2, sexo);
                        stm.setInt(3, idUsuario);

                        ResultSet rs = stm.executeQuery();

                        while (rs.next()) {
                            rs.getInt(1);//paciente_id = rs.getInt(1);
                        }

                        rs.close();
                        stm.close();

                    }else {
                        for(String medicamento : dadosDaLinha) {
                            if(!medicamento.equals("#")) {

                                if(medicamento.indexOf(",") != -1) {
                                    medicamento = medicamento.substring(0, medicamento.length() - 1);
                                }

                                PreparedStatement stm = conection.prepareStatement("insert into remedio (remedio_descricao) values (?) RETURNING remedio_id");
                                stm.setString(1, medicamento);

                                ResultSet rs = stm.executeQuery();

                                while (rs.next()) {
                                    remedio_id = rs.getInt(1);
                                }
                                rs.close();
                                stm.close();

                                PreparedStatement stm2 = conection.prepareStatement("insert into receituario (remedio_id, paciente_id) values (?,?)");
                                stm2.setInt(1, remedio_id);
                                stm2.setInt(2, idUsuario);
                                stm2.execute();
                                stm2.close();  
                            }
                        }
                    }
                }
            }

        }catch(Exception e){
            err.print(" <--Erro insere paciente--> ");
        }
    }

    private static void insereOndaP(String variavelEcg, String path, String[] listaArquivos, String[] ecgCompleto, Integer ecg_id, Connection conection,String separador){
        
        try{
            String[] amplitudeIni;
            String[] amplitudePico;
            String[] amplitudeFim;

            byte[] txt1 = Files.readAllBytes(Paths.get(path + variavelEcg + "\\" + listaArquivos[2]));
            String texto1 = new String(txt1, "UTF-8");
            String[] linhas1 = texto1.split( separador);

            byte[] txt2 = Files.readAllBytes(Paths.get(path + variavelEcg + "\\" + listaArquivos[3]));
            String texto2 = new String(txt2, "UTF-8");
            String[] linhas2 = texto2.split( separador);
            
            byte[] txt3 = Files.readAllBytes(Paths.get(path + variavelEcg + "\\" + listaArquivos[4]));
            String texto3 = new String(txt3, "UTF-8");
            String[] linhas3 = texto3.split( separador);

            amplitudeIni = new String[linhas1.length];
            amplitudePico = new String[linhas1.length];
            amplitudeFim = new String[linhas1.length];
            
            
            int j = 0;
            while(j <= minimo){
                
                amplitudeIni[j] = ecgCompleto[Integer.parseInt(linhas1[j])];
                amplitudePico[j] = ecgCompleto[Integer.parseInt(linhas2[j])];
                amplitudeFim[j] = ecgCompleto[Integer.parseInt(linhas3[j])];
                PreparedStatement stm = conection.prepareStatement("insert into p_onda (p_ini, p_pico, p_fim, p_ini_amp, p_pico_amp, p_fim_amp, ecg_id, ciclo_id) values (?,?,?,?,?,?,?,?)");
                stm.setInt(1, Integer.parseInt(linhas1[j]));
                stm.setInt(2, Integer.parseInt(linhas2[j]));
                stm.setInt(3, Integer.parseInt(linhas3[j]));
                stm.setFloat(4, Float.parseFloat(amplitudeIni[j]));
                stm.setFloat(5, Float.parseFloat(amplitudePico[j]));
                stm.setFloat(6, Float.parseFloat(amplitudeFim[j]));
                stm.setInt(7, ecg_id);
                stm.setInt(8, ultimoCicloCadastrado - linhas1.length + j + 1);
                stm.execute();
                stm.close();		
                j++;
            }
        }catch(Exception e){
            err.print(" <--Erro insere onda p--> -->");
        }
    }

    private static void insereOndaQRS(String variavelEcg, String path, String[] listaArquivos, String[] ecgCompleto, Integer ecg_id, Connection conection,String separador){
        try{
            String[] amplitudeIni;
            String[] amplitudePico;
            String[] amplitudeFim;
            
            byte[] txt1 = Files.readAllBytes(Paths.get(path + variavelEcg + "\\" + listaArquivos[5]));
            String texto1 = new String(txt1, "UTF-8");
            String[] linhas1 = texto1.split( separador);

            byte[] txt2 = Files.readAllBytes(Paths.get(path + variavelEcg + "\\" + listaArquivos[6]));
            String texto2 = new String(txt2, "UTF-8");
            String[] linhas2 = texto2.split( separador);

            byte[] txt3 = Files.readAllBytes(Paths.get(path + variavelEcg + "\\" + listaArquivos[7]));
            String texto3 = new String(txt3, "UTF-8");
            String[] linhas3 = texto3.split( separador);

            amplitudeIni = new String[linhas1.length];
            amplitudePico = new String[linhas1.length];
            amplitudeFim = new String[linhas1.length];

            int j = 0;
            while(j++ <= minimo){
                amplitudeIni[j] = ecgCompleto[Integer.parseInt(linhas1[j])];
                amplitudePico[j] = ecgCompleto[Integer.parseInt(linhas2[j])];
                amplitudeFim[j] = ecgCompleto[Integer.parseInt(linhas3[j])];
                PreparedStatement stm = conection.prepareStatement("insert into qrs_onda (qrs_ini, qrs_pico, qrs_fim, qrs_ini_amp, qrs_pico_amp, qrs_fim_amp, ecg_id, ciclo_id) values (?,?,?,?,?,?,?,?)");
                stm.setInt(1, Integer.parseInt(linhas1[j]));
                stm.setInt(2, Integer.parseInt(linhas2[j]));
                stm.setInt(3, Integer.parseInt(linhas3[j]));
                stm.setFloat(4, Float.parseFloat(amplitudeIni[j]));
                stm.setFloat(5, Float.parseFloat(amplitudePico[j]));
                stm.setFloat(6, Float.parseFloat(amplitudeFim[j]));
                stm.setInt(7, ecg_id);
                stm.setInt(8,ultimoCicloCadastrado - linhas1.length + j + 1);
                stm.execute();
                stm.close();	
            }

        }catch(Exception e){
            err.print(" <--Erro insere onda qrs--> ");
        }
    }

    private static void insereOndaT(String variavelEcg, String path, String[] listaArquivos, String[] ecgCompleto, Integer ecg_id, Connection conection,String separador){
        try{
            String[] amplitudeIni;
            String[] amplitudePico;
            String[] amplitudeFim;

            byte[] txt1 = Files.readAllBytes(Paths.get(path + variavelEcg + "\\" + listaArquivos[8]));
            String texto1 = new String(txt1, "UTF-8");
            String[] linhas1 = texto1.split( separador);

            byte[] txt2 = Files.readAllBytes(Paths.get(path + variavelEcg + "\\" + listaArquivos[9]));
            String texto2 = new String(txt2, "UTF-8");
            String[] linhas2 = texto2.split( separador);

            byte[] txt3 = Files.readAllBytes(Paths.get(path + variavelEcg + "\\" + listaArquivos[10]));
            String texto3 = new String(txt3, "UTF-8");
            String[] linhas3 = texto3.split( separador);

            amplitudeIni = new String[linhas1.length];
            amplitudePico = new String[linhas1.length];
            amplitudeFim = new String[linhas1.length];
            
            int j = 0;
            while(j++ <= minimo){

                amplitudeIni[j] = ecgCompleto[Integer.parseInt(linhas1[j])];
                amplitudePico[j] = ecgCompleto[Integer.parseInt(linhas2[j])];
                amplitudeFim[j] = ecgCompleto[Integer.parseInt(linhas3[j])];
                PreparedStatement stm = conection.prepareStatement("insert into t_onda (t_ini, t_pico, t_fim, t_ini_amp, t_pico_amp, t_fim_amp, ecg_id, ciclo_id) values (?,?,?,?,?,?,?,?)");
                stm.setInt(1, Integer.parseInt(linhas1[j]));
                stm.setInt(2, Integer.parseInt(linhas2[j]));
                stm.setInt(3, Integer.parseInt(linhas3[j]));
                stm.setFloat(4, Float.parseFloat(amplitudeIni[j]));
                stm.setFloat(5, Float.parseFloat(amplitudePico[j]));
                stm.setFloat(6, Float.parseFloat(amplitudeFim[j]));
                stm.setInt(7, ecg_id);
                stm.setInt(8, ultimoCicloCadastrado - linhas1.length + j + 1);
                stm.execute();
                stm.close();   
            }
        }catch(Exception e){
            err.print(" <--Erro insere onda t--> ");
        }
    }

    private static Integer insereOndaBlob(String variavelEcg, String path, String[] listaArquivos,Connection conection, Integer pacienteId, String canal){
        Integer ecg_id = 0;
        try{
            byte[] txt = null;
            if(canal == "canal1") {
                txt = Files.readAllBytes(Paths.get(path + variavelEcg + "\\" + listaArquivos[1]));
            }else {
                txt = Files.readAllBytes(Paths.get(path + variavelEcg + "\\" + listaArquivos[11]));
            }
            PreparedStatement stm = conection.prepareStatement("insert into ecg (ecg_byte, paciente_id) values (?,?) RETURNING ecg_id");
            stm.setBytes(1, txt);
            stm.setInt(2, pacienteId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                ecg_id = rs.getInt(1);
            }
            rs.close();
            stm.close();
        }catch(Exception e){
            err.print(" <--Erro inserir onda blob--> ");
        }
	return ecg_id;
    }

    private static String[] lerSinalCompleto(String variavelEcg, String path, String[] listaArquivos, String canal,String separador){
        String[] linhas = null;
	try{	
            if(canal == "canal1") {
                byte[] txt = Files.readAllBytes(Paths.get(path + variavelEcg + "\\" + listaArquivos[1]));
                String texto = new String(txt, "UTF-8");
                linhas = texto.split(separador);
            }else {
                byte[] txt = Files.readAllBytes(Paths.get(path + variavelEcg + "\\" + listaArquivos[11]));
                String texto = new String(txt, "UTF-8");
                linhas = texto.split(separador);
            }
        }catch(Exception e){
            err.print(" <--Erro ler sinal completo--> ");
        }
		
	return linhas;
    }

    private static void insereCiclo(Integer idEcg, Integer canal_id, String variavelEcg , String path, String[] listaArquivos, Connection conection,String separador){//(Integer idEcg, Integer canal_id, String variavelEcg, String[] ecgCompletoCanal1, String[] ecgCompletoCanal2 , String path, String[] listaArquivos, Connection conection) throws IOException, SQLException{
        try{
            byte[] txt1 = Files.readAllBytes(Paths.get(path + variavelEcg + "\\" + listaArquivos[2]));
            String texto1 = new String(txt1, "UTF-8");
            String[] linhas1 = texto1.split(separador);

            Integer ciclo_id = 0;
            out.println("cliclos inseridos -> "+linhas1.length);
            for (int j = 0; j < linhas1.length ; j++) {
                PreparedStatement stm = conection.prepareStatement("insert into ciclo_completo (ecg_id, canal_id) values (?,?) RETURNING ciclo_id");
                stm.setInt(1, idEcg);
                stm.setInt(2, canal_id);

                ResultSet rs = stm.executeQuery();
                //stm.execute();
                while (rs.next()) {
                    ultimoCicloCadastrado = rs.getInt(1);
                }
                rs.close();
                stm.close();
            }
        }catch(Exception e){
            err.print(" <--Erro insercao ciclo --> ");
        }
    }

    private static int insereCanal(Integer idEcg, Connection conection,String exame){
        Integer canal_id = 0;
        try{
            PreparedStatement stm = conection.prepareStatement("insert into canal(descricao) values (?) RETURNING canal_id");
            stm.setString(1, ("canal1 ecg "+idEcg + "exame "+exame));

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                canal_id = rs.getInt(1);
            }
            rs.close();
            stm.close();
        }catch(Exception e){
            err.print(" <--Erro insercao canal--> ");
        }
        return canal_id;
    }

    private static int numeroCiclos(String variavelEcg, String path, String[] listaArquivos, String[] ecgCompleto, Integer ecg_id, Connection conection,String separador) throws IOException{
	byte[] txt1 = Files.readAllBytes(Paths.get(path + variavelEcg + "/" + listaArquivos[8]));
	String texto1 = new String(txt1, "UTF-8");
	String[] linhas1 = texto1.split(separador);
        return linhas1.length;
    }
    
    private void truncateBancoDados(Connection conection){
        String todosTruncate = "truncate table canal cascade;truncate table canal_ecg cascade;truncate table ciclo_completo cascade;truncate table diagnostico cascade;truncate table diagnostico_ecg cascade;truncate table ecg cascade;truncate table p_onda cascade;truncate table paciente cascade;truncate table qrs_onda cascade;truncate table receituario cascade;truncate table remedio cascade;truncate table t_onda cascade";
        String[] cadaTruncate = todosTruncate.split(";");
        
        for (String truncateAtual : cadaTruncate) {
            truncate(truncateAtual, conection);
        }
    }
    
    private void truncate(String sql,Connection conection){
        try{
            Statement stm = conection.createStatement();
            stm.execute(sql);
        }catch(Exception e){
            err.print(" <--Erro truncate(truncate "+ sql +") "+e+"--> ");
        }
    }
    
    private Connection conexaoBancoRelacional(){
        Connection conection = null;
        
        try{
            Class.forName("org.postgresql.Driver");
            conection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/relacional_ecg","postgres","admin");
        }catch(SQLException e) {
            System.out.println(" <--Erro no banco(conexao banco) "+e+" --> ");
	} catch (ClassNotFoundException ex) {
            System.out.println(" <--Erro no banco(classe nao encontrada) "+ex+"--> ");
            Logger.getLogger(ExportSignalToDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conection;
    }
    
    private void fecharConexao(Connection conection){
        try {
            if(!conection.isClosed()) conection.close();
        } catch (SQLException ex) {
            System.err.println(" <--Erro no banco(fechar coneccao) "+ex+" --> ");
            Logger.getLogger(ExportSignalToDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private String[] pegaNomePastas(String path){
	File folder = new File(path);
	File[] listOfFiles = folder.listFiles();
	String[] ecgs = new String[listOfFiles.length];
        
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isDirectory()) {
                ecgs[i] = listOfFiles[i].getName();
            }
	}
        return ecgs;
    }
    
    private void insereDadosBanco(Connection conection, String path, String[] nomePastasEcgs, String[] listaArquivos){
	Integer EcgId;
        String separador = ";";
        for (int i = 0; i < nomePastasEcgs.length; i++) {
            idUsuario =(int) Integer.parseInt(nomePastasEcgs[i]);
            listaArquivos = criaDiretorio3(nomePastasEcgs[i]);
            
            try {contaOndas(listaArquivos,path,nomePastasEcgs[i],separador);} catch (IOException ex) {Logger.getLogger(ExportSignalToDatabase.class.getName()).log(Level.SEVERE, null, ex);}
            
            inserePaciente(nomePastasEcgs[i], path, listaArquivos, conection);
            String[] ecgCompletoCanal1 = lerSinalCompleto(nomePastasEcgs[i], path, listaArquivos, "canal1",separador);
            EcgId = insereOndaBlob(nomePastasEcgs[i], path, listaArquivos, conection, idUsuario, "canal1");
            Integer canalId = insereCanal(EcgId, conection,nomePastasEcgs[i]);
            //insereCanal_ECG(EcgId,canalId,conection);
            insereCiclo(EcgId, canalId,  nomePastasEcgs[i] ,  path, listaArquivos, conection,separador);
            insereOndaP(nomePastasEcgs[i], path, listaArquivos, ecgCompletoCanal1,EcgId, conection,separador);
            insereOndaQRS(nomePastasEcgs[i], path, listaArquivos, ecgCompletoCanal1,EcgId, conection,separador);
            insereOndaT(nomePastasEcgs[i], path, listaArquivos, ecgCompletoCanal1,EcgId, conection,separador);
	}
    }
    
    void contaOndas(String[] listaArquivos, String path, String variavelEcg, String separador) throws IOException{
            byte[] txt1 = Files.readAllBytes(Paths.get(path + variavelEcg + "\\" + listaArquivos[2]));
            String texto1 = new String(txt1, "UTF-8");
            out.println("\nondas do exame > "+variavelEcg);
            out.println(texto1);
            String[] linhas1 = texto1.split(separador);

            byte[] txt2 = Files.readAllBytes(Paths.get(path + variavelEcg + "\\" + listaArquivos[5]));
            String texto2 = new String(txt2, "UTF-8");
            String[] linhas2 = texto2.split(separador);

            byte[] txt3 = Files.readAllBytes(Paths.get(path + variavelEcg + "\\" + listaArquivos[8]));
            String texto3 = new String(txt3, "UTF-8");
            String[] linhas3 = texto3.split(separador);
            
            int[] menor = new int[3];
            menor[0]=linhas1.length;
            menor[1]=linhas2.length;
            menor[1]=linhas3.length;
            
            if(menor[0]<menor[1] && menor[0]<menor[2]){minimo=menor[0];}
            if(menor[1]<menor[0] && menor[1]<menor[2]){minimo=menor[1];}
            if(menor[2]<menor[1] && menor[2]<menor[0]){minimo=menor[2];}
            minimo = menor[0];
            out.println("MINIMO =  "+minimo);
            out.println("existem ondas P =  "+linhas1.length);
            out.println("existem ondas qrs =  "+linhas2.length);
            out.println("existem ondas t =  "+linhas3.length);
    }
    
    private static void insereCanal_ECG(Integer ecg_id,int canal_id, Connection conection){
        
        PreparedStatement stm;
        try {
            stm = conection.prepareStatement("insert into canal_ecg (ecg_id,canal_id) values (?,?)");
                stm.setInt(1, ecg_id);
                stm.setInt(2, canal_id);
                stm.executeQuery();
                out.println("55");
        } catch (Exception ex) {
            out.println("<--Erro insere Canal_Ecg -->");
        }
    }    
    
}