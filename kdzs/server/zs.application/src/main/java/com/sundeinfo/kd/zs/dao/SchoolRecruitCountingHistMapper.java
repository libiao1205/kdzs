package com.sundeinfo.kd.zs.dao;

import com.sundeinfo.kd.zs.dao.model.SchoolRecruitCountingHist;
import com.sundeinfo.kd.zs.dao.model.SchoolRecruitCountingHistExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SchoolRecruitCountingHistMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table b_school_recruit_counting_hist
     *
     * @mbg.generated
     */
    long countByExample(SchoolRecruitCountingHistExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table b_school_recruit_counting_hist
     *
     * @mbg.generated
     */
    int deleteByExample(SchoolRecruitCountingHistExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table b_school_recruit_counting_hist
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table b_school_recruit_counting_hist
     *
     * @mbg.generated
     */
    int insert(SchoolRecruitCountingHist record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table b_school_recruit_counting_hist
     *
     * @mbg.generated
     */
    int insertSelective(SchoolRecruitCountingHist record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table b_school_recruit_counting_hist
     *
     * @mbg.generated
     */
    List<SchoolRecruitCountingHist> selectByExample(SchoolRecruitCountingHistExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table b_school_recruit_counting_hist
     *
     * @mbg.generated
     */
    SchoolRecruitCountingHist selectByPrimaryKey(Integer id);


    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table b_school_recruit_counting_hist
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") SchoolRecruitCountingHist record, @Param("example") SchoolRecruitCountingHistExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table b_school_recruit_counting_hist
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") SchoolRecruitCountingHist record, @Param("example") SchoolRecruitCountingHistExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table b_school_recruit_counting_hist
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(SchoolRecruitCountingHist record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table b_school_recruit_counting_hist
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(SchoolRecruitCountingHist record);
}