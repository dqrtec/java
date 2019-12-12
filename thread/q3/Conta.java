
package q3;

public class Conta {
    String nomeConta;
    double saldo;

    public Conta(String nomeConta, double saldo) {
        this.nomeConta = nomeConta;
        this.saldo = saldo;
    }
    
    synchronized void operacao(int tipoOperacao,double valor,Conta Destinatario) throws Exception{
        boolean ResultadoBoleanoOperacao = false;
        switch(tipoOperacao){
            case 1:
                System.out.println(" 1 ");
                Deposito deposito = new Deposito(this);
                ResultadoBoleanoOperacao = deposito.Depositar(valor);
                break;
            case 2:
                System.out.println(" 2 ");
                Saque saque = new Saque(this);
                ResultadoBoleanoOperacao = saque.sacar(valor);
                break;
            case 3:
                System.out.println(" 3 ");
                Thread.sleep(5000);
                Transferencia transferencia = new Transferencia(this, Destinatario);
                ResultadoBoleanoOperacao = transferencia.realizarTransacao(valor);
                break;
            
        }
        if(ResultadoBoleanoOperacao){
            System.out.println("Realizado com sucesso");
        }else{
            System.out.println("FALHA");
        }
    }

    public String getNomeConta() {
        return nomeConta;
    }

    public void setNomeConta(String nomeConta) {
        this.nomeConta = nomeConta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    
    
}
