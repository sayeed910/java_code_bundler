package com.tahsinsayeed.bundler;

import org.junit.*;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.nio.file.*;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ArgsTest {
    @ClassRule
    public static TemporaryFolder temporaryFolder = new TemporaryFolder();
    private static File sourceDir;
    private static File mainClassFile;


    @BeforeClass
    public static void init() throws IOException {
        sourceDir = temporaryFolder.newFolder("src");
        Path sourceFile = sourceDir.toPath().resolve("App.java");
        Files.write(sourceFile, new byte[]{});

        mainClassFile = sourceFile.toFile();



    }

    @Test
    public void testGetSourceDirectory_BothArgsValid() throws IOException {
        Chooser fake = mock(Chooser.class);
        when(fake.chooseSourceDirectory()).thenThrow(new RuntimeException("DirectoryChooser should not be called"));


        Args args = Args.get(new String[]{sourceDir.getAbsolutePath(), mainClassFile.getName()});
        assertEquals(sourceDir, args.getSourceDirectory());

    }

    @Test
    public void getMainClassFile_BothArgsValid() throws Exception {
        Chooser fake = mock(Chooser.class);
        when(fake.chooseMainClassFile()).thenThrow(new RuntimeException("FileChooser should not be called"));

        Args args = Args.get(new String[]{sourceDir.getAbsolutePath(), mainClassFile.getName()});
        assertEquals(mainClassFile, args.getMainClassFile());

    }

    @Test
    public void testGetSourceDirectory_InvalidSourceDir_ShowsOnlyDirectoryChooser(){
        Chooser fake = mock(Chooser.class);
        when(fake.chooseSourceDirectory()).thenReturn(Optional.of(sourceDir));

        Args args = Args.getWithCustomFileChooser(new String[]{"wrong/directory/path", mainClassFile.getName()},fake);
        verify(fake, times(1)).chooseSourceDirectory();
        verify(fake, never()).chooseMainClassFile();
    }

    @Test
    public void testGetMainClassFile_InvalidMainClassFile_ShowsOnlyFileChooser(){
        Chooser fake = mock(Chooser.class);
        when(fake.chooseMainClassFile()).thenReturn(Optional.of(mainClassFile));

        Args args = Args.getWithCustomFileChooser(new String[]{sourceDir.getAbsolutePath(), null},fake);
        verify(fake, times(1)).chooseMainClassFile();
        verify(fake, never()).chooseSourceDirectory();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetMainClassFile_MainClassFileOutsideSourceDir_ThrowsException()throws IOException{
        Chooser fake = mock(Chooser.class);
        when(fake.chooseMainClassFile()).thenReturn(Optional.of(temporaryFolder.newFile("outside")));

        Args args = Args.getWithCustomFileChooser(new String[]{sourceDir.getAbsolutePath(), null},fake);

    }



}