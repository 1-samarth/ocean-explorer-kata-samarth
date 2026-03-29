package com.natwest.oceanexplorer;

import java.util.Set;

public class Grid {
    private final int width;
    private final int height;
    private final Set<Coordinate> obstacles;

    public Grid(int width, int height, Set<Coordinate> obstacles) {
        this.width = width;
        this.height = height;
        this.obstacles = obstacles == null ? Set.of() : Set.copyOf(obstacles);
    }

    public boolean isWithinBounds(Coordinate cord) {
        return cord.x() >= 0 && cord.x() < width && cord.y() >= 0 && cord.y() < height;
    }

    public boolean isObstacle(Coordinate cord) {
        return obstacles.contains(cord);
    }
}
