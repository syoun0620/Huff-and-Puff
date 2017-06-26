
// This is an API for an ADT of Huffman Trees. Huffman Trees are
// weighted, full binary trees.  The compareTo function allows them
// to inhabit a priority queue.

public interface HuffmanTree {
    public int getWeight();
    public char getSymbol();
    public HuffmanTree getLeft();
    public HuffmanTree getRight();
    public boolean isLeaf();
    public void computeBitCodes(SymbolTable st, Bits bits);
    public String toString();
    public int compareTo(Object o);
}