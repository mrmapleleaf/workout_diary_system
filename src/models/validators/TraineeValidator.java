package models.validators;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import models.Trainee;
import utils.DBUtil;

public class TraineeValidator {
    public static List<String>  validate(Trainee t, Boolean usernameDupulicateCheckFlag, Boolean passwordCheckFlag) {
        List<String> errors = new ArrayList<>();

        String username_error = validateUsername(t.getUsername(), usernameDupulicateCheckFlag);
        if(!username_error.equals("")) {
            errors.add(username_error);
        }

        String password_error = validatePassword(t.getPassword(), passwordCheckFlag);
        if(!password_error.equals("")) {
            errors.add(password_error);
        }

        return errors;

    }

    private static String validateUsername(String username, Boolean usernameDupulicateCheckFlag) {
        if(username == null || username.equals("")) {
            return "ユーザー名を入力してください";
        }

        if(usernameDupulicateCheckFlag) {
            EntityManager em = DBUtil.createEntityManager();
            long trainees_count = (long)em.createNamedQuery("checkUsername", Long.class)
                                          .setParameter("username", username)
                                          .getSingleResult();
            em.close();
            if(trainees_count > 0) {
                return "そのユーザ名はすでに存在しています";
            }
        }
        return "";
    }

    private static String validatePassword(String password, Boolean passwordCheckFlag) {
        if(passwordCheckFlag && (password == null || password.equals(""))) {
                return "パスワードを入力してください";
        }
        return "";
    }
}
