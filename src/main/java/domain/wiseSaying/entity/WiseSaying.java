package domain.wiseSaying.entity;

public class WiseSaying {
    private int id; // 아이디
    private String saying; // 명언
    private String author; // 작가

    public WiseSaying() {
    // 잭슨 라이브러리에서 역직렬화를 구현하려면 빈 생성자가 필요함
    }

    public WiseSaying(int id, String saying, String author) {

        this.id = id;
        this.saying = saying;
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
