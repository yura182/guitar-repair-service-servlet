package com.yura.repair.constant;

public class PageConstant {

    public static final String LOGIN_PAGE = "login";
    public static final String HOME_PAGE = "/";
    public static final String REDIRECT = "redirect:";
    public static final String REVIEWS_PAGE = "reviews";
    public static final String ADD_ORDER_PAGE = "client-add-order";
    public static final String PROFILE_PAGE = "profile";
    public static final String REGISTER_PAGE = "register";
    public static final String ERROR_PAGE = "404";
    public static final String CLIENT_ORDER_DETAILS_PAGE = "client-order-details";
    public static final String CLIENT_ORDERS_PAGE = "client-all-orders";

    public static final String CLIENT_ORDER_PATH = "/client/order-details?orderId=";
    public static final String CLIENT_ADD_ORDER_PATH = "/client/add-order";

    public static final String MASTER_PROCESSING_ORDERS_PAGE = "master-all-processing-orders";
    public static final String MASTER_ORDER_PAGE = "master-order-details";
    public static final String MASTER_AVAILABLE_ORDERS_PAGE = "master-all-available-orders";

    public static final String MASTER_ORDER_PATH = "/master/order-details?orderId=";
    public static final String MASTER_AVAILABLE_ORDERS_PATH = "/master/available-orders?currentPage=1&recordsPerPage=5";

    public static final String ADMIN_USERS_PAGE = "admin-all-users";
    public static final String ADMIN_ORDER_PAGE = "admin-order-details";
    public static final String ADMIN_REVIEWS_PAGE = "admin-reviews";
    public static final String ADMIN_ORDERS_PAGE = "admin-all-orders";

    public static final String ADMIN_ORDER_PATH = "/admin/order-details?orderId=";
    public static final String ADMIN_REVIEWS_PATH = "/admin/reviews?recordsPerPage=3&currentPage=1";

}
