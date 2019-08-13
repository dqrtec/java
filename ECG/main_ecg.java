package bd;

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
import org.postgresql.*;

public class Main {
    static int ciclos=1;
    static int ultimoCicloCadastrado=0;

    public void mainECG() throws ClassNotFoundException{
        
        try {
			
            Class.forName("org.postgresql.Driver");
            Connection conection = null;
			
            try {
                conection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/relacional_ecg","postgres","admin");                
                
		String[] ecgs;
		String[] listaArquivos;
                
		String path = new String("C:\\Users\\Arida\\Desktop\\Demo ECG\\ECG\\Dados ECG\\web\\"); 
		Integer idPaciente = 0;
		Integer idEcg = 0;                  

		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		ecgs = new String[listOfFiles.length];
		listaArquivos = new String[listOfFiles.length*11];

		for (int i = 0; i < listOfFiles.length; i++) {
                    if (listOfFiles[i].isDirectory()) {
                    ecgs[i] = listOfFiles[i].getName();
                    }
		}
				
		for (int i = 0; i < ecgs.length; i++) {
                    System.err.println("interação ="+i);
                    listaArquivos = criaDiretorio(ecgs[i]);
                    idPaciente = inserePaciente(ecgs[i], path, listaArquivos, conection);
                    String[] ecgCompletoCanal1 = lerSinalCompleto(ecgs[i], path, listaArquivos, "canal1");
//String[] ecgCompleto2Canal2 = lerSinalCompleto(ecgs[i], path, listaArquivos, "canal2");
                    idEcg = insereOndaBlob(ecgs[i], path, listaArquivos, conection, idPaciente, "canal1");
//idEcg = insereOndaBlob(ecgs[i], path, listaArquivos, conection, idPaciente, "canal2");
                                        
                    int canal_id = insereCanal(idEcg, conection);
                    insereCiclo(idEcg, new Integer(canal_id),  ecgs[i] ,  path, listaArquivos, conection);
                    
                    insereOndaP(ecgs[i], path, listaArquivos, ecgCompletoCanal1,idEcg, conection);
                    insereOndaQRS(ecgs[i], path, listaArquivos, ecgCompletoCanal1,idEcg, conection);
                    insereOndaT(ecgs[i], path, listaArquivos, ecgCompletoCanal1,idEcg, conection);
                    
//int numeroCiclosAtual = numeroCiclos(ecgs[i], path, listaArquivos, ecgCompletoCanal1,idEcg, conection);
//ciclos = ciclos + numeroCiclosAtual ;
                    truncateBancoDados(conection);
					
		}
            }catch(Exception e) {
                System.out.println(e);
				
            }finally {
                if(!conection.isClosed())
		conection.close();
            }
	}catch(SQLException e) {
            System.out.println("erro no banco");
	}
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

    private static Integer inserePaciente(String variavelEcg, String path, String[] listaArquivos, Connection conection) throws IOException, SQLException{
		
        int paciente_id = 0;
	int remedio_id = 0;
	byte[] txt = Files.readAllBytes(Paths.get(path + variavelEcg + "/" + listaArquivos[0]));
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

                    PreparedStatement stm = conection.prepareStatement("insert into paciente (paciente_idade, paciente_sexo) values (?,?) RETURNING paciente_id");
                    stm.setInt(1, Integer.parseInt(idade));
                    stm.setString(2, sexo);

                    ResultSet rs = stm.executeQuery();

                    while (rs.next()) {
                        paciente_id = rs.getInt(1);
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
                            stm2.setInt(2, paciente_id);
                            stm2.execute();
                            stm2.close();  
			}
                    }
                }
            }
	}
	return paciente_id;
    }

    private static void insereOndaP(String variavelEcg, String path, String[] listaArquivos, String[] ecgCompleto, Integer ecg_id, Connection conection) throws IOException, SQLException{
        
        String[] amplitudeIni;
	String[] amplitudePico;
	String[] amplitudeFim;
			
	byte[] txt1 = Files.readAllBytes(Paths.get(path + variavelEcg + "/" + listaArquivos[2]));
	String texto1 = new String(txt1, "UTF-8");
	String[] linhas1 = texto1.split(",");
		
	byte[] txt2 = Files.readAllBytes(Paths.get(path + variavelEcg + "/" + listaArquivos[3]));
	String texto2 = new String(txt2, "UTF-8");
	String[] linhas2 = texto2.split(",");
		
	byte[] txt3 = Files.readAllBytes(Paths.get(path + variavelEcg + "/" + listaArquivos[4]));
	String texto3 = new String(txt3, "UTF-8");
	String[] linhas3 = texto3.split(",");
		
	amplitudeIni = new String[linhas1.length];
	amplitudePico = new String[linhas1.length];
	amplitudeFim = new String[linhas1.length];
        
	for (int j = 0; j < linhas1.length - 1; j++) {
            
            out.println(" onda p = "+j);
			
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
	}
    }

    private static void insereOndaQRS(String variavelEcg, String path, String[] listaArquivos, String[] ecgCompleto, Integer ecg_id, Connection conection) throws IOException, SQLException{
        String[] amplitudeIni;
	String[] amplitudePico;
	String[] amplitudeFim;
		
	byte[] txt1 = Files.readAllBytes(Paths.get(path + variavelEcg + "/" + listaArquivos[5]));
	String texto1 = new String(txt1, "UTF-8");
	String[] linhas1 = texto1.split(",");
		
	byte[] txt2 = Files.readAllBytes(Paths.get(path + variavelEcg + "/" + listaArquivos[6]));
	String texto2 = new String(txt2, "UTF-8");
	String[] linhas2 = texto2.split(",");
		
	byte[] txt3 = Files.readAllBytes(Paths.get(path + variavelEcg + "/" + listaArquivos[7]));
	String texto3 = new String(txt3, "UTF-8");
	String[] linhas3 = texto3.split(",");
		
	amplitudeIni = new String[linhas1.length];
	amplitudePico = new String[linhas1.length];
	amplitudeFim = new String[linhas1.length];
	for (int j = 1; j < linhas1.length - 1; j++) {
			
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
            stm.setInt(8, ultimoCicloCadastrado - linhas1.length + j);
            stm.execute();
            stm.close();	
	}
    }

    private static void insereOndaT(String variavelEcg, String path, String[] listaArquivos, String[] ecgCompleto, Integer ecg_id, Connection conection) throws IOException, SQLException{
        String[] amplitudeIni;
	String[] amplitudePico;
	String[] amplitudeFim;
	
	byte[] txt1 = Files.readAllBytes(Paths.get(path + variavelEcg + "/" + listaArquivos[8]));
	String texto1 = new String(txt1, "UTF-8");
	String[] linhas1 = texto1.split(",");
	
	byte[] txt2 = Files.readAllBytes(Paths.get(path + variavelEcg + "/" + listaArquivos[9]));
	String texto2 = new String(txt2, "UTF-8");
	String[] linhas2 = texto2.split(",");
	
	byte[] txt3 = Files.readAllBytes(Paths.get(path + variavelEcg + "/" + listaArquivos[10]));
	String texto3 = new String(txt3, "UTF-8");
	String[] linhas3 = texto3.split(",");
	
	amplitudeIni = new String[linhas1.length];
	amplitudePico = new String[linhas1.length];
	amplitudeFim = new String[linhas1.length];
	for (int j = 1; j < linhas1.length; j++) {
		
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
            stm.setInt(8, ultimoCicloCadastrado - linhas1.length +j);
            stm.execute();
            stm.close();
            
	}
    }

    private static Integer insereOndaBlob(String variavelEcg, String path, String[] listaArquivos,Connection conection, Integer pacienteId, String canal) throws IOException, SQLException{
        Integer ecg_id = 0;
	byte[] txt = null;
	if(canal == "canal1") {
            txt = Files.readAllBytes(Paths.get(path + variavelEcg + "/" + listaArquivos[1]));
	}else {
            txt = Files.readAllBytes(Paths.get(path + variavelEcg + "/" + listaArquivos[11]));
	}
	PreparedStatement stm = conection.prepareStatement("insert into ecg (ecg_byte, paciente_id) values (?,?) RETURNING ecg_id");
	stm.setBytes(1, txt);
	stm.setInt(2, pacienteId);
	ResultSet rs = stm.executeQuery();
	//stm.execute();
	while (rs.next()) {
            ecg_id = rs.getInt(1);
	}
	rs.close();
	stm.close();
	//stm.execute();
        System.err.println(ecg_id);
	return ecg_id;
    }

    private static String[] lerSinalCompleto(String variavelEcg, String path, String[] listaArquivos, String canal) throws IOException{
		
        String[] linhas;
		
        if(canal == "canal1") {
	
            byte[] txt = Files.readAllBytes(Paths.get(path + variavelEcg + "/" + listaArquivos[1]));
	
            String texto = new String(txt, "UTF-8");
	
            linhas = texto.split(",");
	}else {
	
            byte[] txt = Files.readAllBytes(Paths.get(path + variavelEcg + "/" + listaArquivos[11]));
	
            String texto = new String(txt, "UTF-8");
	
            linhas = texto.split(",");
	}
		
	return linhas;
    }

    private static void insereCiclo(Integer idEcg, Integer canal_id, String variavelEcg , String path, String[] listaArquivos, Connection conection) throws IOException, SQLException{//(Integer idEcg, Integer canal_id, String variavelEcg, String[] ecgCompletoCanal1, String[] ecgCompletoCanal2 , String path, String[] listaArquivos, Connection conection) throws IOException, SQLException{
        byte[] txt1 = Files.readAllBytes(Paths.get(path + variavelEcg + "/" + listaArquivos[2]));
	String texto1 = new String(txt1, "UTF-8");
	String[] linhas1 = texto1.split(",");
		
	Integer ciclo_id = 0;
		
	for (int j = 1; j < linhas1.length - 1; j++) {
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
		
    }

    private static int insereCanal(Integer idEcg, Connection conection) throws IOException, SQLException{
        PreparedStatement stm = conection.prepareStatement("insert into canal(descricao) values (?) RETURNING canal_id");
	stm.setString(1, ("canal1 ecg "+idEcg));
                        
	ResultSet rs = stm.executeQuery();
        Integer canal_id = 0;
                        
        while (rs.next()) {
            canal_id = rs.getInt(1);
	}
	rs.close();
	stm.close();
        
        return canal_id;
    }

    private static int numeroCiclos(String variavelEcg, String path, String[] listaArquivos, String[] ecgCompleto, Integer ecg_id, Connection conection) throws IOException{
	
	byte[] txt1 = Files.readAllBytes(Paths.get(path + variavelEcg + "/" + listaArquivos[8]));
	String texto1 = new String(txt1, "UTF-8");
	String[] linhas1 = texto1.split(",");
        return linhas1.length;
    }
    
    
    private void truncateBancoDados(Connection conection){

        String comandoSql = "truncate table ecg cascade";
        truncate(comandoSql, conection);
        
        comandoSql = "truncate table canal cascade";
        truncate(comandoSql, conection);
        
        comandoSql = "truncate table canal_ecg cascade";
        truncate(comandoSql, conection);
        
        comandoSql = "truncate table ciclo_completo cascade";
        truncate(comandoSql, conection);
        
        comandoSql = "truncate table diagnostico cascade";
        truncate(comandoSql, conection);
        
        comandoSql = "truncate table diagnostico_ecg cascade";
        truncate(comandoSql, conection);
        
        comandoSql = "truncate table ecg cascade";
        truncate(comandoSql, conection);
        
        comandoSql = "truncate table p_onda cascade";
        truncate(comandoSql, conection);
        
        comandoSql = "truncate table paciente cascade";
        truncate(comandoSql, conection);
        
        comandoSql = "truncate table qrs_onda cascade";
        truncate(comandoSql, conection);
        
        comandoSql = "truncate table receituario cascade";
        truncate(comandoSql, conection);
        
        comandoSql = "truncate table remedio cascade";
        truncate(comandoSql, conection);
        
        comandoSql = "truncate table t_onda cascade";
        truncate(comandoSql, conection);
        
    }
    
    private void truncate(String sql,Connection conection){
        try{
            Statement stm = conection.createStatement();
            stm.execute(sql);
        }catch(Exception e){
            err.print(" deu erro no truncate ");
        }
    }
    
}
