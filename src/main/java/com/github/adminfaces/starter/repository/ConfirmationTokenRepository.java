package com.github.adminfaces.starter.repository;
import com.github.adminfaces.starter.infra.model.*;
import com.github.adminfaces.starter.model.ConfirmationToken;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository("confirmationTokenRepository")
public interface ConfirmationTokenRepository extends CrudRepository<ConfirmationToken, String> {
    ConfirmationToken findByConfirmationToken(String confirmationToken);
}