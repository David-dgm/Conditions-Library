package org.dgm.conditional.recursive.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ThirdLevelObject {

	String stringValue;

	Integer integerValue;
	Long longValue;
	BigDecimal bigDecimalValue;

	List<String> listValue;
	Set<String> setValue;
	Map<String, String> mapValue;

}
