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

	public static void main(String[] args) {

		// matrix initialization
		double[][] matrix = { { 6.0, 1.0, 1.0, 1.0 }, { 8.0, 4.0, 8.0, 6.0 },
				{ 0.0, 0.0, 0.0, 7.0 }, { 5.0, 7.0, 6.0, 6.0 } };
		double[][] matrix2 = { { 2.0, 1.0, 1.0 }, { 1.0, 0.0, 1.0 },
				{ 1.0, 2.0, 2.0 } };
		double[][] matrix3 = { { 8, 5, 1, 8 }, { 0, 0, 0, 1 }, { 0, 2, 0, 9 },
				{ 0, 0, 0, 3 }, { 7, 8, 4, 5 } };

		// displaying the three matrix representation
		System.out.println("Astar test:");
		System.out.println("Matrix version:");
		System.out.println();
		System.out.println("Matrix number 1:");
		printMatrix(matrix);
		System.out.println();
		System.out.println("Matrix number 2:");
		printMatrix(matrix2);
		System.out.println();
		System.out.println("Matrix number 3:");
		printMatrix(matrix3);
		System.out.println();

		// displaying the path found by astart between 2 vertex
		System.out.println("Start node = (0, 0) | End node = (0, 3)");
		CheckerboardGraph graph = new CheckerboardGraph(matrix);
		printPath(graph, graph.getCheckerboardVertex(0, 0), graph
				.getCheckerboardVertex(0, 3));

		System.out.println("Start node = (0, 0) | End node = (0, 2)");
		graph = new CheckerboardGraph(matrix2);
		printPath(graph, graph.getCheckerboardVertex(0, 0), graph
				.getCheckerboardVertex(0, 2));

		System.out.println("Start node = (2, 1) | End node = (0, 3)");
		graph = new CheckerboardGraph(matrix3);
		printPath(graph, graph.getCheckerboardVertex(2, 1), graph
				.getCheckerboardVertex(0, 2));

		// plan graph implementation
		System.out.println();
		System.out.println("Plan version :");
		PlanGraph graph2 = new PlanGraph();

		// building graph
		PlanVertex[] vertice = buildPlanGraph(graph2);
		System.out.println(graph2);

		PlanVertex start2 = vertice[0];
		PlanVertex end2 = vertice[1];

		// displaying the path found by astart between 2 vertex
		printPath(graph2, start2, end2);

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
	private static void printPath(PlanGraph graph2, PlanVertex start2,
			PlanVertex end2) {

		AStarHeuristic<PlanVertex> heuristic2 = new PlanEuclideanAStarHeuristic();

		AStarResult<PlanVertex> path2 = AStar.aStarAlgorithm(graph2,
				heuristic2, start2, end2);
		System.out.println("Result :");
		if (path2 == null) {
			System.out.println("Cannot reach end vertex.");
		} else {
			System.out.println("Cost : " + path2.getPathWeight());
			int i = 0;
			for (PlanVertex pv : path2.getPath()) {
				System.out.println(i++ + " - " + pv);
			}
		}

	}

	/**
	 * Builds the plangraph and returns starting and ending vertice
	 * 
	 * @param graph2
	 * @return PlanVertex[] array containing two vertice (start & end)
	 */
	private static PlanVertex[] buildPlanGraph(PlanGraph graph2) {

		PlanVertex pv1 = new PlanVertex(0.0, 0.0);
		PlanVertex pv2 = new PlanVertex(0.2, 0.2);
		PlanVertex pv3 = new PlanVertex(0.2, 0.4);
		PlanVertex pv4 = new PlanVertex(0.4, 0.4);
		PlanVertex pv5 = new PlanVertex(0.6, 0.2);
		PlanVertex pv6 = new PlanVertex(0.4, 0.2);
		PlanVertex pv7 = new PlanVertex(1, 1);
		graph2.addPlanVertex(pv1);
		graph2.addPlanVertex(pv2);
		graph2.addPlanVertex(pv3);
		graph2.addPlanVertex(pv4);
		graph2.addPlanVertex(pv5);
		graph2.addPlanVertex(pv6);
		graph2.addPlanVertex(pv7);
		graph2.addEdge(pv1, pv2, 2.0);
		graph2.addEdge(pv2, pv3, 2.0);
		graph2.addEdge(pv2, pv4, 10.0);
		graph2.addEdge(pv2, pv5, 7.0);
		graph2.addEdge(pv2, pv6, 3.0);
		graph2.addEdge(pv3, pv4, 2.0);
		graph2.addEdge(pv4, pv7, 10.0);
		graph2.addEdge(pv5, pv7, 2.0);
		graph2.addEdge(pv6, pv5, 3.0);
		PlanVertex[] vertice = new PlanVertex[2];
		vertice[0] = pv1;
		vertice[1] = pv7;
		return vertice;
	}

	/**
	 * Display the path between vertex start2 and end2 for a matrix graph
	 * representation
	 * 
	 * @param graph
	 *            matrix
	 * @param start
	 *            starting vertex
	 * @param end
	 *            ending vertex
	 */
	private static void printPath(CheckerboardGraph graph,
			CheckerboardVertex start, CheckerboardVertex end) {

		AStarHeuristic<CheckerboardVertex> heuristic = new CheckerboardEuclideanAStarHeuristic();

		AStarResult<CheckerboardVertex> path = AStar.aStarAlgorithm(graph,
				heuristic, start, end);
		System.out.println("Result :");
		if (path == null) {
			System.out.println("Cannot reach end vertex.");
		} else {
			System.out.println(path.toString());
		}
	}

	/**
	 * Displays a matrix graph implementation
	 * 
	 * @param matrix
	 */
	private static void printMatrix(double[][] matrix) {

		for (int line = 0; line < matrix[0].length; line++) {
			for (int col = 0; col < matrix.length; col++) {
				System.out.print(matrix[col][line] + " ");
			}
			System.out.println();
		}
	}

}
