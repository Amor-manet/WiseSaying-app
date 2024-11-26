package common.exception;

public class BuildFileException extends RuntimeException {

    public BuildFileException() {
        super("빌드 파일이 위치할 디렉토리가 없거나 빌드할 파일이 없습니다.");
    }

    public BuildFileException(Throwable cause) {
        super("파일을 빌드 하는 중 오류가 발생했습니다: " + cause);
    }
}
