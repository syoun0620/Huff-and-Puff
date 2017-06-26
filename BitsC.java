
// This is an implementation of the Bits ADT. It supports the
// representation of variable-length sequences of bits.

public class BitsC implements Bits {

  private int bits;     // holds the actual bits
  private int count;    // how many of bits we care about

  public BitsC() {
		this.bits  = 0;
		this.count = 0;
  }

  public BitsC(int bits, int size) {
		this.bits  = bits;
		this.count = size;
  }

  public int getBits() { return bits; }
  public int getSize() { return count; }

  public Bits addBit(int bit) {
    return new BitsC((this.getBits() << 1) | bit, this.getSize() + 1);
  }

  public void write(BinaryOut outFile) {
    if(Huff.DEBUG)
      System.out.println("writing bs=" + this.toString());
    outFile.write(this.getBits(), this.getSize());
  }

  public String toString() {

		StringBuilder sb = new StringBuilder();

		int bitsCopy = this.bits;

		for(int i = 0; i < count; i++) {
	    sb.append(bitsCopy % 2 == 0 ? "0" : "1");
	    bitsCopy = bitsCopy / 2;
		}
		return sb.reverse().toString();
  }
}
