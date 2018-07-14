package com.sabihashaik.f4us2.ic2;


public class blog {

    private String title;
    private String desc;
    private String Image;
    private String Tags;
    private String Location;


    public blog(){

    }


    public blog(String title, String desc, String image,String Tags,String Location) {
       this.title = title;
        this.desc = desc;
        this.Tags=Tags;
        this.Location=Location;
        Image = image;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public void setTags(String tags) {
        Tags = tags;
    }
    public String getTags() {
        return Tags;
    }


}
