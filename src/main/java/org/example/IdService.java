package org.example;

import domain.wiseSaying.repository.WiseSayingRepository;

public class IdService {
    private WiseSayingRepository repository;

    public IdService(WiseSayingRepository storage) {
        this.repository = storage;
    }

    public int generateId() {

        return repository.loadLastWiseSayId() + 1;
    }

    public void saveId(int id) {
        repository.saveIdFile(id);
    }


}
