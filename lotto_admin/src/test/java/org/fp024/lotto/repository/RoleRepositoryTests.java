package org.fp024.lotto.repository;

import java.util.Set;
import org.fp024.lotto.entity.Role;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

@SpringBootTest
class RoleRepositoryTests {

  @Autowired private RoleRepository repository;

  @Disabled("초기 데이터 입력")
  @Test
  @Commit
  void testSave() {

    var role =
        Role.builder() //
            .name("ADMIN")
            .description("관리자")
            .urlPatterns(Set.of("/", "/lotto/**", "/admin/**"))
            .build();
    repository.save(role);

    var member =
        Role.builder() //
            .name("MEMBER")
            .description("회원")
            .urlPatterns(Set.of("/", "/lotto/**"))
            .build();

    repository.save(member);
  }
}
