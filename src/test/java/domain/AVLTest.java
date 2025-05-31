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
            int randomNumber = Utility.random(20, 200);
            avl.add(randomNumber);
            listaNumeros[i] = randomNumber;
        }

        //b
        System.out.println("Contenido del arbol: "+ avl +"\n");

        try {
            //c
            System.out.println("Tamaño del árbol: "+ avl.size() +"\n");
            System.out.println("Elemento más grande del árbol: "+ avl.max() +"\n");
            System.out.println("Elemento más pequeño del árbol: "+ avl.min() +"\n");

            //d
            if (avl.isBalanced())
                System.out.println("El árbol esta correctamente balanceado\n");
            else
                System.out.println("El árbol no esta balanceado\n");

            //e
            int deleted = 0;
            for (int i = 0; i < listaNumeros.length && deleted < 5; i++) {
                if (avl.contains(listaNumeros[i])) {
                    avl.remove(listaNumeros[i]);
                    System.out.println("Eliminado: " + listaNumeros[i] +"\n");
                    deleted++;
                }
            }

            //f
            System.out.println("Contenido del árbol: "+ avl +"\n");

            //g
            if (avl.isBalanced())
                System.out.println("El árbol esta correctamente balanceado\n");
            else {
                System.out.println("El árbol no esta balanceado");
                System.out.println("El árbol debe ser balanceado\n");
            }

            //h
            if (!avl.isBalanced()) {
                int[] elementosActuales = new int[avl.size()];
                int index = 0;

                for (int i = 0; i < listaNumeros.length; i++) {
                    if (avl.contains(listaNumeros[i]) && index < elementosActuales.length) {
                        elementosActuales[index] = listaNumeros[i];
                        index++;
                    }
                }

                AVL nuevoAvl = new AVL();

                for (int i = 0; i < index; i++) {
                    nuevoAvl.add(elementosActuales[i]);
                }

                avl = nuevoAvl;
            }

            //i
            System.out.println("Contenido final del árbol: "+ avl +"\n");

            //j
            if (avl.isBalanced())
                System.out.println("El árbol está correctamente balanceado");
            else
                System.out.println("El árbol no está balanceado");

        } catch (TreeException e) {
            throw new RuntimeException(e);
        }
    }

}