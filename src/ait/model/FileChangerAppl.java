package ait.model;

import ait.model.action.Action;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileChangerAppl {
     public static void main(String[] args) {
            try (
                    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                    PrintWriter printWriter = new PrintWriter(System.out, true)
            ) {
                printWriter.println("Enter inputFile name: ");
                String inputFile = br.readLine();

                printWriter.println("Enter outPutFile name: ");
                String outputFile = br.readLine();

                printWriter.println("Enter action (Sort, Reverse, Shuffle): ");
                String actionName = br.readLine();

                Action action = loadActionByName(actionName);
                if (action == null) {
                    printWriter.println("Unknow action: " + actionName);
                    return;
                }

                List<String> lines = readLinesFromFile(inputFile);
                List<String> result = action.perform(lines);
                writeLinesToFile(result, outputFile);

                printWriter.println("Operation '" + actionName + "' completed successfully.");
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
                e.printStackTrace();
            }
        }

        private static Action loadActionByName(String name) {
            try {
                String className = "ait.model.action." + name;
                Class<?> clazz = Class.forName(className);
                return (Action) clazz.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                System.out.println("wrong: "+e.getMessage());
                return null;
            }
        }



        private static List<String> readLinesFromFile(String fileName) throws IOException {
            List<String> lines = new ArrayList<>();
            try (BufferedReader fileReader = new BufferedReader(new FileReader(fileName))) {
                String line;
                while ((line = fileReader.readLine()) != null) {
                    lines.add(line);
                }
            }
            return lines;
        }

        private static void writeLinesToFile(List<String> lines, String fileName) throws IOException {
            try (PrintWriter writer = new PrintWriter(fileName)) {
                for (String line : lines) {
                    writer.println(line);
                }
            }
        }
}