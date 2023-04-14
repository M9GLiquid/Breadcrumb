package eu.kingconquest;

import java.awt.*;

public interface Element {
    Location getLocation();
    void setLocation(int x, int y);

    void draw(Graphics g);
}
