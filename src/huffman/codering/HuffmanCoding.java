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
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.TreeMap;

/**
 *
 * @author yanni
 */
public class HuffmanCoding {
    
    public HuffmanCoding()
    {
        
    }
     /*
    gets the text from a txt file, turns it into a string 'importText'
    */
    public static String getText() throws FileNotFoundException{
        String text = "";
       try(Scanner scanner = new Scanner( new File("tekst.txt") )) {
           text = scanner.useDelimiter("\\A").next();
           return text;
       }
       catch(Exception e){
           System.out.println(e);
           return text;
       }
    }
    
    public static Map getFrequency(String text){
            
        Map<Character, Integer> CharFreqs = new TreeMap();
        for (char s : text.toCharArray()) {
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
    
    public static PriorityQueue<CharFreq> sortFreqChars(Map<Character, Integer> charInt)
    {
        PriorityQueue<CharFreq> q = new PriorityQueue<>(charInt.size());
        for(Map.Entry<Character, Integer> ci : charInt.entrySet())
        {
            q.add(new CharFreq(ci.getKey(), ci.getValue()));
        }
        return q;
    }

    public static CharFreq buildTree(PriorityQueue<CharFreq> queue)
    {
        
        while(queue.size() > 1)
        {
            CharFreq freqOne = queue.poll();
            CharFreq freqTwo = queue.poll();
            
            int combined = freqOne.getFreq() + freqTwo.getFreq();
            CharFreq combinedCharFreq = new CharFreq(combined);
            
            combinedCharFreq.left = freqOne;
            combinedCharFreq.right = freqTwo;
           
            queue.add(combinedCharFreq);
        }
        CharFreq cf = queue.poll();
        return cf;
    }
    
    public static HashMap<Character, String> readCode(Map<Character, Integer> returnMap, CharFreq builtTree)
    {
        HashMap<Character, String> returnReadMap = new HashMap<>();
        
        HashMap<Character, String> map = new HashMap<>();
        
        for(Map.Entry<Character, Integer> m : returnMap.entrySet())
        {
            
            String s = "";
            Character c = m.getKey();
            
            builtTree.getCode(c, s);
        }
        return returnReadMap;
    }
    
    public static String encodeText(String inputText, Map<Character, String> codes){
        
        String result = "";
        
        for(Character c : inputText.toCharArray()){
            String code = codes.get(c);
            result += code;
        }
        
        return result;
    }
    
    public static void writeDataToFile(String onesandzeros, CharFreq tree) throws IOException{
        FileOutputStream fos = new FileOutputStream("encoded.dat");
        
        for(int i = 0; i < onesandzeros.length(); i = i + 8){
            if((i + 8) < onesandzeros.length()){
                int byteInt = Integer.parseInt(onesandzeros.substring(i, i + 8), 2);
                byte b = (byte)byteInt;
                fos.write(b);   
            }
            else{
                int toAddCharacters = i + 8 - onesandzeros.length();
                for(int j = 0; j < toAddCharacters; j++){
                    onesandzeros += "0";
                }
                int byteInt = Integer.parseInt(onesandzeros.substring(i, i + 8), 2);   
                byte b = (byte)byteInt;
                fos.write(b);
            }
        }
        fos.close();    
        
        try
           {
                  ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("key.ser"));
                  oos.writeObject(tree);
                  oos.close();
           }
        catch(IOException ioe){
                  ioe.printStackTrace();
            }
    }
    
    public static String decodeFile() throws IOException, ClassNotFoundException{
        File file = new File("encoded.dat");
        byte[] fileData = new byte[(int) file.length()];
        DataInputStream dis = new DataInputStream(new FileInputStream(file));
        dis.readFully(fileData);
        dis.close();    
    
        String binaryResult = "";
        for(byte b: fileData){
        String s = String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0'); //http://stackoverflow.com/questions/12310017/how-to-convert-a-byte-to-its-binary-string-representation
        binaryResult += s;
        }
             
        ObjectInputStream fis = new ObjectInputStream(new FileInputStream("key.ser"));
        CharFreq tree = (CharFreq) fis.readObject();
        
        String finalText = "";
        CharFreq cf = tree;
        for(char c: binaryResult.toCharArray()){
            
                    if(c == '0'){
                        cf = cf.returnLeft();
                    }
                    if(c == '1'){
                        cf = cf.returnRight();
                    }
                    
                    if(cf.hasCharacter()){
                        finalText += cf.getChar();   
                        cf = tree;   
                    }
        }
        return finalText;
    }
}
