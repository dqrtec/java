class Operando extends Expressao {
   int valor;

   Operando (int valor) {
     this.valor = valor;
   }

   int calcular () {
      return valor;
   }

}
