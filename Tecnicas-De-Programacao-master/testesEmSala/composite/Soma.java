class Soma extends Operador{
    int calcular(){
        return this.esquerda.calcular() + this.direita.calcular();
    }

}
