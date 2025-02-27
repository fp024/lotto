package org.fp024.lotto.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.fp024.lotto.constant.Constants;

/** 회원 */
@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(
    of = {"id"},
    callSuper = false)
@Table(name = Constants.TABLE_PREFIX + "member")
public class Member extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true)
  private String nickname;

  private String password;

  @Builder.Default
  @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
  private Set<MemberRole> roles = new HashSet<>();

  public Set<String> getRoleNames() {
    Set<String> nameSet = new HashSet<>();
    var itr = roles.iterator();

    while (itr.hasNext()) {
      nameSet.add(itr.next().getRole().getName());
    }
    return nameSet;
  }
}
