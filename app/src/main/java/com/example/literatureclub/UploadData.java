package com.example.literatureclub;

public class UploadData {

    private String Name,Info,imgURL,docURL,Limit,Actual;

    public void setActual(String actual) {
        Actual = actual;
    }

    public String getActual() {
        return Actual;
    }

    public void setLimit(String limit) {
        Limit = limit;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setInfo(String info) {
        Info = info;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public void setDocURL(String docURL) {
        this.docURL = docURL;
    }

    public String getLimit() { return Limit; }

    public String getName() {
        return Name;
    }

    public String getInfo() {
        return Info;
    }

    public String getImgURL() {
        return imgURL;
    }

    public String getDocURL() {
        return docURL;
    }

}
