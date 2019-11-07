package hr.fer.avl.tree;

import hr.fer.avl.tree.model.AVLTree;
import hr.fer.avl.tree.model.AVLTreeNode;
import hr.fer.avl.tree.service.AVLTreePrintService;

import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        AVLTree avlTree = new AVLTree();
        AVLTreeNode root = null;
        File file = new File("data.txt");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        AVLTreePrintService printService = new AVLTreePrintService();

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            root = avlTree.insert(root, Integer.parseInt(line));
        }

        printService.printAVLTree(root);

        System.out.println("inorder");
        printService.inOrderPrint(root);
    }
}
