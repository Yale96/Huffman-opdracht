/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman.codering;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Writer;
import static java.lang.System.in;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.TreeMap;
import sun.misc.IOUtils;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author Frank
 */
public class Main {
    static Map<Character, String> codes = new HashMap<>();
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
       HuffmanCoding hc = new HuffmanCoding();
       
       hc.getText();
       System.out.println(hc.getText());
       Map<Character, Integer> returnMap = hc.getFrequency(hc.getText());
       PriorityQueue<CharFreq> p = hc.sortFreqChars(returnMap);
       
        CharFreq builtTree = hc.buildTree(p);
        
        
        hc.readCode(returnMap, builtTree);
        
        String encodedData = hc.encodeText(hc.getText(), codes);
        System.out.println(encodedData);
        hc.writeDataToFile(encodedData, builtTree);   
        String binaryResult = hc.decodeFile();
        System.out.println(binaryResult);
    }
}
