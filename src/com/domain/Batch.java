package com.domain;

import java.util.*;

/**
 * Created by Anton on 2/4/2016.
 */
public class Batch
{
    private String workingDir;
    private Map<String,Command> cmdLookup;
    private List<Command> commandList;

    public Batch()
    {
        this.workingDir = workingDir;
        this.cmdLookup = new LinkedHashMap<String, Command>();
        this.commandList =  new ArrayList<Command>();
    }

    public void addCommand(Command command)
    {
        this.commandList.add(command);
    }

    public List<Command> getCommandsList()
    {
        return this.commandList;
    }

    public String getWorkingDir()
    {
        return this.workingDir;
    }

    public Map<String,Command> getCommands()
    {
        return this.cmdLookup;
    }



    public void setWorkingDir(String workingDir) {
        this.workingDir = workingDir;
    }
}
