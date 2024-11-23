public class node {
    int data;
    int freq;
    node left;
    node right;
    public node(int data, int freq) {
        this.data = data;
        this.freq = freq;
        left = right = null;
    }
}
