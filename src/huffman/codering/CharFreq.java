/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman.codering;

import java.io.Serializable;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Frank
 */
public class CharFreq implements Comparable<CharFreq>, Comparator<CharFreq>, Serializable{
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
    
    boolean hasCharacter(){
        if (this.character == '\u0000'){
            
            return false;
            
        }
        else{
            return true;
        }
    }

    public void getCode(Character c, String code)
    {           
        if(left != null)
        {
            left.getCode(c, code + '0');
        }
        if(right != null)
        {
            right.getCode(c, code + '1');
        }
        
        if(c.equals(this.character))
        {
            Main.codes.put(c, code);
        }
    }
    
    public CharFreq returnLeft(){
        return this.left;
    }
    
    public CharFreq returnRight(){
        return this.right;
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
