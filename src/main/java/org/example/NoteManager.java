package org.example;
import java.util.HashMap;
import java.util.Map;

public class NoteManager {
    private int noteCounter = 0;                      // 명언 카운터 변수
    private Map<Integer, Note> notes = new HashMap<>(); // 노트 객체들을 저장할 맵
    private Storage storage;

    public NoteManager(Storage storage) {
        this.storage = storage;
        this.notes = storage.loadNotes();         // 저장소에서 노트들을 로드
        this.noteCounter = storage.getLastNoteId(); // 저장소에서 마지막 노트 ID를 가져옴
        System.out.println("노트매니저 생성되었음 " + noteCounter);
    }

    public void register(String saying, String author) {
        System.out.println("명언카운터증가");
        noteCounter++;
        Note newNote = new Note(noteCounter, saying, author);
        notes.put(newNote.getId(), newNote);
        System.out.println(noteCounter + "번 명언이 등록되었습니다.");
        // 저장소의 등록 함수에 전달하는 부분은 저장소 구현 후 추가 예정
    }

    public void delete(int noteId) {
        if (notes.containsKey(noteId)) {
            notes.remove(noteId);
            // 저장소의 삭제 함수에 전달하는 부분은 저장소 구현 후 추가 예정
        } else {
            System.out.println("해당 ID의 노트가 존재하지 않습니다.");
        }
    }

    public void update(int noteId, String newSaying, String newAuthor) {
        if (notes.containsKey(noteId)) {
            Note note = notes.get(noteId);
            note.setSaying(newSaying);
            note.setAuthor(newAuthor);
            // 수정된 노트 객체를 저장소의 등록 함수에 전달하는 부분은 저장소 구현 후 추가 예정
        } else {
            System.out.println("해당 ID의 노트가 존재하지 않습니다.");
        }
    }

    public void listNotes() {
        for (Note note : notes.values()) {
            System.out.println("번호 /      명언      /  작가");
            System.out.println(note.getId()+"  /  "+ note.getSaying()+"  /  "+ note.getAuthor());
        }
        // 저장소의 목록 함수를 호출하는 부분은 저장소 구현 후 추가 예정
    }

    public Note getNoteById(int noteId) {
        return notes.get(noteId);
    }
}
