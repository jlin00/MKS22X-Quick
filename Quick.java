import java.util.*;

public class Quick{
  private static int partition(int[] data, int start, int end){
    if (start == end) return start; //if nothing needs to be sorted, just return index

    int random = (int)(Math.random() * (end - start + 1)) + start; //selects a random index of data, index of partition value
    //System.out.println(random); //for debugging purposes

    int pivot = data[random]; //stores the pivot value
    data[random] = data[start]; //performs the swap with starting index
    data[start] = pivot;

    random = start; //index number of pivot changes to start
    start++; //incease start by one

    //sort array according to partition value, beginning at start and ending at end
    while (start != end){ //if start is not equivalent to end
      if (data[start] > pivot){ //if greater than partition value, move to end
        int temp = data[start]; //temporary storage for data[start]
        data[start] = data[end]; //swap with data[end]
        data[end] = temp;
        end--; //decrease end
      }
      else{
        start++; //increase start
      }
    }

    //move the partition value when start == end
    if (data[start] < pivot){ //if pivot is greater than data[start]
      data[random] = data[start]; //perform the swap
      data[start] = pivot;
      random = start; //index of partition value changes
    }
    else{
      data[random] = data[start -1]; //performs the swap
      data[start - 1] = pivot;
      random = start - 1; //index of partition value changes
    }

    return random;
  }

  /*return the value that is the kth smallest value of the array.*/
  public static int quickselect(int[] data, int k){
    int start = 0;
    int end = data.length - 1;
    int pivot = partition(data, start, end); //pibot index
    while (pivot != k){
      if (pivot > k) end = pivot - 1; //if index is greater than k, recur for left side
      else start = pivot + 1; //if index is less than k, recur for right side
      pivot = partition(data, start, end); //updated partition with new start and end
    }
    return data[pivot];
  }

  public static void main(String[] args) {
    int[] data1 = {999,999,0,1,999,999};
    System.out.println(quickselect(data1, 2)); //should print 999;

    int[]ary = { 2, 10, 15, 23, 0,  5};  //sorted : {0,2,5,10,15,23}
   System.out.println(quickselect( ary , 0 )); // would return 0
   System.out.println(quickselect( ary , 1 )); //  would return 2
   System.out.println(quickselect( ary , 2 )); //  would return 5
   System.out.println(quickselect( ary , 3 )); //  would return 10
   System.out.println(quickselect( ary , 4 )); // would return 15
   System.out.println(quickselect( ary , 5 )); //  would return 23
  }
}
