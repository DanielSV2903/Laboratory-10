package controller;

import domain.AVL;
import domain.BST;
import domain.BTreeDrawer;
import domain.TreeException;
import javafx.event.ActionEvent;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.BorderPane;
import util.FXUtility;
import util.Utility;

public class BTreeTourController {
    @javafx.fxml.FXML
    private RadioButton bstButn;
    @javafx.fxml.FXML
    private RadioButton avlBTN;
    @javafx.fxml.FXML
    private Canvas canvas;
    @javafx.fxml.FXML
    private Button tourBtn;
    @javafx.fxml.FXML
    private Button levelsBtn;
    @javafx.fxml.FXML
    private Button isBalancedBTN;
    @javafx.fxml.FXML
    private Label label;
    @javafx.fxml.FXML
    private BorderPane bp;

    private BTreeDrawer drawer;
    private BST bst;
    private AVL avl;
    private String treeType;

    @javafx.fxml.FXML
    public void initialize() {
        bst = new BST();
        avl = new AVL();
        drawer = new BTreeDrawer();
        treeType = "";
    }

    @javafx.fxml.FXML
    public void preOrderOnAction(ActionEvent actionEvent) {
        if (avlBTN.isSelected()) {
            drawer.drawPreOrder(canvas.getGraphicsContext2D(), avl.getRoot());
        } else if (bstButn.isSelected()) drawer.drawPreOrder(canvas.getGraphicsContext2D(), bst.getRoot());
        label.setText(treeType + " Pre Order Transversal Tour (N-L-R)");
    }

    @javafx.fxml.FXML
    public void inOrderOnAction(ActionEvent actionEvent) {
        if (avlBTN.isSelected()) {
            drawer.drawInOrder(canvas.getGraphicsContext2D(), avl.getRoot());
        } else if (bstButn.isSelected()) drawer.drawInOrder(canvas.getGraphicsContext2D(), bst.getRoot());
        label.setText(treeType + " In Order Transversal Tour (L-N-R)");
    }

    @javafx.fxml.FXML
    public void postOrderOnAction(ActionEvent actionEvent) {
        if (avlBTN.isSelected()) {
            drawer.drawPostOrder(canvas.getGraphicsContext2D(), avl.getRoot());
        } else if (bstButn.isSelected()) drawer.drawPostOrder(canvas.getGraphicsContext2D(), bst.getRoot());
        label.setText(treeType + " Post Order Transversal Tour (L-R-N)");
    }

    @javafx.fxml.FXML
    public void randomizeOnAction(ActionEvent actionEvent) {
        if (!bstButn.isSelected() && !avlBTN.isSelected()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error al crear el árbol");
            alert.setHeaderText("Usted no ha seleccionado ninguna casilla para generar el árbol. Asegúrese de marcar una para continuar.");
            alert.show();
        } else {
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

    @javafx.fxml.FXML
    public void bstBTNOnAction(ActionEvent actionEvent) {
        avlBTN.setSelected(false);
        label.setText("BST");
        treeType = "BST";
    }

    @javafx.fxml.FXML
    public void avlBTNOnAction(ActionEvent actionEvent) {
        bstButn.setSelected(false);
        label.setText("AVL");
        treeType = "AVL";
    }
}
