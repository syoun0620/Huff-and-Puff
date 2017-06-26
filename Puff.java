
import java.io.*;
import java.util.*;

public class Puff {
  
  private static final int magicNumber = 0x0BC0;
  private BinaryIn zipInput;
  private BinaryIn zipInput2;
  
  public static void main(String[] args) throws IOException{
    
    FileIO io = new FileIOC(args);
    BinaryIn zipInput = io.openBinaryInputFile(args[0]);
    BinaryIn zipInput2 = io.openBinaryInputFile(args[0]);
    
    int helperN = zipInput2.readInt(16);
    int N = zipInput2.readInt(32);
    
    FileWriter outputFile = io.openOutputFile();
    
    puffAway(args, zipInput, outputFile, N);
    
    outputFile.flush(); // need this in order to work
    outputFile.close();
  }
  
  // puff Away method
  
  public static void puffAway(String[] args, BinaryIn inputFile, FileWriter outputFile, int size) throws IOException{
    
    if (testHuffPedigree(args, inputFile)){
      //create new symbole table
      SymbolTable newTable = new SymbolTableC(inputFile); 
      //create new huffman tree
      HuffmanTree newHuffTree = new HuffmanTreeC(newTable);
      
      for (int i = 0; i < size; i++){
        while(!inputFile.isEmpty()){
          traverse(newHuffTree, inputFile, outputFile, size);
        }
      }
    }
    
    else{
      System.out.println("Error: File is not compressing");
    }
  }
  
  // testing compression
  
  public static boolean testHuffPedigree(String[] args, BinaryIn inputFile){
  
    int testNum = inputFile.readInt(16);
    
    if(testNum!=magicNumber){
      
      return false;
    }
    
    return true;
  }
  
  // traversing
  
  public static void traverse(HuffmanTree letter, BinaryIn input, FileWriter outputFile, int N) throws IOException{
    
    HuffmanTree mover = letter;
    Character holder;
    
    if(!mover.isLeaf()){
      if(!input.isEmpty()){
        int temp = input.readInt(1);
        
        if(temp!=-1){
          if(temp==0){
            mover = mover.getLeft();
            traverse(mover,input,outputFile,N);
            
          }else if(temp==1){
            mover=mover.getRight();
            traverse(mover,input,outputFile,N);
          }
        }
      }  
    }
    else if(mover.isLeaf()){
      holder = mover.getSymbol();
      
      if(!holder.equals(null)){
        outputFile.write(holder);
      }
    }
  }
}

                                   
    
   
  
    
                                                           
                                                       
    
