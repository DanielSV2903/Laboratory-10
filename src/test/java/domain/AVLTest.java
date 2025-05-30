package domain;

import org.junit.jupiter.api.Test;
import util.Utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AVLTest {

    @Test
    void test() {
        AVL avl = new AVL();
        int[] listaNumeros = new int[30];
        for (int i = 0; i < listaNumeros.length; i++) {
            int numero = util.Utility.random(50)+1;
            avl.add(numero);
            listaNumeros[i] = numero;
        }
        System.out.println(avl); //toString
        System.out.println(Arrays.toString(listaNumeros));
        for (int i = 0; i < 5; i++) {
            int numero = util.Utility.random(listaNumeros.length);
            System.out.println("Hermanos de " + numero + ": " + avl.brother(listaNumeros[numero]));
            System.out.println("Hijos de " + numero + ": " + avl.children(listaNumeros[numero]));
        }
    }

    @Test
    void test2() {
        //a
        AVL avl = new AVL();
        int[] listaNumeros = new int[30];
        //añadir numeros
        for (int i = 0; i < 30; i++) {
            int randomNumber = util.Utility.random(20, 200);
            avl.add(randomNumber);
            listaNumeros[i] = randomNumber;
        }

        //b
        System.out.println("Contenido del arbol: "+ avl);

        try {
            //c
            System.out.println("Tamaño del árbol: "+ avl.size());
            System.out.println("Elemento más grande del árbol: "+ avl.max());
            System.out.println("Elemento más pequeño del árbol: "+ avl.min());

            //d
            if (avl.isBalanced())
                System.out.println("El árbol esta correctamente balanceado");
            else
                System.out.println("El árbol no esta balanceado");

            //e
            int deleted = 0;
            while (deleted <= 5) {
                int randomNumber = util.Utility.random(20, 200);
                if (avl.contains(listaNumeros[randomNumber])) {
                    avl.remove(listaNumeros[randomNumber]);
                    deleted++;
                }
            }

            //f
            System.out.println("Contenido del árbol: "+ avl);

            //g
            if (avl.isBalanced())
                System.out.println("El árbol esta correctamente balanceado");
            else
                System.out.println("El árbol no esta balanceado");

            //h
            if (!avl.isBalanced()){

            }




        } catch (TreeException e) {
            throw new RuntimeException(e);
        }



    }

}