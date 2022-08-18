package com.xdu.wjw.supermarketserver.util;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;
/**
 * @author weijunwei
 */
@UtilityClass
public class SensitiveFieldUtil {

    /**
     * example: <张**>
     * @param userName 名字
     * @return 脱敏结果
     */
    public static String chineseName(String userName) {
        if (StringUtils.isEmpty(userName)) {
            return "";
        }
        String name = StringUtils.left(userName, 1);
        return StringUtils.rightPad(name, StringUtils.length(userName), "*");
    }

    /**
     * example: <**************1111>
     * @param idCard 身份证号
     * @return 脱敏结果
     */
    public static String idCard(String idCard) {
        if (StringUtils.isEmpty(idCard)) {
            return "";
        }
        String id = StringUtils.right(idCard, 4);
        return StringUtils.leftPad(id, StringUtils.length(idCard), "*");
    }

    /**
     * example: <173****1100>
     * @param phone 手机号
     * @return 脱敏结果
     */
    public static String telephone(String phone) {
        if (StringUtils.isEmpty(phone)) {
            return "";
        }
        return StringUtils.left(phone, 3).concat(StringUtils.removeStart(StringUtils.leftPad(StringUtils.right(phone, 4), StringUtils.length(phone), "*"), "***"));
    }

    /**
     * example: <天津市滨海新区******>
     * @param address 地址信息
     * @param sensitiveSize 敏感信息长度
     * @return 脱敏结果
     */
    public static String address(String address, int sensitiveSize) {
        if (StringUtils.isBlank(address)) {
            return "";
        }
        int length = StringUtils.length(address);
        return StringUtils.rightPad(StringUtils.left(address, length - sensitiveSize), length, "*");
    }

    /**
     * example: <a**@163.com>
     * @param email 邮箱
     * @return 脱敏结果
     */
    public static String email(String email) {
        if (StringUtils.isBlank(email)) {
            return "";
        }
        int index = StringUtils.indexOf(email, "@");
        if (index <= 1) {
            return email;
        } else {
            return StringUtils.rightPad(StringUtils.left(email, 1), index, "*").concat(StringUtils.mid(email, index, StringUtils.length(email)));
        }
    }


    /**
     * example <6222600**********1234>
     * @param cardNum 银行卡号
     * @return 脱敏结果
     */
    public static String bankCard(String cardNum) {
        if (StringUtils.isBlank(cardNum)) {
            return "";
        }
        return StringUtils.left(cardNum, 6).concat(StringUtils.removeStart(StringUtils.leftPad(StringUtils.right(cardNum, 4), StringUtils.length(cardNum), "*"), "******"));
    }

    /**
     * example <12********>
     * @param code 公司开户银行联号
     * @return 脱敏结果
     */
    public static String cnapsCode(String code) {
        if (StringUtils.isBlank(code)) {
            return "";
        }
        return StringUtils.rightPad(StringUtils.left(code, 2), StringUtils.length(code), "*");
    }
}