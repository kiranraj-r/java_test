package de.lathspell.test.hsql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HsqlPersonRepository extends JpaRepository<HsqlPerson, Long> {
}
