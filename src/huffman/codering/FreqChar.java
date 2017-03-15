/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman.codering;

/**
 *
 * @author Frank
 */
public class FreqChar {
   private char character;
   private int frequency;
   
   public FreqChar(){
       
   }
    
    public FreqChar(char character, int frequency){
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
}
