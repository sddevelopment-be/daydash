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
