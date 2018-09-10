package com.company;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;

public class filmToevoegen {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newDefaultInstance();
        dbf.setIgnoringElementContentWhitespace(true);
        dbf.setNamespaceAware(true);
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse("MOD_oef1.xml");
        Element boekElement = doc.createElement("boek");
        Element titelElement = doc.createElement("titel");
        titelElement.setTextContent("Harry Potter and the Philosopher's Stone");
        Element auteurElement = doc.createElement("Auteur");
        auteurElement.setTextContent("JK Rowling");
        auteurElement.setAttribute("geslacht", "v");
        Element beoordelingElement = doc.createElement("beoordeling");
        beoordelingElement.setTextContent("Goed");
        boekElement.appendChild(titelElement);
        boekElement.appendChild(auteurElement);
        boekElement.appendChild(beoordelingElement);
        doc.getDocumentElement().appendChild(boekElement);

        TransformerFactory tff = TransformerFactory.newDefaultInstance();
        Transformer transformer = tff.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult("MOD_oef2.xml");
        transformer.transform(source, result);
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        result = new StreamResult(System.out);
        transformer.transform(source, result);
    }
}