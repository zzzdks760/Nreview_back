package com.Board.entity;

import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public class BaseEntity {
    @CreationTimestamp // 생성시 시간정보를 줌
    @Column(updatable = false) // 수정시 관여x
    private LocalDateTime createdTime;

    @UpdateTimestamp // 업데이트시 시간정보 줌
    @Column(insertable = false) // // 입력시 관여 x
    private LocalDateTime updatedTime;
}
