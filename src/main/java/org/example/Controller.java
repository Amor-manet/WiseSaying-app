package org.example;

import java.util.Scanner;

public class Controller {

    private NoteManager noteManager;
    private Scanner scanner;

    public Controller() {
        Storage storage = new Storage();            // Storage 객체를 한 번만 생성
        noteManager = new NoteManager(storage);
        scanner = new Scanner(System.in);
        System.out.println("컨트롤러가 생성되었음 ");
    }

    public void run() {
        while (true) {
            System.out.println("== 명언 앱 ==");
            System.out.print("명령어를 입력하세요 (등록, 삭제, 수정, 목록, 도움말, 종료): ");
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
                    noteManager.listNotes();
                    break;
//                case "도움말":
//                    printHelp();
//                    break;
                case "종료":
                    System.out.println("프로그램을 종료합니다.");
                    return;
                default:
                    System.out.println("유효하지 않은 명령어입니다. '도움말'을 입력하여 사용 가능한 명령어를 확인하세요.");
            }
        }
    }

    private void registerNote() {
        System.out.print("명언을 입력하세요: ");
        String saying = scanner.nextLine();
        System.out.print("작가를 입력하세요: ");
        String author = scanner.nextLine();
        noteManager.register(saying, author);
    }

    private void deleteNote() {
        try {
            System.out.print("삭제할 노트의 ID를 입력하세요: ");
            int noteId = Integer.parseInt(scanner.nextLine());
            noteManager.delete(noteId);
        } catch (NumberFormatException e) {
            System.out.println("ID는 숫자여야 합니다.");
        }
    }

    private void updateNote() {
        try {
            System.out.print("수정할 노트의 ID를 입력하세요: ");
            int noteId = Integer.parseInt(scanner.nextLine());
            Notes note = noteManager.getNoteById(noteId);
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

//    private void printHelp() {
//        System.out.println("명령어 목록: 등록, 삭제, 수정, 목록, 도움말, 종료");
//    }


}
