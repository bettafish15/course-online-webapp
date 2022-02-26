/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.swp490_g25_sse.model;

import org.springframework.stereotype.Component;

/**
 *
 * @author ADMIN
 */
@Component
public class LectureImage {

    private int id;
    private String imgUrl;

    public LectureImage() {
    }

    public LectureImage(int id, String imgUrl) {
        this.id = id;
        this.imgUrl = imgUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

}
