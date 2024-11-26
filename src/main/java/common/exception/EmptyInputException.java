package common.exception;

public class EmptyInputException extends RuntimeException {
    public EmptyInputException() {
        super("입력은 비어 있을 수 없습니다.");
    }
}
