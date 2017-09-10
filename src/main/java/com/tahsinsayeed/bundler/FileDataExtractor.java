package com.tahsinsayeed.bundler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;


public class FileDataExtractor {

    private static final String EOL = System.getProperty("line.separator");

    private StringBuilder header;
    private StringBuilder content;


    public FileData extractFileData(Path file) {

        header = new StringBuilder(300);
        content = new StringBuilder(1000);

        try {
            List<String> lines = Files.readAllLines(file);
            return getFileDataFrom(lines);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
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

    private String getRequiredContentFrom(String line) {
        if (isClassDeclaration(line))
            return removePublicDeclarationFrom(line);
        else if (isInterfaceDeclaration(line))
            return removePublicDeclarationFrom(line);
        else
           return line;
    }

    private String removePublicDeclarationFrom(String line) {

        String undesiredPart = "public ";
        int indexOfUndesiredPart = line.indexOf(undesiredPart);

        int desiredStringStartsFrom = indexOfUndesiredPart + undesiredPart.length();
        return line.substring(desiredStringStartsFrom);
    }


    private boolean isInterfaceDeclaration(String line) {
        return line.contains("public interface");
    }

    private boolean isHeaderLine(String line) {
        return line.trim().startsWith("import");
    }

    public FileData extractFileDataFromMainFile(Path mainFile) {
        header = new StringBuilder(300);
        content = new StringBuilder(1000);

        try {
            List<String> lines = Files.readAllLines(mainFile);
            return getMainFileDataFrom(lines);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
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

    private boolean isClassDeclaration(String line) {
        return line.contains("public class");
    }

    private boolean isPackageDeclaration(String line) {
        return line.trim().startsWith("package");
    }


}
