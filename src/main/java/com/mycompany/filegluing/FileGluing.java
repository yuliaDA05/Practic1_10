/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.filegluing;
import java.io.*;
import static java.lang.System.out;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileGluing {
    public static void main(String[] args) {
        System.out.println("Дойнеко Юлия Антоновна. Вариант 1");
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Введите количество файлов для склейки:");
            int numOfFiles = Integer.parseInt(reader.readLine());

            if (numOfFiles < 2) {
                System.out.println("Для склейки файлов нужно указать как минимум два файла.");
                return;
            }

            StringBuilder gluedContent = new StringBuilder();
            String directoryPath = "";

            for (int i = 1; i <= numOfFiles; i++) {
                System.out.println("Введите путь к файлу " + i + ":");
                String filePath = reader.readLine();

                String fileContent = readStringFromFile(filePath);
                gluedContent.append(fileContent);

                if (i == 1) {
                    directoryPath = Paths.get(filePath).getParent().toString();
                }
            }

            writeStringToFile(Paths.get(directoryPath, "glued_file.txt").toString(), gluedContent.toString());

            System.out.println("Файлы успешно склеены в файл 'glued_file.txt' рядом с первым файлом.");
            System.out.println("Содержимое склееного файла:");
            System.out.println(gluedContent.toString());
            System.out.println("Количество байтов в склееном файле: " + countBytesInGluedFile(gluedContent.toString()));
        } catch (IOException | NumberFormatException e) {
            System.out.println("Произошла ошибка: " + e.getMessage());
        }
    }

    public static String readStringFromFile(String path) throws FileNotFoundException, IOException {
        File file = new File(path);
        if (file.exists()) {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            reader.close();
            return content.toString();
        } else {
            return "";
        }
    }

    public static void writeStringToFile(String path, String content) throws IOException {
        FileWriter writer = new FileWriter(path);
        writer.write(content);
        writer.close();
    }
    public static int countBytesInGluedFile(String gluedContent) {
    String[] parts = gluedContent.split("\\s+"); 
    int byteCount = 0;

    for (String part : parts) {
        byteCount += part.length() / 4;
    }

    return byteCount;
   }
}
