import exception.FileNotValidException;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class FileInputTest {
    private final String FILENAME = "cities.txt";

    private final String CORRUPT_FILE_FILENAME = "wrong_input.txt";

    @Test
    public void testGetCityFromFile() throws Exception {
        List<City> cities = App.createCities(FILENAME);
        Assert.assertNotNull(cities);
    }

    @Test(expected = FileNotValidException.class)
    public void testGetCityFromCorruptFile() throws Exception {
        List<City> cities = App.createCities(CORRUPT_FILE_FILENAME);
    }
}
