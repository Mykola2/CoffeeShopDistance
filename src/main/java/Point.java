import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Point {
    private int x;
    private int y;
    private boolean isCoffeeShop;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
        this.isCoffeeShop = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (x != point.x) return false;
        return y == point.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
