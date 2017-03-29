/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HuffmanTests;

import huffman.codering.CharFreq;
import huffman.codering.HuffmanCoding;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
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
    public void testGetText() throws FileNotFoundException {
        String expected = "Vandaag ben ik naar school geweest en op school was het druk geweest. // -0 - + ==";
        assertEquals(expected, hc.getText("tekst.txt"));
    }

    @Test
    public void testGetTextWithException() {
        try {
            hc.getText("jcf");
        } catch (FileNotFoundException ex) {
            System.out.print(ex.toString());
        }

    }

    @Test
    public void testGetFrequency() {
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
    public void testSortFreqChars() {
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
        PriorityQueue<CharFreq> queue = hc.sortFreqChars(mapje);

        for (Map.Entry<Character, Integer> ci : mapje.entrySet()) {
            q.add(new CharFreq(ci.getKey(), ci.getValue()));
        }

        while (q.size() > 0) {
            CharFreq cf = q.poll();
            CharFreq cg = queue.poll();
            assertEquals(cf.getChar(), cg.getChar());
            assertEquals(cf.getFreq(), cg.getFreq());
        }
    }

    @Test
    public void testBuildTree() {
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
        for (Map.Entry<Character, Integer> ci : mapje.entrySet()) {
            q.add(new CharFreq(ci.getKey(), ci.getValue()));
        }

        CharFreq cf = hc.buildTree(q);
        assertEquals('F', cf.left.left.left.getChar());
    }

    @Test
    public void testReadCode() {
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
        for (Map.Entry<Character, Integer> ci : mapje.entrySet()) {
            q.add(new CharFreq(ci.getKey(), ci.getValue()));
        }

        CharFreq cf = hc.buildTree(q);

        hc.readCode(mapje, cf);

        Map<Character, String> mapp = new HashMap<>();
        mapp.put(' ', "10");
        mapp.put('c', "010");
        mapp.put('s', "001");
        mapp.put('D', "0111");
        mapp.put('t', "1111");
        mapp.put('F', "000");
        mapp.put('i', "110");
        mapp.put('J', "0110");
        mapp.put('+', "1110");

        assertEquals(mapp, hc.codes);
    }

    @Test
    public void testEncodeText() {
        Map<Character, String> mapp = new HashMap<>();
        mapp.put(' ', "10");
        mapp.put('c', "010");
        mapp.put('s', "001");
        mapp.put('D', "0111");
        mapp.put('t', "1111");
        mapp.put('F', "000");
        mapp.put('i', "110");
        mapp.put('J', "0110");
        mapp.put('+', "1110");

        String result = hc.encodeText(text, mapp);
        assertEquals("0111110111110110001101110100110010000", result);
    }

    @Test
    public void testReadWriteDataToFile() throws IOException, ClassNotFoundException {
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
        for (Map.Entry<Character, Integer> ci : mapje.entrySet()) {
            q.add(new CharFreq(ci.getKey(), ci.getValue()));
        }

        CharFreq cf = hc.buildTree(q);

        hc.writeDataToFile("0111110111110110001101110100110010000", cf, "key.ser");

        assertEquals(text, hc.decodeFile());
    }

    @Test
    public void testWriteException() {
        try {
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
            for (Map.Entry<Character, Integer> ci : mapje.entrySet()) {
                q.add(new CharFreq(ci.getKey(), ci.getValue()));
            }

            CharFreq cf = hc.buildTree(q);

            hc.writeDataToFile("0111110111110110001101110100110010000", cf, "key?.ser");
        } catch (IOException ex) {
            System.out.print(ex.toString());
        }

    }

}
