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

import models.Trainee;
import models.WorkoutReport;
import models.validators.WorkoutReportValidator;
import utils.DBUtil;

/**
 * Servlet implementation class WorkoutReportCreateServlet
 */
@WebServlet("/workoutreports/create")
public class WorkoutReportCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public WorkoutReportCreateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            WorkoutReport wr = new WorkoutReport();

            wr.setTrainee((Trainee)request.getSession().getAttribute("login_trainee"));

            Date report_date = new Date(System.currentTimeMillis());
            String rd_str = request.getParameter("report_date");
            if(rd_str != null && !rd_str.equals("")) {
                report_date = Date.valueOf(request.getParameter("report_date"));
            }
            wr.setReport_date(report_date);

            wr.setBody_part(request.getParameter("body_part"));
            wr.setMenu(request.getParameter("menu"));
            wr.setRep(Integer.parseInt(request.getParameter("reps")));
            wr.setSets(Integer.parseInt(request.getParameter("sets")));
            wr.setIntervals(Integer.parseInt(request.getParameter("intervals")));
            wr.setReview(request.getParameter("review"));

            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            wr.setCreated_at(currentTime);
            wr.setUpdated_at(currentTime);

            List<String> errors = WorkoutReportValidator.validate(wr);
            if(errors.size() > 0) {
                em.close();

                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("errors", errors);
                request.setAttribute("workoutreport", wr);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/workoutreports/new.jsp");
                rd.forward(request, response);
            } else {
                em.getTransaction().begin();
                em.persist(wr);
                em.getTransaction().commit();
                em.close();

                request.getSession().setAttribute("flush", "投稿完了");
                response.sendRedirect(request.getContextPath() + "/workoutreports/index");
            }

        }
    }
}
