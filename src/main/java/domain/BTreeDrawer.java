package domain;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.Random;

public class BTreeDrawer {
    private static final double NODE_RADIUS = 20;
    private static final double LEVEL_GAP = 70;
    private int visitCounter;

    private Random random = new Random();

    // Métodos originales sin modificar
    public void draw(GraphicsContext gc,BTreeNode node,int height) {
        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
        double canvasWidth = gc.getCanvas().getWidth();
        drawNode(gc, node, canvasWidth / 2, 50, canvasWidth / 2, height,0); // x inicial y offset inicial
         }

    private void drawNode(GraphicsContext gc, BTreeNode node, double x, double y, double offset, int treeHeight,int level) {
        final double NODE_RADIUS = 20;
        final double LEVEL_GAP = 70;
        if (node == null) return;

        // Dibuja nodo
        gc.setFill(Color.LIGHTBLUE);
        gc.fillOval(x - NODE_RADIUS, y - NODE_RADIUS, NODE_RADIUS * 2, NODE_RADIUS * 2);
        gc.setStroke(Color.BLACK);
        gc.strokeOval(x - NODE_RADIUS, y - NODE_RADIUS, NODE_RADIUS * 2, NODE_RADIUS * 2);
        gc.setFill(Color.BLACK);
        gc.setFont(new Font(12));
        gc.fillText(node.data.toString(), x - NODE_RADIUS / 2, y + 5);

        // Offset decreciente por nivel
        double newOffset = offset/2;

        // Recursión con líneas
        if (node.left != null) {
            double childX = x - newOffset;
            double childY = y + LEVEL_GAP;
            gc.strokeLine(x, y + NODE_RADIUS, childX, childY - NODE_RADIUS);
            drawNode(gc, node.left, childX, childY, newOffset, treeHeight,level+1);
        }

        if (node.right != null) {
            double childX = x + newOffset;
            double childY = y + LEVEL_GAP;
            gc.strokeLine(x, y + NODE_RADIUS, childX, childY - NODE_RADIUS);
            drawNode(gc, node.right, childX, childY, newOffset, treeHeight,level+1);
        }
    }
    private double calcOffset(int level, double radius) {
        return radius * Math.pow(2, level + 1);
    }
    public void drawLevels(GraphicsContext gc,BTreeNode node,double width,int height) throws TreeException {
        drawLevels(gc,node,0,width,height);
    }
    private void drawLevels(GraphicsContext gc, BTreeNode node, int initialLevel, double canvasWidth,int height) throws TreeException {
        int treeHeight = height; // altura del árbol}
        final double LEVEL_GAP=70;
        gc.setStroke(Color.RED);
        gc.setFill(Color.RED);
        gc.setLineWidth(1.5);
        gc.setFont(new Font("Arial", 12));

        for (int level = 0; level <= treeHeight; level++) {
            double y = 50 + level * LEVEL_GAP;
            gc.strokeLine(0, y, canvasWidth, y); // línea horizontal
            gc.fillText(""+(level+1), 5, y - 5); // etiqueta al inicio
        }
    }


    // Nuevos métodos para el recorrido
    public void drawPreOrder(GraphicsContext gc, BTreeNode root) {
        gc.clearRect(0, 0, 800, 600);
        visitCounter = 1;
        drawPreOrderNode(gc, root, 400, 50, 200);
    }

    private void drawPreOrderNode(GraphicsContext gc, BTreeNode node, double x, double y, double offset) {
        if (node == null) return;

        // Primero dibujamos las conexiones
        drawConnections(gc, node, x, y, offset);
        // Luego dibujamos el nodo actual con su número
        drawNodeWithNumber(gc, node, x, y, visitCounter++);

        // Finalmente procesamos los hijos
        if (node.left != null) {
            drawPreOrderNode(gc, node.left, x - offset, y + LEVEL_GAP, offset / 2);
        }
        if (node.right != null) {
            drawPreOrderNode(gc, node.right, x + offset, y + LEVEL_GAP, offset / 2);
        }
    }

    public void drawInOrder(GraphicsContext gc, BTreeNode root) {
        gc.clearRect(0, 0, 800, 600);
        visitCounter = 1;
        drawInOrderNode(gc, root, 400, 50, 200);
    }

    private void drawInOrderNode(GraphicsContext gc, BTreeNode node, double x, double y, double offset) {
        if (node == null) return;

        drawConnections(gc, node, x, y, offset);

        if (node.left != null) {
            drawInOrderNode(gc, node.left, x - offset, y + LEVEL_GAP, offset / 2);
        }

        drawNodeWithNumber(gc, node, x, y, visitCounter++);

        if (node.right != null) {
            drawInOrderNode(gc, node.right, x + offset, y + LEVEL_GAP, offset / 2);
        }
    }

    public void drawPostOrder(GraphicsContext gc, BTreeNode root) {
        gc.clearRect(0, 0, 800, 600);
        visitCounter = 1;
        drawPostOrderNode(gc, root, 400, 50, 200);
    }

    private void drawPostOrderNode(GraphicsContext gc, BTreeNode node, double x, double y, double offset) {
        if (node == null) return;

        drawConnections(gc, node, x, y, offset);

        if (node.left != null) {
            drawPostOrderNode(gc, node.left, x - offset, y + LEVEL_GAP, offset / 2);
        }
        if (node.right != null) {
            drawPostOrderNode(gc, node.right, x + offset, y + LEVEL_GAP, offset / 2);
        }

        drawNodeWithNumber(gc, node, x, y, visitCounter++);
    }

    private void drawNodeWithNumber(GraphicsContext gc, BTreeNode node, double x, double y, int order) {
        // Dibuja el círculo del nodo
        Color color = Color.LIGHTBLUE;
        gc.setFill(color);
        gc.fillOval(x - NODE_RADIUS, y - NODE_RADIUS, NODE_RADIUS * 2, NODE_RADIUS * 2);
        gc.setStroke(Color.BLACK);
        gc.strokeOval(x - NODE_RADIUS, y - NODE_RADIUS, NODE_RADIUS * 2, NODE_RADIUS * 2);

        gc.setFill(Color.BLACK);
        gc.setFont(new Font(12));
        String dataText = node.data.toString();
        gc.fillText(dataText, x - NODE_RADIUS / 2, y + 5);

        String orderText = String.valueOf(order);
        double orderX = x + NODE_RADIUS + 5;
        double orderY = y;

        // Añade un pequeño círculo blanco detrás del número
        gc.setFill(Color.WHITE);
        gc.fillOval(orderX - 2, orderY - 8, 20, 20);
        gc.setFill(Color.BLACK);
        gc.fillText(orderText, orderX, orderY + 5);
    }

    private void drawConnections(GraphicsContext gc, BTreeNode node, double x, double y, double offset) {
        if (node.left != null) {
            double childX = x - offset;
            double childY = y + LEVEL_GAP;
            gc.strokeLine(x, y + NODE_RADIUS, childX, childY - NODE_RADIUS);
        }
        if (node.right != null) {
            double childX = x + offset;
            double childY = y + LEVEL_GAP;
            gc.strokeLine(x, y + NODE_RADIUS, childX, childY - NODE_RADIUS);
        }
    }
}