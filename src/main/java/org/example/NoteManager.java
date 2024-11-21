package org.example;

public class NoteManager {
    private int id; // 명언 카운터 변수
    private Storage storage;
    private IdManager idManager;

    public NoteManager(Storage storage) {

        this.id = idManager.lastId;
        this.storage = storage;
        System.out.println("노트매니저 생성되었음");

    }

    public int register(String saying, String author) throws SaveException{

        id = idManager.generateId();
        Note newNote = new Note(id, saying, author);
        storage.saveNote(newNote);
         idManager.

        storage.saveLastNoteId(id);
        return id;

    }

    public void delete(int noteId) throws SaveException, NoteNotFoundException {

        storage.deleteNote(noteId);
    }


    public Note load(int noteId) throws NoteNotFoundException, JsonParsingException {

        return storage.loadNoteById(noteId);
    }

    public int update(Note note) throws SaveException {

        storage.saveNote(note);
        return note.getId();
    }



}
