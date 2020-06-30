package com.sundeinfo.kd.zs.dto.apis.school;

import lombok.Data;

import java.util.List;

@Data
public class OutRecruitSumInfoDTO {

    private List<SumSchoolRecruit> sumSchoolRecruit;

    private  List<SumSchoolRecruit> sumSchoolRecruitByWeek;

    private  List<SumSchoolRecruit> sumSchoolRecruitByMonth;

    private  List<SumSchoolRecruit> sumSchoolRecruitBySeason;

    private  List<SumSchoolRecruit> sumSchoolRecruitByYear;

    @Data
    public static class SumSchoolRecruit {
        private String schoolId;
        private String schoolName;
        private String sumByIndex;
        private String sumByName;
        private int technicalPeople;
        private int undergraduatePeople;
        private int gkPeople;
    }

}
