package org.fp024.lotto.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.fp024.lotto.constant.Constants;

/** 회원 권한 */
@Getter
@Entity
@NoArgsConstructor
@ToString(of = {"id"})
@EqualsAndHashCode(
    of = {"id"},
    callSuper = false)
@Table(
    name = Constants.TABLE_PREFIX + "member_role",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"member_id", "role_id"})})
public class MemberRole extends BaseEntity {

  @EmbeddedId private MemberRoleId id;

  @ManyToOne(fetch = FetchType.LAZY)
  @MapsId("memberId")
  @JoinColumn(name = "member_id")
  private Member member;

  @ManyToOne(fetch = FetchType.LAZY)
  @MapsId("roleId")
  @JoinColumn(name = "role_id")
  private Role role;

  @Builder
  public MemberRole(Member member, Role role) {
    this.member = member;
    this.role = role;
    this.id = new MemberRoleId(member.getId(), role.getId());
  }

  @Embeddable
  @Getter
  @EqualsAndHashCode
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class MemberRoleId implements Serializable {
    private Long memberId;
    private Long roleId;
  }
}
