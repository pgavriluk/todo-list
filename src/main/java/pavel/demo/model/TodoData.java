package pavel.demo.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

import lombok.NonNull;

public class TodoData {

	// fields
	private static int idValue = 1;

	private final List<TodoItem> items = new ArrayList<>();

	// constructors


	public TodoData() {
		// add some dummy data
		addItem(new TodoItem("first", "first details", LocalDate.now()));
		addItem(new TodoItem("second", "second details", LocalDate.now()));
		addItem(new TodoItem("third", "third details", LocalDate.now()));
	}

	// public methods
	public List<TodoItem> getItems(){
		return Collections.unmodifiableList(items);
	}

	public void addItem(@NonNull TodoItem itemToAdd){
		itemToAdd.setId(idValue);
		items.add(itemToAdd);
		idValue++;
	}

	public void removeItem(int id){
		ListIterator<TodoItem> itemListIterator = items.listIterator();

		while(itemListIterator.hasNext()){
			TodoItem item = itemListIterator.next();

			if(item.getId() == id){
				itemListIterator.remove();
				break;
			}
		}
	}

	public TodoItem getItem(int id){
		for(TodoItem item: items){
			if(item.getId() == id){
				return item;
			}
		}

		return null;
	}

	public void updateItem(@NonNull TodoItem itemToUpdate){

		ListIterator<TodoItem> itemListIterator = items.listIterator();

		while(itemListIterator.hasNext()){
			TodoItem item = itemListIterator.next();

			if(item.equals(itemToUpdate)){
				itemListIterator.set(itemToUpdate);
				break;
			}
		}
	}
}
