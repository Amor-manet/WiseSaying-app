package org.example;

import java.util.HashMap;
import java.util.Map;

public class NoteManager {
    private int noteCounter = 0;                      // 명언 카운터 변수


    private Map<Integer, Note> notes = new HashMap<>(); // 노트 객체들을 저장할 맵
    private Storage storage;

    public NoteManager(Storage storage) {
        this.storage = storage;
        this.noteCounter = storage.loadLastNoteId(); // 저장소에서 마지막 노트 ID를 가져옴
        System.out.println("노트매니저 생성되었음 마지막 노트번호는: " + noteCounter);
    }

    public int register(String saying, String author) throws SaveException{
        noteCounter++;
        System.out.println("노트카운터증가");
        Note newNote = new Note(noteCounter, saying, author);
        storage.saveNote(newNote);
        storage.saveLastNoteId(noteCounter);
        return noteCounter;
    }

    public void delete(int noteId) throws SaveException, NoteNotFoundException {
        storage.deleteNote(noteId);
    }


    public void update(int noteId, String newSaying, String newAuthor) throws NoteNotFoundException {
        if (notes.containsKey(noteId)) {
            Note note = notes.get(noteId);
            note.setSaying(newSaying);
            note.setAuthor(newAuthor);
            // 수정된 노트 객체를 저장소의 등록 함수에 전달하는 부분은 저장소 구현 후 추가 예정
        } else {
            throw new NoteNotFoundException(noteId);
        }
    }

    public void listNotes() throws EmptySayingException {
        if (notes.isEmpty()) {
            throw new EmptySayingException();
        }
        for (Note note : notes.values()) {
            System.out.println(note.getId() + "  /  " + note.getSaying() + "  /  " + note.getAuthor());
        }
        // 저장소의 목록 함수를 호출하는 부분은 저장소 구현 후 추가 예정
    }

    public Note getNoteById(int noteId) {
        return notes.get(noteId);
    }
}
