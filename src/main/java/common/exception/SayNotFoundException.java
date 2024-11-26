package common.exception;

public class SayNotFoundException extends RuntimeException {
    public SayNotFoundException(int noteId) {
        super("해당 ID의 명언이 존재하지 않습니다. ID:" + noteId);
    }
}
