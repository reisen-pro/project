package com.project.cache.dto;

import com.project.cache.constant.OperateType;

import java.util.Date;
import java.util.Map;


public class CacheOperate {

    private OperateType operateType;

    private String namespace;

    private String key;

    private String field;

    private Object value;

    private Long start;

    private Long end;

    private String[] fields;

    private Date expire;

    private Map<String,String> keyValuesMap;

    private String min;

    private String max;

    private int offset;

    private int count;


    public CacheOperate() {

    }

    public CacheOperate(OperateType operateType, String namespace, String key) {
        this.operateType = operateType;
        this.namespace = namespace;
        this.key = key;
    }


    public CacheOperate(OperateType operateType, String namespace, String key, String field, Object value) {
        this.operateType = operateType;
        this.namespace = namespace;
        this.key = key;
        this.field = field;
        this.value = value;
    }

    public CacheOperate(OperateType operateType, String namespace, String key, Long start, Long end) {
        this.operateType = operateType;
        this.namespace = namespace;
        this.key = key;
        this.start = start;
        this.end = end;
    }

    public CacheOperate(OperateType operateType, String namespace, String key, String[] fields) {
        this.operateType = operateType;
        this.namespace = namespace;
        this.key = key;
        this.fields = fields;
    }

    public CacheOperate(OperateType operateType, String namespace, String key, Object value, Date expire) {
        this.operateType = operateType;
        this.namespace = namespace;
        this.key = key;
        this.value = value;
        this.expire = expire;
    }

    public CacheOperate(OperateType operateType, String namespace, String key, Date expire) {
        this.operateType = operateType;
        this.namespace = namespace;
        this.key = key;
        this.expire = expire;
    }

    public CacheOperate(OperateType operateType, String namespace, String key, Object value) {
        this.operateType = operateType;
        this.namespace = namespace;
        this.key = key;
        this.value = value;
    }

    public CacheOperate(OperateType operateType, String namespace, String key, Map<String,String> keyValuesMap) {
        this.operateType = operateType;
        this.namespace = namespace;
        this.key = key;
        this.keyValuesMap = keyValuesMap;
    }


    public CacheOperate(OperateType operateType, String namespace, String key, String min, String max, int offset, int count) {
        this.operateType = operateType;
        this.namespace = namespace;
        this.key = key;
        this.min = min;
        this.max = max;
        this.offset = offset;
        this.count = count;
    }

    public CacheOperate(OperateType operateType, String namespace, String key, String min, String max) {
        this.operateType = operateType;
        this.namespace = namespace;
        this.key = key;
        this.min = min;
        this.max = max;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public OperateType getOperateType() {
        return operateType;
    }

    public void setOperateType(OperateType operateType) {
        this.operateType = operateType;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public Long getStart() {
        return start;
    }

    public void setStart(Long start) {
        this.start = start;
    }

    public Long getEnd() {
        return end;
    }

    public void setEnd(Long end) {
        this.end = end;
    }

    public String[] getFields() {
        return fields;
    }

    public void setFields(String[] fields) {
        this.fields = fields;
    }

    public Date getExpire() {
        return expire;
    }

    public void setExpire(Date expire) {
        this.expire = expire;
    }

    public Map<String, String> getKeyValuesMap() {
        return keyValuesMap;
    }

    public void setKeyValuesMap(Map<String, String> keyValuesMap) {
        this.keyValuesMap = keyValuesMap;
    }

}
