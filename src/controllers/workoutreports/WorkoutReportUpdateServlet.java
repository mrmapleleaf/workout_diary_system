package controllers.workoutreports;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.WorkoutReport;
import models.validators.WorkoutReportValidator;
import utils.DBUtil;

/**
 * Servlet implementation class WorkoutReportUpdateServlet
 */
@WebServlet("/workoutreports/update")
public class WorkoutReportUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public WorkoutReportUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = (String)request.getParameter("_token");

        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            WorkoutReport wr = em.find(WorkoutReport.class , (Integer)(request.getSession().getAttribute("workoutreport_id")));

            wr.setReport_date(Date.valueOf(request.getParameter("report_date")));
            wr.setBody_part(request.getParameter("body_part"));
            wr.setMenu(request.getParameter("menu"));
            wr.setRep(Integer.parseInt(request.getParameter("rep")));
            wr.setSets(Integer.parseInt(request.getParameter("sets")));
            wr.setIntervals(Integer.parseInt(request.getParameter("intervals")));
            wr.setReview(request.getParameter("review"));

            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            wr.setUpdated_at(currentTime);

            List<String> errors = WorkoutReportValidator.validate(wr);
            if(errors.size() > 0) {
                em.close();

                request.setAttribute("errors", errors);
                request.setAttribute("workoutreport", wr);
                request.setAttribute("_token", request.getSession().getId());

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/workoutreports/edit.jsp");
                rd.forward(request, response);
            } else {
                em.getTransaction().begin();
                em.getTransaction().commit();
                em.close();

                request.getSession().setAttribute("flush", "更新完了");
                request.getSession().removeAttribute("workoutreport_id");
                response.sendRedirect(request.getContextPath() + "/workoutreports/index");
            }
        }
    }

}
