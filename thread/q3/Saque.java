package q3;

public class Saque {
    Conta conta;
    
    Saque(Conta conta){
        this.conta = conta;
    }
    
    boolean sacar(double valorSaque){
        double saldoAtual = conta.getSaldo();
        if(saldoAtual>=valorSaque){
            conta.setSaldo(saldoAtual-valorSaque);
            return true;
        }
        return false;
    }
}
