package org.fp024.lotto.repository;

import org.fp024.lotto.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

  Member findByNicknameAndPassword(String nickname, String encodedPassword);
}
