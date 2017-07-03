package cied.in.Utz.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cied.in.Utz.Model.Items;


/**
 * Created by cied on 4/10/16.
 */
public class UtzDatabase extends SQLiteOpenHelper {

    private static int databaseversion = 1;
    private static final String databasename = "utz_db";
    private static final String chapterTable = "chapterTable";
    private static final String blockTable = "blockTable";
    private static final String inspectionYearTable = "inspectionYearTable";
    private static final String criterionTable = "criterionTable";
    private static final String commonAuditDetailsTable = "commonAuditDetailsTable";
    private static final String subAuditDetailsTable = "subAuditDetailsTable";
    private static final String auditorstable = "auditorstable";
    private static final String auditCriterionComplianceTable = "auditCriterionComplianceTable";
    private static final String auditChapterComplianceTable = "auditChapterComplianceTable";
    private static final String filesAttachmentTable = "filesAttachmentTable";
    private static final String auditVersionTable = "auditVersionTable";
    private static final String groupAuditDetailsTable = "groupAuditDetailsTable";

    private static final String id = "id";
    private static final String blockId = "blockId";
    private static final String blockLabel = "blockLabel";
    private static final String blockName = "blockName";
    private static final String inspectionYear = "inspectionYear";
    private static final String inspectionYearId = "inspectionYearId";
    private static final String chapterId = "chapterId";
    private static final String position = "Position";
    private static final String chaptername = "chaptername";
    private static final String description = "description";
    private static final String criterionId = "criterionId";
    private static final String criterionType = "criterionType";
    private static final String isMandatory = "isMandatory";

    private static final String auditId = "auditId";
    private static final String auditName = "auditName";
    private static final String year = "year";
    private static final String category = "category";
    private static final String status = "status";
    private static final String startDate = "startDate";
    private static final String endDate = "endDate";
    private static final String overallComplianceValue = "overallComplianceValue";

    private static final String auditorsId = "auditorsId";
    private static final String auditorsName = "auditorsName";

    private static final String farmId = "farmId";
    private static final String farmName = "farmName";
    private static final String address = "address";
    private static final String district = "district";
    private static final String phoneNumber = "phoneNumber";
    private static final String city = "city";
    private static final String country = "country";
    private static final String identificationNumber = "identificationNumber";
    private static final String typeOfOperator = "typeOfOperator";
    private static final String tempFemaleWorkers = "tempFemaleWorkers";
    private static final String tempMaleWorkers = "tempMaleWorkers";
    private static final String crops = "crops";
    private static final String compliancePercentage = "compliancePercentage";
    private static final String otherCertificationBoards = "otherCertificationBoards";
    private static final String typeOfProcess = "typeOfProcess";
    private static final String isCertifiedBefore = "isCertifiedBefore";
    private static final String personInCharge = "personInCharge";
    private static final String permenentMaleWorkers = "permenentMaleWorkers";
    private static final String permenentFemaleWorkers = "permenentFemaleWorkers";
    private static final String totalArea = "totalArea";
    private static final String totalProduction = "totalProduction";
    private static final String areaUnderUtz = "areaUnderUtz";
    private static final String totalWorkersLivingatPeakSeason = "totalWorkersLivingatPeakSeason";


    private static final String submittedPercentage = "submittedPercentage";
    private static final String groupName = "groupName";
    private static final String averageOverallScore = "averageOverallScore";
    private static final String numberOfFarmers = "numberOfFarmers";
    private static final String headFarmer = "headFarmer";
    private static final String ongoingPercentage = "ongoingPercentage";

    private static final String subAuditId = "subAuditId";
    private static final String strengthArea = "strengthArea";
    private static final String criticalImprovementArea = "criticalImprovementArea";
    private static final String otherImprovement = "otherImprovement";
    private static final String recomendation = "recomendation";
    private static final String lastUpdatedDate = "lastUpdatedDate";

    private static final String complianceValue = "complianceValue";
    private static final String criterionSuggestion = "criterionSuggestion";
    private static final String comment = "comment";
    private static final String auditCriterionComplianceId = "auditCriterionComplianceId";

    private static final String fileName = "fileName";
    private static final String filePath = "filePath";
    private static final String isDeleted = "isDeleted";
    private static final String isSynced = "isSynced";
    private static final String versionName = "versionName";

    private static final String createInspectionYearTable = "create table inspectionYearTable(id integer primary key ,"
            + "inspectionYear text);";

    private static final String createBlockTable = "create table blockTable(id integer primary key ,"
            + "blockLabel int,"
            + "blockName text);";

    private static final String createChapterTable = "create table chapterTable(id integer primary key ,"
            + "blockId int,"
            + "position int,"
            + "chaptername text,"
            + "description text);";

    private static final String createCriterionTable = "create table criterionTable(id integer ,"
            + "blockId int,"
            + "chapterId int,"
            + "position int,"
            + "description text,"
            + "versionName text,"
            + "inspectionYear text,"
            + "criterionType text);";

    private static final String createCommonAuditDetailsTable = "create table commonAuditDetailsTable(auditId integer primary key ,"
            + "auditName text,"
            + "status text,"
            + "overallComplianceValue  text,"
            + "startDate  text,"
            + "endDate text,"
            + "year text,"
            + "versionName text,"
            + "inspectionYearId int,"
            + "inspectionYear text,"
            + "category text);";

    private static final String createAuditorstable = "create table auditorstable(id integer primary key autoincrement,"
            + "auditId int not null,"
            + "auditorsId text,"
            + "auditorsName text);";

    private static final String createSubAuditDetailsTable = "create table subAuditDetailsTable(subAuditId integer primary key ,"
            + "auditId int not null,"
            + "farmId text,"
            + "farmName  text,"
            + "status text,"
            + "address text,"
            + "district  text,"
            + "phoneNumber text,"
            + "city text,"
            + "identificationNumber  text,"
            + "country  text,"
            + "typeOfOperator  text,"
            + "tempFemaleWorkers  text,"
            + "tempMaleWorkers  text,"
            + "crops  text,"
            + "compliancePercentage  text,"
            + "otherCertificationBoards  text,"
            + "typeOfProcess  text,"
            + "isCertifiedBefore  text,"
            + "permenentMaleWorkers  text,"
            + "permenentFemaleWorkers  text,"
            + "totalArea  text,"
            + "areaUnderUtz  text,"
            + "totalWorkersLivingatPeakSeason  text,"
            + "totalProduction  text,"
            + "personInCharge text);";

    private static final String createGroupAuditDetailsTable = "create table groupAuditDetailsTable(id integer primary key autoincrement,"
            + "auditId int not null,"
            + "groupName text,"
            + "headFarmer  text,"
            + "numberOfFarmers  text,"
            + "averageOverallScore text,"
            + "submittedPercentage text,"
            + "ongoingPercentage text);";
   /* private static final String createAuditChapterComplianceTable = "create table auditChapterComplianceTable (id integer primary  key autoincrement,"
            + "auditId int not null,"
            + "subAuditId int not null,"
            + "chapterId int not null,"
            + "strengthArea text,"
            + "criticalImprovementArea text,"
            + "otherImprovement  text,"
            + "recomendation  text,"
            + "lastUpdatedDate text);";*/

    private static final String createAuditCriterionComplianceTable = "create table auditCriterionComplianceTable(auditCriterionComplianceId integer primary key autoincrement,"
            + "auditId int not null,"
            + "subAuditId int not null,"
            + "blockId int not null,"
            + "chapterId int not null,"
            + "criterionId int not null,"
            + "complianceValue text,"
            + "comment text,"
            + "criterionSuggestion text,"
            + "lastUpdatedDate text);";

    private static final String createFilesAttachmentTable = "create table filesAttachmentTable(id integer primary key autoincrement,"
            + "subAuditId int not null,"
            + "criterionId int not null,"
            + "auditCriterionComplianceId int not null,"
            + "fileName text,"
            + "filePath text,"
            + "status text,"
            + "lastUpdatedDate text,"
            + "isDeleted text,"
            + "isSynced text);";
    private static final String createAuditVersionTable = "create table auditVersionTable(id integer primary key autoincrement ,"
            + "chapterId int not null,"
            + "criterionId int not null,"
            + "versionName text);";



