

public class ThirdMax1 {

    public static int find(int[] a){
        int max = a[0];
        int max1Idx = 0;
        int temp = max;
        int max2Idx = 0;
        for (int i = 1; i < a.length; i++) {
            if (a[i] > max) {
                max = a[i];
                max1Idx = i;
            }
            else{
                temp = a[i];
                max2Idx = i;
            }
        }

        max = temp;
        for (int i = 0; i < a.length; i++) {
            if (i != max1Idx && a[i] > max) {
                max = a[i];
                max2Idx = i;
            }
            else
                temp = a[i];
        }

        for(int i = 0; i < a.length; i++){
            if(i != max2Idx && i != max1Idx && a[i] > temp){
                temp = a[i];
            }
        }
        return temp;
    }

    public static void main(String[] args) {
        int[] a = {7, 20, 18, 4, 20, 19, 20, 3};
        System.out.println(find(a));
    }
}
