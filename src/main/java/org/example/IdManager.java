package org.example;

public class IdManager {
    private Storage storage;
    public int lastId;


    public IdManager(Storage storage) throws ReadFileException {
        this.lastId = storage.loadLastNoteId();
        this.storage = storage;
    }

    public int generateId(){
        int newid = lastId + 1;
        System.out.println("ID 카운터 증가");
        return newid;
    }

    public void saveId(int id) throws SaveFileException {
        storage.saveIdFile(id);
    }


}