    public UtzDatabase(Context context) {
        super(context, databasename, null, databaseversion);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(createChapterTable);
        db.execSQL(createCriterionTable);
        db.execSQL(createCommonAuditDetailsTable);
        db.execSQL(createAuditorstable);
        db.execSQL(createSubAuditDetailsTable);
        db.execSQL(createAuditCriterionComplianceTable);
        db.execSQL(createFilesAttachmentTable);
        db.execSQL(createAuditVersionTable);
        db.execSQL(createGroupAuditDetailsTable);
        db.execSQL(createBlockTable);
        db.execSQL(createInspectionYearTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // ================   ============================================= Adding Datas to Version Table  ============================

    public long addtoVersionTable(Items aa) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(chapterId, aa.getChapterId());
        values.put(criterionId, aa.getCriterionId());
        values.put(versionName, aa.getVersionName());

        long a = db.insert(auditVersionTable, null, values);

        db.close();
        return a;
    }
// ================   ============================================= Adding Datas to inspectionYearTable  ============================

    public long addtoInspectionYearTable(Items aa) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(id, aa.getInspectionYearId());
        values.put(inspectionYear, aa.getInspectionYear());

        long a = db.insert(inspectionYearTable, null, values);

        db.close();
        return a;
    }
    // ================   ============================================= Adding Datas to BLockTable ============================

    public long addtoBlockTable(Items aa) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(id, aa.getBlockId());
        values.put(blockLabel, aa.getBlockLabel());
        values.put(blockName, aa.getBlockName());

        long a = db.insert(blockTable, null, values);

