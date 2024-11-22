package org.example;

import java.util.List;
import java.util.Scanner;

public class NoteController {

    private NoteManager noteManager;
    private Scanner scanner;

    public NoteController() {
        try {
            Storage storage = new Storage();// Storage 객체를 한 번만 생성
            noteManager = new NoteManager(storage);
            scanner = new Scanner(System.in);
            //System.out.println("컨트롤러가 생성되었음 ");

        } catch (ReadFileException e) {
            ExceptionHandler.handleException(e);
        }
    }

    public void registerNote() {
        try {
            String newSaying = getStringInput("명언을 입력하세요: ");
            String newAuthor = getStringInput("작가를 입력하세요: ");
            int noteId = noteManager.register(newSaying, newAuthor);
            System.out.println( noteId + "번 노트가 성공적으로 등록되었습니다.");
        } catch (EmptyInputException | SaveFileException | ReadFileException e ){
            ExceptionHandler.handleException(e);
        }
    }

    public void deleteNote() {
        try {
            int noteId = getNoteIdFromUser("삭제할 노트의 ID를 입력하세요: ");
            noteManager.delete(noteId);
            System.out.println(noteId + "번 노트가 성공적으로 삭제되었습니다.");
        } catch (InvalidInputFormatException | NoteNotFoundException | SaveFileException e) {
            ExceptionHandler.handleException(e);
        }
    }

    public void updateNote() {
        try {
            int noteId = getNoteIdFromUser("수정할 노트의 ID를 입력하세요: ");
            Note note = noteManager.load(noteId); // 기존에 있던 노트객체를 불러옴
            String newSaying = getStringInput("기존 명언: "+ note.getSaying() +"\n새로운 명언을 입력하세요: ");
            String newAuthor = getStringInput("기존 작가: "+ note.getAuthor() +"\n새로운 작가를 입력하세요: ");

            int fixedId = noteManager.update(noteId,newSaying,newAuthor);
            System.out.println( fixedId + "번 노트가 성공적으로 수정되었습니다.");

        } catch ( EmptyInputException | InvalidInputFormatException e) { // 입력단계에서 발생하는 에러
            ExceptionHandler.handleException(e);
        } catch (SaveFileException | JsonParsingException | NoteNotFoundException e) {
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
            System.out.println("ID는 0 이상입니다. ");
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

    // 코드 체크 필요
    public void buildNotes() {
        try {
            noteManager.build();
            System.out.println("모든 명언이 성공적으로 빌드되었습니다.");
        } catch (BuildFileException | SaveFileException e) {
            ExceptionHandler.handleException(e);
        }
    }

    public void showNoteList() {
        try {
            noteManager.build();
            List<Note> notes = noteManager.loadNotes();
            // 목록이 비어 있는 경우 처리
            if (notes.isEmpty()) {
                System.out.println("현재 저장된 명언이 없습니다.");
                return;
            }
            // 목록 출력
            System.out.println(" 번호 /     명언      /   작가  ");
            for (Note note : notes) {
                System.out.println(" " + note.getId() + " / " + note.getSaying() + " / " + note.getAuthor());
            }
        } catch (ReadFileException | BuildFileException | SaveFileException e) {
            ExceptionHandler.handleException(e);
        }
    }

}
