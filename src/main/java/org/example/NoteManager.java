package org.example;

public class NoteManager {
    private Storage storage;
    private IdManager idManager;

    public NoteManager(Storage storage) throws ReadFileException {
        this.idManager = new IdManager(storage);
        this.storage = storage;
        System.out.println("노트매니저 생성되었음");
    }

    public int register(String saying, String author) throws SaveFileException {

        int id = idManager.generateId();
        Note newNote = new Note(id, saying, author);
        storage.saveNote(newNote);
        idManager.saveId(id);
        return id;

    }

    public void delete(int noteId) throws SaveFileException, NoteNotFoundException {

        storage.deleteNote(noteId);
    }


    public Note load(int noteId) throws NoteNotFoundException, JsonParsingException {

        return storage.loadNoteById(noteId);
    }

    public int update(Note note) throws SaveFileException {

        storage.saveNote(note);
        return note.getId();
    }



}
