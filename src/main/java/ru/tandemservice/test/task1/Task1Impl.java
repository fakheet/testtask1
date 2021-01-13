package ru.tandemservice.test.task1;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <h1>Задание №1</h1>
 * Реализуйте интерфейс {@link IStringRowsListSorter}.
 *
 * <p>Мы будем обращать внимание в первую очередь на структуру кода и владение стандартными средствами java.</p>
 */
public class Task1Impl implements IStringRowsListSorter {
    private static IStringRowsListSorter INSTANCE;

    public static synchronized Task1Impl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Task1Impl();
        }
        return (Task1Impl) INSTANCE;
    }

    /**
     * @param rows список записей таблицы (например, результат sql select), которые нужно отсортировать по указанной колонке
     * @param columnIndex индекс колонки, по которой нужно провести сортировку
     */
    @Override
    public void sort(final List<String[]> rows, final int columnIndex) {
        rows.sort(new StringArrayComparator(columnIndex));
    }

    public static class StringArrayComparator implements Comparator<String[]> {
        int index;

        /**
         * @param index индекс колонки по которой нужно провести сортировку
         */
        public StringArrayComparator(int index) {
            this.index = index;
        }

        @Override
        public int compare(String[] string1, String[] string2) {
            // sorting order: null -> empty string -> numbers -> strings

            String e1 = string1[index];
            String e2 = string2[index];

            if (e1 == null && e2 == null) return 0;
            if (e1 == null) return -1;
            if (e2 == null) return 1;

            if (e1.equalsIgnoreCase(e2)) return 0;
            if (e1.isEmpty()) return -1;
            if (e2.isEmpty()) return 1;

            ArrayList<String> subs1 = new ArrayList<>();
            ArrayList<String> subs2 = new ArrayList<>();
            // create regex "signed integer OR any other sequence" pattern
            Pattern pattern = Pattern.compile("-?\\D+|\\d+");
            Matcher matcher1 = pattern.matcher(e1);
            Matcher matcher2 = pattern.matcher(e2);

            // split the strings
            while (matcher1.find()) subs1.add(matcher1.group());
            while (matcher2.find()) subs2.add(matcher2.group());

            // prepare for iteration
            Iterator<String> iter1 = subs1.iterator();
            Iterator<String> iter2 = subs2.iterator();

            while (iter1.hasNext() && iter2.hasNext()) {
                String w1 = iter1.next();
                String w2 = iter2.next();

                if (!w1.equals(w2)) {
                    if (w1.matches("-?\\d+") && w2.matches("-?\\d+")) {
                        Integer i1 = Integer.parseInt(w1);
                        Integer i2 = Integer.parseInt(w2);
                        return i1.compareTo(i2);
                    }
                    return w1.compareTo(w2);
                }
            }
            if (iter1.hasNext()) return 1;
            if (iter2.hasNext()) return -1;

            return 0;
        }
    }
}
