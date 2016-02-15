package com.domain;

import com.exception.ProcessException;
import org.w3c.dom.Element;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by Anton on 2/4/2016.
 */
public class CmdCommand extends Command
{
    private String path;
    private List<String> cmdArgs;
    private String inId;
    private String outId;


    public CmdCommand()
    {
        super();
    }

    @Override
    public String describe()
    {
        return "Executing Cmd command " + this.getId();
    }

    @Override
    public void execute(String workingDir) throws IOException, InterruptedException
    {
        Batch batch = Batch.getInstance();

        List<String> shellcommand = new ArrayList<String>();
        shellcommand.add(this.path);
        for(String argument : this.cmdArgs)
        {
            shellcommand.add(argument);
        }

        ProcessBuilder builder = new ProcessBuilder();
        builder.command(shellcommand);

        builder.directory(new File(batch.getWorkingDir()));
        File wd = builder.directory();

        File inFile;
        FileCommand inFileCommand;
        if(inId != null)
        {
            inFileCommand =  (FileCommand)batch.getCommands().get(this.inId);

            inFile = new File(wd,inFileCommand.getPath());
            builder.redirectInput(inFile);
        }

        File outFile;
        FileCommand outFileCommand;
        if(outId != null)
        {
            outFileCommand =  (FileCommand)batch.getCommands().get(this.outId);

            outFile = new File(wd,outFileCommand.getPath());
            builder.redirectOutput(outFile);
        }


        Process process = builder.start();
        process.waitFor();

        System.out.println("Cmd " + this.getId() + " finished");
    }

    @Override
    public void parse(Element element) throws ProcessException {

        System.out.println("Parsing Cmd Command ");

        String id = element.getAttribute("id");
        if (id == null || id.isEmpty()) {
            throw new ProcessException("Missing ID in Cmd Command");
        }
        super.setId(id);


        String path = element.getAttribute("path");
        if (path == null || path.isEmpty()) {
            throw new ProcessException("Missing PATH in Cmd Command");
        }
        this.path = path;


        // Arguments must be passed to ProcessBuilder as a list of
        // individual strings.
        List<String> cmdArgs = new ArrayList<String>();
        String arg = element.getAttribute("args");
        if (!(arg == null || arg.isEmpty())) {
            StringTokenizer st = new StringTokenizer(arg);
            while (st.hasMoreTokens()) {
                String tok = st.nextToken();
                cmdArgs.add(tok);
            }
        }
        this.cmdArgs=cmdArgs;

        for(String argi: cmdArgs) {

            System.out.println("Arg " + argi);
        }

        String inID = element.getAttribute("in");
        if (!(inID == null || inID.isEmpty())) {
            this.inId=inID;
            System.out.println("inID: " + inID);
        }

        String outID = element.getAttribute("out");
        if (!(outID == null || outID.isEmpty())) {
            this.outId = outID;
            System.out.println("outID: " + outID);
        }

    }


    /**  Getters  **/

    public String getPath() {
        return path;
    }

    public String getInId() {
        return inId;
    }

    public String getOutId() {
        return outId;
    }

    public List<String> getCmdArgs()
    {
        return cmdArgs;
    }
}
