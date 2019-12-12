package escalonador;

public class FilaCircular {
    public int quantidadeElementos = 0;
    int inicio = 0;
    int tamanho = 10;
    int fim = this.inicio;
    Object[] fila = new Object[tamanho];

    public FilaCircular() {}
    
    void adicionarElemento(Object elemento){
        
        if(fim == tamanho){
            fim = 0;
        }
        
        fila[fim] = elemento;
        
        quantidadeElementos++;
        fim+=1;
    }
    
    Object remover(){
        quantidadeElementos--;
        Object elemento = fila[inicio];
        inicio++;
        if (inicio == tamanho){
            inicio = 0;
        }
        return elemento;
    }
    
}