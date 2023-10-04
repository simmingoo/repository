package com.rpt.admin.common.main;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public abstract class BaseAbstractDao extends SqlSessionDaoSupport {

    /**
     * Annotation ?��?��?���? sqlSession(SqlSessionFactoryBean)?�� 받아?? 
     * ?���? super(SqlSessionDaoSupport)?�� setSqlSessionFactory 메서?���? ?��출하?�� ?��?��?�� �??��.
     * <p>
     * SqlSessionTemplate?�� �??��?�� 경우?�� 경우 ?�� SqlSessionFactory�? 무시?��?��. (Batch 처리�? ?��?��?��?�� SqlSessionTemplate�? ?��?��)
     * 
     * 
     * @param sqlSession SqlSessionFactory�? MyBatis???�� ?��계�?? ?��?�� 기본 ?��?��?��
     */
    @Resource(name = "sqlSessionFactory")
    public void setSqlSessionFactory(SqlSessionFactory sqlSession) {
    	super.setSqlSessionFactory(sqlSession);
    }

    /**
     * ?��?�� 처리 SQL mapping ?�� ?��?��?��?��.
     * 
     * @param queryId -  ?��?�� 처리 SQL mapping 쿼리 ID
     * @param parameterObject - ?��?�� 처리 SQL mapping ?��?�� ?��?��?���? ?��?��?�� ?��?��메터 객체(보통 VO ?��?�� Map)
     * 
     * @return DBMS�? �??��?��?�� 경우 insert ?��?�� 결과 count
     */
    public int insert(String queryId, Object parameterObject) {
    	return getSqlSession().insert(queryId, parameterObject);
    }

    /**
     * ?��?�� 처리 SQL mapping ?�� ?��?��?��?��.
     * 
     * @param queryId - ?��?�� 처리 SQL mapping 쿼리 ID
     * @param parameterObject - ?��?�� 처리 SQL mapping ?��?�� ?��?��?��(key 조건 �? �?�? ?��?��?��)�? ?��?��?�� ?��?��메터 객체(보통 VO ?��?�� Map)
     * 
     * @return DBMS�? �??��?��?�� 경우 update ?��?�� 결과 count
     */
    public int update(String queryId) {
        return getSqlSession().update(queryId);
    }
    
    /**
     * ?��?�� 처리 SQL mapping ?�� ?��?��?��?��.
     * 
     * @param queryId - ?��?�� 처리 SQL mapping 쿼리 ID
     * @param parameterObject - ?��?�� 처리 SQL mapping ?��?�� ?��?��?��(key 조건 �? �?�? ?��?��?��)�? ?��?��?�� ?��?��메터 객체(보통 VO ?��?�� Map)
     * 
     * @return DBMS�? �??��?��?�� 경우 update ?��?�� 결과 count
     */
    public int update(String queryId, Object parameterObject) {
        return getSqlSession().update(queryId, parameterObject);
    }

    /**
     * ?��?�� 처리 SQL mapping ?�� ?��?��?��?��.
     * 
     * @param queryId - ?��?�� 처리 SQL mapping 쿼리 ID
     * @param parameterObject - ?��?�� 처리 SQL mapping ?��?�� ?��?��?��(?��반적?���? key 조건)�?  ?��?��?�� ?��?��메터 객체(보통 VO ?��?�� Map)
     *        
     * @return DBMS�? �??��?��?�� 경우 delete ?��?�� 결과 count
     */
    public int delete(String queryId, Object parameterObject) {
        return getSqlSession().delete(queryId, parameterObject);
    }

    /**
     * pk �? 조건?���? ?�� ?��건조?�� 처리 SQL mapping ?�� ?��?��?��?��. 
     * 
     * @param queryId - ?���? 조회 처리 SQL mapping 쿼리 ID
     *
     * @return 결과 객체 - SQL mapping ?��?��?��?�� �??��?�� resultClass/resultMap ?�� ?��?�� ?��?�� 결과 객체(보통 VO ?��?�� Map)
     */
    public Object selectOne(String queryId) {
        return getSqlSession().selectOne(queryId);
    }
    /**
     * pk �? 조건?���? ?�� ?��건조?�� 처리 SQL mapping ?�� ?��?��?��?��. 
     * 
     * @param queryId - ?���? 조회 처리 SQL mapping 쿼리 ID
     * @param parameterObject - ?���? 조회 처리 SQL mapping ?��?�� ?��?��?��(key)�? ?��?��?�� ?��?��메터 객체(보통 VO ?��?�� Map)
     *        
     * @return 결과 객체 - SQL mapping ?��?��?��?�� �??��?�� resultClass/resultMap ?�� ?��?�� ?��?�� 결과 객체(보통 VO ?��?�� Map)
     */
    public Object selectByPk(String queryId, Object parameterObject) {
        return getSqlSession().selectOne(queryId, parameterObject);
    }

    /**
     * 리스?�� 조회 처리 SQL mapping ?�� ?��?��?��?��.
     * 
     * @param queryId - 리스?�� 조회 처리 SQL mapping 쿼리 ID
     *
     * @return 결과 List 객체 - SQL mapping ?��?��?��?�� �??��?��  resultClass/resultMap ?�� ?��?�� 결과 객체(보통 VO   ?��?�� Map)?�� List
     */
    @SuppressWarnings("rawtypes")
	public List list(String queryId) {
        return getSqlSession().selectList(queryId);
    }
    @SuppressWarnings("rawtypes")
	public List list(String queryId, Object parameterObject) {
        return getSqlSession().selectList(queryId, parameterObject);
    }

    /**w
     * �?�? 범위 리스?�� 조회 처리 SQL mapping ?�� ?��?��?��?��. 
     * (�?�? 범위 - pageIndex ?? pageSize 기반?���? ?��?�� �?�? 범위 조회�? ?��?�� skipResults, maxResults �? 계산?��?�� ibatis ?���?)
     * 
     * @param queryId - 리스?�� 조회 처리 SQL mapping 쿼리 ID
     * @param parameterObject - 리스?�� 조회 처리 SQL mapping ?��?�� ?��?��?��(조회 조건)�? ?��?��?�� ?��?��메터 객체(보통 VO ?��?�� Map)
     * @param pageIndex - ?��?�� ?��?���? 번호
     * @param pageSize - ?�� ?��?���? 조회 ?��(pageSize)
     * 
     * @return �?�? 범위 결과 List 객체 - SQL mapping ?��?��?��?�� �??��?�� resultClass/resultMap ?�� ?��?�� �?�? 범위 결과 객체(보통 VO ?��?�� Map) List
     */
    @SuppressWarnings("rawtypes")
	public List listWithPaging(String queryId, Object parameterObject, int pageIndex, int pageSize) {
        int skipResults = pageIndex * pageSize;
        //int maxResults = (pageIndex * pageSize) + pageSize;
        
        RowBounds rowBounds = new RowBounds(skipResults, pageSize);
        
        return getSqlSession().selectList(queryId, parameterObject, rowBounds);
    }
    
    public void select(String queryId, Object parameter, ResultHandler handler) {
    	getSqlSession().select(queryId, parameter, handler);
      }
}
