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
 * Servlet implementation class WorkoutReportEditServlet
 */
@WebServlet("/workoutreports/edit")
public class WorkoutReportEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public WorkoutReportEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        WorkoutReport wr = em.find(WorkoutReport.class, Integer.parseInt(request.getParameter("id")));
        em.close();

        Trainee login_trainee = (Trainee)request.getSession().getAttribute("login_trainee");
        if(wr != null && login_trainee.getId() == wr.getTrainee().getId()) {
            request.setAttribute("workoutreport", wr);
            request.setAttribute("_token", request.getSession().getId());
            request.getSession().setAttribute("workoutreport_id", wr.getId());
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/workoutreports/edit.jsp");
        rd.forward(request, response);
    }

}
