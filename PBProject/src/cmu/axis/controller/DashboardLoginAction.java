package cmu.axis.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

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
			
			
			if((form.getUserName().equals("manager") && form.getPassword().equals("axis")) 
					|| (form.getUserName().equals("employee") && form.getPassword().equals("axis"))) {
				session.setAttribute("userName",form.getUserName());
				session.setAttribute("password", form.getPassword());
				
				long start = System.currentTimeMillis() - 480000;

				DateFormat df = new SimpleDateFormat("MM-dd-yyyy HH:mm");
				df.setTimeZone(TimeZone.getTimeZone("GMT-4"));
				session.setAttribute("loginTime", df.format(new Date(start)));
				
				return "helpRequest.jsp";
			} 
			
			errors.add("Password is invalid.");
			return "login2.jsp";
			
			
		} catch (FormBeanException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "login2.jsp";
		}
		
	}

}
