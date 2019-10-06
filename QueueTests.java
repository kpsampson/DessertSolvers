
/**
 * Test the validity of code and ensures methods are functioning properly
 * 
 * @author Kylie Sampson
 *
 */
public class QueueTests {
	public static void main(String[] args) {
		System.out.println("testAddandToString(): " + testAddandToString());
		System.out.println("testAddingAndRemovingElements(): " + testAddingAndRemovingElements());
		System.out.println("testIsEmpty(): " + testIsEmpty());
	}

	/**
	 * Test that Guests are added to the queue, with their individual dietary
	 * restrictions. Also adds a guest with no allergies. Tests that the toString
	 * method returns the expected output
	 * 
	 * @return true if test passes, false otherwise
	 */
	private static boolean testAddandToString() {
		ServingQueue test1 = new ServingQueue(6);
		for (int i = 0; i < 5; i++) {
			test1.add(new Guest("allergy" + i));
		}
		test1.add(new Guest());

		Guest.resetNextGuestIndex();
		if (test1.toString()
				.contentEquals("[#1(allergy0), #2(allergy1), #3(allergy2), #4(allergy3), #5(allergy4), #6]")) {
			return true;
		}
		return false;
	}

	/**
	 * Test adding and removing elements. Frequent checks insure that each method is
	 * working as expected throughout. Peek is used to see the next element.
	 * 
	 * @return true if test passes, false otherwise
	 */
	private static boolean testAddingAndRemovingElements() {

		ServingQueue test2 = new ServingQueue(5);
		Guest one = new Guest("one");
		Guest two = new Guest("two");
		Guest three = new Guest("three");

		// [one]
		test2.add(one);
		if (!one.equals(test2.peek())) {
			return false;
		}
		// [one, two]
		test2.add(two);

		// [two]
		test2.remove();
		if (!two.equals(test2.peek())) {
			return false;
		}

		// [two, three]
		test2.add(three);

		if (!two.equals(test2.peek()))
			return false;

		test2.remove();
		test2.remove();

		// []
		if (test2.peek() != null)
			return false;
		return true;
	}

	/**
	 * Tests the isEmpty method when it should return false, as well as when it
	 * should return true. The queue starts as empty, then has elements added to it.
	 * It is then tested and should not be empty. All elements are then removed and
	 * should be empty once again.
	 * 
	 * @return true if test passes, false otherwise
	 */
	private static boolean testIsEmpty() {

		ServingQueue test3 = new ServingQueue(5);
		if (!test3.isEmpty()) {
			return false;
		}
		for (int i = 0; i < 5; i++) {
			test3.add(new Guest("allergy" + i));
		}
		if (test3.isEmpty()) {
			return false;
		}
		for (int i = 0; i < 5; i++) {
			test3.remove();
		}
		
		Guest.resetNextGuestIndex();
		if (!test3.isEmpty()) {
			return false;
		}
		return true;
	}
}
