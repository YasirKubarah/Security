package servlets;

import com.google.appengine.repackaged.com.google.api.client.http.HttpStatusCodes;
import com.google.gson.Gson;
import sec.model.Token;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "authenticate", urlPatterns = "/authenticate")

public class AuthenticateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter out = resp.getWriter();

        String token = req.getParameter("token");

        Gson gson = new Gson();
        String json = "";
        if (Token.tokenExists(token)==false)
        {
            json = gson.toJson(HttpStatusCodes.STATUS_CODE_NOT_FOUND);
            out.print (json);
        }
        else
        {

            out.print( gson.toJson(Token.getEmailOfToken(token)));
        }
    }
}
