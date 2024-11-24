import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.PriorityQueue;

public class StandardHuffman {
    private Node root;
    private HashMap<Character, String> charToCode;
    private HashMap<String, Character> codeToChar;

    public StandardHuffman() {
        root = null;
        charToCode = new HashMap<>();
        codeToChar = new HashMap<>();
    }

    public void compress(String input, String outputFilePath) throws IOException {
        HashMap<Character, Integer> freqMap = new HashMap<>();
        for (char c : input.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (var entry : freqMap.entrySet()) {
            pq.add(new Node(entry.getKey(), entry.getValue()));
        }
        while (pq.size() > 1) {
            Node left = pq.poll();
            Node right = pq.poll();
            pq.add(new Node(left.freq + right.freq, left, right));
        }
        root = pq.poll();
        generateCodes(root, "");
        StringBuilder encoded = new StringBuilder();
        for (char c : input.toCharArray()) {
            encoded.append(charToCode.get(c));
        }
        Files.writeString(Paths.get(outputFilePath), encoded.toString());
    }

    public String decompress(String inputFilePath) throws IOException {
        String encoded = Files.readString(Paths.get(inputFilePath));
        StringBuilder decoded = new StringBuilder();
        Node current = root;
        for (char bit : encoded.toCharArray()) {
            current = (bit == '0') ? current.left : current.right;
            if (current.isLeaf()) {
                decoded.append(current.data);
                current = root;
            }
        }
        return decoded.toString();
    }

    private void generateCodes(Node node, String code) {
        if (node == null) return;

        if (node.isLeaf()) {
            charToCode.put(node.data, code);
            codeToChar.put(code, node.data);
        }

        generateCodes(node.left, code + "0");
        generateCodes(node.right, code + "1");
    }
}
