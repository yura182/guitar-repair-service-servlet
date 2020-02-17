package com.yura.repair.command.admin;

import com.yura.repair.command.Command;
import com.yura.repair.command.MultipleMethodCommand;
import com.yura.repair.command.PaginationUtility;
import com.yura.repair.dto.UserDto;
import com.yura.repair.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AllUsersCommand extends MultipleMethodCommand implements PaginationUtility {
    private final UserService userService;

    public AllUsersCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected String executeGet(HttpServletRequest request) {
        int currentPage = getCurrentPage(request);
        int recordsPerPage = getRecordsPerPage(request);

        List<UserDto> users = userService.findAll(getOffset(currentPage, recordsPerPage), recordsPerPage);

        paginate(currentPage, recordsPerPage, userService.numberOfEntries(), users, "/admin/all-users", request, "admin");

        return "admin-all-users";
    }

    @Override
    protected String executePost(HttpServletRequest request) {
        return null;
    }
}
