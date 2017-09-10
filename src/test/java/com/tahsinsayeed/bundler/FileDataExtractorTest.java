package com.tahsinsayeed.bundler;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class FileDataExtractorTest {

    private static final String EOL = System.getProperty("line.separator");
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();
    private File file;
    private FileData data;

    @Before
    public void classSetup() throws IOException {
        file = folder.newFile("source.java");


    }

    @Test
    public void testExtractFileData_header() throws Exception {

        FileWriter writer = new FileWriter(file);
        writer.write(String.format("package sayeed;%s%simport me;%s%spublic class This{%s}%s", EOL, EOL, EOL, EOL, EOL, EOL));
        writer.flush();

        data = new FileDataExtractor().extractFileData(file.toPath());
        assertEquals("import me;" + EOL, data.header);


    }

    @Test
    public void testExtractFileData_Content() throws IOException {

        FileWriter writer = new FileWriter(file);
        writer.write(String.format("package sayeed;%s%simport me;%s%spublic class This{%s}%s", EOL, EOL, EOL, EOL, EOL, EOL));
        writer.flush();

        data = new FileDataExtractor().extractFileData(file.toPath());

        assertEquals(EOL + EOL +
                "class This{" + EOL +
                "}" + EOL, data.content);
    }

    @Test
    public void testExtractFileDataFromMainFile_Header() throws Exception {
        FileWriter writer = new FileWriter(file);
        writer.write(String.format("package sayeed;%s%simport me;%s%spublic class This{%s}%s", EOL, EOL, EOL, EOL, EOL, EOL));
        writer.flush();

        data = new FileDataExtractor().extractFileDataFromMainFile(file.toPath());

        assertEquals("import me;" + EOL, data.header);

    }

    @Test
    public void testExtractFileDataFromMainFile_Content() throws Exception {
        FileWriter writer = new FileWriter(file);
        writer.write(String.format("package sayeed;%s%simport me;%s%spublic class This{%s}%s", EOL, EOL, EOL, EOL, EOL, EOL));
        writer.flush();

        data = new FileDataExtractor().extractFileDataFromMainFile(file.toPath());

        assertEquals(EOL + EOL +
                "public class This{" + EOL +
                "}" + EOL, data.content);

    }
}