package com.mew.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Console")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Console {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @EqualsAndHashCode.Include
  @Column(name = "Console_Id", nullable = false)
  private int consoleId;

  @Column(name = "Console_Name", nullable = true, length = 12)
  private String consoleName;

  @OneToMany(mappedBy = "preferredConsole")
  @Builder.Default
  @ToString.Exclude
  private List<User> users = new java.util.ArrayList<>();
}
