package com.tahsinsayeed.bundler;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FilenameFilter;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BundlerTest {

    private Bundler bundler;

    @Before
    public void setup(){
        File directory = mock(File.class);
        File mainClassFile = new File("main.java");
        FileDataExtractor extractor = mock(FileDataExtractor.class);

        File abc = new File("abc.java");

        when(directory.listFiles(any(FilenameFilter.class))).thenReturn(new File[]{
                abc, mainClassFile});
        when(extractor.extractFileData(abc.toPath())).thenReturn(new FileData("import me;\n", "class Bundler{}\n"));
        when(extractor.extractFileDataFromMainFile(mainClassFile.toPath())).thenReturn(new FileData("import main;\n", "public class App{}\n"));


        bundler = Bundler.create(directory, mainClassFile, extractor);
    }





    @Test
    public void testBundle() throws Exception {

        String expected = "import main;\n" +
                "import me;\n" +
                "public class App{}\n" +
                "class Bundler{}\n";

        assertEquals(expected, bundler.bundle());

    }
}