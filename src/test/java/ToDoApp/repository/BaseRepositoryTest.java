package ToDoApp.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@Slf4j
@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureTestEntityManager
public class BaseRepositoryTest {

    @BeforeEach
    public void setup(TestInfo testInfo) {
        log.info("*********** TEST STARTS ***********");
        log.info("TESTING {} method", testInfo.getDisplayName());
    }

    @AfterEach
    public void tearDown() {
        log.info("*********** TEST ENDS ***********");
    }

}
