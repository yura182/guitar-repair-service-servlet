package com.yura.repair.command.admin;

import com.yura.repair.command.Command;
import com.yura.repair.dto.UserDto;
import com.yura.repair.service.UserService;
import com.yura.repair.util.PaginationUtility;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.yura.repair.constant.PageConstant.ADMIN_USERS_PAGE;

public class AllUsersCommand implements Command {
    private static final String ADMIN_USERS_COMMAND = "/admin/users";

    private final UserService userService;

    private final PaginationUtility pagination;

    public AllUsersCommand(UserService userService, PaginationUtility pagination) {
        this.userService = userService;
        this.pagination = pagination;
    }

    @Override
    public String execute(HttpServletRequest request) {
        int currentPage = pagination.getCurrentPage(request);
        int recordsPerPage = pagination.getRecordsPerPage(request);

        List<UserDto> users = userService.findAll(pagination.getOffset(currentPage, recordsPerPage), recordsPerPage);

        pagination.paginate(currentPage, recordsPerPage, userService.numberOfEntries(), users, ADMIN_USERS_COMMAND, request);

        return ADMIN_USERS_PAGE;
    }
}
