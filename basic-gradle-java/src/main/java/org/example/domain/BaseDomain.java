package org.example.domain;

import io.ebean.Model;
import io.ebean.annotation.WhenCreated;
import io.ebean.annotation.WhenModified;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;

@MappedSuperclass
@Getter  @Setter
@ToString(doNotUseGetters = true, callSuper = false) // avoid getters!
//@Accessors(fluent = true, chain = true) // instead of @Builder(toBuilder = true)
@NoArgsConstructor  @AllArgsConstructor
public abstract class BaseDomain extends Model {

	@Id
	long id;

	@Version
	long version;

	@WhenCreated
	Instant whenCreated;

	@WhenModified
	Instant whenModified;

}
