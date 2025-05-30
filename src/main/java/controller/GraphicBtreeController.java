package controller;

import domain.*;
import javafx.event.ActionEvent;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import util.Utility;

public class GraphicBtreeController
{
    @javafx.fxml.FXML
    private BorderPane bp;
    @javafx.fxml.FXML
    private Canvas canvas;
    @javafx.fxml.FXML
    private Button tourBtn;
    @javafx.fxml.FXML
    private Button levelsBtn;
    @javafx.fxml.FXML
    private Label label;
    @javafx.fxml.FXML
    private RadioButton bstButn;
    @javafx.fxml.FXML
    private RadioButton avlBTN;
    @javafx.fxml.FXML
    private Button isBalancedBTN;

    private BTreeDrawer drawer;
    private BST bst;
    private AVL avl;

    @javafx.fxml.FXML
    public void initialize() {
        bst = new BST();
        avl = new AVL();
        drawer = new BTreeDrawer();
    }

    @javafx.fxml.FXML
    public void levelsOnAction(ActionEvent actionEvent) {
        try {
            if (bstButn.isSelected())
                drawer.drawLevels(canvas.getGraphicsContext2D(), bst.getRoot(),canvas.getWidth()-150, bst.height());
            else if (avlBTN.isSelected())
                drawer.drawLevels(canvas.getGraphicsContext2D(), avl.getRoot(),canvas.getWidth()-150, avl.height());
        } catch (TreeException e) {
            throw new RuntimeException(e);
        }
    }

    @javafx.fxml.FXML
    public void randomizeOnAction(ActionEvent actionEvent) {
        if (!bstButn.isSelected()&&!avlBTN.isSelected())
            mostrarAlerta(Alert.AlertType.WARNING,"Seleccione un tipo de Binary Tree");
        else if (avlBTN.isSelected()||bstButn.isSelected()){
        canvas.getGraphicsContext2D().clearRect(0,0,canvas.getWidth(),canvas.getHeight());
        bst.clear();
        avl.clear();
        levelsBtn.setDisable(false);
        tourBtn.setDisable(false);
        isBalancedBTN.setDisable(false);
        try {
        if (bstButn.isSelected()){
            avlBTN.setSelected(false);
            for (int i=0;i<30;i++){
                bst.add(Utility.random(50));
            }
            drawer.draw(canvas.getGraphicsContext2D(),bst.getRoot(),bst.height());
        } else if (avlBTN.isSelected()){
            bstButn.setSelected(false);
            for (int i=0;i<30;i++){
                avl.add(Utility.random(50));
            }
            drawer.draw(canvas.getGraphicsContext2D(),avl.getRoot(),avl.height());
        }
        }catch (TreeException e){
            e.printStackTrace();
        }
        }
    }

    private void mostrarAlerta(Alert.AlertType alertType, String s) {
        Alert alert = new Alert(alertType);
        alert.initOwner(canvas.getScene().getWindow());
        alert.setTitle("Alerta");
        alert.setHeaderText(null);
        alert.setContentText(s);
        alert.showAndWait();
    }

    @javafx.fxml.FXML
    public void tourOnAction(ActionEvent actionEvent) {
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Tour");
        alert.setHeaderText(null);
        TextArea textArea=new TextArea();
        textArea.setEditable(false);
        try {
            if (bstButn.isSelected()){
                avlBTN.setSelected(false);
            textArea.setText("BST  Tour info: \n Tree Height: "+ bst.height()+"\n"+ bst.toString());
            }else if (avlBTN.isSelected()){
                textArea.setText("AVL  Tour info: \n Tree Height: "+ avl.height()+"\n"+ avl.toString());
            }
        } catch (TreeException e) {
            throw new RuntimeException(e);
        }
        alert.getDialogPane().setContent(textArea);
        alert.setResizable(true);
        alert.showAndWait();
    }

    @javafx.fxml.FXML
    public void isBalancedOnAction(ActionEvent actionEvent) {
        try {
        if (bstButn.isSelected()){
            avlBTN.setSelected(false);
            label.setText(bst.isBalanced() ? "BST is balanced" : "BST is not balanced");
        }else if (avlBTN.isSelected()){
            label.setText("AVL is balanced");
        }
        }catch (TreeException e){
            e.printStackTrace();
        }
    }

    @javafx.fxml.FXML
    public void bstBTNOnAction(ActionEvent actionEvent) {
        avlBTN.setSelected(false);
        label.setText("BST selected");
    }

    @javafx.fxml.FXML
    public void avlBTNOnAction(ActionEvent actionEvent) {
        bstButn.setSelected(false);
        label.setText("AVL selected");
    }
}