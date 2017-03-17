/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman.codering;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.TreeMap;

/**
 *
 * @author Frank
 */
public class HuffmanCodering {
    static String importText = null;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
       getText();
       System.out.println(importText);
       Map<Character, Integer> returnMap = getFrequency();
       PriorityQueue<CharFreq> p = sortFreqChars(returnMap);
       String s = "hoi";
//       while(!p.isEmpty())
//       {
//           //Met poll wordt de priority que "Leeg getrokken".
//           CharFreq cf = p.poll();
//           System.out.println(cf.getFreq() + ", " + cf.getChar());
//       }
       
        PriorityQueue<CharFreq> builtTree = buildTree(p);
        while(builtTree.size() >= 1)
        {
            CharFreq cf = builtTree.poll();
            System.out.println(cf.getFreq() + ", " + cf.left.getFreq() + ", " +  cf.left.getChar() + ", " + cf.right.getFreq() + ", " + cf.right.getChar());
        }
    }
    
    /*
    gets the text from a txt file, turns it into a string 'importText'
    */
    private static void getText() throws FileNotFoundException{
       try(Scanner scanner = new Scanner( new File("tekst.txt") )) {
           importText = scanner.useDelimiter("\\A").next();
       }
       catch(Exception e){
           System.out.println(e);
       }
    }
    
    private static Map getFrequency(){
            
        Map<Character, Integer> CharFreqs = new TreeMap();
        for (char s : importText.toCharArray()) {
            Integer count = CharFreqs.get(s);
            if (count == null) {
                CharFreqs.put(s,1);
            }
            else {
                CharFreqs.put(s,count + 1);
            }
        }
        return CharFreqs;
    }
    
    private static PriorityQueue<CharFreq> sortFreqChars(Map<Character, Integer> charInt)
    {
        PriorityQueue<CharFreq> q = new PriorityQueue<>(charInt.size());
        for(Map.Entry<Character, Integer> ci : charInt.entrySet())
        {
            q.add(new CharFreq(ci.getKey(), ci.getValue()));
        }
        return q;
    }
    
//    private static List<CharFreq> buildTree(PriorityQueue<CharFreq> queue)
//    {
//        List<CharFreq> returnList = new LinkedList<>();
//        while(queue.size() > 1)
//        {
//            CharFreq freqOne = queue.poll();
//            CharFreq freqTwo = queue.poll();
//            
//            int combined = freqOne.getFreq() + freqTwo.getFreq();
//            CharFreq combinedCharFreq = new CharFreq(combined);
//            
//            combinedCharFreq.left = freqOne;
//            combinedCharFreq.right = freqTwo;
//           
//            
//            returnList.add(combinedCharFreq);
//        }
//        return returnList;
//    }
    private static PriorityQueue<CharFreq> buildTree(PriorityQueue<CharFreq> queue)
    {
        PriorityQueue<CharFreq> returnList = new PriorityQueue<>();
        while(queue.size() > 1)
        {
            CharFreq freqOne = queue.poll();
            CharFreq freqTwo = queue.poll();
            
            int combined = freqOne.getFreq() + freqTwo.getFreq();
            CharFreq combinedCharFreq = new CharFreq(combined);
            
            combinedCharFreq.left = freqOne;
            combinedCharFreq.right = freqTwo;
           
            queue.add(combinedCharFreq);
            //returnList.add(combinedCharFreq);
        }
        returnList = queue;
        return returnList;
    }
}
