public class SortedList {
	private int size;
	ListNode head;

	/**
	 * Construct an empty list
	 **/
	public SortedList() {
		size = 0;
		head = null;
	}

	/**
	 * Returns true if this list is empty, false otherwise.
	 **/
	public boolean isEmpty() {
		return (size == 0);
	}

	/**
	 * Returns the length of this list.
	 **/
	public int length() {
		return size;
	}

	/**
	 * Inserts the element x into the proper sorted location.
	 **/
	public void insert(Keyable x) {
		ListNode newnode = new ListNode(x, null);

		if (head == null) {
			head = newnode;
		} else if (!head.item.lessThan(x)) { // checks if x should be positioned in the first place;
			newnode.next = head;
			head = newnode;
		} else {
			ListNode temp = head;
			while (temp.next != null) {
				if (!temp.next.item.lessThan(x)) {
					newnode.next = temp.next;
					temp.next = newnode;
					temp = temp.next;
					break;
				}
				temp = temp.next;
			}
			if (temp.next == null) {
				temp.next = newnode;
			}
		}
		size++;
	}

	/**
	 * Returns the element with the given key, or null if none of the elements have
	 * that key.
	 **/
	public Keyable find(int key) {
		ListNode temp = head;
		while (temp != null) {
			if (temp.item.getKey() == key) {
				return temp.item;
			}
			temp = temp.next;
		}
		return null;
	}

	/**
	 * Returns a String representation of this list.
	 **/
	public String toString() {
		int i;
		Object obj;
		String result = "[  ";

		ListNode cur = head;

		while (cur != null) {
			obj = cur.item;
			result = result + obj.toString() + "  ";
			cur = cur.next;
		}
		result = result + "]";
		return result;
	}
}