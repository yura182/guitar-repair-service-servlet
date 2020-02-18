package com.yura.repair.context;

import com.yura.repair.command.Command;
import com.yura.repair.command.admin.*;
import com.yura.repair.command.helper.PaginationUtility;
import com.yura.repair.command.master.*;
import com.yura.repair.command.user.*;
import com.yura.repair.dto.InstrumentDto;
import com.yura.repair.dto.OrderDto;
import com.yura.repair.dto.ReviewDto;
import com.yura.repair.dto.UserDto;
import com.yura.repair.entity.InstrumentEntity;
import com.yura.repair.entity.OrderEntity;
import com.yura.repair.entity.ReviewEntity;
import com.yura.repair.entity.UserEntity;
import com.yura.repair.repository.InstrumentRepository;
import com.yura.repair.repository.OrderRepository;
import com.yura.repair.repository.ReviewRepository;
import com.yura.repair.repository.UserRepository;
import com.yura.repair.repository.connector.DBConnector;
import com.yura.repair.repository.impl.InstrumentRepositoryImpl;
import com.yura.repair.repository.impl.OrderRepositoryImpl;
import com.yura.repair.repository.impl.ReviewRepositoryImpl;
import com.yura.repair.repository.impl.UserRepositoryImpl;
import com.yura.repair.service.InstrumentService;
import com.yura.repair.service.OrderService;
import com.yura.repair.service.ReviewService;
import com.yura.repair.service.UserService;
import com.yura.repair.service.encoder.PasswordEncoder;
import com.yura.repair.service.impl.InstrumentServiceImpl;
import com.yura.repair.service.impl.OrderServiceImpl;
import com.yura.repair.service.impl.ReviewServiceImpl;
import com.yura.repair.service.impl.UserServiceImpl;
import com.yura.repair.service.mapper.*;
import com.yura.repair.service.validator.*;
import org.apache.commons.dbcp.BasicDataSource;

import java.util.*;

public class ApplicationContextInjector {
    private static final String DATABASE_PROPERTY_FILE = "database";

    private static final BasicDataSource BASIC_DATA_SOURCE = new BasicDataSource();
    private static final DBConnector CONNECTOR = new DBConnector(DATABASE_PROPERTY_FILE, BASIC_DATA_SOURCE);
    private static final PasswordEncoder PASSWORD_ENCODER = new PasswordEncoder();

    private static final UserRepository USER_REPOSITORY = new UserRepositoryImpl(CONNECTOR);
    private static final InstrumentRepository INSTRUMENT_REPOSITORY = new InstrumentRepositoryImpl(CONNECTOR);
    private static final OrderRepository ORDER_REPOSITORY = new OrderRepositoryImpl(CONNECTOR);
    private static final ReviewRepository REVIEW_REPOSITORY = new ReviewRepositoryImpl(CONNECTOR);

    private static final EntityMapper<UserEntity, UserDto> USER_MAPPER = new UserMapper();
    private static final EntityMapper<InstrumentEntity, InstrumentDto> INSTRUMENT_MAPPER = new InstrumentMapper();
    private static final EntityMapper<OrderEntity, OrderDto> ORDER_MAPPER = new OrderMapper(USER_MAPPER, INSTRUMENT_MAPPER);
    private static final EntityMapper<ReviewEntity, ReviewDto> COMMENT_MAPPER = new ReviewMapper(USER_MAPPER, ORDER_MAPPER);

    private static final Validator<UserDto> USER_VALIDATOR = new UserValidator();
    private static final Validator<InstrumentDto> INSTRUMENT_VALIDATOR = new InstrumentValidator();
    private static final Validator<OrderDto> ORDER_VALIDATOR = new OrderValidator();
    private static final Validator<ReviewDto> REVIEW_VALIDATOR = new ReviewValidator();

    private static final PaginationUtility PAGINATION = new PaginationUtility();

