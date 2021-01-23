package controllers.workoutreports;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Trainee;
import models.WorkoutReport;
import utils.DBUtil;

/**
 * Servlet implementation class WorkoutReportShowServlet
 */
@WebServlet("/workoutreports/show")
public class WorkoutReportShowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public WorkoutReportShowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        WorkoutReport wr = em.find(WorkoutReport.class, Integer.parseInt(request.getParameter("id")));
        Trainee login_trainee = (Trainee)request.getSession().getAttribute("login_trainee");

        long checkLikedAlready = (long)em.createNamedQuery("checkLikedAlready", Long.class)
                                   .setParameter("trainee", login_trainee)
                                   .setParameter("workoutreport", wr)
                                   .getSingleResult();

        long likesCount = (long)em.createNamedQuery("getAllLikesCount", Long.class)
                                  .setParameter("workoutreport", wr)
                                  .getSingleResult();

        em.close();

        request.setAttribute("workoutreport", wr);
        request.setAttribute("checkLikedAlready", checkLikedAlready);
        request.setAttribute("likesCount", likesCount);
        request.setAttribute("_token", request.getSession().getId());

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/workoutreports/show.jsp");
        rd.forward(request, response);
    }

}
