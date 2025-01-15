import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        FallAktenReaderJSON fallAktenReaderJSON = new FallAktenReaderJSON("C:\\Users\\zaxx4\\Desktop\\Anul 2\\Semestrul 1\\MAP\\KogalniceanuAna722Aufgabe1\\KogalniceanuAna722Aufgabe1\\src\\fallakten.json", "C:\\Users\\zaxx4\\Desktop\\Anul 2\\Semestrul 1\\MAP\\KogalniceanuAna722Aufgabe1\\KogalniceanuAna722Aufgabe1\\src\\fallanzahl.txt");
        List<FallAkten> fallAkten = fallAktenReaderJSON.parse("C:\\Users\\zaxx4\\Desktop\\Anul 2\\Semestrul 1\\MAP\\KogalniceanuAna722Aufgabe1\\KogalniceanuAna722Aufgabe1\\src\\fallakten.json");
        fallAkten.forEach(System.out::println);


        fallAkten.stream()
                .filter(log -> Objects.equals(log.getSymptom(), "Fieber"))
                .sorted(Comparator.comparing(FallAkten::getDate))
                .map(FallAkten::getDiagnosis)
                .forEach(System.out::println);
        System.out.println();

        Map<String, Integer> hospitals = new HashMap<>();
        for (FallAkten log : fallAkten) {

            hospitals.merge(log.getHospital(), points, Integer::sum);
        }

        List<String> results = hospitals.entrySet().stream()
                .sorted((e1, e2) -> Integer.compare(e2.getValue(), e1.getValue()))
                .map(entry -> entry.getKey() + "&" + entry.getValue())
                .collect(Collectors.toList());

        Files.write(Path.of(outputFilePath), results);
    }
}