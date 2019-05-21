package pavel.demo.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
	public String addEditItem(Model model) {
		TodoItem todoItem = new TodoItem("", "", LocalDate.now());
		model.addAttribute(AttributeNames.TODO_ITEM, todoItem);
		return ViewNames.ADD_ITEM;
	}

	@PostMapping(Mappings.ADD_ITEM)
	public String processItem(@ModelAttribute(AttributeNames.TODO_ITEM) TodoItem todoItem) {
		log.info("todoItem from form = {}", todoItem);
		todoItemService.addItem(todoItem);
		return "redirect:/" + Mappings.ITEMS;
	}
}
