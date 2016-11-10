package com.drawable;

import java.util.Scanner;

/**
 * Created by TSM on 2016/10/16.
 */
public class SchoolBean {
    private int logoImage;
    private String name;
    private Boolean checked;
    private int telephone;
    Scanner input = new Scanner(System.in);

    public void setLogoImage(int logoImage){
        this.logoImage = logoImage;
    }
    public int getLogoImage(){
        return logoImage;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = input.next();
    }
    public void setTelephone(int telephone){
        this.telephone = input.nextInt();
    }
    public int getTelephone(){
        return telephone;
    }
    public Boolean getChecked() {
        return checked;
    }
    public void setChecked(Boolean checked) {
        this.checked = checked;
    }
}
