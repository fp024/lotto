package org.fp024.lotto.repository;

import static org.assertj.core.api.Assertions.assertThat;

import lombok.extern.slf4j.Slf4j;
import org.fp024.lotto.entity.Member;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

@Slf4j
@SpringBootTest
class MemberRepositoryTests {

  @Autowired private MemberRepository repository;

  @Disabled("초기 데이터 입력")
  @Test
  @Commit
  void save() {
    var admin =
        Member.builder() //
            .nickname("admin")
            .password("{noop}1234")
            .build();

    repository.save(admin);

    var user01 =
        Member.builder() //
            .nickname("user01")
            .password("{noop}1234")
            .build();

    repository.save(user01);
  }

  @Test
  void findMemberWithRolesByNickname() {
    var admin = repository.findMemberWithRolesByNickname("admin");

    assertThat(admin.getNickname()).isEqualTo("admin");

    assertThat(admin.getRoleNames()).contains("ADMIN");
  }
}
