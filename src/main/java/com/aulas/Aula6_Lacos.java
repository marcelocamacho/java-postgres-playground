package com.aulas;

import java.util.Arrays;

public class Aula6_Lacos {
    public static void main(String[] args) {
// Laço for em vetor        
        int vetor[] = new int[6];

        for (int i = 0; i < vetor.length; i++) {
            vetor[i] = 100 * (i+1);
        }

        System.out.println(Arrays.toString(vetor));
        System.out.println();
//Laço for em matriz

        double matriz[][] = new double[2][3];

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print(i + "*" + matriz[i].length + "+" + j + "  ");

                matriz[i][j] = i * matriz[i].length + j;
            }
            System.out.println();
        }

        System.out.println(Arrays.toString(matriz[0]));
        System.out.println(Arrays.toString(matriz[1]));
    
        System.out.println();

        System.out.println("Imprimindo a matriz");
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print(i+""+j+" ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("Imprimindo a matriz transposta");
        
        System.out.println("método 1");
        for (int j = 0; j < matriz[0].length; j++) {
            for (int i = 0; i < matriz.length; i++) {
                System.out.print(i+""+j+" ");
            }
            System.out.println();
        }
    }
}
