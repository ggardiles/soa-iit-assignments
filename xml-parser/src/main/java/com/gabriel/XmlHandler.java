package com.gabriel;


import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

public class XmlHandler extends DefaultHandler {

    // Static fields
    private static final String START_TAG = "class";

    // Global fields
    private String tableName = "", sqlLine = "";
    private ArrayList<String> columns = new ArrayList<String>(),
            values = new ArrayList<String>();
    private ArrayList<String> sqlCommands = new ArrayList<String>();

    public ArrayList<String> getSqlCommands() {
        return sqlCommands;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException {

        if (qName.equalsIgnoreCase(START_TAG)) { // <class>
            sqlLine = "INSERT INTO ";
        } else if (!sqlLine.isEmpty() && tableName.isEmpty()) { // table name. i.e.: <customer>
            tableName = qName;
        } else {
            columns.add(qName); // column name. i.e.: <full-name>
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        if (qName.equalsIgnoreCase(START_TAG)) {
            // Print all commands
            for (String cmd : sqlCommands) {
                System.out.println(cmd);
            }

            // Clear sqlLine
            sqlLine = "";

        } else if (qName.equalsIgnoreCase(tableName)) {
            // Build SQL Line
            StringBuilder sb = new StringBuilder();
            sb.append(sqlLine);
            sb.append(tableName);
            sb.append(" (");

            // Add Columns
            for (String column : columns) {
                sb.append(column);
                sb.append(", ");
            }
            sb.setLength(sb.length() - 2); // Remove last comma
            sb.append(") ");

            // Add Values
            sb.append("VALUES (");
            for (String value : values) {
                sb.append(value);
                sb.append(", ");
            }
            sb.setLength(sb.length() - 2); // Remove last comma
            sb.append(")");

            // Add to commands list
            sqlCommands.add(sb.toString());

            // Clear tableName, columns and values
            tableName = "";
            columns.clear();
            values.clear();
        }
    }

    @Override
    public void characters(char ch[], int start, int length)
            throws SAXException {
        if (tableName.isEmpty()) {
            return;
        }
        if (columns.size() > values.size()) {
            values.add(new String(ch, start, length));
        }

    }
}
