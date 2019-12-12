import java.util.*;
public class CountWords{
	 //String texto = "Eu amo amo java";
	public static void main(String[] args) {
		int count = 0;

		Set words = new HashSet();
		
		Scanner teclado = new Scanner(System.in);

		while (teclado.hasNext()) {
			words.add(teclado.next());
			count++;
		}
		teclado.close();
		System.out.println("Quantidade Palavras = " + count);
		System.out.println("Palavras Distintas = " + words.size());
	}
	
}