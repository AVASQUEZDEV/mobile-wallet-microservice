package com.nttdata.mobilewallet.service;

import com.nttdata.mobilewallet.dto.request.P2PRequest;
import com.nttdata.mobilewallet.generics.ICRUD;
import com.nttdata.mobilewallet.model.P2P;

/**
 * This interface extends the methods CRUD
 *
 * @author Alcibar Vasquez
 * @version 1.0
 */
public interface IP2PService extends ICRUD<P2P, P2PRequest> {
}
