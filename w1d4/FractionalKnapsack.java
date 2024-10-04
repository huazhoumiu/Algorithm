import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FractionalKnapsack {
    static class Item {
        private int weight;
        private int value;
        Item(int value, int weight) {
            this.value = value;
            this.weight = weight;
        }
        int getValue() {
            return value;
        }
        int getWeight() {
            return weight;
        }
    }

    static class FractionalKnapsackItem implements Comparable<FractionalKnapsackItem> {
        private float valuePerWeight;
        private int index;
        public FractionalKnapsackItem(float fractionalWeight, int index) {
            this.valuePerWeight = fractionalWeight;
            this.index = index;
        }
        public float getValuePerWeight() {
            return valuePerWeight;
        }
        public int getIndex() {
            return index;
        }


        @Override
        public int compareTo(FractionalKnapsackItem o) {
            return Float.compare(o.valuePerWeight, this.valuePerWeight);
        }
        @Override
        public String toString() {
            return "FractionalKnapsackItem: " + this.valuePerWeight + " " + this.index;
        }
    }

    public static float maxValue(Item[] items, int weight) {
        List<FractionalKnapsackItem> list = new ArrayList<FractionalKnapsackItem>();
        for (int i = 0; i < items.length; i++) {
            list.add(new FractionalKnapsackItem((float) items[i].getValue() /items[i].getWeight(), i));
        }
        Collections.sort(list);

        float sum = 0.0F;
        int pos = 0;
        while(weight >= items[list.get(pos).getIndex()].getWeight()) {
            sum += items[list.get(pos).getIndex()].getValue();
            weight -= items[list.get(pos).getIndex()].getWeight();
            pos++;
        }
        if(weight > 0){
            float fractional = (float)weight / (float)items[list.get(pos).getIndex()].getWeight() ;
            sum += fractional * (float)items[list.get(pos).getIndex()].getValue();
        }

        return sum;
    }
    
    public static void main(String[] args) {
        Item[] items = {
                new Item(15, 2),
                new Item(9, 3),
                new Item(16, 4),
                new Item(12, 5),
                new Item(17, 6),
        };
        System.out.println(maxValue(items, 12));
    }
}
