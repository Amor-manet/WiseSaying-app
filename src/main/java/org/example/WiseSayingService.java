package org.example;

import java.util.List;

public class WiseSayingService {
    private WiseSayingRepository repository;
    private IdService idService;

    public WiseSayingService(WiseSayingRepository storage) throws ReadFileException {

        this.idService = new IdService(storage);
        this.repository = storage;

    }

    public int register(String saying, String author) throws SaveFileException, ReadFileException {

        int id = idService.generateId();
        WiseSaying newNote = new WiseSaying(id, saying, author);
        repository.saveWiseSay(newNote);
        idService.saveId(id);

        return id;
    }

    public void delete(int noteId) throws SaveFileException, SayNotFoundException {

        repository.deleteWiseSay(noteId);
    }


    public WiseSaying load(int noteId) throws SayNotFoundException, JsonParsingException {

        return repository.loadWiseSayById(noteId);
    }

    public int update(int noteId, String newSaying, String newAuthor) throws SaveFileException, SayNotFoundException, JsonParsingException {

        WiseSaying note = repository.loadWiseSayById(noteId);
        note.setSaying(newSaying);
        note.setAuthor(newAuthor);
        repository.saveWiseSay(note);

        return note.getId();
    }

    public void build() throws BuildFileException, SaveFileException {
        repository.buildDataFile();
    }

    public List<WiseSaying> loadWiseSay() throws ReadFileException {
        return repository.loadDataFile();
    }

}
