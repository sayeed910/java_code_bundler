package com.tahsinsayeed.bundler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;

class ApplicationBuilder {
    private static final Logger logger = Logger.getLogger(ApplicationBuilder.class.getName());

    static {
        logger.addHandler(new ConsoleHandler());
    }

    private final String[] commandlineArgs;

    ApplicationBuilder(String[] args) {
        commandlineArgs = args;
    }

    App build() throws IOException {
        Bundler codeBundler = createBundler();
        FileWriter codeWriter = new FileWriter(getOutputFile());

        return new App(codeBundler, codeWriter);
    }

    private File getOutputFile() {
        File outputFile = new File(commandlineArgs[2]);
        if (outputFile.exists()) {
            outputFile = new File("Output.java");
        }

        return outputFile;
    }


    Bundler createBundler() {

        if (commandlineArgs == null)
            throw new IllegalArgumentException("Source directory and main file are not provided.");

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
