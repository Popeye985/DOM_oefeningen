package com.company;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.IOException;
import java.util.Scanner;

public class Mendeljev3 {
        public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, XPathExpressionException {
            Scanner invoer = new Scanner (System.in);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newDefaultInstance();
            dbf.setIgnoringElementContentWhitespace(true);
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse("elements.xml");
            XPathFactory factory = XPathFactory.newDefaultInstance();
            XPath xpath = factory.newXPath();
            Scanner scanner = new Scanner(System.in);
            System.out.print("Geef een symbool: ");
            String symbool = scanner.nextLine();
            String expression = String.format("elements/element[symbol/text()='%s']/name/text()", symbool);
            String elementnaam = (String)xpath.evaluate(expression,doc, XPathConstants.STRING);
            if (elementnaam.length() > 0) {
                System.out.printf("Het element met symbool %s heet %s%n", symbool, elementnaam);
            }else{
                System.out.println("Dit element bestaat niet.");
            }

        }
    }



