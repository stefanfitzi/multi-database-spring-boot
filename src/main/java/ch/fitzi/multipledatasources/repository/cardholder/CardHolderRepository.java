package ch.fitzi.multipledatasources.repository.cardholder;

import ch.fitzi.multipledatasources.model.cardholder.CardHolder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardHolderRepository extends JpaRepository<CardHolder, Long> {
}
