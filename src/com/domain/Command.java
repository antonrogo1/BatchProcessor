package com.domain;

import org.w3c.dom.Element;

/**
 * Created by Anton on 2/4/2016.
 */
public abstract class Command
{
    public abstract String describe();
    public abstract void execute(String workingDir);
    public abstract void parse(Element element);
}
