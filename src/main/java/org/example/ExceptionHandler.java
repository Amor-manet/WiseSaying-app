package org.example;

public class ExceptionHandler {
    public static void handleException(Exception e) {
        System.out.println(e.getMessage());
    }
}

class NoteNotFoundException extends Exception {
    private int noteId;

    public NoteNotFoundException(int noteId) {
        super("해당 ID의 노트가 존재하지 않습니다." + noteId);
        this.noteId = noteId;
    }
}

class InvalidNoteIdException extends Exception {
    private int invalidId;

    public InvalidNoteIdException(int invalidId) {
        super("유효하지 않은 노트 ID입니다." + invalidId);
        this.invalidId = invalidId;
    }
}

class EmptyInputException extends Exception {
    public EmptyInputException() {
        super("입력은 비어 있을 수 없습니다.");
    }
}

class InvalidInputFormatException extends Exception {
    public InvalidInputFormatException() {
        super("ID는 숫자여야 합니다."); // 사용자가 입력한 값을 보여줘도 좋을듯
    }
}

class EmptyNotesException extends Exception {
    public EmptyNotesException() {
        super("작성된 명언이 없습니다. 명언 등록을 진행해주세요."); // 사용자가 입력한 값을 보여줘도 좋을듯
    }
}