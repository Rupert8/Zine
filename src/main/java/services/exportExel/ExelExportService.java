package services.exportExel;

import hibernate.entity.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExelExportService {
    public static void exportToExcelSocialPassport(List<SocialPassport> passports, String filePath) {
        Workbook workbook = new XSSFWorkbook(); // Створюємо новий Excel-файл
        Sheet sheet = workbook.createSheet("Social Passports");

        Row headerRow = sheet.createRow(0);
        String[] headers = {
                "Ім'я", "Прізвище", "По батькові", "Дата народження",
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

            row.createCell(0).setCellValue(passport.getStudentInfo().getName());
            row.createCell(1).setCellValue(passport.getStudentInfo().getSurname());
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

        Sheet educationSheet = workbook.createSheet("Дані про освіту");
        writeEducationSheet(educationSheet,educationInfoList);

        Sheet militarySheet = workbook.createSheet("Служба в ЗСУ");
        writeMilitarySheet(militarySheet,militaryServiceList);

        Sheet ParentsSheet = workbook.createSheet("Інформація про батьків");
        writeParentsSheet(ParentsSheet,studentParentsList);

        Sheet WorkSheet = workbook.createSheet("Трудова діяльність");
        writeStudentJobSheet(WorkSheet,studentJobList);

        Sheet SocialActivitySheet = workbook.createSheet("Громадська діяльність");
        writeSocialActivitySheet(SocialActivitySheet,socialActivityList);

        Sheet GroupActivitySheet = workbook.createSheet("Гурткова діяльність");
        writeGroupActivitySheet(GroupActivitySheet,circleActivityList);

        Sheet IndividualSupportSheet = workbook.createSheet("Індивідуальний супровід");
        writeIndividualSupportSheet(IndividualSupportSheet,individualSupport);

        Sheet PromotionSheet = workbook.createSheet("Заохочення");
        writePromotionSheet(PromotionSheet,promotionList);

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

    private static void writeEducationSheet(Sheet sheet, List<EducationInfo> data) {
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Дата закінчення");
        header.createCell(1).setCellValue("Назва закладу");
        header.createCell(2).setCellValue("Середній бал");

        int rowIndex = 1;
        for (EducationInfo edu : data) {
            Row row = sheet.createRow(rowIndex++);
            row.createCell(0).setCellValue(edu.getEndDate());
            row.createCell(1).setCellValue(edu.getSchoolName());
            row.createCell(2).setCellValue(edu.getGradeAvarage());
        }
    }

    private static void writeMilitarySheet(Sheet sheet, List<MilitaryService> data) {
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Дата початку");
        header.createCell(1).setCellValue("Дата кінця");
        header.createCell(2).setCellValue("Підрозділ");

        int rowIndex = 1;
        for (MilitaryService milit : data) {
            Row row = sheet.createRow(rowIndex++);
            row.createCell(0).setCellValue(milit.getStartDate());
            row.createCell(1).setCellValue(milit.getEndDate());
            row.createCell(2).setCellValue(milit.getUnit());
        }
    }

    private static void writeParentsSheet(Sheet sheet, List<StudentParents> data) {
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("ПІП батька");
        header.createCell(1).setCellValue("Телефон батька");
        header.createCell(2).setCellValue("ПІП матері");
        header.createCell(3).setCellValue("Телефон матері");

        int rowIndex = 1;
        for (StudentParents parents : data) {
            Row row = sheet.createRow(rowIndex++);
            row.createCell(0).setCellValue(parents.getFatherFullName());
            row.createCell(1).setCellValue(parents.getPhoneFather());
            row.createCell(2).setCellValue(parents.getMotherFullName());
            row.createCell(3).setCellValue(parents.getPhoneMother());

        }
    }

    private static void writeStudentJobSheet(Sheet sheet, List<StudentJob> data) {
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Дата початку");
        header.createCell(1).setCellValue("Дата закінчення");
        header.createCell(2).setCellValue("Місце");
        header.createCell(3).setCellValue("Посада");

        int rowIndex = 1;
        for (StudentJob job : data) {
            Row row = sheet.createRow(rowIndex++);
            row.createCell(0).setCellValue(job.getStartDate());
            row.createCell(1).setCellValue(job.getEndDate());
            row.createCell(2).setCellValue(job.getPlace());
            row.createCell(3).setCellValue(job.getPosition());
        }
    }

    private static void writeGroupActivitySheet(Sheet sheet, List<CircleActivity> data) {
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Семестер");
        header.createCell(1).setCellValue("Назва Гуртка");
        header.createCell(2).setCellValue("Примітка");

        int rowIndex = 1;
        for (CircleActivity group : data) {
            Row row = sheet.createRow(rowIndex++);
            row.createCell(0).setCellValue(group.getSemestr());
            row.createCell(1).setCellValue(group.getCircleName());
            row.createCell(2).setCellValue(group.getNote());
        }
    }

    private static void writeSocialActivitySheet(Sheet sheet, List<SocialActivity> data) {
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Семестер");
        header.createCell(1).setCellValue("Дата");
        header.createCell(2).setCellValue("Примітка");

        int rowIndex = 1;
        for (SocialActivity social : data) {
            Row row = sheet.createRow(rowIndex++);
            row.createCell(0).setCellValue(social.getSemestr());
            row.createCell(1).setCellValue(social.getDate());
            row.createCell(2).setCellValue(social.getActivity());
        }
    }

    private static void writePromotionSheet(Sheet sheet, List<Promotion> data) {
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Семестер");
        header.createCell(1).setCellValue("Дата");
        header.createCell(2).setCellValue("Зміст");

        int rowIndex = 1;
        for (Promotion prom : data) {
            Row row = sheet.createRow(rowIndex++);
            row.createCell(0).setCellValue(prom.getSemestr());
            row.createCell(1).setCellValue(prom.getStartDate());
            row.createCell(2).setCellValue(prom.getContent());
        }
    }

    private static void writeIndividualSupportSheet(Sheet sheet, List<IndividualSupport> data) {
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Семестер");
        header.createCell(1).setCellValue("Дата");
        header.createCell(2).setCellValue("Зміст");

        int rowIndex = 1;
        for (IndividualSupport indiv : data) {
            Row row = sheet.createRow(rowIndex++);
            row.createCell(0).setCellValue(indiv.getSemestr());
            row.createCell(1).setCellValue(indiv.getDate());
            row.createCell(2).setCellValue(indiv.getContent());
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
}
