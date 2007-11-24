import fr.umlv.astar.AStar;
import fr.umlv.astar.AStarPath;
import fr.umlv.astar.graph.CheckerboardGraph;
import fr.umlv.astar.graph.CheckerboardVertex;
import fr.umlv.astar.graph.PlanGraph;
import fr.umlv.astar.graph.PlanVertex;
import fr.umlv.astar.heuristic.AStarHeuristic;
import fr.umlv.astar.heuristic.CheckboardEuclideanAStarHeuristic;
import fr.umlv.astar.heuristic.PlanEuclideanAStarHeuristic;

public class TestAStar {

	public static void main(String[] args) {

		// checkboard graph
		double[][] matrix = { { 6.0, 1.0, 1.0, 1.0 }, { 8.0, 4.0, 8.0, 6.0 },
				{ 0.0, 0.0, 0.0, 7.0 }, { 5.0, 7.0, 6.0, 6.0 } };
		double[][] matrix2 = { { 2.0, 1.0, 1.0 }, { 1.0, 0.0, 1.0 },
				{ 1.0, 2.0, 2.0 } };
		CheckerboardGraph graph = new CheckerboardGraph(matrix2);

		AStarHeuristic<CheckerboardVertex> heuristic = new CheckboardEuclideanAStarHeuristic();
		CheckerboardVertex start = graph.getCheckerboardVertex(0, 0);
		CheckerboardVertex end = graph.getCheckerboardVertex(2, 2);

		AStarPath<CheckerboardVertex> path = AStar.aStarAlgorithm(graph,
				heuristic, start, end);
		if (path == null) {
			System.out.println("Cannot reach end vertex.");
		} else {
			System.out.println(path.toString());
		}

		// plan graph
		PlanGraph graph2 = new PlanGraph();
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

		AStarHeuristic<PlanVertex> heuristic2 = new PlanEuclideanAStarHeuristic();
		PlanVertex start2 = pv1;
		PlanVertex end2 = pv7;

		AStarPath<PlanVertex> path2 = AStar.aStarAlgorithm(graph2, heuristic2,
				start2, end2);
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

}
