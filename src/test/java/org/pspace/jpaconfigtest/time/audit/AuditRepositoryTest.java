package org.pspace.jpaconfigtest.time.audit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.pspace.jpaconfigtest.Main;
import org.pspace.jpaconfigtest.testsupport.CrudRepositoryTest;
import org.pspace.jpaconfigtest.time.audit.AuditEntity;
import org.pspace.jpaconfigtest.time.audit.AuditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Main.class)
public class AuditRepositoryTest extends CrudRepositoryTest<AuditEntity, AuditRepository> {

    @Autowired
    AuditRepository repository;

    @Override
    protected AuditRepository getRepository() {
        return repository;
    }

    @Override
    protected AuditEntity exampleEntity() {
        AuditEntity AuditEntity = new AuditEntity();
        AuditEntity.setName("A NAME");
        return AuditEntity;
    }

    @Override
    protected AuditEntity modifyExample(AuditEntity entity) {
        return entity;
    }

    @Test
    public void makeSureDatesAreHandledByJPAAndNotByDatabase() throws Exception {

        AuditEntity AuditEntity = exampleEntity();
        AuditEntity saved = repository.save(AuditEntity);

        assertThat(saved.getCreatedAt(), notNullValue());
        assertThat(saved.getUpdatedAt(), notNullValue());

    }
}
