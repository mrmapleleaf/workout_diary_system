package controllers.likes;

import java.io.IOException;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Likes;
import models.Trainee;
import models.WorkoutReport;
import utils.DBUtil;

/**
 * Servlet implementation class LikesCreateServlet
 */
@WebServlet("/likes/create")
public class LikesCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LikesCreateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        WorkoutReport wr = em.find(WorkoutReport.class, Integer.parseInt(request.getParameter("id")));
        Likes l = new Likes();

        l.setTrainee((Trainee)(request.getSession().getAttribute("login_trainee")));
        l.setWorkoutreport(wr);
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        l.setCreated_at(currentTime);
        l.setUpdated_at(currentTime);

        em.getTransaction().begin();
        em.persist(l);
        em.getTransaction().commit();
        em.close();

        request.getSession().setAttribute("flush", "いいねしました");
        response.sendRedirect(request.getContextPath() + "/workoutreports/index");



    }

}
