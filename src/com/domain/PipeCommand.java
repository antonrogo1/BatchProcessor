package com.domain;

import com.controller.BatchParser;
import com.exception.ProcessException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by Anton on 2/7/2016.
 */
public class PipeCommand extends Command
{
    List<CmdCommand> commands;

    public PipeCommand()
    {}

    @Override
    public String describe() {
        return "Executing Pipe command " + this.getId();
    }

    @Override
    public void execute(String workingDir) throws IOException, InterruptedException {
        System.out.println("Executing Pipe Command");



        CmdCommand firstCommand = (CmdCommand)this.commands.get(0);
        CmdCommand secondCommand = (CmdCommand)this.commands.get(1);


        Batch batch = Batch.getInstance();

        List<String> shellcommand = new ArrayList<String>();
        shellcommand.add(firstCommand.getPath());
        for(String argument : firstCommand.getCmdArgs())
        {
            shellcommand.add(argument);
        }

        ProcessBuilder builder = new ProcessBuilder();
        builder.command(shellcommand);

        builder.directory(new File(batch.getWorkingDir()));
        File wd = builder.directory();

        final Process process = builder.start();

        File inFile;
        FileCommand inFileCommand;
        if(firstCommand.getInId() != null)
        {
            inFileCommand =  (FileCommand)batch.getCommands().get(firstCommand.getInId());
            inFile = new File(wd,inFileCommand.getPath());

            OutputStream os = process.getOutputStream();
            FileInputStream fis = new FileInputStream(inFile);

            int achar;
            while ((achar = fis.read()) != -1) {
                os.write(achar);
            }
            os.close();

            //builder.redirectInput(inFile);
        }

        File outFile;
        FileCommand outFileCommand;
        if(secondCommand.getOutId() != null)
        {
            outFileCommand =  (FileCommand)batch.getCommands().get(secondCommand.getOutId());

            outFile = new File(wd,outFileCommand.getPath());
            FileOutputStream fos = new FileOutputStream(outFile);

            int achar;
            InputStream is = process.getInputStream();
            while ((achar = is.read()) != -1) {
                fos.write(achar);
            }
            fos.close();

            builder.redirectOutput(outFile);
        }

        process.waitFor();

        System.out.println("Program terminated!");
    }

    @Override
    public void parse(Element element) throws ProcessException
    {

        System.out.println("Parsing Pipe Command");
        Batch batch = Batch.getInstance();

        String id = element.getAttribute("id");
        if (id == null || id.isEmpty()) {
            throw new ProcessException("Missing ID in Pipe Command");
        }
        super.setId(id);

        if(element.hasChildNodes())
        {
            System.out.println("Found Commands connected by Pipe");
        }


        NodeList nodes = element.getChildNodes();

        for (int idx = 0; idx < nodes.getLength(); idx++)
        {
            System.out.println("Parsing commands connected by Pipe");
            Node node = nodes.item(idx);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) node;

                CmdCommand command = new CmdCommand();
                command.parse(elem);

                if(this.commands == null)
                    this.commands = new ArrayList<CmdCommand>();

                this.commands.add(command);
            }
            System.out.println("Finished parsing commands inside Pipe");
        }
    }
}
