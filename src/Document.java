public class Document {
    String docNumber;
    String email;
    String phoneNumber;

    public Document() {
    }

    public Document(String docNumber, String email, String phoneNumber) {
        this.docNumber = docNumber;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "{" +
                "docNumber='" + docNumber + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
