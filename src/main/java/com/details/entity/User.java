package com.details.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * 用于配置多数据源的测试实体
 * @author zlp
 * @date 2019-11-06 11:07
 */
@Data
@Slf4j
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User implements Externalizable {

    private Integer id;

    private String name;

    private Integer age;

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        log.info("进入write");
        out.writeObject(id);
        out.writeObject(name);
        out.writeObject(age);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        log.info("进入read");
        id = (Integer) in.readObject();
        name = (String) in.readObject();
        age = (Integer) in.readObject();
    }
}
