package org.pspace.jpaconfigtest.time.audit;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author peach
 */
public interface AuditRepository extends JpaRepository<AuditEntity, Long> {

}