/*-
 * #%L
 * daydash-core
 * %%
 * Copyright (C) 2020 - 2023 SD Development
 * %%
 * Licensed under the EUPL, Version 1.1 or – as soon they will be
 * approved by the European Commission - subsequent versions of the
 * EUPL (the "Licence");
 *
 * You may not use this work except in compliance with the Licence.
 * You may obtain a copy of the Licence at:
 *
 * http://ec.europa.eu/idabc/eupl5
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the Licence is distributed on an "AS IS" basis,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the Licence for the specific language governing permissions and
 * limitations under the Licence.
 * #L%
 */

package be.sddevelopment.daydash.core.strategic;

import static be.sddevelopment.daydash.core.strategic.Status.BACKLOG;
import static be.sddevelopment.daydash.core.strategic.Status.DONE;
import static be.sddevelopment.daydash.core.strategic.Status.IN_PROGRESS;
import static java.util.UUID.randomUUID;

import java.util.UUID;

import be.sddevelopment.daydash.core.shared.FailureToPersist;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Value;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.builder.EqualsExclude;
import org.apache.commons.lang3.builder.HashCodeExclude;

@Value
@Slf4j
@EqualsAndHashCode
@Accessors(chain = true, fluent = true)
@Builder(builderMethodName = "action", toBuilder = true, setterPrefix = "with", buildMethodName = "create")
public class Action {

	@Builder.Default
	UUID identifier = randomUUID();
	String name;
	@Builder.Default
	Status status = BACKLOG;
	@Getter(AccessLevel.NONE)
	@EqualsExclude
	@HashCodeExclude
	ActionRepository repository;

	public String identifier() {
		return identifier.toString();
	}

	public Action start() {
		this.status.transitionTo(IN_PROGRESS);
		save();
		return this;
	}

	private void save() {
		try {
			repository.save(this);
		} catch (FailureToPersist e) {
			log.error("Failure to persist Action {}", this.identifier);
		}
	}

	public Action finish() {
		this.status.transitionTo(DONE);
		this.save();
		return this;
	}
}
