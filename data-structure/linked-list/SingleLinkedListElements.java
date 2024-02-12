static class Node {
    public Node next;
    public int e;

    public Node(int e) {
        this.e = e;
        this.next = null;
    }
}

static class SingleLinkedListElements {
    Node head;
    Node tail;
    int size;

    public SingleLinkedListElements() {
        head = null;
        tail = null;
        size = 0;
    }

    Node newNode(int element) {
        return new Node(element);
    }

    void addLast(int element) {
        if (size == 0) {
            head = newNode(element);
            tail = head;
        } else {
            Node tmp = newNode(element);
            tail.next = tmp;
            tail = tmp;
        }
        size += 1;
    }

    void addFirstElements(int[] element) {
        Node newHead = newNode(element[0]);

        Node now = newHead;
        for(int i=1; i<element.length; i++) {
            Node tmp = newNode(element[i]);
            now.next = tmp;
            now = tmp;
        }
        now.next = head;
        head = newHead;

        size += element.length;
    }

    void addLastElements(int[] element) {
        Node now = tail;

        for(int e : element) {
            Node tmp = newNode(e);
            now.next = tmp;
        }

        size += element.length;
    }

    int popLeft() {
        int val = this.head.e;
        this.head = this.head.next;
        return val;
    }

    void delete(int idx, int cnt) {  // idx 번 인덱스부터 cnt 개 만큼 삭제하기
        Node cur = head;
        if(idx == 0) {  // 맨 앞이 삭제되는 경우 (head가 재조정 되는 경우)
            for(int i=0; i<cnt; i++) {
                cur = cur.next;
            }
            head = cur;
            return;
        }
        // idx개 만큼 이동하기
        for(int i=1; i<idx; i++) {
            cur = cur.next;
        }
        Node anchor = cur;  // 삭제되기 직전 위치 기억하기
        for(int i=0; i<cnt; i++) {
            cur = cur.next;
        }
        anchor.next = cur.next;

        if (anchor.next == null) {
            tail = anchor;
        }
    }

    // idx가 node의 길이보다 작거나 같같다는 가정
    void addMiddleElements(int idx, int[] element) {

        if (idx == size) {
            addLastElements(element);
            return;
        }

        if (idx == 0) {
            addFirstElements(element);
            return;
        }

        int i = 0;
        Node now = head;

        while(++i < idx) {
            now = now.next;
        }

        for(int e : element) {
            Node tmp = newNode(e);
            tmp.next = now.next;
            now.next = tmp;
            now = tmp;
        }
        size += element.length;
    }

    void printNode() {
        Node now = head;
        while (now.next != null) {
            now = now.next;
        }
    }

}