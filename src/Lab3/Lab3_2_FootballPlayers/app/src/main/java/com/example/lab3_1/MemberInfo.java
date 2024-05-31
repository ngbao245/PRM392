package com.example.lab3_1;

public class MemberInfo {
    private String Name;
    private String Description;

    private String URLImageMember;
    private String URLImageFlat;

    private int ImageMember = 0;
    private int ImageFlat = 0;

    public MemberInfo(String name, String description, int imageMember, int imageFlat) {
        Name = name;
        Description = description;
        ImageMember = imageMember;
        ImageFlat = imageFlat;
    }

    public MemberInfo(String name, String description) {
        Name = name;
        Description = description;
    }

    public MemberInfo(String name, String description, String URLImageMember, String URLImageFlat) {
        Name = name;
        Description = description;
        this.URLImageMember = URLImageMember;
        this.URLImageFlat = URLImageFlat;
    }

    public String getName() {
        return Name;
    }

    public String getDescription() {
        return Description;
    }

    public int getImageMember() {
        return ImageMember;
    }

    public int getImageFlat() {
        return ImageFlat;
    }

    public String getURLImageMember() {
        return URLImageMember;
    }

    public String getURLImageFlat() {
        return URLImageFlat;
    }

    public void setURLImageMember(String URLImageMember) {
        this.URLImageMember = URLImageMember;
    }

    public void setURLImageFlat(String URLImageFlat) {
        this.URLImageFlat = URLImageFlat;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setImageMember(int imageMember) {
        ImageMember = imageMember;
    }

    public void setImageFlat(int imageFlat) {
        ImageFlat = imageFlat;
    }
}
