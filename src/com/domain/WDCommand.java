package com.domain;

import org.w3c.dom.Element;
import com.exception.ProcessException;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

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
    public void execute(String workingDir) {

    }

    @Override
    public void parse(Element element) throws ProcessException {
        String id = element.getAttribute("id");
        if (id == null || id.isEmpty()) {
            throw new ProcessException("Missing ID in CMD Command");
        }
        super.setId(id);
        System.out.println("ID: " + id);

        String path = element.getAttribute("path");
        if (path == null || path.isEmpty()) {
            throw new ProcessException("Missing PATH in CMD Command");
        }
        this.path = path;
        System.out.println("Path: " + path);
    }

    public String getPath() {
        return path;
    }
}
