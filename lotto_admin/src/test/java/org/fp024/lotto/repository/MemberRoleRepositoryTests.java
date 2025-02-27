package org.fp024.lotto.repository;

import org.fp024.lotto.entity.MemberRole;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

@SpringBootTest
class MemberRoleRepositoryTests {
  @Autowired private MemberRoleRepository repository;

  @Autowired private MemberRepository memberRepository;

  @Autowired private RoleRepository roleRepository;

  @Disabled("초기 데이터 입력")
  @Test
  @Commit
  void testSave() {
    // 관리자 권한 설정
    var member = memberRepository.findByNicknameAndPassword("admin", "{noop}1234");
    var role = roleRepository.findByName("ADMIN");
    repository.save(MemberRole.builder().member(member).role(role).build());

    // 일반 유저 권한 설정
    member = memberRepository.findByNicknameAndPassword("user01", "{noop}1234");
    role = roleRepository.findByName("MEMBER");
    repository.save(MemberRole.builder().member(member).role(role).build());
  }
}
