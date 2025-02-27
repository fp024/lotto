package org.fp024.lotto.repository;

import org.fp024.lotto.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberRepository extends JpaRepository<Member, Long> {

  Member findByNicknameAndPassword(String nickname, String encodedPassword);

  @Query(
      """
      SELECT m
        FROM Member m
        LEFT JOIN FETCH m.roles mr
        LEFT JOIN FETCH mr.role r
       WHERE m.nickname = :nickname
      """)
  Member findMemberWithRolesByNickname(@Param("nickname") String nickname);
}
