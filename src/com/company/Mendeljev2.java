package com.company;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.IOException;

    public class Mendeljev2 {
        public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, XPathExpressionException {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newDefaultInstance();
            dbf.setIgnoringElementContentWhitespace(true);
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse("elements.xml");
            XPathFactory factory = XPathFactory.newDefaultInstance();
            XPath xpath = factory.newXPath();
            XPathExpression expression = xpath.compile("/elements/element[symbol/text()='sb']/name/text()");
            String symboolnaam = (String)expression.evaluate(doc, XPathConstants.STRING);
            System.out.printf("Het element met symbool Sb heet %s%n", symboolnaam);

            }
        }



