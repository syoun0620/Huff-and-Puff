# Huff-and-Puff
a Program that transfer a text file into compressed file by using Huffman Algorithm that achieves a lossless data compression.

The algorithm implemented here accepts a single command line
argument that should be a filename of the form name.txt, where the
extension ".txt" is required. It then proceeds as follows:

1. Read all of the characters from the input file and create a
   symbol table that records the frequency of each character that
   appears in the input file.

2. Use the constructed symbol table as the basis for constructing
   the corresponding Huffman coding tree.

3. Using the Huffman coding tree, fill in the symbol table entry
   for each character to record the bit string assigned to that
   character in the Huffman coding tree.

4. Create the compressed file.
