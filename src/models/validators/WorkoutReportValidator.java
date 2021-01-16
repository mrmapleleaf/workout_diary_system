package models.validators;

import java.util.ArrayList;
import java.util.List;

import models.WorkoutReport;

public class WorkoutReportValidator {
    public static List<String> validate(WorkoutReport w) {
        List<String> errors = new ArrayList<>();

        String menu_error = _validateMenu(w.getMenu());
        if(!menu_error.equals("")) {
            errors.add(menu_error);
        }

        String rep_error = _validateRep(w.getRep());
        if(!rep_error.equals("")) {
            errors.add(rep_error);
        }

        String sets_error = _validateSets(w.getSets());
        if(!sets_error.equals("")) {
            errors.add(sets_error);
        }

        String intervals_error = _validateIntervals(w.getIntervals());
        if(!intervals_error.equals("")) {
            errors.add(intervals_error);
        }

        String review_error = _validateReview(w.getReview());
        if(!review_error.equals("")) {
            errors.add(review_error);
        }

        return errors;
    }

    private static String _validateMenu(String menu) {
        if(menu == null || menu.equals("")) {
            return "実施したメニューを入力してください";
        }
        return "";
    }

    private static String _validateRep(Integer rep) {
        if(rep == null || rep.equals("")) {
            return "レップ数を入力してください";
        }
        return "";
    }

    private static String _validateSets(Integer sets) {
        if(sets == null || sets.equals("")) {
            return "セット数を入力してください";
        }
        return "";
    }

    private static String _validateIntervals(Integer intervals) {
        if(intervals == null || intervals.equals("")) {
            return "インターバルを入力してください";
        }
        return "";
    }

    private static String _validateReview(String review) {
        if(review == null || review.equals("")) {
            return "レビューを入力してください";
        }
        return "";
    }
}
