package org.example;

import java.util.Scanner;

public class NoteController {

    private NoteManager noteManager;
    private Scanner scanner;

    public NoteController() {
        Storage storage = new Storage();// Storage 객체를 한 번만 생성
        noteManager = new NoteManager(storage);
        scanner = new Scanner(System.in);
        System.out.println("컨트롤러가 생성되었음 ");
    }

    public void ctrrun() {
        while (true) {
            System.out.println("== 명언 앱 ==");
            System.out.print("명령어를 입력하세요 (등록, 삭제, 수정, 목록, 랜덤, 종료): ");
            String command = scanner.nextLine();

            switch (command) {
                case "등록":
                    registerNote();
                    break;
                case "삭제":
                    deleteNote();
                    break;
                case "수정":
                    updateNote();
                    break;
                case "목록":
                    showNoteList();
                    break;
                case "종료":
                    System.out.println("프로그램을 종료합니다.");
                    return;
                default:
                    System.out.println("유효하지 않은 명령어입니다. 사용 가능한 명령어를 확인하세요.");
            }
        }
    }

    private void registerNote() {
        try {
            String newSaying = getStringInput("명언을 입력하세요: ");
            String newAuthor = getStringInput("작가를 입력하세요: ");
            int noteid = noteManager.register(newSaying, newAuthor);
            System.out.println( noteid + "번 노트가 성공적으로 등록되었습니다.");
        } catch (EmptyInputException | SaveException e ){
            ExceptionHandler.handleException(e);
        }
    }

    private void deleteNote() {
        try {
            int noteId = getNoteIdFromUser("삭제할 노트의 ID를 입력하세요: ");
            noteManager.delete(noteId);
            System.out.println( noteId + "번 노트가 성공적으로 삭제되었습니다.");
        } catch (InvalidInputFormatException | NoteNotFoundException | SaveException e) {
            ExceptionHandler.handleException(e);
        }
    }

    private void updateNote() {
        try {
            int noteId = getNoteIdFromUser("수정할 노트의 ID를 입력하세요: ");
            Note note = noteManager.load(noteId);
            String newSaying = getStringInput("기존 명언: "+ note.getSaying() +"\n새로운 명언을 입력하세요: ");
            String newAuthor = getStringInput("기존 작가: "+ note.getAuthor() +"\n새로운 작가를 입력하세요: ");

            note.setSaying(newSaying);
            note.setAuthor(newAuthor);

            int fixedId = noteManager.update(note);
            System.out.println( fixedId + "노트가 성공적으로 수정되었습니다.");

        } catch ( EmptyInputException | InvalidInputFormatException e) { // 입력단계에서 발생하는 에러
            ExceptionHandler.handleException(e);
        } catch (SaveException | JsonParsingException | NoteNotFoundException e) {
            // 파일 저장, 읽기, 접근 단계에서 발생하는 오류
            ExceptionHandler.handleException(e);
        }
    }


    private int getNoteIdFromUser(String message) throws InvalidInputFormatException{
        System.out.print(message);
        String input = scanner.nextLine().trim();
        int noteId;
        try{
            noteId = Integer.parseInt(input);
        } catch (NumberFormatException e){
            throw new InvalidInputFormatException();
        }
        if(noteId < 0) {
            System.out.println("ID는 0 이상입니다. ");;
        }
        return noteId;
    }

    private String getStringInput(String message) throws EmptyInputException{
        System.out.print(message);
        String input = scanner.nextLine().trim();
        if(input.isEmpty()){
            throw new EmptyInputException();
        }
        return input;
    }

    private void showNoteList() {

    }


}
