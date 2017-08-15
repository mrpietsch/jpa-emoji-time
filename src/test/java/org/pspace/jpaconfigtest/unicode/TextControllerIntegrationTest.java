package org.pspace.jpaconfigtest.unicode;

import org.junit.Before;
import org.junit.Test;
import org.pspace.jpaconfigtest.testsupport.AbstractRestControllerTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

public class TextControllerIntegrationTest extends AbstractRestControllerTest<TextEntity> {

    private static final String EMOJI = "\uD83D\uDE00";

    @Autowired
    TextRepository repository;

    private TextEntity exampleEntity;

    public TextControllerIntegrationTest() {
        super("/textEntities", TextEntity.class);
    }

    @Before
    public void setup() {
        TextEntity textEntity = new TextEntity();
        textEntity.setName("Name " + EMOJI);
        textEntity.setText("Text " + EMOJI);
        exampleEntity = repository.save(textEntity);
    }

    @Test
    public void shouldBeAbleToGetOnePosting() {
        testGetEntity("/" + exampleEntity.getId(), this.exampleEntity);
    }

    @Test
    public void shouldBeAbleToGetAList() {
        List<TextEntity> textEntities = testGetList("/");
        assertThat(textEntities, not(empty()));
    }

}