    private static final UserService USER_SERVICE = new UserServiceImpl(USER_REPOSITORY, USER_MAPPER, USER_VALIDATOR, PASSWORD_ENCODER);
    private static final InstrumentService INSTRUMENT_SERVICE = new InstrumentServiceImpl(INSTRUMENT_REPOSITORY, INSTRUMENT_MAPPER, INSTRUMENT_VALIDATOR);
    private static final OrderService ORDER_SERVICE = new OrderServiceImpl(ORDER_REPOSITORY, ORDER_MAPPER, ORDER_VALIDATOR, INSTRUMENT_VALIDATOR);
    private static final ReviewService REVIEW_SERVICE = new ReviewServiceImpl(REVIEW_REPOSITORY, COMMENT_MAPPER, REVIEW_VALIDATOR);

    private static final Command LOGIN_COMMAND = new LoginCommand(USER_SERVICE);
    private static final Command REGISTER_COMMAND = new RegisterCommand(USER_SERVICE);
    private static final Command LOGOUT_COMMAND = new LogoutCommand();
    private static final Command ADD_ORDER_COMMAND = new MakeOrderCommand(INSTRUMENT_SERVICE, ORDER_SERVICE);
    private static final Command USER_ALL_ORDERS = new UserOrdersCommand(ORDER_SERVICE, PAGINATION);
    private static final Command USER_ORDER_DETAILS_COMMAND = new UserOrderDetailsCommand(ORDER_SERVICE);
    private static final Command LEAVE_REVIEW_COMMAND = new LeaveReviewCommand(REVIEW_SERVICE);
    private static final Command ALL_REVIEWS = new AllReviewsCommand(REVIEW_SERVICE, PAGINATION);
    private static final Command PROFILE_COMMAND = new ProfileCommand();

    private static final Command All_USERS_COMMAND = new AllUsersCommand(USER_SERVICE, PAGINATION);
    private static final Command ALL_ORDERS_COMMAND = new AdminAllOrdersCommand(PAGINATION, ORDER_SERVICE);
    private static final Command ADMIN_ORDER_DETAILS_COMMAND = new AdminOrderDetailsCommand(ORDER_SERVICE);
    private static final Command ACCEPT_ORDER_COMMAND = new AcceptOrderCommand(ORDER_SERVICE);
    private static final Command REJECT_ORDER_COMMAND = new RejectOrderCommand(ORDER_SERVICE);
    private static final Command ADMIN_ALL_REVIEWS_COMMAND = new AdminAllReviewsCommand(REVIEW_SERVICE, PAGINATION);
    private static final Command DELETE_REVIEW_COMMAND = new DeleteReviewCommand(REVIEW_SERVICE);

    private static final Command MASTER_AVAILABLE_ORDERS_COMMAND = new MasterAllAvailableOrdersCommand(ORDER_SERVICE, PAGINATION);
    private static final Command MASTER_ORDER_DETAILS_COMMAND = new MasterOrderDetailsCommand(ORDER_SERVICE);
    private static final Command MASTER_PROCESS_ORDER_COMMAND = new ProcessOrderCommand(ORDER_SERVICE);
    private static final Command MASTER_COMPLETE_ORDER_COMMAND = new CompleteOrderCommand(ORDER_SERVICE);
    private static final Command MASTER_PROCESSING_ORDERS_COMMAND = new MasterProcessingOrdersCommand(ORDER_SERVICE, PAGINATION);

    private static final Map<String, Command> COMMAND_NAME_TO_USER_COMMAND = new HashMap<>();
    private static final Map<String, Command> COMMAND_NAME_TO_ADMIN_COMMAND = new HashMap<>();
    private static final Map<String, Command> COMMAND_NAME_TO_MASTER_COMMAND = new HashMap<>();
    private static final Map<String, Command> COMMAND_NAME_TO_DEFAULT_COMMAND = new HashMap<>();

    private static final List<String> adminPages = new ArrayList<>();
    private static final List<String> masterPages = new ArrayList<>();
    private static final List<String> userPages = new ArrayList<>();

