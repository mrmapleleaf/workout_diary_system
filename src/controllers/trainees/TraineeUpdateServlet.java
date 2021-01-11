package controllers.trainees;

import java.io.IOException;
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
import models.validators.TraineeValidator;
import utils.DBUtil;
import utils.EncryptUtil;

/**
 * Servlet implementation class TraineeUpdateServlet
 */
@WebServlet("/trainees/update")
public class TraineeUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TraineeUpdateServlet() {
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

            Trainee t = em.find(Trainee.class, (Integer)(request.getSession().getAttribute("trainee_id")));

            Boolean usernameDuplicateCheckFlag = true;
            if(t.getUsername().equals(request.getParameter("username"))) {
                usernameDuplicateCheckFlag = false;
            } else {
                t.setUsername(request.getParameter("username"));
            }

            Boolean passwordCheckFlag = true;
            String password = request.getParameter("password");
            if(password == null || password.equals("")) {
                passwordCheckFlag = false;
            } else {
                t.setPassword(EncryptUtil.getPasswordEncrypt(password, (String)this.getServletContext().getAttribute("pepper")));
            }

            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            t.setUpdated_at(currentTime);

            List<String> errors = TraineeValidator.validate(t, usernameDuplicateCheckFlag, passwordCheckFlag);
            if(errors.size() > 0) {
                em.close();

                request.setAttribute("trainee", t);
                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/trainees/edit.jsp");
                rd.forward(request, response);

            } else {

                em.getTransaction().begin();
                em.getTransaction().commit();
                em.close();

                request.getSession().setAttribute("flush", "更新完了");
                request.getSession().removeAttribute("trainee_id");
                response.sendRedirect(request.getContextPath() + "/trainees/index");
            }
        }
    }
}
