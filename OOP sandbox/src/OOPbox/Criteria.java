package OOPbox;

/**
 * Created by alice on 11/8/14.
 */
public class Criteria {

    private String major;
    private int hours;
    private double gpa;

    public Criteria(String m, int h, double g) {
        setMajor(m);
        setHours(h);
        setGpa(g);
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }
}

