
/**
 * Mimics the table the guests are sat at. Uses a circular queue where elements
 * are enqueued at the tail and dequeued at the head. Head, tail, and size are
 * used to track the front, back, and size of the array.
 * 
 * @author Kylie Sampson
 *
 */
public class ServingQueue {
	private Guest[] array;
	private int head;
	private int tail;
	private int size;

	/**
	 * Creates a new array based queue with a capacity of "seatsAtTable" guests.
	 * This queue should be initialized to be empty.
	 * 
	 * @param seatsAtTable the size of the array holding this queue data
	 */
	public ServingQueue(int seatsAtTable) {
		array = new Guest[seatsAtTable];
		head = -1; // Head is initialized to -1 because there are no elements yet
		tail = -1; // "" for tail
		size = 0; // initialized to 0
	}

	/**
	 * Checks whether there are any guests in this serving queue.
	 * 
	 * @return true when this queue contains zero guests, and false otherwise.
	 */
	public boolean isEmpty() { // The order checked does not matter, boolean so if there is an element anywhere
								// true will be returned
		for (int i = 0; i < array.length; i++) {
			if (array[i] != null) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Adds a single new guest to this queue (to be served after the others that
	 * were previously added to the queue).
	 * 
	 * @param newGuest is the guest that is being added to this queue.
	 * @throws IllegalStateException when called on a ServingQueue with an array
	 *                               that is full
	 */
	public void add(Guest newGuest) throws IllegalStateException {
		if (size == array.length) {
			throw new IllegalStateException("Table is full! No more guests can be added");
		}
		if (head == -1) { // If the head has not yet been initialized as the head it should be set to 1
							// when a guest has been added
			head = 0;
		}

		tail = (tail + 1) % array.length; // Makes the loop circle back if it calls a size greater than
		array[tail] = newGuest;
		size++; // a guest is added, size is incremented
	}

	/**
	 * Accessor for the guest that has been in this queue for the longest. This
	 * method does not add or remove any guests.
	 * 
	 * @return a reference to the guest that has been in this queue the longest.
	 * @throws IllegalStateException when called on an empty ServingQueue
	 */
	public Guest peek() {
		return array[head]; // Head is the start of the array, where items are removed
	}

	/**
	 * Removes the guest that has been in this queue for the longest.
	 * 
	 * @return a reference to the specific guest that is being removed.
	 * @throws IllegalStateException when called on an empty ServingQueue
	 */
	public Guest remove() throws IllegalStateException {
		if (this.isEmpty()) {
			throw new IllegalStateException("Table is empty. No guest to be removed");
		}
		Guest toBeRemoved = array[head]; // this is the element that must be returned. The head is used because the
											// first one to enter is the first to go
		array[head] = null; // removes the guest from the queue
		head = (head + 1) % array.length;
		size--; // a guest has been removed

		return toBeRemoved;
	}

	/**
	 * The string representation of the guests in this queue should display each of
	 * the guests in this queue (using their toString() implementation), and should
	 * display them in a comma separated list that is surrounded by a set of square
	 * brackets. (this is similar to the formatting of
	 * java.util.ArrayList.toString()). The order that these guests are presented in
	 * should be (from left to right) the guest that has been in this queue the
	 * longest, to the guest that has been in this queue the shortest. When called
	 * on an empty ServingQueue, returns "[]".
	 * 
	 * @return string representation of the ordered guests in this queue
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String orderedGuest = "[";
		for (int i = head; i < array.length; i++) { // Starts at the head and iterates through the non null index for
													// the remaining loop
			if (array[i] != null) { // Adds comma to the end of the string
				orderedGuest = orderedGuest + array[i].toString() + ", ";
			}
		}
		for (int j = 0; j < head; j++) { // Iterates through the array prior to head
			if (array[j] != null) {
				orderedGuest = orderedGuest + array[j].toString() + ", ";
			}
		}
		orderedGuest = orderedGuest.substring(0, orderedGuest.length() - 2); // Removes the last comma and space
		orderedGuest = orderedGuest.concat("]");
		return orderedGuest; // Returns the string
	}

}
