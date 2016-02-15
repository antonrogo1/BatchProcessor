package com.controller;

import com.domain.Batch;
import com.domain.Command;
import com.exception.ProcessException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * Created by Anton on 2/4/2016.
 */
public class BatchProcessor {

    public static void main(String args[]) throws ParserConfigurationException, SAXException, ProcessException, IOException, InterruptedException {

        String filename = null;
        if(args.length > 0) {
            filename = args[0];
        }
        else
        {
            //filename = "work/batch1.dos.xml";
            //filename = "work/batch2.dos.xml";
            //filename = "work/batch3.dos.xml";
            //filename = "work/batch4.dos.xml";
            filename = "work/batch5.dos.xml";
        }

        File batchFile = new File(filename);

        BatchParser batchParser = new BatchParser();
        Batch batch = batchParser.buildBatch(batchFile);

        executeBatch(batch);

        System.out.println("Program exited");
    }

    public static void executeBatch(Batch batch) throws IOException, InterruptedException {
        for (Command command : batch.getCommandsList())
        {
            command.describe();
            command.execute(Batch.getInstance().getWorkingDir());
        }

        System.out.println("Finished Batch");
    }

}
