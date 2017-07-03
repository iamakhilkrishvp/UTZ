package cied.in.Utz.Model;

import android.util.Log;

/**
 * Created by cied on 4/10/16.
 */
public class Items {

    private int chapterId;
    private int criterionId;
    private String position;
    private int blockId;
    private int auditId;
    private int subAuditId;
    private int auditCriterionComplianceId;
    private String chapterName;
    private String blockName;
    private String blockLabel;
    private String inspectionYear;
    private int inspectionYearId;

    public Items(int inspection_year_id, String inspection_year_code) {
        this.inspectionYearId = inspection_year_id;
        this.inspectionYear = inspection_year_code;
    }

    public Items(int auditId, int subAuditId, int blockId, int chapterId, int criterionId,
                 String compliancevalue, String comment, String suggestion, String lastupdatedDate) {
       // Log.e(" cv ...........",compliancevalue+"         cm     "+comment+"        sug      "+suggestion);

        this.auditId = auditId;
        this.subAuditId = subAuditId;
        this.blockId = blockId;
        this.chapterId = chapterId;
        this.criterionId = criterionId;
        this.complianceValue = compliancevalue;
        this.comment = comment;
        this.criterionSuggestion = suggestion;
        this.lastUpdatedDate = lastupdatedDate;

    }

    public Items(int chapterId, int blockid, String chapter_name, String s, String chapter_description) {

        this.chapterId = chapterId;
        this.blockId = blockid;
        this.chapterName = chapter_name;
        this.position = s;
        this.description = chapter_description;
    }

    public Items(int criterionId, int blockid, int chapterId, String position, String description, String type, String inspectionYear, String versionName, int i) {

        this.criterionId = criterionId;
        this.blockId = blockid;
        this.chapterId = chapterId;
        this.position = position;
        this.description = description;
        this.criterionType = type;
        this.inspectionYear = inspectionYear;
        this.versionName = versionName;
    }

    public String getBlockLabel() {
        return blockLabel;
    }

    public void setBlockLabel(String blockLabel) {
        this.blockLabel = blockLabel;
    }

    public int getBlockId() {
        return blockId;
    }

    public void setBlockId(int blockId) {
        this.blockId = blockId;
    }

    public int getInspectionYearId() {
        return inspectionYearId;
    }

    public void setInspectionYearId(int inspectionYearId) {
        this.inspectionYearId = inspectionYearId;
    }

    public String getInspectionYear() {
        return inspectionYear;
    }

    public void setInspectionYear(String inspectionYear) {
        this.inspectionYear = inspectionYear;
    }

    public String getYearName() {
        return yearName;
    }

    public void setYearName(String yearName) {
        this.yearName = yearName;
    }

    private String yearName;
    private String description;
    private String criterionType;
    private String auditorName;
    private String year;
    private String category;
    private String status;
    private String startDate;
    private String endDate;
    private String overallComplianceValue;
    private String auditorsId;
    private String auditName;
    private String auditorsName;
    private String farmId;
    private String farmName;
    private String address;
    private String district;
    private String phoneNumber;
    private String city;

