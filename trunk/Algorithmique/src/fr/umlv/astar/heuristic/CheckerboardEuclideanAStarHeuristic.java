package fr.umlv.astar.heuristic;

import fr.umlv.astar.graph.CheckerboardVertex;
import fr.umlv.astar.util.Distances;

/**
 * Heuristic using the euclidean distance for checkboard graphs and vertices.
 * 
 * @author Tom MIETTE
 * @author Sebastien MOURET
 * @version 1.0
 */
public class CheckerboardEuclideanAStarHeuristic implements
		AStarHeuristic<CheckerboardVertex> {

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
	public double heuristicValue(CheckerboardVertex vertex,
			CheckerboardVertex end) {

		int x1 = vertex.getXCoordinate();
		int y1 = vertex.getYCoordinate();
		int x2 = end.getXCoordinate();
		int y2 = end.getYCoordinate();

		return Distances.euclideanDistance(x1, y1, x2, y2);

	}

}
