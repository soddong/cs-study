// single에 비해 삭제만 변경
public static class Node {
	int element;
	Node next;
	Node prev;
	
	public Node(int elemenet) {
		this.element = elemenet;
		this.next = null;
		this.prev = null;
	}
}

public static class DoubleLinkedListEachElement {
	Node head;
	Node tail;
	int size;
	
	public DoubleLinkedListEachElement() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}

	public Node getNode(int element) {
		return new Node(element);
	}
	
	public void insertFirst(int element) {
		Node node = getNode(element);
		node.next = head;
		head.prev = node; // 추가
		
		head = node;
		size += 1;
	}
	
	public void insertLast(int element) {
		if (size == 0) {
			head = getNode(element);
			tail = head;
			size += 1;
			return;
		}
		
		Node node = getNode(element);
		tail.next = node;
		node.prev = tail; // 추가
		
		tail = node;
		size += 1;
	}
	
	public void insertMiddle(int idx, int element) {
		if (idx > size) {
			return;
		}
		
		if (idx == 0) {
			insertFirst(element);
			return;
		}
		
		if (idx == size) {
			insertLast(element);
			return;
		}
		
		Node node = getNode(element);
		Node now = head;
		int i = 0;
		while (now != null) {
			if (i++ == idx-1) {
				node.next = now.next;
				now.next.prev = node; // 추가
				now.next = node;
				node.prev = now; // 추가
				size += 1;
			}
			now = now.next;
		}
	}
	
	public void deleteLast() { // 간단해짐!
		Node now = tail;
		tail = now.prev;
		
		size -= 1;
	}
	
	public void deleteFirst() {
		if (size == 0) {
			return;
		} else if (size == 1) {
			head = null;
		} else {
			head = head.next;
		}
		size -= 1;
	}
	
	public void delete(int idx) {
		if (idx >= size)
			return;
		if (idx == 0) {
			deleteFirst();
			return;
		}
		if (idx == size-1) {
			deleteLast();
			return;
		}
		
		int i = 0;
		Node now = head;
		while (now != null) {
			if (i++ == idx) {
//					now.next = now.next.next;
//					now.next.next.prev = now;
				now.prev.next = now.next;
				now.next.prev = now.prev;
				size -=1;
				return;
			}
			now = now.next;
		}
	}
	
	public void set(int idx, int element) {
		Node now = head;
		int i = 0;
		while (now != null) {
			if (i++ == idx) {
				now.element = element;
			}
			now = now.next;
		}
	}
	
	public int find(int idx) {
		Node now = head;
		int i = 0;
		while (now != null) {
			if (i++ == idx) {
				return now.element;
			}
			now = now.next;
		}
		return -1;
	}
	
	public void print() {
		Node now = head;
		do {
			System.out.println(now.element + ", " + size);
			now = now.next;
		} while (now != null);
	}
}