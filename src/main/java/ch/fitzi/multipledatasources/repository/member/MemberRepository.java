package ch.fitzi.multipledatasources.repository.member;

import ch.fitzi.multipledatasources.model.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
