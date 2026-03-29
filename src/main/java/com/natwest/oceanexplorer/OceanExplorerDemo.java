package com.natwest.oceanexplorer;

import java.util.Set;

public final class OceanExplorerDemo {

    private OceanExplorerDemo() {}

    public static void main(String[] args) {

        System.out.println("=== Ocean Explorer Simulation Started ===\n");

        // Create grid with obstacle
        Grid grid = new Grid(10, 10, Set.of(new Coordinate(3, 3)));

        // Initialize probe
        Probe probe = new Probe(grid, new Coordinate(0, 0), Direction.N);

        // Command script
        String script = "FFRFFLBBF";
        System.out.println("Commands Executed: " + script);

        try {
            // Execute commands
            probe.execute(script);

            // Final Output
            System.out.println("\n=== Final Status ===");
            System.out.println("Final Position : " + formatCoordinate(probe.getPosition()));
            System.out.println("Facing Direction : " + probe.getDirection());

            System.out.println("\nVisited Path:");
            printVisited(probe.getVisitedSummary());

        } catch (Exception e) {
            System.out.println("\n⚠️ Error occurred during execution:");
            System.out.println(e.getMessage());
        }

        System.out.println("\n=== Simulation Ended ===");
    }

    // Helper method to format coordinate nicely
    private static String formatCoordinate(Coordinate c) {
        return "(" + c.x() + ", " + c.y() + ")";
    }

    // Helper method to print path in clean format
    private static void printVisited(Iterable<Coordinate> visited) {
        for (Coordinate c : visited) {
            System.out.print(formatCoordinate(c) + " -> ");
        }
        System.out.println("END");
    }
}