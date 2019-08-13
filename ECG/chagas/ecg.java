package chagas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.System.err;
import static java.lang.System.out;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ecg {
    
    String [] Inicio_P;
    String [] Inicio_QRS;
    String [] Inicio_T;
    String [] Pico_P;
    String [] Pico_T;
    String [] Picos_R;
    String [] Fim_P;
    String [] Fim_QRS;
    String [] Fim_T;
    
    String [] sinalCompleto;
    
    int ecg_id;
    int canal_id = 1;
    int numeroCiclos;
    
    String nomePaciente;
    int idPaciente;
    String instituicao;
    int idInstituicao;
    int prontuario;
    String[] Pat = new String[42];
    
    String ecg_file ;
    float imc ;
    String marcapasso ;
    int pressao_sistolica ;
    String cancer ;
    int pressao_diastolica ;
    String tabagismo ;
    String alcoolismo ;
    String sincope ;
    String has ;
    String sedentarismo ;
    String data_hora ;
    String fibrilacao_flutter ;
    String avc ;
    int numero_canais = 1 ;
    
    String pastaRaiz = new String("C:\\Users\\Arida\\Desktop\\LerEstesArquivos");
    String[] nomePastasEcgs = pegaNomePastas(pastaRaiz);
    String[] listaArquivos = especificaNomeArquivos(nomePastasEcgs[0]);
    
    ecg(Connection conection){
        
        BufferedReader br = null;
        String line = "";
        try{
            br = new BufferedReader(new FileReader(pastaRaiz+"\\"+nomePastasEcgs[0]+"\\"+nomePastasEcgs[0]+".PAT"));
           
            for(int i =0;i<Pat.length;i++){
                Pat[i] = br.readLine();
            }
           
            nomePaciente = Pat[9] +" "+Pat[8];
            idPaciente = retornarPac_id(conection);
           
            instituicao = Pat[36];
            idInstituicao = retornIdInstituicao(conection);
           
            prontuario = Integer.parseInt( Pat[12] );
            
            //cadastrarInstituicaoPaciente(conection);

            String nomeArquivoCSV = "pacientes.csv";
            String pathCSV = "C:\\Users\\Arida\\Desktop\\" + nomeArquivoCSV;

            line = "";
            String csvSplitBy = ",";
        
            try{
                br = new BufferedReader(new FileReader(pathCSV));

                br.readLine();
                br.readLine(); //descatando os dois primeiros
                while((line = br.readLine()) != null){
                    String[] pacients = line.split(csvSplitBy); 
                     
                    data_hora  = pacients[3];
                    data_hora = consertaFormatoData(data_hora);
                    String[] dataEspacada = data_hora.split("-");
                    
                    if(
                        ( dataEspacada[0].equals( Pat[7] ) ) &&
                        ( dataEspacada[1].equals( Pat[5] ) ) &&
                        ( dataEspacada[2].equals( Pat[6] ) ) &&
                        ( pacients[2].equals( Pat[12] ) )
                    ){
                        ecg_file = pastaRaiz ;
                        imc  = Float.parseFloat(pacients[7]);
                        marcapasso  = pacients[12];
                        //pressao_sistolica  = Integer.parseInt(pacients[7]);
                        cancer  = pacients[8];
                        //pressao_diastolica  = Integer.parseInt(pacients[7]);
                        tabagismo  = pacients[23];
                        alcoolismo  = pacients[24];
                        sincope  = pacients[13];
                        has  = pacients[9];
                        sedentarismo  = pacients[25];
                        fibrilacao_flutter  = pacients[14];
                        avc  = pacients[20];
                        
                        ecg_id = cadastrarEcg(conection);
                        break;
                    }
                }
               
                cadastroCanalEcg(conection,ecg_id, 1);
               
                colocarTodosArquivosEmMemoria();
                numeroCiclos = quantidadeCiclosECG();
                
                for(int i = 0; i<numeroCiclos ;i++){
                    cadastroCiclo(conection,i,ecg_id, canal_id);
                    cadastroOndaP(conection,i,ecg_id, canal_id);
                    cadastroOndaQRS(conection,i,ecg_id, canal_id);
                    cadastroOndaT(conection, i, ecg_id, canal_id);
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

        } catch (Exception e) {
            System.err.println("Erro <Ler arquivo PAT>");
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

    private static String[] especificaNomeArquivos(String diretorio) {
        String nomeArquivo[] = new String[9];
	
        nomeArquivo[0] = "Inicio_P_Parametros_ECG_File"+diretorio+"dat_seg1.txt.txt";
        nomeArquivo[1] = "Inicio_QRS_Parametros_ECG_File"+diretorio+"dat_seg1.txt.txt";
        nomeArquivo[2] = "Inicio_T_Parametros_ECG_File"+diretorio+"dat_seg1.txt.txt";
        nomeArquivo[3] = "Pico_P_Parametros_ECG_File"+diretorio+"dat_seg1.txt.txt";
        nomeArquivo[4] = "Pico_T_Parametros_ECG_File"+diretorio+"dat_seg1.txt.txt";
        nomeArquivo[5] = "Picos_R_Parametros_ECG_File"+diretorio+"dat_seg1.txt.txt";
        nomeArquivo[6] = "Fim_P_Parametros_ECG_File"+diretorio+"dat_seg1.txt.txt";
        nomeArquivo[7] = "Fim_QRS_Parametros_ECG_File"+diretorio+"dat_seg1.txt.txt";
        nomeArquivo[8] = "Fim_T_Parametros_ECG_File"+diretorio+"dat_seg1.txt.txt";

        return nomeArquivo;
    }
    
    void cadastrarInstituicaoPaciente(Connection conection){
        try {
            String cadastroSQL_Inst_Pac = "INSERT INTO instituicao_paciente (pac_id,inst_pac_id,id_paciente_instituicao) VALUES(?,?,?)";
            PreparedStatement pstmt = conection.prepareStatement(cadastroSQL_Inst_Pac);
            pstmt.setInt(1, idPaciente);
            pstmt.setInt(2, idInstituicao);
            pstmt.setInt(3, prontuario);
            
            pstmt.execute();
            pstmt.close();
        } catch (Exception e) {
            System.err.println(e);
            System.err.println("Erro <Cadastro PAC_INST>");
        }
    }
    
    int cadastrarEcg(Connection conection){
        try {
            String cadastroSQL_ECG = "INSERT INTO ecg (pac_id ,ecg_file ,imc ,marcapasso ,\n" +
                                                            "pressao_sistolica ,cancer ,pressao_diastolica ,\n" +
                                                            "tabagismo ,alcoolismo ,sincope ,has ,sedentarismo ,\n" +
                                                            "data_hora ,fibrilacao_flutter ,avc ,numero_canais) "
                                            + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) returning ecg_id";
            PreparedStatement pstmt = conection.prepareStatement(cadastroSQL_ECG);
            pstmt.setInt(1, idPaciente);
            pstmt.setString(2, pastaRaiz);
            pstmt.setFloat(3, imc);
            pstmt.setObject(4, marcapasso, java.sql.Types.CHAR);
            pstmt.setInt(5, pressao_sistolica);
            pstmt.setObject(6, cancer, java.sql.Types.CHAR);
            pstmt.setInt(7, pressao_diastolica);
            pstmt.setObject(8, tabagismo, java.sql.Types.CHAR);
            pstmt.setObject(9, alcoolismo, java.sql.Types.CHAR);
            pstmt.setObject(10, sincope, java.sql.Types.CHAR);
            pstmt.setObject(11, has, java.sql.Types.CHAR);
            pstmt.setObject(12, sedentarismo, java.sql.Types.CHAR);
            pstmt.setDate(13, java.sql.Date.valueOf(data_hora));
            pstmt.setObject(14, fibrilacao_flutter, java.sql.Types.CHAR);
            pstmt.setObject(15, avc, java.sql.Types.CHAR);
            pstmt.setInt(16, numero_canais);
            
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            int id = rs.getInt(1);
            pstmt.close();
            return id;
        } catch (Exception e) {
            System.err.println(e);
            System.err.println("Erro <Cadastro ECG>");
            System.err.println();
        }
        return 0;
    }
    
    int retornarPac_id(Connection conection){
        try{
            int pac_id ;
            Statement stmt = conection.createStatement();
            String consultSQLPaciente = "SELECT pac_id FROM paciente WHERE unaccent(nome) ilike unaccent('%"+nomePaciente+"%')";
            ResultSet rs = stmt.executeQuery(consultSQLPaciente);
            rs.next();
            pac_id = rs.getInt("pac_id");
            return pac_id;
        }catch(Exception e){
            System.err.println(e);
            System.out.println("Erro <Retorna Paciente>");
        }
        
        return 0;
    }
    
    int retornIdInstituicao(Connection conection){
        try{
            int inst_orig_id;
            Statement stmt = conection.createStatement();
            String consultSQLPaciente = "SELECT inst_orig_id FROM instituicao_origem WHERE descricao like '%"+instituicao+"%'";
            ResultSet rs = stmt.executeQuery(consultSQLPaciente);
            rs.next();
            inst_orig_id = rs.getInt("inst_orig_id");
            return inst_orig_id;
        }catch(Exception e){
            try{
                int inst_orig_id;
                Statement stmt = conection.createStatement();
                String consultSQLPaciente = "INSERT INTO instituicao_origem (descricao) VALUES ('"+instituicao+"') RETURNING inst_orig_id ";
                ResultSet rs = stmt.executeQuery(consultSQLPaciente);
                rs.next();
                inst_orig_id = rs.getInt("inst_orig_id");
                return inst_orig_id;
            }catch(Exception erro){
                System.err.println(e);
                System.err.println(erro);
                System.out.println("Erro <Retorna instituicao>");
            }
        }
        
        return 0;
    }
    
    String consertaFormatoData(String data){
        if(!data.equals("")){
            String[] partesTexto = data.split("/");
            return partesTexto[2]+"-"+partesTexto[1]+"-"+partesTexto[0];
        }
        return "";
    }
    
    void cadastroCanalEcg(Connection conection, int ecg_id, int canal_id){
        try {
            String cadastroSQL_Inst_Pac = "INSERT INTO canal_ecg (ecg_id,canal_id) VALUES(?,?)";
            PreparedStatement pstmt = conection.prepareStatement(cadastroSQL_Inst_Pac);
            pstmt.setInt(1, ecg_id);
            pstmt.setInt(2, canal_id);
            
            pstmt.execute();
            pstmt.close();
        } catch (Exception e) {
            System.err.println(e);
            System.err.println("Erro <Cadastro CANAL_ECG>");
        }
    }
    
    void cadastroCiclo(Connection conection,int i, int ecg_id, int canal_id){
        try {
            float segundoDeOcorrenciaCiclo = instanteSegundoCiclo(i);
            int periodo_id = retornaIdPeriodoSobreInstanteCiclo( segundoDeOcorrenciaCiclo );
            String cadastroSQL_Inst_Pac = "INSERT INTO ciclo (ciclo_id,ecg_id,canal_id,per_id,instante_ciclo_ecg) VALUES(?,?,?,?,?)";
            PreparedStatement pstmt = conection.prepareStatement(cadastroSQL_Inst_Pac);
            pstmt.setInt(1, i );
            pstmt.setInt(2, ecg_id );
            pstmt.setInt(3, canal_id );
            pstmt.setInt(4, periodo_id );
            pstmt.setFloat(5, segundoDeOcorrenciaCiclo );
            
            pstmt.execute();
            pstmt.close();
        } catch (Exception e) {
            System.err.println(e);
            System.err.println("Erro <Cadastro CICLO>");
        }
    }
    
    void cadastroOndaP(Connection conection,int i, int ecg_id, int canal_id){
        try{
            
                float amplitudeInicio = Float.parseFloat( sinalCompleto[Integer.parseInt(Inicio_P[i])] );
                float amplitudePico = Float.parseFloat( sinalCompleto[Integer.parseInt(Pico_P[i])] );
                float amplitudeFim = Float.parseFloat( sinalCompleto[Integer.parseInt(Fim_P[i])] );
                
                PreparedStatement stm = conection.prepareStatement(""
                        + "insert into p_onda "
                        + "(p_id,ciclo_id,ecg_id,canal_id,"
                        + "p_onset,p_offset,p_peak,p_onset_amp,"
                        + "p_offset_amp,p_peak_amp) "
                        + "values (?,?,?,?,?,?,?,?,?,?)");
                stm.setInt(1, i);
                stm.setInt(2, i);
                stm.setInt(3, ecg_id);
                stm.setInt(4, canal_id);
                stm.setInt(5, Integer.parseInt( Inicio_P[i] ));
                stm.setInt(6, Integer.parseInt( Fim_P[i] ));
                stm.setInt(7, Integer.parseInt( Pico_P[i] ));
                stm.setFloat(8, amplitudeInicio );
                stm.setFloat(9, amplitudeFim );
                stm.setFloat(10, amplitudePico );
                
                stm.execute();
                stm.close();		
        }catch(Exception e){
            err.println(e);
            err.println(i);
            err.println("Erro <cadastro Onda P>");
        }
    }
    
    void cadastroOndaQRS(Connection conection,int i, int ecg_id, int canal_id){
        try{
            
                float amplitudeInicio = Float.parseFloat( sinalCompleto[Integer.parseInt(Inicio_QRS[i])] );
                float amplitudePico = Float.parseFloat( sinalCompleto[Integer.parseInt(Picos_R[i])] );
                float amplitudeFim = Float.parseFloat( sinalCompleto[Integer.parseInt(Fim_QRS[i])] );
                
                PreparedStatement stm = conection.prepareStatement(""
                        + "insert into qrs_onda "
                        + "(qrs_id,ciclo_id,ecg_id,canal_id,"
                        + "qrs_onset,qrs_offset,qrs_peak,qrs_onset_amp,"
                        + "qrs_offset_amp,qrs_peak_amp) "
                        + "values (?,?,?,?,?,?,?,?,?,?)");
                stm.setInt(1, i);
                stm.setInt(2, i);
                stm.setInt(3, ecg_id);
                stm.setInt(4, canal_id);
                stm.setInt(5, Integer.parseInt( Inicio_QRS[i] ));
                stm.setInt(6, Integer.parseInt( Fim_QRS[i] ));
                stm.setInt(7, Integer.parseInt( Picos_R[i] ));
                stm.setFloat(8, amplitudeInicio );
                stm.setFloat(9, amplitudeFim );
                stm.setFloat(10, amplitudePico );
                
                stm.execute();
                stm.close();		
        }catch(Exception e){
            err.println(e);
            err.println(i);
            err.println("Erro <cadastro Onda QRS>");
        }
    }
    
    void cadastroOndaT(Connection conection,int i, int ecg_id, int canal_id){
        try{
            
                float amplitudeInicio = Float.parseFloat( sinalCompleto[Integer.parseInt(Inicio_T[i])] );
                float amplitudePico = Float.parseFloat( sinalCompleto[Integer.parseInt(Pico_T[i])] );
                float amplitudeFim = Float.parseFloat( sinalCompleto[Integer.parseInt(Fim_T[i])] );
                
                PreparedStatement stm = conection.prepareStatement(""
                        + "insert into t_onda "
                        + "(t_id,ciclo_id,ecg_id,canal_id,"
                        + "t_onset,t_offset,t_peak,t_onset_amp,"
                        + "t_offset_amp,t_peak_amp) "
                        + "values (?,?,?,?,?,?,?,?,?,?)");
                stm.setInt(1, i);
                stm.setInt(2, i);
                stm.setInt(3, ecg_id);
                stm.setInt(4, canal_id);
                stm.setInt(5, Integer.parseInt( Inicio_T[i] ));
                stm.setInt(6, Integer.parseInt( Fim_T[i] ));
                stm.setInt(7, Integer.parseInt( Pico_T[i] ));
                stm.setFloat(8, amplitudeInicio );
                stm.setFloat(9, amplitudeFim );
                stm.setFloat(10, amplitudePico );
                
                stm.execute();
                stm.close();		
        }catch(Exception e){
            err.println(e);
            err.println(i);
            err.println("Erro <cadastro Onda T>");
        }
    }
    
    int quantidadeCiclosECG(){
        return Inicio_QRS.length;
    }
    
    void colocarTodosArquivosEmMemoria(){

            byte[] txt;
            String texto;
            try{
                txt = Files.readAllBytes(Paths.get(pastaRaiz + "\\" + nomePastasEcgs[0] + "\\" + listaArquivos[0]));
                texto = new String(txt, "UTF-8");
                Inicio_P = texto.split(";");

                txt = Files.readAllBytes(Paths.get(pastaRaiz + "\\" + nomePastasEcgs[0] + "\\" + listaArquivos[1]));
                texto = new String(txt, "UTF-8");
                Inicio_QRS = texto.split(";");

                txt = Files.readAllBytes(Paths.get(pastaRaiz + "\\" + nomePastasEcgs[0] + "\\" + listaArquivos[2]));
                texto = new String(txt, "UTF-8");
                Inicio_T = texto.split(";");

                txt = Files.readAllBytes(Paths.get(pastaRaiz + "\\" + nomePastasEcgs[0] + "\\" + listaArquivos[3]));
                texto = new String(txt, "UTF-8");
                Pico_P = texto.split(";");

                txt = Files.readAllBytes(Paths.get(pastaRaiz + "\\" + nomePastasEcgs[0] + "\\" + listaArquivos[4]));
                texto = new String(txt, "UTF-8");
                Pico_T = texto.split(";");

                txt = Files.readAllBytes(Paths.get(pastaRaiz + "\\" + nomePastasEcgs[0] + "\\" + listaArquivos[5]));
                texto = new String(txt, "UTF-8");
                Picos_R = texto.split(";");

                txt = Files.readAllBytes(Paths.get(pastaRaiz + "\\" + nomePastasEcgs[0] + "\\" + listaArquivos[6]));
                texto = new String(txt, "UTF-8");
                Fim_P = texto.split(";");

                txt = Files.readAllBytes(Paths.get(pastaRaiz + "\\" + nomePastasEcgs[0] + "\\" + listaArquivos[7]));
                texto = new String(txt, "UTF-8");
                Fim_QRS = texto.split(";");

                txt = Files.readAllBytes(Paths.get(pastaRaiz + "\\" + nomePastasEcgs[0] + "\\" + listaArquivos[8]));
                texto = new String(txt, "UTF-8");
                Fim_T = texto.split(";");

                txt = Files.readAllBytes(Paths.get(pastaRaiz + "\\" + nomePastasEcgs[0] + "\\" + "Exame_Parametros_ECG_File"+ nomePastasEcgs[0] +"dat_seg1.txt.txt" ));
                texto = new String(txt, "UTF-8");
                sinalCompleto = texto.split(";");
                
                System.out.println(Inicio_P.length);
                System.out.println(Fim_P.length);
                System.out.println(Pico_P.length);
            }catch(Exception e){
                System.out.println("Erro <Todos Arquivos na Memoria>");
            }

    }
    
    float instanteSegundoCiclo(int numeroCiclo){
        return Float.parseFloat( Picos_R[numeroCiclo] ) / 128;
    }
    
    int retornaIdPeriodoSobreInstanteCiclo(float instanteCiclo){
        if(instanteCiclo < 14400) return 1;
        if(instanteCiclo < 36000) return 2;
        if(instanteCiclo < 57600) return 3;
        if(instanteCiclo < 79200) return 4;
        return 5;
    }
}
