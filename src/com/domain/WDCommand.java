package com.domain;

import org.w3c.dom.Element;
import com.exception.ProcessException;

import java.io.File;

/**
 * Created by Anton on 2/7/2016.
 */
public class WDCommand extends Command
{

    private String path;

    public WDCommand()
    {
        super();
    }

    @Override
    public String describe() {
        return "Parsing wd";
    }

    @Override
    public void execute(String workingDir)
    {
        System.out.println("Executing WD Command id:" + super.getId());
        Batch.getInstance().setWorkingDir(this.path);
        System.out.println("Command WD with id " + super.getId() + " set path to" + this.path);
    }

    @Override
    public void parse(Element element) throws ProcessException {
        String id = element.getAttribute("id");
        if (id == null || id.isEmpty()) {
            throw new ProcessException("Missing ID in WD Command");
        }
        super.setId(id);
        System.out.println("ID: " + id);

        String path = element.getAttribute("path");
        if (path == null || path.isEmpty()) {
            throw new ProcessException("Missing PATH in WD Command");
        }
        this.path = path;
        System.out.println("Path: " + path);
    }

    public String getPath() {
        return path;
    }
}
