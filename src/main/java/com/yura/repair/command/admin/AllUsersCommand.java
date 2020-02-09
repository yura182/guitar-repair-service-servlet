package com.yura.repair.command.admin;

import com.yura.repair.command.Command;
import com.yura.repair.command.PaginationUtility;
import com.yura.repair.dto.UserDto;
import com.yura.repair.service.UserService;

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

        List<UserDto> users = userService.findAll(getOffset(currentPage, recordsPerPage), recordsPerPage);

        paginate(currentPage, recordsPerPage, userService.numberOfEntries(), users, "allUsers", request, "admin");

        return "admin-all-users.jsp";
    }
}
