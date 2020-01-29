package com.yura.repairservice.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

public interface Command {

    String execute(HttpServletRequest request);
}
