package org.example;

import java.util.HashMap;
import java.util.Map;

public class Storage {
    private Map<Integer, Note> notes;
    private static final String FILE_NAME = "notes.dat";

    public Storage() {
        notes = new HashMap<>();
        System.out.println("스토리지가 생성되었음 ");
        loadFromFile();
    }

    private void loadFromFile() {

    }

    public Map<Integer, Note> loadNotes() {
        return notes;
    }

    public int getLastNoteId() {
        return 0;
    }

    public void saveNote(Note note) {

    }

    public void deleteNote(int noteId) {

    }

    private void saveToFile() {

    }
}
