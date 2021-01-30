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
 * Servlet implementation class TraineeShowServlet
 */
@WebServlet("/trainees/show")
public class TraineeShowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TraineeShowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Trainee t = em.find(Trainee.class, Integer.parseInt(request.getParameter("id")));
        Trainee trainee1 = (Trainee) request.getSession().getAttribute("login_trainee");


        long checkFollowedAlready = (long)em.createNamedQuery("checkFollowedAlready", Long.class)
                                      .setParameter("trainee1", trainee1)
                                      .setParameter("trainee2", t)
                                      .getSingleResult();

        em.close();

        if(request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }

        request.setAttribute("trainee", t);
        request.setAttribute("checkFollowedAlready", checkFollowedAlready);
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/trainees/show.jsp");
        rd.forward(request, response);
    }

}
