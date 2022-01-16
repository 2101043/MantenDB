package servlet;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import model.UserEntryCheck;

/**
 * Servlet implementation class UserEntry
 */
@WebServlet("/UserEntry")
public class UserEntry extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/UserEntry.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub



		if(request.getParameter("name") == "") {
			request.setAttribute("message", "ユーザ名を入力してください");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/UserEntry.jsp");
			dispatcher.forward(request, response);
		}

		if(request.getParameter("id") == "") {
			request.setAttribute("message", "UserIDを入力してください");RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/UserEntry.jsp");
			dispatcher.forward(request, response);
		}

		if(request.getParameter("pass") == "") {
			request.setAttribute("message", "Passwordを入力してください");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/UserEntry.jsp");
			dispatcher.forward(request, response);
		}

		if(request.getParameter("pass2") == "") {
			request.setAttribute("message", "Password（再入力）を入力してください");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/UserEntry.jsp");
			dispatcher.forward(request, response);
		}

		String name = request.getParameter("name");
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		String pass2 = request.getParameter("pass2");

		Pattern p = Pattern.compile("[a-z0-9._]{1,15}");
		Matcher matcher = p.matcher(id);
		if (matcher.find()) {
		} else {
			request.setAttribute("message", "UserIDには半角英数字と【_】のみ使用可能です");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/UserEntry.jsp");
			dispatcher.forward(request, response);
		}


		User user = new User(id,name,pass,pass2);
		UserEntryCheck bn = new UserEntryCheck();
		String total = bn.FindByCheck(user);



		if(total == "OK") {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/UserEntryOK.jsp");
			dispatcher.forward(request, response);
		}else if(total == "err"){
			request.setAttribute("message", "PasswordとPassword（再入力）には同じものを入力してください");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/UserEntry.jsp");
			dispatcher.forward(request, response);
		}else if(total == "err2") {
			request.setAttribute("message", "入力されたUserIDは登録済みです");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/UserEntry.jsp");
			dispatcher.forward(request, response);
		}

	}

}
