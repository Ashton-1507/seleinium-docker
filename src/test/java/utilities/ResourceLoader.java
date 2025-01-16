package utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class ResourceLoader
{
    private static final Logger log = LoggerFactory.getLogger(ResourceLoader.class);
    public static InputStream getResource(String url)throws Exception
    {
        log.info("Reading resource from file : {}",url);
        InputStream stream = ResourceLoader.class.getClassLoader().getResourceAsStream(url);
        if(Objects.nonNull(stream))
        {
            return stream;
        }
        return Files.newInputStream(Path.of(url));
    }
}
