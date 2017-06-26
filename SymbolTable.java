
 // An API for a symbol table ADT used in the Huffman Coding Algorithm
 // part of a problem set in CS102.
 
 import java.util.*;

 public interface SymbolTable {

  public Object put(Integer key, STValue value);
  public STValue get(Integer key);
  public boolean containsKey(Object key);
  public int size();
  public boolean isEmpty();

  public SortedSet<Integer> keySet();
  public Collection<STValue> values();
  public void writeFrequencyTable(BinaryOut outputFile);
  public String toString();
}
