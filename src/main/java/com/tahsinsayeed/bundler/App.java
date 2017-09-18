package com.tahsinsayeed.bundler;

import java.io.FileWriter;

public class App {

    private final Bundler codeBundler;
    private final FileWriter bundledCodeWriter;

    App(Bundler codeBundler, FileWriter bundledCodeWriter) {
        this.codeBundler = codeBundler;
        this.bundledCodeWriter = bundledCodeWriter;
    }

    public static void main(String[] args) {
        App application = new ApplicationBuilder(args).build();
    }
}
