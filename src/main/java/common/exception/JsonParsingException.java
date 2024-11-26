package common.exception;

public class JsonParsingException extends RuntimeException {
    public JsonParsingException(Exception cause) {
        super("제이슨이 존재하지만 파일을 읽어 올 수 없습니다. " + cause);
    }
    public JsonParsingException(String filePath, Throwable cause) {
        super("JSON 파싱 오류: " + filePath + cause);
    }
}
