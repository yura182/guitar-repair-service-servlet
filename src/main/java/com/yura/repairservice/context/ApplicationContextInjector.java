package com.yura.repairservice.context;

import com.yura.repairservice.command.Command;
import com.yura.repairservice.command.admin.*;
import com.yura.repairservice.command.master.*;
import com.yura.repairservice.command.user.*;
import com.yura.repairservice.domain.instrument.Instrument;
import com.yura.repairservice.domain.order.Order;
import com.yura.repairservice.domain.order.Review;
import com.yura.repairservice.domain.user.User;
import com.yura.repairservice.entity.InstrumentEntity;
import com.yura.repairservice.entity.OrderEntity;
import com.yura.repairservice.entity.ReviewEntity;
import com.yura.repairservice.entity.UserEntity;
import com.yura.repairservice.repository.InstrumentRepository;
import com.yura.repairservice.repository.OrderRepository;
import com.yura.repairservice.repository.ReviewRepository;
import com.yura.repairservice.repository.UserRepository;
import com.yura.repairservice.repository.connector.DBConnector;
import com.yura.repairservice.repository.impl.InstrumentRepositoryImpl;
import com.yura.repairservice.repository.impl.OrderRepositoryImpl;
import com.yura.repairservice.repository.impl.ReviewRepositoryImpl;
import com.yura.repairservice.repository.impl.UserRepositoryImpl;
import com.yura.repairservice.service.InstrumentService;
import com.yura.repairservice.service.OrderService;
import com.yura.repairservice.service.ReviewService;
import com.yura.repairservice.service.UserService;
import com.yura.repairservice.service.encoder.PasswordEncoder;
import com.yura.repairservice.service.impl.InstrumentServiceImpl;
import com.yura.repairservice.service.impl.OrderServiceImpl;
import com.yura.repairservice.service.impl.ReviewServiceImpl;
import com.yura.repairservice.service.impl.UserServiceImpl;
import com.yura.repairservice.service.mapper.*;
import com.yura.repairservice.service.validator.*;
import org.apache.commons.dbcp.BasicDataSource;

import java.util.HashMap;
import java.util.Map;

public class ApplicationContextInjector {
    private static final String DATABASE_PROPERTY_FILE = "database";

    private static final BasicDataSource BASIC_DATA_SOURCE = new BasicDataSource();
    private static final DBConnector CONNECTOR = new DBConnector(DATABASE_PROPERTY_FILE, BASIC_DATA_SOURCE);
    private static final PasswordEncoder PASSWORD_ENCODER = new PasswordEncoder();

    private static final UserRepository USER_REPOSITORY = new UserRepositoryImpl(CONNECTOR);
    private static final InstrumentRepository INSTRUMENT_REPOSITORY = new InstrumentRepositoryImpl(CONNECTOR);
    private static final OrderRepository ORDER_REPOSITORY = new OrderRepositoryImpl(CONNECTOR);
    private static final ReviewRepository REVIEW_REPOSITORY = new ReviewRepositoryImpl(CONNECTOR);

    private static final EntityMapper<UserEntity, User> USER_MAPPER = new UserMapper();
    private static final EntityMapper<InstrumentEntity, Instrument> INSTRUMENT_MAPPER = new InstrumentMapper();
    private static final EntityMapper<OrderEntity, Order> ORDER_MAPPER = new OrderMapper(USER_MAPPER, INSTRUMENT_MAPPER);
    private static final EntityMapper<ReviewEntity, Review> COMMENT_MAPPER = new ReviewMapper(USER_MAPPER, ORDER_MAPPER);

    private static final Validator<User> USER_VALIDATOR = new UserValidator();
    private static final Validator<Instrument> INSTRUMENT_VALIDATOR = new InstrumentValidator();
    private static final Validator<Order> ORDER_VALIDATOR = new OrderValidator();
    private static final Validator<Review> REVIEW_VALIDATOR = new ReviewValidator();

    private static final UserService USER_SERVICE = new UserServiceImpl(USER_REPOSITORY, USER_MAPPER, USER_VALIDATOR, PASSWORD_ENCODER);
    private static final InstrumentService INSTRUMENT_SERVICE = new InstrumentServiceImpl(INSTRUMENT_REPOSITORY, INSTRUMENT_MAPPER, INSTRUMENT_VALIDATOR);
    private static final OrderService ORDER_SERVICE = new OrderServiceImpl(ORDER_REPOSITORY, ORDER_MAPPER, ORDER_VALIDATOR, INSTRUMENT_VALIDATOR);
    private static final ReviewService REVIEW_SERVICE = new ReviewServiceImpl(REVIEW_REPOSITORY, COMMENT_MAPPER, REVIEW_VALIDATOR);

    private static final Command LOGIN_COMMAND = new LoginCommand(USER_SERVICE);
    private static final Command REGISTER_COMMAND = new RegisterCommand(USER_SERVICE);
    private static final Command LOGOUT_COMMAND = new LogoutCommand();
    private static final Command ADD_ORDER_COMMAND = new MakeOrderCommand(INSTRUMENT_SERVICE, ORDER_SERVICE);
    private static final Command USER_ALL_ORDERS = new UserOrdersCommand(ORDER_SERVICE);
    private static final Command USER_ORDER_DETAILS_COMMAND = new UserOrderDetailsCommand(ORDER_SERVICE);
    private static final Command LEAVE_REVIEW_COMMAND = new LeaveReviewCommand(REVIEW_SERVICE);
    private static final Command ALL_REVIEWS = new AllReviewsCommand(REVIEW_SERVICE);

