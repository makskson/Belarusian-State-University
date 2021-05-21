import java.io.*;
import java.util.*;

public class Main implements Runnable {
    @Override
    public void run() {
        try {
            Scanner in = new Scanner(new File("in.txt"));
            ArrayList<Tree.Node> nodes = new ArrayList<>();
            Tree tree = new Tree(in.nextInt());
            while (in.hasNextInt()) {
                tree.insert(in.nextInt(), tree.root);
            }
            tree.setHeight();
            tree.findAndDelete(nodes);
            PrintWriter out = new PrintWriter("out.txt");
            tree.pot(out, tree.root);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static class Tree {
        static class Node {
            int key;
            int height;
            Node left;
            Node right;
            Node parent;

            Node(int key) {
                this.key = key;
                this.height = 0;
            }
            Node(int key, Node parent) {
                this.key = key;
                this.height = 0;
                this.parent = parent;
            }
        }
        private Node root;

        public Tree(int key) {
            root = new Node(key);
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

        public void pot(PrintWriter out, Node node) {
            out.println(node.key);
            if (node.left != null) {
                pot(out, node.left);
            }
            if (node.right != null) {
                pot(out, node.right);
            }
        }

        public void setHeight() {
            setHighUtil(root);
        }

        public int setHighUtil(Node root) {
            if (root == null) {
                return -1;
            }
            root.height = 1 + Math.max(setHighUtil(root.left),setHighUtil(root.right));
            return root.height;
        }

        public int countNodes(Node node) {
            if (node == null)
                return 0;
            int a, b;
            a = countNodes(node.left);
            b = countNodes(node.right);
            return a + b + 1;
        }

        public void findAndDelete(ArrayList<Node> nodes) {
            findNodesUtil(root, nodes);
            if (nodes.size() % 2 == 1) {
                Collections.sort(nodes, new Comparator<Node>() {
                    @Override
                    public int compare(Node o1, Node o2) {
                        if (o1.key > o2.key)
                            return -1;
                        else if (o1.key < o2.key)
                            return 1;
                        else
                            return 0;
                    }
                });
                delete(nodes.get((nodes.size() - 1) / 2).key, root);
            }
        }

        public void findNodesUtil(Node node, ArrayList<Node> nodes) {
            if (node == null)
                return;
            findNodesUtil(node.left, nodes);
            findNodesUtil(node.right, nodes);
            if (countNodes(node.left) == countNodes(node.right) && node.right != null && node.left != null && node.left.height != node.right.height)
                nodes.add(node);
        }

    }

    public static void main(String[] args) throws IOException {
        new Thread(null, new Main(), "", 64 * 1024 * 1024).start();
    }
}