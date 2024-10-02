import java.util.Arrays;
import java.util.Collections;

public class ThirdMax3 {
    public static int find(int[] a){
        Arrays.sort(a);
        return a[a.length-3];
    }

    public static void main(String[] args) {
        int[] a = {7, 20, 18, 4, 20, 19, 20, 3};
        System.out.println(find(a));
    }
}
