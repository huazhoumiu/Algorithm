import java.util.Arrays;

public class Heap {

    public static int buildHeapTopDown(int[] a){
        int[] heap = new int[a.length + 1];
        int i = 1;
        int j = 0;
        int count = 1;
        while(j < a.length){
            heap[i] = a[j++];
            int k = i++;
            while((k > 1) && (count++ > 0 && (heap[k / 2] < heap[k]))){
                int temp = heap[k];
                heap[k] = heap[k/2];
                heap[k/2] = temp ;
                k = k/2;
            }
        }

        System.out.println(Arrays.toString(heap));

        return count;
    }

    public static int buildHeapBottomUp(int[] a, int stop){
        int start = stop / 2;
        int count = 1;
        for(int i = start; i > -1; i--) {
            int k = i;
            while (k != 0) {
                    int temp = a[k];
                    if(count++ > 0 && 2*k + 1 < stop){
//                        if(count++ > 0 && (a[2*k + 1] > temp || temp < a[2*k])){
                        if(a[2*k + 1] > temp || temp < a[2*k]){
//                            if(count++ > 0 && (a[2*k + 1] > a[2*k])){
                            if(a[2*k + 1] > a[2*k]){
                                a[k] = a[2*k+1];
                                a[2*k+1] = temp;
                                k = 2*k+1;
                            }else{
                                a[k] = a[2*k];
                                a[2*k] = temp;
                                k = 2*k;
                            }
                        }
                        else
                            k = 0;
                    }else if(count++ > 0 && 2*k < stop && a[2*k] > temp){
                        a[k] = a[2*k];
                        a[2*k] = temp;
                        k = 2*k;
                    }
                    else
                        k = 0;
                }

        }
        //System.out.println(Arrays.toString(a));
        return count;
    }

    public static int[] phase1(int[] a){
        int[] heap = new int[a.length + 1];
        int i = 1;
        int j = 0;
        while(j < a.length){
            heap[i] = a[j++];
            int k = i++;
            while(k > 1 && heap[k / 2] < heap[k]){
                int temp = heap[k];
                heap[k] = heap[k/2];
                heap[k/2] = temp ;
                k = k/2;
            }
        }
        return heap;
    }

    public static int heapSort(int[] a){
        int len = a.length;
        int count = 0;
        while(len > 1){
            count += buildHeapBottomUp(a,len);
            int temp = a[1];
            a[1] = a[len-1];
            a[len-1] = temp;
            len--;
        }

        System.out.println(Arrays.toString(a));
        return count;

    }


    public static void main(String[] args) {
        int[] test1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
        int[] test2 = {2, 1, 4, 3, 6, 5, 8, 7, 10, 9, 12, 11, 14, 13, 16, 15};
        int[] test3 = {4, 2, 3, 1, 5, 8, 7, 6, 11, 10, 12, 9, 13, 14, 16, 15};
        int[] test4 = {5, 6, 7, 4, 2, 3, 1, 8, 11, 13, 14, 16, 10, 12, 11, 9};
        System.out.println("TopDown");
        System.out.println(buildHeapTopDown(test1));
        System.out.println(buildHeapTopDown(test2));
        System.out.println(buildHeapTopDown(test3));
        System.out.println(buildHeapTopDown(test4));


        System.out.println("Bottom Up");

        int[] temp1 = new int[test1.length + 1];
        System.arraycopy(test1, 0, temp1, 1, test1.length + 1 - 1);
        System.out.println(buildHeapBottomUp(temp1, temp1.length));
        int[] temp2 = new int[test2.length + 1];
        System.arraycopy(test2, 0, temp2, 1, test2.length + 1 - 1);
        System.out.println(buildHeapBottomUp(temp2, temp2.length));
        int[] temp3 = new int[test3.length + 1];
        System.arraycopy(test2, 0, temp3, 1, test3.length + 1 - 1);
        System.out.println(buildHeapBottomUp(temp3, temp3.length));
        int[] temp4 = new int[test4.length + 1];
        System.arraycopy(test4, 0, temp4, 1, test4.length + 1 - 1);
        System.out.println(buildHeapBottomUp(temp4, temp4.length));

        System.out.println("heap sort");
        int[] tmp1 = new int[test1.length + 1];
        System.arraycopy(test1, 0, tmp1, 1, test1.length + 1 - 1);
        System.out.println(heapSort(tmp1));

        int[] tmp2 = new int[test1.length + 1];
        System.arraycopy(test1, 0, tmp2, 1, test2.length + 1 - 1);
        System.out.println(heapSort(tmp2));

        int[] tmp3 = new int[test1.length + 1];
        System.arraycopy(test3, 0, tmp3, 1, test3.length + 1 - 1);
        System.out.println(heapSort(tmp3));

        int[] tmp4 = new int[test1.length + 1];
        System.arraycopy(test4, 0, tmp4, 1, test4.length + 1 - 1);
        System.out.println(heapSort(tmp4));
    }
}
