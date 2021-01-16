package controllers.workoutreports;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.WorkoutReport;
import utils.DBUtil;

/**
 * Servlet implementation class WorkoutReportIndexServlet
 */
@WebServlet("/workoutreports/index")
public class WorkoutReportIndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public WorkoutReportIndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        int page;
        try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch(Exception e) {
            page = 1;
        }
        List<WorkoutReport> workoutreports = em.createNamedQuery("getAllReports", WorkoutReport.class)
                                 .setFirstResult(15 * (page - 1))
                                 .setMaxResults(15)
                                 .getResultList();

        long workoutreports_count = (long)em.createNamedQuery("getAllReportsCount", Long.class)
                                      .getSingleResult();

        em.close();

        request.setAttribute("workoutreports", workoutreports);
        request.setAttribute("workoutreports_count", workoutreports_count);
        request.setAttribute("page", page);
        if(request.getSession().getAttribute("_flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/workoutreports/index.jsp");
        rd.forward(request, response);
    }

}
