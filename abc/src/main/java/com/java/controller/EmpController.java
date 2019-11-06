package com.java.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.java.bean.Emp;
import com.java.dao.EmpDao;

@Controller
public class EmpController {

	@Autowired
	EmpDao dao;

	@RequestMapping("/empform")
	public String showform(Model m) {
		m.addAttribute("command", new Emp());
		
		return "empform";
	}

	@RequestMapping(value = "/save/emp", method = RequestMethod.POST)
	public String save( @Valid @ModelAttribute("emp") Emp emp,BindingResult br) {
		//if(br.hasErrors())
		//{
	    // return "empform";
		//}else
		//{
			dao.save(emp);
			
			return "redirect:/viewemp";
		//}
		
		
		//dao.save(emp);
		
		//return "redirect:/viewemp";
	}

	@RequestMapping("/viewemp")
	public String viewemp(Model m) {
		List<Emp> list = dao.getEmployees();
		m.addAttribute("list", list);
 		return "viewemp";
	}

	@RequestMapping(value = "/edit/emp/{id}")
	public String edit(@PathVariable int id, Model m) {
		Emp emp = dao.getEmpById(id);
		m.addAttribute("command", emp);
		return "empeditform";
	}

	@RequestMapping(value = "/edit/save", method = RequestMethod.POST)
	public String editsave(@ModelAttribute("emp") Emp emp) {
 		dao.update(emp);
		return "redirect:/viewemp";
	}

	@RequestMapping(value = "/delete/emp/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable int id) {
		dao.delete(id);
		return "redirect:/viewemp";
	}

}
