package com.tahsinsayeed.bundler;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;


public class ApplicationBuilderTest {

    private Bundler bundler;
    private final String sourceDirPath = "C:\\Users\\sayee\\Documents\\Programs\\java\\java_code_bundler\\src\\main\\java\\com\\tahsinsayeed\\bundler" ;
    private final String mainClassFileName  = "App.java";


    @Test
    public void testCreateBundler_Proper_DirPath_With_Separator() throws Exception {

        String[] args = {"App", sourceDirPath + File.separator + File.separator, mainClassFileName};

        bundler = new ApplicationBuilder(args).createBundler();
        assertEquals(sourceDirPath, bundler.getSourceDirPath());
    }

    @Test
    public void testCreateBundler_Proper_DirPath_Without_Separator() throws Exception {

        String[] args = {"App", sourceDirPath, mainClassFileName};

        bundler = new ApplicationBuilder(args).createBundler();
        assertEquals(sourceDirPath, bundler.getSourceDirPath());
    }

    @Test
    public void testCreateBundler_Proper_FileName(){
        String[] args = {"App", sourceDirPath + File.separator, mainClassFileName};

        bundler = new ApplicationBuilder(args).createBundler();
        assertEquals(sourceDirPath + File.separator + mainClassFileName, bundler.getMainClassFileName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateBundler_Wrong_DirPath_throw_Exception() {
        String[] args = {"App", "E:\\jet" + File.separator, mainClassFileName};

        bundler = new ApplicationBuilder(args).createBundler();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateBundler_Wrong_MainFileName_throw_Exception() {
        String[] args = {"App", sourceDirPath + File.separator, "Wrong.java"};

        bundler = new ApplicationBuilder(args).createBundler();

    }


    @Test(expected = IllegalArgumentException.class)
    public void testCreateBundler_Null_DirPath_throw_Exception() {
        String[] args = {"App", null, mainClassFileName};

        bundler = new ApplicationBuilder(args).createBundler();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateBundler_Null_MainFileName_throw_Exception() {
        String[] args = {"App", sourceDirPath + File.separator, null};

        bundler = new ApplicationBuilder(args).createBundler();

    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateBundler_Null_throw_Exception() {
        bundler = new ApplicationBuilder(null).createBundler();

    }





  

}