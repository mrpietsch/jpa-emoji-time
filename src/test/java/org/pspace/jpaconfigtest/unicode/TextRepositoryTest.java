package org.pspace.jpaconfigtest.unicode;

import org.junit.runner.RunWith;
import org.pspace.jpaconfigtest.Main;
import org.pspace.jpaconfigtest.testsupport.CrudRepositoryTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Main.class)
public class TextRepositoryTest extends CrudRepositoryTest<TextEntity, TextRepository> {

    @Autowired
    TextRepository repository;

    @Override
    protected TextRepository getRepository() { return repository; }

    @Override
    protected TextEntity exampleEntity() {
        TextEntity textEntity = new TextEntity();
        textEntity.setName("TEST-NAME");
        textEntity.setText("TEST-TEXT");
        return textEntity;
    }

    @Override
    protected TextEntity modifyExample(TextEntity entity) {
        entity.setName("MODIFIED-NAME");
        return entity;
    }

}
