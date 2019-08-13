
public class ondaRTdiretorio {
	
	public String[] criaDiretorio(String diretorio) {
		
		String nomeArquivo[] = new String[9];
		
		nomeArquivo[0] = "Pini_" + diretorio + "_Canal1";
		nomeArquivo[1] = "Ppeak_" + diretorio + "_Canal1";
		nomeArquivo[2] = "Pend_" + diretorio + "_Canal1";
		nomeArquivo[3] = "QRS_ON_" + diretorio;
		nomeArquivo[4] = "OndaR_" + diretorio + "_Canal1";
		nomeArquivo[5] = "QRS_OFF_" + diretorio;
		nomeArquivo[6] = "Tini_" + diretorio + "_Canal1";
		nomeArquivo[7] = "Tpeak_" + diretorio + "_Canal1";
		nomeArquivo[8] = "Tend_" + diretorio + "_Canal1";

		return nomeArquivo;
	}

}
