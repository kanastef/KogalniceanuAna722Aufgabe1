import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FallAktenReaderJSON {
    String inputFile;
    String outputFile;

    public FallAktenReaderJSON(String inputFile, String outputFile) {
        this.inputFile = inputFile;
        this.outputFile = outputFile;
    }

    public String getInputFile() {
        return inputFile;
    }

    public void setInputFile(String inputFile) {
        this.inputFile = inputFile;
    }

    public String getOutputFile() {
        return outputFile;
    }

    public void setOutputFile(String outputFile) {
        this.outputFile = outputFile;
    }

    public List<FallAkten> parse(String filePath) throws IOException {
        Path path = Path.of(filePath);

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String firstLine = reader.readLine();
            if (firstLine == null || !firstLine.contains("[")) {
                throw new IOException("Invalid JSON file format: Missing opening '['");
            }

            List<FallAkten> avengerLogs = new ArrayList<>();
            String currentLine = reader.readLine();

            while (currentLine != null && !currentLine.contains("]")) {
                if (currentLine.trim().startsWith("{")) {
                    FallAkten avengerLog = parseAvengerLog(reader, currentLine);
                    avengerLogs.add(avengerLog);
                }
                currentLine = reader.readLine();
            }

            return avengerLogs;
        }
    }

    private FallAkten parseAvengerLog(BufferedReader reader, String firstLine) throws IOException {
        String id = null;
        String patient = null;
        String symptom = null;
        String diagnosis = null;
        String date = null;
        String hospital = null;

        String line = reader.readLine();
        while (line != null && !line.contains("}")) {
            String fieldName = extractFieldName(line);
            String value = extractFieldValue(line);

            switch (fieldName) {
                case "Id" -> id = value;
                case "Patient" -> patient = value;
                case "Symptom" -> symptom = value;
                case "Diagnose" -> diagnosis = value;
                case "Datum" -> date = value;
                case "Krankenhaus" -> hospital = value;
                default -> throw new IOException("Unexpected field name: " + fieldName);
            }

            line = reader.readLine();
        }

        if (line == null || !line.contains("}")) {
            throw new IOException("Invalid JSON object format: Missing closing '}'");
        }

        return new FallAkten(Integer.parseInt(id), patient, symptom, diagnosis, date, hospital);
    }

    private String extractFieldName(String line) {
        String[] parts = line.split(":");
        return parts[0].replace("\"", "").trim();
    }

    /**
     * Extracts the field value from a JSON line.
     *
     * @param line the line to parse
     * @return the field value
     */
    private String extractFieldValue(String line) {
        String[] parts = line.split(":");
        String value = parts[1].trim();

        return value.replace("\"", "").replace(",", "").trim();
    }
}
