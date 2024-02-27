# 트리

사이클이 없는 연결 그래프


## **필수 조건**

1. 직간접적으로 모두 연결
2. 사이클 x
3. 간선 = 노드 - 1

(두개가 만족하면 나머지 하나는 따라옴)


## Routed Tree

- 부모가 없는 유일한 정점인 루트가 존재하는 트리
- 하나의 트리에서 다양한 Routed Tree가 될 수 있음
- but, 트리의 지름은 유일함
- 트리의 지름
    1. 임의의 한 노드를 루트 t로 지정한 후, 가장 먼 점 u를 찾는다
    2. u에서 가장 먼 점 v를 찾는다 
    3. u-v가 트리의 지름



## 이진 트리 **(Binary Tree)**

---

<aside>
각 노드가 최대 두개의 자식 노드를 갖는 트리.

</aside>

- 데이터를 탐색하거나 정렬할 때 유용!
- 구현은 배열 & 연결 리스트 모두 가능하지만, 배열이 더 유리

## 이진 탐색 트리 **(Binary Search Tree)**

---

<aside>
왼쪽 자식 < 부모 노드 && 오른쪽 자식 > 부모 노드 를 만족하는 이진 트리

</aside>

- 검색, 삽입, 삭제 에 유리
- 정렬 : 중위 순회를 통한 데이터 정렬 가능

## 완전 이진 트리 **(Complete Binary Tree)**

---

<aside>
마지막 레벨을 제외한 모든 레벨이 완전히 채워져 있고, 마지막 레벨은 왼쪽에서 오른쪽으로 순서대로 채워져 있는 이진 트리

</aside>

## 이진 힙 (Binary Heap)

---

자식 노드들이 특정한 성질을 가지고 정렬되어 있는 완전 이진 트리로 만들어진 자료구조.


- 최대, 최소값에 빠른 접근에 유리 —> 우선순위 큐(ADT) 구현에 활용
- 정렬 : 루트 노드를 뽑아 데이터 정렬 가능

## 트라이 (Re**trie**val Tree = Trie)

---


문자열의 집합을 표현하는데 특화된 트리 자료구조.


- 동작 방식
    - 계층 구조로, 루트에서부터 각 문자열의 글자를 따라가면서 트리를 탐색하는 방식
- 특징
    - 공통 접두사를 공유하는 문자열들을 같은 부모의 자식으로 둠
    - 하지만, 최악의 공간복잡도가 O(NM)으로 메모리가 많이 필요함
    (N은 문자열의 개수, M은 문자열의 평균 길이)
- 활용
    - 검색 엔진 (자동완성, 오타교정 등), IP 라우팅 (프리픽스 트리)


# 탐색


자료를 찾는 작업. 

탐색의 단위는 항목(value)이며, 구분의 기준은 키(key) 이다.


- 정렬 되지 않은 놈들의 탐색 : 선형, 해시
- 정렬 된 놈들의 탐색 : 이진

## 선형 탐색

---

- 단순히 처음부터 끝까지 검사하여 항목을 찾는 방식
- 코드
    

## 이진 탐색

---

- 정렬되어있는 배열에서 중간 부분의 원소를 기준으로 두부분으로 나누고 탐색 범위를 줄여가는 탐색 방식
- 코드
    

## 해시 탐색

---

- 값과 index를 연결해 둠으로써 O(1)의 시간으로 값을 찾는 방식
- 데이터 저장하는 알고리즘 & 데이터 검색하는 알고리즘 필요
    - 공간 : 해시 배열을 저장하기 위해 기존 배열x2 사이즈의 배열이 추가로 필요함
    - 해시값 충돌 : 해시값이 겹치면 다음 인덱스를 훑음 —> 해시값 충돌을 막기위한 다양한 기법이 존재


# 예상 질문

- Hash Table을 사용하면 O(1)의 시간복잡도인데, BST를 사용하는 경우는 언제일까?
    
    해시 테이블은 키를 기반으로 값을 빠르게 검색할 수 있고, 이상적으로는 O(1)의 시간복잡도를 가지지만, 해시 충돌이 발생하면 O(1)의 시간복잡도가 나오지 않을 수 있다.
    
    특히 해시 함수는 순서를 가지지 않는 무작위 값에서 더 좋은 성능을 보이기 때문에, **정렬 등 순서를 가지는 데이터나 범위를 검색해야 할 때**는 BST가 더 적합하다.
    
- 주어진 Tree가 BST인지 확인하는 방법은?
    
    **중위 순회**를 통해 정렬된 값인지 확인해보면 알 수 있다.