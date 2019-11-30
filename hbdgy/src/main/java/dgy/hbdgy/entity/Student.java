package dgy.hbdgy.entity;


public class Student {
    private String sno;
    private String sname;
    private String sage;
    private String ssex;
    private String sscore;


    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSage() {
        return sage;
    }

    public void setSage(String sage) {
        this.sage = sage;
    }

    public String getSsex() {
        return ssex;
    }

    public void setSsex(String ssex) {
        this.ssex = ssex;
    }

    public String getSscore() {
        return sscore;
    }

    public void setSscore(String sscore) {
        this.sscore = sscore;
    }

    public Student(){
        this.sscore = "0";
    }
}
