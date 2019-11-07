package hr.fer.avl.tree.service;

import hr.fer.avl.tree.model.AVLTreeNode;

import java.util.ArrayList;
import java.util.List;

public class AVLTreePrintService {

    public void printAVLTree(final AVLTreeNode rootNode) {
        if (rootNode == null) {
            System.out.println("Empty tree");
            return;
        }

        int treeHeight = rootNode.getHeight();
        int treeWidth = (int) Math.pow(2, rootNode.getHeight() - 1);

        List<AVLTreeNode> currentNode = new ArrayList<>();
        List<AVLTreeNode> nextNodes = new ArrayList<>();

        currentNode.add(rootNode);

        int numberOfNodes = 1;

        StringBuilder emptySpaceStringBuilder = new StringBuilder();
        for (int i = 0; i < 5 * treeWidth; i++) {
            emptySpaceStringBuilder.append(' ');
        }

        for (int i = 0; i < treeHeight; i++) {
            emptySpaceStringBuilder.setLength(5 * ((int) Math.pow(2, treeHeight - 1 - i) - 1));

            for (AVLTreeNode node : currentNode) {
                System.out.print(emptySpaceStringBuilder.toString());

                if (node == null) {
                    System.out.print("     ");

                    nextNodes.add(null);
                    nextNodes.add(null);
                } else {
                    System.out.print("(" + node.getValue() + ")");

                    nextNodes.add(node.getLeftAVLTreeNode());
                    nextNodes.add(node.getRightAVLTreeNode());
                }

                System.out.print(emptySpaceStringBuilder.toString());
            }

            numberOfNodes *= 2;
            currentNode = nextNodes;
            nextNodes = new ArrayList<>();
        }
    }

    public void preOrderPrint(final AVLTreeNode root) {
        if (root != null) {
            System.out.print(root.getValue() + ", ");
            preOrderPrint(root.getLeftAVLTreeNode());
            preOrderPrint(root.getRightAVLTreeNode());
        }
    }

    public void inOrderPrint(final AVLTreeNode root) {
        if (root != null) {
            inOrderPrint(root.getLeftAVLTreeNode());
            System.out.print(root.getValue() + ", ");
            inOrderPrint(root.getRightAVLTreeNode());
        }
    }

    public void postOrderPrint(final AVLTreeNode root) {
        if (root != null) {
            postOrderPrint(root.getLeftAVLTreeNode());
            postOrderPrint(root.getRightAVLTreeNode());
            System.out.print(root.getValue() + ", ");
        }
    }
}
