public class Node implements Comparable<Node> {
    char data;
    int freq;
    Node left;
    Node right;
    public Node(char data, int freq) {
        this.data = data;
        this.freq = freq;
        left = right = null;
    }
    public Node(int freq, Node left, Node right) {
        this.data = '\0';
        this.freq = freq;
        this.left = left;
        this.right = right;
    }

    @Override
    public int compareTo(Node other) {
        return this.freq - other.freq;
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }
}
