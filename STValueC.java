
// This file contains an implementation of the STValue ADT. It is used
// as part of an implementation of symbol tables for a Huffman
// coding/decoding algorithm.  A symbol table value packages up an
// integer frequency as well as the binary address of the symbol in
// the Huffman Coding Tree.

public class STValueC implements STValue {

  // Instance variables
  private int frequency;
  private Bits bits;

  public STValueC(int frequency, Bits bits) {
	  this.frequency = frequency;
	  this.bits = bits;
  }

  public STValueC(int frequency) {
    this(frequency, null);
  }

  // Getters
  public Bits getBits() { return this.bits; }
  public int getFrequency() { return this.frequency; }

  // Setters (Mutable Types)
  public void setFrequency(int frequency) {
    this.frequency = frequency;
  }
  public void setBits(Bits bits) { this.bits = bits; }

  public String toString() {
    String bs = this.bits == null ? "" : this.getBits().toString();
	  return "{freq = " + this.getFrequency() + ", bits = " + bs + "}";
  }
}
