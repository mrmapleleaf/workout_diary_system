package controllers.trainees;

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
import utils.DBUtil;

/**
 * Servlet implementation class TraineeIndexServlet
 */
@WebServlet("/trainees/index")
public class TraineeIndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TraineeIndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        EntityManager em = DBUtil.createEntityManager();

        int page = 1;
        try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch(NumberFormatException e) { }
        List<Trainee> trainees = em.createNamedQuery("getAllTrainees", Trainee.class)
                                    .setFirstResult(15 * (page - 1))
                                    .setMaxResults(15)
                                    .getResultList();

        long trainees_count = em.createNamedQuery("getAllTraineesCount", Long.class)
                                .getSingleResult();

        em.close();

        request.setAttribute("trainees", trainees);
        request.setAttribute("trainees_count", trainees_count);
        request.setAttribute("page", page);


        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/trainees/index.jsp");
        rd.forward(request, response);

    }

}
