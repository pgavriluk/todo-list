package pavel.demo.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;
import pavel.demo.model.TodoData;
import pavel.demo.model.TodoItem;
import pavel.demo.service.TodoItemService;
import pavel.demo.util.AttributeNames;
import pavel.demo.util.Mappings;
import pavel.demo.util.ViewNames;

@Slf4j
@Controller
public class TodoItemController {

	// fields
	private final TodoItemService todoItemService;

	// constructors
	@Autowired
	public TodoItemController(TodoItemService todoItemService) {
		this.todoItemService = todoItemService;
	}

	// model attributes
	@ModelAttribute
	public TodoData todoData() {
		return todoItemService.getData();
	}

	// handler methods
	// http://localhost:8080/todo-list/items
	@GetMapping(Mappings.ITEMS)
	public String items() {
		return ViewNames.ITEMS_LIST;
	}

	// http://localhost:8080/todo-list/addItem
	@GetMapping(Mappings.ADD_ITEM)
	public String addEditItem(@RequestParam(required = false, defaultValue = "-1") int id, Model model) {
		log.info("editing item id = {}", id);
		TodoItem todoItem = todoItemService.getItem(id);

		if (todoItem == null) {
			todoItem = new TodoItem("", "", LocalDate.now());
		}

		model.addAttribute(AttributeNames.TODO_ITEM, todoItem);
		return ViewNames.ADD_ITEM;
	}

	@PostMapping(Mappings.ADD_ITEM)
	public String processItem(@ModelAttribute(AttributeNames.TODO_ITEM) TodoItem todoItem) {
		log.info("todoItem from form = {}", todoItem);

		if (todoItem.getId() == 0) {
			todoItemService.addItem(todoItem);
		} else{
			todoItemService.updateItem(todoItem);
		}

		return "redirect:/" + Mappings.ITEMS;
	}

	@GetMapping(Mappings.DELETE_ITEM)
	public String deleteItem(@ModelAttribute(AttributeNames.TODO_ITEM) @RequestParam int id) {
		log.info("Deleting item with id = {}", id);
		todoItemService.removeItem(id);
		return "redirect:/" + Mappings.ITEMS;
	}

	@GetMapping(Mappings.VIEW_ITEM)
	public String viewItem(@RequestParam int id, Model model){
		log.info("Displaying item with id = {}", id);
		TodoItem todoItem = todoItemService.getItem(id);
		model.addAttribute(AttributeNames.TODO_ITEM, todoItem);
		return ViewNames.VIEW_ITEM;
	}
}
