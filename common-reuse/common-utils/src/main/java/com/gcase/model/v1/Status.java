package com.gcase.model.v1;

/**
 * <p>
 * 枚举状态码设计
 * <p>
 *
 * @creator bicheng.deng
 * @createTime 2021/2/1
 */
public enum Status {
    AUTH_FAIL(10001,"用户认证失败"),
    TOKEN_TIMEOUT(10002,"Token已过期"),
    SUCCESS(20000,"响应成功")
    ;
    private Integer code;
    private String desc;

    Status(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


}
