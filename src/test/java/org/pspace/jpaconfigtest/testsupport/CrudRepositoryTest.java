package org.pspace.jpaconfigtest.testsupport;

import org.junit.Test;
import org.pspace.jpaconfigtest.unicode.EntityWithId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.CrudRepository;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public abstract class CrudRepositoryTest<E extends EntityWithId, R extends CrudRepository<E, Long>> extends VirginDatabaseTest {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    protected abstract R getRepository();
    protected abstract E exampleEntity();
    protected abstract E modifyExample(E entity);

    @Test
    public void itSupportsBasicCrudOperations() throws Exception {

        R repository = getRepository();
        E entity     = exampleEntity();

        // create
        logger.info("Saving " + entity);
        E savedEntity = repository.save(entity);
        assertThat(savedEntity.getId(), notNullValue());
        assertThat(savedEntity, equalTo(entity));

        // load by id
        E reloadedEntity = repository.findOne(savedEntity.getId());
        logger.debug("Reloaded " + reloadedEntity);
        assertThat(reloadedEntity, equalTo(savedEntity));

        // update
        E modifiedEntity      = modifyExample(reloadedEntity);
        E savedModifiedEntity = repository.save(modifiedEntity);
        assertThat(savedModifiedEntity.getId(), equalTo(reloadedEntity.getId()));
        assertThat(savedModifiedEntity, equalTo(modifiedEntity));

        // delete
        repository.delete(reloadedEntity.getId());
        E ghost = repository.findOne(reloadedEntity.getId());
        assertThat(ghost, nullValue());
    }

}
