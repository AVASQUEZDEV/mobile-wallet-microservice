package com.nttdata.mobilewallet.repository;

import com.nttdata.mobilewallet.model.P2P;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * This interface defines the repository of P2P
 *
 * @author Alcibar Vasquez
 * @version 1.0
 */
@Repository
public interface IP2PRepository extends ReactiveMongoRepository<P2P, String> {
}
