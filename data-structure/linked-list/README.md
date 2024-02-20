# 개념

## LinkedList
 
> 각 노드는 데이터 필드와 다음 노드에 대한 참조로 구성되어 있고, 각 노드가 서로 연결되어 있는 자료형**

## Java에서의 LinkedList

> Deque 인터페이스 혹은 List 인터페이스를 구현한 Collection 구현체

** 참고) Java의 컬렉션 프레임워크에 구현된 LinkedList 클래스는 doubly linked list로 구현 되어 있음 **

### 장점

- 메모리상에 연속으로 저장되지 않으므로, **메모리 관리**가 용이 (성능X, 편의O)
- 즉, **삽입과 삭제가 용이**

### 단점

- 삽입, 삭제시 O(1) 이지만, 중간에 요소를 삽입하는 경우에는 사실상 O(n) 소요. —> 보완하기 위해 이중연결리스트 사용
- 즉, **탐색 속도가 느림**
- 노드 간의 포인터 연결로 인해 캐시 효율이 떨어질 수 있음


# 구현

## 1) 단순 연결 리스트

### 동적으로 구현

- 코드1 (배열단위 삽입&삭제)
    
    ```java
    static class Node {
            public Node next;
            public int e;
    
            public Node(int e) {
                this.e = e;
                this.next = null;
            }
        }
    
        static class LinkedList {
            Node head;
            Node tail;
    
            int size;
    
            public LinkedList() {
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
    ```
    
- 코드2 (원소단위 삽입&삭제)
    
    ```java
    package datastructure;
    
    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.util.StringTokenizer;
    
    // 수열 편집
    public class LinkedListEachElement {
    	public static class Node {
    		int element;
    		Node next;
    		
    		public Node(int elemenet) {
    			this.element = elemenet;
    			this.next = null;
    		}
    	}
    	
    	public static class LinkedList {
    		Node head;
    		Node tail;
    		int size;
    		
    		public LinkedList() {
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
    }
    ```
    

### 정적으로 구현

- 코드3 (코드1 베이스 + 정적 할당)
    
    ```java
    static int NODE_MAX = 5000;
        static class Node {
            public Node next;
            public int e;
    
            public Node(int e) {
                this.e = e;
                this.next = null;
            }
        }
    
        static class LinkedList {
            Node head;
            Node tail;
    
            Node[] nodeArr;
    
            int cnt;
    
            int size;
    
            public LinkedList() {
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
        }
    ```
    

### 동적 / 정적 시간차이
* 노드풀을 두어 관리하는 방식(정적)이 더 빠를것이라 생각했으나, 자바에서는 비슷함.
* 이유는, JVM이 이미 노드풀을 두어 관리하는 방식을 사용하기 때문.


## 2) 이중 연결 리스트

### 동적으로 구현

- 코드4 보기 (코드2 베이스 + prev 구현)
    
    ```java
    package datastructure;
    
    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.util.StringTokenizer;
    
    // single에 비해 삭제만 변경
    public class DoubleLinkedListEachElement {
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
    	
    	public static class LinkedList {
    		Node head;
    		Node tail;
    		int size;
    		
    		public LinkedList() {
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
    }
    ```
    

### 단순 연결 / 이중 연결 시간차이

- double linked list를 성능적으로 올릴려면, 추가적인 로직이 필요함
    
    **커서를 두어야함!**
    

# 참고

[https://inpa.tistory.com/entry/JAVA-☕-LinkedList-구조-사용법-완벽-정복하기#:~:text=LinkedList의 가장 큰 특징,를 변경하기만하면 된다](https://inpa.tistory.com/entry/JAVA-%E2%98%95-LinkedList-%EA%B5%AC%EC%A1%B0-%EC%82%AC%EC%9A%A9%EB%B2%95-%EC%99%84%EB%B2%BD-%EC%A0%95%EB%B3%B5%ED%95%98%EA%B8%B0#:~:text=LinkedList%EC%9D%98%20%EA%B0%80%EC%9E%A5%20%ED%81%B0%20%ED%8A%B9%EC%A7%95,%EB%A5%BC%20%EB%B3%80%EA%B2%BD%ED%95%98%EA%B8%B0%EB%A7%8C%ED%95%98%EB%A9%B4%20%EB%90%9C%EB%8B%A4).

# 예상 질문

- Array vs ArrayList vs LinkedList
    
    ** 참고) ArrayList에는 원소가 선형적으로 저장되는 것이 아니라, 원소의 참조값이 선형적으로 저장되어 잇음! 따라서, 순차리스트라고 하기에 애매할지도?? 진정한 순차리스트는 array!! **
    
    —> int, char 등 원시 클래스는 원소값이 저장되어 있음. 따라서 어떤 타입을 저장하냐에 따라 다름.
    
- double linked list 는 언제 유리한가?
    
    커서가 있어야 한다!