static int NODE_MAX = 5000;
static class Node {
	public Node next;
	public int e;

	public Node(int e) {
		this.e = e;
		this.next = null;
	}
}

static class SingleLinkedListEachElementStatic {
	Node head;
	Node tail;

	Node[] nodeArr;

	int cnt;

	int size;

	public SingleLinkedListEachElementStatic() {
		head = null;
		tail = null;
		size = 0;
		nodeArr = new Node[NODE_MAX];
		cnt = 0;
	}

	Node newNode(int element) {
		nodeArr[cnt] = new Node(element);
		return nodeArr[cnt++];
	}
    
	public void insertFirst(int element) {
		Node node = getNode(element);
		node.next = head;
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
			Node node = getNode(element);
			tail.next = node;
			node = tail;
			size += 1;
			return;
		}
		
		Node node = getNode(element);
		Node now = head;
		int i = 0;
		while (now != null) {
			if (i++ == idx-1) {
				node.next = now.next;
				now.next = node;
				size += 1;
			}
			now = now.next;
		}
	}
	
	public void deleteLast() {
		int i = 0;
		Node now = head;
		while (now != null) {
			if (i++ == size - 2) {
				now.next = null;
				tail = now;
				size -=1;
				return;
			}
			now = now.next;
		}
	}
	
	public void delete(int idx) {
		if (idx >= size)
			return;
		if (idx == 0) {
			if (size == 0) {
				return;
			} else if (size == 1) {
				head = null;
			} else {
				head = head.next;
			}
			size -= 1;
			return;
		}
		if (idx == size-1) {
			deleteLast();
			return;
		}
		
		int i = 0;
		Node now = head;
		while (now != null) {
			if (i++ == idx-1) {
				now.next = now.next.next;
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
