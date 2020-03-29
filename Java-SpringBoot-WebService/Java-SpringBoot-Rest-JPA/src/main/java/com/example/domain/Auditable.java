package com.example.domain;


import com.example.convert.db.BooleanConverter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;


@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@ApiModel(description = "Esta es la tabla da auditoria")
@Getter
@Setter
public abstract class Auditable implements Serializable {

	private static final long serialVersionUID = 2374014313701735497L;

	@CreatedBy
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="created_by", referencedColumnName="user_id", updatable = false)
	@ApiModelProperty(notes = "El usuario que creo el registro")
	private User createdBy;

    @CreatedDate
	@ApiModelProperty(notes = "La fecha en la que se creo el registro")
    @Column(nullable = false, columnDefinition = "timestamp default now()")
	private LocalDateTime created;

    @LastModifiedBy
    @ManyToOne(fetch = FetchType.LAZY)
	@ApiModelProperty(notes = "El usuario que modifico el registro")
    @JoinColumn(name="updated_by", referencedColumnName="user_id", updatable = true)
	private User updatedBy;

    @LastModifiedDate
	@ApiModelProperty(notes = "La fecha en la que se modifico el registro")
    @Column(nullable = false, columnDefinition = "timestamp default now()")
	private LocalDateTime updated;

	@Convert(converter = BooleanConverter.class)
    @Column(length = 1, nullable = false, columnDefinition = "character default 'Y'")
	@ApiModelProperty(notes = "Saber si esta activo el registro")
	private Boolean isActive;

}
