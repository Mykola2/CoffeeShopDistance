import exception.FileNotValidException;
import exception.InvalidSizeException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class App {

    private static final String FILENAME = "cities.txt";

    public static void main(String[] args) {
        for (City city : createCities(FILENAME)) {
            for (Integer step : city.getSteps()) {
                System.out.println(city.getBestPlaces(step));
            }
            System.out.println();
        }

    }

    public static List<City> createCities(String fileName) {
        List<City> testCases = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while (!"0 0 0 0".equals(line = br.readLine())) {
                String[] params = line.split(" ");
                int x = Integer.parseInt(params[0]);
                int y = Integer.parseInt(params[1]);
                int shopsNum = Integer.parseInt(params[2]);
                int stepsCount = Integer.parseInt(params[3]);
                List<Point> shops = new ArrayList<>();
                for (int i = 0; i < shopsNum; i++) {
                    String[] shopPoint = br.readLine().split(" ");
                    shops.add(new Point(Integer.parseInt(shopPoint[0]), Integer.parseInt(shopPoint[1])));
                }
                List<Integer> steps = new ArrayList<>();
                for (int i = 0; i < stepsCount; i++) {
                    String stepsNum = br.readLine();
                    steps.add(Integer.parseInt(stepsNum));
                }
                City newCity = new City(x, y, shops);
                newCity.setSteps(steps);
                testCases.add(newCity);
            }
        } catch (IOException | InvalidSizeException | NumberFormatException e) {
            throw new FileNotValidException(e);
        }
        return testCases;
    }
}
