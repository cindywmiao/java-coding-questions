class Sort{
	 public void sort(int[] A) {
        // Write your code here
        if(A == null || A.length == 0) return;
        quickSort(A, 0, A.length - 1);
    }
    
    public void quickSort(int[] A, int low, int high){
        int i = low, j = high;
        int pivot = A[low + (high - low)/2];
        while(i <= j){
            while(A[i] < pivot) i++;
            while(A[j] > pivot) j--;
            if(i <= j){
                exchange(A, i, j);
                i++;
                j--;
            }
        }
        if(low < j) quickSort(A, low, j);
        if(i < high) quickSort(A, i, high);
    }
    
    private void exchange(int[] A, int i, int j){
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    private int[] helper;
    public void sort2(int[] A) {
        // Write your code here
        if(A == null || A.length == 0) return;
        helper = new int[A.length];
        mergeSort(A, 0, A.length - 1);
    }
    
    public void mergeSort(int[] A, int low, int high){
        if(low < high){
            int middle = low + (high - low)/2;
            mergeSort(A, low, middle);
            mergeSort(A, middle + 1, high);
            merge(A, low, middle, high);
        }
    }
    
    private void merge(int[] A, int low, int middle, int high) {
        for (int i = low; i <= high; i++) helper[i] = A[i];
        int i = low, j = middle + 1, k = low;
        while (i <= middle && j <= high) {
            if (helper[i] <= helper[j]) A[k++] = helper[i++];
            else A[k++] = helper[j++];
        }
        while (i <= middle) A[k++] = helper[i++];
        
    }
}