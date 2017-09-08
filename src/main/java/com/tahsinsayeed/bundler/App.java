package com.tahsinsayeed.bundler;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;


public class App {
    public static void main(String[] args) {
        Bundler codeBundler = new com.tahsinsayeed.bundler.BundlerFactory().createBundler(args);
        codeBundler.bundle();
    }
}
