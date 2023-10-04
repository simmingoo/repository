package com.rpt.admin.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 모든 ?��메인 객체�? ?��?��받아?��?�� 기본 ?��?��?��.
 *
 */
public class BaseObject implements Serializable {
    /**
     * Use serialVersionUID science
     */
    private static final long serialVersionUID = 4711031603329794236L;

    /**
     * ?��?�� 객체?�� toString ?��버라?��?��.
     *
     * @return 객체?�� �? ?��로퍼?��?? ?��?�� 값을 text�? ?���?
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    /**
     * ?��?�� 객체?�� equals ?��버라?��?��.
     *
     * @param obj 비교 ???�� 객체
     * @return 비교 ???�� 객체?? �? ?��로퍼?��값이 ?��?��?�� 경우 <code>tue</code>반환
     */
    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    /**
     * ?��?�� 객체?�� hashCode ?��버라?��?��.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}