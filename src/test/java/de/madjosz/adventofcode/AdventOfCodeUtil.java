package de.madjosz.adventofcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;


public final class AdventOfCodeUtil {

    private AdventOfCodeUtil() {}

    public static List<String> readLines(int year, int day) {
        return readLines(year, day, "");
    }

    public static List<String> readLines(int year, int day, String additional) {
        String resourceName = String.format("y%d/day%02d%s.txt", year, day, additional);
        try (BufferedReader r = new BufferedReader(
                new InputStreamReader(AdventOfCodeUtil.class.getResourceAsStream(resourceName)))) {
            List<String> lines = new ArrayList<>();
            String line;
            while ((line = r.readLine()) != null)
                lines.add(line);
            return lines;
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private static void createAdventOfCodeStubs(int year) throws IOException {
        Path testResources = Paths.get("src", "test", "resources", "de", "madjosz", "adventofcode");
        String classTemplate = Files.readString(testResources.resolve("class-template.txt"));
        String testclassTemplate = Files.readString(testResources.resolve("testclass-template.txt"));

        Path mainJavaDir = Paths.get("src", "main", "java", "de", "madjosz", "adventofcode", "y" + year);
        Path testJavaDir = Paths.get("src", "test", "java", "de", "madjosz", "adventofcode", "y" + year);
        Path mainResourcesDir = Paths.get("src", "main", "resources", "de", "madjosz", "adventofcode", "y" + year);
        Path testResourcesDir = testResources.resolve("y" + year);

        Files.createDirectories(mainJavaDir);
        Files.createDirectories(testJavaDir);
        Files.createDirectories(mainResourcesDir);
        Files.createDirectories(testResourcesDir);

        for (int day = 1; day <= 25; ++day) {
            String dayStr = String.format("%02d", day);
            String sourcecode = classTemplate.replace("${year}", Integer.toString(year)).replace("${day}", dayStr);
            String testsourcecode = testclassTemplate.replace("${year}", Integer.toString(year))
                    .replace("${day}", dayStr)
                    .replace("${dayInt}", Integer.toString(day));
            Files.writeString(mainJavaDir.resolve("Day" + dayStr + ".java"), sourcecode, StandardOpenOption.CREATE_NEW);
            Files.writeString(testJavaDir.resolve("Day" + dayStr + "Test.java"), testsourcecode,
                    StandardOpenOption.CREATE_NEW);
            Files.createFile(mainResourcesDir.resolve("day" + dayStr + ".txt"));
            Files.createFile(testResourcesDir.resolve("day" + dayStr + "test.txt"));
        }
    }

    public static void main(String[] args) throws IOException {
        createAdventOfCodeStubs(2017);
    }

}
