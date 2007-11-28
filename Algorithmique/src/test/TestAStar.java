package test;

import fr.umlv.astar.AStar;
import fr.umlv.astar.AStarResult;
import fr.umlv.astar.graph.CheckerboardGraph;
import fr.umlv.astar.graph.CheckerboardVertex;
import fr.umlv.astar.graph.PlanGraph;
import fr.umlv.astar.graph.PlanVertex;
import fr.umlv.astar.heuristic.AStarHeuristic;
import fr.umlv.astar.heuristic.CheckerboardEuclideanAStarHeuristic;
import fr.umlv.astar.heuristic.PlanEuclideanAStarHeuristic;

/**
 * Main class which perform some tests with astar algorithm.
 * 
 * @author Tom MIETTE
 * @author Sebastien MOURET
 * @version 1.0
 */
public class TestAStar {

	/**
	 * Main method which lauch a sample of tests of a star algorithm.
	 * 
	 * @param args
	 *            line command parameters (useless).
	 */
	public static void main(String[] args) {

		// Samples of astar algorithm
		System.out.println("A* TESTS : ");

		// Tests with CheckerbordGraph implementation
		System.out.println("\n----------------------");
		System.out.println("FIRST PART - Checkerbord graphs : ");

		// first checkerboard graph
		double[][] matrix = { { 6.0, 4.0, 7.0, 8.0 }, { 8.0, 6.0, 4.0, 4.0 },
				{ 5.0, 4.0, 8.0, 5.0 }, { 6.0, 7.0, 8.0, 6.0 } };
		CheckerboardGraph graph = new CheckerboardGraph(matrix);
		System.out.println("\nCheckerboard graph 1 :\n" + graph);

		// first sample : path between vertices (0, 0) and (3, 3)
		System.out.println("Sample 1");
		performAndDisplayWithCheckerboard(graph, graph.getCheckerboardVertex(0,
				0), graph.getCheckerboardVertex(3, 3));
		// second sample : path between vertices (3, 1) and (0, 2)
		System.out.println("Sample 2");
		performAndDisplayWithCheckerboard(graph, graph.getCheckerboardVertex(3,
				1), graph.getCheckerboardVertex(0, 2));

		// second checkerboard graph
		double[][] matrix2 = { { 1.0, 4.0, 2.0, 8.0, 8.0, 0.0, 8.0 },
				{ 8.0, 0.0, 4.0, 3.0, 4.0, 0.0, 0.0 },
				{ 5.0, 4.0, 2.0, 3.0, 4.0, 5.0, 5.0 },
				{ 6.0, 7.0, 8.0, 0.0, 6.0, 1.0, 6.0 },
				{ 2.0, 3.0, 8.0, 6.0, 3.0, 0.0, 2.0 },
				{ 0.0, 7.0, 8.0, 6.0, 0.0, 0.0, 1.0 }, };
		CheckerboardGraph graph2 = new CheckerboardGraph(matrix2);
		System.out.println("\nCheckerboard graph 2 :\n" + graph2);

		// first sample : path between vertices (0, 0) and (5, 6)
		System.out.println("Sample 1");
		performAndDisplayWithCheckerboard(graph2, graph2.getCheckerboardVertex(
				0, 0), graph2.getCheckerboardVertex(5, 6));
		// second sample : path between vertices (0, 0) and (0, 6)
		System.out.println("Sample 2");
		performAndDisplayWithCheckerboard(graph2, graph2.getCheckerboardVertex(
				0, 0), graph2.getCheckerboardVertex(0, 6));

		// Tests with PlanGraph implementation
		System.out.println("\n----------------------");
		System.out.println("SECOND PART - Plan graphs : ");

		// first plan graph
		PlanGraph graph3 = new PlanGraph();
		PlanVertex pv1 = new PlanVertex(0.0, 0.0);
		PlanVertex pv2 = new PlanVertex(0.2, 0.2);
		PlanVertex pv3 = new PlanVertex(0.2, 0.4);
		PlanVertex pv4 = new PlanVertex(0.4, 0.4);
		PlanVertex pv5 = new PlanVertex(0.6, 0.2);
		PlanVertex pv6 = new PlanVertex(0.4, 0.2);
		PlanVertex pv7 = new PlanVertex(1, 1);
		graph3.addPlanVertex(pv1);
		graph3.addPlanVertex(pv2);
		graph3.addPlanVertex(pv3);
		graph3.addPlanVertex(pv4);
		graph3.addPlanVertex(pv5);
		graph3.addPlanVertex(pv6);
		graph3.addPlanVertex(pv7);
		graph3.addEdge(pv1, pv2, 2.0);
		graph3.addEdge(pv2, pv3, 2.0);
		graph3.addEdge(pv2, pv4, 10.0);
		graph3.addEdge(pv2, pv5, 7.0);
		graph3.addEdge(pv2, pv6, 3.0);
		graph3.addEdge(pv3, pv4, 2.0);
		graph3.addEdge(pv4, pv7, 10.0);
		graph3.addEdge(pv5, pv7, 2.0);
		graph3.addEdge(pv6, pv5, 3.0);
		System.out.println("\nPlan graph 1 :\n" + graph3);

		// first sample : path between vertices (0, 0) and (3, 3)
		System.out.println("Sample 1");
		performAndDisplayWithPlan(graph3, pv1, pv7);

	}

	/**
	 * Perform a star algorithm with the specified parameters and display the
	 * result.
	 * 
	 * @param graph
	 *            the graph.
	 * @param start
	 *            the start vertex.
	 * @param end
	 *            the end vertex.
	 */
	private static void performAndDisplayWithCheckerboard(
			CheckerboardGraph graph, CheckerboardVertex start,
			CheckerboardVertex end) {

		System.out.println("----------------------");
		System.out
				.println("Start vertex = " + start + " | End vertex = " + end);
		// Init heuristic
		AStarHeuristic<CheckerboardVertex> heuristic = new CheckerboardEuclideanAStarHeuristic();
		// Perform a star algorithm
		AStarResult<CheckerboardVertex> path = AStar.aStarAlgorithm(graph,
				heuristic, start, end);
		// Display result with toString method
		System.out.println("A* result :");
		if (path == null) {
			System.out.println("Cannot reach end vertex : " + end);
		} else {
			System.out.println(path);
		}
		System.out.println("----------------------");
	}

	/**
	 * Display the path between vertex start2 and end2 for a graph plan
	 * 
	 * @param graph2
	 *            graph
	 * @param start2
	 *            starting vertex
	 * @param end2
	 *            ending vertex
	 */
	private static void performAndDisplayWithPlan(PlanGraph graph2,
			PlanVertex start, PlanVertex end) {

		System.out.println("----------------------");
		System.out
				.println("Start vertex = " + start + " | End vertex = " + end);
		// Init heuristic
		AStarHeuristic<PlanVertex> heuristic = new PlanEuclideanAStarHeuristic();
		// Perform a star algorithm
		AStarResult<PlanVertex> path2 = AStar.aStarAlgorithm(graph2, heuristic,
				start, end);
		// Display result with getting list
		System.out.println("A* result :");
		if (path2 == null) {
			System.out.println("Cannot reach end vertex : " + end);
		} else {
			System.out.println("Cost : " + path2.getPathWeight());
			int i = 0;
			for (PlanVertex pv : path2.getPath()) {
				System.out.println(i++ + " - " + pv);
			}
		}
		System.out.println("----------------------");

	}
}
