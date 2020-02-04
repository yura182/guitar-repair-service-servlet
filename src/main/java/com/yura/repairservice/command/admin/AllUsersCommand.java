package com.yura.repairservice.command.admin;

import com.yura.repairservice.command.Command;
import com.yura.repairservice.command.PaginationUtility;
import com.yura.repairservice.domain.user.User;
import com.yura.repairservice.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AllUsersCommand implements Command, PaginationUtility {

    private final UserService userService;

    public AllUsersCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        int currentPage = getCurrentPage(request);
        int recordsPerPage = getRecordsPerPage(request);

        List<User> users = userService.findAll(getOffset(currentPage, recordsPerPage), recordsPerPage);

        paginate(currentPage, recordsPerPage, userService.numberOfEntries(), users, "allUsers", request, "admin");

        return "admin-all-users.jsp";
    }
}
