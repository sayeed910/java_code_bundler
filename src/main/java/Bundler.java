import java.io.File;

/**
 * Created by Tahsin Sayeed on 07/09/2017.
 */
public class Bundler {
    private final File sourceDirectory;
    private final File mainSourceFile;

    public Bundler(File sourceDirectory, File mainSourceFile) {
        this.sourceDirectory = sourceDirectory;
        this.mainSourceFile = mainSourceFile;
    }

    public static Bundler create(){

    }

    public static Bundler withSourceDirectoryAndMainFile(File sourceDirectory, File mainSourceFile){
        assert sourceDirectory != null;
        assert mainSourceFile != null;

        if (!sourceDirectory.isDirectory()) throw new IllegalArgumentException("Not a directory");
        return new Bundler(sourceDirectory, mainSourceFile);
    }
}
