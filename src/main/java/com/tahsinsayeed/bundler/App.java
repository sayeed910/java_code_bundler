package com.tahsinsayeed.bundler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {
    private final static Logger logger = Logger.getLogger(App.class.getName());

    private final Bundler codeBundler;
    private final File output;

    App(Bundler codeBundler, File bundledCodeWriter) {
        this.codeBundler = codeBundler;
        this.output = bundledCodeWriter;
    }

    public static void main(String[] args) {
        try {
            Args argumentParser = Args.get(args);
            File sourceDirectory = argumentParser.getSourceDirectory();
            File mainClassFile = argumentParser.getMainClassFile();

            ApplicationBuilder applicationBuilder = new ApplicationBuilder(
                    sourceDirectory, mainClassFile);

            App application = applicationBuilder.build();
            application.start();
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    private void start(){
        String bundledCode = codeBundler.bundle();
        Logger.getGlobal().info("Writing output to " + output.getAbsolutePath());
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(output))) {
            writer.write(bundledCode);
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException("Could not write to output file", e);
        }
    }
}
