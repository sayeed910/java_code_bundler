package com.tahsinsayeed.bundler;

import java.util.Objects;

/**
 * Created by Tahsin Sayeed on 09/09/2017.
 */
public class FileData {
    public static final FileData EMPTY = new FileData("", "");
    public final String header;
    public final String content;

    public FileData(String header, String content) {
        this.header = header;
        this.content = content;
    }

}
