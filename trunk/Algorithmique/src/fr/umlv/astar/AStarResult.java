package fr.umlv.astar;

import java.util.List;

public interface AStarResult<V> {
	/**
	 * Returns the cost of the path.
	 * 
	 * @return the cost of the path.
	 */
	public double getPathWeight();

	/**
	 * Returns a list of successive vertices in the path.
	 * 
	 * @return a list of successive vertices in the path.
	 */
	public List<V> getPath();

	/**
	 * Returns the length of the path.
	 * 
	 * @return the length of the path.
	 */
	public int length();
}