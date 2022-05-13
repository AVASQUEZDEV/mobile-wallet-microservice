package com.nttdata.mobilewallet.repository;

import com.nttdata.mobilewallet.model.Yanki;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * This interface defines the repository of Yanki
 *
 * @author Alcibar Vasquez
 * @version 1.0
 */
@Repository
public interface IYankiRepository extends ReactiveMongoRepository<Yanki, String> {
}
