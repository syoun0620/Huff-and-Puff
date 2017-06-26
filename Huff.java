
// This is the Huffman compression half of the Huffman
// compression/decompression algorithm. (This is sometimes called a
// codec for "code"/"decode".)
//
// 1. This program isn't working properly on files other than standard
//    ASCII text, that is, character codes 00 - 127. This seems to be a
//    problem with the read function.
//
// The Algorithm
//
// The algorithm implemented here accepts a single command line
// argument that should be a filename of the form name.txt, where the
// extension ".txt" is required. It then proceeds as follows:
//
// 1. Read all of the characters from the input file and create a
//    symbol table that records the frequency of each character that
//    appears in the input file.
//
// 2. Use the constructed symbol table as the basis for constructing
//    the corresponding Huffman coding tree.
//
// 3. Using the Huffman coding tree, fill in the symbol table entry
//    for each character to record the bit string assigned to that
//    character in the Huffman coding tree.
//
// 4. Create the compressed file.


import java.io.*;
import java.util.*;

public class Huff {

  // The following creates a hexadecimal (base 16) constant.
  //
  public static final int MAGIC_NUMBER = 0x0bc0;
  public static final boolean DEBUG = false;
  public static void dbgOut(String s) {
    if (DEBUG) System.out.print(s);
  }
  public static void dbgOutln(String s) {
    if (DEBUG) System.out.println(s);
  }

  private final String[] args;

  public Huff(String[] args) {
    this.args = args;
  }

  // This is the main routine in the Huffman Coding Algorithm.
  private void go() {

    FileIO io = new FileIOC(this.args);

    FileReader inputFile = io.openInputFile(this.args[0]);
    dbgOutln("go: opened input file " + this.args[0]);

    SymbolTable st = new SymbolTableC(inputFile);
    dbgOutln("go: symbol table = " + st.toString());

    HuffmanTree ht = new HuffmanTreeC(st);
    dbgOutln("go: Huffman coding tree = " + ht.toString());

    // We'll now recursively walk the tree building up the bit
    // strings as we go.  When we reach a leaf node, we'll add
    // the computed bit string to its symbol table entry. This
    // will facilitate writing the bit strings for the input
    // letters.
    ht.computeBitCodes(st, new BitsC());
    dbgOutln("go: symbol table = " + st.toString());

    // We now have everything we need to write the compressed
    // file. First reopen the source file.
    inputFile = io.openInputFile(this.args[0]);

    BinaryOut outputFile = io.openBinaryOutputFile();

    // 1. write the magic number.
    outputFile.write(MAGIC_NUMBER, 16);

    // 2. write out the frequency table.
    dbgOutln("go: symbol table size = " + st.size());
    st.writeFrequencyTable(outputFile);

    // 3. read through the input text file again. This time, write
    // the variable length bit strings to the binary output file.
    int c = 0;
    try {
      while(c != -1) {
        c = inputFile.read();

        if(c != -1) {
          Integer key = new Integer(c);
          STValue stv = st.get(key);
          Bits bits = stv.getBits();
          bits.write(outputFile);

          dbgOutln("go: wrote " + (char) c + " = " + bits.toString());
        }
      }
      inputFile.close();
      outputFile.flush();
      outputFile.close();
    }
    catch (IOException e) {
      System.out.println("hit with this IOException");
    }
  }

  public static void main(String[] args) {
    new Huff(args).go();
  }

}
