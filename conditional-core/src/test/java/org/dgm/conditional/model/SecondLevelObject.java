package org.dgm.conditional.model;

import java.util.List;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class SecondLevelObject {

	ThirdLevelObject thirdLevelObjectInside;
	List<ThirdLevelObject> thirdLevelObjectInsideAsList;
}
