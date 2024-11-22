package org.example;

import java.util.Scanner;

public class App {

    Scanner scanner = new Scanner(System.in);

    public void run(){

        NoteController noteController = new NoteController();
        //noteController.noteConAct();

        while (true) {
            System.out.println("== 명언 앱 ==");
            System.out.print("명령어를 입력하세요 (등록, 삭제, 수정, 목록, 종료): ");

            String command = scanner.nextLine();

            switch (command) {
                case "등록":
                    noteController.registerNote();
                    break;
                case "삭제":
                    noteController.deleteNote();
                    break;
                case "수정":
                    noteController.updateNote();
                    break;
                case "목록":
                    noteController.buildNotes();
                    noteController.showNoteList();
                    break;
                case "빌드":
                    noteController.buildNotes();
                    break;
                case "종료":
                    System.out.println("프로그램을 종료합니다.");
                    return;
                default:
                    System.out.println("유효하지 않은 명령어입니다. 사용 가능한 명령어를 확인하세요.");
            }
        }






    }

    public void noteConAct() {

    }






}



