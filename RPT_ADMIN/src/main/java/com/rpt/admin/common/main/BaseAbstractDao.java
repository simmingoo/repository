package com.rpt.admin.common.main;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public abstract class BaseAbstractDao extends SqlSessionDaoSupport {

    /**
     * Annotation ?˜•?‹?œ¼ë¡? sqlSession(SqlSessionFactoryBean)?„ ë°›ì•„?? 
     * ?´ë¥? super(SqlSessionDaoSupport)?˜ setSqlSessionFactory ë©”ì„œ?“œë¥? ?˜¸ì¶œí•˜?—¬ ?„¤? •?•´ ì¤??‹¤.
     * <p>
     * SqlSessionTemplate?´ ì§?? •?œ ê²½ìš°?œ ê²½ìš° ?´ SqlSessionFactoryê°? ë¬´ì‹œ?œ?‹¤. (Batch ì²˜ë¦¬ë¥? ?œ„?•´?„œ?Š” SqlSessionTemplateê°? ?•„?š”)
     * 
     * 
     * @param sqlSession SqlSessionFactoryë¡? MyBatis???˜ ?—°ê³„ë?? ?œ„?•œ ê¸°ë³¸ ?´?˜?Š¤
     */
    @Resource(name = "sqlSessionFactory")
    public void setSqlSessionFactory(SqlSessionFactory sqlSession) {
    	super.setSqlSessionFactory(sqlSession);
    }

    /**
     * ?…? ¥ ì²˜ë¦¬ SQL mapping ?„ ?‹¤?–‰?•œ?‹¤.
     * 
     * @param queryId -  ?…? ¥ ì²˜ë¦¬ SQL mapping ì¿¼ë¦¬ ID
     * @param parameterObject - ?…? ¥ ì²˜ë¦¬ SQL mapping ?…? ¥ ?°?´?„°ë¥? ?„¸?Œ…?•œ ?ŒŒ?¼ë©”í„° ê°ì²´(ë³´í†µ VO ?˜?Š” Map)
     * 
     * @return DBMSê°? ì§??›?•˜?Š” ê²½ìš° insert ? ?š© ê²°ê³¼ count
     */
    public int insert(String queryId, Object parameterObject) {
    	return getSqlSession().insert(queryId, parameterObject);
    }

    /**
     * ?ˆ˜? • ì²˜ë¦¬ SQL mapping ?„ ?‹¤?–‰?•œ?‹¤.
     * 
     * @param queryId - ?ˆ˜? • ì²˜ë¦¬ SQL mapping ì¿¼ë¦¬ ID
     * @param parameterObject - ?ˆ˜? • ì²˜ë¦¬ SQL mapping ?…? ¥ ?°?´?„°(key ì¡°ê±´ ë°? ë³?ê²? ?°?´?„°)ë¥? ?„¸?Œ…?•œ ?ŒŒ?¼ë©”í„° ê°ì²´(ë³´í†µ VO ?˜?Š” Map)
     * 
     * @return DBMSê°? ì§??›?•˜?Š” ê²½ìš° update ? ?š© ê²°ê³¼ count
     */
    public int update(String queryId) {
        return getSqlSession().update(queryId);
    }
    
    /**
     * ?ˆ˜? • ì²˜ë¦¬ SQL mapping ?„ ?‹¤?–‰?•œ?‹¤.
     * 
     * @param queryId - ?ˆ˜? • ì²˜ë¦¬ SQL mapping ì¿¼ë¦¬ ID
     * @param parameterObject - ?ˆ˜? • ì²˜ë¦¬ SQL mapping ?…? ¥ ?°?´?„°(key ì¡°ê±´ ë°? ë³?ê²? ?°?´?„°)ë¥? ?„¸?Œ…?•œ ?ŒŒ?¼ë©”í„° ê°ì²´(ë³´í†µ VO ?˜?Š” Map)
     * 
     * @return DBMSê°? ì§??›?•˜?Š” ê²½ìš° update ? ?š© ê²°ê³¼ count
     */
    public int update(String queryId, Object parameterObject) {
        return getSqlSession().update(queryId, parameterObject);
    }

    /**
     * ?‚­? œ ì²˜ë¦¬ SQL mapping ?„ ?‹¤?–‰?•œ?‹¤.
     * 
     * @param queryId - ?‚­? œ ì²˜ë¦¬ SQL mapping ì¿¼ë¦¬ ID
     * @param parameterObject - ?‚­? œ ì²˜ë¦¬ SQL mapping ?…? ¥ ?°?´?„°(?¼ë°˜ì ?œ¼ë¡? key ì¡°ê±´)ë¥?  ?„¸?Œ…?•œ ?ŒŒ?¼ë©”í„° ê°ì²´(ë³´í†µ VO ?˜?Š” Map)
     *        
     * @return DBMSê°? ì§??›?•˜?Š” ê²½ìš° delete ? ?š© ê²°ê³¼ count
     */
    public int delete(String queryId, Object parameterObject) {
        return getSqlSession().delete(queryId, parameterObject);
    }

    /**
     * pk ë¥? ì¡°ê±´?œ¼ë¡? ?•œ ?‹¨ê±´ì¡°?šŒ ì²˜ë¦¬ SQL mapping ?„ ?‹¤?–‰?•œ?‹¤. 
     * 
     * @param queryId - ?‹¨ê±? ì¡°íšŒ ì²˜ë¦¬ SQL mapping ì¿¼ë¦¬ ID
     *
     * @return ê²°ê³¼ ê°ì²´ - SQL mapping ?ŒŒ?¼?—?„œ ì§?? •?•œ resultClass/resultMap ?— ?˜?•œ ?‹¨?¼ ê²°ê³¼ ê°ì²´(ë³´í†µ VO ?˜?Š” Map)
     */
    public Object selectOne(String queryId) {
        return getSqlSession().selectOne(queryId);
    }
    /**
     * pk ë¥? ì¡°ê±´?œ¼ë¡? ?•œ ?‹¨ê±´ì¡°?šŒ ì²˜ë¦¬ SQL mapping ?„ ?‹¤?–‰?•œ?‹¤. 
     * 
     * @param queryId - ?‹¨ê±? ì¡°íšŒ ì²˜ë¦¬ SQL mapping ì¿¼ë¦¬ ID
     * @param parameterObject - ?‹¨ê±? ì¡°íšŒ ì²˜ë¦¬ SQL mapping ?…? ¥ ?°?´?„°(key)ë¥? ?„¸?Œ…?•œ ?ŒŒ?¼ë©”í„° ê°ì²´(ë³´í†µ VO ?˜?Š” Map)
     *        
     * @return ê²°ê³¼ ê°ì²´ - SQL mapping ?ŒŒ?¼?—?„œ ì§?? •?•œ resultClass/resultMap ?— ?˜?•œ ?‹¨?¼ ê²°ê³¼ ê°ì²´(ë³´í†µ VO ?˜?Š” Map)
     */
    public Object selectByPk(String queryId, Object parameterObject) {
        return getSqlSession().selectOne(queryId, parameterObject);
    }

    /**
     * ë¦¬ìŠ¤?Š¸ ì¡°íšŒ ì²˜ë¦¬ SQL mapping ?„ ?‹¤?–‰?•œ?‹¤.
     * 
     * @param queryId - ë¦¬ìŠ¤?Š¸ ì¡°íšŒ ì²˜ë¦¬ SQL mapping ì¿¼ë¦¬ ID
     *
     * @return ê²°ê³¼ List ê°ì²´ - SQL mapping ?ŒŒ?¼?—?„œ ì§?? •?•œ  resultClass/resultMap ?— ?˜?•œ ê²°ê³¼ ê°ì²´(ë³´í†µ VO   ?˜?Š” Map)?˜ List
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
     * ë¶?ë¶? ë²”ìœ„ ë¦¬ìŠ¤?Š¸ ì¡°íšŒ ì²˜ë¦¬ SQL mapping ?„ ?‹¤?–‰?•œ?‹¤. 
     * (ë¶?ë¶? ë²”ìœ„ - pageIndex ?? pageSize ê¸°ë°˜?œ¼ë¡? ?˜„?¬ ë¶?ë¶? ë²”ìœ„ ì¡°íšŒë¥? ?œ„?•œ skipResults, maxResults ë¥? ê³„ì‚°?•˜?—¬ ibatis ?˜¸ì¶?)
     * 
     * @param queryId - ë¦¬ìŠ¤?Š¸ ì¡°íšŒ ì²˜ë¦¬ SQL mapping ì¿¼ë¦¬ ID
     * @param parameterObject - ë¦¬ìŠ¤?Š¸ ì¡°íšŒ ì²˜ë¦¬ SQL mapping ?…? ¥ ?°?´?„°(ì¡°íšŒ ì¡°ê±´)ë¥? ?„¸?Œ…?•œ ?ŒŒ?¼ë©”í„° ê°ì²´(ë³´í†µ VO ?˜?Š” Map)
     * @param pageIndex - ?˜„?¬ ?˜?´ì§? ë²ˆí˜¸
     * @param pageSize - ?•œ ?˜?´ì§? ì¡°íšŒ ?ˆ˜(pageSize)
     * 
     * @return ë¶?ë¶? ë²”ìœ„ ê²°ê³¼ List ê°ì²´ - SQL mapping ?ŒŒ?¼?—?„œ ì§?? •?•œ resultClass/resultMap ?— ?˜?•œ ë¶?ë¶? ë²”ìœ„ ê²°ê³¼ ê°ì²´(ë³´í†µ VO ?˜?Š” Map) List
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
