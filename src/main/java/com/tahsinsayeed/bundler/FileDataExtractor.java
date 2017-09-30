package com.tahsinsayeed.bundler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.logging.Logger;


public class FileDataExtractor {

    private static final String EOL = System.getProperty("line.separator");

    private StringBuilder header;
    private StringBuilder content;


    public FileData extractFileData(Path file) {

        Logger.getGlobal().info("Extracting content from " + file.getFileName());
        header = new StringBuilder(300);
        content = new StringBuilder(1000);

        try {
            List<String> lines = Files.readAllLines(file);
            return getFileDataFrom(lines);
        } catch (IOException e) {
            return FileData.EMPTY;
        }

    }

    private FileData getFileDataFrom(List<String> lines) {
        for (String line : lines) {
            if (isPackageDeclaration(line)) continue;

            if (isHeaderLine(line))
                header.append(line).append(EOL);
            else
                content.append(getRequiredContentFrom(line)).append(EOL);

        }
        return new FileData(header.toString(), content.toString());
    }

    private boolean isPackageDeclaration(String line) {
        return line.trim().startsWith("package");
    }

    private boolean isHeaderLine(String line) {
        return line.trim().startsWith("import");
    }

    private String getRequiredContentFrom(String line) {
        if (isClassDeclaration(line))
            return removePublicDeclarationFrom(line);
        else if (isInterfaceDeclaration(line))
            return removePublicDeclarationFrom(line);
        else
           return line;
    }

    private boolean isClassDeclaration(String line) {
        return line.trim().startsWith("public class");
    }

    private boolean isInterfaceDeclaration(String line) {
        return line.trim().startsWith("public interface");
    }

    private String removePublicDeclarationFrom(String line) {

        String undesiredPart = "public ";
        int indexOfUndesiredPart = line.indexOf(undesiredPart);

        int desiredStringStartsFrom = indexOfUndesiredPart + undesiredPart.length();
        return line.substring(desiredStringStartsFrom);
    }


    public FileData extractFileDataFromMainFile(Path mainFile) {
        header = new StringBuilder(300);
        content = new StringBuilder(1000);

        Logger.getGlobal().info("Extracting content from the main file " + mainFile.getFileName());

        try {
            List<String> lines = Files.readAllLines(mainFile);
            return getMainFileDataFrom(lines);
        } catch (IOException e) {
            return FileData.EMPTY;
        }

    }

    private FileData getMainFileDataFrom(List<String> lines) {
        for (String line : lines) {
            if (isPackageDeclaration(line)) continue;
            if (isHeaderLine(line))
                header.append(line).append(EOL);
            else
                content.append(line).append(EOL);
        }
        return new FileData(header.toString(), content.toString());
    }


}
