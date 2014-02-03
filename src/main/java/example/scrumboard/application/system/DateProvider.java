package example.scrumboard.application.system;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class DateProvider {

	public Date currentDate() {
		return new Date();
	}

}
