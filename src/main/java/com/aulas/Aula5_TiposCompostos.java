package com.aulas;

import java.util.Arrays;

public class Aula5_TiposCompostos {
    public static void main(String[] args) {
        int x = 10;
        System.out.println(x);

        x = 20;
        System.out.println(x);

        //inicialização estática
        double vetor[] = {10,20,30};
        
        //inicialização dinâmica
        double vetor2[] = new double[3];
        vetor2[0]=100;
        vetor2[1]=200;
        vetor2[2]=300;
        

        System.out.println(Arrays.toString(vetor));
        System.out.println(Arrays.toString(vetor2));

        System.out.println("Valor da posição 0: " + vetor[0]);
        System.out.println("Valor da posição 1: " + vetor[1]);
        System.out.println("Valor da posição 0: " + vetor[2]);
       // System.out.println("Valor da posição INEXISTENTE: " + vetor[4]);// IndexOfBound

        // Conversão CAST de tipos compostos não funciona!
        int vetInt [] = {1,2,3};
        //  double vetDouble [] = vetInt;// Type mismatch: cannot convert int[] in double[]
        
        // Matriz

        double matriz[][] = { {10,20,30}, {40,50,60}};
        matriz[0][2]=900;
        System.out.println(Arrays.toString(matriz[0]));
        System.out.println(Arrays.toString(matriz[1]));
        matriz[1] = new double[]{100,200};
        System.out.println(Arrays.toString(matriz[1]));
    }
}
