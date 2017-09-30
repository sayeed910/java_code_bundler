package com.tahsinsayeed.bundler;


import java.io.File;
import java.util.Arrays;
import java.util.Optional;
import java.util.logging.Logger;

public class Args {
    private final String[] commandlineArgs;
    private final File sourceDir;
    private final File mainClassFile;
    private Chooser fileChooser;

    private Args(String[] commandlineArgs, Chooser chooser) {
        this.commandlineArgs = commandlineArgs;
        this.fileChooser = chooser;
        this.sourceDir = getSourceDirectoryFromArgsOrChooser();
        this.mainClassFile = getMainClassFileFromArgsOrChooser();
    }

    public static Args get(String[] commandlineArgs){
        return new Args(commandlineArgs, Chooser.get());
    }

    static Args getWithCustomFileChooser(String[] commandlineArgs, Chooser chooser){
        return new Args(commandlineArgs, chooser);
    }


    public File getSourceDirectory() {
        return sourceDir;
    }

    public File getMainClassFile() {
        return mainClassFile;
    }

    private File getSourceDirectoryFromArgsOrChooser() {
        try {
            return getDirectoryFromArgs();
        } catch (RuntimeException ex) {
            Optional<File> selectedDirectory = fileChooser.chooseSourceDirectory();
            if (selectedDirectory.isPresent()) return selectedDirectory.get();
            else System.exit(0);
        }
        return null;

    }

    private File getMainClassFileFromArgsOrChooser() {
        File mainClassFile = null;
        try {
            mainClassFile = getMainFileFromArgs();
        } catch (RuntimeException ex) {
            Optional<File> selectedFile = fileChooser.chooseMainClassFile();

            if (selectedFile.isPresent()) mainClassFile = selectedFile.get();
            else System.exit(0);
        }

        if (isMainClassFileInsideSourceDir(mainClassFile)) return mainClassFile;

        throw new IllegalArgumentException("Main class file should be inside the source directory.");
    }

    private boolean isMainClassFileInsideSourceDir(File mainClassFile) {
        return Arrays.asList(sourceDir.list()).contains(mainClassFile.getName());
    }


    private File getDirectoryFromArgs() {

        String path = commandlineArgs[0];
        if (path == null) throw new IllegalArgumentException("No directory provided");

        File dir = new File(path);
        if (!dir.isDirectory())
            throw new IllegalArgumentException("Source directory " + path + " is not a valid directory.");

        return dir;
    }

    private File getMainFileFromArgs() {
        String fileName = commandlineArgs[1];
        if (fileName == null) throw new IllegalArgumentException("Main class file not provided");

        File file = new File(sourceDir.getAbsolutePath() + File.separator + fileName);
        if (!file.exists()) throw new IllegalArgumentException("The file " + fileName + "does not exist.");

        return file;
    }

}