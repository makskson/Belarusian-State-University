import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main implements Runnable {
    @Override
    public void run() {
        try {
            Scanner in = new Scanner(new File("input.txt"));
            int itemToDelete = in.nextInt();
            Tree tree = new Tree(in.nextInt());
            while (in.hasNextInt()) {
                tree.insert(in.nextInt(), tree.root);
            }
            tree.root = tree.delete(itemToDelete, tree.root);
            PrintWriter out = new PrintWriter("output.txt");
            tree.obhod(out, tree.root);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static class Node {
        int key;
        Node left;
        Node right;
        Node parent;

        Node(int key) {
            this.key = key;
        }
        Node(int key, Node parent) {
            this.key = key;
            this.parent = parent;
        }
        Node() {

        }
    }
    public static class Tree {
        Node root;

        Tree(int key) {
            root = new Node(key);
        }

        Tree() {
            root = new Node();
        }

        public void insert(int key, Node node) {
            if (key > node.key) {
                if (node.right == null) {
                    node.right = new Node(key, node);
                    return;
                }
                insert(key,node.right);
            } else if (key < node.key) {
                if (node.left == null) {
                    node.left = new Node(key, node);
                    return;
                }
                insert(key, node.left);
            }
        }

        public Node delete (int key, Node root) {
            if (root == null) {
                return root;
            }
            if (key < root.key) {
                root.left = delete(key, root.left);
            } else if (key > root.key) {
                root.right = delete(key, root.right);
            } else if (root.left != null && root.right != null) {
                Node tmp = root.right;
                while (tmp.left != null) {
                    tmp = tmp.left;
                }
                root.key = tmp.key;
                root.right = delete(root.key, root.right);
            } else {
                if (root.left != null) {
                    root = root.left;
                } else if (root.right != null) {
                    root = root.right;
                } else {
                    root = null;
                }
            }
            return root;
        }

        public void obhod(PrintWriter out, Node node) {
            out.println(node.key);
            if (node.left != null) {
                obhod(out, node.left);
            }
            if (node.right != null) {
                obhod(out, node.right);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Thread(null, new Main(), "", 64 * 1024 * 1024).start();
    }
}