package com.yura.repairservice.context;

import com.yura.repairservice.command.Command;
import com.yura.repairservice.command.LoginCommand;
import com.yura.repairservice.command.RegisterCommand;
import com.yura.repairservice.repository.UserRepository;
import com.yura.repairservice.repository.connector.DBConnector;
import com.yura.repairservice.repository.impl.UserRepositoryImpl;
import com.yura.repairservice.service.UserService;
import com.yura.repairservice.service.impl.UserServiceImpl;
import com.yura.repairservice.service.mapper.UserMapper;
import org.apache.commons.dbcp.BasicDataSource;

import java.util.HashMap;
import java.util.Map;

public class ApplicationContextInjector {
    private static final String DATABASE_PROPERTY_FILE = "database";

    private static final BasicDataSource BASIC_DATA_SOURCE = new BasicDataSource();
    private static final DBConnector CONNECTOR = new DBConnector(DATABASE_PROPERTY_FILE, BASIC_DATA_SOURCE);
    private static final UserRepository USER_REPOSITORY = new UserRepositoryImpl(CONNECTOR);
    private static final UserMapper USER_MAPPER = new UserMapper();
    private static final UserService USER_SERVICE = new UserServiceImpl(USER_REPOSITORY, USER_MAPPER);

    private static final Command LOGIN_COMMAND = new LoginCommand(USER_SERVICE);
    private static final Command REGISTER_COMMAND = new RegisterCommand(USER_SERVICE);
    private static final Map<String, Command> COMMAND_NAME_TO_COMMAND = new HashMap<>();

    static {
        COMMAND_NAME_TO_COMMAND.put("login", LOGIN_COMMAND);
        COMMAND_NAME_TO_COMMAND.put("register", REGISTER_COMMAND);
    }

    private static volatile ApplicationContextInjector applicationContextInjector;

    private ApplicationContextInjector() {
    }

    public static ApplicationContextInjector getInstance() {
        if (applicationContextInjector == null) {
            synchronized (ApplicationContextInjector.class) {
                if (applicationContextInjector == null) {
                    applicationContextInjector = new ApplicationContextInjector();
                }
            }
        }
        return applicationContextInjector;
    }

    public  Map<String, Command> getCommand() {
        return COMMAND_NAME_TO_COMMAND;
    }
}
