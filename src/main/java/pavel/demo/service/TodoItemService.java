package pavel.demo.service;

import java.util.List;

import pavel.demo.model.TodoData;
import pavel.demo.model.TodoItem;

public interface TodoItemService {

	void addItem(TodoItem itemToAdd);

	void removeItem(int id);

	TodoItem getItem(int id);

	void updateItem(TodoItem itemToUpdate);

	TodoData getData();
}
