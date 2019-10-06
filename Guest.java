
/**
 * Models a Guest used in the ServingQueue. Each guest is assigned a place in
 * line based on when they were added in comparison to other guests.
 * 
 * @author Kylie Sampson
 *
 */
public class Guest {
	private int placeInLine = 0;
	private static int guestIndex = 1; // first person in line is position 1 not 0
	private String dietaryRestriction;

	/**
	 * Resets the counting of newly constructed guest indexes, so that the next new
	 * Guest that is created will be associated with the guest index of one.
	 * 
	 * Note: that this will be helpful when running several tests, or solving
	 * solving several dessert simulation problems. And that this should never be
	 * called from ServingQueue.java
	 */
	public static void resetNextGuestIndex() {
		guestIndex = 1;
	}

	/**
	 * Constructs a new guest with no dietary restrictions. This guest should be
	 * associated with an index that is one larger than the previously constructed
	 * guest (or 1, if no prior guest, or if resetNextGuestIndex() was called more
	 * recently).
	 */
	public Guest() {
		this.placeInLine = Guest.guestIndex;
		guestIndex++; // Adds one for the next guest created
	}

	/**
	 * Constructs a new guest with the specified dietary restrictions. This guest
	 * should be associated with an index that is one larger than the previously
	 * constructed guest (or 1, if no prior guest, or if resetNextGuestIndex() was
	 * called more recently).
	 * 
	 * @param dietaryRestriction describes requirements for what this guest can and
	 *                           cannot eat
	 */
	public Guest(String dietaryRestriction) {
		this.placeInLine = Guest.guestIndex;
		guestIndex++; // Next guest
		this.dietaryRestriction = dietaryRestriction;
	}

	/**
	 * Access whether this guest has any dietary restrictions or not
	 * 
	 * @return true for guests constructed using new Guest(String), false otherwise
	 */
	public boolean hasDietaryRestriction() {
		if (dietaryRestriction != null) { // checks if the object's dietaryRestriction has been instantiated
			return true;
		}
		return false;
	}

	/**
	 * The string representation of a Guest should be formatted as, for examples:
	 * #3(no dairy) for a guest with a guest index of 3 and the dietary restriction
	 * of "no dairy", or: #4 for a guest with a guest index of 4 and no dietary
	 * restriction
	 * 
	 * @return string representing the guest index and any dietary restriction they
	 *         might have
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		if (dietaryRestriction != null) { // If they have a non null dietaryRestriction, it will be printed; else just
											// the place in line is printed
			return "#" + placeInLine + "(" + dietaryRestriction + ")";
		} else {
			return "#" + placeInLine;
		}
	}
}