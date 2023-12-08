package io.bootify.no_s_q_l.repos;

import io.bootify.no_s_q_l.domain.App;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface AppRepository extends MongoRepository<App, String> {

    boolean existsByAppIgnoreCase(String app);

}
