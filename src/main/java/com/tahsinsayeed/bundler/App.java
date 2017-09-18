package com.tahsinsayeed.bundler;

import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;

public class App {
    private final static Logger logger = Logger.getLogger(App.class.getName());
    static {
        logger.addHandler(new ConsoleHandler());
    }


    private final Bundler codeBundler;
    private final FileWriter bundledCodeWriter;

    App(Bundler codeBundler, FileWriter bundledCodeWriter) {
        this.codeBundler = codeBundler;
        this.bundledCodeWriter = bundledCodeWriter;
    }

    public static void main(String[] args) {
        try {
            App application = new ApplicationBuilder(args).build();
            application.start();
        } catch (Exception e) {
            logger.severe("" + e);
        }
    }

    private void start() throws IOException {
        String bundledCode = codeBundler.bundle();
        try {
            bundledCodeWriter.write(bundledCode);
            bundledCodeWriter.flush();
            bundledCodeWriter.close();
        } catch (IOException e) {
            throw new IOException("Could not write to output file");
        }
    }
}
