package com.domain;

import org.w3c.dom.Element;

/**
 * Created by Anton on 2/7/2016.
 */
public class FileCommand extends Command
{

    @Override
    public String describe() {

        return "Parsing File";
    }

    @Override
    public void execute(String workingDir) {

    }

    @Override
    public void parse(Element element) {

    }
}
