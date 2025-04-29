package ait.model;

import ait.model.action.Action;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileChangerAppl {
    public static void main(String[] args) {
        try (
                BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
                PrintWriter consoleWriter = new PrintWriter(System.out, true)
        ) {
            consoleWriter.print("Введите имя входного файла: ");
            consoleWriter.flush();
            String inputFile = consoleReader.readLine();

            consoleWriter.print("Введите имя выходного файла: ");
            consoleWriter.flush();
            String outputFile = consoleReader.readLine();

            consoleWriter.print("Введите действие (sort, reverse, shuffle): ");
            consoleWriter.flush();
            String actionName = consoleReader.readLine();

            // Чтение строк из файла
            List<String> lines = Files.readAllLines(Paths.get(inputFile));

            // Загрузка действия по имени
            Action action = getActionByName(actionName);

            // Выполнение действия
            List<String> result = action.perform(lines);

            // Запись результата в файл
            try (PrintWriter fileWriter = new PrintWriter(outputFile)) {
                for (String line : result) {
                    fileWriter.println(line);
                }
            }

            consoleWriter.println("Операция '" + actionName + "' успешно выполнена.");

        } catch (Exception e) {
            System.err.println("Ошибка: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static Action getActionByName(String name) throws Exception {
        String className = "ait.model.action." + capitalize(name);
        Class<?> clazz = Class.forName(className);
        return (Action) clazz.getDeclaredConstructor().newInstance();
    }

    private static String capitalize(String str) {
        if (str == null || str.isEmpty()) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }
}
