package com.wzmtr.dom.constant;

/**
 * 通用字典
 * @author  Ray
 * @version 1.0
 * @date 2024/03/06
 */
public class CommonConstants {

    public static String API_RES_DATA = "data";
    public static String API_RES_LIST = "list";

    /**
     * ADD UPDATE DELETE
     */
    public static String ADD = "ADD";
    public static String UPDATE = "UPDATE";
    public static String DELETE = "DELETE";

    /**
     * id
     */
    public static final String ID = "id";

    public static final String WSDL_SUCCESS = "0";

    public static final String EMPTY = "";
    public static final String BLANK = " ";
    public static final String COMMA = ",";
    public static final String UNDERLINE = "_";
    public static final String SINGLE_QUOTATION_MARK = "'";
    public static final String DOUBLE_QUOTATION_MARKS = "\"";
    public static final String TIME = "yyyy-MM-dd HH:mm:ss";

    public static final String XLS = "xls";

    public static final String XLSX = "xlsx";

    public static final String UNKNOWN = "unknown";

    public static final String ROOT = "root";
    /**
     * 开启
     */
    public static final String ON = "on";
    /**
     * 关闭
     */
    public static final String OFF = "off";

    /**
     * 权限
     */
    public static final String ADMIN = "admin";

    /**
     * 字符串数字
     */
    public static final String ZERO_STRING = "0";
    public static final String ONE_STRING = "1";
    public static final String TWO_STRING = "2";
    public static final String THREE_STRING = "3";
    public static final String FOUR_STRING = "4";
    public static final String FIVE_STRING = "5";
    public static final String SIX_STRING = "6";
    public static final String SEVEN_STRING = "7";
    public static final String EIGHT_STRING = "8";
    public static final String NINE_STRING = "9";
    public static final String TEN_STRING = "10";
    public static final String TWENTY_STRING = "20";
    public static final String THIRTY_STRING = "30";
    public static final String FORTY_STRING = "40";
    public static final String FIFTY_STRING = "50";
    public static final String NINETY_STRING = "90";
    public static final String INFINITY_STRING = "∞";
    public static final String ZERO_PERCENTAGE_STRING = "0%";
    public static final String DECIMAL_FMT_STRING = "0.0000";
    public static final String PERCENTAGE_STRING = "%";

    /**
     * 数字
     */
    public static final int ZERO = 0;
    public static final long ZERO_LONG = 0L;
    public static final int ONE = 1;
    public static final int TWO = 2;
    public static final int THREE = 3;
    public static final int FOUR = 4;
    public static final int FIVE = 5;
    public static final int SIX = 6;
    public static final int SEVEN = 7;
    public static final int EIGHT = 8;
    public static final int NINE = 9;
    public static final int TEN = 10;
    public static final int TWENTY = 20;
    public static final int THIRTY = 30;
    public static final int ONE_HUNDRED = 100;
    public static final long ONE_HUNDRED_LONG = 100L;
    public static final int TEN_THOUSAND = 10000;
    public static final double TEN_THOUSAND_DOUBLE = 10000.0;

    /**
     * 流程相关
     */
    public static final String CODE = "code";
    public static final String PROCESS_ERROR_CODE = "-1";

    /**
     * 线路编号
     */
    public static final String LINE_CODE_ONE = "01";
    public static final String LINE_CODE_TWO = "02";

    public static final String XT = "下塘";
    public static final String TT = "汀田";
    /**
     * 下塘停车场
     */
    public static final String STATION_280 = "280";

    /**
     * 汀田车辆段
     */
    public static final String STATION_281 = "281";

    /**
     * 汀田车辆段
     */
    public static final String[]  STATION_280_281 = {"280","281"};

    /**
     * 指定的日期格式
     */
    public static final String DATE_FORMAT = "yyyy-MM-dd";

    /**
     * 指定数据获取时间
     */
    public static final String DEFAULT_TIME = " 05:00:00";

    /**
     * 类型:日报
     */
    public static final String DATA_TYPE_DAILY = "1";

    /**
     * 类型:周报
     */
    public static final String DATA_TYPE_WEEKLY = "2";

    /**
     * 类型:月报
     */
    public static final String DATA_TYPE_MONTHLY = "3";

    /**
     * 事件类型
     */
    public static final String EVENT_TYPE_1 = "1";
    public static final String EVENT_TYPE_2 = "2";
    public static final String EVENT_TYPE_3 = "3";
    public static final String EVENT_TYPE_4 = "4";
    public static final String EVENT_TYPE_5 = "5";

    public static final String HOTLINE_TYPE = "HOTLINE_TYPE";

    public static final String OPERATE_DAY = "OPERATE_DAY";

    public static final String DEFAULT_PRODUCTION_TITLE = "安全生产情况";
    public static final String[] S2_STATION_ARRAY = {"231","232","233","234","235","236","237","238","239","240","241","242","243","244","245","246","247","248","249","250"};

