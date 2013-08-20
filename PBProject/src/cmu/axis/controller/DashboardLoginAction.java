package cmu.axis.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;




import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import cmu.axis.formbean.DashboardLoginForm;
import cmu.axis.model.Model;

public class DashboardLoginAction extends Action  {

	private FormBeanFactory<DashboardLoginForm> formBeanFactory = FormBeanFactory.getInstance(DashboardLoginForm.class);
	
	public DashboardLoginAction(Model model) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "dashboardLogin.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);

        DashboardLoginForm form;
		try {
			form = formBeanFactory.create(request);
			request.setAttribute("form",form);
			
			if (!form.isPresent()) {
				System.out.println("is return");
				return "login2.jsp";
			}
			
			// Any validation errors?
			errors.addAll(form.getValidationErrors());
			if (errors.size() != 0) {
				System.out.println("error return");
				return "login2.jsp";
			}
			
			HttpSession session = request.getSession();
			System.out.println("name "+form.getUserName()+"  password: "+form.getPassword());
			session.setAttribute("userName",form.getUserName());
			session.setAttribute("password", form.getPassword());
			
			if(session.getAttribute("userName").equals("manager") && session.getAttribute("password").equals("axis")) {
				return "helpRequest.jsp";
			} else if (session.getAttribute("userName").equals("employee") && session.getAttribute("password").equals("axis")) {
				return "helpRequest.jsp";
			}
			
			
			return "login2.jsp";
			
			
		} catch (FormBeanException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "login2.jsp";
		}
		
	}

}
