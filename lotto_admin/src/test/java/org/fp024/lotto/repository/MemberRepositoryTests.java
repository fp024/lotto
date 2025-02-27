package org.fp024.lotto.repository;

import org.fp024.lotto.entity.Member;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

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
}