    public String getBlockName() {
        return blockName;
    }

    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }

    public Items(String totalArea, String address, String phoneNumber, String city, String country,
                 String identificationNumber, String permenentMaleWorkers, String permenentFemaleWorkers,
                 String productionMined, String totalProduction, String isCertifiedBefore, String tempMaleWorkers,
                 String tempFemaleWorkers, String legalStatus, String personInCharge, String district) {

        this.totalArea = totalArea;
        this.address =address;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.country = country;
        this.identificationNumber = identificationNumber;
        this.permenentMaleWorkers = permenentMaleWorkers;
        this.permenentFemaleWorkers = permenentFemaleWorkers;
        this.typeOfProcess = productionMined;
        this.totalProduction = totalProduction;
        this.isCertifiedBefore = isCertifiedBefore;
        this.tempFemaleWorkers = tempFemaleWorkers;
        this.tempMaleWorkers = tempMaleWorkers;
        this.personInCharge = personInCharge;
        this.district = district;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTypeOfOperator() {
        return typeOfOperator;
    }

    public void setTypeOfOperator(String typeOfOperator) {
        this.typeOfOperator = typeOfOperator;
    }

    public String getTempFemaleWorkers() {
        return tempFemaleWorkers;
    }

    public void setTempFemaleWorkers(String tempFemaleWorkers) {
        this.tempFemaleWorkers = tempFemaleWorkers;
    }

    public String getTempMaleWorkers() {
        return tempMaleWorkers;
    }

    public void setTempMaleWorkers(String tempMaleWorkers) {
        this.tempMaleWorkers = tempMaleWorkers;
    }

    public String getCrops() {
        return crops;
    }

    public void setCrops(String crops) {
        this.crops = crops;
    }

    public String getCompliancePercentage() {
        return compliancePercentage;
    }

    public void setCompliancePercentage(String compliancePercentage) {
        this.compliancePercentage = compliancePercentage;
    }

    public String getOtherCertificationBoards() {
        return otherCertificationBoards;
    }

    public void setOtherCertificationBoards(String otherCertificationBoards) {
        this.otherCertificationBoards = otherCertificationBoards;
    }

    public String getTypeOfProcess() {
        return typeOfProcess;
    }

    public void setTypeOfProcess(String typeOfProcess) {
        this.typeOfProcess = typeOfProcess;
    }

    public String getIsCertifiedBefore() {
        return isCertifiedBefore;
    }

    public void setIsCertifiedBefore(String isCertifiedBefore) {
        this.isCertifiedBefore = isCertifiedBefore;
    }

    public String getPermenentMaleWorkers() {
        return permenentMaleWorkers;
    }

    public void setPermenentMaleWorkers(String permenentMaleWorkers) {
        this.permenentMaleWorkers = permenentMaleWorkers;
    }

    public String getPermenentFemaleWorkers() {
        return permenentFemaleWorkers;
    }

    public void setPermenentFemaleWorkers(String permenentFemaleWorkers) {
        this.permenentFemaleWorkers = permenentFemaleWorkers;
    }

    public String getTotalArea() {
        return totalArea;
    }

    public void setTotalArea(String totalArea) {
        this.totalArea = totalArea;
    }

    public String getLeagalStatus() {
        return leagalStatus;
    }

    public void setLeagalStatus(String leagalStatus) {
        this.leagalStatus = leagalStatus;
    }

    public String getAreaUnderUtz() {
        return areaUnderUtz;
    }

    public void setAreaUnderUtz(String areaUnderUtz) {
        this.areaUnderUtz = areaUnderUtz;
    }

    public String getTotalWorkersLivingatPeakSeason() {
        return totalWorkersLivingatPeakSeason;
    }

    public void setTotalWorkersLivingatPeakSeason(String totalWorkersLivingatPeakSeason) {
        this.totalWorkersLivingatPeakSeason = totalWorkersLivingatPeakSeason;
    }

    private String totalProduction;

    public String getTotalProduction() {
        return totalProduction;
    }

    public void setTotalProduction(String totalProduction) {
        this.totalProduction = totalProduction;
    }

    private String identificationNumber;
    private String country;
    private String typeOfOperator;
    private String tempFemaleWorkers;
    private String tempMaleWorkers;
    private String crops;
    private String compliancePercentage;
    private String otherCertificationBoards;
    private String typeOfProcess;
    private String isCertifiedBefore;
    private String permenentMaleWorkers;
    private String permenentFemaleWorkers;
    private String totalArea;
    private String leagalStatus;
    private String areaUnderUtz;
    private String totalWorkersLivingatPeakSeason;

    private String personInCharge;
    private String strengthArea;
    private String criticalImprovementArea;
    private String otherImprovement;
    private String recomendation;
    private String lastUpdatedDate;
    private String complianceValue;
    private String criterionSuggestion;
    private String comment;

    private String fileName;
    private String isDeleted;
    private String isSynced;
    private String accId;
    private String versionName;

    private String submittedPercentage;
    private String groupName;
    private String averageOverallScore;

    private String isMandatory;
    private String isBlank;
    private String filePath;
    private int fileId;


    public Items(String strengthArea, String criticalImprovement, String otherImprovement, String recommendation, String lastUpdatedDate) {

        this.strengthArea = strengthArea;
        this.criticalImprovementArea = criticalImprovement;
        this.otherImprovement = otherImprovement;
        this.recomendation = recommendation;
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public Items(int subuditId, int criteriaid, int accId, String filename, String imagepath,
                 String Status, String lastpdatedDate, String isdeleted, String issync) {


        this.subAuditId = subuditId;
        this.criterionId = criteriaid;
        this.auditCriterionComplianceId = accId;
        this.fileName = filename;
        this.filePath = imagepath;
        this.status = Status;
        this.lastUpdatedDate = lastpdatedDate;
        this.isDeleted = isdeleted;
        this.isSynced = issync;

    }

    public Items(String isdeleted) {
        isDeleted = isdeleted;
        this.versionName = isdeleted;
    }

    public Items(int auditid, int subAuditid, int chapterid, int criterionid, String compliancevalue, String comment, String suggestion, String lastupdateddate, String s) {
        this.auditId = auditid;
        this.subAuditId = subAuditid;
        this.chapterId = chapterid;
        this.criterionId = criterionid;
        this.complianceValue = compliancevalue;
        this.comment = comment;
        this.criterionSuggestion = suggestion;
        this.lastUpdatedDate = lastupdateddate;

    }


    public String getIsBlank() {
        return isBlank;
    }

    public void setIsBlank(String isBlank) {
        this.isBlank = isBlank;
    }

    public Items(int auditId, String group_name, String head_farmer, String no_of_farmers, String avg_overall_score, String submitted_percentage, String on_going_percentage) {
        this.auditId = auditId;
        this.groupName = group_name;
        this.averageOverallScore = avg_overall_score;
        this.submittedPercentage = submitted_percentage;

    }

    public Items(String startDate, String endDate) {

        this.startDate = startDate;
        this.endDate = endDate;

        this.complianceValue = startDate;
        this.lastUpdatedDate = endDate;

        this.comment = startDate;
        this.lastUpdatedDate = endDate;

        this.criterionSuggestion = startDate;
        this.lastUpdatedDate = endDate;



    }
    public Items(String startDate, String endDate, String s) {

        Log.e("mmmnt ",startDate);
        this.comment = startDate;
        this.lastUpdatedDate = endDate;


    }
    public Items(String startDate, String endDate, String s, String q) {


        this.criterionSuggestion = startDate;
        this.lastUpdatedDate = endDate;


    }

    public Items(String farmArea, String address, String phoneNumber, String city, String village, String identificationNumber, String cfa,
                 String totalProductionArea, String areaUnderCastor, String totalProduction, String yearsInCastor, String ownLandArea,
                 String leasedStatus, String leasedArea, String personInCharge, String district, String ownsourcepercentage, String number_of_suppliers
                    , String number_of_shifts, String leadership_compliance_percentage) {

        this.totalArea = farmArea;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.personInCharge = personInCharge;
        this.district = district;
        this.country = village;
        this.permenentMaleWorkers = cfa;
        this.permenentFemaleWorkers = totalProductionArea;
        this.identificationNumber = identificationNumber;
        this.typeOfProcess = areaUnderCastor;
        this.totalProduction = totalProduction;
        this.isCertifiedBefore = yearsInCastor;
        this.tempMaleWorkers = ownLandArea;
        this.tempFemaleWorkers = leasedStatus;
        this.leagalStatus = leasedArea;
        this.typeOfOperator = ownsourcepercentage;
        this.otherCertificationBoards = number_of_suppliers;
        this.areaUnderUtz = number_of_shifts;
        this.totalWorkersLivingatPeakSeason = leadership_compliance_percentage;


    }

    public Items(int chapterId, int criteriaId, String versionName) {
        this.chapterId = chapterId;
        this.criterionId = criteriaId;
        this.versionName = versionName;
    }


    public Items(int auditId, int subAuditId, int chapterId, String strengthArea, String criticalImprovementArea,
                 String otherImprovement, String recomendation, String lastUpdatedDate) {

        this.auditId = auditId;
        this.subAuditId = subAuditId;
        this.chapterId = chapterId;
        this.strengthArea = strengthArea;
        this.criticalImprovementArea = criticalImprovementArea;
        this.otherImprovement = otherImprovement;
        this.recomendation = recomendation;
        this.lastUpdatedDate = lastUpdatedDate;


    }

    public Items(int auditId, int subAuditId, int chapterId, int criterionId, String complianceValue,
                 String comment, String criterionSuggestion, String lastUpdatedDate) {


        this.auditId = auditId;
        this.subAuditId = subAuditId;
        this.chapterId = chapterId;
        this.criterionId = criterionId;
        this.complianceValue = complianceValue;
        this.comment = comment;
        this.criterionSuggestion = criterionSuggestion;
        this.lastUpdatedDate =lastUpdatedDate;

    }

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getIsMandatory() {
        return isMandatory;
    }

    public void setIsMandatory(String isMandatory) {
        this.isMandatory = isMandatory;
    }

    public String getSubmittedPercentage() {
        return submittedPercentage;
    }

    public void setSubmittedPercentage(String submittedPercentage) {
        this.submittedPercentage = submittedPercentage;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getAverageOverallScore() {
        return averageOverallScore;
    }

    public void setAverageOverallScore(String averageOverallScore) {
        this.averageOverallScore = averageOverallScore;
    }

    public Items(String audit_id, String audit_name, String audit_type, String status, String category, String start_date, String end_date,
                 String year, String overall_compliance, String versionName) {


        this.auditId = Integer.parseInt(audit_id);
        this.auditName = audit_name;
        this.status = status;
        this.category = category;
        this.startDate = start_date;
        this.endDate = end_date;
        this.year = year;
        this.overallComplianceValue = overall_compliance;
        this.versionName = versionName;
        this.inspectionYear = audit_type;


    }

    public Items(int auditId, String id, String auditorsName) {
        this.auditId = auditId;
        this.auditorsId = id;
        this.auditorName = auditorsName;

        this.blockId = auditId;
        this.blockLabel = id;
        this.blockName = auditorsName;
    }

    public Items(int auditid, String subAuditId, String farm_id, String farm_name, String address, String district,
                 String phone, String working_hours, String city, String status, String own_source_percentage, String identification_number,
                 String temporary_female_workers, String subcontracting_arrangement, String compliance_percentage, String person_incharge,
                 String number_of_suppliers, String product_mined, String mine_type, String permanent_female_workers,
                 String production_capacity, String permanent_male_workers, String production_area, String legal_status,
                 String temporary_male_workers, String leadership_compliance_percentage, String number_of_shifts) {

        Log.e("test ....."," Test...........2 ");
        this.auditId = auditid;
        this.subAuditId = Integer.parseInt(subAuditId);
        this.farmId = farm_id;
        this.farmName = farm_name;
        this.address = address;
        this.district = district;
        this.phoneNumber = phone;
        this.city = city;
        this.status = status;
        this.personInCharge = person_incharge;
        this.country = working_hours;
        this.typeOfOperator = own_source_percentage;
        this.identificationNumber = identification_number;
        this.tempFemaleWorkers = temporary_female_workers;
        this.crops = subcontracting_arrangement;
        this.compliancePercentage = compliance_percentage;
        this.otherCertificationBoards = number_of_suppliers;
        this.typeOfProcess = product_mined;
        this.isCertifiedBefore = mine_type;
        this.permenentFemaleWorkers = permanent_female_workers;
        this.totalProduction = production_capacity;
        this.permenentMaleWorkers = permanent_male_workers;
        this.totalArea = production_area;
        this.leagalStatus = legal_status;
        this.tempMaleWorkers = temporary_male_workers;
        this.totalWorkersLivingatPeakSeason = leadership_compliance_percentage;
        this.areaUnderUtz = number_of_shifts;


    }

    public Items() {

    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getAccId() {
        return accId;
    }

    public void setAccId(String accId) {
        this.accId = accId;
    }

    public int getAuditCriterionComplianceId() {
        return auditCriterionComplianceId;
    }

    public void setAuditCriterionComplianceId(int auditCriterionComplianceId) {
        this.auditCriterionComplianceId = auditCriterionComplianceId;
    }

    public int getChapterId() {
        return chapterId;
    }

    public String getAuditName() {
        return auditName;
    }

    public void setAuditName(String auditName) {
        this.auditName = auditName;
    }

    public void setChapterId(int chapterId) {
        this.chapterId = chapterId;
    }

    public int getCriterionId() {
        return criterionId;
    }

    public void setCriterionId(int criterionId) {
        this.criterionId = criterionId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getAuditId() {
        return auditId;
    }

    public void setAuditId(int auditId) {
        this.auditId = auditId;
    }

    public int getSubAuditId() {
        return subAuditId;
    }

    public void setSubAuditId(int subAuditId) {
        this.subAuditId = subAuditId;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCriterionType() {
        return criterionType;
    }

    public void setCriterionType(String criterionType) {
        this.criterionType = criterionType;
    }

    public String getAuditorName() {
        return auditorName;
    }

    public void setAuditorName(String auditorName) {
        this.auditorName = auditorName;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getOverallComplianceValue() {
        return overallComplianceValue;
    }

    public void setOverallComplianceValue(String overallComplianceValue) {
        this.overallComplianceValue = overallComplianceValue;
    }

    public String getAuditorsId() {
        return auditorsId;
    }

    public void setAuditorsId(String auditorsId) {
        this.auditorsId = auditorsId;
    }

    public String getAuditorsName() {
        return auditorsName;
    }

    public void setAuditorsName(String auditorsName) {
        this.auditorsName = auditorsName;
    }

    public String getFarmId() {
        return farmId;
    }

    public void setFarmId(String farmId) {
        this.farmId = farmId;
    }

    public String getFarmName() {
        return farmName;
    }

    public void setFarmName(String farmName) {
        this.farmName = farmName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPersonInCharge() {
        return personInCharge;
    }

    public void setPersonInCharge(String personInCharge) {
        this.personInCharge = personInCharge;
    }

    public String getStrengthArea() {
        return strengthArea;
    }

    public void setStrengthArea(String strengthArea) {
        this.strengthArea = strengthArea;
    }

    public String getCriticalImprovementArea() {
        return criticalImprovementArea;
    }

    public void setCriticalImprovementArea(String criticalImprovementArea) {
        this.criticalImprovementArea = criticalImprovementArea;
    }

    public String getOtherImprovement() {
        return otherImprovement;
    }

    public void setOtherImprovement(String otherImprovement) {
        this.otherImprovement = otherImprovement;
    }

    public String getRecomendation() {
        return recomendation;
    }

    public void setRecomendation(String recomendation) {
        this.recomendation = recomendation;
    }

    public String getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(String lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getComplianceValue() {
        return complianceValue;
    }

    public void setComplianceValue(String complianceValue) {
        this.complianceValue = complianceValue;
    }

    public String getCriterionSuggestion() {
        return criterionSuggestion;
    }

    public void setCriterionSuggestion(String criterionSuggestion) {
        this.criterionSuggestion = criterionSuggestion;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getIsSynced() {
        return isSynced;
    }

    public void setIsSynced(String isSynced) {
        this.isSynced = isSynced;
    }
}
