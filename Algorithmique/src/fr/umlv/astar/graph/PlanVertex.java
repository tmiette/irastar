package fr.umlv.astar.graph;

/**
 * Represents a vertex containing in a plan graph.
 * 
 * @author Tom MIETTE
 * @author Sebastien MOURET
 * @version 1.0
 */
public class PlanVertex {
	
	/*
	 * The x coordinate of the vertex.
	 */
	private final double xCoordinate;

	/*
	 * The x coordinate of the vertex.
	 */
	private final double yCoordinate;

	/**
	 * Constructor of a plan vertex.
	 * 
	 * @param x
	 *            the x coordinate.
	 * @param y
	 *            the y coordinate.
	 */
	public PlanVertex(final double xCoordinate, final double yCoordinate) {
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
	}

	/**
	 * Gets the x coordinate.
	 * 
	 * @return the x coordinate.
	 */
	public double getXCoordinate() {
		return this.xCoordinate;
	}

	/**
	 * Gets the y coordinate.
	 * 
	 * @return the y coordinate.
	 */
	public double getYCoordinate() {
		return this.yCoordinate;
	}

	/**
	 * Indicates whether some other object is "equal to" this one.
	 */
	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		if (!(o instanceof PlanVertex)) {
			return false;
		}

		PlanVertex pl = (PlanVertex) o;
		return ((this.xCoordinate == pl.xCoordinate) && (this.yCoordinate == pl.yCoordinate));
	}

	/**
	 * Returns a hash code for this plan vertex.
	 * 
	 * @return a hash code for the plan vertex.
	 */
	@Override
	public int hashCode() {
		return (int) (this.xCoordinate + this.yCoordinate);
	}

	/**
	 * Returns a string representation of the object.
	 * 
	 * @return a string representation of the object..
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("PlanVertex[x=");
		sb.append(this.xCoordinate);
		sb.append(";y=");
		sb.append(this.yCoordinate);
		sb.append("]");
		return sb.toString();
	}
}
