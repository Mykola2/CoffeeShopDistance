public class PointDTO {
    private final Point point;
    private final int shopCount;

    public PointDTO(Point point, int shopCount) {
        this.point = point;
        this.shopCount = shopCount;
    }

    @Override
    public String toString() {
        return shopCount + point.toString();
    }
}
