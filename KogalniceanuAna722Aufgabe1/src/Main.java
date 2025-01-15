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


        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEnter a letter to search patients by:");
        String searchName = scanner.nextLine().trim();
        fallAkten.stream()
                .map(FallAkten::getPatient)
                .filter(Patient -> Patient.startsWith(searchName))
                .distinct()
                .forEach(System.out::println);

        System.out.println("Sicknesses from patients with Fieber: ");
        fallAkten.stream()
                .filter(log -> Objects.equals(log.getSymptom(), "Fieber"))
                .sorted(Comparator.comparing(FallAkten::getDate))
                .map(FallAkten::getDiagnosis)
                .forEach(System.out::println);
        System.out.println();

    }
}