    static {
        COMMAND_NAME_TO_USER_COMMAND.put("/client/add-order", ADD_ORDER_COMMAND);
        COMMAND_NAME_TO_USER_COMMAND.put("/client/all-orders", USER_ALL_ORDERS);
        COMMAND_NAME_TO_USER_COMMAND.put("/client/order-details", USER_ORDER_DETAILS_COMMAND);
        COMMAND_NAME_TO_USER_COMMAND.put("/client/leave-review", LEAVE_REVIEW_COMMAND);
        COMMAND_NAME_TO_USER_COMMAND.put("/login", LOGIN_COMMAND);
        COMMAND_NAME_TO_USER_COMMAND.put("/register", REGISTER_COMMAND);
        COMMAND_NAME_TO_USER_COMMAND.put("/logout", LOGOUT_COMMAND);
        COMMAND_NAME_TO_USER_COMMAND.put("/reviews", ALL_REVIEWS);
        COMMAND_NAME_TO_USER_COMMAND.put("/profile", PROFILE_COMMAND);
    }

    static {
        COMMAND_NAME_TO_ADMIN_COMMAND.put("/admin/users", All_USERS_COMMAND);
        COMMAND_NAME_TO_ADMIN_COMMAND.put("/admin/orders", ALL_ORDERS_COMMAND);
        COMMAND_NAME_TO_ADMIN_COMMAND.put("/admin/order-details", ADMIN_ORDER_DETAILS_COMMAND);
        COMMAND_NAME_TO_ADMIN_COMMAND.put("/admin/accept-order", ACCEPT_ORDER_COMMAND);
        COMMAND_NAME_TO_ADMIN_COMMAND.put("/admin/reject-order", REJECT_ORDER_COMMAND);
        COMMAND_NAME_TO_ADMIN_COMMAND.put("/admin/reviews", ADMIN_ALL_REVIEWS_COMMAND);
        COMMAND_NAME_TO_ADMIN_COMMAND.put("/admin/delete-review", DELETE_REVIEW_COMMAND);
    }

    static {
        COMMAND_NAME_TO_MASTER_COMMAND.put("/master/available-orders", MASTER_AVAILABLE_ORDERS_COMMAND);
        COMMAND_NAME_TO_MASTER_COMMAND.put("/master/order-details", MASTER_ORDER_DETAILS_COMMAND);
        COMMAND_NAME_TO_MASTER_COMMAND.put("/master/process-order", MASTER_PROCESS_ORDER_COMMAND);
        COMMAND_NAME_TO_MASTER_COMMAND.put("/master/complete-order", MASTER_COMPLETE_ORDER_COMMAND);
        COMMAND_NAME_TO_MASTER_COMMAND.put("/master/my-orders", MASTER_PROCESSING_ORDERS_COMMAND);
    }

    static {
        adminPages.addAll(Arrays.asList("/admin/orders", "/admin/order-details", "/admin/users", "/admin/reviews",
                "/admin/accept-order", "/admin/reject-order", "/admin/delete-review"));
        masterPages.addAll(Arrays.asList("/master/available-orders", "/master/my-orders", "/master/order-details",
                "/master/complete-order", "/master/process-order"));
        userPages.addAll(Arrays.asList("/login", "/register", "/reviews", "/user", "/logout", "/profile",
                "/client/all-orders", "/client/order-details", "/client/add-order", "/client/leave-review"));
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

    public Map<String, Command> getCommand(String page) {
        if (adminPages.contains(page)) {
            return COMMAND_NAME_TO_ADMIN_COMMAND;
        } else if (masterPages.contains(page)) {
            return COMMAND_NAME_TO_MASTER_COMMAND;
        } else if (userPages.contains(page)) {
            return COMMAND_NAME_TO_USER_COMMAND;
        }

        return COMMAND_NAME_TO_DEFAULT_COMMAND;
    }
}
