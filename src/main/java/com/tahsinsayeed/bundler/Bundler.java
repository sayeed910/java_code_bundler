package com.tahsinsayeed.bundler;

import java.io.File;
import java.io.FilenameFilter;

class Bundler{

    private final File sourceDir;
    private final File mainClassFile;
    private File[] filesInSourceDir;

    public Bundler(File sourceDirectory, File mainClassFile) {
        this.sourceDir = sourceDirectory;
        this.mainClassFile = mainClassFile;
    }

    public static Bundler create(File sourceDir, File mainClassFile) {
        return new Bundler(sourceDir, mainClassFile);
    }

    public String getSourceDirPath() {
        return sourceDir.getAbsolutePath();
    }

    public String getMainClassFileName() {
        return mainClassFile.getAbsolutePath();
    }

    public File[] bundle() {
        //TODO: Add support for bundling files inside subdirectory
        filesInSourceDir = sourceDir.listFiles((file, s) -> s.endsWith("java"));

        return filesInSourceDir;
    }
}






