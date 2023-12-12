package ch.fitzi.multipledatasources.repository.card;

import ch.fitzi.multipledatasources.model.card.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
}
