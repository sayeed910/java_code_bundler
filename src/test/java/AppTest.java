import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class AppTest {

    @Test
    public void testMain(){
        Bundler bundler = mock(Bundler.class);

        App.main(new String[]{"AppTest", "path/to/directory", "Main.java"});

        verify(Bundler.create());
    }
}