public class exercise4{
  public static void windowPosSum(int[] a,  int n){
    /** your code here */
    for(int i = 0; i < a.length; i++){
      if(a[i] < 0){
        continue;
      }
      else{
        int sum = 0;
        for(int j = 0; j <= n; j++){
          if(i + j >= a.length){
            break;
          }
          sum = sum + a[i + j];
        }
        a[i] = sum;
      }
    }
  }
  public static void main(String[] args){
    int[] a = {1, 2, -3, 4, 5, 4};
    int n = 3;
    windowPosSum(a, n);

    //should print 4, 8, -3, 13, 9, 4
    System.out.println(java.util.Arrays.toString(a));
  }
}
