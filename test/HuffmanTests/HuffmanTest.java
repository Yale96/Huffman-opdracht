/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HuffmanTests;

import huffman.codering.CharFreq;
import huffman.codering.HuffmanCoding;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author yanni
 */
public class HuffmanTest {
    
    public HuffmanTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    HuffmanCoding hc = new HuffmanCoding();
    String text = "Dit is + JcF";
    
    @Test
    public void testGetText() throws FileNotFoundException
    {
        String expected = "Vandaag ben ik naar school geweest en op school was het druk geweest. // -0 - + ==";
        assertEquals(expected, hc.getText());
    }
    
    @Test
    public void testGetFrequency()
    {
        Map<Character, Integer> mapje = new TreeMap();
        mapje.put(' ', 3);
        mapje.put('+', 1);
        mapje.put('D', 1);
        mapje.put('F', 1);
        mapje.put('J', 1);
        mapje.put('c', 1);
        mapje.put('i', 2);
        mapje.put('s', 1);
        mapje.put('t', 1);
        
        assertEquals(mapje, hc.getFrequency(text));
    }
    
    @Test
    public void testSortFreqChars()
    {
        Map<Character, Integer> mapje = new TreeMap();
        mapje.put(' ', 3);
        mapje.put('+', 1);
        mapje.put('D', 1);
        mapje.put('F', 1);
        mapje.put('J', 1);
        mapje.put('c', 1);
        mapje.put('i', 2);
        mapje.put('s', 1);
        mapje.put('t', 1);
        
        PriorityQueue<CharFreq> q = new PriorityQueue<>(mapje.size());
        
        for(Map.Entry<Character, Integer> ci : mapje.entrySet())
        {
            q.add(new CharFreq(ci.getKey(), ci.getValue()));
        }
        
        assertEquals(q, hc.sortFreqChars(mapje));
    }
}
