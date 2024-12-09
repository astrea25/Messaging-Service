package app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.entities.Quote;

@Repository
public interface MessageRepository extends JpaRepository<Quote, Long>{
	List<Quote> findAllByCategory(String category);
}
