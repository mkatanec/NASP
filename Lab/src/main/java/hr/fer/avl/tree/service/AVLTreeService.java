package hr.fer.avl.tree.service;

import hr.fer.avl.tree.model.AVLTreeNode;

import java.util.ArrayList;
import java.util.List;

public class AVLTreeService {
    private int height(final AVLTreeNode node) {
        if (node == null) {
            return 0;
        }

        return node.getHeight();
    }

    private AVLTreeNode rightRotate(final AVLTreeNode parent) {
        AVLTreeNode leftNode = parent.getLeftAVLTreeNode();
        AVLTreeNode rightLeftNode = leftNode.getRightAVLTreeNode();

        leftNode.setRightAVLTreeNode(parent);
        parent.setLeftAVLTreeNode(rightLeftNode);

        parent.setHeight(Math.max(height(parent.getLeftAVLTreeNode()), height(parent.getRightAVLTreeNode())) + 1);
        leftNode.setHeight(Math.max(height(leftNode.getLeftAVLTreeNode()), height(leftNode.getRightAVLTreeNode())) + 1);

        return leftNode;
    }

    private AVLTreeNode leftRotate(final AVLTreeNode parent) {
        AVLTreeNode rightNode = parent.getRightAVLTreeNode();
        AVLTreeNode leftRightNode = rightNode.getLeftAVLTreeNode();

        rightNode.setLeftAVLTreeNode(parent);
        parent.setRightAVLTreeNode(leftRightNode);

        parent.setHeight(Math.max(height(parent.getLeftAVLTreeNode()), height(parent.getRightAVLTreeNode())) + 1);
        rightNode.setHeight(Math.max(height(rightNode.getLeftAVLTreeNode()), height(rightNode.getRightAVLTreeNode())) + 1);

        return rightNode;
    }

    private int getBalanceFactor(AVLTreeNode node) {
        if (node == null) {
            return 0;
        }

        return height(node.getRightAVLTreeNode()) - height(node.getLeftAVLTreeNode());
    }

    public AVLTreeNode insert(AVLTreeNode node, final int value) {
        //setting root node
        if (node == null) {
           return new AVLTreeNode(value);
        }

        if (value > node.getValue()) {
            node.setRightAVLTreeNode(insert(node.getRightAVLTreeNode(), value));
        } else if (value < node.getValue()) {
            node.setLeftAVLTreeNode(insert(node.getLeftAVLTreeNode(), value));
        }

        node.setHeight(Math.max(height(node.getLeftAVLTreeNode()), height(node.getRightAVLTreeNode())) + 1);

        int balanceFactor = getBalanceFactor(node);

        if (balanceFactor == 2) {
            //new value in right node
            int childBalanceFactor = getBalanceFactor(node.getRightAVLTreeNode());

            if (childBalanceFactor == 1) {
                //new value in right node of child node
                return leftRotate(node);
            } else if (childBalanceFactor == -1) {
                //new value in left node of child node
                node.setRightAVLTreeNode(rightRotate(node.getRightAVLTreeNode()));

                return leftRotate(node);
            }

        } else if (balanceFactor == -2) {
            //new value in right node
            int childBalanceFactor = getBalanceFactor(node.getLeftAVLTreeNode());

            if (childBalanceFactor == 1) {
                //new value in right node of child node
                node.setLeftAVLTreeNode(leftRotate(node.getLeftAVLTreeNode()));

                return rightRotate(node);
            } else if (childBalanceFactor == -1) {
                //new value in left node of child node
                return rightRotate(node);
            }

        }

        return node;
    }

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
            System.out.println();

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
