package controller;

import domain.AVL;
import domain.BST;
import domain.BTreeDrawer;
import domain.TreeException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import util.Utility;

import javax.swing.*;

public class OperationsController{
    private BTreeDrawer drawer;
    private BST bst;
    private AVL avl;

    @javafx.fxml.FXML
    private Canvas treeCanvas;
    @FXML
    private RadioButton bstButton;
    @FXML
    private RadioButton avlButton;
    @FXML
    private Label label;

    @javafx.fxml.FXML
    public void initialize() {
        bst = new BST();
        avl = new AVL();
        drawer = new BTreeDrawer();
    }

    @javafx.fxml.FXML
    public void nodeHeightOnAction(ActionEvent actionEvent) {
        int value = treeInput("Ingrese un valor para buscar su tamaño", "Tamaño del nodo");
        try {
            if (bstButton.isSelected()) {
                int totalHeight = bst.height(value);
                mostrarAlerta("La altura total del árbol BST es: " + totalHeight);
            } else if (avlButton.isSelected()) {
                int totalHeight = avl.height(value);
                mostrarAlerta("La altura total del árbol AVL es: " + totalHeight);
            }

        } catch (TreeException e) {
            throw new RuntimeException(e);
        }
    }

    @javafx.fxml.FXML
    public void removeOnAction(ActionEvent actionEvent) {
        int removedValue = treeInput("Ingrese un valor para eliminar", "Valor a eliminar");
        try {
            if(bstButton.isSelected()) {
                boolean exists = bst.contains(removedValue);

                if (exists) {
                    bst.remove(removedValue);
                    drawer.draw(treeCanvas.getGraphicsContext2D(), bst.getRoot(), bst.height());
                    mostrarAlerta("El valor [" + removedValue + "] ha sido eliminado");
                } else
                    mostrarAlerta("El valor [" + removedValue + "] no se encuentra en el arbol");
            } else if(avlButton.isSelected()) {
                boolean exists = avl.contains(removedValue);

                if (exists) {
                    avl.remove(removedValue);
                    drawer.draw(treeCanvas.getGraphicsContext2D(), avl.getRoot(), avl.height());
                    mostrarAlerta("El valor [" + removedValue + "] ha sido eliminado");
                } else
                    mostrarAlerta("El valor [" + removedValue + "] no se encuentra en el arbol");
            }

        } catch (TreeException e) {
            throw new RuntimeException(e);
        }
    }

    @javafx.fxml.FXML
    public void addOnAction(ActionEvent actionEvent) {
        int newValue = treeInput("Ingrese un valor para añadir al árbol", "Nuevo valor");

        try {
            if(bstButton.isSelected()) {
                if (bst.contains(newValue)) {
                    mostrarAlerta("El valor [" + newValue + "] ya existe en el árbol");
                } else {
                    bst.add(newValue);
                    drawer.draw(treeCanvas.getGraphicsContext2D(), bst.getRoot(), bst.height());
                    mostrarAlerta("El valor [" + newValue + "] ha sido añadido correctamente");
                }
            } else if (avlButton.isSelected()) {
                if (avl.contains(newValue)) {
                    mostrarAlerta("El valor [" + newValue + "] ya existe en el árbol");
                } else {
                    avl.add(newValue);
                    drawer.draw(treeCanvas.getGraphicsContext2D(), avl.getRoot(), avl.height());
                    mostrarAlerta("El valor [" + newValue + "] ha sido añadido correctamente");
                }

            }
        } catch (TreeException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    public void randomizeOnAction(ActionEvent actionEvent) {
        treeCanvas.getGraphicsContext2D().clearRect(0,0,treeCanvas.getWidth(),treeCanvas.getHeight());
        bst.clear();
        avl.clear();

        try {
            if (!bstButton.isSelected() && !avlButton.isSelected()) {
                mostrarAlerta("Seleccione si desea generar un árbol BST o AVL");
            } else {
                if (bstButton.isSelected()) {
                    avlButton.setSelected(false);
                    for (int i = 0; i < 30; i++) {
                        bst.add(Utility.random(50));
                    }
                    drawer.draw(treeCanvas.getGraphicsContext2D(), bst.getRoot(), bst.height());
                    label.setText(bst.isBalanced() ? "BST esta balanceado" : "BST no esta balanceado");

                } else if (avlButton.isSelected()) {
                    bstButton.setSelected(false);
                    for (int i = 0; i < 30; i++) {
                        avl.add(Utility.random(50));
                    }
                    drawer.draw(treeCanvas.getGraphicsContext2D(), avl.getRoot(), avl.height());
                    label.setText(avl.isBalanced() ? "AVL esta balanceado" : "AVL no esta balanceado");

                }
            }
        } catch (TreeException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void treeAVLOnAction(ActionEvent actionEvent) {bstButton.setSelected(false);}

    @javafx.fxml.FXML
    public void treeBSTOnAction(ActionEvent actionEvent) {avlButton.setSelected(false);}

    @javafx.fxml.FXML
    public void containsOnAction(ActionEvent actionEvent) {
        int value = treeInput("Ingrese un valor para buscar", "Valor a buscar");

        try {
            if (bstButton.isSelected()) {
                boolean found = bst.contains(value);

                if (found)
                    mostrarAlerta("El valor [" + value + "] fue encontrado");
                else
                    mostrarAlerta("El valor [" + value + "] no fue encontrado");

            } else if (avlButton.isSelected()) {
                boolean found = avl.contains(value);
                if (found)
                    mostrarAlerta("El valor [" + value + "] fue encontrado");
                else
                    mostrarAlerta("El valor [" + value + "] no fue encontrado");
            }

        } catch (TreeException e) {
            throw new RuntimeException(e);
        }

    }

    @javafx.fxml.FXML
    public void treeHeightOnAction(ActionEvent actionEvent) {
        try {
            if (bstButton.isSelected()) {
                int totalHeight = bst.height();
                mostrarAlerta("La altura total del árbol es: " + totalHeight);
            } else if (avlButton.isSelected()) {
                int totalHeight = avl.height();
                mostrarAlerta("La altura total del árbol es: " + totalHeight);
            }

        } catch (TreeException e) {
            throw new RuntimeException(e);
        }
    }

    public int treeInput(String message, String title) {
        while (true) {
            String input = JOptionPane.showInputDialog(null, message, title, JOptionPane.QUESTION_MESSAGE);

            if (input == null) {
                return 0;
            }

            if (input.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null,
                        "Por favor, ingrese un número.",
                        "Campo vacío",
                        JOptionPane.WARNING_MESSAGE);
                continue;
            }

            try {
                return Integer.parseInt(input.trim());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null,
                        "'" + input + "' no es un número válido.\nPor favor, ingrese solo números enteros.",
                        "Error de formato",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static void mostrarAlerta(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Error de validación");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}