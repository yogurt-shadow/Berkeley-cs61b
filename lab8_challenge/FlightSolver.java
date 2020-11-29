import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Comparator;

/**
 * Solver for the Flight problem (#9) from CS 61B Spring 2018 Midterm 2.
 * Assumes valid input, i.e. all Flight start times are >= end times.
 * If a flight starts at the same time as a flight's end time, they are
 * considered to be in the air at the same time.
 */
public class FlightSolver {
	private PriorityQueue<Flight> start;
	private PriorityQueue<Flight> end;
	private PriorityQueue<Integer> number;
	private final Comparator<Flight> startcompare = (i, j) -> i.startTime() - j.startTime();
    private final Comparator<Flight> endcompare = (i, j) -> i.endTime() - j.endTime();
    private final Comparator<Integer> numbercompare = (i, j) -> j - i;

    public FlightSolver(ArrayList<Flight> flights) {
        start = new PriorityQueue<>(startcompare);
        end = new PriorityQueue<>(endcompare);
        for(int i = 0; i < flights.size(); i++){
        	start.add(flights.get(i));
        	end.add(flights.get(i));
        }
        number = new PriorityQueue<>(numbercompare);
    }

    public int solve() {
        int passenger = 0;
        while(!start.isEmpty() || !end.isEmpty()){
        	if(start.isEmpty() || start.peek().startTime() > end.peek().endTime()){
        		Flight arrive = end.poll();
        		passenger -= arrive.passengers();
        		number.add(passenger);
        	}
        	else{
        		Flight takeoff = start.poll();
        		passenger += takeoff.passengers();
        		number.add(passenger);
        	}
        }
        return number.peek();
    }

    public static void main(String[] args){
    	int[] startTimes = {10, 20, 30, 40};
        int[] endTimes = {19, 29, 39, 49};
        int[] passengerCounts = {1, 2, 3, 4};
        ArrayList<Flight> flight = new ArrayList<>();
        for(int i = 0; i < 4; i++){
        	flight.add(new Flight(startTimes[i], endTimes[i], passengerCounts[i]));
        }
        FlightSolver solver = new FlightSolver(flight);
        System.out.println(solver.solve());
    }

}
