
// The API for bit strings in the Huffman Coding Algorithm.

public interface Bits {
  public int getBits();
  public int getSize();
  public String toString();
  public Bits addBit(int bit);
  public void write(BinaryOut outFile);
}
