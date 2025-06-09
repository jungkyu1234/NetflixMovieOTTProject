package com.reviewboy.domain.users.cache;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface UserCacheRepository extends CrudRepository<UserCache, String> {

    Optional<UserCache> findByUsername(String username);
}
