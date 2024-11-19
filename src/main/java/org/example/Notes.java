package org.example;

public class Notes{
    private int id;
    private String saying;
    private String author;

    public Notes(int id, String quote, String author) {
        this.id = id;
        this.saying = quote;
        this.author = author;
    }

    public int getId(){
        return id;
    }

    public String getSaying(){
        return saying;
    }

    public void setSaying(){
        this.saying = saying;
    }

    public String getAuthor(){
        return author;
    }

    public void setAuthor(){
        this.author = author;
    }

}
