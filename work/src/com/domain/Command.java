package com.domain;

import org.w3c.dom.Element;
import com.exception.ProcessException;

/**
 * Created by Anton on 2/4/2016.
 */
public abstract class Command
{
    private String id;

    public Command()
    {}

    public abstract String describe();
    public abstract void execute(String workingDir);
    public abstract void parse(Element element) throws ProcessException;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
