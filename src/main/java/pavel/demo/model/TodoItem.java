package pavel.demo.model;

import java.time.LocalDate;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "id")
public class TodoItem {

	// fileds
	private int id;
	private String title;
	private String details;
	private LocalDate deadline;

	// constructors
	public TodoItem(String title, String details, LocalDate deadline) {
		this.title = title;
		this.details = details;
		this.deadline = deadline;
	}
}
