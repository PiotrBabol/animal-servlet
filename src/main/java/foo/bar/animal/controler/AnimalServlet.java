package foo.bar.animal.controler;

import foo.bar.animal.model.Animal;
import foo.bar.animal.model.AnimalKingdom;
import foo.bar.animal.dbConnetion.ConnectingToSqlite;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Animal", urlPatterns = {"/", "/add", "/list", "/det", "/remove", "/edit", "/error"})
public class AnimalServlet extends HttpServlet {

    private static final String TEXT_PLAIN = "text/plain;charset=UTF-8";
    private static final String EMPTY_STRING = "";

    private static final String ANIMAL_ERROR_PATH = "/Animal/error";
    public static final String ANIMAL_LIST_PATH = "/Animal/list";
    private static final String SERVLET_PATH = "/Animal";
    private static final String REMOVE_PATH = "/remove";
    private static final String DISPLAY_PATH = "/list";
    private static final String ERROR_PATH = "/error";
    private static final String DETAILS_PATH = "/det";
    private static final String EDIT_PATH = "/edit";
    private static final String ADD_PATH = "/add";

    private static final String ANIMAL_DETAILS_JSP = "animal-details.jsp";
    private static final String ANIMAL_REMOVE_JSP = "animal-remove.jsp";
    private static final String ANIMAL_ERROR_JSP = "animal-error.jsp";
    private static final String ANIMAL_LIST_JSP = "animal-list.jsp";
    private static final String ANIMAL_FORM_JSP = "animal-form.jsp";
    private static final String INDEX_JSP = "index.jsp";

    private static final String ANIMAL_DESCRIPTION = "animalDescription";
    private static final String ANIMAL_TO_REMOVE_ID = "animalToRemoveId";
    private static final String ANIMAL_DETAILS = "animalDetails";
    private static final String ANIMAL_REMOVE = "animalRemove";
    private static final String ANIMAL_LIST = "animalList";
    private static final String ANIMAL_EDIT = "animalEdit";
    private static final String KINGDOM = "kingdom";
    private static final String NAME = "animalName";
    private static final String ID = "id";

    private ConnectingToSqlite connectingToSqlite;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType(TEXT_PLAIN);
        switch (request.getServletPath()) {
            case ADD_PATH:
                try {
                    request.setAttribute(ANIMAL_LIST, connectingToSqlite.findAll());
                } catch (NamingException e) {
                    e.printStackTrace();
                }
                request.getRequestDispatcher(ANIMAL_FORM_JSP).forward(request, response);
                break;
            case DISPLAY_PATH:
                try {
                    request.setAttribute(ANIMAL_LIST, connectingToSqlite.findAll());
                } catch (NamingException e) {
                    e.printStackTrace();
                }
                request.getRequestDispatcher(ANIMAL_LIST_JSP).forward(request, response);
                break;
            case DETAILS_PATH:
                String detId = request.getParameter(ID);
                try {
                    request.setAttribute(ANIMAL_DETAILS, connectingToSqlite.findOne(detId));
                } catch (NamingException e) {
                    e.printStackTrace();
                }
                request.getRequestDispatcher(ANIMAL_DETAILS_JSP).forward(request, response);
                break;
            case REMOVE_PATH:
                String removeId = request.getParameter(ID);
                request.setAttribute(ID, removeId);
                try {
                    request.setAttribute(ANIMAL_REMOVE, connectingToSqlite.findOne(removeId));
                } catch (NamingException e) {
                    e.printStackTrace();
                }
                request.getRequestDispatcher(ANIMAL_REMOVE_JSP).forward(request, response);
                break;
            case EDIT_PATH:
                removeId = request.getParameter(ID);
                request.setAttribute(ID, removeId);
                try {
                    request.setAttribute(ANIMAL_EDIT, connectingToSqlite.findOne(removeId));
                } catch (NamingException e) {
                    e.printStackTrace();
                }

                request.getRequestDispatcher(ANIMAL_FORM_JSP).forward(request, response);
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
                e.printStackTrace();
            }
        }
    }

    private void animalForm(HttpServletRequest request, HttpServletResponse response) throws IOException, NamingException {
        String name = request.getParameter(NAME).toLowerCase();
        if (name.equals(EMPTY_STRING)) {
            response.sendRedirect(ANIMAL_ERROR_PATH);
        } else {
            String kingdom = request.getParameter(KINGDOM);
            String details = request.getParameter(ANIMAL_DESCRIPTION);
            if (request.getParameter(ID) == null || request.getParameter(ID).equals("")) {
                connectingToSqlite.add(new Animal(name, AnimalKingdom.valueOf(kingdom), details));
            } else {
                String animalToEdit = request.getParameter(ID);
                connectingToSqlite.remove(animalToEdit);
                connectingToSqlite.add(new Animal(animalToEdit, name, AnimalKingdom.valueOf(kingdom), details));
            }
            response.sendRedirect(ANIMAL_LIST_PATH);
        }
    }

    private void animalRemove(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String animalToRemove = request.getParameter(ANIMAL_TO_REMOVE_ID);
        try {
            connectingToSqlite.remove(animalToRemove);
        } catch (NamingException e) {
            e.printStackTrace();
        }
        response.sendRedirect(ANIMAL_LIST_PATH);
    }

    @Override
    public void init() {
        System.out.println("Servlet " + this.getServletName() + " has started");
        try {
            connectingToSqlite = new ConnectingToSqlite();
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {

    }

}

