public class QuickSort {
	
	static int partition(int[] nonsorted, int left, int right) {
		int pivot = nonsorted[left];
		int low = left; int high = right+1;
		int tmp;
		
		do {
			System.out.println(low + ", " + high + ", " + left + ", " + right);
			do {
				low++;
			} while (low <= right && pivot > nonsorted[low]);
			
			do {
				high--;
			} while (high >= left && pivot < nonsorted[high]);
			
			if (low < high) {
				tmp = nonsorted[low];
				nonsorted[low] = nonsorted[high];
				nonsorted[high] = tmp;
			}
		} while (low < high);
		
		tmp = nonsorted[high];
		nonsorted[high] = nonsorted[left];
		nonsorted[left] = tmp;
		return high;
	}
	
	static void quicksort(int[] nonsorted, int left, int right) {
		if (left < right) {
			int pivot = partition(nonsorted, left, right);
			quicksort(nonsorted, left, pivot-1);
			quicksort(nonsorted, pivot+1, right);
			
		}
		
	}
	
	public static void main(String[] args) {
		int[] nonsorted = {1, 5, 2, 6, 8, 4, 2, 6, 34, 7, 6};
		quicksort(nonsorted, 0, nonsorted.length-1);
		
		for (int i = 0; i < nonsorted.length; i++) {
			System.out.printf("%d ", nonsorted[i]);
		}
	}
}
