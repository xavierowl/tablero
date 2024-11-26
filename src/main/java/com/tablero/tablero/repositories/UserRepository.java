package com.tablero.tablero.repositories;

import com.tablero.tablero.domains.Persona;
import com.tablero.tablero.domains.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUserName(String userName);
}
