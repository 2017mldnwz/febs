package org.febs.common.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
/**
 * 为通用查询参数类，用于接收前端上送的分页和排序信息
 */
public class QueryRequest {
    private static final long serialVersionUID = -4869594085374385813L;
    /**
     * 当前页面数据量
     */
    private int pageSize = 10;
    /**
     * 当前页码
     */
    private int pageNum = 1;
    /**
     * 排序字段
     */
    private String field;
    /**
     * 排序规则，asc升序，desc降序
     */
    private String order;
}
