package q3;

public class Q3 {

    public static void main(String[] args) throws Exception{
        Conta ca = new Conta("conta A",15);
        Conta cb = new Conta("conta B", 50);
        
        ca.operacao(3, 5 , cb);
        ca.operacao(1, 5 , null);
    }
    
}
