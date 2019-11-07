package hr.fer.avl.tree.model;

public class AVLTree {
    AVLTreeNode root;

    public AVLTreeNode getRoot() {
        return root;
    }

    public void setRoot(AVLTreeNode root) {
        this.root = root;
    }

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

        node.setHeight(Math.max(height(node.getLeftAVLTreeNode()), height(node.getRightAVLTreeNode())));

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
                return rightRotate(node);
            } else if (childBalanceFactor == -1) {
                //new value in left node of child node
                node.setLeftAVLTreeNode(leftRotate(node.getLeftAVLTreeNode()));

                return rightRotate(node);
            }

        }

        return node;
    }
}
