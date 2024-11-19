package org.example;

import java.util.Scanner;

public class Controller {

    private NoteManager noteManager;
    private Scanner scanner;

    public Controller() {
        noteManager = new NoteManager();
        scanner = new Scanner(System.in);
    }

    public void run() {
        while (true) {
            System.out.print("명령어를 입력하세요 (등록, 삭제, 수정, 목록, 도움말, 종료): ");
            String command = scanner.nextLine();


        }
    }


}
