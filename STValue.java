
// This file contains an API for an ADT of symbol table values. It is
// used as part of a Huffman coding/decoding algorithm.  A symbol table
// value packages up an integer frequency as well as the binary address
// of the symbol in the Huffman Coding Tree.

public interface STValue {

  public int getFrequency();
  public Bits getBits();

  public void setFrequency(int frequency);
  public void setBits(Bits bits);

  public String toString();
}
