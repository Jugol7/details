package com.details.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 用于配置多数据源的测试实体
 * @author zlp
 * @date 2019-11-06 11:07
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class user {

    private Integer id;

    private String name;

    private Integer age;
}
