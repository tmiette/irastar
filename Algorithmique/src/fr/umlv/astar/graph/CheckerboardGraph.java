package fr.umlv.astar.graph;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Implementation of a graph representing by a checkerboard.
 * 
 * @author Tom MIETTE
 * @author Sebastien MOURET
 * @version 1.0
 */
public class CheckerboardGraph implements Graph<CheckerboardVertex> {

	/*
	 * Matrix of vertices.
	 */
	private final CheckerboardVertex[][] matrix;

	/*
	 * Map which associates a vertex to the list of its successors.
	 */
	private final HashMap<CheckerboardVertex, ArrayList<CheckerboardVertex>> successorsLists;

	/**
	 * Contructor of a CheckerboardGraph with a matrix of weights.
	 * 
	 * @param matrix
	 *            the matrix of weight.
	 */
	public CheckerboardGraph(double[][] matrix) {
		this.matrix = new CheckerboardVertex[matrix.length][matrix[0].length];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				this.matrix[i][j] = new CheckerboardVertex(i, j, matrix[i][j]);
			}
		}
		this.successorsLists = new HashMap<CheckerboardVertex, ArrayList<CheckerboardVertex>>();
	}

	/**
	 * Returns the checkboard vertex with the specified coordinates, or null if
	 * the coordinates are away from the checkboard.
	 * 
	 * @param x
	 *            x coordinate.
	 * @param y
	 *            y coordinate.
	 * @return the checkboard vertex or null if the coordinates are wrong.
	 */
	public CheckerboardVertex getCheckerboardVertex(int x, int y) {
		if (this.isCorrectCoordinates(x, y)) {
			return this.matrix[x][y];
		}
		return null;
	}

	/**
	 * Returns if an edge exists between start and end.
	 * 
	 * @param start
	 *            start of the edge.
	 * @param end
	 *            end of the edge.
	 * @return if an edge exists between start and end.
	 */
	public boolean isEdge(CheckerboardVertex start, CheckerboardVertex end) {
		ArrayList<CheckerboardVertex> successors = this
				.getAdjacentVertices(start);
		for (CheckerboardVertex cv : successors) {
			if (cv.equals(end)) {
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
	public double edgeValue(CheckerboardVertex start, CheckerboardVertex end) {
		if (this.isEdge(start, end)) {
			return end.getWeight();
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
	public int numberOfSuccessors(CheckerboardVertex vertex) {
		ArrayList<CheckerboardVertex> successors = this
				.getAdjacentVertices(vertex);
		return successors.size();
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
	public CheckerboardVertex getSuccessor(CheckerboardVertex vertex, int n) {
		ArrayList<CheckerboardVertex> successors = this
				.getAdjacentVertices(vertex);
		return successors.get(n);
	}

	/**
	 * Returns a list containing all successors of a vertex. Successors are the
	 * adjcency tiles in the checkerboard.
	 * 
	 * @param vertex
	 *            the vertex.
	 * @return the list of successors.
	 */
	private ArrayList<CheckerboardVertex> getAdjacentVertices(
			CheckerboardVertex vertex) {

		// gets the list of the successors of the vertex in the hashmap
		ArrayList<CheckerboardVertex> list = this.successorsLists.get(vertex);

		// if the list doesn't exist already, creates it
		if (list == null) {
			// creates the list
			list = new ArrayList<CheckerboardVertex>();
			// egts the coordinates of the vertex
			int x = vertex.getXCoordinate();
			int y = vertex.getYCoordinate();

			// tests the coordinates of all adjency tiles and add the successor
			// if the coordinates are correct.
			if (this.isCorrectCoordinates(x, y - 1)) {
				// don't add successor with negative weight
				if (this.matrix[x][y - 1].getWeight() > 0) {
					list.add(this.matrix[x][y - 1]);
				}
			}
			if (this.isCorrectCoordinates(x + 1, y - 1)) {
				if (this.matrix[x + 1][y - 1].getWeight() > 0) {
					list.add(this.matrix[x + 1][y - 1]);
				}
			}
			if (this.isCorrectCoordinates(x + 1, y)) {
				if (this.matrix[x + 1][y].getWeight() > 0) {
					list.add(this.matrix[x + 1][y]);
				}
			}
			if (this.isCorrectCoordinates(x + 1, y + 1)) {
				if (this.matrix[x + 1][y + 1].getWeight() > 0) {
					list.add(this.matrix[x + 1][y + 1]);
				}
			}
			if (this.isCorrectCoordinates(x, y + 1)) {
				if (this.matrix[x][y + 1].getWeight() > 0) {
					list.add(this.matrix[x][y + 1]);
				}
			}
			if (this.isCorrectCoordinates(x - 1, y + 1)) {
				if (this.matrix[x - 1][y + 1].getWeight() > 0) {
					list.add(this.matrix[x - 1][y + 1]);
				}
			}
			if (this.isCorrectCoordinates(x - 1, y)) {
				if (this.matrix[x - 1][y].getWeight() > 0) {
					list.add(this.matrix[x - 1][y]);
				}
			}
			if (this.isCorrectCoordinates(x - 1, y - 1)) {
				if (this.matrix[x - 1][y - 1].getWeight() > 0) {
					list.add(this.matrix[x - 1][y - 1]);
				}
			}
			// add the new list in the hashmap
			this.successorsLists.put(vertex, list);
		}

		// returns the list
		return list;
	}

	/**
	 * Returns if two coordinates are containing in the checkerboard.
	 * 
	 * @param x
	 *            the x coordinates.
	 * @param y
	 *            the y coordinates.
	 * @return if the coordinates are containing in the checkerboard.
	 */
	private boolean isCorrectCoordinates(int x, int y) {
		// tests the x coordinate
		if (x < 0 || x >= this.matrix.length) {
			return false;
		}
		// tests the y coordinate
		if (y < 0 || y >= this.matrix[0].length) {
			return false;
		}
		return true;
	}

	/**
	 * Returns a string representation of this checkerbord graph.
	 * 
	 * @return a string representation of this checkerbord graph.
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				// Print just the weight, coordinates are inherent
				sb.append(this.matrix[i][j].getWeight());
				sb.append(" ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}
}
