
// This file contains an implementation of the SymbolTable ADT that is
// used in the Huffman Coding/Decoding algorithm. We implement the
// symbol table by composition with the java.util.HashMap class. (See
// Bloch Item 16).

import java.util.*;
import java.io.*;

public class SymbolTableC implements SymbolTable {

  private Map<Integer, STValue> st;

  // This constructor is used in the Huff program.
  public SymbolTableC(FileReader inputFile) {

    this.st = new HashMap<Integer, STValue>();
    int c = 0;

    try {
      while(c != -1) {
        c = inputFile.read();           //read returns -1 at EOF.
        if (Huff.DEBUG)
          System.out.format("read c = %s, 0x%x%n", (char) c, c);
        if(c != -1) {
          if(st.containsKey(c)) {
            STValue stv = st.get(c);
            int frequency = stv.getFrequency();
            stv.setFrequency(frequency + 1);
          }
          else
            st.put(new Integer(c), new STValueC(1));
        }
      }
      inputFile.close();
    }
    catch (IOException e) {
      System.out.println("Encountered IOException when reading the input file.");
    }
  }

  // This constructor is used to reconstitute the symbol table from
  // the information in the zip file. Used in the Puff program.
  public SymbolTableC(BinaryIn inputFile) {

    this.st = new HashMap<Integer, STValue>();

    int size = inputFile.readInt();

    for(int i = 0; i < size; i++) {

      char c = inputFile.readChar();
      int frequency = inputFile.readInt();

      Integer key = new Integer(c);
      STValue stv = new STValueC(frequency);

      st.put(key, stv);
    }
  }

  // This routine writes the frequency table part of the symbol
  // table to the zip file.
  public void writeFrequencyTable(BinaryOut outputFile) {

    outputFile.write(this.size());

    Set<Integer> keys = this.keySet();

    for(Integer key : keys) {
      STValue v = this.get(key);
      int frequency = v.getFrequency();
      outputFile.write((char) key.intValue());
      outputFile.write(frequency);
    }
  }

  // Standard issue.
  public boolean containsKey(Object key) { return st.containsKey(key); }
  public STValue get(Integer key) { return st.get(key); }
  public boolean isEmpty() { return st.isEmpty(); }
  public Object  put(Integer key, STValue value) {
    return st.put(key, value);
  }
  public int size() { return st.size(); }
  public SortedSet<Integer> keySet() { return new TreeSet(st.keySet()); }
  public Collection<STValue> values() { return st.values(); }
  public String toString() { return st.toString(); }
}
