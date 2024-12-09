package app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.entities.Quote;

@Repository
public interface MessageRepository extends JpaRepository<Quote, Long>{
	Quote findByCategory(String c);
}
