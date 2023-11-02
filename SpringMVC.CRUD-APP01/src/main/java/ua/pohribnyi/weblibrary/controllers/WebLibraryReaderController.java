package ua.pohribnyi.weblibrary.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import ua.pohribnyi.weblibrary.dao.BookDAO;
import ua.pohribnyi.weblibrary.dao.ReaderDAO;
import ua.pohribnyi.weblibrary.model.Reader;
import ua.pohribnyi.weblibrary.util.ReaderValidator;

@Controller
@RequestMapping("/web-library/readers")
public class WebLibraryReaderController {

	private ReaderDAO readerDAO;
	private BookDAO bookDAO;
	private ReaderValidator readerValidator;

	@Autowired
	public WebLibraryReaderController(ReaderDAO readerDAO, BookDAO bookDAO, ReaderValidator readerValidator) {
		this.readerDAO = readerDAO;
		this.bookDAO = bookDAO;
		this.readerValidator = readerValidator;
	}

	@GetMapping()
	public String index(Model model) {
		model.addAttribute("readers", readerDAO.index());
		return "readers/index";
	}

	@GetMapping("/{id}")
	public String show(@PathVariable("id") int id, Model model) {
		model.addAttribute("reader", readerDAO.show(id));
		model.addAttribute("books", bookDAO.showReaderBooks(id));
		return "readers/show";
	}

	@GetMapping("/new")
	public String create(@ModelAttribute("reader") Reader reader) {
		return "readers/new";
	}

	@PostMapping()
	public String save(@ModelAttribute("reader") @Valid Reader reader, BindingResult bindingResult) {
		readerValidator.validate(reader, bindingResult);

		if (bindingResult.hasErrors())
			return "readers/new";
		readerDAO.save(reader);
		return "redirect:/web-library/readers";
	}

	@GetMapping("/{id}/edit")
	public String edit(@PathVariable("id") int id, Model model) {
		model.addAttribute("reader", readerDAO.show(id));
		return "readers/edit";
	}

	@PatchMapping("/{id}")
	public String update(@ModelAttribute("reader") @Valid Reader reader, BindingResult bindingResult,
			@PathVariable("id") int id) {

		readerValidator.validate(reader, bindingResult);

		if (bindingResult.hasErrors())
			return "readers/edit";
		readerDAO.update(id, reader);
		return "redirect:/web-library/readers";
	}

	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") int id) {
		readerDAO.delete(id);
		return "redirect:/web-library/readers";
	}

}
