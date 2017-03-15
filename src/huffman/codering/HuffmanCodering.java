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
       System.out.println(getFrequency());
    }
    
    /*
    gets the text from a txt file, turns it into a string 'importText'
    */
    private static void getText() throws FileNotFoundException{
        Scanner scanner = new Scanner( new File("tekst.txt") );
       try{
           importText = scanner.useDelimiter("\\A").next();
       }
       catch(Exception e){
           System.out.println(e);
       }
       finally{
           scanner.close();
       }
    }
    
    private static String getFrequency(){

        TreeMap<Character, Integer> CharFreqs = new TreeMap();
        for ( char s : importText.toCharArray()) {
            Integer count = CharFreqs.get(s);
            if (count == null) {
                CharFreqs.put(s,1);
            }
            else {
                CharFreqs.put(s,count + 1);
            }
        }
        
        List<Map.Entry<Character, Integer>> gesorteerdeLijst = new LinkedList<>(CharFreqs.entrySet());
        
        Collections.sort(gesorteerdeLijst, new Comparator<Map.Entry<Character, Integer>>()
        {
            @Override
            public int compare(Map.Entry<Character, Integer> compOne,
                               Map.Entry<Character, Integer> compTwo)
            {
                return compOne.getValue().compareTo(compTwo.getValue());
            }
        });
        return gesorteerdeLijst.toString();
    }
}
