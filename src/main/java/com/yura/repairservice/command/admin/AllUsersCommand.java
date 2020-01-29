package com.yura.repairservice.command.admin;

import com.yura.repairservice.command.Command;
import com.yura.repairservice.command.PaginationUtility;
import com.yura.repairservice.domain.user.User;
import com.yura.repairservice.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AllUsersCommand implements Command, PaginationUtility {
    private static final int DEFAULT_PAGINATION_PAGE = 1;
    private static final int DEFAULT_PAGINATION_RECORDS = 5;

    private final UserService userService;

    public AllUsersCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        int currentPage = getPaginationParameter(request.getParameter("currentPage"), DEFAULT_PAGINATION_PAGE);
        int recordsPerPage = getPaginationParameter(request.getParameter("recordsPerPage"), DEFAULT_PAGINATION_RECORDS);


        List<User> users = userService.findAll(currentPage * recordsPerPage - recordsPerPage, recordsPerPage);

        paginate(currentPage, recordsPerPage, userService.numberOfEntries(), users, "allUsers", request, "admin");

        return "admin-all-users.jsp";
    }
}
