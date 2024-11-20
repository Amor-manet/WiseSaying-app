package org.example;

public class Note {
    private int id; // 메모 아이디
    private String saying; // 명언
    private String author; // 작가

    public Note(int id, String quote, String author) {
        System.out.println("노트가 생성되었음 ");
        this.id = id;
        this.saying = quote;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public String getSaying() {
        return saying;
    }

    public void setSaying(String saying) {
        this.saying = saying;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

}
