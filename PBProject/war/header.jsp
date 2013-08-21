<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="header">
		<div id="top">
			<div class="left">
				<p>Welcome, <strong>${userName}</strong> [ <a href="logout.do">logout</a> ]</p>
			</div>
			<div class="right">
				<div class="align-right">
					<p>Last login: <strong>${loginTime}</strong></p>
				</div>
			</div>
		</div>
		<div id="nav">
			<ul>
				<li class="upp"><a href="helpRequest.jsp">In-store Help</a>					
				</li>
				<c:if test="${userName == 'manager'}">
        <li class="upp"><a href="statistics.jsp">Statistics</a>
          <ul>
            <li>&#8250; <a href="statistics.jsp">Help Feature Analysis</a></li>
            <li>&#8250; <a href="staff_evaluation.jsp">Staff Evaluation</a></li>
            <li>&#8250; <a href="customer_analysis.jsp">Customer Analysis</a></li>
            <li>&#8250; <a href="other_graphics.jsp">Other Graphics</a></li>
          </ul>
        </li>
        </c:if>
        
        <c:if test="${userName == 'employee'}">
        <li class="upp"><a href="employee_details.jsp">My performance</a>
        </li>
        </c:if>
				<li class="upp"><a href="#">Settings</a>
					<ul>
						<li>&#8250; <a href="">Site Configuration</a></li>
						<li>&#8250; <a href="">Contact Form</a></li>
					</ul>
				</li>
			</ul>
		</div>
	</div>