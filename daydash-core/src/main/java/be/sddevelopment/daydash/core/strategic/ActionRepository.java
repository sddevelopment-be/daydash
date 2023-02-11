package be.sddevelopment.daydash.core.strategic;

import java.util.Optional;

import be.sddevelopment.commons.validation.Fallible;
import be.sddevelopment.daydash.core.shared.FailureToPersist;

public interface ActionRepository {
	Optional<Action> actionByIdentifier(String identifier);

	Action save(Action toSave) throws FailureToPersist;
}
