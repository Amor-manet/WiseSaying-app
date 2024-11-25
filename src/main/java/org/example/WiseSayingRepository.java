package org.example;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class WiseSayingRepository {
    private static final String DATA_DIRECTORY = "db" + File.separator + "wiseSaying";
    private static final String LAST_ID_FILE_NAME = DATA_DIRECTORY + File.separator + "lastId.txt";
    private ObjectMapper objectMapper;

    public WiseSayingRepository() {
        createDataDirectory();
        //System.out.println("스토리지가 생성되었습니다.");
        objectMapper = new ObjectMapper();
    }

    private void createDataDirectory() {
        File directory = new File(DATA_DIRECTORY); // 파일 객체 생성
        if (!directory.exists()) { // 만약에 파일 경로가 없을 경우
            directory.mkdirs(); // 파일의 상위 디렉토리가 없을 경우 지정한 상위 폴더를 생성
            //System.out.println("디렉토리가 생성되었습니다: " + directory.getPath());
        }
        // System.out.println("디렉토리가 이미 존재합니다: " + directory.getPath());
    }

    public void saveWiseSay(WiseSaying wisesay) {
        String fileName = wisesay.getId() + ".json"; // 파일이름 지정
        File file = new File(DATA_DIRECTORY, fileName);
        // System.out.println("노트객체를 넘겨받은 파일이 생성되었습니다. " + "저장으로 넘어갑니다.");
        // System.out.println("파일 경로: "+ DATA_DIRECTORY + "/  \n파일이름: "+fileName);
        try {
            objectMapper.writeValue(file, wisesay); // 명언 객체를 JSON으로 직렬화하여 파일로 저장
            // System.out.println("노트가 저장되었습니다: " + file.getAbsolutePath() + fileName);
        } catch (IOException e) {
            // IOException을 SaveException으로 래핑하여 전달
            throw new SaveFileException(wisesay.getId(), e);
        }
    }

    public void deleteWiseSay(int wisesayId) {
        String fileName = wisesayId + ".json";
        File file = new File(DATA_DIRECTORY, fileName);

        if (!file.exists()) { // 파일이 존재하는지 체크
            throw new SayNotFoundException(wisesayId);
        }
        if (!file.delete()) { // 파일이 지워졌는지 체크
            throw new SaveFileException(wisesayId);
        }
    }

    public WiseSaying loadWiseSayById(int wiseSayId) {
        String filePath = DATA_DIRECTORY + "/" + wiseSayId + ".json";
        File file = new File(filePath);

        if (!file.exists()) { // 파일이 존재하는지 체크
            throw new SayNotFoundException(wiseSayId);
        }

        try {
            // JSON 파일을 명언 객체로 반환
            return objectMapper.readValue(file, WiseSaying.class);
        } catch (JsonParseException | JsonMappingException e) {
            // JSON 형식 오류 처리
            throw new JsonParsingException(filePath, e);
        } catch (IOException e) {
            // 파일 읽기 중 발생한 일반적인 오류 처리
            throw new JsonParsingException(e);
        }
    }

    // 마지막 아이디 불러오기
    public int loadLastWiseSayId() {
        File file = new File(LAST_ID_FILE_NAME);

        if (!file.exists()) { // 파일이 없다면 0을 반환
            return 0;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String lastid = reader.readLine(); // 파일에 있는 숫자를 문자열로
            return Integer.parseInt(lastid.trim()); // 받은 문자열을 숫자로 반환
        } catch (IOException | NumberFormatException e) {
            throw new ReadFileException(e);
        }
    }

    public void saveIdFile(int lastSayId) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LAST_ID_FILE_NAME))) {
            writer.write(String.valueOf(lastSayId));
        } catch (IOException e) {
            throw new SaveFileException(lastSayId);
        }
    }

    // 데이터 파일을 빌드하는 메소드
    public void buildDataFile() {

        File directory = new File(DATA_DIRECTORY); // 파일이 저장된 디렉토리
        File buildFile = new File(DATA_DIRECTORY, "data.json");

        // 디렉토리 유효성 검사
        if (!directory.exists() || !directory.isDirectory()) {
            throw new BuildFileException();
        }
        // 제이슨 파일 필터링
        File[] files = directory.listFiles((dir, name) -> name.endsWith(".json") && !name.equals("data.json"));
        if (files == null || files.length == 0) { // directory.listFiles()이 null 값인 경우, 배열에 넣을 알맞은 파일이 없어서 배일이 0일 경우
            createEmptyJsonFile(buildFile);
            throw new BuildFileException();
        }

        List<WiseSaying> wiseSayings = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();

        // 여기부터 아래는 다시 체크해볼 필요가 있음
        for (File file : files) {
            try {
                WiseSaying say = objectMapper.readValue(file, WiseSaying.class); // JSON -> 명언 객체 변환
                wiseSayings.add(say); // 리스트에 추가
            } catch (IOException e) {
                throw new BuildFileException(e);
            }
        }

        // Id를 기준으로 명언 리스트 정렬
        wiseSayings.sort(Comparator.comparingInt(WiseSaying::getId));

        // data.json 파일로 명언 리스트 저장
        try {
            objectMapper.writeValue(buildFile, wiseSayings); // 명언 리스트 -> data.json 저장
            //System.out.println("모든 명언이 data.json 파일에 성공적으로 저장되었습니다.");
        } catch (IOException e) {
            throw new SaveFileException(e);
        }
    }

    public List<WiseSaying> loadDataFile() {
        File dataFile = new File(DATA_DIRECTORY, "data.json");

        try {
            // JSON 파일을 명언 객체 리스트로 변환
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(dataFile, new TypeReference<List<WiseSaying>>() {
            });
        } catch (IOException e) {
            throw new ReadFileException(e);
        }
    }

    // 빈 JSON 파일을 생성하는 메서드
    private void createEmptyJsonFile(File buildFile) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            objectMapper.writeValue(buildFile, new ArrayList<>()); // 빈 리스트를 JSON 파일로 직렬화

        } catch (IOException e) {
            throw new BuildFileException();
        }
    }


}
