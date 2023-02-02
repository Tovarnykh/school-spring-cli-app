package ua.foxminded.javaspring.tovarnykh.school_jdbc_api.domain.generator;

import java.util.List;
import java.util.Random;

public interface Generator<T> {

    List<T> generate();

    default Random getRandom() {
        return new Random();
    }

}