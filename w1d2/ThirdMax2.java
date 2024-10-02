public class ThirdMax2 {
    public static int find(int[] a){
        int max = a[0];
        int preMax = max;
        int prePreMax = preMax;

        if(max < a[1])
            max = a[1];
        else{
            preMax = a[1];
            prePreMax = preMax;
        }
        if(max < a[2]){
            preMax = max;
            max = a[2];
        }else{
            if(preMax < a[2]){
                preMax = a[2];
            }else{
                prePreMax = a[2];
            }
        }

        for (int i = 3; i < a.length; i++) {
            if (a[i] > max) {
                prePreMax = preMax;
                preMax = max;
                max = a[i];
            }
            else{
                if(preMax < a[i]){
                    prePreMax = preMax;
                    preMax = a[i];
                }
                else if (prePreMax < a[i]){
                    prePreMax = a[i];
                }
            }
        }
        return prePreMax;
    }

    public static void main(String[] args) {
        int[] a = {7, 20, 18, 4, 20, 19, 20, 3};
        System.out.println(find(a));
    }
}
