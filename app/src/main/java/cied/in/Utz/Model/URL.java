package cied.in.Utz.Model;

/**
 * Created by cied on 5/10/16.
 */

public class URL {
    public static String mobileRootUrl = "http://ec2-54-164-173-150.compute-1.amazonaws.com/utz/";
    public static String rootUrl = "http://ec2-54-164-173-150.compute-1.amazonaws.com/utz/mobile/";
    public static String loginUrl = rootUrl+"utz/mobile/login/";
    public static String getProfileUrl = mobileRootUrl+"auditor/";
    public static String forgottenPasswordUrl = rootUrl+"user/forgot/password/";
    public static String logOutUrl = mobileRootUrl+"logout/";
    public static String auditDetailsUrl = rootUrl+"audits/get-audit/";
    public static String groupAuditDetailsUrl = "http://ec2-54-85-186-192.compute-1.amazonaws.com/audit/group/";
    public static String codeDetailsUrl = "http://ec2-54-85-186-192.compute-1.amazonaws.com/mobile/";

}
