package com.gabriel;


import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.ArrayList;

public class XmlParserRunner {

    public static void main(String[] args) {
        try {
            // Get XML Filename
            if (args.length < 1) {
                System.out.println("Provide XMl filename.");
                return;
            }
            String xmlName = args[0];
            System.out.println("XML file: " + xmlName);

            // Get SAX Instance
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            // Parse File
            XmlHandler xmlHandler = new XmlHandler();
            saxParser.parse(xmlName, xmlHandler);

            // Get SQL Commands
            ArrayList<String> sqlCommands = xmlHandler.getSqlCommands();

            // Execute SQL using JDBC
            DAO dao = new DAO();
            dao.executeSQL(sqlCommands);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
