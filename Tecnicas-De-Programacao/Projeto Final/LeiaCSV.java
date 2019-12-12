import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LeiaCSV{
	public static void main(String[] args) {
		LeiaCSV obj = new LeiaCSV;
		obj.run();
	}


	public void run(){
		String arquivoCSV = "arquivo.csv";
		BufferedReader br = null;
		String linha = "";
		String csvDivisor = ",";
		try{
			br = mew BufferedReader(new FileReader(arquivoCSV));
			while( (linha = br.readLine()) != null){
				String[] colunas = line.split(",");
			}
		}
	}
}