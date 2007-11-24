package fr.umlv.astar.graph;

import java.util.HashMap;
import java.util.Set;
import java.util.Map.Entry;

import fr.umlv.astar.util.Distances;

/**
 * Implementation of a graph in a two dimensions plan.
 * 
 * @author Tom MIETTE
 * @author Sébastien MOURET
 * @version 1.0
 */
public class PlanGraph implements Graph<PlanVertex> {

	/*
	 * Map which associates a plan vertex to the list of its successors (another
	 * map which associates the successor vertex to the weight of the edge)
	 */
	private final HashMap<PlanVertex, HashMap<PlanVertex, Double>> graph;

	/**
	 * Constructor of a empty plan graph.
	 * 
	 */
	public PlanGraph() {
		this.graph = new HashMap<PlanVertex, HashMap<PlanVertex, Double>>();
	}

	/**
	 * Add a plan vertex to the plan graph.
	 * 
	 * @param vertex
	 *            the vertex to add.
	 */
	public void addPlanVertex(PlanVertex vertex) {
		if (!this.graph.containsKey(vertex)) {
			this.graph.put(vertex, new HashMap<PlanVertex, Double>());
		}
	}

	/**
	 * Creates an edge between start and end vertex associated to the weight. If
	 * this weight is less than the euclidean distance between the two vertices
	 * in the plan, the edge is not created.
	 * 
	 * @param start
	 *            source of the edge.
	 * @param end
	 *            destination of the edge.
	 * @param weight
	 *            weight of the edge.
	 */
	public void addEdge(PlanVertex start, PlanVertex end, double weight) {

		// tests if the two vertices exist
		if (this.graph.containsKey(start) && this.graph.containsKey(end)) {
			// tests if the weight is higher than the euclidean distance between
			// the two vertices
			if (weight > Distances.euclideanDistance(start.getXCoordinate(),
					start.getYCoordinate(), end.getXCoordinate(), end
							.getYCoordinate())) {
				// add the successor with the specified weight
				this.graph.get(start).put(end, weight);
			}
		}

	}

	/**
	 * Returns if an edge exists between start and end.
	 * 
	 * @param start
	 *            start of the edge.
	 * @param end
	 *            end of the edge.
	 * @return if an edge exists between start end end.
	 */
	public boolean isEdge(PlanVertex start, PlanVertex end) {
		if (this.graph.containsKey(start)) {
			if (this.graph.get(start).containsKey(end)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns the weigth of the edge connecting start and end, or 0 if this
	 * edge does not exists.
	 * 
	 * @param start
	 *            start of the edge.
	 * @param end
	 *            end of the edge.
	 * @return the weight of the edge connecting the two vertices, or 0 if the
	 *         edge does not exists.
	 */
	public double edgeValue(PlanVertex start, PlanVertex end) {
		if (this.graph.containsKey(start)) {
			return this.graph.get(start).get(end);
		}
		return 0.0;
	}

	/**
	 * Returns the number of successors of the vertex, or -1 if the vertex does
	 * not exists.
	 * 
	 * @param vertex
	 *            the vertex.
	 * @return the number of successors of the vertex, or -1 if the vertex does
	 *         not exists.
	 */
	public int numberOfSuccessors(PlanVertex vertex) {
		if (this.graph.containsKey(vertex)) {
			return this.graph.get(vertex).size();
		}
		return -1;
	}

	/**
	 * Returns the successor in position n of the vertex, or null if there is no
	 * more successor.
	 * 
	 * @param vertex
	 *            the vertex.
	 * @param n
	 *            the position.
	 * @return the successor in position n of the vertex, or null if there is no
	 *         more successor.
	 */
	public PlanVertex getSuccessor(PlanVertex vertex, int n) {
		if (!this.graph.containsKey(vertex)) {
			return null;
		}
		// create a set of entry of the map
		Set<Entry<PlanVertex, Double>> entries = this.graph.get(vertex)
				.entrySet();

		// traverse the set until nth element
		PlanVertex result = null;
		int i = 0;
		for(Entry<PlanVertex, Double> entry : entries){
			if(i == n){
				result = entry.getKey();
			}
			i++;
		}
		
		return result;
	}

}
