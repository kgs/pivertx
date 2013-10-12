package org.crumbleworks.mcdonnough.morsecoder;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Parser extends DefaultHandler {
    private List<MorseCodeCharacter> tempMorseCharacters;
    private MorseCodeCharacter tempMorseCharacter;

    private boolean letter, code;

    public Parser() {
        tempMorseCharacters = new ArrayList<MorseCodeCharacter>();

        letter = false;
        code = false;
    }

    public List<MorseCodeCharacter> parseDocument(String pathToDocument) {
        InputSource is = null;

        try {
            InputStream inputStream = ClassLoader.getSystemResourceAsStream(pathToDocument);
            Reader reader = new StringReader("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                    "<morsecode name=\"general\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"morsecode.xsd\">\n" +
                    "\t<morsecharacter>\n" +
                    "\t\t<letter>A</letter>\n" +
                    "\t\t<code>.-</code>\n" +
                    "\t</morsecharacter>\n" +
                    "\t<morsecharacter>\n" +
                    "\t\t<letter>B</letter>\n" +
                    "\t\t<code>-...</code>\n" +
                    "\t</morsecharacter>\n" +
                    "\t<morsecharacter>\n" +
                    "\t\t<letter>C</letter>\n" +
                    "\t\t<code>-.-.</code>\n" +
                    "\t</morsecharacter>\n" +
                    "\t<morsecharacter>\n" +
                    "\t\t<letter>D</letter>\n" +
                    "\t\t<code>-..</code>\n" +
                    "\t</morsecharacter>\n" +
                    "\t<morsecharacter>\n" +
                    "\t\t<letter>E</letter>\n" +
                    "\t\t<code>.</code>\n" +
                    "\t</morsecharacter>\n" +
                    "\t<morsecharacter>\n" +
                    "\t\t<letter>F</letter>\n" +
                    "\t\t<code>..-.</code>\n" +
                    "\t</morsecharacter>\n" +
                    "\t<morsecharacter>\n" +
                    "\t\t<letter>G</letter>\n" +
                    "\t\t<code>--.</code>\n" +
                    "\t</morsecharacter>\n" +
                    "\t<morsecharacter>\n" +
                    "\t\t<letter>H</letter>\n" +
                    "\t\t<code>....</code>\n" +
                    "\t</morsecharacter>\n" +
                    "\t<morsecharacter>\n" +
                    "\t\t<letter>I</letter>\n" +
                    "\t\t<code>..</code>\n" +
                    "\t</morsecharacter>\n" +
                    "\t<morsecharacter>\n" +
                    "\t\t<letter>J</letter>\n" +
                    "\t\t<code>.---</code>\n" +
                    "\t</morsecharacter>\n" +
                    "\t<morsecharacter>\n" +
                    "\t\t<letter>K</letter>\n" +
                    "\t\t<code>-.-</code>\n" +
                    "\t</morsecharacter>\n" +
                    "\t<morsecharacter>\n" +
                    "\t\t<letter>L</letter>\n" +
                    "\t\t<code>.-..</code>\n" +
                    "\t</morsecharacter>\n" +
                    "\t<morsecharacter>\n" +
                    "\t\t<letter>M</letter>\n" +
                    "\t\t<code>--</code>\n" +
                    "\t</morsecharacter>\n" +
                    "\t<morsecharacter>\n" +
                    "\t\t<letter>N</letter>\n" +
                    "\t\t<code>-.</code>\n" +
                    "\t</morsecharacter>\n" +
                    "\t<morsecharacter>\n" +
                    "\t\t<letter>O</letter>\n" +
                    "\t\t<code>---</code>\n" +
                    "\t</morsecharacter>\n" +
                    "\t<morsecharacter>\n" +
                    "\t\t<letter>P</letter>\n" +
                    "\t\t<code>.--.</code>\n" +
                    "\t</morsecharacter>\n" +
                    "\t<morsecharacter>\n" +
                    "\t\t<letter>Q</letter>\n" +
                    "\t\t<code>--.-</code>\n" +
                    "\t</morsecharacter>\n" +
                    "\t<morsecharacter>\n" +
                    "\t\t<letter>R</letter>\n" +
                    "\t\t<code>.-.</code>\n" +
                    "\t</morsecharacter>\n" +
                    "\t<morsecharacter>\n" +
                    "\t\t<letter>S</letter>\n" +
                    "\t\t<code>...</code>\n" +
                    "\t</morsecharacter>\n" +
                    "\t<morsecharacter>\n" +
                    "\t\t<letter>T</letter>\n" +
                    "\t\t<code>-</code>\n" +
                    "\t</morsecharacter>\n" +
                    "\t<morsecharacter>\n" +
                    "\t\t<letter>U</letter>\n" +
                    "\t\t<code>..-</code>\n" +
                    "\t</morsecharacter>\n" +
                    "\t<morsecharacter>\n" +
                    "\t\t<letter>V</letter>\n" +
                    "\t\t<code>...-</code>\n" +
                    "\t</morsecharacter>\n" +
                    "\t<morsecharacter>\n" +
                    "\t\t<letter>W</letter>\n" +
                    "\t\t<code>.--</code>\n" +
                    "\t</morsecharacter>\n" +
                    "\t<morsecharacter>\n" +
                    "\t\t<letter>X</letter>\n" +
                    "\t\t<code>-..-</code>\n" +
                    "\t</morsecharacter>\n" +
                    "\t<morsecharacter>\n" +
                    "\t\t<letter>Y</letter>\n" +
                    "\t\t<code>-.--</code>\n" +
                    "\t</morsecharacter>\n" +
                    "\t<morsecharacter>\n" +
                    "\t\t<letter>Z</letter>\n" +
                    "\t\t<code>--..</code>\n" +
                    "\t</morsecharacter>\n" +
                    "\t\n" +
                    "\t<morsecharacter>\n" +
                    "\t\t<letter>0</letter>\n" +
                    "\t\t<code>-----</code>\n" +
                    "\t</morsecharacter>\n" +
                    "\t<morsecharacter>\n" +
                    "\t\t<letter>1</letter>\n" +
                    "\t\t<code>.----</code>\n" +
                    "\t</morsecharacter>\n" +
                    "\t<morsecharacter>\n" +
                    "\t\t<letter>2</letter>\n" +
                    "\t\t<code>..---</code>\n" +
                    "\t</morsecharacter>\n" +
                    "\t<morsecharacter>\n" +
                    "\t\t<letter>3</letter>\n" +
                    "\t\t<code>...--</code>\n" +
                    "\t</morsecharacter>\n" +
                    "\t<morsecharacter>\n" +
                    "\t\t<letter>4</letter>\n" +
                    "\t\t<code>....-</code>\n" +
                    "\t</morsecharacter>\n" +
                    "\t<morsecharacter>\n" +
                    "\t\t<letter>5</letter>\n" +
                    "\t\t<code>.....</code>\n" +
                    "\t</morsecharacter>\n" +
                    "\t<morsecharacter>\n" +
                    "\t\t<letter>6</letter>\n" +
                    "\t\t<code>-....</code>\n" +
                    "\t</morsecharacter>\n" +
                    "\t<morsecharacter>\n" +
                    "\t\t<letter>7</letter>\n" +
                    "\t\t<code>--...</code>\n" +
                    "\t</morsecharacter>\n" +
                    "\t<morsecharacter>\n" +
                    "\t\t<letter>8</letter>\n" +
                    "\t\t<code>---..</code>\n" +
                    "\t</morsecharacter>\n" +
                    "\t<morsecharacter>\n" +
                    "\t\t<letter>9</letter>\n" +
                    "\t\t<code>----.</code>\n" +
                    "\t</morsecharacter>\n" +
                    "\t\n" +
                    "\t<morsecharacter>\n" +
                    "\t\t<letter>.</letter>\n" +
                    "\t\t<code>.-.-.-</code>\n" +
                    "\t</morsecharacter>\n" +
                    "\t<morsecharacter>\n" +
                    "\t\t<letter> </letter>\n" +
                    "\t\t<code>/</code>\n" +
                    "\t</morsecharacter>\n" +
                    "\t<morsecharacter>\n" +
                    "\t\t<letter>,</letter>\n" +
                    "\t\t<code>--..--</code>\n" +
                    "\t</morsecharacter>\n" +
                    "\t<morsecharacter>\n" +
                    "\t\t<letter>:</letter>\n" +
                    "\t\t<code>---...</code>\n" +
                    "\t</morsecharacter>\n" +
                    "\t<morsecharacter>\n" +
                    "\t\t<letter>;</letter>\n" +
                    "\t\t<code>-.-.-.</code>\n" +
                    "\t</morsecharacter>\n" +
                    "\t<morsecharacter>\n" +
                    "\t\t<letter>?</letter>\n" +
                    "\t\t<code>--..--</code>\n" +
                    "\t</morsecharacter>\n" +
                    "\t<morsecharacter>\n" +
                    "\t\t<letter>-</letter>\n" +
                    "\t\t<code>-....-</code>\n" +
                    "\t</morsecharacter>\n" +
                    "\t<morsecharacter>\n" +
                    "\t\t<letter>_</letter>\n" +
                    "\t\t<code>--..-.</code>\n" +
                    "\t</morsecharacter>\n" +
                    "\t<morsecharacter>\n" +
                    "\t\t<letter>(</letter>\n" +
                    "\t\t<code>-.--.</code>\n" +
                    "\t</morsecharacter>\n" +
                    "\t<morsecharacter>\n" +
                    "\t\t<letter>)</letter>\n" +
                    "\t\t<code>-.--.-</code>\n" +
                    "\t</morsecharacter>\n" +
                    "\t<morsecharacter>\n" +
                    "\t\t<letter>'</letter>\n" +
                    "\t\t<code>.----.</code>\n" +
                    "\t</morsecharacter>\n" +
                    "\t<morsecharacter>\n" +
                    "\t\t<letter>=</letter>\n" +
                    "\t\t<code>-...-</code>\n" +
                    "\t</morsecharacter>\n" +
                    "\t<morsecharacter>\n" +
                    "\t\t<letter>+</letter>\n" +
                    "\t\t<code>.-.-.</code>\n" +
                    "\t</morsecharacter>\n" +
                    "\t<morsecharacter>\n" +
                    "\t\t<letter>/</letter>\n" +
                    "\t\t<code>-..-.</code>\n" +
                    "\t</morsecharacter>\n" +
                    "\t<morsecharacter>\n" +
                    "\t\t<letter>@</letter>\n" +
                    "\t\t<code>.--.-.</code>\n" +
                    "\t</morsecharacter>\n" +
                    "\t\n" +
                    "\t<morsecharacter>\n" +
                    "\t\t<letter>[AA]</letter>\n" +
                    "\t\t<code>.-.-</code>\n" +
                    "\t</morsecharacter>\n" +
                    "\t<morsecharacter>\n" +
                    "\t\t<letter>[AR]</letter>\n" +
                    "\t\t<code>.-.-.</code>\n" +
                    "\t</morsecharacter>\n" +
                    "\t<morsecharacter>\n" +
                    "\t\t<letter>[AS]</letter>\n" +
                    "\t\t<code>.-...</code>\n" +
                    "\t</morsecharacter>\n" +
                    "\t<morsecharacter>\n" +
                    "\t\t<letter>[BK]</letter>\n" +
                    "\t\t<code>-...-.-</code>\n" +
                    "\t</morsecharacter>\n" +
                    "\t<morsecharacter>\n" +
                    "\t\t<letter>[BT]</letter>\n" +
                    "\t\t<code>-...-</code>\n" +
                    "\t</morsecharacter>\n" +
                    "\t<morsecharacter>\n" +
                    "\t\t<letter>[CL]</letter>\n" +
                    "\t\t<code>-.-..-..</code>\n" +
                    "\t</morsecharacter>\n" +
                    "\t<morsecharacter>\n" +
                    "\t\t<letter>[CT]</letter>\n" +
                    "\t\t<code>-.-.-</code>\n" +
                    "\t</morsecharacter>\n" +
                    "\t<morsecharacter>\n" +
                    "\t\t<letter>[K]</letter>\n" +
                    "\t\t<code>-.-</code>\n" +
                    "\t</morsecharacter>\n" +
                    "\t<morsecharacter>\n" +
                    "\t\t<letter>[KN]</letter>\n" +
                    "\t\t<code>-.--.</code>\n" +
                    "\t</morsecharacter>\n" +
                    "\t<morsecharacter>\n" +
                    "\t\t<letter>[SK]</letter>\n" +
                    "\t\t<code>...-.-</code>\n" +
                    "\t</morsecharacter>\n" +
                    "\t<morsecharacter>\n" +
                    "\t\t<letter>[SN]</letter>\n" +
                    "\t\t<code>...-.</code>\n" +
                    "\t</morsecharacter>\n" +
                    "\t<morsecharacter>\n" +
                    "\t\t<letter>[SOS]</letter>\n" +
                    "\t\t<code>...---...</code>\n" +
                    "\t</morsecharacter>\n" +
                    "</morsecode>");
            is = new InputSource(reader);
            is.setEncoding("UTF-8");
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        SAXParserFactory spf = SAXParserFactory.newInstance();

        try {
            SAXParser sp = spf.newSAXParser();
            sp.parse(is, this);
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return tempMorseCharacters;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if(qName.equalsIgnoreCase("morsecharacter")) {
            tempMorseCharacter = new MorseCodeCharacter();
        }
        else if(qName.equalsIgnoreCase("letter")) {
            letter = true;
        }
        else if(qName.equalsIgnoreCase("code")) {
            code = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        if(letter) {
            tempMorseCharacter.setLetter(new String(ch, start, length));
            letter = false;
        }
        else if(code) {
            tempMorseCharacter.setCode(new String(ch, start, length));
            code = false;
            tempMorseCharacters.add(tempMorseCharacter);
        }
    }
}