import java.util.Arrays;

public class QuickSort {
    static int quickSelect(int[] a, int lo, int hi, int pos) {
        int pivot, idx;
        int mid = (lo + hi)/2;
        if((hi - lo) > 2){
            if(a[lo] > a[hi]) {
                if(a[lo] > a[mid]) {
                    if (a[mid] > a[hi]) {
                        pivot = a[mid];
                        idx = mid;
                    } else {
                        pivot = a[hi];
                        idx = hi;
                    }
                }else{
                    pivot = a[lo];
                    idx = lo;
                }
            }else{
                if(a[mid] > a[hi]) {
                    pivot = a[hi];
                    idx = hi;
                }else{
                    if(a[mid] > a[lo]) {
                        pivot = a[mid];
                        idx = mid;
                    }else{
                        pivot = a[lo];
                        idx = lo;
                    }
                }
            }
        }else{
            pivot = a[hi];
            idx = hi;
        }
        int temp = a[hi];
        a[hi] = pivot;
        a[idx] = temp;

        int i = lo, j = hi - 1;
        while (i <= j) {
            while(a[i] < pivot)
                i++;
            while(a[j] > pivot)
                j--;
            if(i <= j){
                temp = a[i];
                a[i] = a[j];
                a[j] = temp;
                i++;
                j--;
            }
        }
        temp = a[i];
        a[i] = a[hi];
        a[hi] = temp;

        if(j >= pos)
            return quickSelect(a, lo, j, pos);
        else if(i == pos)
            return i;
        else
            return quickSelect(a, i + 1 , hi, pos);

    }

    static void quickSort(int[] a, int lo, int hi) {
        if(lo >= hi)
            return;
        int idx = quickSelect(a, lo, hi, (lo+hi)/2);
        int pivot = a[idx];
        a[idx] = a[hi];
        a[hi] = pivot;
        int i = lo, j = hi - 1;
        while(i <= j){
            while(a[i] < pivot)
                i++;
            while(a[j] > pivot)
                j--;
            if(i <= j){
                int temp = a[i];
                a[i] = a[j];
                a[j] = temp;
                i++;
                j--;
            }

        }
        a[hi] = a[i];
        a[i] = pivot;
        quickSort(a, lo, j);
        quickSort(a, i + 1, hi);
    }

    public static void main(String[] args) {
        int[] a = {8,9,21,34,5,77,11,14};
//        int mid = quickSelect(a,0,a.length-1, 4);
//        System.out.println(mid);
        quickSort(a, 0, a.length - 1);
        System.out.println(Arrays.toString(a));
    }
}
