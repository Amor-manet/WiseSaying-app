package domain.wiseSaying.controller;

import common.exception.EmptyInputException;
import domain.wiseSaying.entity.WiseSaying;
import domain.wiseSaying.repository.WiseSayingRepository;
import domain.wiseSaying.service.WiseSayingService;

import java.util.List;
import java.util.Scanner;

public class WiseSayingController {

    private WiseSayingService wiseService;
    private Scanner scanner;

    public WiseSayingController() {

            WiseSayingRepository storage = new WiseSayingRepository();// Storage 객체를 한 번만 생성
            wiseService = new WiseSayingService(storage);
            scanner = new Scanner(System.in);
            //System.out.println("컨트롤러가 생성되었음 ");

    }

    public void registerWiseSay() {

            String newSaying = getStringInput("명언을 입력하세요: ");
            String newAuthor = getStringInput("작가를 입력하세요: ");
            int wisesayId = wiseService.register(newSaying, newAuthor);
            System.out.println( wisesayId + "번 명언이 성공적으로 등록되었습니다.");

    }

    public void deleteWiseSay() {

            int wisesayId = getWiseSayIdFromUser("삭제할 명언의 ID를 입력하세요: ");
            wiseService.delete(wisesayId);
            System.out.println(wisesayId + "번 명언가 성공적으로 삭제되었습니다.");

    }

    public void updateWiseSay() {

            int wisesayId = getWiseSayIdFromUser("수정할 명언의 ID를 입력하세요: ");
            WiseSaying wisesay = wiseService.load(wisesayId); // 기존에 있던 노트객체를 불러옴
            String newSaying = getStringInput("기존 명언: "+ wisesay.getSaying() +"\n새로운 명언을 입력하세요: ");
            String newAuthor = getStringInput("기존 작가: "+ wisesay.getAuthor() +"\n새로운 작가를 입력하세요: ");

            int fixedId = wiseService.update(wisesayId,newSaying,newAuthor);
            System.out.println( fixedId + "번 명언이 성공적으로 수정되었습니다.");

    }


    private int getWiseSayIdFromUser(String message) {
        System.out.print(message);
        String input = scanner.nextLine().trim();
        int wiseSayid = Integer.parseInt(input);

        if(wiseSayid < 0) {
            System.out.println("ID는 0 이상입니다. ");
        }
        return wiseSayid;
    }

    private String getStringInput(String message) {
        System.out.print(message);
        String input = scanner.nextLine().trim();
        if(input.isEmpty()){
            throw new EmptyInputException();
        }
        return input;
    }

    // 코드 체크 필요
    public void buildWiseSay() {

            wiseService.build();
            System.out.println("모든 명언이 성공적으로 빌드되었습니다.");

    }

    public void showWiseSayList() {

            wiseService.build();
            List<WiseSaying> wisesay = wiseService.loadWiseSay();
            // 목록이 비어 있는 경우 처리
            if (wisesay.isEmpty()) {
                System.out.println("현재 저장된 명언이 없습니다.");
                return;
            }
            // 목록 출력
            System.out.println(" 번호 /     명언      /   작가  ");
            for (WiseSaying wisesay2 : wisesay) {
                System.out.println(" " + wisesay2.getId() + " / " + wisesay2.getSaying() + " / " + wisesay2.getAuthor());
            }

    }

}
