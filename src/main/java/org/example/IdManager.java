package org.example;

public class IdManager {
    private Storage storage;



    public IdManager(Storage storage) {
        this.storage = storage;
    }

    public int generateId() throws ReadFileException {
        int newid = storage.loadLastNoteId() + 1;
        //System.out.println("ID 카운터 증가");
        return newid;
    }

    public void saveId(int id) throws SaveFileException {
        storage.saveIdFile(id);
    }


}
