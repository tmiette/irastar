package fr.umlv.astar.priorityqueue;

/**
 * Interface of the object that can be insert in priority queues.
 * 
 * @author Tom MIETTE
 * @author Sébastien MOURET
 * @version 1.0
 */
public interface PrioritizedElement {

	/**
	 * Get the priority value of the object.
	 * 
	 * @return the priority value of the object.
	 */
	public double getPriorityValue();

	/**
	 * Set the priority value of the object.
	 * 
	 * @param priorityValue
	 *            the priority value of the object.
	 */
	public void setPriorityValue(double priorityValue);

}