import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;

public class SeparableEnemySolverTestsThree {

    @Test
    public void triangleEnemies() {
        Graph g = new Graph();
        g.connect("A", "B");
        g.connect("A", "C");
        g.connect("A", "D");
        g.connect("C", "D");
        SeparableEnemySolver solver = new SeparableEnemySolver(g);
        assertEquals(true, solver.isSeparable_three());
    }

    @Test
    public void disconnected() {
        Graph g = new Graph();
        g.connect("A", "B");
        g.connect("C", "D");
        SeparableEnemySolver solver = new SeparableEnemySolver(g);
        assertEquals(true, solver.isSeparable_three());
    }

    @Test
    public void disconnected2() {
        Graph g = new Graph();
        g.connect("A", "B");
        g.connect("C", "D");
        g.connect("E", "D");
        g.connect("E", "C");
        SeparableEnemySolver solver = new SeparableEnemySolver(g);
        assertEquals(true, solver.isSeparable_three());
    }

    @Test
    public void input1() throws FileNotFoundException {
        SeparableEnemySolver solver = new SeparableEnemySolver("input/party1");
        assertEquals(true, solver.isSeparable_three());
    }

    @Test
    public void input2() throws FileNotFoundException {
        SeparableEnemySolver solver = new SeparableEnemySolver("input/party2");
        assertEquals(true, solver.isSeparable_three());
    }

}
