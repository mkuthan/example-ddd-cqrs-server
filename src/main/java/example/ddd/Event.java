package example.ddd;

import java.io.Serializable;

public interface Event extends Serializable {

	String CREATED_AT_HEADER = "createdAt";
	String CREATED_BY_HEADER = "createdBy";

}