package utilities;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import tests.DashBoardTest.model.TestData;

import java.io.InputStream;
import java.lang.runtime.ObjectMethods;

public class jsonUtil
{
    private static final Logger log = LoggerFactory.getLogger(jsonUtil.class);
    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> T getResource(String path,Class<T> type)
    {
        try(InputStream stream = ResourceLoader.getResource(path))
        {
            return mapper.readValue(stream, type);
        }
        catch(Exception e)
        {
            log.error("unable to read test data",path,e);
        }
        return null;
    }
}
