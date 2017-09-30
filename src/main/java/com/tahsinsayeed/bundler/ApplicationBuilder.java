package com.tahsinsayeed.bundler;

import java.io.File;

class ApplicationBuilder {

    private final File sourceDir;
    private final File mainClassFile;

    ApplicationBuilder(File sourceDir, File mainClassFile) {
        this.sourceDir = sourceDir;
        this.mainClassFile = mainClassFile;
    }

    App build() {
        FileDataExtractor dataExtractor = new FileDataExtractor();
        Bundler codeBundler = Bundler.create(sourceDir, mainClassFile, dataExtractor);

        return new App(codeBundler, getOutputFile());
    }


    private File getOutputFile() {
        File outputFile = new File(mainClassFile.getName());
        if (outputFile.exists()) {
            outputFile = new File("Output.java");
        }

        return outputFile;
    }



}
