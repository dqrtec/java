package q3;
public class Deposito {
    Conta conta;
    
    Deposito(Conta conta){
        this.conta = conta;
    }
    
    boolean Depositar(double valorDeposito){
        double saldoAtual = conta.getSaldo();
        conta.setSaldo(saldoAtual+valorDeposito);
        return true;
    }
}
