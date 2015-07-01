package com.volkoval.jest.lang.reflect;


import java.math.BigDecimal;
import java.util.Date;

public class LoadDataStatusTO {
    private Integer index;
    private Long idRow;
    private BigDecimal dataSourceId;
    private BigDecimal level;
    private BigDecimal refRole;
    private String dimension;
    private Date date;
    private String user;
    private String role;
    private String approved;
    private String enabled;
    private String state;
    private String timeDuration;
    private String stageList;
    private BigDecimal rowsCount;
    private String blockingUser;
    private String blockingRole;

    public String getId() {
        return "loadStatus_" + this.index;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public BigDecimal getLevel() {
        return level;
    }

    public void setLevel(BigDecimal level) {
        this.level = level;
    }

    public Long getRoleId() {
        if (refRole != null) {
            return refRole.longValue();
        }
        return null;
    }

    public String getTimeDuration() {
        return timeDuration;
    }

    public void setTimeDuration(String timeDuration) {
        this.timeDuration = timeDuration;
    }

    public String getStageList() {
        return stageList;
    }

    public void setStageList(String stageList) {
        this.stageList = stageList;
    }

    public BigDecimal getRowsCount() {
        return rowsCount;
    }

    public void setRowsCount(BigDecimal rowsCount) {
        this.rowsCount = rowsCount;
    }

    public String getBlockingUser() {
        return blockingUser;
    }

    public void setBlockingUser(String blockingUser) {
        this.blockingUser = blockingUser;
    }

    public String getBlockingRole() {
        return blockingRole;
    }

    public void setBlockingRole(String blockingRole) {
        this.blockingRole = blockingRole;
    }

    public BigDecimal getRefRole() {
        return refRole;
    }

    public void setRefRole(BigDecimal refRole) {
        this.refRole = refRole;
    }

    public BigDecimal getDataSourceId() {
        return dataSourceId;
    }

    public void setDataSourceId(BigDecimal dataSourceId) {
        this.dataSourceId = dataSourceId;
    }

    public Long getIdRow() {
        return idRow;
    }

    public void setIdRow(Long idRow) {
        this.idRow = idRow;
    }

    public String getModifiedDate() {
        return "";
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getApproved() {
        return approved;
    }

    public void setApproved(String approved) {
        this.approved = approved;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public boolean isDisabled() {
        return false;
    }

    public boolean isTransferred() {
        return false;
    }

    public boolean isPartial() {
        return false;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStatus() {
        return this.state == null ? null : getStatusValue().toString();
    }

    public Integer getStatusId() {
        return 0;
    }

    private Object getStatusValue() {
        return new Object();
    }
}
