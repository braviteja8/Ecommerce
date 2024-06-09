package com.example.authrevision.repositories;

import com.example.authrevision.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token,Long> {
    Token save(Token token);
    Optional<Token>findByValueAndDeletedEqualsAndExpireAtGreaterThan(String token, boolean deleted, Date date);

    Optional<Token> findByValue(String token);
}
