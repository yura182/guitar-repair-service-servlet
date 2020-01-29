package com.yura.repairservice.command;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

public interface PaginationUtility {

    default <T> void paginate(int currentPage, int recordsPerPage, int entries, List<T> entities, String command, HttpServletRequest request, String page) {
        int numberOfPages = (int) Math.ceil((double) entries / recordsPerPage);

        request.setAttribute("entities", entities);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("recordsPerPage", recordsPerPage);
        request.setAttribute("numberOfPages", numberOfPages);
        request.setAttribute("command", command);
        request.setAttribute("page", page);
    }

    default int getPaginationParameter(String parameter, int defaultParameter) {
        int result;

        if (Objects.isNull(parameter)) {
            return defaultParameter;
        }

        try {
            result = Integer.parseInt(parameter);
        } catch (NumberFormatException e) {
            result = defaultParameter;
        }

        return result > 0 ? result : defaultParameter;
    }
}
