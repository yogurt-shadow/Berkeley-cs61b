package bearmaps.hw4;

import java.util.List;
import edu.princeton.cs.algs4.Stopwatch;
import bearmaps.proj2ab.*;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedList;

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex>{
    private double path_sum;
    private LinkedList<Vertex> path;
    private int numbers;
    private double timeSpent;
    private boolean enoughquick;
    private boolean solved;

	public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout){
		 Stopwatch sw = new Stopwatch();
         enoughquick = true;
         solved = true;
         numbers = 0;
         path_sum = 0.0;
         path = new LinkedList<>();
         Map<Vertex, Double> distTo = new HashMap<>();
         Map<Vertex, WeightedEdge<Vertex>> edgeTo = new HashMap<>();
		 ArrayHeapMinPQ<Vertex> pq = new ArrayHeapMinPQ<>();
         distTo.put(start, 0.0);
         pq.add(start, distTo.get(start) + input.estimatedDistanceToGoal(start, end));
         while(pq.size() > 0){
             timeSpent = sw.elapsedTime();
             if(timeSpent > timeout){
                 enoughquick = false;
                 return;
             }
            Vertex current = pq.removeSmallest();
            numbers += 1;
            List<WeightedEdge<Vertex>> neigh = input.neighbors(current);
            for(WeightedEdge<Vertex> edge: neigh){
                if(!distTo.containsKey(edge.to()) || distTo.get(edge.to()) > distTo.get(current) + edge.weight()){
                    distTo.put(edge.to(), distTo.get(current) + edge.weight());
                    edgeTo.put(edge.to(), edge);
                    if(!pq.contains(edge.to())){
                        pq.add(edge.to(), distTo.get(edge.to()) + input.estimatedDistanceToGoal(edge.to(), end));
                    }
                    else{
                        pq.changePriority(edge.to(), distTo.get(edge.to()) + input.estimatedDistanceToGoal(edge.to(), end));
                    }
                }
            }
            if(pq.size() > 0 && pq.getSmallest().equals(end)){
                Vertex v = end;
                while(!v.equals(start)){
                    path.addFirst(v);
                    v = edgeTo.get(v).from();
                }
                path.addFirst(start);
                path_sum += distTo.get(end);
                timeSpent = sw.elapsedTime();
                if(timeSpent > timeout){
                    enoughquick = false;
                }
                return;
            }
         }
         solved = false;
	}

	@Override
	public SolverOutcome outcome(){
        if(solved && enoughquick){
            return SolverOutcome.SOLVED;
        }
        if(solved && !enoughquick){
            return SolverOutcome.TIMEOUT;
        }
        else{
            return SolverOutcome.UNSOLVABLE;
        }
	}
    
    @Override
    public List<Vertex> solution(){
        if(solved && enoughquick){
        return path;
    }
    else{
        return new LinkedList<Vertex>();
    }
    }
    
    @Override
    public double solutionWeight(){
        if(solved && enoughquick){
        return path_sum;
    }
    else{
        return 0.0;
    }
    }
    
    @Override
    public int numStatesExplored(){
        return numbers;
    }

    @Override
    public double explorationTime(){
        return timeSpent;
    }
}