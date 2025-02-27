package org.fp024.lotto.repository;

import org.fp024.lotto.entity.MemberRole;
import org.fp024.lotto.entity.MemberRole.MemberRoleId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRoleRepository extends JpaRepository<MemberRole, MemberRoleId> {}
