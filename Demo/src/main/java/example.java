// Import Required Java Libraries
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

// extends HttpServlet Class
public class example extends HttpServlet {

    private String message;

    public void init() throws ServletException {
        // Do required Initialization
        message = "Hello World";
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //Set Response content type
        resp.setContentType("text/html");

        // Actual logic goes here.
        PrintWriter out = resp.getWriter();
        out.println("<h1>" + message + "</h1>");
    }

    public void destroy() {
        // do nothing
    }
}
