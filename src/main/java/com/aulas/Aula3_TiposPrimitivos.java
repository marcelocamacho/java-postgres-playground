package com.aulas;
public class Aula3_TiposPrimitivos {
    public static void main (String[] args){
        System.out.println();

        //byte idade = 128; // Type mismatch pq o compilador já entende que aqui deveria ser uma variável Int
        byte idade = 127; // Atribuição possível

        int peso = 80;
        //byte pesoEmByte = peso; //não é possível a atribuição pq internamente o java atribui um espaço maior para representar um INT, mesmo que o valor esteja dentro do intervalo de um Byte        System.out.println("Idade é "+ idade);
        byte pesoEmByte = (byte)peso; // Cast explícito. Cuidado! pode forçar uma conversão com estouro, ou seja, pode passar um erro pela compilação e dar erro de execução.
        //por exemplo, se a idade=2000 e fizer cast para byte ele compila, mesmo não sendo possível representar 2000 com 8 dígitos. 
        long pesoEmLong = peso; // aqui é possível

        System.out.println("Peso em Long: "+ pesoEmLong);

        var sexo = 'M';
        sexo = 9;
        System.out.println("Sexo é: " +sexo);



    }
}
