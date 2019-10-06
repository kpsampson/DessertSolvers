
/**
 * Runs a series of problems using the ServingQueue and Guest classes
 * 
 * @author Kylie Sampson
 *
 */
public class DessertSolvers {
	/**
	 * 
	 * @param numberOfGuests - number of guest at the table, num to initialize array
	 * @param guestsSkipped  - how many guest should be skipped each time one is
	 *                       served
	 * @return guest - first guest to be served the next course, the last guest to
	 *         be served
	 * @throws IllegalArgumentException - thrown if numOfGuests is less than one or
	 *                                  if guestsSkipped is not positive
	 */
	public static Guest firstDessertVariableSkips(int numberOfGuests, int guestsSkipped)
			throws IllegalArgumentException {
		if (numberOfGuests < 1) {
			throw new IllegalArgumentException("Invalid number of guests. Must be 1 or more.");
		}
		if (guestsSkipped < 0) {
			throw new IllegalArgumentException("Number of guests to be skipped can not be negative");
		}

		ServingQueue firstProblem = new ServingQueue(numberOfGuests);
		for (int i = 0; i < numberOfGuests; i++) {
			firstProblem.add(new Guest());
		}
		Guest firstRemoved = firstProblem.remove(); // The first guest is always removed first, no guest skipped
		for (int k = 0; k < numberOfGuests - 2; k++) {
			for (int j = 0; j < guestsSkipped; j++) {
				firstProblem.add(firstProblem.remove());
			}
			firstProblem.remove();
		}
		Guest.resetNextGuestIndex();
		if (firstProblem.isEmpty()) {
			return firstRemoved;
		}
		return firstProblem.remove();
	}

	public static Guest firstDessertVariableCourses(int numberOfGuests, int coursesServed) {
		if (numberOfGuests < 1) {
			throw new IllegalArgumentException("Invalid number of guests");
		}

		ServingQueue secondProblem = new ServingQueue(numberOfGuests);
		Guest lastRemoved = null;
		for (int b = 0; b < coursesServed - 1; b++) {
			ServingQueue nextCourse = new ServingQueue(numberOfGuests);

			// If first iteration, b is filled with guests
			if (b == 0) {
				for (int i = 0; i < numberOfGuests; i++) {
					secondProblem.add(new Guest());
				}
			}
			nextCourse.add(secondProblem.remove()); // The first guest is always removed first, no guest skipped
			for (int k = 0; k < numberOfGuests - 2; k++) {
				secondProblem.add(secondProblem.remove());
				nextCourse.add(secondProblem.remove());
			}
			lastRemoved = secondProblem.remove();
			nextCourse.add(lastRemoved);
			System.out.println("last removed: " + lastRemoved);

			for (int q = 0; q < numberOfGuests - 1; q++) {
				nextCourse.add(nextCourse.remove());
			}
			System.out.println(nextCourse.toString());
			secondProblem = nextCourse;
		}
		return lastRemoved;

	}

	public static void main(String[] args) {
		//System.out.println(firstDessertVariableSkips(1, 3));
		//System.out.println(firstDessertVariableCourses(8, 3));
	}
}
