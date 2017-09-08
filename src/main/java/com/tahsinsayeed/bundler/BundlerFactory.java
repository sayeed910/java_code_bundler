package com.tahsinsayeed.bundler;

import java.io.File;

public class BundlerFactory {


    public Bundler createBundler(String[] args) {

        if (args == null) throw new IllegalArgumentException("Source directory and main file are not provided.");

        File sourceDir = getDirectoryFromString(args[1]);
        File mainClassFile = getFileFromString(sourceDir.getAbsolutePath() + File.separator + args[2]);

        return Bundler.create(sourceDir, mainClassFile);
    }

    private File getDirectoryFromString(String path) {

        if (path == null) throw new IllegalArgumentException("No directory provided");

        File dir = new File(path);
        if (!dir.isDirectory())
            throw new IllegalArgumentException("Source directory " + path + " is not a valid directory.");

        return dir;
    }

    private File getFileFromString(String path) {

        if (path == null) throw new IllegalArgumentException("No File provided");

        File file = new File(path);
        if (!file.exists()) throw new IllegalArgumentException("The file " + path + "does not exist.");

        return file;
    }
}
