package com.rpt.admin.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * ëª¨ë“  ?„ë©”ì¸ ê°ì²´ê°? ?ƒ?†ë°›ì•„?•¼?•  ê¸°ë³¸ ?´?˜?Š¤.
 *
 */
public class BaseObject implements Serializable {
    /**
     * Use serialVersionUID science
     */
    private static final long serialVersionUID = 4711031603329794236L;

    /**
     * ?•´?‹¹ ê°ì²´?˜ toString ?˜¤ë²„ë¼?´?“œ.
     *
     * @return ê°ì²´?˜ ê°? ?”„ë¡œí¼?‹°?? ?•´?‹¹ ê°’ì„ textë¡? ? œê³?
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    /**
     * ?•´?‹¹ ê°ì²´?˜ equals ?˜¤ë²„ë¼?´?“œ.
     *
     * @param obj ë¹„êµ ???ƒ ê°ì²´
     * @return ë¹„êµ ???ƒ ê°ì²´?? ê°? ?”„ë¡œí¼?‹°ê°’ì´ ?™?¼?•œ ê²½ìš° <code>tue</code>ë°˜í™˜
     */
    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    /**
     * ?•´?‹¹ ê°ì²´?˜ hashCode ?˜¤ë²„ë¼?´?“œ.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}