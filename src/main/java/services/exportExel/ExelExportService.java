package services.exportExel;

import hibernate.entity.SocialPassport;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ExelExportService {
    public static void exportToExcel(List<SocialPassport> passports, String filePath) {
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
