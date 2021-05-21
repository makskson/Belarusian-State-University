import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main implements Runnable {
    @Override
    public void run() {
        Scanner in = null;
        Node node = new Node();
        try {
            in = new Scanner(new File("input.txt"));
            node.value = in.nextInt();
            while (in.hasNextInt()) {
                node.insert(in.nextInt());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        PrintWriter out = null;
        try {
            out = new PrintWriter("output.txt");
            node.pre_order_traversal(out);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static class Node {
        public int value;
        public Node left_child;
        public Node right_child;

        public Node() {
            this.value = 0;
            left_child = null;
            right_child = null;
        }

        public Node(int value) {
            this.value = value;
            left_child = null;
            right_child = null;
        }

        public void insert(int value) {
            if (value > this.value) {
                if (this.right_child == null) {
                    this.right_child = new Node(value);
                }
                this.right_child.insert(value);
            } else if (value < this.value) {
                if (this.left_child == null) {
                    this.left_child = new Node(value);
                }
                this.left_child.insert(value);
            }
        }

        public void pre_order_traversal(PrintWriter out) {
            out.println(this.value);
            if (this.left_child != null) {
                this.left_child.pre_order_traversal(out);
            }
            if (this.right_child != null) {
                this.right_child.pre_order_traversal(out);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Thread(null, new Main(), "", 64 * 1024 * 1024).start();
    }
}