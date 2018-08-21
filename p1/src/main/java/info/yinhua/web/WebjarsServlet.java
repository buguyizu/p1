package info.yinhua.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.webjars.RequireJS;

import info.yinhua.core.CommonConst;

// https://blog.csdn.net/rickiyeat/article/details/77543445
// https://www.webjars.org/documentation#springmvc
// https://www.jamesward.com/2014/02/19/official-support-for-requirejs-in-webjars
@WebServlet({ "/webjarsjs" })
// @WebFilter("/webjars*")
public class WebjarsServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 189345318956750300L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getRequestURI().contains("webjarsjs")) {

			resp.setContentType("application/javascript;charset=UTF-8");
			String js = RequireJS.getSetupJavaScript(CommonConst.CONTEXT + "/webjars/");
			// 因为dataTables.bootstrap.js中定义了需要datatables.net
			js = js.replace("\"datatables\"", "\"datatables.net\"");
			resp.getWriter().print(js);
		} else {
			resp.sendRedirect(req.getRequestURI());
		}
	}
}
