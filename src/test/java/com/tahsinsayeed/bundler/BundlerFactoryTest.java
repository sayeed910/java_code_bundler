package com.tahsinsayeed.bundler;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;


public class BundlerFactoryTest {

    private Bundler bundler;
    private final String sourceDirPath = "E:\\IdeaProjects\\Bundler\\src\\main\\java\\com\\tahsinsayeed\\bundler";
    private final String mainClassFileName  = "App.java";


    @Test
    public void testCreateBundler_Proper_DirPath() throws Exception {

        String[] args = {"App", sourceDirPath + File.separator, mainClassFileName};

        bundler = new BundlerFactory().createBundler(args);
        assertEquals(sourceDirPath, bundler.getSourceDirPath());
    }

    @Test
    public void testCreateBundler_Proper_FileName(){
        String[] args = {"App", sourceDirPath + File.separator, mainClassFileName};

        bundler = new BundlerFactory().createBundler(args);
        assertEquals(sourceDirPath + File.separator + mainClassFileName, bundler.getMainClassFileName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateBundler_Wrong_DirPath_throw_Exception() {
        String[] args = {"App", "E:\\jet" + File.separator, mainClassFileName};

        bundler = new BundlerFactory().createBundler(args);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateBundler_Wrong_MainFileName_throw_Exception() {
        String[] args = {"App", sourceDirPath + File.separator, "Wrong.java"};

        bundler = new BundlerFactory().createBundler(args);

    }


    @Test(expected = IllegalArgumentException.class)
    public void testCreateBundler_Null_DirPath_throw_Exception() {
        String[] args = {"App", null, mainClassFileName};

        bundler = new BundlerFactory().createBundler(args);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateBundler_Null_MainFileName_throw_Exception() {
        String[] args = {"App", sourceDirPath + File.separator, null};

        bundler = new BundlerFactory().createBundler(args);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateBundler_Null_throw_Exception() {
        bundler = new BundlerFactory().createBundler(null);

    }





  

}