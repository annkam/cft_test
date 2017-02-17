import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by eshkigal on 17.02.17.
 */
public class Solution {

    private static final String exitMessage = " Нажмите Enter, чтобы завершить программу.";

    public static void main(String[] args) {
        if (args.length < 4) {
            System.out.println("Указаны не все аргументы!" + exitMessage);
            exitByEnterPressed();
        }
        String inputFileName = args[0];
        String outputFileName = args[1];
        String arrayType = args[2];
        String order = args[3];
        ArrayList<String> rawData = new ArrayList<>();
        String data[];
        int intArr[];
        boolean isAscending;
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName))) {
            while(reader.ready()) {
                rawData.add(reader.readLine());
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("Входной файл с заданым именем не найден." + exitMessage);
            exitByEnterPressed();
        }
        catch (IOException e) {
            System.out.println("Ошибка чтения входных данных." + exitMessage);
            exitByEnterPressed();
        }
        data = new String[rawData.size()];
        rawData.toArray(data);
        arrayType = checkArrayType(data, arrayType);
        isAscending = checkOrder(order);
        try(FileWriter writer = new FileWriter(outputFileName)) {
            if (arrayType.equals("-i")) {
                intArr = Arrays.stream(data).mapToInt(Integer::parseInt).toArray();
                InsertionSorter.sortIntArray(intArr, isAscending);
                for (int i = 0; i < intArr.length; i++)
                    writer.write(intArr[i] + "\n");

            } else {
                InsertionSorter.sortStringArray(data, isAscending);
                for (int i = 0; i < data.length; i++)
                    writer.write(data[i] + "\n");
            }
        }
        catch (IOException e) {
            System.out.println("Ошибка записи выходных данных." + exitMessage);
        }
    }

    // Завершение программы по нажатии пользователем Enter
    public static void exitByEnterPressed() {
        try {
            System.in.read();
            System.exit(1);
        }
        catch (IOException ignored) {

        }
    }

    // Проверка соответствия выбранного типа данных типу данных в файле
    public static String checkArrayType(String[] arr, String expectedType) {
        if (expectedType.equals("-i")) {
            try {
                int intArr[] = Arrays.stream(arr).mapToInt(Integer::parseInt).toArray();
            }
            catch (NumberFormatException e) {
                System.out.println("Вы пытаетесь отсортировать массив, содержащий строки, как массив целых чисел. Тип сортируемых данных будет изменен.");
                return "-s";
            }
        }
        else if (expectedType.equals("-s")) {
            try {
                int intArr[] = Arrays.stream(arr).mapToInt(Integer::parseInt).toArray();
                System.out.println("Вы пытаетесь отсортировать массив целых чисел как массив строк. Тип сортируемых данных будет изменен.");
                return "-i";
            }
            catch (NumberFormatException e) {
                return  expectedType;
            }
        }
        else {
            System.out.println("Вы указали неверный тип входных данных. Данные будут отсортированы по умолчанию как массив строк.");
            return "-s";
        }
        return expectedType;

    }

    // Проверка способа упорядочивания отсортированных элементов
    public static boolean checkOrder(String order) {
        if(order.equals("-a"))
            return true;
        else if(order.equals("-d"))
            return false;
        System.out.println("Вы неверно указали тип сортировки. Элементы будут отсортированы по возрастанию по умолчанию.");
        return true;
    }
}
