abstract class Operador extends Expressao {
     Expressao esquerda, direita;

     abstract int calcular ();

     void adicionar (Expressao esquerda, Expressao direita) {
       this.esquerda = esquerda;
       this.direita = direita; 
     }
}
