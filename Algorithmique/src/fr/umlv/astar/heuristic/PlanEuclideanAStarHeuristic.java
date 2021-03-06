package fr.umlv.astar.heuristic;

import fr.umlv.astar.graph.PlanVertex;
import fr.umlv.astar.util.Distances;

/**
 * Heuristic using the euclidean distance for plan graphs and vertices.
 * 
 * @author Tom MIETTE
 * @author Sebastien MOURET
 * @version 1.0
 */
public class PlanEuclideanAStarHeuristic implements AStarHeuristic<PlanVertex> {

	/**
	 * Returns the value of the heuristic for the start vertex to reach the end
	 * vertex.
	 * 
	 * @param vertex
	 *            the vertex.
	 * @param end
	 *            the vertex to reach.
	 * @return the hauristic value.
	 */
	public double heuristicValue(PlanVertex vertex, PlanVertex end) {

		double x1 = vertex.getXCoordinate();
		double y1 = vertex.getYCoordinate();
		double x2 = end.getXCoordinate();
		double y2 = end.getYCoordinate();

		return Distances.euclideanDistance(x1, y1, x2, y2);

	}
}