    private static final Command All_USERS_COMMAND = new AllUsersCommand(USER_SERVICE);
    private static final Command ALL_ORDERS_COMMAND = new AdminAllOrdersCommand(ORDER_SERVICE);
    private static final Command ADMIN_ORDER_DETAILS_COMMAND = new AdminOrderDetailsCommand(ORDER_SERVICE);
    private static final Command ACCEPT_ORDER_COMMAND = new AcceptOrderCommand(ORDER_SERVICE);
    private static final Command REJECT_ORDER_COMMAND = new RejectOrderCommand(ORDER_SERVICE);
    private static final Command ADMIN_ALL_REVIEWS_COMMAND = new AdminAllReviewsCommand(REVIEW_SERVICE);
    private static final Command DELETE_REVIEW_COMMAND = new DeleteReviewCommand(REVIEW_SERVICE);

    private static final Command MASTER_AVAILABLE_ORDERS_COMMAND = new MasterAllAvailableOrdersCommand(ORDER_SERVICE);
    private static final Command MASTER_ORDER_DETAILS_COMMAND = new MasterOrderDetailsCommand(ORDER_SERVICE);
    private static final Command MASTER_PROCESS_ORDER_COMMAND = new ProcessOrderCommand(ORDER_SERVICE);
    private static final Command MASTER_COMPLETE_ORDER_COMMAND = new CompleteOrderCommand(ORDER_SERVICE);
    private static final Command MASTER_PROCESSING_ORDERS_COMMAND = new MasterProcessingOrdersCommand(ORDER_SERVICE);

    private static final Map<String, Command> COMMAND_NAME_TO_USER_COMMAND = new HashMap<>();
    private static final Map<String, Command> COMMAND_NAME_TO_ADMIN_COMMAND = new HashMap<>();
    private static final Map<String, Command> COMMAND_NAME_TO_MASTER_COMMAND = new HashMap<>();

    static {
        COMMAND_NAME_TO_USER_COMMAND.put("login", LOGIN_COMMAND);
        COMMAND_NAME_TO_USER_COMMAND.put("register", REGISTER_COMMAND);
        COMMAND_NAME_TO_USER_COMMAND.put("logout", LOGOUT_COMMAND);
        COMMAND_NAME_TO_USER_COMMAND.put("makeOrder", ADD_ORDER_COMMAND);
        COMMAND_NAME_TO_USER_COMMAND.put("userAllOrders", USER_ALL_ORDERS);
        COMMAND_NAME_TO_USER_COMMAND.put("userOrderDetails", USER_ORDER_DETAILS_COMMAND);
        COMMAND_NAME_TO_USER_COMMAND.put("leaveReview", LEAVE_REVIEW_COMMAND);
        COMMAND_NAME_TO_USER_COMMAND.put("allReviews", ALL_REVIEWS);
    }

    static {
        COMMAND_NAME_TO_ADMIN_COMMAND.put("allUsers", All_USERS_COMMAND);
        COMMAND_NAME_TO_ADMIN_COMMAND.put("adminAllOrders", ALL_ORDERS_COMMAND);
        COMMAND_NAME_TO_ADMIN_COMMAND.put("adminOrderDetails", ADMIN_ORDER_DETAILS_COMMAND);
        COMMAND_NAME_TO_ADMIN_COMMAND.put("acceptOrder", ACCEPT_ORDER_COMMAND);
        COMMAND_NAME_TO_ADMIN_COMMAND.put("rejectOrder", REJECT_ORDER_COMMAND);
        COMMAND_NAME_TO_ADMIN_COMMAND.put("adminAllReviews", ADMIN_ALL_REVIEWS_COMMAND);
        COMMAND_NAME_TO_ADMIN_COMMAND.put("deleteReview", DELETE_REVIEW_COMMAND);
    }

    static {
        COMMAND_NAME_TO_MASTER_COMMAND.put("masterAllAvailableOrders", MASTER_AVAILABLE_ORDERS_COMMAND);
        COMMAND_NAME_TO_MASTER_COMMAND.put("masterOrderDetails", MASTER_ORDER_DETAILS_COMMAND);
        COMMAND_NAME_TO_MASTER_COMMAND.put("processOrder", MASTER_PROCESS_ORDER_COMMAND);
        COMMAND_NAME_TO_MASTER_COMMAND.put("completeOrder", MASTER_COMPLETE_ORDER_COMMAND);
        COMMAND_NAME_TO_MASTER_COMMAND.put("masterProcessingOrders", MASTER_PROCESSING_ORDERS_COMMAND);
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

    public Map<String, Command> getUserCommand() {
        return COMMAND_NAME_TO_USER_COMMAND;
    }

    public Map<String, Command> getAdminCommand() {
        return COMMAND_NAME_TO_ADMIN_COMMAND;
    }

    public Map<String, Command> getMasterCommand() {
        return COMMAND_NAME_TO_MASTER_COMMAND;
    }
}
