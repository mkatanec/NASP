package hr.fer.avl.tree.model;

public class AVLTreeNode {
    private int value;
    private int height;
    private AVLTreeNode leftAVLTreeNode;
    private AVLTreeNode rightAVLTreeNode;

    public AVLTreeNode(int value) {
        this.value = value;
        this.height = 1;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public AVLTreeNode getLeftAVLTreeNode() {
        return leftAVLTreeNode;
    }

    public void setLeftAVLTreeNode(AVLTreeNode leftAVLTreeNode) {
        this.leftAVLTreeNode = leftAVLTreeNode;
    }

    public AVLTreeNode getRightAVLTreeNode() {
        return rightAVLTreeNode;
    }

    public void setRightAVLTreeNode(AVLTreeNode rightAVLTreeNode) {
        this.rightAVLTreeNode = rightAVLTreeNode;
    }
}
