package com.example.shammobile.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Applicants {
    private List<Applicants> data;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("studentstatus")
    @Expose
    private String studentstatus;
    @SerializedName("lrnstat")
    @Expose
    private String lrnstat;
    @SerializedName("gradetoenroll")
    @Expose
    private String gradetoenroll;
    @SerializedName("presentgrade")
    @Expose
    private String presentgrade;
    @SerializedName("section")
    @Expose
    private String section;
    @SerializedName("yeartofinish")
    @Expose
    private String yeartofinish;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("lastschoolattended")
    @Expose
    private String lastschoolattended;
    @SerializedName("lastschooladdress")
    @Expose
    private String lastschooladdress;
    @SerializedName("schoolid")
    @Expose
    private String schoolid;
    @SerializedName("schooltype")
    @Expose
    private String schooltype;
    @SerializedName("schooltoenroll")
    @Expose
    private String schooltoenroll;
    @SerializedName("schooladdress")
    @Expose
    private String schooladdress;
    @SerializedName("semester")
    @Expose
    private String semester;
    @SerializedName("track")
    @Expose
    private String track;
    @SerializedName("firstchoice")
    @Expose
    private String firstchoice;
    @SerializedName("secondchoice")
    @Expose
    private String secondchoice;
    @SerializedName("thirdchoice")
    @Expose
    private String thirdchoice;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("reportcard")
    @Expose
    private String reportcard;
    @SerializedName("birthcertificate")
    @Expose
    private String birthcertificate;
    @SerializedName("englishgrade")
    @Expose
    private String englishgrade;
    @SerializedName("mathgrade")
    @Expose
    private String mathgrade;
    @SerializedName("sciencegrade")
    @Expose
    private String sciencegrade;
    @SerializedName("filipinograde")
    @Expose
    private String filipinograde;
    @SerializedName("lrn")
    @Expose
    private String lrn;
    @SerializedName("psanumber")
    @Expose
    private String psanumber;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("fname")
    @Expose
    private String fname;
    @SerializedName("mname")
    @Expose
    private String mname;
    @SerializedName("lname")
    @Expose
    private String lname;
    @SerializedName("extname")
    @Expose
    private String extname;
    @SerializedName("age")
    @Expose
    private String age;
    @SerializedName("birthdate")
    @Expose
    private String birthdate;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("contact")
    @Expose
    private String contact;
    @SerializedName("mothertongue")
    @Expose
    private String mothertongue;
    @SerializedName("religion")
    @Expose
    private String religion;
    @SerializedName("indipeople")
    @Expose
    private String indipeople;
    @SerializedName("specialneeds")
    @Expose
    private String specialneeds;
    @SerializedName("assistivedevices")
    @Expose
    private String assistivedevices;
    @SerializedName("mothername")
    @Expose
    private String mothername;
    @SerializedName("mothereducation")
    @Expose
    private String mothereducation;
    @SerializedName("motheremployment")
    @Expose
    private String motheremployment;
    @SerializedName("motherworkstat")
    @Expose
    private String motherworkstat;
    @SerializedName("mothercontact")
    @Expose
    private String mothercontact;
    @SerializedName("fathername")
    @Expose
    private String fathername;
    @SerializedName("fathereducation")
    @Expose
    private String fathereducation;
    @SerializedName("fatheremployment")
    @Expose
    private String fatheremployment;
    @SerializedName("fatherworkstat")
    @Expose
    private String fatherworkstat;
    @SerializedName("fathercontact")
    @Expose
    private String fathercontact;
    @SerializedName("guardianname")
    @Expose
    private String guardianname;
    @SerializedName("guardianeducation")
    @Expose
    private String guardianeducation;
    @SerializedName("guardianemployment")
    @Expose
    private String guardianemployment;
    @SerializedName("guardianworkstat")
    @Expose
    private String guardianworkstat;
    @SerializedName("guardiancontact")
    @Expose
    private String guardiancontact;
    @SerializedName("housestreet")
    @Expose
    private String housestreet;
    @SerializedName("barangay")
    @Expose
    private String barangay;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("province")
    @Expose
    private String province;
    @SerializedName("region")
    @Expose
    private String region;

    public Applicants(List<Applicants> data, String id, String studentstatus, String lrnstat,
                      String gradetoenroll, String presentgrade, String section,
                      String yeartofinish, String image, String lastschoolattended,
                      String lastschooladdress, String schoolid, String schooltype,
                      String schooltoenroll, String schooladdress, String semester, String track,
                      String firstchoice, String secondchoice, String thirdchoice, String status,
                      String reportcard, String birthcertificate, String englishgrade,
                      String mathgrade, String sciencegrade, String filipinograde, String lrn,
                      String psanumber, String email, String fname, String mname, String lname,
                      String extname, String age, String birthdate, String gender, String contact,
                      String mothertongue, String religion, String indipeople, String specialneeds,
                      String assistivedevices, String mothername, String mothereducation,
                      String motheremployment, String motherworkstat, String mothercontact,
                      String fathername, String fathereducation, String fatheremployment,
                      String fatherworkstat, String fathercontact, String guardianname,
                      String guardianeducation, String guardianemployment, String guardianworkstat,
                      String guardiancontact, String housestreet, String barangay, String city,
                      String province, String region) {
        this.data = data;
        this.id = id;
        this.studentstatus = studentstatus;
        this.lrnstat = lrnstat;
        this.gradetoenroll = gradetoenroll;
        this.presentgrade = presentgrade;
        this.section = section;
        this.yeartofinish = yeartofinish;
        this.image = image;
        this.lastschoolattended = lastschoolattended;
        this.lastschooladdress = lastschooladdress;
        this.schoolid = schoolid;
        this.schooltype = schooltype;
        this.schooltoenroll = schooltoenroll;
        this.schooladdress = schooladdress;
        this.semester = semester;
        this.track = track;
        this.firstchoice = firstchoice;
        this.secondchoice = secondchoice;
        this.thirdchoice = thirdchoice;
        this.status = status;
        this.reportcard = reportcard;
        this.birthcertificate = birthcertificate;
        this.englishgrade = englishgrade;
        this.mathgrade = mathgrade;
        this.sciencegrade = sciencegrade;
        this.filipinograde = filipinograde;
        this.lrn = lrn;
        this.psanumber = psanumber;
        this.email = email;
        this.fname = fname;
        this.mname = mname;
        this.lname = lname;
        this.extname = extname;
        this.age = age;
        this.birthdate = birthdate;
        this.gender = gender;
        this.contact = contact;
        this.mothertongue = mothertongue;
        this.religion = religion;
        this.indipeople = indipeople;
        this.specialneeds = specialneeds;
        this.assistivedevices = assistivedevices;
        this.mothername = mothername;
        this.mothereducation = mothereducation;
        this.motheremployment = motheremployment;
        this.motherworkstat = motherworkstat;
        this.mothercontact = mothercontact;
        this.fathername = fathername;
        this.fathereducation = fathereducation;
        this.fatheremployment = fatheremployment;
        this.fatherworkstat = fatherworkstat;
        this.fathercontact = fathercontact;
        this.guardianname = guardianname;
        this.guardianeducation = guardianeducation;
        this.guardianemployment = guardianemployment;
        this.guardianworkstat = guardianworkstat;
        this.guardiancontact = guardiancontact;
        this.housestreet = housestreet;
        this.barangay = barangay;
        this.city = city;
        this.province = province;
        this.region = region;
    }

    public Applicants() {
    }

    public List<Applicants> getData() {
        return data;
    }

    public void setData(List<Applicants> data) {
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudentstatus() {
        return studentstatus;
    }

    public void setStudentstatus(String studentstatus) {
        this.studentstatus = studentstatus;
    }

    public String getLrnstat() {
        return lrnstat;
    }

    public void setLrnstat(String lrnstat) {
        this.lrnstat = lrnstat;
    }

    public String getGradetoenroll() {
        return gradetoenroll;
    }

    public void setGradetoenroll(String gradetoenroll) {
        this.gradetoenroll = gradetoenroll;
    }

    public String getPresentgrade() {
        return presentgrade;
    }

    public void setPresentgrade(String presentgrade) {
        this.presentgrade = presentgrade;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getYeartofinish() {
        return yeartofinish;
    }

    public void setYeartofinish(String yeartofinish) {
        this.yeartofinish = yeartofinish;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLastschoolattended() {
        return lastschoolattended;
    }

    public void setLastschoolattended(String lastschoolattended) {
        this.lastschoolattended = lastschoolattended;
    }

    public String getLastschooladdress() {
        return lastschooladdress;
    }

    public void setLastschooladdress(String lastschooladdress) {
        this.lastschooladdress = lastschooladdress;
    }

    public String getSchoolid() {
        return schoolid;
    }

    public void setSchoolid(String schoolid) {
        this.schoolid = schoolid;
    }

    public String getSchooltype() {
        return schooltype;
    }

    public void setSchooltype(String schooltype) {
        this.schooltype = schooltype;
    }

    public String getSchooltoenroll() {
        return schooltoenroll;
    }

    public void setSchooltoenroll(String schooltoenroll) {
        this.schooltoenroll = schooltoenroll;
    }

    public String getSchooladdress() {
        return schooladdress;
    }

    public void setSchooladdress(String schooladdress) {
        this.schooladdress = schooladdress;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getTrack() {
        return track;
    }

    public void setTrack(String track) {
        this.track = track;
    }

    public String getFirstchoice() {
        return firstchoice;
    }

    public void setFirstchoice(String firstchoice) {
        this.firstchoice = firstchoice;
    }

    public String getSecondchoice() {
        return secondchoice;
    }

    public void setSecondchoice(String secondchoice) {
        this.secondchoice = secondchoice;
    }

    public String getThirdchoice() {
        return thirdchoice;
    }

    public void setThirdchoice(String thirdchoice) {
        this.thirdchoice = thirdchoice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReportcard() {
        return reportcard;
    }

    public void setReportcard(String reportcard) {
        this.reportcard = reportcard;
    }

    public String getBirthcertificate() {
        return birthcertificate;
    }

    public void setBirthcertificate(String birthcertificate) {
        this.birthcertificate = birthcertificate;
    }

    public String getEnglishgrade() {
        return englishgrade;
    }

    public void setEnglishgrade(String englishgrade) {
        this.englishgrade = englishgrade;
    }

    public String getMathgrade() {
        return mathgrade;
    }

    public void setMathgrade(String mathgrade) {
        this.mathgrade = mathgrade;
    }

    public String getSciencegrade() {
        return sciencegrade;
    }

    public void setSciencegrade(String sciencegrade) {
        this.sciencegrade = sciencegrade;
    }

    public String getFilipinograde() {
        return filipinograde;
    }

    public void setFilipinograde(String filipinograde) {
        this.filipinograde = filipinograde;
    }

    public String getLrn() {
        return lrn;
    }

    public void setLrn(String lrn) {
        this.lrn = lrn;
    }

    public String getPsanumber() {
        return psanumber;
    }

    public void setPsanumber(String psanumber) {
        this.psanumber = psanumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getExtname() {
        return extname;
    }

    public void setExtname(String extname) {
        this.extname = extname;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getMothertongue() {
        return mothertongue;
    }

    public void setMothertongue(String mothertongue) {
        this.mothertongue = mothertongue;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getIndipeople() {
        return indipeople;
    }

    public void setIndipeople(String indipeople) {
        this.indipeople = indipeople;
    }

    public String getSpecialneeds() {
        return specialneeds;
    }

    public void setSpecialneeds(String specialneeds) {
        this.specialneeds = specialneeds;
    }

    public String getAssistivedevices() {
        return assistivedevices;
    }

    public void setAssistivedevices(String assistivedevices) {
        this.assistivedevices = assistivedevices;
    }

    public String getMothername() {
        return mothername;
    }

    public void setMothername(String mothername) {
        this.mothername = mothername;
    }

    public String getMothereducation() {
        return mothereducation;
    }

    public void setMothereducation(String mothereducation) {
        this.mothereducation = mothereducation;
    }

    public String getMotheremployment() {
        return motheremployment;
    }

    public void setMotheremployment(String motheremployment) {
        this.motheremployment = motheremployment;
    }

    public String getMotherworkstat() {
        return motherworkstat;
    }

    public void setMotherworkstat(String motherworkstat) {
        this.motherworkstat = motherworkstat;
    }

    public String getMothercontact() {
        return mothercontact;
    }

    public void setMothercontact(String mothercontact) {
        this.mothercontact = mothercontact;
    }

    public String getFathername() {
        return fathername;
    }

    public void setFathername(String fathername) {
        this.fathername = fathername;
    }

    public String getFathereducation() {
        return fathereducation;
    }

    public void setFathereducation(String fathereducation) {
        this.fathereducation = fathereducation;
    }

    public String getFatheremployment() {
        return fatheremployment;
    }

    public void setFatheremployment(String fatheremployment) {
        this.fatheremployment = fatheremployment;
    }

    public String getFatherworkstat() {
        return fatherworkstat;
    }

    public void setFatherworkstat(String fatherworkstat) {
        this.fatherworkstat = fatherworkstat;
    }

    public String getFathercontact() {
        return fathercontact;
    }

    public void setFathercontact(String fathercontact) {
        this.fathercontact = fathercontact;
    }

    public String getGuardianname() {
        return guardianname;
    }

    public void setGuardianname(String guardianname) {
        this.guardianname = guardianname;
    }

    public String getGuardianeducation() {
        return guardianeducation;
    }

    public void setGuardianeducation(String guardianeducation) {
        this.guardianeducation = guardianeducation;
    }

    public String getGuardianemployment() {
        return guardianemployment;
    }

    public void setGuardianemployment(String guardianemployment) {
        this.guardianemployment = guardianemployment;
    }

    public String getGuardianworkstat() {
        return guardianworkstat;
    }

    public void setGuardianworkstat(String guardianworkstat) {
        this.guardianworkstat = guardianworkstat;
    }

    public String getGuardiancontact() {
        return guardiancontact;
    }

    public void setGuardiancontact(String guardiancontact) {
        this.guardiancontact = guardiancontact;
    }

    public String getHousestreet() {
        return housestreet;
    }

    public void setHousestreet(String housestreet) {
        this.housestreet = housestreet;
    }

    public String getBarangay() {
        return barangay;
    }

    public void setBarangay(String barangay) {
        this.barangay = barangay;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
