package example.scrumboard.application.system;

import org.springframework.stereotype.Component;

@Component
public class UserProvider {

	String currentUser() {
		return "mickey mouse";
	}

}
