public class SearchBinary {
	static int[] list = {1, 3, 6, 8, 9, 22, 61};
	
	static int search_binary_recursive(int key, int low, int high) {
		int middle;
		while (low <= high) {
			middle = (low+high)/2;
			
			if (list[middle] == key)
				return middle;
			else if (list[middle] > key)
				return search_binary_recursive(key, low, middle-1);
			else if (list[middle] < key)
				return search_binary_recursive(key, middle+1, high);
		}

		return -1;
	}

	static int search_binary_loop(int key, int low, int high) {
		int middle;
		while (low <= high) {
			middle = (low+high)/2;
			
			if (key == list[middle])
				return middle;
			else if (list[middle] > key)
				high = middle - 1;
			else if (list[middle] < key)
				low = middle + 1;
		}

		return -1;
	}
	
	public static void main(String[] args) {
		System.out.println(search_binary_recursive(22, 0, list.length-1));
		System.out.println(search_binary_loop(22, 0, list.length-1));
	}
}
