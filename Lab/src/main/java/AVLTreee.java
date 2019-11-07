import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AVLTreee {
    public class Node {
        private Node left, right;
        private int height = 1;
        private int value;

        private Node(int val) {
            this.value = val;
        }
    }

    private int height(Node N) {
        if (N == null)
            return 0;
        return N.height;
    }

    private Node insert(Node node, int value) {
        /* 1.  Perform the normal BST rotation */
        if (node == null) {
            return (new Node(value));
        }

        if (value < node.value)
            node.left = insert(node.left, value);
        else
            node.right = insert(node.right, value);

        /* 2. Update height of this ancestor node */
        node.height = Math.max(height(node.left), height(node.right)) + 1;

        /* 3. Get the balance factor of this ancestor node to check whether
           this node became unbalanced */
        int balance = getBalance(node);

        // If this node becomes unbalanced, then there are 4 cases

        // Left Left Case
        if (balance > 1 && value < node.left.value)
            return rightRotate(node);

        // Right Right Case
        if (balance < -1 && value > node.right.value)
            return leftRotate(node);

        // Left Right Case
        if (balance > 1 && value > node.left.value) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Left Case
        if (balance < -1 && value < node.right.value) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        /* return the (unchanged) node pointer */
        return node;
    }

    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        // Update heights
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        // Return new root
        return x;
    }

    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;

        //  Update heights
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        // Return new root
        return y;
    }

    // Get Balance factor of node N
    private int getBalance(Node N) {
        if (N == null)
            return 0;
        return height(N.left) - height(N.right);
    }

    public void print(Node root) {

        if (root == null) {
            System.out.println("(XXXXXX)");
            return;
        }

        int height = root.height,
                width = (int) Math.pow(2, height - 1);

        // Preparing variables for loop.
        List<Node> current = new ArrayList<Node>(1),
                next = new ArrayList<Node>(2);
        current.add(root);

        final int maxHalfLength = 2;
        int elements = 1;

        StringBuilder sb = new StringBuilder(maxHalfLength * width);
        for (int i = 0; i < maxHalfLength * width; i++)
            sb.append(' ');
        String textBuffer;

        // Iterating through height levels.
        for (int i = 0; i < height; i++) {

            sb.setLength(maxHalfLength * ((int) Math.pow(2, height - 1 - i) - 1));

            // Creating spacer space indicator.
            textBuffer = sb.toString();

            // Print tree node elements
            for (Node n : current) {

                System.out.print(textBuffer);

                if (n == null) {

                    System.out.print("        ");
                    next.add(null);
                    next.add(null);

                } else {

                    System.out.printf("(%6d)", n.value);
                    next.add(n.left);
                    next.add(n.right);

                }

                System.out.print(textBuffer);

            }

            System.out.println();
            // Print tree node extensions for next level.
            if (i < height - 1) {

                for (Node n : current) {

                    System.out.print(textBuffer);

                    if (n == null)
                        System.out.print("        ");
                    else
                        System.out.printf("%s      %s",
                                n.left == null ? " " : "/", n.right == null ? " " : "\\");

                    System.out.print(textBuffer);

                }

                System.out.println();

            }

            // Renewing indicators for next run.
            elements *= 2;
            current = next;
            next = new ArrayList<Node>(elements);

        }

    }

    public static void  preOrder(Node node) {
        if (node != null) {
            System.out.print(node.value + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    public static void  inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.value + " ");
            inOrder(node.right);
        }
    }

    public static void main(String args[]) throws IOException {
        AVLTreee t = new AVLTreee();
        Node root = null;
        File file = new File("data.txt");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            root = t.insert(root, Integer.parseInt(line));
        }
        t.print(root);
        preOrder(root);
        System.out.println();
        inOrder(root);
    }
}