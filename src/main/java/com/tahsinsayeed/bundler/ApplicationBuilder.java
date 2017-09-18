package com.tahsinsayeed.bundler;

import java.io.File;

class ApplicationBuilder {
    private final String[] commandlineArgs;

    ApplicationBuilder(String[] args){
        commandlineArgs = args;
    }

    App build(){
        return new App(null, null);
    }


    Bundler createBundler() {

        if (commandlineArgs == null) throw new IllegalArgumentException("Source directory and main file are not provided.");

        File sourceDir = getDirectoryFromString(commandlineArgs[1]);
        File mainClassFile = getFileFromString(sourceDir.getAbsolutePath() + File.separator + commandlineArgs[2]);

        return Bundler.create(sourceDir, mainClassFile, new FileDataExtractor());
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
