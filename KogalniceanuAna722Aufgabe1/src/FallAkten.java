public class FallAkten {
    int id;
    String patient;
    String symptom;
    String diagnosis;
    String date;
    String hospital;

    public FallAkten(int id, String patient, String symptom, String diagnosis, String date, String hospital) {
        this.id = id;
        this.patient = patient;
        this.symptom = symptom;
        this.diagnosis = diagnosis;
        this.date = date;
        this.hospital = hospital;
    }

    @Override
    public String toString() {
        return "FallAkten{" +
                "id=" + id +
                ", patient='" + patient + '\'' +
                ", symptom='" + symptom + '\'' +
                ", diagnosis='" + diagnosis + '\'' +
                ", date='" + date + '\'' +
                ", hospital='" + hospital + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }
}
