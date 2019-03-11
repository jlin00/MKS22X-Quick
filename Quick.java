import java.util.*;

public class Quick{
  public static int partition(int[] data, int start, int end){
    if (data.length <= 1) return data.length;

    int random = (int)(Math.random() * data.length); //selects a random index of data
    System.out.println(random);

    int temp = data[0]; //temporary storage to perform swap
    data[0] = data[random]; //performs the swap
    data[random] = temp;

    //sort array according to partition
    while (start != end){ //if start is not equivalent to end
      if (data[start] > data[0]){ //if greater than partition value, move to end
        temp = data[start]; //temporary storage for data[start]
        data[start] = data[end]; //swap with data[end]
        data[end] = temp;
        end--; //decrease end
      }
      else{
        start++; //increase start
      }
    }

    //move the partition value
    temp = data[start]; //middle value
    if (data[0] <= data[start]){
      int temp_value = data[start - 1]; //swap with value next to middle value
      data[start - 1] = data[0];
      data[0] = temp_value;
      return start - 1;
    }
    else{
      data[start] = data[0]; //swap with middle value
      data[0] = temp;
    }

    return start;
  }

  /*return the value that is the kth smallest value of the array.*/
  public static int quickselect(int[] data, int k){
    return quickselectH(data, k, 0, data.length - 1); //calls helper function
  }

  public static int quickselectH(int[] data, int k, int start, int end){
    return 0;
  }

  public static void main(String[] args) {
    int[] data1 = {999,999,0,1,999,999};
    System.out.println(quickselect(data1, 2)); //should print 999;
  }
}
