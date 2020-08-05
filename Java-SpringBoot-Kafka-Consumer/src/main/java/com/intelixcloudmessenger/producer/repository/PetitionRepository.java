package com.intelixcloudmessenger.producer.repository;

import com.intelixcloudmessenger.producer.domain.Petition;
import org.springframework.data.cassandra.repository.CassandraRepository;

public interface PetitionRepository extends CassandraRepository<Petition, Long> {

    Petition findById(long id);

    void deleteById(long id);
}
