package io.github.felixzailskas.settlingmesoftly.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "account")
public class Account {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String type;

  @Column(nullable = false, precision = 19, scale = 4)
  private BigDecimal balance = BigDecimal.ZERO;

  @Version private Integer version;

  @Column(name = "created_at", nullable = false, updatable = false)
  private Instant createdAt = Instant.now();

  @Column(name = "updated_at", nullable = false)
  private Instant updatedAt = Instant.now();

  @Column(name = "deleted_at", updatable = false)
  private Instant deletedAt;

  protected Account() {}

  public Account(String name, String type) {
    this.name = name;
    this.type = type;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getType() {
    return type;
  }

  public BigDecimal getBalance() {
    return balance;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }
}
