package com.sundeinfo.kd.zs.dao;

import com.sundeinfo.kd.zs.dao.model.School;
import com.sundeinfo.kd.zs.dao.model.SchoolExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SchoolMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_school
     *
     * @mbg.generated
     */
    long countByExample(SchoolExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_school
     *
     * @mbg.generated
     */
    int deleteByExample(SchoolExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_school
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_school
     *
     * @mbg.generated
     */
    int insert(School record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_school
     *
     * @mbg.generated
     */
    int insertSelective(School record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_school
     *
     * @mbg.generated
     */
    List<School> selectByExample(SchoolExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_school
     *
     * @mbg.generated
     */
    School selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_school
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") School record, @Param("example") SchoolExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_school
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") School record, @Param("example") SchoolExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_school
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(School record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_school
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(School record);
}