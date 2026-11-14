import java.util.*;

class NumberContainers {

    HashMap<Integer, Integer> indexNumber;
    HashMap<Integer, TreeSet<Integer>> numberIndex;

    public NumberContainers() {

        indexNumber = new HashMap<>();
        numberIndex = new HashMap<>();
    }

    public void change(int index, int number) {

        if(indexNumber.containsKey(index)) {

            int oldNumber = indexNumber.get(index);

            numberIndex.get(oldNumber).remove(index);
        }

        indexNumber.put(index, number);

        numberIndex.putIfAbsent(number, new TreeSet<>());

        numberIndex.get(number).add(index);
    }

    public int find(int number) {

        if(!numberIndex.containsKey(number) ||
           numberIndex.get(number).isEmpty()) {

            return -1;
        }

        return numberIndex.get(number).first();
    }
}
