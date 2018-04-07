package qarvis;

public class Queue {
	
	Node tail;
	
	public Queue(Node tail) {
		this.tail = tail;
	}
	
	public Node peek() {
		if (tail == null || tail.next == null) {
			return tail;
		}
		else {
			return tail.next;
		}
	}
	
	public Node dequeue() {
		if (tail == null) {
			return tail;
		}
		else {
			if (tail.next == null || tail.next == tail) {
				tail = null;
				return tail;
			}
			else {
				Node node = tail.next;
				tail.next = tail.next.next;
				return node;
			}
		}
	}
	
	public void enqueue(Node node) {
		if (tail == null) {
			tail = node;
		}
		else if (tail.next == null) {
			node.next = tail;
			tail.next = node;
			tail = tail.next;
		}
		else {
			node.next = tail.next;
			tail.next = node;
			tail = tail.next;
		}
	}
	
	public String print() {
		
		System.out.println("inside of print()!");
		String list = "__LIST__\n";
		
		if (tail == null) {
			System.out.println("tail is null!");
			return null;
		}
		
		if (tail.next == null) {
			String ret = "`1.` **" + tail.name + "**";
			System.out.println("ret: " + ret);
			return ret;
		}
		
		int count = 1;
		
		Node node = tail.next;
		do {
			System.out.println("list: " + list);
			list += "`" + count + ".` **" + node.name + "**\n";
			count++;
			node = node.next;
		} while(node != null && node != tail.next);
		
		return list;
	}
	
	public void delete(String name) {
		System.out.println("inside of delete()!");
		
		if (tail == null) {
			System.out.println("delete() is returning!");
			return;
		}
		
		Node n = tail;
		Node prev = n.next;
		
		
		do {
			if (prev == null || prev == n) {
				break;
			}
			if (prev.next == n) {
				break;
			}
			prev = prev.next;
		}while(prev.next != n);
		
		do {
			if (n.name.equals(name)) {
				if (prev == null || prev == n) {
					tail = null;
					return;
				}
				else {
					if (n == tail) {
						System.out.println("prev: " + prev.name);
						System.out.println("tail: " + tail.name);
						prev.next = n.next;
						tail = prev;
						return;
					}
					if (prev == n.next) {
						System.out.println("==prev: " + prev.name);
						prev.next = null;
						return;
					}
					prev.next = n.next;
					return;
				}
			}
			prev = n;
			n = n.next;
		}while(n != tail);
		
	}
	
	public boolean contains(String name) {
		Node n = tail;
		
		if (n == null) {
			return false;
		}
		
		do {
			if (n == null) {
				return false;
			}
			if (n.name.equals(name)) {
				return true;
			}
			n = n.next;
		}while(n != tail);
		
		return false;
	}
}
