package org.example;

public class ExceptionHandler {

    public static void handleException(Exception e) {
        System.out.println(e.getMessage());
    }
}

class NoteNotFoundException extends Exception {
    public NoteNotFoundException(int noteId) {
        super("해당 ID의 노트가 존재하지 않습니다. ID:" + noteId);
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

class SaveFileException extends Exception {
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

class ReadFileException extends Exception {
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

class JsonParsingException extends Exception {
    public JsonParsingException(Exception cause) {
        super("제이슨이 존재하지만 파일을 읽어 올 수 없습니다. " + cause);
    }
    public JsonParsingException(String filePath, Throwable cause) {
        super("JSON 파싱 오류: " + filePath + cause);
    }
}

class BuildFileException extends Exception {

    public BuildFileException() {
        super("빌드 파일이 위치할 디렉토리가 없거나 빌드할 파일이 없습니다.");
    }

    public BuildFileException(Throwable cause) {
        super("파일을 빌드 하는 중 오류가 발생했습니다: " + cause);
    }
}
