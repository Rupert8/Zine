package services.exportExel;

import hibernate.entity.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.jdbc.Work;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ExelExportService {
    public static void exportToExcelSocialPassport(List<SocialPassport> passports, String filePath) {
        Workbook workbook = new XSSFWorkbook(); // Створюємо новий Excel-файл
        Sheet sheet = workbook.createSheet("Social Passports");

        Row headerRow = sheet.createRow(0);
        String[] headers = {
                "Прізвище", "Ім'я", "По батькові", "Дата народження",
                "Семестр", "Назва категорії", "Дата початку",
                "Дата кінця", "Статус повнолітності", "Група"
        };

        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(getHeaderCellStyle(workbook));
        }

        CreationHelper createHelper = workbook.getCreationHelper();
        CellStyle dateCellStyle = workbook.createCellStyle();
        dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("yyyy-MM-dd"));  // Формат дати

        int rowIndex = 1;
        for (SocialPassport passport : passports) {
            Row row = sheet.createRow(rowIndex++);

            row.createCell(0).setCellValue(passport.getStudentInfo().getSurname());
            row.createCell(1).setCellValue(passport.getStudentInfo().getName());
            row.createCell(2).setCellValue(passport.getStudentInfo().getMiddleName());

            Cell birthDateCell = row.createCell(3);
            birthDateCell.setCellValue(passport.getStudentInfo().getDate_of_birth());
            birthDateCell.setCellStyle(dateCellStyle);  // Застосовуємо стиль дати

            row.createCell(4).setCellValue(passport.getSemester());

            row.createCell(5).setCellValue(passport.getSpCategoryName().getCategory());

            Cell startDateCell = row.createCell(6);
            startDateCell.setCellValue(passport.getStartDate());
            startDateCell.setCellStyle(dateCellStyle);  // Застосовуємо стиль дати

            Cell endDateCell = row.createCell(7);
            endDateCell.setCellValue(passport.getEndDate());
            endDateCell.setCellStyle(dateCellStyle);  // Застосовуємо стиль дати

            String adultStatus = passport.isStatusAdult() ? "Повнолітній" : "Неповнолітній";
            row.createCell(8).setCellValue(adultStatus);

            row.createCell(9).setCellValue(passport.getStudentInfo().getGroupName());
        }

        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }

        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
            System.out.println("Дані успішно експортовано у файл: " + filePath);
        } catch (IOException e) {
            System.err.println("Помилка при експорті: " + e.getMessage());
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void exportMultiSheetExcel(
            List<StudentInfo> generalStudentInfoList,
            List<EducationInfo> educationInfoList,
            List<MilitaryService> militaryServiceList,
            List<StudentParents> studentParentsList,
            List<StudentJob> studentJobList,
            List<SocialActivity> socialActivityList,
            List<CircleActivity> circleActivityList,
            List<IndividualSupport> individualSupport,
            List<Promotion> promotionList,
            List<SocialPassport> socialPassportList,
            String filePath
    ) {
        Workbook workbook = new XSSFWorkbook();

        Sheet studentSheet = workbook.createSheet("Загальні дані");
        writeGeneralStudentInfoSheet(studentSheet,generalStudentInfoList,workbook);

        Sheet educationSheet = workbook.createSheet("Дані про освіту");
        writeEducationSheet(educationSheet,educationInfoList,workbook);

        Sheet militarySheet = workbook.createSheet("Служба в ЗСУ");
        writeMilitarySheet(militarySheet,militaryServiceList,workbook);

        Sheet ParentsSheet = workbook.createSheet("Інформація про батьків");
        writeParentsSheet(ParentsSheet,studentParentsList,workbook);

        Sheet WorkSheet = workbook.createSheet("Трудова діяльність");
        writeStudentJobSheet(WorkSheet,studentJobList,workbook);

        Sheet SocialActivitySheet = workbook.createSheet("Громадська діяльність");
        writeSocialActivitySheet(SocialActivitySheet,socialActivityList,workbook);

        Sheet GroupActivitySheet = workbook.createSheet("Гурткова діяльність");
        writeGroupActivitySheet(GroupActivitySheet,circleActivityList,workbook);

        Sheet IndividualSupportSheet = workbook.createSheet("Індивідуальний супровід");
        writeIndividualSupportSheet(IndividualSupportSheet,individualSupport,workbook);

        Sheet PromotionSheet = workbook.createSheet("Заохочення");
        writePromotionSheet(PromotionSheet,promotionList,workbook);

        Sheet SocialPassportSheet = workbook.createSheet("Соціальний паспорт");
        writeSocialPassportSheet(SocialPassportSheet,socialPassportList,workbook);

        // Збереження файлу
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
            System.out.println("Файл збережено: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void exportGroupAndWorkPlanInfoExcel(List<StudentInfo> studentInfoList, List<WorkPlan> workPlanList,String GroupName){
        Workbook workbook = new XSSFWorkbook();

        Sheet GroupStudent = workbook.createSheet("Студенти");
        writeGeneralStudentInfoSheet(GroupStudent,studentInfoList,workbook);

        Sheet GroupWorkPlan = workbook.createSheet("План роботи");
        writeWorkPlanInfoSheet(GroupWorkPlan,workPlanList,workbook);

        String downloadFolder = System.getProperty("user.home") + "\\Downloads";

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = dateFormat.format(new java.util.Date());
        String filePath = downloadFolder + "\\Повна_інформація_"+ GroupName + "_" +currentDate + ".xlsx";


        // Збереження файлу
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
            System.out.println("Файл збережено: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void writeGeneralStudentInfoSheet(Sheet sheet, List<StudentInfo> data,Workbook workbook) {
        // Створюємо стиль заголовків
        CellStyle headerStyle = getHeaderCellStyle(workbook);
        // Створення рядка заголовків
        Row header = sheet.createRow(0);

        Cell cellSurname = header.createCell(0);
        cellSurname.setCellValue("Прізвище");
        cellSurname.setCellStyle(headerStyle);

        Cell cellName = header.createCell(1);
        cellName.setCellValue("Ім'я");
        cellName.setCellStyle(headerStyle);

        Cell cellMiddleName = header.createCell(2);
        cellMiddleName.setCellValue("По-батькові");
        cellMiddleName.setCellStyle(headerStyle);

        Cell cellGroupName = header.createCell(3);
        cellGroupName.setCellValue("Назва групи");
        cellGroupName.setCellStyle(headerStyle);

        Cell cellDate_of_birth = header.createCell(4);
        cellDate_of_birth.setCellValue("День народження");
        cellDate_of_birth.setCellStyle(headerStyle);

        Cell cellAddress = header.createCell(5);
        cellAddress.setCellValue("Адреса");
        cellAddress.setCellStyle(headerStyle);

        Cell cellPhoneNumber = header.createCell(6);
        cellPhoneNumber.setCellValue("Номер телефону");
        cellPhoneNumber.setCellStyle(headerStyle);

        int rowIndex = 1;
        for (StudentInfo stud : data) {
            Row row = sheet.createRow(rowIndex++);

            row.createCell(0).setCellValue(stud.getSurname());
            row.createCell(1).setCellValue(stud.getName());
            row.createCell(2).setCellValue(stud.getMiddleName());
            row.createCell(3).setCellValue(stud.getGroupName());
            Cell cellDate = row.createCell(4);
            cellDate.setCellValue(stud.getDate_of_birth());
            cellDate.setCellStyle(dateStyle(workbook));
            row.createCell(5).setCellValue(stud.getAddress());
            row.createCell(6).setCellValue(stud.getPhoneNumber());
        }

        for (int i = 0; i < 7; i++) {
            sheet.autoSizeColumn(i);
        }

    }

    private static void writeWorkPlanInfoSheet(Sheet sheet, List<WorkPlan> data,Workbook workbook) {

        CellStyle headerStyle = getHeaderCellStyle(workbook);

        Row header = sheet.createRow(0);

        Cell cellEventName = header.createCell(0);
        cellEventName.setCellValue("Назва заходу");
        cellEventName.setCellStyle(headerStyle);

        Cell cellPerformer = header.createCell(1);
        cellPerformer.setCellValue("Виконавець");
        cellPerformer.setCellStyle(headerStyle);

        Cell cellExecutionDate = header.createCell(2);
        cellExecutionDate.setCellValue("Дата виконання");
        cellExecutionDate.setCellStyle(headerStyle);

        Cell cellAcademyYear = header.createCell(3);
        cellAcademyYear.setCellValue("Навчальний рік");
        cellAcademyYear.setCellStyle(headerStyle);

        Cell cellSemester = header.createCell(4);
        cellSemester.setCellValue("Семестер");
        cellSemester.setCellStyle(headerStyle);

        int rowIndex = 1;
        for (WorkPlan plan : data) {
            Row row = sheet.createRow(rowIndex++);

            row.createCell(0).setCellValue(plan.getEventName());
            row.createCell(1).setCellValue(plan.getPerformer());
            Cell cellDate = row.createCell(2);
            cellDate.setCellValue(plan.getExecutionDate());
            cellDate.setCellStyle(dateStyle(workbook));
            row.createCell(3).setCellValue(plan.getAcademicYear());
            row.createCell(4).setCellValue(plan.getSemester());
        }

        for (int i = 0; i < 5; i++) {
            sheet.autoSizeColumn(i);
        }

    }

    private static void writeEducationSheet(Sheet sheet, List<EducationInfo> data,Workbook workbook) {
        // Створюємо стиль заголовків
        CellStyle headerStyle = getHeaderCellStyle(workbook);
        // Створення рядка заголовків
        Row header = sheet.createRow(0);

        Cell cellEndDate = header.createCell(0);
        cellEndDate.setCellValue("Дата закінчення");
        cellEndDate.setCellStyle(headerStyle);

        Cell cellSchoolName = header.createCell(1);
        cellSchoolName.setCellValue("Назва закладу");
        cellSchoolName.setCellStyle(headerStyle);

        Cell cellGradeAverage = header.createCell(2);
        cellGradeAverage.setCellValue("Середній бал");
        cellGradeAverage.setCellStyle(headerStyle);


        int rowIndex = 1;
        for (EducationInfo edu : data) {
            Row row = sheet.createRow(rowIndex++);


            Cell endDate = row.createCell(0);
            endDate.setCellValue(edu.getEndDate());
            endDate.setCellStyle(dateStyle(workbook));
            row.createCell(1).setCellValue(edu.getSchoolName());
            row.createCell(2).setCellValue(edu.getGradeAvarage());

        }

        for (int i = 0; i < 3; i++) {
            sheet.autoSizeColumn(i);
        }

    }

    private static void writeMilitarySheet(Sheet sheet, List<MilitaryService> data,Workbook workbook) {
        // Створюємо стиль заголовків
        CellStyle headerStyle = getHeaderCellStyle(workbook);

        Row header = sheet.createRow(0);

        Cell cellStartDate = header.createCell(0);
        cellStartDate.setCellValue("Дата початку");
        cellStartDate.setCellStyle(headerStyle);

        Cell cellEndDate = header.createCell(1);
        cellEndDate.setCellValue("Дата закінчення");
        cellEndDate.setCellStyle(headerStyle);

        Cell cellUnit = header.createCell(2);
        cellUnit.setCellValue("Підрозділ");
        cellUnit.setCellStyle(headerStyle);

        int rowIndex = 1;
        for (MilitaryService milit : data) {
            Row row = sheet.createRow(rowIndex++);

            Cell startDate = header.createCell(0);
            startDate.setCellValue(milit.getStartDate());
            startDate.setCellStyle(dateStyle(workbook));
            Cell endDate = header.createCell(1);
            endDate.setCellValue(milit.getEndDate());
            endDate.setCellStyle(dateStyle(workbook));
            row.createCell(2).setCellValue(milit.getUnit());
        }

        for (int i = 0; i < 3; i++) {
            sheet.autoSizeColumn(i);
        }
    }

    private static void writeParentsSheet(Sheet sheet, List<StudentParents> data,Workbook workbook) {
        // Створюємо стиль заголовків
        CellStyle headerStyle = getHeaderCellStyle(workbook);

        Row header = sheet.createRow(0);

        Cell cellPIPFather = header.createCell(0);
        cellPIPFather.setCellValue("ПІП батька");
        cellPIPFather.setCellStyle(headerStyle);

        Cell cellPhoneFather = header.createCell(1);
        cellPhoneFather.setCellValue("Телефон батька");
        cellPhoneFather.setCellStyle(headerStyle);

        Cell cellPIPMother = header.createCell(2);
        cellPIPMother.setCellValue("ПІП матері");
        cellPIPMother.setCellStyle(headerStyle);

        Cell cellPhoneMother = header.createCell(3);
        cellPhoneMother.setCellValue("Телефон матері");
        cellPhoneMother.setCellStyle(headerStyle);

        int rowIndex = 1;
        for (StudentParents parents : data) {
            Row row = sheet.createRow(rowIndex++);
            row.createCell(0).setCellValue(parents.getFatherFullName());
            row.createCell(1).setCellValue(parents.getPhoneFather());
            row.createCell(2).setCellValue(parents.getMotherFullName());
            row.createCell(3).setCellValue(parents.getPhoneMother());

        }

        for (int i = 0; i < 4; i++) {
            sheet.autoSizeColumn(i);
        }
    }

    private static void writeStudentJobSheet(Sheet sheet, List<StudentJob> data,Workbook workbook) {
        // Створюємо стиль заголовків
        CellStyle headerStyle = getHeaderCellStyle(workbook);

        Row header = sheet.createRow(0);
        Cell cellStartDate = header.createCell(0);
        cellStartDate.setCellValue("Дата початку");
        cellStartDate.setCellStyle(headerStyle);

        Cell cellEndDate = header.createCell(1);
        cellEndDate.setCellValue("Дата закінчення");
        cellEndDate.setCellStyle(headerStyle);

        Cell cellPlace = header.createCell(2);
        cellPlace.setCellValue("Місце");
        cellPlace.setCellStyle(headerStyle);

        Cell cellPosition = header.createCell(3);
        cellPosition.setCellValue("Посада");
        cellPosition.setCellStyle(headerStyle);

        int rowIndex = 1;
        for (StudentJob job : data) {
            Row row = sheet.createRow(rowIndex++);

            Cell startDate = row.createCell(0);
            startDate.setCellValue(job.getStartDate());
            startDate.setCellStyle(dateStyle(workbook));
            Cell endDate = row.createCell(1);
            endDate.setCellValue(job.getEndDate());
            endDate.setCellStyle(dateStyle(workbook));
            row.createCell(2).setCellValue(job.getPlace());
            row.createCell(3).setCellValue(job.getPosition());
        }

        for (int i = 0; i < 4; i++) {
            sheet.autoSizeColumn(i);
        }
    }

    private static void writeGroupActivitySheet(Sheet sheet, List<CircleActivity> data,Workbook workbook) {
        // Створюємо стиль заголовків
        CellStyle headerStyle = getHeaderCellStyle(workbook);

        Row header = sheet.createRow(0);

        Cell cellSemester = header.createCell(0);
        cellSemester.setCellValue("Семестер");
        cellSemester.setCellStyle(headerStyle);

        Cell cellGroupName = header.createCell(1);
        cellGroupName.setCellValue("Назва Гуртка");
        cellGroupName.setCellStyle(headerStyle);

        Cell cellNote = header.createCell(2);
        cellNote.setCellValue("Примітка");
        cellNote.setCellStyle(headerStyle);

        int rowIndex = 1;
        for (CircleActivity group : data) {
            Row row = sheet.createRow(rowIndex++);
            row.createCell(0).setCellValue(group.getSemestr());
            row.createCell(1).setCellValue(group.getCircleName());
            row.createCell(2).setCellValue(group.getNote());
        }

        for (int i = 0; i < 3; i++) {
            sheet.autoSizeColumn(i);
        }
    }

    private static void writeSocialActivitySheet(Sheet sheet, List<SocialActivity> data,Workbook workbook) {
        // Створюємо стиль заголовків
        CellStyle headerStyle = getHeaderCellStyle(workbook);

        Row header = sheet.createRow(0);

        Cell cellSemester = header.createCell(0);
        cellSemester.setCellValue("Семестер");
        cellSemester.setCellStyle(headerStyle);

        Cell cellDate = header.createCell(1);
        cellDate.setCellValue("Дата");
        cellDate.setCellStyle(headerStyle);

        Cell cellNote = header.createCell(2);
        cellNote.setCellValue("Примітка");
        cellNote.setCellStyle(headerStyle);

        int rowIndex = 1;
        for (SocialActivity social : data) {
            Row row = sheet.createRow(rowIndex++);
            row.createCell(0).setCellValue(social.getSemestr());
            Cell date = row.createCell(1);
            date.setCellValue(social.getDate());
            date.setCellStyle(dateStyle(workbook));
            row.createCell(2).setCellValue(social.getActivity());
        }

        for (int i = 0; i < 3; i++) {
            sheet.autoSizeColumn(i);
        }
    }

    private static void writePromotionSheet(Sheet sheet, List<Promotion> data,Workbook workbook) {
        // Створюємо стиль заголовків
        CellStyle headerStyle = getHeaderCellStyle(workbook);

        Row header = sheet.createRow(0);
        Cell cellSemester = header.createCell(0);
        cellSemester.setCellValue("Семестер");
        cellSemester.setCellStyle(headerStyle);

        Cell cellDate = header.createCell(1);
        cellDate.setCellValue("Дата");
        cellDate.setCellStyle(headerStyle);

        Cell cellContent = header.createCell(2);
        cellContent.setCellValue("Зміст");
        cellContent.setCellStyle(headerStyle);

        int rowIndex = 1;
        for (Promotion prom : data) {
            Row row = sheet.createRow(rowIndex++);
            row.createCell(0).setCellValue(prom.getSemestr());
            Cell date = row.createCell(1);
            date.setCellValue(prom.getStartDate());
            date.setCellStyle(dateStyle(workbook));
            row.createCell(2).setCellValue(prom.getContent());
        }

        for (int i = 0; i < 3; i++) {
            sheet.autoSizeColumn(i);
        }
    }

    public static CellStyle dateStyle(Workbook workbook) {
        CreationHelper createHelper = workbook.getCreationHelper();
        CellStyle dateCellStyle = workbook.createCellStyle();
        dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("yyyy-MM-dd"));  // Формат дати
        return dateCellStyle;
    }

    private static void writeIndividualSupportSheet(Sheet sheet, List<IndividualSupport> data, Workbook workbook) {
        // Створюємо стиль заголовків
        CellStyle headerStyle = getHeaderCellStyle(workbook);

        Row header = sheet.createRow(0);
        Cell cellSemester = header.createCell(0);
        cellSemester.setCellValue("Семестер");
        cellSemester.setCellStyle(headerStyle);

        Cell cellDate = header.createCell(1);
        cellDate.setCellValue("Дата");
        cellDate.setCellStyle(headerStyle);

        Cell cellContent = header.createCell(2);
        cellContent.setCellValue("Зміст");
        cellContent.setCellStyle(headerStyle);

        int rowIndex = 1;
        for (IndividualSupport indiv : data) {
            Row row = sheet.createRow(rowIndex++);
            row.createCell(0).setCellValue(indiv.getSemestr());
            Cell date = row.createCell(1);
            date.setCellValue(indiv.getDate());
            date.setCellStyle(dateStyle(workbook));
            row.createCell(2).setCellValue(indiv.getContent());
        }

        for (int i = 0; i < 3; i++) {
            sheet.autoSizeColumn(i);
        }
    }

    public static void writeSocialPassportSheet(Sheet sheet, List<SocialPassport> passports, Workbook workbook) {
        Row headerRow = sheet.createRow(0);
        String[] headers = {
                "Семестр", "Назва категорії", "Дата початку",
                "Дата кінця", "Статус повнолітності", "Група"
        };

        // Стиль заголовків
        CellStyle headerStyle = getHeaderCellStyle(workbook);

        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerStyle);
        }

        // Стиль дати
        CreationHelper createHelper = workbook.getCreationHelper();
        CellStyle dateCellStyle = workbook.createCellStyle();
        dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("yyyy-MM-dd"));

        int rowIndex = 1;
        for (SocialPassport passport : passports) {
            Row row = sheet.createRow(rowIndex++);

            row.createCell(0).setCellValue(passport.getSemester());
            row.createCell(1).setCellValue(passport.getSpCategoryName().getCategory());

            Cell startDateCell = row.createCell(2);
            startDateCell.setCellValue(passport.getStartDate());
            startDateCell.setCellStyle(dateCellStyle);

            Cell endDateCell = row.createCell(3);
            endDateCell.setCellValue(passport.getEndDate());
            endDateCell.setCellStyle(dateCellStyle);

            String adultStatus = passport.isStatusAdult() ? "Повнолітній" : "Неповнолітній";
            row.createCell(4).setCellValue(adultStatus);

            row.createCell(5).setCellValue(passport.getStudentInfo().getGroupName());
        }

        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }
    }




    private static CellStyle getHeaderCellStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 12);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        return style;
    }

    public static void exportStudentInfoOnDisk(String StudentName,String StudentSurname,String StudentMiddleName,List<StudentInfo> studentInfoList,
                                     List<EducationInfo> educationInfoList,
                                     List<MilitaryService> militaryList,
                                     List<StudentParents> parentsList,
                                     List<StudentJob> jobList,
                                     List<CircleActivity> circleActivityList,
                                     List<SocialActivity> socialActivityList,
                                     List<Promotion> promotionList,
                                     List<IndividualSupport> individualSupportList,
                                     List<SocialPassport> socialPassportList){

        String downloadFolder = System.getProperty("user.home") + "\\Downloads";

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = dateFormat.format(new java.util.Date());
        String filePath = downloadFolder + "\\Повна_інформація_"+ StudentSurname + "_" + StudentName + "_" + StudentMiddleName + "_" + currentDate + ".xlsx";

        try {
            exportMultiSheetExcel(studentInfoList,educationInfoList,militaryList,parentsList,jobList,socialActivityList,circleActivityList,individualSupportList,promotionList,socialPassportList,filePath);
        }catch (Exception e){
            e.printStackTrace();

        }
    }

    public static void exportSocialPassportOnDisk(List<SocialPassport> socialPassportList){
        String downloadFolder = System.getProperty("user.home") + "\\Downloads";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = dateFormat.format(new Date());
        String filePath = downloadFolder + "\\Соціальний_паспорт_експорт_" + currentDate + ".xlsx";
        exportToExcelSocialPassport(socialPassportList, filePath);
    }
}
