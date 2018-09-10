package com.company;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.xpath.*;
import java.io.IOException;

public class Main {
    private static class MyErrorHandler implements ErrorHandler {

        @Override
        public void warning(SAXParseException exception) throws SAXException {
            System.out.printf("Waarschuwing: %s%n", exception.getMessage());
        }

        @Override
        public void error(SAXParseException exception) throws SAXException {
            System.out.printf("Error: %s%n", exception.getMessage());
        }

        @Override
        public void fatalError(SAXParseException exception) throws SAXException {
            System.out.printf("Fatal error: %s%n", exception.getMessage());
        }

        public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException, XPathExpressionException {
            // write your code here
            SchemaFactory schemaFactory = SchemaFactory.newDefaultInstance();
            Schema schema = schemaFactory.newSchema(new StreamSource("boeken.xsd"));
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newDefaultInstance();
            dbf.setNamespaceAware(true);
            dbf.setSchema(schema);
            dbf.setIgnoringElementContentWhitespace(true);
            DocumentBuilder db = dbf.newDocumentBuilder();
            db.setErrorHandler((ErrorHandler) new MyErrorHandler());
            Document doc = db.parse("MOD_oef1.xml");
            doc.getDocumentElement().normalize(); //De normalize()-methode zorgt ervoor dat tekst die verspreid is over verschillende regels toch als 1 textnode wordt gezien.
            System.out.printf("Root node: %s%n", doc.getDocumentElement().getTagName());
            NodeList children =  doc.getDocumentElement().getChildNodes();
            //toonChildren(children);
            XPathFactory xpathFactory= XPathFactory.newDefaultInstance();
            XPath xpath= xpathFactory.newXPath();
            XPathExpression expression = xpath.compile("/boeken/boek[auteur/@geslacht='v']/titel/text()");
            String titel = (String)expression.evaluate(doc, XPathConstants.STRING);
            System.out.printf("De titel van het eerste boek is: %s%n", titel);
            NodeList titels = (NodeList) expression.evaluate(doc, XPathConstants.NODESET);
            for (int i = 0; i < titels.getLength(); i++) {
                System.out.println("Titel: " + titels.item(i).getNodeValue());

            }
        }
    }

    private static void toonChildren(NodeList children) {
        for (int i = 0; i < children.getLength(); i++) {
                switch(children.item(i).getNodeType()){
                    case Node.ELEMENT_NODE:
                        System.out.printf("Node: %s%n", children.item(i).getNodeName());
                        if (children.item(i).hasChildNodes()){
                            toonChildren(children.item(i).getChildNodes());
                        }
                        break;
                    case Node.TEXT_NODE:
                        System.out.println(children.item(i).getNodeValue());
                        break;
                }
        }
    }
}
