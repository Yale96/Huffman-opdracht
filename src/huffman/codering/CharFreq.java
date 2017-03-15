/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman.codering;

import java.util.Comparator;

/**
 *
 * @author Frank
 */
public class CharFreq implements Comparable<CharFreq>, Comparator<CharFreq>{
   private char character;
   private int frequency;
   public CharFreq left;
   public CharFreq right;
   
   public CharFreq(){
       
   }
   
   public CharFreq(int frequentie){
       this.frequency = frequentie;
   }
    
    public CharFreq(char character, int frequency){
        this.character = character;
        this.frequency = frequency;
    }
    
    public void setChar(char character){
        this.character = character;
    }
    
    public void setFreq(int frequency){
        this.frequency = frequency;
    }
    
    public char getChar(){
        return this.character;
    }
    
    public int getFreq(){
        return this.frequency;
    }

   @Override
    public int compareTo(CharFreq o) {
        Integer one = this.frequency;
        Integer two = o.frequency;
        return one.compareTo(two);
    }
    
    @Override
    public int compare(CharFreq o1, CharFreq o2) {
        return o1.compareTo(o2);
    }
}
