package controllers.timeline;

import java.io.IOException;
import java.util.List;

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
 * Servlet implementation class TimelineServlet
 */
@WebServlet("/timeline")
public class TimelineServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TimelineServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Trainee login_trainee = (Trainee)request.getSession().getAttribute("login_trainee");

        int page;
        try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch(Exception e) {
            page = 1;
        }

        List<WorkoutReport> followReportList = em.createNamedQuery("getAllFollowReports", WorkoutReport.class)
                                                 .setParameter("trainee1", login_trainee)
                                                 .setFirstResult(15 * (page - 1))
                                                 .setMaxResults(15)
                                                 .getResultList();

        long followReportListCount = (long)em.createNamedQuery("getAllFollowReportsCount", Long.class)
                                            .setParameter("trainee1", login_trainee)
                                            .getSingleResult();

        em.close();

        request.setAttribute("page",page);
        request.setAttribute("followReportList", followReportList);
        request.setAttribute("followReportListCount", followReportListCount);

        if(request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/timeline/timeline.jsp");
        rd.forward(request, response);

    }

}