        db.close();
        return a;
    }
    // ================   ============================================= Adding Datas to ChapterTable ============================

    public long addtoChapterTable(Items aa) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
      //  Log.e("chapter id ",""+aa.getChapterId() +"     blockkkk   "+ aa.getBlockId());

        values.put(id, aa.getChapterId());
        values.put(blockId, aa.getBlockId());
        values.put(position, aa.getPosition());
        values.put(chaptername, aa.getChapterName());
        values.put(description, aa.getDescription());

        long a = db.insert(chapterTable, null, values);

        db.close();
        return a;
    }
    // ================   ============================================= Adding Datas to CriterionTable ============================


    public long addtoAuditCriterionTable(Items aa) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

       // Log.e("crit id   ",""+aa.getCriterionId()+"  inspectionYear   "+ aa.getInspectionYear() +"    chapterId   "+aa.getChapterId()+"   vName "+aa.getVersionName());

        values.put(id, aa.getCriterionId());
        values.put(blockId, aa.getBlockId());
        values.put(chapterId, aa.getChapterId());
        values.put(position, aa.getPosition());
        values.put(description, aa.getDescription());
        values.put(criterionType, aa.getCriterionType());
        values.put(inspectionYear, aa.getInspectionYear());
        values.put(versionName, aa.getVersionName());

        long a = db.insert(criterionTable, null, values);

        db.close();
        return a;
    }

    // ================   ============================================= Adding Datas to CommonAuditDetails Table ============================



    public long addtoCommonAuditDetailsTable(Items items) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(auditId, items.getAuditId());
        values.put(auditName, items.getAuditName());
        values.put(status, items.getStatus());
        values.put(overallComplianceValue, items.getOverallComplianceValue());
        values.put(startDate, items.getStartDate());
        values.put(endDate, items.getEndDate());
        values.put(year, items.getYear());
        values.put(versionName, items.getVersionName());
        values.put(inspectionYearId, items.getInspectionYearId());
        values.put(inspectionYear, items.getInspectionYear());
        values.put(category, items.getCategory());


        long a = db.insert(commonAuditDetailsTable, null, values);

        db.close();
        return a;
    }

    // ================   ============================================= Adding Datas to Auditors Table ============================

    public long addtoAuditorsTable(Items items) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(auditId, items.getAuditId());
        values.put(auditorsId, items.getAuditorsId());
        values.put(auditorsName, items.getAuditorName());

        // Log.e("auditors Name",items.getAuditorName());

        long a = db.insert(auditorstable, null, values);

        db.close();
        return a;
    }

    // ================   ============================================= Adding Datas to SubAuditDetailsTable Table ============================


    public long addtoSubAuditDetailsTable(Items items) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(subAuditId, items.getSubAuditId());
        values.put(auditId, items.getAuditId());
        values.put(farmId, items.getFarmId());
        values.put(farmName, items.getFarmName());
        values.put(status, items.getStatus());
        values.put(address, items.getAddress());
        values.put(district, items.getDistrict());
        values.put(phoneNumber, items.getPhoneNumber());
        values.put(city, items.getCity());
        values.put(identificationNumber, items.getIdentificationNumber());
        values.put(country, items.getCountry());
        values.put(typeOfOperator, items.getTypeOfOperator());
        values.put(tempMaleWorkers, items.getTempMaleWorkers());
        values.put(tempFemaleWorkers, items.getTempFemaleWorkers());
        values.put(crops, items.getCrops());
        values.put(compliancePercentage, items.getCompliancePercentage());
        values.put(otherCertificationBoards, items.getOtherCertificationBoards());
        values.put(typeOfProcess, items.getTypeOfProcess());
        values.put(isCertifiedBefore, items.getIsCertifiedBefore());
        values.put(totalProduction, items.getTotalProduction());
        values.put(permenentMaleWorkers, items.getPermenentMaleWorkers());
        values.put(permenentFemaleWorkers, items.getPermenentFemaleWorkers());
        values.put(totalArea, items.getTotalArea());
        values.put(areaUnderUtz, items.getAreaUnderUtz());
        values.put(totalWorkersLivingatPeakSeason, items.getTotalWorkersLivingatPeakSeason());


      /*  Log.e("farmId",items.getFarmId());
        Log.e("identification_number",items.getIdentificationNumber());
*/
        long a = db.insert(subAuditDetailsTable, null, values);

        db.close();
        return a;
    }

    // ================   ============================================= Adding Datas to groupAuditDetailsTable ============================

    public long addtoGroupAuditDetailsTable(Items aa) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(auditId, aa.getAuditId());
        values.put(groupName, aa.getGroupName());
        values.put(averageOverallScore, aa.getAverageOverallScore());
        values.put(submittedPercentage, aa.getSubmittedPercentage());

        long a = db.insert(groupAuditDetailsTable, null, values);

        db.close();
        return a;
    }

    // ================   ============================================= Adding Datas to AuditCriterionComplianceTable ============================

    public long addtoAuditCriterionComplianceTable(Items aa) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        /*Log.e("criterionId ",""+ aa.getCriterionId());
        Log.e("auditId ",""+ aa.getAuditId());
        Log.e("subAuditId ",""+ aa.getSubAuditId());*/
        //Log.e("criterionId ",""+ aa.getCriterionId());

        values.put(auditId, aa.getAuditId());
        values.put(subAuditId, aa.getSubAuditId());
        values.put(blockId, aa.getBlockId());
        values.put(chapterId, aa.getChapterId());
        values.put(criterionId, aa.getCriterionId());
        values.put(complianceValue, aa.getComplianceValue());
        values.put(comment, aa.getComment());
        values.put(criterionSuggestion, aa.getCriterionSuggestion());
        values.put(lastUpdatedDate, aa.getLastUpdatedDate());

        long a = db.insert(auditCriterionComplianceTable, null, values);

        db.close();
        return a;
    }

    // ================   ============================================= Adding Datas to FilesAttachmentTable ============================


    public long addtoFilesAttachmentTable(Items aa) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(subAuditId, aa.getSubAuditId());
        values.put(criterionId, aa.getCriterionId());
        values.put(auditCriterionComplianceId, aa.getAuditCriterionComplianceId());
        values.put(fileName, aa.getFileName());
        values.put(filePath, aa.getFilePath());
        values.put(status, aa.getStatus());
        values.put(lastUpdatedDate, aa.getLastUpdatedDate());
        values.put(isDeleted, aa.getIsDeleted());
        values.put(isSynced, aa.getIsSynced());


        long a = db.insert(filesAttachmentTable, null, values);

        db.close();
        return a;
    }



    // ================================================================================================== Getting inspectionYearTable Details ============================


    public List<Items> getInspectionYearTableDetails() {
        SQLiteDatabase db = this.getReadableDatabase();

        List<Items> contactList = new ArrayList<Items>();
        String countQuery = "SELECT  * FROM inspectionYearTable ";

        Cursor cursor = db.rawQuery(countQuery, null);
        if (cursor != null)
            cursor.moveToFirst();

        if (!cursor.isAfterLast()) {
            do {

                Items aa = new Items();
                aa.setInspectionYearId(cursor.getInt(0));
                aa.setInspectionYear(cursor.getString(1));

                contactList.add(aa);

            } while (cursor.moveToNext());
        }
        return contactList;
    }
    // ================================================================================================== Getting VersionTable Details ============================


    public List<Items> getVersionTableDetails() {
        SQLiteDatabase db = this.getReadableDatabase();

        List<Items> contactList = new ArrayList<Items>();
        String countQuery = "SELECT  * FROM auditVersionTable ";

        Cursor cursor = db.rawQuery(countQuery, null);
        if (cursor != null)
            cursor.moveToFirst();

        if (!cursor.isAfterLast()) {
            do {

                Items aa = new Items();
                aa.setChapterId(cursor.getInt(1));
                aa.setCriterionId(cursor.getInt(2));
                aa.setVersionName(cursor.getString(3));

                contactList.add(aa);

            } while (cursor.moveToNext());
        }
        return contactList;
    }


    // =================================================================================================== Getting  BlockTable Details ============================

    public List<Items> getBlockTableDetails() {
        SQLiteDatabase db = this.getReadableDatabase();

        List<Items> contactList = new ArrayList<Items>();
        String countQuery = "SELECT  * FROM blockTable ";

        Cursor cursor = db.rawQuery(countQuery, null);
        if (cursor != null)
            cursor.moveToFirst();

        if (!cursor.isAfterLast()) {
            do {

                Items aa = new Items();
                aa.setBlockId(cursor.getInt(0));
                aa.setBlockLabel(cursor.getString(1));
                aa.setBlockName(cursor.getString(2));

                contactList.add(aa);

            } while (cursor.moveToNext());
        }
        return contactList;
    }
    public List<Items> getBlockTableDetails(int blockid) {
        SQLiteDatabase db = this.getReadableDatabase();

        List<Items> contactList = new ArrayList<Items>();
        String countQuery = "SELECT  * FROM blockTable where id = "+blockid;

        Cursor cursor = db.rawQuery(countQuery, null);
        if (cursor != null)
            cursor.moveToFirst();

        if (!cursor.isAfterLast()) {
            do {

                Items aa = new Items();
                aa.setBlockId(cursor.getInt(0));
                aa.setBlockLabel(cursor.getString(1));
                aa.setBlockName(cursor.getString(2));

                contactList.add(aa);

            } while (cursor.moveToNext());
        }
        return contactList;
    }
    public List<Items> getBlockDetails (int[] blockids) {
        SQLiteDatabase db = this.getReadableDatabase();

        String inClause = Arrays.toString(blockids);
        // Log.e("clause..........", inClause);
        inClause = inClause.replace("[", "(");
        inClause = inClause.replace("]", ")");


        List<Items> contactList = new ArrayList<Items>();
        String countQuery = "SELECT * FROM blockTable where id in "+inClause;

        Cursor cursor = db.rawQuery(countQuery, null);
        if (cursor != null)
            cursor.moveToFirst();

        if (!cursor.isAfterLast()) {
            do {
                Items aa = new Items();
                aa.setBlockId(cursor.getInt(0));
                aa.setBlockLabel(cursor.getString(1));
                aa.setBlockName(cursor.getString(2));
                contactList.add(aa);

            } while (cursor.moveToNext());
        }
        return contactList;
    }


    // =================================================================================================== Getting ChapterTable Details ============================

    public List<Items> getChapterTableDetails() {
        SQLiteDatabase db = this.getReadableDatabase();

        List<Items> contactList = new ArrayList<Items>();
        String countQuery = "SELECT * FROM chapterTable ";

        Cursor cursor = db.rawQuery(countQuery, null);
        if (cursor != null)
            cursor.moveToFirst();

        if (!cursor.isAfterLast()) {
            do {
                Items aa = new Items();

                aa.setChapterId(cursor.getInt(0));
                aa.setBlockId(cursor.getInt(1));
                aa.setPosition(cursor.getString(2));
                aa.setChapterName(cursor.getString(3));
                aa.setDescription(cursor.getString(4));

                contactList.add(aa);

            } while (cursor.moveToNext());
        }
        return contactList;
    }
    public List<Items> getChapterTableDetails(int blockId,String s) {
        SQLiteDatabase db = this.getReadableDatabase();

        List<Items> contactList = new ArrayList<Items>();
        String countQuery = "SELECT * FROM chapterTable where blockId = "+blockId;

        Cursor cursor = db.rawQuery(countQuery, null);
        if (cursor != null)
            cursor.moveToFirst();

        if (!cursor.isAfterLast()) {
            do {
                Items aa = new Items();

                aa.setChapterId(cursor.getInt(0));
                aa.setBlockId(cursor.getInt(1));
                aa.setPosition(cursor.getString(2));
                aa.setChapterName(cursor.getString(3));
                aa.setDescription(cursor.getString(4));

                contactList.add(aa);

            } while (cursor.moveToNext());
        }
        return contactList;
    }
    public List<Items> getChapterTableDetails (int chapterid) {
        SQLiteDatabase db = this.getReadableDatabase();


        List<Items> contactList = new ArrayList<Items>();
        String countQuery = "SELECT * FROM chapterTable where id = "+chapterid;

        Cursor cursor = db.rawQuery(countQuery, null);
        if (cursor != null)
            cursor.moveToFirst();

        if (!cursor.isAfterLast()) {
            do {
                Items aa = new Items();

                aa.setChapterId(cursor.getInt(0));
                aa.setBlockId(cursor.getInt(1));
                aa.setPosition(cursor.getString(2));
                aa.setChapterName(cursor.getString(3));
                aa.setDescription(cursor.getString(4));

                contactList.add(aa);

            } while (cursor.moveToNext());
        }
        return contactList;
    }

    public List<Items> getChapterTableDetails (int[] chapterids) {
        SQLiteDatabase db = this.getReadableDatabase();

        String inClause = Arrays.toString(chapterids);
        // Log.e("clause..........", inClause);
        inClause = inClause.replace("[", "(");
        inClause = inClause.replace("]", ")");


        List<Items> contactList = new ArrayList<Items>();
        String countQuery = "SELECT  DISTINCT * FROM chapterTable where id in "+inClause;

        Cursor cursor = db.rawQuery(countQuery, null);
        if (cursor != null)
            cursor.moveToFirst();

        if (!cursor.isAfterLast()) {
            do {
                Items aa = new Items();

                aa.setChapterId(cursor.getInt(0));
                aa.setBlockId(cursor.getInt(1));
                aa.setPosition(cursor.getString(2));
                aa.setChapterName(cursor.getString(3));
                aa.setDescription(cursor.getString(4));

                contactList.add(aa);

            } while (cursor.moveToNext());
        }
        return contactList;
    }

    // =================================================================================================== Getting CriterionTable Details ============================

    public List<Items> getCriterionTableDetails() {
        SQLiteDatabase db = this.getReadableDatabase();

        List<Items> contactList = new ArrayList<Items>();
        String countQuery = "SELECT * FROM criterionTable ";

        Cursor cursor = db.rawQuery(countQuery, null);
        if (cursor != null)
            cursor.moveToFirst();

        if (!cursor.isAfterLast()) {
            do {

                Items aa = new Items();
                aa.setCriterionId(cursor.getInt(0));
                aa.setBlockId(cursor.getInt(1));
                aa.setChapterId(cursor.getInt(2));
                aa.setPosition(cursor.getString(3));
                aa.setDescription(cursor.getString(4));
                aa.setVersionName(cursor.getString(5));
                aa.setInspectionYear(cursor.getString(6));
                aa.setCriterionType(cursor.getString(7));
                contactList.add(aa);

            } while (cursor.moveToNext());
        }
        return contactList;
    }
    public List<Items> getCriterionTableDetails(int blockid,String s,String ss) {
        SQLiteDatabase db = this.getReadableDatabase();

        List<Items> contactList = new ArrayList<Items>();
        String countQuery = "SELECT * FROM criterionTable where blockId = "+blockid;

        Cursor cursor = db.rawQuery(countQuery, null);
        if (cursor != null)
            cursor.moveToFirst();

        if (!cursor.isAfterLast()) {
            do {

                Items aa = new Items();
                aa.setCriterionId(cursor.getInt(0));
                aa.setBlockId(cursor.getInt(1));
                aa.setChapterId(cursor.getInt(2));
                aa.setPosition(cursor.getString(3));
                aa.setDescription(cursor.getString(4));
                aa.setVersionName(cursor.getString(5));
                aa.setInspectionYear(cursor.getString(6));
                aa.setCriterionType(cursor.getString(7));
                contactList.add(aa);

            } while (cursor.moveToNext());
        }
        return contactList;
    }
    public List<Items> getCriterionTableDetails(int blockid,String versionName,String inspectionYear,int i) {

        SQLiteDatabase db = this.getReadableDatabase();
        List<Items> contactList = new ArrayList<Items>();
        String countQuery = "SELECT * FROM criterionTable where blockId = "+blockid + " and versionName = '"
                +versionName+"' and inspectionYear = '"+inspectionYear+"'";

        Cursor cursor = db.rawQuery(countQuery, null);
        if (cursor != null)
            cursor.moveToFirst();

        if (!cursor.isAfterLast()) {
            do {

                Items aa = new Items();
                aa.setCriterionId(cursor.getInt(0));
                aa.setBlockId(cursor.getInt(1));
                aa.setChapterId(cursor.getInt(2));
                aa.setPosition(cursor.getString(3));
                aa.setDescription(cursor.getString(4));
                aa.setVersionName(cursor.getString(5));
                aa.setInspectionYear(cursor.getString(6));
                aa.setCriterionType(cursor.getString(7));
                contactList.add(aa);

            } while (cursor.moveToNext());
        }
        return contactList;
    }

    public List<Items> getCriterionTableDetails (int[] chapterids,String criterionType,String versionName,String inspectionYear) {
        SQLiteDatabase db = this.getReadableDatabase();

        String inClause = Arrays.toString(chapterids);
        // Log.e("clause..........", inClause);
        inClause = inClause.replace("[", "(");
        inClause = inClause.replace("]", ")");


        List<Items> contactList = new ArrayList<Items>();
        String countQuery = "SELECT * FROM criterionTable where id in "+inClause+" and criterionType = '"+criterionType+"' and versionName = '"
                +versionName+"' and inspectionYear = '"+inspectionYear+"'";

        Cursor cursor = db.rawQuery(countQuery, null);
        if (cursor != null)
            cursor.moveToFirst();

        if (!cursor.isAfterLast()) {
            do {
                Items aa = new Items();

                aa.setCriterionId(cursor.getInt(0));
                aa.setBlockId(cursor.getInt(1));
                aa.setChapterId(cursor.getInt(2));
                aa.setPosition(cursor.getString(3));
                aa.setDescription(cursor.getString(4));
                aa.setVersionName(cursor.getString(5));
                aa.setInspectionYear(cursor.getString(6));
                aa.setCriterionType(cursor.getString(7));
                contactList.add(aa);

            } while (cursor.moveToNext());
        }
        return contactList;
    }

    public List<Items> getCriterionTableDetails (int[] criterionids,String criterionType) {
        SQLiteDatabase db = this.getReadableDatabase();

        String inClause = Arrays.toString(criterionids);
        // Log.e("clause..........", inClause);
        inClause = inClause.replace("[", "(");
        inClause = inClause.replace("]", ")");


        List<Items> contactList = new ArrayList<Items>();
        String countQuery = "SELECT * FROM criterionTable where id in "+inClause+" and criterionType = '"+criterionType+"' and versionName = '"
                +versionName+"' and inspectionYear = '"+inspectionYear+"'";

        Cursor cursor = db.rawQuery(countQuery, null);
        if (cursor != null)
            cursor.moveToFirst();

        if (!cursor.isAfterLast()) {
            do {
                Items aa = new Items();

                aa.setCriterionId(cursor.getInt(0));
                aa.setBlockId(cursor.getInt(1));
                aa.setChapterId(cursor.getInt(2));
                aa.setPosition(cursor.getString(3));
                aa.setDescription(cursor.getString(4));
                aa.setVersionName(cursor.getString(5));
                aa.setInspectionYear(cursor.getString(6));
                aa.setCriterionType(cursor.getString(7));
                contactList.add(aa);

            } while (cursor.moveToNext());
        }
        return contactList;
    }
    public List<Items> getCriterionTableDetailsResult (int[] criterionids,String criterionType,String inspectionyear) {
        SQLiteDatabase db = this.getReadableDatabase();

        String inClause = Arrays.toString(criterionids);
        // Log.e("clause..........", inClause);
        inClause = inClause.replace("[", "(");
        inClause = inClause.replace("]", ")");

        List<Items> contactList = new ArrayList<Items>();
        String countQuery = "SELECT * FROM criterionTable where id in "+inClause+" and criterionType = '"
                +criterionType+"' and inspectionYear ='"+inspectionyear+"'";

        Cursor cursor = db.rawQuery(countQuery, null);
        if (cursor != null)
            cursor.moveToFirst();

        if (!cursor.isAfterLast()) {
            do {
                Items aa = new Items();

                aa.setCriterionId(cursor.getInt(0));
                aa.setBlockId(cursor.getInt(1));
                aa.setChapterId(cursor.getInt(2));
                aa.setPosition(cursor.getString(3));
                aa.setDescription(cursor.getString(4));
                aa.setVersionName(cursor.getString(5));
                aa.setInspectionYear(cursor.getString(6));
                aa.setCriterionType(cursor.getString(7));
                contactList.add(aa);

            } while (cursor.moveToNext());
        }
        return contactList;
    }
    public List<Items> getCriterionTableDetailsResult (int[] criterionids) {
        SQLiteDatabase db = this.getReadableDatabase();

        String inClause = Arrays.toString(criterionids);
        // Log.e("clause..........", inClause);
        inClause = inClause.replace("[", "(");
        inClause = inClause.replace("]", ")");


        List<Items> contactList = new ArrayList<Items>();
        String countQuery = "SELECT * FROM criterionTable where id in "+inClause;

        Cursor cursor = db.rawQuery(countQuery, null);
        if (cursor != null)
            cursor.moveToFirst();

        if (!cursor.isAfterLast()) {
            do {
                Items aa = new Items();

                aa.setCriterionId(cursor.getInt(0));
                aa.setBlockId(cursor.getInt(1));
                aa.setChapterId(cursor.getInt(2));
                aa.setPosition(cursor.getString(3));
                aa.setDescription(cursor.getString(4));
                aa.setVersionName(cursor.getString(5));
                aa.setInspectionYear(cursor.getString(6));
                aa.setCriterionType(cursor.getString(7));
                contactList.add(aa);

            } while (cursor.moveToNext());
        }
        return contactList;
    }


    public List<Items> getCriterionTableDetails(int criterionId,String s,String year,String y) {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Items> contactList = new ArrayList<Items>();
        String countQuery = "SELECT * FROM criterionTable where id = "+criterionId+" AND versionName = '"+s+"' AND inspectionYear = '"+year+"'";
        Cursor cursor = db.rawQuery(countQuery, null);
        if (cursor != null)
            cursor.moveToFirst();

        if (!cursor.isAfterLast()) {
            do {

                Items aa = new Items();
                aa.setCriterionId(cursor.getInt(0));
                aa.setBlockId(cursor.getInt(1));
                aa.setChapterId(cursor.getInt(2));
                aa.setPosition(cursor.getString(3));
                aa.setDescription(cursor.getString(4));
                aa.setVersionName(cursor.getString(5));
                aa.setInspectionYear(cursor.getString(6));
                aa.setCriterionType(cursor.getString(7));
                contactList.add(aa);

            } while (cursor.moveToNext());
        }
        return contactList;
    }
    public List<Items> getCriterionTableDetails(int chapterId) {
        SQLiteDatabase db = this.getReadableDatabase();

        List<Items> contactList = new ArrayList<Items>();
        String countQuery = "SELECT * FROM criterionTable where chapterId = "+chapterId;

        Cursor cursor = db.rawQuery(countQuery, null);
        if (cursor != null)
            cursor.moveToFirst();

        if (!cursor.isAfterLast()) {
            do {

                Items aa = new Items();
                aa.setCriterionId(cursor.getInt(0));
                aa.setBlockId(cursor.getInt(1));
                aa.setChapterId(cursor.getInt(2));
                aa.setPosition(cursor.getString(3));
                aa.setDescription(cursor.getString(4));
                aa.setVersionName(cursor.getString(5));
                aa.setInspectionYear(cursor.getString(6));
                aa.setCriterionType(cursor.getString(7));
                contactList.add(aa);


            } while (cursor.moveToNext());
        }
        return contactList;
    }


    public List<Items> getCriterionTableDetails(String versionname,String inspectionyear) {
        SQLiteDatabase db = this.getReadableDatabase();

        List<Items> contactList = new ArrayList<Items>();
        String countQuery = "SELECT * FROM criterionTable where versionName = '"+versionname+"' and " +
                "inspectionYear = '"+inspectionyear+"'";

       // Log.e("qqqqqq  ",countQuery);
        Cursor cursor = db.rawQuery(countQuery, null);
        if (cursor != null)
            cursor.moveToFirst();

        if (!cursor.isAfterLast()) {
            do {

                Items aa = new Items();

               // Log.e("cid..   ",""+cursor.getInt(0) +"   ChapterId   "+cursor.getInt(2) );
                aa.setCriterionId(cursor.getInt(0));
                aa.setBlockId(cursor.getInt(1));
                aa.setChapterId(cursor.getInt(2));
                aa.setPosition(cursor.getString(3));
                aa.setDescription(cursor.getString(4));
                aa.setVersionName(cursor.getString(5));
                aa.setInspectionYear(cursor.getString(6));
                aa.setCriterionType(cursor.getString(7));
                contactList.add(aa);


            } while (cursor.moveToNext());
        }
        return contactList;
    }

    public List<Items> getCriterionTableDetails(String versionname,String inspectionyear,String criterionType) {
        SQLiteDatabase db = this.getReadableDatabase();

        List<Items> contactList = new ArrayList<Items>();
        String countQuery = "SELECT * FROM criterionTable where versionName = '"+versionname+"' and " +
                "inspectionYear = '"+inspectionyear+"' and criterionType = '"+criterionType+"'";

        Cursor cursor = db.rawQuery(countQuery, null);
        if (cursor != null)
            cursor.moveToFirst();

        if (!cursor.isAfterLast()) {
            do {

                Items aa = new Items();
                aa.setCriterionId(cursor.getInt(0));
                aa.setBlockId(cursor.getInt(1));
                aa.setChapterId(cursor.getInt(2));
                aa.setPosition(cursor.getString(3));
                aa.setDescription(cursor.getString(4));
                aa.setVersionName(cursor.getString(5));
                aa.setInspectionYear(cursor.getString(6));
                aa.setCriterionType(cursor.getString(7));
                contactList.add(aa);


            } while (cursor.moveToNext());
        }
        return contactList;
    }
    public List<Items> getCriterionTableDetails(String versionname,String inspectionyear,int blockId) {
        SQLiteDatabase db = this.getReadableDatabase();

        List<Items> contactList = new ArrayList<Items>();
        String countQuery = "SELECT * FROM criterionTable where versionName = '"+versionname+"' and " +
                "inspectionYear = '"+inspectionyear+"' and blockId = "+blockId;

        Cursor cursor = db.rawQuery(countQuery, null);
        if (cursor != null)
            cursor.moveToFirst();

        if (!cursor.isAfterLast()) {
            do {

                Items aa = new Items();
                aa.setCriterionId(cursor.getInt(0));
                aa.setBlockId(cursor.getInt(1));
                aa.setChapterId(cursor.getInt(2));
                aa.setPosition(cursor.getString(3));
                aa.setDescription(cursor.getString(4));
                aa.setVersionName(cursor.getString(5));
                aa.setInspectionYear(cursor.getString(6));
                aa.setCriterionType(cursor.getString(7));
                contactList.add(aa);


            } while (cursor.moveToNext());
        }
        return contactList;
    }
    // =================================================================================================== Getting GroupAuditDetailsTable Details ============================

    public List<Items> getGroupAuditDetailsTabl(int auditId) {
        SQLiteDatabase db = this.getReadableDatabase();

        List<Items> contactList = new ArrayList<Items>();
        String countQuery = "SELECT * FROM groupAuditDetailsTable where auditId = "+auditId;

        Cursor cursor = db.rawQuery(countQuery, null);
        if (cursor != null)
            cursor.moveToFirst();

        if (!cursor.isAfterLast()) {
            do {

                Items aa = new Items();
                aa.setAuditId(cursor.getInt(1));
                aa.setGroupName(cursor.getString(2));
                aa.setAverageOverallScore(cursor.getString(5));
                aa.setSubmittedPercentage(cursor.getString(6));

                contactList.add(aa);

            } while (cursor.moveToNext());
        }
        return contactList;
    }

    // =================================================================================================== Getting CommonAuditDetailsTable Details ============================


    public List<Items> getCommonAuditTableDetails() {
        SQLiteDatabase db = this.getReadableDatabase();

        List<Items> contactList = new ArrayList<Items>();
        String countQuery = "SELECT * FROM commonAuditDetailsTable";

        Cursor cursor = db.rawQuery(countQuery, null);
        if (cursor != null)
            cursor.moveToFirst();

        if (!cursor.isAfterLast()) {
            do {

                Items aa = new Items();
                aa.setAuditId(cursor.getInt(0));
                aa.setAuditName(cursor.getString(1));
                aa.setStatus(cursor.getString(2));
                aa.setOverallComplianceValue(cursor.getString(3));
                aa.setStartDate(cursor.getString(4));
                aa.setEndDate(cursor.getString(5));
                aa.setYear(cursor.getString(6));
                aa.setVersionName(cursor.getString(7));
                aa.setInspectionYearId(cursor.getInt(8));
                aa.setInspectionYear(cursor.getString(9));
                aa.setCategory(cursor.getString(10));

                contactList.add(aa);

            } while (cursor.moveToNext());
        }
        return contactList;
    }
    public List<Items> getCommonAuditTableDetails(String category) {
        SQLiteDatabase db = this.getReadableDatabase();

        List<Items> contactList = new ArrayList<Items>();
        String countQuery = "SELECT * FROM commonAuditDetailsTable where category = '"+category+"'";

        Cursor cursor = db.rawQuery(countQuery, null);
        if (cursor != null)
            cursor.moveToFirst();

        if (!cursor.isAfterLast()) {
            do {

                Items aa = new Items();
                aa.setAuditId(cursor.getInt(0));
                aa.setAuditName(cursor.getString(1));
                aa.setStatus(cursor.getString(2));
                aa.setOverallComplianceValue(cursor.getString(3));
                aa.setStartDate(cursor.getString(4));
                aa.setEndDate(cursor.getString(5));
                aa.setYear(cursor.getString(6));
                aa.setVersionName(cursor.getString(7));
                aa.setInspectionYearId(cursor.getInt(8));
                aa.setInspectionYear(cursor.getString(9));
                aa.setCategory(cursor.getString(10));

                contactList.add(aa);

            } while (cursor.moveToNext());
        }
        return contactList;
    }
    public List<Items> getCommonAuditTableDetails(int auditid) {
        SQLiteDatabase db = this.getReadableDatabase();

        List<Items> contactList = new ArrayList<Items>();
        String countQuery = "SELECT * FROM commonAuditDetailsTable where auditId = "+auditid;

        Cursor cursor = db.rawQuery(countQuery, null);
        if (cursor != null)
            cursor.moveToFirst();

        if (!cursor.isAfterLast()) {
            do {

                Items aa = new Items();
                aa.setAuditId(cursor.getInt(0));
                aa.setAuditName(cursor.getString(1));
                aa.setStatus(cursor.getString(2));
                aa.setOverallComplianceValue(cursor.getString(3));
                aa.setStartDate(cursor.getString(4));
                aa.setEndDate(cursor.getString(5));
                aa.setYear(cursor.getString(6));
                aa.setVersionName(cursor.getString(7));
                aa.setInspectionYearId(cursor.getInt(8));
                aa.setInspectionYear(cursor.getString(9));
                aa.setCategory(cursor.getString(10));

                contactList.add(aa);

            } while (cursor.moveToNext());
        }
        return contactList;
    }
    public void updateCommonAuditDetails(Items listItems, String auditid) {


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(startDate, listItems.getStartDate());
        values.put(endDate, listItems.getEndDate());

        db.update(commonAuditDetailsTable, values, auditId + " = ? ", new String[] { auditid });

    }


    // =================================================================================================== Getting AuditorsTable Details ============================

    public List<Items> getAuditorstable(int auditId) {
        SQLiteDatabase db = this.getReadableDatabase();

        List<Items> contactList = new ArrayList<Items>();
        String countQuery = "SELECT * FROM auditorstable where auditId = "+auditId;

        Cursor cursor = db.rawQuery(countQuery, null);
        if (cursor != null)
            cursor.moveToFirst();

        if (!cursor.isAfterLast()) {
            do {

                Items aa = new Items();
                aa.setAuditId(cursor.getInt(1));
                aa.setAuditorsId(cursor.getString(2));
                aa.setAuditorName(cursor.getString(3));

                contactList.add(aa);

            } while (cursor.moveToNext());
        }
        return contactList;
    }

    // =================================================================================================== Getting and updating SubAuditDetailsTable Details ============================

    public List<Items> getSubAuditDetailsTable() {
        SQLiteDatabase db = this.getReadableDatabase();

        List<Items> contactList = new ArrayList<Items>();
        String countQuery = "SELECT * FROM subAuditDetailsTable";

        Cursor cursor = db.rawQuery(countQuery, null);
        if (cursor != null)
            cursor.moveToFirst();

        if (!cursor.isAfterLast()) {
            do {

                Items aa = new Items();

                aa.setSubAuditId(cursor.getInt(0));
                aa.setAuditId(cursor.getInt(1));
                aa.setFarmId(cursor.getString(2));
                aa.setFarmName(cursor.getString(3));
                aa.setStatus(cursor.getString(4));
                aa.setAddress(cursor.getString(5));
                aa.setDistrict(cursor.getString(6));
                aa.setPhoneNumber(cursor.getString(7));
                aa.setCity(cursor.getString(8));
                aa.setIdentificationNumber(cursor.getString(9));
                aa.setCountry(cursor.getString(10));
                aa.setTypeOfOperator(cursor.getString(11));
                aa.setTempMaleWorkers(cursor.getString(12));
                aa.setTempFemaleWorkers(cursor.getString(13));
                aa.setCrops(cursor.getString(14));
                aa.setCompliancePercentage(cursor.getString(15));
                aa.setOtherCertificationBoards(cursor.getString(16));
                aa.setTypeOfProcess(cursor.getString(17));
                aa.setIsCertifiedBefore(cursor.getString(18));
                aa.setPermenentMaleWorkers(cursor.getString(19));
                aa.setPermenentFemaleWorkers(cursor.getString(20));
                aa.setTotalArea(cursor.getString(21));
                aa.setAreaUnderUtz(cursor.getString(22));
                aa.setTotalWorkersLivingatPeakSeason(cursor.getString(23));
                aa.setTotalProduction(cursor.getString(24));
                aa.setPersonInCharge(cursor.getString(25));

                contactList.add(aa);

            } while (cursor.moveToNext());
        }
        return contactList;
    }
    public List<Items> getSubAuditDetailsTable(int auditId,String str) {
        SQLiteDatabase db = this.getReadableDatabase();

        List<Items> contactList = new ArrayList<Items>();
        String countQuery = "SELECT * FROM subAuditDetailsTable WHERE auditId = "+auditId;

        Cursor cursor = db.rawQuery(countQuery, null);
        if (cursor != null)
            cursor.moveToFirst();

        if (!cursor.isAfterLast()) {
            do {

                Items aa = new Items();

                aa.setSubAuditId(cursor.getInt(0));
                aa.setAuditId(cursor.getInt(1));
                aa.setFarmId(cursor.getString(2));
                aa.setFarmName(cursor.getString(3));
                aa.setStatus(cursor.getString(4));
                aa.setAddress(cursor.getString(5));
                aa.setDistrict(cursor.getString(6));
                aa.setPhoneNumber(cursor.getString(7));
                aa.setCity(cursor.getString(8));
                aa.setIdentificationNumber(cursor.getString(9));
                aa.setCountry(cursor.getString(10));
                aa.setTypeOfOperator(cursor.getString(11));
                aa.setTempMaleWorkers(cursor.getString(12));
                aa.setTempFemaleWorkers(cursor.getString(13));
                aa.setCrops(cursor.getString(14));
                aa.setCompliancePercentage(cursor.getString(15));
                aa.setOtherCertificationBoards(cursor.getString(16));
                aa.setTypeOfProcess(cursor.getString(17));
                aa.setIsCertifiedBefore(cursor.getString(18));
                aa.setPermenentMaleWorkers(cursor.getString(19));
                aa.setPermenentFemaleWorkers(cursor.getString(20));
                aa.setTotalArea(cursor.getString(21));
                aa.setAreaUnderUtz(cursor.getString(22));
                aa.setTotalWorkersLivingatPeakSeason(cursor.getString(23));
                aa.setTotalProduction(cursor.getString(24));
                aa.setPersonInCharge(cursor.getString(25));

                contactList.add(aa);

            } while (cursor.moveToNext());
        }
        return contactList;
    }

    public List<Items> getSubAuditDetailsTable(int subauditid) {
        SQLiteDatabase db = this.getReadableDatabase();

        List<Items> contactList = new ArrayList<Items>();
        String countQuery = "SELECT * FROM subAuditDetailsTable where subAuditId = "+subauditid;

        Cursor cursor = db.rawQuery(countQuery, null);
        if (cursor != null)
            cursor.moveToFirst();

        if (!cursor.isAfterLast()) {
            do {

                Items aa = new Items();

                aa.setSubAuditId(cursor.getInt(0));
                aa.setAuditId(cursor.getInt(1));
                aa.setFarmId(cursor.getString(2));
                aa.setFarmName(cursor.getString(3));
                aa.setStatus(cursor.getString(4));
                aa.setAddress(cursor.getString(5));
                aa.setDistrict(cursor.getString(6));
                aa.setPhoneNumber(cursor.getString(7));
                aa.setCity(cursor.getString(8));
                aa.setIdentificationNumber(cursor.getString(9));
                aa.setCountry(cursor.getString(10));
                aa.setTypeOfOperator(cursor.getString(11));
                aa.setTempMaleWorkers(cursor.getString(12));
                aa.setTempFemaleWorkers(cursor.getString(13));
                aa.setCrops(cursor.getString(14));
                aa.setCompliancePercentage(cursor.getString(15));
                aa.setOtherCertificationBoards(cursor.getString(16));
                aa.setTypeOfProcess(cursor.getString(17));
                aa.setIsCertifiedBefore(cursor.getString(18));
                aa.setPermenentMaleWorkers(cursor.getString(19));
                aa.setPermenentFemaleWorkers(cursor.getString(20));
                aa.setTotalArea(cursor.getString(21));
                aa.setAreaUnderUtz(cursor.getString(22));
                aa.setTotalWorkersLivingatPeakSeason(cursor.getString(23));
                aa.setTotalProduction(cursor.getString(24));
                aa.setPersonInCharge(cursor.getString(25));

                contactList.add(aa);

            } while (cursor.moveToNext());
        }
        return contactList;
    }

    public void updateSubAuditDetailsTable(Items items, String subAuditid) {


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(address, items.getAddress());
        values.put(district, items.getDistrict());
        values.put(phoneNumber, items.getPhoneNumber());
        values.put(city, items.getCity());
        values.put(totalArea, items.getTotalArea());
        values.put(country, items.getCountry());
        values.put(permenentMaleWorkers, items.getPermenentMaleWorkers());
        values.put(permenentFemaleWorkers, items.getPermenentFemaleWorkers());
        values.put(totalProduction, items.getTotalProduction());
        values.put(isCertifiedBefore, items.getIsCertifiedBefore());
        values.put(tempMaleWorkers, items.getTempMaleWorkers());
        values.put(tempFemaleWorkers, items.getTempFemaleWorkers());
        values.put(typeOfOperator, items.getTypeOfOperator());
        values.put(typeOfProcess, items.getTypeOfProcess());
        values.put(otherCertificationBoards, items.getOtherCertificationBoards());
        values.put(areaUnderUtz, items.getAreaUnderUtz());
        values.put(totalWorkersLivingatPeakSeason, items.getTotalWorkersLivingatPeakSeason());

        db.update(subAuditDetailsTable, values, subAuditId + " = ? ", new String[] { subAuditid });

    }
    // =================================================================================================== Getting AuditCriterionComplianceTable Details ============================

    public List<Items> getAuditCriterionComplianceDetails(int subauditId,int criterionId) {
        SQLiteDatabase db = this.getReadableDatabase();

        List<Items> contactList = new ArrayList<Items>();
        String countQuery = "SELECT * FROM auditCriterionComplianceTable where subAuditId = "+subauditId+" and criterionId = "+criterionId;

        Cursor cursor = db.rawQuery(countQuery, null);
        if (cursor != null)
            cursor.moveToFirst();

        if (!cursor.isAfterLast()) {
            do {


                Items aa = new Items();
                aa.setAuditCriterionComplianceId(cursor.getInt(0));
                aa.setAuditId(cursor.getInt(1));
                aa.setSubAuditId(cursor.getInt(2));
                aa.setBlockId(cursor.getInt(3));
                aa.setChapterId(cursor.getInt(4));
                aa.setCriterionId(cursor.getInt(5));
                aa.setComplianceValue(cursor.getString(6));
                aa.setComment(cursor.getString(7));
                aa.setCriterionSuggestion(cursor.getString(8));
                aa.setLastUpdatedDate(cursor.getString(9));

                contactList.add(aa);

            } while (cursor.moveToNext());
        }
        return contactList;
    }

    public List<Items> getAuditCriterionComplianceDetails(int subauditId) {
        SQLiteDatabase db = this.getReadableDatabase();

        List<Items> contactList = new ArrayList<Items>();
        String countQuery = "SELECT * FROM auditCriterionComplianceTable where subAuditId = "+subauditId;
        Log.e("query...",countQuery);

        Cursor cursor = db.rawQuery(countQuery, null);
        if (cursor != null)
            cursor.moveToFirst();

        if (!cursor.isAfterLast()) {
            do {

                Items aa = new Items();
                aa.setAuditCriterionComplianceId(cursor.getInt(0));
                aa.setAuditId(cursor.getInt(1));
                aa.setSubAuditId(cursor.getInt(2));
                aa.setBlockId(cursor.getInt(3));
                aa.setChapterId(cursor.getInt(4));
                aa.setCriterionId(cursor.getInt(5));
                aa.setComplianceValue(cursor.getString(6));
                aa.setComment(cursor.getString(7));
                aa.setCriterionSuggestion(cursor.getString(8));
                aa.setLastUpdatedDate(cursor.getString(9));


                contactList.add(aa);

            } while (cursor.moveToNext());
        }
        return contactList;
    }

    public List<Items> getAuditCriterionComplianceDetails(int subauditId,String cv) {
        SQLiteDatabase db = this.getReadableDatabase();

        List<Items> contactList = new ArrayList<Items>();
        String countQuery = "SELECT * FROM auditCriterionComplianceTable where subAuditId = "
                +subauditId+" and complianceValue = '"+cv+"'";

        Cursor cursor = db.rawQuery(countQuery, null);
        if (cursor != null)
            cursor.moveToFirst();

        if (!cursor.isAfterLast()) {
            do {

                Items aa = new Items();
                aa.setAuditCriterionComplianceId(cursor.getInt(0));
                aa.setAuditId(cursor.getInt(1));
                aa.setSubAuditId(cursor.getInt(2));
                aa.setBlockId(cursor.getInt(3));
                aa.setChapterId(cursor.getInt(4));
                aa.setCriterionId(cursor.getInt(5));
                aa.setComplianceValue(cursor.getString(6));
                aa.setComment(cursor.getString(7));
                aa.setCriterionSuggestion(cursor.getString(8));
                aa.setLastUpdatedDate(cursor.getString(9));


                contactList.add(aa);

            } while (cursor.moveToNext());
        }
        return contactList;
    }

    public List<Items> getAuditCriterionComplianceDetails(int[] criterionIds) {
        SQLiteDatabase db = this.getReadableDatabase();

        String inClause = Arrays.toString(criterionIds);
        // Log.e("clause..........", inClause);
        inClause = inClause.replace("[", "(");
        inClause = inClause.replace("]", ")");


        List<Items> contactList = new ArrayList<Items>();
        String countQuery = "SELECT * FROM auditCriterionComplianceTable where criterionId in "+inClause;

        Cursor cursor = db.rawQuery(countQuery, null);
        if (cursor != null)
            cursor.moveToFirst();

        if (!cursor.isAfterLast()) {
            do {

                Items aa = new Items();
                aa.setAuditCriterionComplianceId(cursor.getInt(0));
                aa.setAuditId(cursor.getInt(1));
                aa.setSubAuditId(cursor.getInt(2));
                aa.setBlockId(cursor.getInt(3));
                aa.setChapterId(cursor.getInt(4));
                aa.setCriterionId(cursor.getInt(5));
                aa.setComplianceValue(cursor.getString(6));
                aa.setComment(cursor.getString(7));
                aa.setCriterionSuggestion(cursor.getString(8));
                aa.setLastUpdatedDate(cursor.getString(9));


                contactList.add(aa);

            } while (cursor.moveToNext());
        }
        return contactList;
    }
    public List<Items> getAuditCriterionComplianceDetails(int[] criterionIds,String complianceValue) {
        SQLiteDatabase db = this.getReadableDatabase();

        String inClause = Arrays.toString(criterionIds);
        // Log.e("clause..........", inClause);
        inClause = inClause.replace("[", "(");
        inClause = inClause.replace("]", ")");


        List<Items> contactList = new ArrayList<Items>();
        String countQuery = "SELECT * FROM auditCriterionComplianceTable where criterionId in " +
                ""+inClause+" and complianceValue = '"+complianceValue+"'";

        Cursor cursor = db.rawQuery(countQuery, null);
        if (cursor != null)
            cursor.moveToFirst();

        if (!cursor.isAfterLast()) {
            do {

                Items aa = new Items();
                aa.setAuditCriterionComplianceId(cursor.getInt(0));
                aa.setAuditId(cursor.getInt(1));
                aa.setSubAuditId(cursor.getInt(2));
                aa.setBlockId(cursor.getInt(3));
                aa.setChapterId(cursor.getInt(4));
                aa.setCriterionId(cursor.getInt(5));
                aa.setComplianceValue(cursor.getString(6));
                aa.setComment(cursor.getString(7));
                aa.setCriterionSuggestion(cursor.getString(8));
                aa.setLastUpdatedDate(cursor.getString(9));

                contactList.add(aa);

            } while (cursor.moveToNext());
        }
        return contactList;
    }
    public void updateCompliesValuesToACC(Items listItems, String auditid,String subauditid,String criterionid) {


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(complianceValue, listItems.getComplianceValue());
        values.put(lastUpdatedDate, listItems.getLastUpdatedDate());

        db.update(auditCriterionComplianceTable, values, auditId + " = ? AND "+subAuditId+" = ? AND "
                +criterionId+" =?", new String[] { auditid ,subauditid,criterionid});

    }
    public void updateCommentToACC(Items listItems, String auditid,String subauditid,String criterionid) {


        Log.e("comment ......",listItems.getComment());



        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(comment, listItems.getComment());
        values.put(lastUpdatedDate, listItems.getLastUpdatedDate());

        db.update(auditCriterionComplianceTable, values, auditId + " = ? AND "+subAuditId+" = ? AND "
                +criterionId+" =?", new String[] { auditid ,subauditid,criterionid});

    }

    public void updateSuggestionToACC(Items listItems, String auditid,String subauditid,String criterionid) {


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        Log.e("criterionSuggestion ",listItems.getCriterionSuggestion());

        values.put(criterionSuggestion, listItems.getCriterionSuggestion());
        values.put(lastUpdatedDate, listItems.getLastUpdatedDate());

        db.update(auditCriterionComplianceTable, values, auditId + " = ? AND "+subAuditId+" = ? AND "
                +criterionId+" =?", new String[] { auditid ,subauditid,criterionid});

    }
