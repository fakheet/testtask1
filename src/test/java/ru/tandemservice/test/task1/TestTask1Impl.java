package ru.tandemservice.test.task1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;

public class TestTask1Impl {
    @Test
    public void TestImplementation() {
        ArrayList<String[]> testRows = new ArrayList<>();
        testRows.add(new String[] {"7", "11"});
        testRows.add(new String[] {"5", "2"});
        testRows.add(new String[] {"1", ""});
        testRows.add(new String[] {"2", "1"});
        testRows.add(new String[] {"3", "1"});
        testRows.add(new String[] {"0", null});
        testRows.add(new String[] {"6", "3"});
        testRows.add(new String[] {"11", "word 200 word"});
        testRows.add(new String[] {"4", "1 word"});
        testRows.add(new String[] {"9", "num 10 word"});
        testRows.add(new String[] {"10", "num 20 two words"});
        testRows.add(new String[] {"12", "word 400"});
        testRows.add(new String[] {"8", "just words"});

        Task1Impl sorter = Task1Impl.getInstance();
        sorter.sort(testRows, 1);

        for (String[] ss : testRows) {
            System.out.println();
            for (String s : ss) {
                System.out.print(s + "; ");
            }
        }

        for (int i=0; i<12; i++) {
            assertEquals(testRows.get(i)[0], String.valueOf(i));
        }
    }
}
