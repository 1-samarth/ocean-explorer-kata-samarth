package com.natwest.oceanexplorer;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProbeTest {

    private Grid emptyGrid;
    private Grid obstacleGrid;

    @BeforeEach
    void setUp() {
        emptyGrid = new Grid(10, 10, Set.of());
        obstacleGrid = new Grid(10, 10, Set.of(new Coordinate(2, 2), new Coordinate(5, 5)));
    }

    @Test
    void testProbeInitialization() {
        Probe probe = new Probe(emptyGrid, new Coordinate(0, 0), Direction.N);
        assertEquals(new Coordinate(0, 0), probe.getPosition());
        assertEquals(Direction.N, probe.getDirection());
    }

    @Test
    void testProbeRotatesLeft() {
        Probe probe = new Probe(emptyGrid, new Coordinate(0, 0), Direction.N);
        probe.execute("L");
        assertEquals(Direction.W, probe.getDirection());
    }

    @Test
    void testProbeRotatesRight() {
        Probe probe = new Probe(emptyGrid, new Coordinate(0, 0), Direction.N);
        probe.execute("R");
        assertEquals(Direction.E, probe.getDirection());
    }

    @Test
    void testProbeMovesForward() {
        Probe probe = new Probe(emptyGrid, new Coordinate(5, 5), Direction.N);
        probe.execute("F");
        assertEquals(new Coordinate(5, 6), probe.getPosition());
    }

    @Test
    void testProbeMovesBackward() {
        Probe probe = new Probe(emptyGrid, new Coordinate(5, 5), Direction.E);
        probe.execute("B");
        assertEquals(new Coordinate(4, 5), probe.getPosition());
    }

    @Test
    void testProbeStaysOnGridBoundary() {
        Probe probe = new Probe(emptyGrid, new Coordinate(9, 9), Direction.N);
        probe.execute("F");
        assertEquals(new Coordinate(9, 9), probe.getPosition());
    }

    @Test
    void testProbeStopsAtObstacle() {
        Probe probe = new Probe(obstacleGrid, new Coordinate(2, 0), Direction.N);

        Exception exception =
                assertThrows(
                        ObstacleEncounteredException.class,
                        () -> probe.execute("FFF"));

        assertEquals(new Coordinate(2, 1), probe.getPosition());
        assertTrue(exception.getMessage().contains("Obstacle detected"));
    }

    @Test
    void testSummaryOfVisitedCoordinates() {
        Probe probe = new Probe(emptyGrid, new Coordinate(0, 0), Direction.N);
        probe.execute("FRF");

        //  Use Set
        Set<Coordinate> history = probe.getVisitedSummary();

        assertEquals(3, history.size());
        assertTrue(history.contains(new Coordinate(1, 1)));
    }

    @Test
    void testInvalidCommandThrowsException() {
        Probe probe = new Probe(emptyGrid, new Coordinate(0, 0), Direction.N);
        assertThrows(IllegalArgumentException.class, () -> probe.execute("X"));
    }
}