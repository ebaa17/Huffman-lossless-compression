import java.io.*;
import java.nio.file.*;


class Main {
    public static void main(String[] args) throws IOException {
        String inputFile = "input.txt";
        String compressedFile = "compressed.bin";
        String decompressedFile = "decompressed.txt";
        StandardHuffman huffman = new StandardHuffman();
        String inputText = Files.readString(Paths.get(inputFile));
        huffman.compress(inputText, compressedFile);
        String decompressedText = huffman.decompress(compressedFile);
        Files.writeString(Paths.get(decompressedFile), decompressedText);

    }
}