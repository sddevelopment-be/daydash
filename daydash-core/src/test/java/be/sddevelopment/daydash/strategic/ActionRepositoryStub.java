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

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import be.sddevelopment.daydash.core.shared.FailureToPersist;
import be.sddevelopment.daydash.core.strategic.Action;
import be.sddevelopment.daydash.core.strategic.ActionRepository;

public class ActionRepositoryStub implements ActionRepository {

	private final Set<Action> actions = new HashSet<>();

	@Override
	public Optional<Action> actionByIdentifier(String identifier) {
		return actions.stream().filter(action -> identifier.equals(action.identifier())).findFirst();
	}

	@Override
	public Action save(Action toSave) throws FailureToPersist {
		if (toSave != null) {
			this.actions.add(toSave);
		}
		return toSave;
	}

}
