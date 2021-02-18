package guru.springframework.repositories.reactive;

import guru.springframework.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataMongoTest
class UnitOfMeasureReactiveRepositoryIT {

    private static final String DESCRIPTION_TEST_MESSAGE = "reactive test";

    @Autowired
    private UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepository;

    UnitOfMeasure unitOfMeasure = new UnitOfMeasure();

    @BeforeEach
    void setUp() {
        unitOfMeasureReactiveRepository.deleteAll().block();
        unitOfMeasure.setDescription(DESCRIPTION_TEST_MESSAGE);
    }

    @Test
    void findByDescriptionReactive() {
        unitOfMeasure.setId(unitOfMeasureReactiveRepository.save(unitOfMeasure).block().getId());

        Long count = unitOfMeasureReactiveRepository.count().block();
        assertEquals(1L, count);
        assertEquals(unitOfMeasure.getId(), unitOfMeasureReactiveRepository.findAll().blockFirst().getId());

    }
}