// =================================================================================================== Getting File attachment Table Details ============================


    public List<Items> getFilesAttachmentTableDetails(int subauditid,int accid) {
        SQLiteDatabase db = this.getReadableDatabase();
//
        List<Items> contactList = new ArrayList<Items>();
        String countQuery = "SELECT * FROM filesAttachmentTable where subAuditId = "
                +subauditid+" and auditCriterionComplianceId = "+accid+" and isDeleted = 'false'";

        Cursor cursor = db.rawQuery(countQuery, null);
        if (cursor != null)
            cursor.moveToFirst();

        if (!cursor.isAfterLast()) {
            do {


                Items aa = new Items();
                aa.setFileId(cursor.getInt(0));
                aa.setSubAuditId(cursor.getInt(1));
                aa.setCriterionId(cursor.getInt(2));
                aa.setAuditCriterionComplianceId(cursor.getInt(3));
                aa.setFileName(cursor.getString(4));
                aa.setFilePath(cursor.getString(5));
                aa.setStatus(cursor.getString(6));
                aa.setLastUpdatedDate(cursor.getString(7));
                aa.setIsDeleted(cursor.getString(8));
                aa.setIsSynced(cursor.getString(9));

                contactList.add(aa);

            } while (cursor.moveToNext());
        }
        return contactList;
    }
    public void updateFilesAttachmentTable(Items listItems,String Id) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(isDeleted, listItems.getIsDeleted());

        db.update(filesAttachmentTable, values, id+" =?", new String[] {Id});

    }
    public List<Items> getDeletedFilesAttachment(int subauditid) {
        SQLiteDatabase db = this.getReadableDatabase();

        List<Items> contactList = new ArrayList<Items>();
        String countQuery = "SELECT * FROM filesAttachmentTable where subAuditId = "+subauditid+" and isDeleted = 'true' ";

        Cursor cursor = db.rawQuery(countQuery, null);
        if (cursor != null)
            cursor.moveToFirst();

        if (!cursor.isAfterLast()) {
            do {

                Items aa = new Items();
                aa.setFileId(cursor.getInt(0));
                aa.setSubAuditId(cursor.getInt(1));
                aa.setCriterionId(cursor.getInt(2));
                aa.setAuditCriterionComplianceId(cursor.getInt(3));
                aa.setFileName(cursor.getString(4));
                aa.setFilePath(cursor.getString(5));
                aa.setStatus(cursor.getString(6));
                aa.setLastUpdatedDate(cursor.getString(7));
                aa.setIsDeleted(cursor.getString(8));
                aa.setIsSynced(cursor.getString(9));

                contactList.add(aa);

            } while (cursor.moveToNext());
        }
        return contactList;
    }
    public List<Items> getFilesAttachmentTable(int subauditid) {
        SQLiteDatabase db = this.getReadableDatabase();

        List<Items> contactList = new ArrayList<Items>();
        String countQuery = "SELECT * FROM filesAttachmentTable where subAuditId = "+subauditid+"" +
                " and isDeleted = 'false' and status = 'true' and isSynced = 'false'";

        Cursor cursor = db.rawQuery(countQuery, null);
        if (cursor != null)
            cursor.moveToFirst();

        if (!cursor.isAfterLast()) {
            do {


                Items aa = new Items();

                aa.setFileId(cursor.getInt(0));
                aa.setSubAuditId(cursor.getInt(1));
                aa.setCriterionId(cursor.getInt(2));
                aa.setAuditCriterionComplianceId(cursor.getInt(3));
                aa.setFileName(cursor.getString(4));
                aa.setFilePath(cursor.getString(5));
                aa.setStatus(cursor.getString(6));
                aa.setLastUpdatedDate(cursor.getString(7));
                aa.setIsDeleted(cursor.getString(8));
                aa.setIsSynced(cursor.getString(9));

                contactList.add(aa);

            } while (cursor.moveToNext());
        }
        return contactList;
    }
    public void updateIsSyncedToFilesAttachmentTable(Items listItems,String Id) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(isSynced, listItems.getIsDeleted());

        db.update(filesAttachmentTable, values, id+" =?", new String[] {Id});

    }
