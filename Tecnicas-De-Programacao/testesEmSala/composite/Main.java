class Main {

   public static void main (String arg[]) {
     Expressao op1, op2, op3, op4, op5;

     op1 = new Operando(2);
     op2 = new Diferenca();
     op3 = new Multiplicacao();
     op4 = new Operando(1);
     op5 = new Operando(3);

     op3.adicionar(op1, op2);     
     op2.adicionar(op4, op5);     

     System.out.println(op3.calcular());

   }

}
