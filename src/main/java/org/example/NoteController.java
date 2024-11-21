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
//                case "도움말":
//                    printHelp();
//                    break;
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
            int id = noteManager.register(newSaying, newAuthor);
            System.out.println( id + "번 노트가 성공적으로 등록되었습니다.");
        } catch (Exception e){
            ExceptionHandler.handleException(e);
        }
    }

    private void deleteNote() {
        try {
            int noteId = getNoteIdFromUser("삭제할 노트의 ID를 입력하세요: ");
            noteManager.delete(noteId);
            System.out.println( noteId + "번 노트가 성공적으로 삭제되었습니다.");
        } catch (InvalidInputFormatException e) {
            ExceptionHandler.handleException(e);
        }
    }

    private void updateNote() {
        try {
            System.out.print("수정할 노트의 ID를 입력하세요: ");
            int noteId = Integer.parseInt(scanner.nextLine());
            Note note = noteManager.getNoteById(noteId);
            if (note != null) {
                System.out.println("현재 명언: " + note.getSaying());
                System.out.print("새로운 명언을 입력하세요: ");
                String newSaying = scanner.nextLine();
                System.out.println("현재 작가: " + note.getAuthor());
                System.out.print("새로운 작가를 입력하세요: ");
                String newAuthor = scanner.nextLine();
                noteManager.update(noteId, newSaying, newAuthor);
            } else {
                System.out.println("해당 ID의 노트가 존재하지 않습니다.");
            }
        } catch (NumberFormatException e) {
            System.out.println("ID는 숫자여야 합니다.");
        }
    }

    private void showNoteList() {
        try {
            System.out.println("번호 /      명언      /  작가");
            noteManager.listNotes();
        } catch (Exception e) {
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



//    private void printHelp() {
//        System.out.println("명령어 목록: 등록, 삭제, 수정, 목록, 도움말, 종료");
//    }
//

}
