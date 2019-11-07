package hr.fer.avl.tree;

import hr.fer.avl.tree.service.AVLTreeService;
import hr.fer.avl.tree.model.AVLTreeNode;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        AVLTreeService avlTreeService = new AVLTreeService();
        AVLTreeNode root = null;
        File file;
        Scanner scanner = new Scanner(System.in);

        if(args.length != 0){
            file = new File(args[0]);
        } else {
            file = new File("data.txt");
        }

        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            root = avlTreeService.insert(root, Integer.parseInt(line));
        }

        avlTreeService.printAVLTree(root);

        System.out.println("inorder");
        avlTreeService.inOrderPrint(root);

        String nextLine;

        while (scanner.hasNext()){
            nextLine = scanner.nextLine();

            root = avlTreeService.insert(root, Integer.parseInt(nextLine));

            avlTreeService.printAVLTree(root);
        }
    }
}
