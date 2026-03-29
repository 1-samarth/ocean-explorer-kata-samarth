package com.natwest.oceanexplorer;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Probe {

    private final Grid grid;
    private Coordinate position;
    private Direction direction;
    // Maintains insertion order while avoiding duplicate coordinates

    private final Set<Coordinate> visited;  //  Set for unique + ordered path

    private final Map<Character, Runnable> commandMap;

    public Probe(Grid grid, Coordinate startPosition, Direction startDirection) {

        this.grid = Objects.requireNonNull(grid, "Grid cannot be null");
        this.position = Objects.requireNonNull(startPosition, "Start position cannot be null");
        this.direction = Objects.requireNonNull(startDirection, "Start direction cannot be null");

        // 🔥 Validate start position
        if (!grid.isWithinBounds(startPosition)) {
            throw new IllegalArgumentException("Start position is outside grid");
        }

        if (grid.isObstacle(startPosition)) {
            throw new IllegalArgumentException("Start position is on an obstacle");
        }

        // 🔥 Use LinkedHashSet (order + no duplicates)
        this.visited = new LinkedHashSet<>();
        this.visited.add(startPosition);

        // Command mapping
        this.commandMap = Map.of(
                'L', this::turnLeft,
                'R', this::turnRight,
                'F', () -> move(1),
                'B', () -> move(-1)
        );
    }

    // Execute command string
    public void execute(String commands) {

        if (commands == null || commands.isEmpty()) {
            throw new IllegalArgumentException("Command string cannot be null or empty");
        }

        for (char c : commands.toCharArray()) {

            Runnable action = this.commandMap.get(c);

            if (action == null) {
                throw new IllegalArgumentException("Unknown command: " + c);
            }

            action.run();
        }
    }

    // Turn left
    private void turnLeft() {
        this.direction = this.direction.turnLeft();
    }

    // Turn right
    private void turnRight() {
        this.direction = this.direction.turnRight();
    }

    // Move forward/backward
    private void move(int multiplier) {

        Coordinate nextCoord = new Coordinate(
                this.position.x() + (this.direction.getDx() * multiplier),
                this.position.y() + (this.direction.getDy() * multiplier)
        );

        // Boundary check
        if (!grid.isWithinBounds(nextCoord)) {
            return; // Ignore move
        }

        // Obstacle check
        if (grid.isObstacle(nextCoord)) {
            throw new ObstacleEncounteredException(
                    String.format("Obstacle detected at (%d, %d). Probe halted.",
                            nextCoord.x(), nextCoord.y())
            );
        }

        // Update position
        this.position = nextCoord;

        // Track visited (no duplicates now)
        this.visited.add(this.position);
    }

    // Get current position
    public Coordinate getPosition() {
        return position;
    }

    // Get current direction
    public Direction getDirection() {
        return direction;
    }

    // Return visited path
    public Set<Coordinate> getVisitedSummary() {
        return new LinkedHashSet<>(visited); // safe copy
    }
}