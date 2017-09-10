package com.tahsinsayeed.bundler;

public class App {
    public static void main(String[] args) {
        Bundler codeBundler = new com.tahsinsayeed.bundler.BundlerFactory().createBundler(args);
        codeBundler.bundle();
    }
}
