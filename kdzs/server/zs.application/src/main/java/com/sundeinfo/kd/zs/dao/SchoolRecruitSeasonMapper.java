package com.sundeinfo.kd.zs.dao;

import com.sundeinfo.kd.zs.dao.model.SchoolRecruitSeason;
import com.sundeinfo.kd.zs.dao.model.SchoolRecruitSeasonExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SchoolRecruitSeasonMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table b_school_recruit_season
     *
     * @mbg.generated
     */
    long countByExample(SchoolRecruitSeasonExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table b_school_recruit_season
     *
     * @mbg.generated
     */
    int deleteByExample(SchoolRecruitSeasonExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table b_school_recruit_season
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table b_school_recruit_season
     *
     * @mbg.generated
     */
    int insert(SchoolRecruitSeason record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table b_school_recruit_season
     *
     * @mbg.generated
     */
    int insertSelective(SchoolRecruitSeason record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table b_school_recruit_season
     *
     * @mbg.generated
     */
    List<SchoolRecruitSeason> selectByExample(SchoolRecruitSeasonExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table b_school_recruit_season
     *
     * @mbg.generated
     */
    SchoolRecruitSeason selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table b_school_recruit_season
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") SchoolRecruitSeason record, @Param("example") SchoolRecruitSeasonExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table b_school_recruit_season
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") SchoolRecruitSeason record, @Param("example") SchoolRecruitSeasonExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table b_school_recruit_season
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(SchoolRecruitSeason record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table b_school_recruit_season
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(SchoolRecruitSeason record);
}