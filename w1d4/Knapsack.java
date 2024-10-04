import java.util.Arrays;

public class Knapsack {

    public static class Item{
        private int value;
        private int weight;
        public Item(int value, int weight){
            this.weight = weight;
            this.value = value;
        }
        public int getWeight(){
            return weight;
        }
        public int getValue(){
            return value;
        }
    };

    public static int[] mostValue(Item [] a, int w){
        int len = w + 1;
        int[][] matrix = new int[a.length][len];
        for(int i = a[0].getWeight(); i < len; i++){
            matrix[0][i] = a[0].getValue();
        }
        for(int i = 1; i < a.length; i++){
            int weight = a[i].getWeight();
            int value = a[i].getValue();
            for(int j = 0; j < len; j++){
                int gap = j - weight;
                int oldValue = matrix[i-1][j];
                if(gap > 0){
                    int tmp = matrix[i-1][j - weight] + value;
                    if(oldValue < tmp){
                        matrix[i][j] = tmp;
                        continue;
                    }
                }else if(gap == 0){
                    if(value > oldValue){
                        matrix[i][j] = value;
                        continue;
                    }
                }
                matrix[i][j] = oldValue;
            }
        }


        int [] tmp = new int[a.length];
        int pos = 0;

        int colNum = len - 1;
        int rowNum = a.length - 1;
        while(colNum != 0){
            if(rowNum == 0){
                tmp[pos++] = 0;
                break;
            }
            if(matrix[rowNum - 1][colNum] != matrix[rowNum][colNum]){
                tmp[pos++] = rowNum;
                colNum -= a[rowNum].getWeight();
            }
            rowNum--;
        }

        int[] ret = new int[pos];
        System.arraycopy(tmp, 0, ret, 0, pos);
        return ret;

    }
    public static void main(String[] args) {

        Item[] items = new Item[5];
        Item a = new Item(15, 2);
        Item b = new Item(9, 3);
        Item c = new Item(16, 4);
        Item d = new Item(12, 5);
        Item e = new Item(17, 6);
        items[0] = a;
        items[1] = b;
        items[2] = c;
        items[3] = d;
        items[4] = e;
        System.out.println(Arrays.toString(mostValue(items, 12)));



    }
}
