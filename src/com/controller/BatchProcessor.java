package com.controller;

import com.domain.Batch;
import com.exception.ProcessException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * Created by Anton on 2/4/2016.
 */
public class BatchProcessor {

    public static void main(String args[]) throws ParserConfigurationException, SAXException, ProcessException, IOException {

        String filename = null;
        if(args.length > 0) {
            filename = args[0];
        }
        else
        {
            filename = "work/batch1.dos.xml";
        }

        File batchFile = new File(filename);

        BatchParser batchParser = new BatchParser();
        batchParser.buildBatch(batchFile);

    }

}
