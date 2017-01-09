package com.example;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.valueOf;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class MyEntityRepositoryTest {

    private static final int TEST_DATA_SIZE = 5;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MyEntityRepository repository;

    @After
    public void tearDown() {
        repository.deleteAll();
    }

    @Test
    public void sortDescending() throws Exception {
        final List<MyEntity> testData = createTestData();
        repository.save(testData);
        final MyEntity lastEntity = testData.get(TEST_DATA_SIZE - 1);

        mockMvc.perform(get("/myEntities").param("sort", "embedded.name,desc")
                                          .param("size", valueOf(TEST_DATA_SIZE)))
               .andExpect(jsonPath("$._embedded.myEntities[0].embedded.name")
                       .value(is(lastEntity.getEmbedded().getName())));
    }

    private List<MyEntity> createTestData() {
        final List<MyEntity> result = new ArrayList<>(TEST_DATA_SIZE);

        for (int i = 0; i < TEST_DATA_SIZE; i++) {
            result.add(new MyEntity(new Embedded("NAME-" + i)));
        }

        return result;
    }
}
