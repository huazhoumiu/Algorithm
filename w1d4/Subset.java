import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Subset {
    // T/F for k
    static boolean exist(int[] a, int k){
        int length = k + 1;
        boolean[][] matrix = new boolean[a.length][length];
        // set first col of every row is True
        for(int i = 0; i < a.length; i++){
            matrix[i][0] = true;
        }

        // if a[0] = n, set the nth col to Ture in row 0
        matrix[0][a[0]] = true;

        // start from row 1 and col 1
        for(int i = 1; i < a.length; i++){
            for(int j = 1; j < length; j++){
                if(a[i] == j || (a[i] < j && matrix[i - 1][j - a[i]]) || matrix[i - 1][j])
                    matrix[i][j] = true;
            }
        }

        // check if the kth col has true value
        // start from the last row
        for(int i = a.length - 1; i >= 0; i--){
            if(matrix[i][k])
                return true;
        }
        return false;
    }

    //single solution
    static int[] single(int[] a, int k){

        // avoid multiple true by one solution
        Arrays.sort(a);
        int length = k + 1;
        boolean[][] matrix = new boolean[a.length][length];
        // set first col of every row is True
        for(int i = 0; i < a.length; i++){
            matrix[i][0] = true;
        }

        // if a[0] = n, set the nth col to Ture in row 0
        matrix[0][a[0]] = true;

        // start from row 1 and col 1
        for(int i = 1; i < a.length; i++){
            for(int j = 1; j < length; j++){
                if(a[i] == j || (a[i] < j && matrix[i - 1][j - a[i]]) || matrix[i - 1][j])
                    matrix[i][j] = true;
            }
        }

        // check if the kth col has true value
        // start from the last row
        int curRow = -1;

        for(int i = a.length - 1; i >= 0; i--){
            if(matrix[i][k]){
                curRow = i;
                break;
            }
        }
        //no valid subset
        if (curRow == -1){
            return new int[0];
        }else if(curRow == 0){
            // if curRow = 0 which means there is only one subset which is a[0]
            return new int[]{a[0]};
        }

        int curCol = k;
        int [] tmp = new int[a.length];
        int pos = 0;
        while(curCol != 0){
            //find the first true
            if (curRow == 0){
                tmp[pos++] = a[0];
                break;
            }
            if (!matrix[curRow - 1][curCol]){
                tmp[pos++] = a[curRow];
                curCol -= a[curRow];
            }
            curRow -= 1;
        }
        int[] ret = new int[pos];
        System.arraycopy(tmp, 0, ret, 0, pos);

        return ret;
    }

    //multi solutions
    static List<int[]> multi(int[] a, int k){

        // avoid multiple true by one solution
        Arrays.sort(a);
        int length = k + 1;

        List<int[]> [][] matrix = new List[a.length][length];
        for(int i = 0; i < a.length; i++){
            for(int j = 0; j < length; j++){
                matrix[i][j] = new ArrayList<>();
            }
            matrix[i][0].add(new int[]{0});
        }

        // if a[0] = n, set the nth col to Ture in row 0
        matrix[0][a[0]].add(new int[]{a[0]});

        // start from row 1 and col 1
        for(int i = 1; i < a.length; i++){
            for(int j = 1; j < length; j++){
                if(a[i] == j){
                    matrix[i][j].add(new int[]{a[i]});
                }else if(a[i] < j && !matrix[i-1][j - a[i]].isEmpty()){
                    for(int[] arr: matrix[i-1][j-a[i]]){
                        int[] newArr = new int[arr.length + 1];
                        System.arraycopy(arr, 0, newArr, 0, arr.length);
                        newArr[arr.length] = a[i];
                        matrix[i][j].add(newArr);
                    }
                }
                if(!matrix[i - 1][j].isEmpty()){
                    for(int[] arr: matrix[i - 1][j]){
                        matrix[i][j].add(arr);
                    }
                }
            }
        }

        // check if the kth col has true value
        // start from the last row
        int curRow = -1;

        for(int i = a.length - 1; i >= 0; i--)
            if(!matrix[i][k].isEmpty())
                return  matrix[i][k];

        return new ArrayList<int[]>();

    }

//    Solve subset problem where S = {3, 4, 7, 8} and k = 15
    public static void main(String[] args) {
        int[] a = {3,4,7,8};
        int k = 15;
        //System.out.println(exist(a, k));
        //System.out.println(Arrays.toString(single(a, k)));
        List<int[]> multi = multi(a, k);
        for(int[] arr: multi){
            System.out.println(Arrays.toString(arr));
        }
    }
}
