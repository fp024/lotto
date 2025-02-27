package org.fp024.lotto.entity;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.fp024.lotto.constant.Constants;

/** 권한 */
@Getter
@NoArgsConstructor
@Entity
@ToString
@EqualsAndHashCode(
    of = {"id"},
    callSuper = false)
@Table(name = Constants.TABLE_PREFIX + "role")
public class Role extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /** 권한 이름 */
  @Column(unique = true)
  private String name;

  /** 권한 설명 */
  private String description;

  /** 접근 URL 패턴 목록 */
  @ElementCollection
  @CollectionTable(
      name = Constants.TABLE_PREFIX + "role_url_patterns",
      joinColumns = @JoinColumn(name = "role_id"))
  @Column(name = "url_pattern")
  private Set<String> urlPatterns = new HashSet<>();
}
