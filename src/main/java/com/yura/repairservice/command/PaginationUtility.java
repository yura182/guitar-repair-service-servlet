package com.yura.repairservice.command;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

public interface PaginationUtility {
    int DEFAULT_PAGINATION_PAGE = 1;
    int DEFAULT_PAGINATION_RECORDS = 5;

    default <T> void paginate(int currentPage, int recordsPerPage, int entries, List<T> entities, String command, HttpServletRequest request, String page) {
        int numberOfPages = (int) Math.ceil((double) entries / recordsPerPage);

        request.setAttribute("entities", entities);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("recordsPerPage", recordsPerPage);
        request.setAttribute("numberOfPages", numberOfPages);
        request.setAttribute("command", command);
        request.setAttribute("page", page);
    }

    default int getCurrentPage(HttpServletRequest request) {
        return getPaginationParameter(request.getParameter("currentPage"), DEFAULT_PAGINATION_PAGE);
    }

    default int getRecordsPerPage(HttpServletRequest request) {
        return getPaginationParameter(request.getParameter("recordsPerPage"), DEFAULT_PAGINATION_RECORDS);
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

    default int getOffset(int currentPage, int recordsPerPage) {
        return currentPage * recordsPerPage - recordsPerPage;
    }
}
