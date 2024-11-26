package common.exception;

public class SaveFileException extends RuntimeException {
    public SaveFileException(int id) {
        super("파일을 저장하는 중 오류가 발생했습니다. " + id);
    }

    public SaveFileException(Throwable cause) {
        super("빌드파일을 저장하는 중 오류가 발생했습니다: " + cause);
    }

    public SaveFileException(int id, Throwable cause) {
        super("파일을 저장하는 중 오류가 발생했습니다: " + id + cause);
    }
}