// =================================================================================================== Deleting All tables ============================

    public void deleteAuditCriterionComplianceTable(int subauditid) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(auditCriterionComplianceTable,subAuditId + " = ?", new String[] {""+subauditid});

    }
    public void deleteFilesAttachmentTable(int id) {

        SQLiteDatabase db = this.getWritableDatabase();

        String querey = "DELETE FROM filesAttachmentTable where subAuditId = "+id;

        db.execSQL(querey);


    }
    public void deleteInspectionYearTable() {

        SQLiteDatabase db = this.getWritableDatabase();

        String querey = "DELETE FROM inspectionYearTable ";

        db.execSQL(querey);


    }
    public void deletecommonAuditDetailsTable() {

        SQLiteDatabase db = this.getWritableDatabase();

        String querey = "DELETE FROM commonAuditDetailsTable ";

        db.execSQL(querey);


    }
    public void deleteBlockTable() {

        SQLiteDatabase db = this.getWritableDatabase();

        String querey = "DELETE FROM blockTable ";

        db.execSQL(querey);


    }
    public void deleteSubAuditDetailsTable() {

        SQLiteDatabase db = this.getWritableDatabase();

        String querey = "DELETE FROM subAuditDetailsTable ";

        db.execSQL(querey);


    }
    public void deleteVersionTable() {

        SQLiteDatabase db = this.getWritableDatabase();

        String querey = "DELETE FROM auditVersionTable ";

        db.execSQL(querey);


    }
    public void deleteChapterTable() {

        SQLiteDatabase db = this.getWritableDatabase();

        String querey = "DELETE FROM chapterTable ";

        db.execSQL(querey);


    }
    public void deleteCriterionTable() {

        SQLiteDatabase db = this.getWritableDatabase();

        String querey = "DELETE FROM criterionTable ";

        db.execSQL(querey);


    }
    public void deleteCommonAuditTable() {

        SQLiteDatabase db = this.getWritableDatabase();

        String querey = "DELETE FROM commonAuditDetailsTable ";

        db.execSQL(querey);


    }
    public void deleteSubauditDetailsTable() {

        SQLiteDatabase db = this.getWritableDatabase();

        String querey = "DELETE FROM subAuditDetailsTable ";

        db.execSQL(querey);


    }
    public void deleteAuditChapterComplianceTable() {

       /* SQLiteDatabase db = this.getWritableDatabase();

        String querey = "DELETE FROM auditChapterComplianceTable ";

        db.execSQL(querey);*/


    }
    public void deleteAuditCriterionComplianceTable() {

        SQLiteDatabase db = this.getWritableDatabase();

        String querey = "DELETE FROM auditCriterionComplianceTable ";

        db.execSQL(querey);


    }
    public void deleteFileAttachmentTable() {

        SQLiteDatabase db = this.getWritableDatabase();

        String querey = "DELETE FROM filesAttachmentTable ";

        db.execSQL(querey);


    }
    public void deleteGropuAuditDetailsTable() {

        SQLiteDatabase db = this.getWritableDatabase();

        String querey = "DELETE FROM groupAuditDetailsTable ";

        db.execSQL(querey);


    }
    public void deleteAuditorsTable() {

        SQLiteDatabase db = this.getWritableDatabase();

        String querey = "DELETE FROM auditorstable ";

        db.execSQL(querey);


    }


}
