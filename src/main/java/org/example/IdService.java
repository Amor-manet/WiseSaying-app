package org.example;

public class IdService {
    private WiseSayingRepository repository;

    public IdService(WiseSayingRepository storage) {
        this.repository = storage;
    }

    public int generateId() {

        int newid = repository.loadLastWiseSayId() + 1;

        return newid;
    }

    public void saveId(int id) {
        repository.saveIdFile(id);
    }


}
