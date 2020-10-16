package com.hqs.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hqs.domain.Category;
import com.hqs.service.CategoryService;
import com.hqs.serviceImp.CategoryServiceImp;

@WebServlet("/category/*")
public class CateGoryServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	private CategoryService service = new CategoryServiceImp();
	
	public void findAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Category> list = service.findAll();
		writeValue(list, response);
	}
}