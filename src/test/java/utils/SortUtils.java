package utils;

import models.TestModel;
import java.util.*;

public class SortUtils {

    public static boolean isTimeSorted(List<TestModel> testsModelList){
        ArrayList<String> StartTimeList = new ArrayList<>();
        for (TestModel model : testsModelList){
            StartTimeList.add(model.getStartTime());
        }
        List<String> unsortedDate = new ArrayList(StartTimeList);
        Collections.copy(unsortedDate, StartTimeList);
        StartTimeList.sort(Collections.reverseOrder());
        return StartTimeList.equals(unsortedDate);
    }

    public static boolean isJsonResponseSorted(TestModel[] testsArray, List<TestModel> testList, int size){
        return Arrays.equals(testList.stream().toArray(TestModel[]::new), Arrays.copyOfRange(testsArray,0, size));
    }
}