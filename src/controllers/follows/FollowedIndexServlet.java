package controllers.follows;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Follow;
import models.Trainee;
import utils.DBUtil;

/**
 * Servlet implementation class FollowedIndexServlet
 */
@WebServlet("/follows/followedIndex")
public class FollowedIndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FollowedIndexServlet() {
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

        List<Follow> followerList = em.createNamedQuery("getAllFollowed", Follow.class)
                                      .setParameter("trainee2", login_trainee)
                                      .setFirstResult(15 * (page - 1))
                                      .setMaxResults(15)
                                      .getResultList();

        long followerListCount = (long)em.createNamedQuery("getAllFollowedCount", Long.class)
                                   .setParameter("trainee2", login_trainee)
                                   .getSingleResult();

        request.setAttribute("page", page);
        request.setAttribute("followerList", followerList);
        request.setAttribute("followerListCount", followerListCount);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/followed/index.jsp");
        rd.forward(request, response);

    }

}
