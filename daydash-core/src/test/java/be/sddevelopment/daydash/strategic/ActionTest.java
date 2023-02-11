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

package be.sddevelopment.daydash.strategic;

import static be.sddevelopment.daydash.core.strategic.Action.action;
import static be.sddevelopment.daydash.core.strategic.Status.BACKLOG;
import static be.sddevelopment.daydash.core.strategic.Status.DONE;
import static be.sddevelopment.daydash.core.strategic.Status.IN_PROGRESS;

import be.sddevelopment.daydash.core.strategic.Action;
import be.sddevelopment.daydash.core.strategic.ActionRepository;
import be.sddevelopment.daydash.testing.DayDashBaseTest;
import lombok.SneakyThrows;

import org.junit.jupiter.api.Test;

class ActionTest implements DayDashBaseTest {

	private final ActionRepository stubRepo = new ActionRepositoryStub();

	@Test
	void whenFinishingTheTask_thenItsStatusShouldBeDone() {
		var action = action().withRepository(stubRepo()).withName("Do something").create();
		assertThat(action.status()).isEqualTo(BACKLOG);

		action.start();
		assertThat(action.status()).isEqualTo(IN_PROGRESS);

		action.finish();
		assertThat(action.status()).isEqualTo(DONE);
	}

	@Test
	@SneakyThrows
	void whenAnActionsTransitions_thenItsPersited() {
		var action = action().withRepository(stubRepo()).withName("Do something").create();
		stubRepo().save(action);
		assertThat(stubRepo().actionByIdentifier(action.identifier())).isPresent();

		action.start().finish();

		assertThat(stubRepo().actionByIdentifier(action.identifier())).isPresent().get().extracting(Action::status).isEqualTo(DONE);
	}

	private ActionRepository stubRepo() {
		return stubRepo;
	}
}
