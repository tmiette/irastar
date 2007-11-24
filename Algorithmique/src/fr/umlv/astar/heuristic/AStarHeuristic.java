package fr.umlv.astar.heuristic;

import fr.umlv.astar.graph.Graph;

/**
 * Interface of a heuristic which can be used with astar algorithm.
 * 
 * @author Tom MIETTE
 * @author Sébastien MOURET
 * @version 1.0
 */
public interface AStarHeuristic<V> {
	/**
	 * Returns the value of the heuristic for the start vertex to reach the end
	 * vertex.
	 * 
	 * @param graph
	 *            the graph containing the two vertices.
	 * @param vertex
	 *            the vertex.
	 * @param end
	 *            the vertex to reach.
	 * @return the hauristic value.
	 */
	public double heuristicValue(Graph<V> graph, V vertex, V end);
}
