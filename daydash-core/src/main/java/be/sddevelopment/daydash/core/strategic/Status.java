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

import java.util.Set;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Singular;
import lombok.ToString;

/**
 * Fake-enum construct to allow for dynamically adding values, if needed.
 */
@ToString
@EqualsAndHashCode
@Builder(access = AccessLevel.PRIVATE, builderMethodName = "status", buildMethodName = "create", setterPrefix = "with")
public class Status {

	public static final Status BACKLOG = status().withStatusName("BACKLOG").create();
	public static final Status IN_PROGRESS = status().withStatusName("IN_PROGRESS").withTransition(BACKLOG).create();
	public static final Status DONE = status().withStatusName("DONE").withTransition(IN_PROGRESS).create();
	public static final Status OBSOLETE = status().withStatusName("OBSOLETE").withTransition(BACKLOG).withTransition(IN_PROGRESS).create();

	private String statusName;
	@Singular
	private Set<Status> transitions;

	public Status transitionTo(Status requestedState) {
		if (requestedState.accepts(this)) {
			this.statusName = requestedState.statusName;
			this.transitions = requestedState.transitions;
		}
		return this;
	}

	private boolean accepts(Status requestedState) {
		return this.transitions.contains(requestedState);
	}
}
