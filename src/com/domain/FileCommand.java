package com.domain;

import com.exception.ProcessException;
import org.w3c.dom.Element;

/**
 * Created by Anton on 2/7/2016.
 */
public class FileCommand extends Command
{
    private String path;

    public FileCommand()
    {
        super();
    }

    @Override
    public String describe() {

        return "Parsing File";
    }

    @Override
    public void execute(String workingDir)
    {
        System.out.println("Executing File Command id:" + super.getId());

        System.out.println("Command File with id " + super.getId() + " set path to" + this.path);
    }

    @Override
    public void parse(Element element) throws ProcessException {
        String id = element.getAttribute("id");
        if (id == null || id.isEmpty()) {
            throw new ProcessException("Missing ID in File Command");
        }
        super.setId(id);
        System.out.println("ID: " + id);

        String path = element.getAttribute("path");
        if (path == null || path.isEmpty()) {
            throw new ProcessException("Missing PATH in File Command");
        }
        this.path = path;
        System.out.println("Path: " + path);
    }

    public String getPath()
    {
        return this.path;
    }
}
