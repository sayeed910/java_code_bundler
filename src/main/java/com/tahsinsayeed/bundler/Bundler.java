package com.tahsinsayeed.bundler;

import java.io.File;
import java.io.FilenameFilter;

import static java.util.Objects.requireNonNull;

class Bundler{

    private final File sourceDir;
    private final File mainClassFile;
    private final FileDataExtractor extractor;

    private Bundler(File sourceDirectory, File mainClassFile, FileDataExtractor extractor) {
        this.sourceDir = sourceDirectory;
        this.mainClassFile = mainClassFile;
        this.extractor = extractor;
    }

    public static Bundler create(File sourceDir, File mainClassFile, FileDataExtractor extractor) {
        return new Bundler(sourceDir, mainClassFile, extractor);
    }



    public String bundle() {
        //TODO: Add support for bundling files inside subdirectory
        StringBuilder header = new StringBuilder();
        StringBuilder content = new StringBuilder();
        StringBuilder fullData = new StringBuilder();

        FileData mainData = extractor.extractFileDataFromMainFile(mainClassFile.toPath());
        header.append(mainData.header);
        content.append(mainData.content);

        File[] filesInSourceDir = sourceDir.listFiles((file, s) -> s.endsWith(".java"));

        requireNonNull(filesInSourceDir, "The provided source directory contains no java source file");
        for (File currentFile: filesInSourceDir){
              if (!currentFile.equals(mainClassFile)){
                  FileData data = extractor.extractFileData(currentFile.toPath());
                  header.append(data.header);
                  content.append(data.content);
              }
        }


        return fullData.append(header).append(content).toString();
    }
}






