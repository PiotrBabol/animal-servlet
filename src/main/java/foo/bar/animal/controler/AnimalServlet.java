package foo.bar.animal.controler;

import foo.bar.animal.model.Animal;
import foo.bar.animal.model.AnimalKingdom;
import foo.bar.animal.service.AnimalService;
import org.apache.log4j.Logger;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static foo.bar.animal.controler.AnimalServletConstant.*;


@WebServlet(name = "Animal", urlPatterns = {"/", "/add", "/list", "/det", "/remove", "/edit", "/error"})
public class AnimalServlet extends HttpServlet {

    private Logger log = Logger.getLogger(AnimalServlet.class);
    private AnimalService animalService;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType(TEXT_PLAIN);
        switch (request.getServletPath()) {
            case ADD_PATH:
                request.setAttribute(ANIMAL_LIST, animalService.findAll());
                request.getRequestDispatcher(ANIMAL_FORM_JSP).forward(request, response);
                break;
            case DISPLAY_PATH:
                request.setAttribute(ANIMAL_LIST, animalService.findAll());
                request.getRequestDispatcher(ANIMAL_LIST_JSP).forward(request, response);
                break;
            case DETAILS_PATH:
                String detId = request.getParameter(ID);
                request.setAttribute(ANIMAL_DETAILS, animalService.findOne(detId));
                request.getRequestDispatcher(ANIMAL_DETAILS_JSP).forward(request, response);
                break;
            case REMOVE_PATH:
                String removeId = request.getParameter(ID);
                request.setAttribute(ID, removeId);
                request.setAttribute(ANIMAL_REMOVE, animalService.findOne(removeId));
                request.getRequestDispatcher(ANIMAL_REMOVE_JSP).forward(request, response);
                break;
            case EDIT_PATH:
                removeId = request.getParameter(ID);
                if (!animalService.findOne(removeId).isEditeble()) {
                    response.sendRedirect(ANIMAL_ERROR_PATH);
                }else {
                    request.setAttribute(ID, removeId);
                    request.setAttribute(ANIMAL_EDIT, animalService.findOne(removeId));
                    request.getRequestDispatcher(ANIMAL_FORM_JSP).forward(request, response);
                }
                break;
            case ERROR_PATH:
                request.getRequestDispatcher(ANIMAL_ERROR_JSP).forward(request, response);
                break;
            default:
                request.getRequestDispatcher(INDEX_JSP).forward(request, response);
                break;
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getServletPath().equals(REMOVE_PATH)) {
            animalRemove(request, response);
        } else {
            try {
                animalForm(request, response);
            } catch (NamingException e) {
                log.error("Naming exception", e);
            }
        }
    }

    private void animalForm(HttpServletRequest request, HttpServletResponse response) throws IOException, NamingException {
        String name = request.getParameter(NAME).toLowerCase();
        String kingdom = request.getParameter(KINGDOM);
        String details = request.getParameter(ANIMAL_DESCRIPTION);
        String animalToEdit = request.getParameter(ID);
        if (name.equals(EMPTY_STRING)) {
            response.sendRedirect(ANIMAL_ERROR_PATH);
        } else {
            if (request.getParameter(ID) == null || request.getParameter(ID).equals("")) {
                animalService.add(new Animal(name, AnimalKingdom.valueOf(kingdom), details));
            } else {
                animalService.remove(animalToEdit);
                animalService.add(new Animal(animalToEdit, name, AnimalKingdom.valueOf(kingdom), details));
            }
            response.sendRedirect(ANIMAL_LIST_PATH);
        }
    }

    private void animalRemove(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String animalToRemove = request.getParameter(ANIMAL_TO_REMOVE_ID);
        animalService.remove(animalToRemove);
        response.sendRedirect(ANIMAL_LIST_PATH);
    }

    @Override
    public void init() {
        log.info("Servlet " + this.getServletName() + " has started");
        animalService = new AnimalService();

    }

    @Override
    public void destroy() {
        log.info("Servlet " + this.getServletName() + " has been destroyed");

    }

}

