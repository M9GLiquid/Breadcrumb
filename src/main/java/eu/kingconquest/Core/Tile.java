package eu.kingconquest.Core;

public class Tile extends Entity {
    public static final int TILE_SIZE = 32;
    private final boolean isWalkable;

    public Tile(int x, int y, String imagePath, boolean isWalkable) {
        super(x, y, imagePath);
        this.isWalkable = isWalkable;
    }

    public boolean isWalkable(){
        return isWalkable;
    }
}
