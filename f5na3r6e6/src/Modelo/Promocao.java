
package Modelo;


public class Promocao {
    private String desconto;
    private String limite;
    private int id_veiculo;

    public String getDesconto() {
        return desconto;
    }

    public void setDesconto(String desconto) {
        this.desconto = desconto;
    }

    public String getLimite() {
        return limite;
    }

    public void setLimite(String limite) {
        this.limite = limite;
    }

    public int getId_veiculo() {
        return id_veiculo;
    }

    public void setId_veiculo(int id_veiculo) {
        this.id_veiculo = id_veiculo;
    }

    public int getId_promocao() {
        return id_promocao;
    }

    public void setId_promocao(int id_promocao) {
        this.id_promocao = id_promocao;
    }
    private int id_promocao; 
}
