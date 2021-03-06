import java.util.*;

public class Quick{
  private static int partition(int[] data, int start, int end){
    if (start == end) return start; //if nothing needs to be sorted, just return index

    //int random = (int)(Math.random() * (end - start + 1)) + start; //selects a random index of data, index of partition value
    //System.out.println(random); //for debugging purposes

    int random = data.length / 2; //automatically sets to index value to the middle value

    if (data[start] >= data[end] && data[end] >= data[random] || data[start] <= data[end] && data[end] <= data[random]){
      random = end; //if end is median, change index of partition
    }
    else if (data[end] >= data[start] && data[start] >= data[random] || data[end] <= data[start] && data[start] <= data[random]){
      random = start; //if start is median, change index of partition
    }
    //System.out.println(data[random]); //debugging purposes

    int pivot = data[random]; //stores the pivot value
    data[random] = data[start]; //performs the swap with starting index
    data[start] = pivot;

    random = start; //index number of pivot changes to start
    start++; //incease start by one

    //sort array according to partition value, beginning at start and ending at end
    while (start != end){ //if start is not equivalent to end
      int rand = (int)(Math.random() * 2); //either 1 or 0
      if (data[start] > pivot || (data[start] == pivot && rand == 0)){ //if greater than partition value, move to end
        int temp = data[start]; //temporary storage for data[start]
        data[start] = data[end]; //swap with data[end]
        data[end] = temp;
        end--; //decrease end
        //if (data[start] == pivot && rand == 0) System.out.println("left"); //debugging purposes
      }
      else{ //if less than partition value, leave on left side
        start++; //increase start
        //if (data[start] == pivot) System.out.println("right"); //debugging purposes
      }
    }

    //move the partition value when start == end
    if (data[start] < pivot){ //if pivot is greater than data[start]
      data[random] = data[start]; //perform the swap
      data[start] = pivot;
      random = start; //index of partition value changes
    }
    else{
      data[random] = data[start - 1]; //performs the swap
      data[start - 1] = pivot;
      random = start - 1; //index of partition value changes
    }

    return random;
  }

  private static int[] partitionDutch(int[] data, int lo, int hi){
    int random = (int)(Math.random() * (hi - lo + 1)) + lo; //randomizes pivot
    int pivot = data[random]; //pivot is at start
    data[random] = data[lo]; //swap
    data[lo] = pivot;
    int i = lo + 1;
    while (i <= hi){ //less than or equal because you still have to sort middle element in one of the three partitions
      if (data[i] < pivot){ //less than partition value
        int temp = data[lo]; //perform swap between start and i
        data[lo] = data[i];
        data[i] = temp;
        lo++; //increase lo
        i++; //increase i
      }
      else if (data[i] == pivot){ //equal to partition value
        i++; //increase i
      }
      else{
        int temp = data[hi]; //performs swap between i and end
        data[hi] = data[i];
        data[i] = temp;
        hi--; //decrease hi
      }
    }
    return new int[]{lo, hi};
  }

  //insertion sort
  public static void insertionSort(int[] data, int start, int end){
    int length = end - start + 1;
    for (int i = 1; i < length; i++){
      int old_val = data[start + i]; //keeps track of the value that is being shifted
      int j;
      for (j = i; j > 0 && old_val < data[start + j - 1]; j--){ //loops through backwards to calculate new_ind
          data[start + j] = data[start + j - 1]; //shifts down value in that spot by one
        }
      data[start + j] = old_val; //performs the insertion
    }
  }

  /*return the value that is the kth smallest value of the array.*/
  public static int quickselect(int[] data, int k){
    int start = 0;
    int end = data.length - 1;
    int pivot = partition(data, start, end); //pivot index
    while (pivot != k){
      if (pivot > k) end = pivot - 1; //if index is greater than k, recur for left side
      else start = pivot + 1; //if index is less than k, recur for right side
      pivot = partition(data, start, end); //updated partition with new start and end
    }
    return data[pivot];
  }

  public static void quicksort(int[] data){
    quicksortD(data, 0, data.length - 1); //calls helper method
  }

  public static void quicksortH(int[] data, int start, int end){
    if (start < end){
      int pivot = partition(data, start, end);
      quicksortH(data, pivot + 1, end); //recur for right side
      quicksortH(data, start, pivot - 1); //recur for left side
    }
  }

  public static void quicksortD(int[] data, int start, int end){ //use dutch partition
    /*
    if (start >= end){
      return;
    }
    */
    if (end - start < 10){
      insertionSort(data, start, end);
      return;
    }
    int[] pivots = partitionDutch(data, start, end);
    quicksortD(data, pivots[1] + 1, end); //recur for values greater than partition
    quicksortD(data, start, pivots[0] - 1); //recur for values less than partition
  }

  public static boolean sorted(int[] ary){
    for (int i = 1; i < ary.length; i++){
      if (ary[i - 1] > ary[i]) return false;
    }
    return true;
  }

  public static void main(String[] args) {
    /*
    int[] data1 = {999,999,999,999,999,999};
    System.out.println(quickselect(data1, 0)); //should print 999;


    int[] ary = { 2, 10, 15, 23, 0,  5};  //sorted : {0,2,5,10,15,23}
    System.out.println(quickselect( ary , 0 )); // would return 0
    System.out.println(quickselect( ary , 1 )); //  would return 2
    System.out.println(quickselect( ary , 2 )); //  would return 5
    System.out.println(quickselect( ary , 3 )); //  would return 10
    System.out.println(quickselect( ary , 4 )); // would return 15
    System.out.println(quickselect( ary , 5 )); //  would return 23

    int[] dupes = new int[8000000];
    for (int n = 0; n < 80000000; n++){
      dupes[n] = 99;
    }

    int[] uniq = new int[80000000];
    for (int n = 0; n < 80000000; n++){
      uniq[n] = (int)(Math.random() * 80000000);
    }

    System.out.println(quickselect(uniq, 50000000)); //should be fast
    System.out.println(quickselect(dupes, 50000000)); //should return 1
    */

    int[] array = new int[80000000];
    for (int i = 0;  i < array.length; i++){
      array[i] = (int)(Math.random() * 100);
    }
    //System.out.println(sorted(array));
    quicksort(array);
    System.out.println(sorted(array));

  }
}
