package org.example;

public class ExceptionHandler {
    public static void handleException(Exception e) {
        System.out.println(e.getMessage());
    }
}

class NoteNotFoundException extends Exception {
    public NoteNotFoundException(int noteId) {
        super("해당 ID의 노트가 존재하지 않습니다." + noteId);
    }
}

class EmptyInputException extends Exception {
    public EmptyInputException() {
        super("입력은 비어 있을 수 없습니다.");
    }
}

class InvalidInputFormatException extends Exception {
    public InvalidInputFormatException() {
        super("숫자 ID를 입력해주세요. "); // 사용자가 입력한 값을 보여줘도 좋을듯
    }
}

class EmptySayingException extends Exception {
    public EmptySayingException() {
        super("작성된 명언이 없습니다. 명언 등록을 진행해주세요."); // 사용자가 입력한 값을 보여줘도 좋을듯
    }
}

class SaveException extends Exception {
    public SaveException(String message, int id) {
        super(message + id);
    }

    public SaveException(String message, Throwable cause) {
        super(message, cause);
    }
}