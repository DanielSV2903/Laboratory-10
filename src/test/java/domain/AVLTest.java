package domain;

import org.junit.jupiter.api.Test;
import util.Utility;

import java.util.Arrays;

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
}