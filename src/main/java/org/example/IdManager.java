package org.example;

public class IdManager {
    private Storage storage;
    public int lastId;


    public IdManager(Storage storage){
        this.lastId = storage.loadLastNoteId();
        this.storage = storage;
    }

    public int generateId(){
        int newId = lastId + 1;
        System.out.println("ID 카운터 증가");
        return newId;
    }

    public void saveId(int id) throws SaveException {
        storage.saveIdFile(id);
    }


}
