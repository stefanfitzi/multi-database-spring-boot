package ch.fitzi.multipledatasources;

import ch.fitzi.multipledatasources.model.card.Card;
import ch.fitzi.multipledatasources.model.cardholder.CardHolder;
import ch.fitzi.multipledatasources.model.member.Member;
import ch.fitzi.multipledatasources.repository.card.CardRepository;
import ch.fitzi.multipledatasources.repository.cardholder.CardHolderRepository;
import ch.fitzi.multipledatasources.repository.member.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Disabled
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MultipledatasourcesApplicationTests {

    /*
    * We will be using mysql, postgres and mssql databases we configured in our properties file for our tests
    * Make sure your datasource connections are correct otherwise the test will fail
    * */

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private CardHolderRepository cardHolderRepository;

    @Autowired
    private CardRepository cardRepository;

    private Member member;
    private Card card;
    private CardHolder cardHolder;

    @BeforeEach
    public void initializeDataObjects(){

        member = new Member();
        member.setMemberId("M001");
        member.setName("Maureen Mpofu");

        cardHolder = new CardHolder();
        cardHolder.setCardNumber("4111111111111111");
        cardHolder.setMemberId(member.getMemberId());

        card = new Card();
        card.setExpirationMonth(01);
        card.setExpirationYear(2020);
        card.setName(member.getName());

    }

    @Test
    public void shouldSaveMemberToMemberDB() {
        Member savedMember =memberRepository.save(member);
        Optional<Member> memberFromDb= memberRepository.findById(savedMember.getId());
        assertTrue(memberFromDb.isPresent());
    }

    @Test
    public void shouldSaveCardHolderToCardHolderDB() {
        CardHolder savedCardHolder =cardHolderRepository.save(cardHolder);
        Optional<CardHolder> cardHolderFromDb= cardHolderRepository.findById(savedCardHolder.getId());
        assertTrue(cardHolderFromDb.isPresent());
    }

    @Test
    public void shouldSaveCardToCardDB() {
        Card savedCard = cardRepository.save(card);
        Optional<Card> cardFromDb= cardRepository.findById(savedCard.getId());
        assertTrue(cardFromDb.isPresent());
    }
}
