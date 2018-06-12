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

@WebServlet(name = "Animal", urlPatterns = {"/", "/add", "/list", "/det", "/remove", "/edit"})
public class AnimalServlet extends HttpServlet {

    private static final String TEXT_PLAIN = "text/plain;charset=UTF-8";
    private static final String ANIMAL_LIST = "animalList";
    private static final String ANIMAL_FORM_JSP = "animal-form.jsp";
    private static final String ANIMAL_LIST_JSP = "animal-list.jsp";
    private static final String ANIMAL_DETAILS_JSP = "animal-details.jsp";
    private static final String INDEX_JSP = "index.jsp";
    private static final String NAME = "animalName";
    private static final String KINGDOM = "kingdom";
    private static final String ANIMAL = "/Animal";
    private static final String ID = "id";
    private static final String ANIMAL_REMOVE_JSP = "animal-remove.jsp";
    private static final String ANIMAL_DESCRIPTION = "animalDescription";
    private static final String ANIMAL_TO_REMOVE_ID = "animalToRemoveId";

    private AnimalService animalService;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType(TEXT_PLAIN);
        switch (request.getServletPath()) {
            case "/add":
                request.setAttribute(ANIMAL_LIST,animalService.findAll());
                request.getRequestDispatcher(ANIMAL_FORM_JSP).forward(request, response);
                break;
            case "/list":
                request.setAttribute(ANIMAL_LIST,animalService.findAll());
                request.getRequestDispatcher(ANIMAL_LIST_JSP).forward(request, response);
                break;
            case "/det":
                request.getRequestDispatcher(ANIMAL_DETAILS_JSP).forward(request, response);
                break;
            case "/remove":
                String removeId = request.getParameter(ID);
                request.setAttribute(ID, removeId);
                request.getRequestDispatcher(ANIMAL_REMOVE_JSP).forward(request, response);
                break;
            case "/edit":
                removeId = request.getParameter(ID);
                request.setAttribute(ID, removeId);
                request.getRequestDispatcher(ANIMAL_FORM_JSP).forward(request, response);
                break;
            default:
                request.getRequestDispatcher(INDEX_JSP).forward(request, response);
                break;
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getServletPath().equals("/remove")) {
            animalRemove(request, response);
        } else {
            animalForm(request, response);
        }
    }

    private void animalForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter(NAME).toLowerCase();
        String kingdom = request.getParameter(KINGDOM);
        String details = request.getParameter(ANIMAL_DESCRIPTION);
        if (request.getParameter(ID) == null || request.getParameter(ID).equals("")) {
            animalService.add(new Animal(name, AnimalKingdom.valueOf(kingdom), details));
        } else {
            String animalToEdit = request.getParameter(ID);
            Animal temp = animalService.findAll().get(Integer.valueOf(animalToEdit));
            temp.setDetails(details);
            temp.setName(name);
            temp.setKingdom(AnimalKingdom.valueOf(kingdom));
            animalService.set(Integer.valueOf(animalToEdit), temp);
        }
        response.sendRedirect(ANIMAL);
    }

    private void animalRemove(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String animalToRemove = request.getParameter(ANIMAL_TO_REMOVE_ID);
        animalService.remove(Integer.valueOf(animalToRemove));
        response.sendRedirect(ANIMAL);
    }

    @Override
    public void init() {
        System.out.println("Servlet " + this.getServletName() + " has started");
        animalService = new AnimalService();
    }

    @Override
    public void destroy() {
        System.out.println("Servlet " + this.getServletName() + " has stopped");
    }

}

