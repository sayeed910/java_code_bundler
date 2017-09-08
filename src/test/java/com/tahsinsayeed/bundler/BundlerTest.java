package com.tahsinsayeed.bundler;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BundlerTest {
    private static final String sourceDirPath = "E:\\IdeaProjects\\Bundler\\src\\main\\java\\com\\tahsinsayeed\\bundler\\";
    private static final String mainFileName = "App.java";
    @Before
    public void setup(){
        File directory = mock(File.class);

        Bundler bundler = Bundler.create(new File(sourceDirPath), new File(sourceDirPath + File.separator +"BundlerFactoryTest.java"));
    }

    @Test
    public void testBundle() throws Exception {
        assertEquals();
    }
}