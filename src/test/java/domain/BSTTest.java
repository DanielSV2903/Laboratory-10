package domain;

import org.junit.jupiter.api.Test;
import util.Utility;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class BSTTest {

    @Test
    void test() {
        try {
            BST bst = new BST();

            //a)
            for (int i = 0; i < 100; i++) {
                bst.add(Utility.random(200,500)); // 200–500
            }

            //a.2)
            BST bstLetras = new BST();
            for (char c = 'A'; c <= 'Z'; c++) {
                bstLetras.add(String.valueOf(c));
            }

            //a.3)
            BST bstNames = new BST();
            String[] names = {"Ana", "Luis", "Carlos", "Maria", "Pedro", "Lucia", "Jose", "Elena", "Sofia", "Diego"};
            for (String name : names) {
                bstNames.add(name);
            }

            //b)
            System.out.println("--- BST Numeros ---");
            System.out.println(bst);

            System.out.println("\n--- BST Letras ---");
            System.out.println(bstLetras);

            System.out.println("\n--- BST Names ---");
            System.out.println(bstNames);

            //c)
            System.out.println("\n--- Size, Min, Max ---");
            System.out.println("Números: size=" + bst.size() + ", min=" + bst.min() + ", max=" + bst.max());
            System.out.println("Letras: size=" + bstLetras.size() + ", min=" + bstLetras.min() + ", max=" + bstLetras.max());
            System.out.println("Nombres: size=" + bstNames.size() + ", min=" + bstNames.min() + ", max=" + bstNames.max());

            //d)
            System.out.println("\n--- Contains ---");

//            int [] testInt=new int[]{200, 250, 300, 400, 500};
            for (int i=0;i<5;i++) {
                int value=Utility.random(200,500);
                System.out.println(bst.contains(value)?value+" existe en el BST ":value+" no existe en el BST");
            }


            String[] letters=new String[]{"A", "M", "Z", "F", "T"};
            for (String letter : letters) {
                System.out.println(letter + ": " + bstLetras.contains(letter));
            }


            String[] namesToTest= new String[]{"Ana", "Pedro", "Lucia", "Juan", "Diego"};
            for (String name :namesToTest) {
                System.out.println(name + ": " + bstNames.contains(name));
            }

            //e)

            for (int i = 0; i < 10; i++) {
                int n = Utility.random(10,50);
                bst.add(n);
            }
            System.out.println();

            //f)
            System.out.println("\n¿BST está balanceado?: " + bst.isBalanced());

            //g)
            for (int i=0;i<5;i++){
                try {
                    int value=Utility.random(200,500);
                    System.out.println(bst.contains(value)?value+" fue eliminado":value+" no existe en el BST ");
                    bst.remove(value);
                } catch (TreeException ex) {
                    ex.printStackTrace();
                }
            }

            //h)

            System.out.println(bst);

            //i)
            System.out.println("¿BST está balanceado?: " + bst.isBalanced());

            //j)
            List<List<Integer>> lists= bst.nodeHeights();
            List<Integer> datas=lists.get(0);
            List<Integer> heights=lists.get(1);
            System.out.println("Altura de cada nodo");
            for (int i=0;i< datas.size();i++){
                System.out.println("Node data: " + datas.get(i)+"\n height: " + heights.get(i));
            }

        } catch (TreeException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

}