import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		try {
			
			
			Class.forName("org.postgresql.Driver");
			Connection conection = null;
			
			try {
				conection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ecg","postgres","mnmn0819"); //conexao banco de dados (endereço , user , pass)
				String[] ecgs;
				String[] listaArquivos;
				
				String path = new String("C:\\Users\\dougl\\Desktop\\DadosECG\\"); //diretorio dos arquivos de ecg
				Integer idPaciente = 0;
				Integer idEcg = 0;
				
				File folder = new File(path);///////// whats?? ^\(-_-)/^
				File[] listOfFiles = folder.listFiles();///////// whats?? ^\(-_-)/^
				ecgs = new String[listOfFiles.length];//cria lista de texto com o numero de arquivos no path
				listaArquivos = new String[listOfFiles.length*11];//cria lista de texto com o numero de arquivos no path  //11 pois são 11 arquivos de ecg
		
				for (int i = 0; i < listOfFiles.length; i++) { // define o diretório de cada ecg
					if (listOfFiles[i].isDirectory()) {
						ecgs[i] = listOfFiles[i].getName();
					}
				}
				
				
				for (int i = 0; i < ecgs.length; i++) {
					listaArquivos = criaDiretorio(ecgs[i]);//ln 67
					
					idPaciente = inserePaciente(ecgs[i], path, listaArquivos, conection);// (nome do arquivo , raiz_diretorio , lista_nome_arquivos , conexao)
					String[] ecgCompleto = lerSinalCompleto(ecgs[i], path, listaArquivos); // (nome do arquivo , raiz_diretorio , lista_nome_arquivos)  //retorna todos os dados do exame de ecg em uma lista
					idEcg = insereOndaBlob(ecgs[i], path, listaArquivos, conection, idPaciente);// insere o ecg todo no banco e o relaciona com o paciente
					insereOndaP(ecgs[i], path, listaArquivos, ecgCompleto,idEcg, conection);
					insereOndaQRS(ecgs[i], path, listaArquivos, ecgCompleto,idEcg, conection);
					insereOndaT(ecgs[i], path, listaArquivos, ecgCompleto,idEcg, conection);
					
				}
			}catch(Exception e) {
				
			}finally {
				if(!conection.isClosed())
					conection.close();
			}
		 }catch(SQLException e) {
			//.... 
		}
		    
		    
			
	}

	private static String[] criaDiretorio(String diretorio) {
		String nomeArquivo[] = new String[11];
		
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

		return nomeArquivo;
	}
	
	private static Integer inserePaciente(String variavelEcg, String path, String[] listaArquivos, Connection conection) throws IOException, SQLException{// (nome do arquivo , raiz_diretorio , lista_nome_arquivos , conexao)
		
		int paciente_id = 0;
		int remedio_id = 0;
		byte[] txt = Files.readAllBytes(Paths.get(path + variavelEcg + "\\" + listaArquivos[0])); // ler o arquivo head de um determinado ecg  // para que o byte[] ?? ///////// whats?? ^\(-_-)/^
		String texto = new String(txt, "UTF-8"); // coloca no padrao utf-8
		
		String[] linhas = texto.split("\n");
		
		for(String linha : linhas) { // Mesmo que o python?? ///////// whats?? ^\(-_-)/^
			
			if(linha.substring(0,1).equals("#")) { // Não sei o que faz ///////// whats?? ^\(-_-)/^
				
				String[] dadosDaLinha = linha.split(" ");
				String info1 = dadosDaLinha[1];
				String info2 = dadosDaLinha[2];
				
				if(info1.matches("[0-9]+")) { // EOQ????  ///////// whats?? ^\(-_-)/^
					
					String idade = dadosDaLinha[1];
					String sexo = dadosDaLinha[2];
					
					PreparedStatement stm = conection.prepareStatement("insert into paciente (paciente_idade, paciente_sexo) values (?,?) RETURNING paciente_id"); // insere o paciente no banco
					stm.setInt(1, Integer.parseInt(idade));
					stm.setString(2, sexo);
					
					ResultSet rs = stm.executeQuery();
					//stm.execute();
					while (rs.next()) { //EOQ??  ///////// whats?? ^\(-_-)/^
					    paciente_id = rs.getInt(1);
						
					}
					//stm.execute();
					rs.close();
					stm.close();
					
				}else {
					System.out.println("Dados Medicamento");
					for(String medicamento : dadosDaLinha) {
						if(!medicamento.equals("#")) {
							
							if(medicamento.indexOf(",") != -1) { // o que e index off ///////// whats?? ^\(-_-)/^
								medicamento = medicamento.substring(0, medicamento.length() - 1);
							}
							
							PreparedStatement stm = conection.prepareStatement("insert into remedio (remedio_descricao) values (?) RETURNING remedio_id");
							stm.setString(1, medicamento);

							ResultSet rs = stm.executeQuery();
							//stm.execute();
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
		
		
		byte[] txt1 = Files.readAllBytes(Paths.get(path + variavelEcg + "\\" + listaArquivos[2]));
		String texto1 = new String(txt1, "UTF-8");
		String[] linhas1 = texto1.split(",");
		
		byte[] txt2 = Files.readAllBytes(Paths.get(path + variavelEcg + "\\" + listaArquivos[3]));
		String texto2 = new String(txt2, "UTF-8");
		String[] linhas2 = texto2.split(",");
		
		byte[] txt3 = Files.readAllBytes(Paths.get(path + variavelEcg + "\\" + listaArquivos[4]));
		String texto3 = new String(txt3, "UTF-8");
		String[] linhas3 = texto3.split(",");

		int quantidade_onda_p = linhas1.length; // daniel
		
		amplitudeIni = new String[quantidade_onda_p];
		amplitudePico = new String[quantidade_onda_p];
		amplitudeFim = new String[quantidade_onda_p];
		for (int j = 0; j < quantidade_onda_p; j++) {
			
			amplitudeIni[j] = ecgCompleto[Integer.parseInt(linhas1[j])];
			amplitudePico[j] = ecgCompleto[Integer.parseInt(linhas2[j])];
			amplitudeFim[j] = ecgCompleto[Integer.parseInt(linhas3[j])];
			PreparedStatement stm = conection.prepareStatement("insert into p_onda (p_ini, p_pico, p_fim, p_ini_amp, p_pico_amp, p_fim_amp, ecg_id) values (?,?,?,?,?,?,?)");
			stm.setInt(1, Integer.parseInt(linhas1[j]));
			stm.setInt(2, Integer.parseInt(linhas2[j]));
			stm.setInt(3, Integer.parseInt(linhas3[j]));
			stm.setFloat(4, Float.parseFloat(amplitudeIni[j]));
			stm.setFloat(5, Float.parseFloat(amplitudePico[j]));
			stm.setFloat(6, Float.parseFloat(amplitudeFim[j]));
			stm.setInt(7, ecg_id);
			stm.execute();
			stm.close();
			
		}
	}
	
	private static void insereOndaQRS(String variavelEcg, String path, String[] listaArquivos, String[] ecgCompleto, Integer ecg_id, Connection conection) throws IOException, SQLException{
		String[] amplitudeIni;
		String[] amplitudePico;
		String[] amplitudeFim;
		
		
		byte[] txt1 = Files.readAllBytes(Paths.get(path + variavelEcg + "\\" + listaArquivos[5]));
		String texto1 = new String(txt1, "UTF-8");
		String[] linhas1 = texto1.split(",");
		
		byte[] txt2 = Files.readAllBytes(Paths.get(path + variavelEcg + "\\" + listaArquivos[6]));
		String texto2 = new String(txt2, "UTF-8");
		String[] linhas2 = texto2.split(",");
		
		byte[] txt3 = Files.readAllBytes(Paths.get(path + variavelEcg + "\\" + listaArquivos[7]));
		String texto3 = new String(txt3, "UTF-8");
		String[] linhas3 = texto3.split(",");
		
		amplitudeIni = new String[linhas1.length];
		amplitudePico = new String[linhas1.length];
		amplitudeFim = new String[linhas1.length];
		for (int j = 0; j < linhas1.length; j++) {
			
			amplitudeIni[j] = ecgCompleto[Integer.parseInt(linhas1[j])];
			amplitudePico[j] = ecgCompleto[Integer.parseInt(linhas2[j])];
			amplitudeFim[j] = ecgCompleto[Integer.parseInt(linhas3[j])];
			PreparedStatement stm = conection.prepareStatement("insert into qrs_onda (qrs_ini, qrs_pico, qrs_fim, qrs_ini_amp, qrs_pico_amp, qrs_fim_amp, ecg_id) values (?,?,?,?,?,?,?)");
			stm.setInt(1, Integer.parseInt(linhas1[j]));
			stm.setInt(2, Integer.parseInt(linhas2[j]));
			stm.setInt(3, Integer.parseInt(linhas3[j]));
			stm.setFloat(4, Float.parseFloat(amplitudeIni[j]));
			stm.setFloat(5, Float.parseFloat(amplitudePico[j]));
			stm.setFloat(6, Float.parseFloat(amplitudeFim[j]));
			stm.setInt(7, ecg_id);
			stm.execute();
			stm.close();
			
		}
	}
	
	private static void insereOndaT(String variavelEcg, String path, String[] listaArquivos, String[] ecgCompleto, Integer ecg_id, Connection conection) throws IOException, SQLException{
		String[] amplitudeIni;
		String[] amplitudePico;
		String[] amplitudeFim;
		
		
		byte[] txt1 = Files.readAllBytes(Paths.get(path + variavelEcg + "\\" + listaArquivos[8]));
		String texto1 = new String(txt1, "UTF-8");
		String[] linhas1 = texto1.split(",");
		
		byte[] txt2 = Files.readAllBytes(Paths.get(path + variavelEcg + "\\" + listaArquivos[9]));
		String texto2 = new String(txt2, "UTF-8");
		String[] linhas2 = texto2.split(",");
		
		byte[] txt3 = Files.readAllBytes(Paths.get(path + variavelEcg + "\\" + listaArquivos[10]));
		String texto3 = new String(txt3, "UTF-8");
		String[] linhas3 = texto3.split(",");
		
		amplitudeIni = new String[linhas1.length];
		amplitudePico = new String[linhas1.length];
		amplitudeFim = new String[linhas1.length];
		for (int j = 0; j < linhas1.length; j++) {
			
			amplitudeIni[j] = ecgCompleto[Integer.parseInt(linhas1[j])];
			amplitudePico[j] = ecgCompleto[Integer.parseInt(linhas2[j])];
			amplitudeFim[j] = ecgCompleto[Integer.parseInt(linhas3[j])];
			PreparedStatement stm = conection.prepareStatement("insert into t_onda (t_ini, t_pico, t_fim, t_ini_amp, t_pico_amp, t_fim_amp, ecg_id) values (?,?,?,?,?,?,?)");
			stm.setInt(1, Integer.parseInt(linhas1[j]));
			stm.setInt(2, Integer.parseInt(linhas2[j]));
			stm.setInt(3, Integer.parseInt(linhas3[j]));
			stm.setFloat(4, Float.parseFloat(amplitudeIni[j]));
			stm.setFloat(5, Float.parseFloat(amplitudePico[j]));
			stm.setFloat(6, Float.parseFloat(amplitudeFim[j]));
			stm.setInt(7, ecg_id);
			stm.execute();
			stm.close();
			
		}
	}
	
	private static Integer insereOndaBlob(String variavelEcg, String path, String[] listaArquivos,Connection conection, Integer pacienteId) throws IOException, SQLException{
		Integer ecg_id = 0;
		byte[] txt = Files.readAllBytes(Paths.get(path + variavelEcg + "\\" + listaArquivos[1]));
		PreparedStatement stm = conection.prepareStatement("insert into ecg (ecg_dado, paciente_id) values (?,?) RETURNING ecg_id");
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
		return ecg_id;
	}
	private static String[] lerSinalCompleto(String variavelEcg, String path, String[] listaArquivos) throws IOException{
		
		byte[] txt = Files.readAllBytes(Paths.get(path + variavelEcg + "\\" + listaArquivos[1]));
		String texto = new String(txt, "UTF-8");
		String[] linhas = texto.split(",");
		return linhas;
	}
}
