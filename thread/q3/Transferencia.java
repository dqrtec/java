package q3;

public class Transferencia {
    Conta contaEmissor, contaReceptor;

    public Transferencia(Conta contaEmissor, Conta contaReceptor) {
        this.contaEmissor = contaEmissor;
        this.contaReceptor = contaReceptor;
    }
    
    boolean realizarTransacao(double valorTransacao){
        if(contaEmissor.getSaldo()>=valorTransacao){
            contaReceptor.setSaldo(contaReceptor.getSaldo()+valorTransacao);
            contaEmissor.setSaldo(contaEmissor.getSaldo()-valorTransacao);
            return true;
        }
        return false;
    }
}
