class MovieBookingProfile {

    private String name;
    private boolean confirmed;
    private String otp;

    public MovieBookingProfile() {
    }

    public MovieBookingProfile(String name) {
        this();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public void setOtp(String otp) {
        if (otp == null || !otp.matches("\\d{4,6}")) {
            throw new IllegalArgumentException("otp must be a 4-6 digit numeric string");
        }
        this.otp = otp;
    }

    public String toString() {

        return "MovieBookingProfile{name='" + name + "', confirmed=" + confirmed + "}";
    }
}

public class Week5Question4 {
    public static void main(String[] args) {
        System.out.println(new MovieBookingProfile("Rahul Dev").getName());

        MovieBookingProfile p = new MovieBookingProfile("Rahul Dev");
        p.setConfirmed(true);
        System.out.println(p.isConfirmed());

        p.setOtp("4471");
        System.out.println(p);
    }
}
