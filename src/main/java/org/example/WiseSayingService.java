package org.example;

import java.util.List;

public class WiseSayingService {
    private WiseSayingRepository repository;
    private IdService idService;

    public WiseSayingService(WiseSayingRepository storage) {

        this.idService = new IdService(storage);
        this.repository = storage;

    }

    public int register(String saying, String author) {

        int id = idService.generateId();
        WiseSaying newNote = new WiseSaying(id, saying, author);
        repository.saveWiseSay(newNote);
        idService.saveId(id);

        return id;
    }

    public void delete(int noteId) {

        repository.deleteWiseSay(noteId);
    }


    public WiseSaying load(int noteId) {

        return repository.loadWiseSayById(noteId);
    }

    public int update(int noteId, String newSaying, String newAuthor) {

        WiseSaying note = repository.loadWiseSayById(noteId);
        note.setSaying(newSaying);
        note.setAuthor(newAuthor);
        repository.saveWiseSay(note);

        return note.getId();
    }

    public void build()  {
        repository.buildDataFile();
    }

    public List<WiseSaying> loadWiseSay() {
        return repository.loadDataFile();
    }

}
