package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Movies implements Serializable {
    private String title;

    public Movies(String title) {
        this.title = title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

}
