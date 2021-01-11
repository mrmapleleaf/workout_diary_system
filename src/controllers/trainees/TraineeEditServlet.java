package controllers.trainees;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Trainee;
import utils.DBUtil;

/**
 * Servlet implementation class TraineeEditServlet
 */
@WebServlet("/trainees/edit")
public class TraineeEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TraineeEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Trainee t = em.find(Trainee.class, Integer.parseInt(request.getParameter("id")));

        em.close();

        request.setAttribute("trainee", t);
        request.setAttribute("_token", request.getSession().getId());
        request.getSession().setAttribute("trainee_id", t.getId());

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/trainees/edit.jsp");
        rd.forward(request, response);

    }

}
