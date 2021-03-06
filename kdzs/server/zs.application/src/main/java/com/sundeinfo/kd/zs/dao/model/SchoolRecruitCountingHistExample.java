package com.sundeinfo.kd.zs.dao.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class SchoolRecruitCountingHistExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table b_school_recruit_counting_hist
     *
     * @mbg.generated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table b_school_recruit_counting_hist
     *
     * @mbg.generated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table b_school_recruit_counting_hist
     *
     * @mbg.generated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table b_school_recruit_counting_hist
     *
     * @mbg.generated
     */
    public SchoolRecruitCountingHistExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table b_school_recruit_counting_hist
     *
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table b_school_recruit_counting_hist
     *
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table b_school_recruit_counting_hist
     *
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table b_school_recruit_counting_hist
     *
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table b_school_recruit_counting_hist
     *
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table b_school_recruit_counting_hist
     *
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table b_school_recruit_counting_hist
     *
     * @mbg.generated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table b_school_recruit_counting_hist
     *
     * @mbg.generated
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table b_school_recruit_counting_hist
     *
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table b_school_recruit_counting_hist
     *
     * @mbg.generated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table b_school_recruit_counting_hist
     *
     * @mbg.generated
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andSeasonidIsNull() {
            addCriterion("seasonId is null");
            return (Criteria) this;
        }

        public Criteria andSeasonidIsNotNull() {
            addCriterion("seasonId is not null");
            return (Criteria) this;
        }

        public Criteria andSeasonidEqualTo(Integer value) {
            addCriterion("seasonId =", value, "seasonid");
            return (Criteria) this;
        }

        public Criteria andSeasonidNotEqualTo(Integer value) {
            addCriterion("seasonId <>", value, "seasonid");
            return (Criteria) this;
        }

        public Criteria andSeasonidGreaterThan(Integer value) {
            addCriterion("seasonId >", value, "seasonid");
            return (Criteria) this;
        }

        public Criteria andSeasonidGreaterThanOrEqualTo(Integer value) {
            addCriterion("seasonId >=", value, "seasonid");
            return (Criteria) this;
        }

        public Criteria andSeasonidLessThan(Integer value) {
            addCriterion("seasonId <", value, "seasonid");
            return (Criteria) this;
        }

        public Criteria andSeasonidLessThanOrEqualTo(Integer value) {
            addCriterion("seasonId <=", value, "seasonid");
            return (Criteria) this;
        }

        public Criteria andSeasonidIn(List<Integer> values) {
            addCriterion("seasonId in", values, "seasonid");
            return (Criteria) this;
        }

        public Criteria andSeasonidNotIn(List<Integer> values) {
            addCriterion("seasonId not in", values, "seasonid");
            return (Criteria) this;
        }

        public Criteria andSeasonidBetween(Integer value1, Integer value2) {
            addCriterion("seasonId between", value1, value2, "seasonid");
            return (Criteria) this;
        }

        public Criteria andSeasonidNotBetween(Integer value1, Integer value2) {
            addCriterion("seasonId not between", value1, value2, "seasonid");
            return (Criteria) this;
        }

        public Criteria andSchoolidIsNull() {
            addCriterion("schoolId is null");
            return (Criteria) this;
        }

        public Criteria andSchoolidIsNotNull() {
            addCriterion("schoolId is not null");
            return (Criteria) this;
        }

        public Criteria andSchoolidEqualTo(Integer value) {
            addCriterion("schoolId =", value, "schoolid");
            return (Criteria) this;
        }

        public Criteria andSchoolidNotEqualTo(Integer value) {
            addCriterion("schoolId <>", value, "schoolid");
            return (Criteria) this;
        }

        public Criteria andSchoolidGreaterThan(Integer value) {
            addCriterion("schoolId >", value, "schoolid");
            return (Criteria) this;
        }

        public Criteria andSchoolidGreaterThanOrEqualTo(Integer value) {
            addCriterion("schoolId >=", value, "schoolid");
            return (Criteria) this;
        }

        public Criteria andSchoolidLessThan(Integer value) {
            addCriterion("schoolId <", value, "schoolid");
            return (Criteria) this;
        }

        public Criteria andSchoolidLessThanOrEqualTo(Integer value) {
            addCriterion("schoolId <=", value, "schoolid");
            return (Criteria) this;
        }

        public Criteria andSchoolidIn(List<Integer> values) {
            addCriterion("schoolId in", values, "schoolid");
            return (Criteria) this;
        }

        public Criteria andSchoolidNotIn(List<Integer> values) {
            addCriterion("schoolId not in", values, "schoolid");
            return (Criteria) this;
        }

        public Criteria andSchoolidBetween(Integer value1, Integer value2) {
            addCriterion("schoolId between", value1, value2, "schoolid");
            return (Criteria) this;
        }

        public Criteria andSchoolidNotBetween(Integer value1, Integer value2) {
            addCriterion("schoolId not between", value1, value2, "schoolid");
            return (Criteria) this;
        }

        public Criteria andRecruitdateIsNull() {
            addCriterion("recruitDate is null");
            return (Criteria) this;
        }

        public Criteria andRecruitdateIsNotNull() {
            addCriterion("recruitDate is not null");
            return (Criteria) this;
        }

        public Criteria andRecruitdateEqualTo(Date value) {
            addCriterionForJDBCDate("recruitDate =", value, "recruitdate");
            return (Criteria) this;
        }

        public Criteria andRecruitdateNotEqualTo(Date value) {
            addCriterionForJDBCDate("recruitDate <>", value, "recruitdate");
            return (Criteria) this;
        }

        public Criteria andRecruitdateGreaterThan(Date value) {
            addCriterionForJDBCDate("recruitDate >", value, "recruitdate");
            return (Criteria) this;
        }

        public Criteria andRecruitdateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("recruitDate >=", value, "recruitdate");
            return (Criteria) this;
        }

        public Criteria andRecruitdateLessThan(Date value) {
            addCriterionForJDBCDate("recruitDate <", value, "recruitdate");
            return (Criteria) this;
        }

        public Criteria andRecruitdateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("recruitDate <=", value, "recruitdate");
            return (Criteria) this;
        }

        public Criteria andRecruitdateIn(List<Date> values) {
            addCriterionForJDBCDate("recruitDate in", values, "recruitdate");
            return (Criteria) this;
        }

        public Criteria andRecruitdateNotIn(List<Date> values) {
            addCriterionForJDBCDate("recruitDate not in", values, "recruitdate");
            return (Criteria) this;
        }

        public Criteria andRecruitdateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("recruitDate between", value1, value2, "recruitdate");
            return (Criteria) this;
        }

        public Criteria andRecruitdateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("recruitDate not between", value1, value2, "recruitdate");
            return (Criteria) this;
        }

        public Criteria andWritedateIsNull() {
            addCriterion("writeDate is null");
            return (Criteria) this;
        }

        public Criteria andWritedateIsNotNull() {
            addCriterion("writeDate is not null");
            return (Criteria) this;
        }

        public Criteria andWritedateEqualTo(Date value) {
            addCriterion("writeDate =", value, "writedate");
            return (Criteria) this;
        }

        public Criteria andWritedateNotEqualTo(Date value) {
            addCriterion("writeDate <>", value, "writedate");
            return (Criteria) this;
        }

        public Criteria andWritedateGreaterThan(Date value) {
            addCriterion("writeDate >", value, "writedate");
            return (Criteria) this;
        }

        public Criteria andWritedateGreaterThanOrEqualTo(Date value) {
            addCriterion("writeDate >=", value, "writedate");
            return (Criteria) this;
        }

        public Criteria andWritedateLessThan(Date value) {
            addCriterion("writeDate <", value, "writedate");
            return (Criteria) this;
        }

        public Criteria andWritedateLessThanOrEqualTo(Date value) {
            addCriterion("writeDate <=", value, "writedate");
            return (Criteria) this;
        }

        public Criteria andWritedateIn(List<Date> values) {
            addCriterion("writeDate in", values, "writedate");
            return (Criteria) this;
        }

        public Criteria andWritedateNotIn(List<Date> values) {
            addCriterion("writeDate not in", values, "writedate");
            return (Criteria) this;
        }

        public Criteria andWritedateBetween(Date value1, Date value2) {
            addCriterion("writeDate between", value1, value2, "writedate");
            return (Criteria) this;
        }

        public Criteria andWritedateNotBetween(Date value1, Date value2) {
            addCriterion("writeDate not between", value1, value2, "writedate");
            return (Criteria) this;
        }

        public Criteria andUndergraduatepeopleIsNull() {
            addCriterion("undergraduatePeople is null");
            return (Criteria) this;
        }

        public Criteria andUndergraduatepeopleIsNotNull() {
            addCriterion("undergraduatePeople is not null");
            return (Criteria) this;
        }

        public Criteria andUndergraduatepeopleEqualTo(Integer value) {
            addCriterion("undergraduatePeople =", value, "undergraduatepeople");
            return (Criteria) this;
        }

        public Criteria andUndergraduatepeopleNotEqualTo(Integer value) {
            addCriterion("undergraduatePeople <>", value, "undergraduatepeople");
            return (Criteria) this;
        }

        public Criteria andUndergraduatepeopleGreaterThan(Integer value) {
            addCriterion("undergraduatePeople >", value, "undergraduatepeople");
            return (Criteria) this;
        }

        public Criteria andUndergraduatepeopleGreaterThanOrEqualTo(Integer value) {
            addCriterion("undergraduatePeople >=", value, "undergraduatepeople");
            return (Criteria) this;
        }

        public Criteria andUndergraduatepeopleLessThan(Integer value) {
            addCriterion("undergraduatePeople <", value, "undergraduatepeople");
            return (Criteria) this;
        }

        public Criteria andUndergraduatepeopleLessThanOrEqualTo(Integer value) {
            addCriterion("undergraduatePeople <=", value, "undergraduatepeople");
            return (Criteria) this;
        }

        public Criteria andUndergraduatepeopleIn(List<Integer> values) {
            addCriterion("undergraduatePeople in", values, "undergraduatepeople");
            return (Criteria) this;
        }

        public Criteria andUndergraduatepeopleNotIn(List<Integer> values) {
            addCriterion("undergraduatePeople not in", values, "undergraduatepeople");
            return (Criteria) this;
        }

        public Criteria andUndergraduatepeopleBetween(Integer value1, Integer value2) {
            addCriterion("undergraduatePeople between", value1, value2, "undergraduatepeople");
            return (Criteria) this;
        }

        public Criteria andUndergraduatepeopleNotBetween(Integer value1, Integer value2) {
            addCriterion("undergraduatePeople not between", value1, value2, "undergraduatepeople");
            return (Criteria) this;
        }

        public Criteria andTechnicalpeopleIsNull() {
            addCriterion("technicalPeople is null");
            return (Criteria) this;
        }

        public Criteria andTechnicalpeopleIsNotNull() {
            addCriterion("technicalPeople is not null");
            return (Criteria) this;
        }

        public Criteria andTechnicalpeopleEqualTo(Integer value) {
            addCriterion("technicalPeople =", value, "technicalpeople");
            return (Criteria) this;
        }

        public Criteria andTechnicalpeopleNotEqualTo(Integer value) {
            addCriterion("technicalPeople <>", value, "technicalpeople");
            return (Criteria) this;
        }

        public Criteria andTechnicalpeopleGreaterThan(Integer value) {
            addCriterion("technicalPeople >", value, "technicalpeople");
            return (Criteria) this;
        }

        public Criteria andTechnicalpeopleGreaterThanOrEqualTo(Integer value) {
            addCriterion("technicalPeople >=", value, "technicalpeople");
            return (Criteria) this;
        }

        public Criteria andTechnicalpeopleLessThan(Integer value) {
            addCriterion("technicalPeople <", value, "technicalpeople");
            return (Criteria) this;
        }

        public Criteria andTechnicalpeopleLessThanOrEqualTo(Integer value) {
            addCriterion("technicalPeople <=", value, "technicalpeople");
            return (Criteria) this;
        }

        public Criteria andTechnicalpeopleIn(List<Integer> values) {
            addCriterion("technicalPeople in", values, "technicalpeople");
            return (Criteria) this;
        }

        public Criteria andTechnicalpeopleNotIn(List<Integer> values) {
            addCriterion("technicalPeople not in", values, "technicalpeople");
            return (Criteria) this;
        }

        public Criteria andTechnicalpeopleBetween(Integer value1, Integer value2) {
            addCriterion("technicalPeople between", value1, value2, "technicalpeople");
            return (Criteria) this;
        }

        public Criteria andTechnicalpeopleNotBetween(Integer value1, Integer value2) {
            addCriterion("technicalPeople not between", value1, value2, "technicalpeople");
            return (Criteria) this;
        }

        public Criteria andGkpeopleIsNull() {
            addCriterion("gkPeople is null");
            return (Criteria) this;
        }

        public Criteria andGkpeopleIsNotNull() {
            addCriterion("gkPeople is not null");
            return (Criteria) this;
        }

        public Criteria andGkpeopleEqualTo(Integer value) {
            addCriterion("gkPeople =", value, "gkpeople");
            return (Criteria) this;
        }

        public Criteria andGkpeopleNotEqualTo(Integer value) {
            addCriterion("gkPeople <>", value, "gkpeople");
            return (Criteria) this;
        }

        public Criteria andGkpeopleGreaterThan(Integer value) {
            addCriterion("gkPeople >", value, "gkpeople");
            return (Criteria) this;
        }

        public Criteria andGkpeopleGreaterThanOrEqualTo(Integer value) {
            addCriterion("gkPeople >=", value, "gkpeople");
            return (Criteria) this;
        }

        public Criteria andGkpeopleLessThan(Integer value) {
            addCriterion("gkPeople <", value, "gkpeople");
            return (Criteria) this;
        }

        public Criteria andGkpeopleLessThanOrEqualTo(Integer value) {
            addCriterion("gkPeople <=", value, "gkpeople");
            return (Criteria) this;
        }

        public Criteria andGkpeopleIn(List<Integer> values) {
            addCriterion("gkPeople in", values, "gkpeople");
            return (Criteria) this;
        }

        public Criteria andGkpeopleNotIn(List<Integer> values) {
            addCriterion("gkPeople not in", values, "gkpeople");
            return (Criteria) this;
        }

        public Criteria andGkpeopleBetween(Integer value1, Integer value2) {
            addCriterion("gkPeople between", value1, value2, "gkpeople");
            return (Criteria) this;
        }

        public Criteria andGkpeopleNotBetween(Integer value1, Integer value2) {
            addCriterion("gkPeople not between", value1, value2, "gkpeople");
            return (Criteria) this;
        }

        public Criteria andIsdeleteIsNull() {
            addCriterion("isdelete is null");
            return (Criteria) this;
        }

        public Criteria andIsdeleteIsNotNull() {
            addCriterion("isdelete is not null");
            return (Criteria) this;
        }

        public Criteria andIsdeleteEqualTo(Boolean value) {
            addCriterion("isdelete =", value, "isdelete");
            return (Criteria) this;
        }

        public Criteria andIsdeleteNotEqualTo(Boolean value) {
            addCriterion("isdelete <>", value, "isdelete");
            return (Criteria) this;
        }

        public Criteria andIsdeleteGreaterThan(Boolean value) {
            addCriterion("isdelete >", value, "isdelete");
            return (Criteria) this;
        }

        public Criteria andIsdeleteGreaterThanOrEqualTo(Boolean value) {
            addCriterion("isdelete >=", value, "isdelete");
            return (Criteria) this;
        }

        public Criteria andIsdeleteLessThan(Boolean value) {
            addCriterion("isdelete <", value, "isdelete");
            return (Criteria) this;
        }

        public Criteria andIsdeleteLessThanOrEqualTo(Boolean value) {
            addCriterion("isdelete <=", value, "isdelete");
            return (Criteria) this;
        }

        public Criteria andIsdeleteIn(List<Boolean> values) {
            addCriterion("isdelete in", values, "isdelete");
            return (Criteria) this;
        }

        public Criteria andIsdeleteNotIn(List<Boolean> values) {
            addCriterion("isdelete not in", values, "isdelete");
            return (Criteria) this;
        }

        public Criteria andIsdeleteBetween(Boolean value1, Boolean value2) {
            addCriterion("isdelete between", value1, value2, "isdelete");
            return (Criteria) this;
        }

        public Criteria andIsdeleteNotBetween(Boolean value1, Boolean value2) {
            addCriterion("isdelete not between", value1, value2, "isdelete");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNull() {
            addCriterion("createtime is null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNotNull() {
            addCriterion("createtime is not null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeEqualTo(Date value) {
            addCriterion("createtime =", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotEqualTo(Date value) {
            addCriterion("createtime <>", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThan(Date value) {
            addCriterion("createtime >", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("createtime >=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThan(Date value) {
            addCriterion("createtime <", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThanOrEqualTo(Date value) {
            addCriterion("createtime <=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIn(List<Date> values) {
            addCriterion("createtime in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotIn(List<Date> values) {
            addCriterion("createtime not in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeBetween(Date value1, Date value2) {
            addCriterion("createtime between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotBetween(Date value1, Date value2) {
            addCriterion("createtime not between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatebyIsNull() {
            addCriterion("createby is null");
            return (Criteria) this;
        }

        public Criteria andCreatebyIsNotNull() {
            addCriterion("createby is not null");
            return (Criteria) this;
        }

        public Criteria andCreatebyEqualTo(Integer value) {
            addCriterion("createby =", value, "createby");
            return (Criteria) this;
        }

        public Criteria andCreatebyNotEqualTo(Integer value) {
            addCriterion("createby <>", value, "createby");
            return (Criteria) this;
        }

        public Criteria andCreatebyGreaterThan(Integer value) {
            addCriterion("createby >", value, "createby");
            return (Criteria) this;
        }

        public Criteria andCreatebyGreaterThanOrEqualTo(Integer value) {
            addCriterion("createby >=", value, "createby");
            return (Criteria) this;
        }

        public Criteria andCreatebyLessThan(Integer value) {
            addCriterion("createby <", value, "createby");
            return (Criteria) this;
        }

        public Criteria andCreatebyLessThanOrEqualTo(Integer value) {
            addCriterion("createby <=", value, "createby");
            return (Criteria) this;
        }

        public Criteria andCreatebyIn(List<Integer> values) {
            addCriterion("createby in", values, "createby");
            return (Criteria) this;
        }

        public Criteria andCreatebyNotIn(List<Integer> values) {
            addCriterion("createby not in", values, "createby");
            return (Criteria) this;
        }

        public Criteria andCreatebyBetween(Integer value1, Integer value2) {
            addCriterion("createby between", value1, value2, "createby");
            return (Criteria) this;
        }

        public Criteria andCreatebyNotBetween(Integer value1, Integer value2) {
            addCriterion("createby not between", value1, value2, "createby");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIsNull() {
            addCriterion("updatetime is null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIsNotNull() {
            addCriterion("updatetime is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeEqualTo(Date value) {
            addCriterion("updatetime =", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotEqualTo(Date value) {
            addCriterion("updatetime <>", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThan(Date value) {
            addCriterion("updatetime >", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("updatetime >=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThan(Date value) {
            addCriterion("updatetime <", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThanOrEqualTo(Date value) {
            addCriterion("updatetime <=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIn(List<Date> values) {
            addCriterion("updatetime in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotIn(List<Date> values) {
            addCriterion("updatetime not in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeBetween(Date value1, Date value2) {
            addCriterion("updatetime between", value1, value2, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotBetween(Date value1, Date value2) {
            addCriterion("updatetime not between", value1, value2, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatebyIsNull() {
            addCriterion("updateby is null");
            return (Criteria) this;
        }

        public Criteria andUpdatebyIsNotNull() {
            addCriterion("updateby is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatebyEqualTo(Integer value) {
            addCriterion("updateby =", value, "updateby");
            return (Criteria) this;
        }

        public Criteria andUpdatebyNotEqualTo(Integer value) {
            addCriterion("updateby <>", value, "updateby");
            return (Criteria) this;
        }

        public Criteria andUpdatebyGreaterThan(Integer value) {
            addCriterion("updateby >", value, "updateby");
            return (Criteria) this;
        }

        public Criteria andUpdatebyGreaterThanOrEqualTo(Integer value) {
            addCriterion("updateby >=", value, "updateby");
            return (Criteria) this;
        }

        public Criteria andUpdatebyLessThan(Integer value) {
            addCriterion("updateby <", value, "updateby");
            return (Criteria) this;
        }

        public Criteria andUpdatebyLessThanOrEqualTo(Integer value) {
            addCriterion("updateby <=", value, "updateby");
            return (Criteria) this;
        }

        public Criteria andUpdatebyIn(List<Integer> values) {
            addCriterion("updateby in", values, "updateby");
            return (Criteria) this;
        }

        public Criteria andUpdatebyNotIn(List<Integer> values) {
            addCriterion("updateby not in", values, "updateby");
            return (Criteria) this;
        }

        public Criteria andUpdatebyBetween(Integer value1, Integer value2) {
            addCriterion("updateby between", value1, value2, "updateby");
            return (Criteria) this;
        }

        public Criteria andUpdatebyNotBetween(Integer value1, Integer value2) {
            addCriterion("updateby not between", value1, value2, "updateby");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table b_school_recruit_counting_hist
     *
     * @mbg.generated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table b_school_recruit_counting_hist
     *
     * @mbg.generated
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}