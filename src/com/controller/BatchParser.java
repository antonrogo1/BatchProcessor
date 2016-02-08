package com.controller;

import com.domain.*;
import com.exception.*;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Anton on 2/7/2016.
 */
public class BatchParser
{
    public BatchParser()
    {}

    public Batch buildBatch(File batchFile) throws IOException, ParserConfigurationException, SAXException, ProcessException {
        Batch batch = new Batch();

        FileInputStream fis = new FileInputStream(batchFile);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fis);

        Element pnode = doc.getDocumentElement();
        NodeList nodes = pnode.getChildNodes();
        for (int idx = 0; idx < nodes.getLength(); idx++) {
            Node node = nodes.item(idx);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) node;
                this.buildCommand(elem);

            }
        }



        return batch;
    }

    private Command buildCommand(Element elem) throws ProcessException
    {
        String cmdName = elem.getNodeName();

        if (cmdName == null) {
            throw new ProcessException("unable to parse command from " + elem.getTextContent());
        }
        else if ("wd".equalsIgnoreCase(cmdName)) {
            Command command = new WDCommand();
            //Command cmd = WDCommand.parse(elem);
        }
        else if ("file".equalsIgnoreCase(cmdName)) {
            System.out.println("Parsing file");
            //Command cmd = FileCommand.parse(elem);
        }
        else if ("cmd".equalsIgnoreCase(cmdName)) {
            System.out.println("Parsing cmd");
            //Command cmd = CmdCommand.parse(elem);
            //parseCmd(elem); // Example of parsing a cmd element
        }
        else if ("pipe".equalsIgnoreCase(cmdName)) {
            System.out.println("Parsing pipe");
            //Command cmd = PipeCommand.parse(elem);
        }
        else {
            throw new ProcessException("Unknown command " + cmdName + " from: " + elem.getBaseURI());
        }

        return null;
    }


}
