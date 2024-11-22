package org.example;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class Storage {
    private static final String DATA_DIRECTORY = "db" + File.separator + "wiseSaying";
    private static final String LAST_ID_FILE_NAME = DATA_DIRECTORY + File.separator + "lastId.txt";
    private ObjectMapper objectMapper;

    public Storage() {
        createDataDirectory();
        System.out.println("스토리지가 생성되었습니다.");
        objectMapper = new ObjectMapper();
    }

    private void createDataDirectory() {
        File directory = new File(DATA_DIRECTORY); // 파일 객체 생성
        if (!directory.exists()) { // 만약에 파일 경로가 없을 경우
            directory.mkdirs(); // 파일의 상위 디렉토리가 없을 경우 지정한 상위 폴더를 생성
            System.out.println("디렉토리가 생성되었습니다: " + directory.getPath());
        }
        System.out.println("디렉토리가 이미 존재합니다: " + directory.getPath());
    }

    public void saveNote(Note note) throws SaveFileException {
        String fileName = note.getId() + ".json"; // 파일이름 지정
        File file = new File(DATA_DIRECTORY, fileName);
        System.out.println("노트객체를 넘겨받은 파일이 생성되었습니다. " + "저장으로 넘어갑니다.");
        System.out.println("파일 경로: "+ DATA_DIRECTORY + "/  \n파일이름: "+fileName);
        try {
            objectMapper.writeValue(file, note); // Note 객체를 JSON으로 직렬화하여 파일로 저장
            System.out.println("노트가 저장되었습니다: " + file.getAbsolutePath() + fileName);
        } catch (IOException e) {
            // IOException을 SaveException으로 래핑하여 전달
            throw new SaveFileException(note.getId(), e);
        }
    }

    public void deleteNote(int noteId) throws SaveFileException, NoteNotFoundException {
        String fileName = noteId + ". json";
        File file = new File(DATA_DIRECTORY,fileName);

        if (!file.exists()){ // 파일이 존재하는지 체크
            throw new NoteNotFoundException(noteId);
        }
        if (!file.delete()){ // 파일이 지워졌는지 체크
            throw new SaveFileException(noteId);
        }
    }

    public Note loadNoteById(int noteId) throws NoteNotFoundException, JsonParsingException {
        String filePath = DATA_DIRECTORY+"/"+noteId+".json";
        File file = new File(filePath);

        if (!file.exists()){ // 파일이 존재하는지 체크
            throw new NoteNotFoundException(noteId);
        }
        try {
            // JSON 파일을 Note 객체로 반환
            return objectMapper.readValue(file, Note.class);
        } catch (JsonParseException | JsonMappingException e) {
            // JSON 형식 오류 처리
            throw new JsonParsingException(filePath, e);
        } catch (IOException e) {
            // 파일 읽기 중 발생한 일반적인 오류 처리
            throw new JsonParsingException(e);
        }
    }

    public loadAllNotes() {

    }



    public int loadLastNoteId() throws ReadFileException {
        File file = new File(LAST_ID_FILE_NAME);

        if(!file.exists()){ // 파일이 없다면 0을 반환
            return 0;
        }

        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            String lastid = reader.readLine(); // 파일에 있는 숫자를 문자열로
            return Integer.parseInt(lastid.trim()); // 받은 문자열을 숫자로 반환
        } catch (IOException | NumberFormatException e){
         throw new ReadFileException(e);
        }
    }

    public void saveIdFile(int lastNoteId) throws SaveFileException {

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(LAST_ID_FILE_NAME))){
            writer.write(String.valueOf(lastNoteId));
        } catch (IOException e) {
            throw new SaveFileException(lastNoteId);
        }
    }
}
