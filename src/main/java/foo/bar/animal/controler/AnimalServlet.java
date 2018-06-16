package foo.bar.animal.controler;

import foo.bar.animal.model.Animal;
import foo.bar.animal.model.AnimalKingdom;
import foo.bar.animal.service.AnimalService;
import foo.bar.dbConnetion.ConnectingToSqlite;

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
    public static final String ANIMAL_DETAILS = "animalDetails";

//    private AnimalService animalService;
    private ConnectingToSqlite connectingToSqlite;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType(TEXT_PLAIN);
        switch (request.getServletPath()) {
            case "/add":
                try {
                    request.setAttribute(ANIMAL_LIST, connectingToSqlite.findAll());
                } catch (NamingException e) {
                    e.printStackTrace();
                }
                request.getRequestDispatcher(ANIMAL_FORM_JSP).forward(request, response);
                break;
            case "/list":
                try {
                    request.setAttribute(ANIMAL_LIST, connectingToSqlite.findAll());
                } catch (NamingException e) {
                    e.printStackTrace();
                }
                request.getRequestDispatcher(ANIMAL_LIST_JSP).forward(request, response);
                break;
            case "/det":
                String detId = request.getParameter(ID);
                try {
                    request.setAttribute(ANIMAL_DETAILS, connectingToSqlite.findOne(detId));
                } catch (NamingException e) {
                    e.printStackTrace();
                }
                request.getRequestDispatcher(ANIMAL_DETAILS_JSP).forward(request, response);
                break;
            case "/remove":
                String removeId = request.getParameter(ID);
                request.setAttribute(ID, removeId);
                try {
                    request.setAttribute("animalRemove", connectingToSqlite.findOne(removeId));
                } catch (NamingException e) {
                    e.printStackTrace();
                }
                request.getRequestDispatcher(ANIMAL_REMOVE_JSP).forward(request, response);
                break;
            case "/edit":
                removeId = request.getParameter(ID);
                request.setAttribute(ID, removeId);
                try {
                    request.setAttribute("animalEdit", connectingToSqlite.findOne(removeId));
                } catch (NamingException e) {
                    e.printStackTrace();
                }

                request.getRequestDispatcher(ANIMAL_FORM_JSP).forward(request, response);
                break;
            case "/error":
                request.getRequestDispatcher("animal-error.jsp").forward(request, response);
            default:
                request.getRequestDispatcher(INDEX_JSP).forward(request, response);
                break;
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getServletPath().equals("/remove")) {
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
        if (name.equals("")) {
            response.sendRedirect("/Animal/error");
        } else {
            String kingdom = request.getParameter(KINGDOM);
            String details = request.getParameter(ANIMAL_DESCRIPTION);
            if (request.getParameter(ID) == null || request.getParameter(ID).equals("")) {
                connectingToSqlite.add(new Animal(name, AnimalKingdom.valueOf(kingdom), details));
            } else {
                String animalToEdit = request.getParameter(ID);
                connectingToSqlite.remove(animalToEdit);
                connectingToSqlite.add(new Animal(animalToEdit,name,AnimalKingdom.valueOf(kingdom),details));
            }
            response.sendRedirect(ANIMAL);
        }
    }

    private void animalRemove(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String animalToRemove = request.getParameter(ANIMAL_TO_REMOVE_ID);
//        animalService.remove(animalToRemove);
        try {
            connectingToSqlite.remove(animalToRemove);
        } catch (NamingException e) {
            e.printStackTrace();
        }
        response.sendRedirect(ANIMAL);
    }

    @Override
    public void init() {
        System.out.println("Servlet " + this.getServletName() + " has started");
//        animalService = new AnimalService();
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

