package com.example.onlinedoctorappointment;

class DoctorData {
    private String dname,dphone,hosname,hnumber,state,dusername,dpassword,demail,latlong,gender,
    dcategory,opfee,dtimings,dexperience,imagepath;


    public DoctorData() {
    }

    public DoctorData(String dname, String dphone, String hosname, String hnumber, String state,
                      String dusername, String dpassword, String demail, String latlong, String gender,
                      String dcategory, String opfee, String dtimings, String dexperience, String imagepath) {
        this.dname = dname;
        this.dphone = dphone;
        this.hosname = hosname;
        this.hnumber = hnumber;
        this.state = state;
        this.dusername = dusername;
        this.dpassword = dpassword;
        this.demail = demail;
        this.latlong = latlong;
        this.gender = gender;
        this.dcategory = dcategory;
        this.opfee = opfee;
        this.dtimings = dtimings;
        this.dexperience = dexperience;
        this.imagepath = imagepath;
    }

    public String getDname() {
        return dname;
    }

    public String getDphone() {
        return dphone;
    }

    public String getHosname() {
        return hosname;
    }

    public String getHnumber() {
        return hnumber;
    }

    public String getState() {
        return state;
    }

    public String getDusername() {
        return dusername;
    }

    public String getDpassword() {
        return dpassword;
    }

    public String getDemail() {
        return demail;
    }

    public String getLatlong() {
        return latlong;
    }

    public String getGender() {
        return gender;
    }

    public String getDcategory() {
        return dcategory;
    }

    public String getOpfee() {
        return opfee;
    }

    public String getDtimings() {
        return dtimings;
    }

    public String getDexperience() {
        return dexperience;
    }

    public String getImagepath() {
        return imagepath;
    }
}
