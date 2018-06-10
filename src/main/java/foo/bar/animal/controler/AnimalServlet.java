package foo.bar.animal.controler;

import foo.bar.animal.model.Animal;
import foo.bar.animal.model.AnimalKingdom;
import foo.bar.animal.service.AnimalService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Animal", urlPatterns = {"/", "/add", "/list", "/det"})
public class AnimalServlet extends HttpServlet {

    private static final String TEXT_PLAIN = "text/plain;charset=UTF-8";
    private static final String ANIMAL_LIST = "animalList";
    private static final String ANIMAL_FORM_JSP = "animal-form.jsp";
    private static final String ANIMAL_LIST_JSP = "animal-list.jsp";
    private static final String ANIMAL_DETAILS_JSP = "animal-details.jsp";
    private static final String INDEX_JSP = "index.jsp";
    private static final String NAME = "animalName";
    private static final String KINGDOM = "kingdom";
    private static final String ANIMAL = "Animal";
    public static final String ID = "id";

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType(TEXT_PLAIN);
        switch (request.getServletPath()) {
            case "/add":
                request.setAttribute(ANIMAL_LIST, AnimalService.animalList);
                request.getRequestDispatcher(ANIMAL_FORM_JSP).forward(request, response);
                break;
            case "/list":
                request.getRequestDispatcher(ANIMAL_LIST_JSP).forward(request, response);
                break;
            case "/det":

                request.getRequestDispatcher(ANIMAL_DETAILS_JSP).forward(request, response);
                break;
            default:
                request.getRequestDispatcher(INDEX_JSP).forward(request, response);
                break;
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter(NAME);
        String kingdom = request.getParameter(KINGDOM);
        AnimalService.animalList.add(new Animal(name, AnimalKingdom.valueOf(kingdom)));
        response.sendRedirect(ANIMAL);
    }

    @Override
    public void init() {
        System.out.println("Servlet " + this.getServletName() + " has started");
    }

    @Override
    public void destroy() {
        System.out.println("Servlet " + this.getServletName() + " has stopped");
    }

}

