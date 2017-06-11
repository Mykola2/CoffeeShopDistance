import exception.InvalidSizeException;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
class City {
    private final List<Point> points = new ArrayList<>();

    private List<Integer> steps;

    public City(int x, int y, List<Point> coffeeShops) throws InvalidSizeException {
        createCity(x, y);
        populateCoffeeShops(coffeeShops);
    }

    private void populateCoffeeShops(List<Point> coffeeShops) throws InvalidSizeException {
        if (coffeeShops.size() > points.size())
            throw new InvalidSizeException("Number of coffee shop is greater than city");
        for (Point point : points)
            if (coffeeShops.contains(point))
                point.setCoffeeShop(true);
    }

    private void createCity(int x, int y) {
        for (int i = 1; i <= x; i++) {
            for (int j = 1; j <= y; j++) {
                points.add(new Point(i, j));
            }
        }
    }

    public PointDTO getBestPlaces(int steps) {
        List<Point> bestPlaces = new ArrayList<>();
        int maxShops = 0;
        int shops;
        Map<Point, Integer> stepsToPoint = new HashMap<>();
        for (Point point : points) {
            for (Point anotherPoint : points) {
                if (anotherPoint.isCoffeeShop() && anotherPoint != point)
                    stepsToPoint.put(anotherPoint, calculateWay(anotherPoint, point));
            }
            shops = calculateShops(stepsToPoint, steps);
            if (shops == maxShops) {
                bestPlaces.add(point);
            } else if (shops > maxShops) {
                maxShops = shops;
                bestPlaces.clear();
                bestPlaces.add(point);
            }
        }
        int minY = bestPlaces
                .stream()
                .mapToInt(Point::getY)
                .min()
                .getAsInt();
        List<Point> listWithMinY = bestPlaces
                .stream()
                .filter(x -> x.getY() == minY)
                .collect(Collectors.toList());
        if (listWithMinY.size() == 1) {
            return new PointDTO(listWithMinY.get(0), maxShops);
        } else {
            int minX = listWithMinY.stream().mapToInt(Point::getX).min().getAsInt();
            Point point = listWithMinY
                    .stream()
                    .filter(x -> x.getX() == minX)
                    .findFirst()
                    .get();
            return new PointDTO(point, maxShops);
        }
    }


    private int calculateShops(Map<Point, Integer> stepsToPoint, int steps) {
        return (int) stepsToPoint.values().stream().filter(x -> x <= steps).count();
    }

    private Integer calculateWay(Point anotherPoint, Point point) {
        return Math.abs(point.getX() - anotherPoint.getX()) + Math.abs(point.getY() - anotherPoint.getY());
    }
}
