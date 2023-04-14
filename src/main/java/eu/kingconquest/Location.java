package eu.kingconquest;

import java.util.Objects;

public class Location {
    private int x;
    private int y;
    public Location(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setLocation(Location location){
        this.x = location.getX();
        this.y = location.getY();
    }

    public int getDistance(Location location){
        return Math.abs(location.getX() - x) + Math.abs(location.getY() - y);
    }

    @Override
    public String toString() {
        return "Location{" + "x=" + x + ", y=" + y + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return x == location.x && y == location.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
