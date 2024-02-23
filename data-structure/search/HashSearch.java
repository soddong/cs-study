    public class HashSearch {
        private static final int TABLE_SIZE = 10;
        private Integer[] hashTable;
    
        public HashSearch() {
            hashTable = new Integer[TABLE_SIZE];
        }
    
        private int hashFunction(int key) {
            return key % TABLE_SIZE;
        }
    
        public void insert(int key) {
            int index = hashFunction(key);
            while (hashTable[index] != null) { // 충돌 발생 시
                index = (index + 1) % TABLE_SIZE;
            }
            hashTable[index] = key;
        }
    
        public int search(int key) {
            int index = hashFunction(key);
            while (hashTable[index] != null) {
                if (hashTable[index] == key) {
                    return index;
                }
                index = (index + 1) % TABLE_SIZE;
                if (index == hashFunction(key)) { 
                    break;
                }
            }
            return -1;
        }
    
        public static void main(String[] args) {
            HashSearch hashSearch = new HashSearch();
    
            hashSearch.insert(1);
            hashSearch.insert(11);
            hashSearch.insert(21);
    
            // 데이터 탐색 예시
            int key = 21;
            int foundIndex = hashSearch.search(key);
            if (foundIndex != -1) {
                System.out.println("키 " + key + "는 해시 테이블의 " + foundIndex + "번 인덱스에 있습니다.");
            } else {
                System.out.println("키 " + key + "를 찾을 수 없습니다.");
            }
        }
    }