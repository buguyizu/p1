package info.yinhua.core.web.tag;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class NavPathTag extends TagSupport {

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.jsp.tagext.TagSupport#doStartTag()
	 */
	@Override
	public int doStartTag() throws JspException {
		
		try {
			HttpServletRequest request = (HttpServletRequest) pageContext
					.getRequest();
			JspWriter out = pageContext.getOut();

			if ("".compareToIgnoreCase("filename") == 0)
				out.print(request.getServletPath());
			else
				out.print(new Date());

		} catch (IOException e) {
			throw new JspTagException(e.getMessage());
		}

		return SKIP_BODY;
	}

}
