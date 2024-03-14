package be.kuleuven;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
public class CheckNeighboursInGrid {
    public static Iterable<Integer> getSameNeighboursIds(Iterable<Integer> grid,int width, int height, int indexToCheck) {
        List<Integer> result = new ArrayList<>();
        List<Integer> gridArray = new ArrayList<>();
        grid.forEach(gridArray::add);

        int w = indexToCheck%width;
        int h = indexToCheck/width;
        for(int i=h-1; i <= h+1; i++) {
            for (int j = w-1; j <= w+1; j++) {
                int index = i*width+j;
                if(!(i < 0 || i >= height || j < 0 || j >= width || index == indexToCheck)){
                    if(gridArray.get(indexToCheck) == gridArray.get(index)){
                        result.add(index);
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        CheckNeighboursInGrid ch = new CheckNeighboursInGrid();
        List<Integer> grid = Arrays.asList(0, 0, 1, 0,
                                           1, 1, 0, 2,
                                           2, 0, 1, 3,
                                           0, 1, 1, 1);
        System.out.println(ch.getSameNeighboursIds(grid,4,4,14));

    }

}
