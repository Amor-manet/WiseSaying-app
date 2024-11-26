package common.exception;

public class ReadFileException extends RuntimeException {
    public ReadFileException(int id) {
        super("파일을 읽는 중 오류가 발생했습니다. " + id);
    }
    public ReadFileException(Throwable cause) {
        super("파일을 읽는 중 오류가 발생했습니다: " + cause);
    }
    public ReadFileException(String message) {
        super(message);
    }
}
