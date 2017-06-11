import exception.InvalidSizeException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CityTest {

    private List<Point> coffeeShops;
    private City city;

    @Before
    public void setUp() throws Exception {
        coffeeShops = new ArrayList<>();
        coffeeShops.add(new Point(1, 1));
        coffeeShops.add(new Point(1, 2));
        coffeeShops.add(new Point(3, 3));
        coffeeShops.add(new Point(4, 4));
        coffeeShops.add(new Point(2, 4));
        city = new City(4, 4, coffeeShops);
    }

    @Test
    public void testCorrectCreateCity() throws Exception {
        Assert.assertEquals(city.getPoints().size(), 16);
    }

    @Test(expected = InvalidSizeException.class)
    public void testCreateCityFails() throws Exception {
        City smallCity = new City(1, 1, coffeeShops);
    }

    @Test
    public void testOneStep() throws Exception {
        Assert.assertEquals(city.getBestPlaces(1).toString(),"3(3, 4)");
    }

    @Test
    public void testFiveStep() throws Exception {
        Assert.assertEquals(city.getBestPlaces(5).toString(),"5(2, 1)");
    }
}
