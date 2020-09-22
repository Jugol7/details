package com.details.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/***
 * @author zlp
 * @date 14:34 2020/3/16
 */
public class BaseMongoEntity implements Serializable {

    private static final long serialVersionUID = 2625327060349829207L;

    @Id
    @ApiModelProperty(value = "数据id",dataType = "java.lang.String",example = "5a6ee52b080a127e7c070ece",readOnly = true)
    private ObjectId objectId;

    @Version
    @ApiModelProperty(value = "版本号",hidden = true)
    private Long version;

    @CreatedBy
    @ApiModelProperty(value = "创建人",notes="系统自动生成",example = "devuser",readOnly = true)
    private String createdBy;

    @LastModifiedBy
    @ApiModelProperty(value = "最后修改人",notes="系统自动生成",example = "devuser",readOnly = true)
    private String lastModifiedBy;

    @LastModifiedDate
    @ApiModelProperty(value = "最后修改日期",notes="系统自动生成",readOnly = true,example = "1516350070000",dataType = "long")
//    @Expose
    private LocalDateTime lastModifiedDate;

    @CreatedDate
    @ApiModelProperty(value = "创建日期",notes="系统自动生成",readOnly = true,example = "1516350070000",dataType = "long")
    private LocalDateTime createDate;

    //--------------瞬时字段------------------------

    /**
     * 查询用结束时间
     */
    @Transient
    @ApiModelProperty(value = "查询用创建结束时间",notes="查询用",example = "1516350070000",dataType = "long")
    private LocalDateTime createEndDate;

    @Transient
    @ApiModelProperty(value = "查询用最后修改结束时间",notes="查询用",example = "1516350070000",dataType = "long")
    private LocalDateTime lastModifiedEndDate;


    //-----------------getter and setter ---------------------------


    public ObjectId getObjectId() {
        return objectId;
    }

    public BaseMongoEntity setObjectId(ObjectId objectId) {
        this.objectId = objectId;
        return this;
    }

    public Long getVersion() {
        return version;
    }

    public BaseMongoEntity setVersion(Long version) {
        this.version = version;
        return this;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public BaseMongoEntity setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public BaseMongoEntity setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
        return this;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public BaseMongoEntity setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
        return this;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public BaseMongoEntity setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public LocalDateTime getCreateEndDate() {
        return createEndDate;
    }

    public BaseMongoEntity setCreateEndDate(LocalDateTime createEndDate) {
        this.createEndDate = createEndDate;
        return this;
    }

    public LocalDateTime getLastModifiedEndDate() {
        return lastModifiedEndDate;
    }

    public BaseMongoEntity setLastModifiedEndDate(LocalDateTime lastModifiedEndDate) {
        this.lastModifiedEndDate = lastModifiedEndDate;
        return this;
    }

    @Transient
    @JsonIgnore
    public String getIdString() {
        return this.objectId == null ? null : this.objectId.toHexString();
    }
}