    public static final String VEHICLE_DAILY_REPORT = "VEHICLE_DAILY_REPORT";
    public static final String VEHICLE_WEEKLY_REPORT = "VEHICLE_WEEKLY_REPORT";
    public static final String VEHICLE_MONTHLY_REPORT = "VEHICLE_MONTHLY_REPORT";
    public static final String VEHICLE_CONTAINS = "vehicle_";
    public static final String VEHICLE_DAILY_NODE1 ="vehicle_daily_node1";
    public static final String VEHICLE_WEEKLY_NODE1 ="vehicle_weekly_node1";
    public static final String VEHICLE_MONTHLY_NODE1 ="vehicle_monthly_node1";

    public static final String[] TRAFFIC_REPORT_TYPE = {"1","2","3"};
    public static final String TRAFFIC_DAILY_REPORT = "TRAFFIC_DAILY_REPORT";

    public static final String[] TRAFFIC_DAILY_NODE1_SUB = {"traffic_daily_node1_sub1","traffic_daily_node1_sub2","traffic_daily_node1_sub3"};
    public static final String TRAFFIC_DAILY_NODE1_SUB1 ="traffic_daily_node1_sub1";
    public static final String TRAFFIC_DAILY_NODE1_SUB2 ="traffic_daily_node1_sub2";
    public static final String TRAFFIC_DAILY_NODE1_SUB3 ="traffic_daily_node1_sub3";

    public static final String TRAFFIC_WEEKLY_REPORT = "TRAFFIC_WEEKLY_REPORT";
    public static final String[] TRAFFIC_WEEKLY_NODE1_SUB = {"traffic_weekly_node1_sub1","traffic_weekly_node1_sub2","traffic_weekly_node1_sub3"};
    public static final String TRAFFIC_WEEKLY_NODE1_SUB1 ="traffic_weekly_node1_sub1";
    public static final String TRAFFIC_WEEKLY_NODE1_SUB2 ="traffic_weekly_node1_sub2";
    public static final String TRAFFIC_WEEKLY_NODE1_SUB3 ="traffic_weekly_node1_sub3";

    public static final String TRAFFIC_MONTHLY_REPORT = "TRAFFIC_MONTHLY_REPORT";
    public static final String[]  TRAFFIC_MONTHLY_NODE1_SUB = {"traffic_monthly_node1_sub1","traffic_monthly_node1_sub2","traffic_monthly_node1_sub3"};
    public static final String TRAFFIC_MONTHLY_NODE1_SUB1 ="traffic_monthly_node1_sub1";
    public static final String TRAFFIC_MONTHLY_NODE1_SUB2 ="traffic_monthly_node1_sub2";
    public static final String TRAFFIC_MONTHLY_NODE1_SUB3 ="traffic_monthly_node1_sub3";

    public static final String OPERATE_DAILY_REPORT = "OPERATE_DAILY_REPORT";
    public static final String OPERATE_WEEKLY_REPORT = "OPERATE_WEEKLY_REPORT";
    public static final String OPERATE_MONTHLY_REPORT = "OPERATE_MONTHLY_REPORT";
    public static final String OPERATE_DAILY_NODE1 ="operate_daily_node1";
    public static final String OPERATE_WEEKLY_NODE1 ="operate_weekly_node1";
    public static final String OPERATE_MONTHLY_NODE1 ="operate_monthly_node1";

    //运营开始日期
    public static final String OPERATE_START_DAY = "2023-08-26";

    // " 八项指标类型:1件数/次数,2指标值,3指标值(国标)4运营服务指标
    public static final String[] OPERATE_INDICATOR_TYPE = {"1","2"};

    public static final String SYNC_DATA_TIME = " 05:00:00";

    //车辆和信号调试作业
    public static final String CONSTRUCT_DEBUG = "A1";

    //车场作业
    public static final String CONSTRUCT_DEPOT = "B";

    public static final String WEEK_PLAN = "week_plan";
    public static final String DAY_PLAN = "day_plan";
    public static final String TEMP_PLAN = "temp_plan";
    public static final String OPERATE_CONSTRUCT_REMARK_TPL = "（1）本周正线施工计划A、C类施工（周施工计划和日补充计划）共{1}件，临时补修计划{2}件；其中删除计划共计{3}件，擅自取消施工共计{4}件，修改计划共计{5}件，增加计划共计{6}件，实际完成施工共计{7}件。因天气影响等导致施工被迫取消共计{8}件，施工延点（行调同意）共计{9}件;\n" +
            "（2）本周车场施工计划 B 类施工（周施工计划和日补充计划）共{10}件，临时补修计划{11}件；其中删除计划共计{12}件，擅自取消施工共计{13}件，修改计划共计 {14}件，增加计划共计{15}件，实际完成施工共计{16}件。因天气影响等被迫取消共计{17}件，施工延点（场调同意）共计{18}件;\n" +
            "（3）S2 正线本周计划兑现率为{19}，计划准确率为{20}，车场本周计划兑现率为{21}，计划准确率为{22}。正线平均施工时间利用率为：{23}。有{24}项施工时间利用率不足，详见下表；车场本周平均施工时间利用率为：{25}，有{26}项施工时间利用率不足。";
}
