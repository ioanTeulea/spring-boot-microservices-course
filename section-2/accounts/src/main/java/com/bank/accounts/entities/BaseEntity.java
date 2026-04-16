package com.bank.accounts.entities;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@MappedSuperclass
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity {

    @Column(name="created_at",updatable = false)
    private LocalDateTime createdAt;

    @Column(name="updated_at",insertable = false)
    private LocalDateTime updatedAt;

    @Column(name="created_by",updatable = false)
    private String createdBy;

    @Column(name="updated_by",insertable = false)
    private String updatedBy;
}
