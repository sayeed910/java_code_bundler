package com.tahsinsayeed.bundler;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.util.Optional;

public class Chooser {
    private final JFileChooser chooser;

    private Chooser(JFileChooser chooser) {
        this.chooser = chooser;

    }

    public static Chooser get(){
        JFileChooser chooser = new JFileChooser();
        chooser.setAcceptAllFileFilterUsed(false);

        return new Chooser(chooser);
    }


    public Optional<File> chooseSourceDirectory(){
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setDialogTitle("Select Source Directory");
        return choose();
    }

    public Optional<File> chooseMainClassFile(){
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JAVA FILES", "java");
        chooser.setFileFilter(filter);
        chooser.setDialogTitle("Select Main Class File");
        return choose();
    }



    private Optional<File> choose(){
        int state = chooser.showOpenDialog(null);

        if (isValidSelection(state)) return Optional.of(chooser.getSelectedFile());

        return Optional.empty();


    }

    private boolean isValidSelection(int dialogState) {
        return dialogState == JFileChooser.APPROVE_OPTION;
    }

    public String getTitle() {
        return chooser.getDialogTitle();
    }

    public void setTitle(String title) {
        chooser.setDialogTitle(title);
    }
}
