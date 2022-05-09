package com.assignment.online.utils.connections;

public class ServerAPIs {
    public static final String URL ="http://salespersons.somee.com/";

    //username & password
    public static final String LOGIN=URL+"LogInOutAndroid/Login";

    //admin operations
    public static final String ADD_SALES_PERSON=URL+"OperationAdminAndroid/AddSalesPerson";
    public static final String DELETE_SALES_PERSON=URL+"OperationAdminAndroid/DeleteSalesPerson";
    public static final String EDI_SALES_PERSON=URL+"OperationAdminAndroid/EditSalesPerson";
    public static final String ALL_SALES_PERSONS=URL+"OperationAdminAndroid/AllSalesPerson";
    public static final String SEARCH=URL+"OperationAdminAndroid/SearchSalesPerson";

    //sales person operations
    public static final String INSERT_SALES=URL+"OperationSalesPersonAndroid/InsertSalesPersonCommission";
}
