package com.ccsw.teammanager.forecast;

import java.io.File;
import java.io.FileOutputStream;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ccsw.teammanager.config.security.UserUtils;
import com.ccsw.teammanager.forecast.model.ForecastDetailDto;
import com.ccsw.teammanager.forecast.model.ForecastDto;
import com.ccsw.teammanager.person.PersonRepository;
import com.ccsw.teammanager.person.model.PersonEntity;
import com.ccsw.teammanager.personabsence.PersonAbsenceRepository;
import com.ccsw.teammanager.personabsence.model.PersonAbsenceEntity;

/**
 * @author aolmosca
 *
 */
@Service
@Transactional(readOnly = true)
public class ForecastServiceImpl implements ForecastService {

    private static final Logger LOG = LoggerFactory.getLogger(ForecastServiceImpl.class);

    @Autowired
    PersonAbsenceRepository personAbsenceRepository;

    @Autowired
    PersonRepository personRepository;

    private String getUserGrade() {

        String username = UserUtils.getUserDetails().getUsername();
        PersonEntity personUser = this.personRepository.findByUsernameAndActiveTrue(username);

        return normalizePersonGrade(personUser);
    }

    private String normalizePersonGrade(PersonEntity person) {

        if (person == null || person.getGrade() == null || person.getGrade().trim().length() == 0)
            return "A";

        return person.getGrade();
    }

    private Map<String, String> getGroupSagas(Long groupId) {

        List<PersonEntity> groupMembersList = this.personRepository.findPersonByGroupId(groupId);

        return groupMembersList.stream().collect(Collectors.toMap(p -> p.getName() + " " + p.getLastname(), PersonEntity::getSaga));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, ForecastDetailDto> getGroupAbsenceByDate(Long groupId, Date init, Date end) {

        String userGrade = getUserGrade();
        List<PersonEntity> groupMembersList = this.personRepository.findPersonByGroupId(groupId);

        init.setHours(0);
        end.setHours(23);

        List<Integer> personIds = extractListPersonId(groupMembersList);
        List<PersonAbsenceEntity> absenceList = this.personAbsenceRepository.findByPerson_IdInAndDateBetween(personIds, init, end);

        SortedMap<String, ForecastDetailDto> hashAbsence = new TreeMap<>();

        for (PersonEntity member : groupMembersList) {
            ForecastDetailDto absence = new ForecastDetailDto();
            String key = member.getName() + " " + member.getLastname();
            List<ForecastDto> forecastList = new ArrayList<>();
            absence.setVisible(isVisibleByGrade(userGrade, member));

            if (absence.isVisible())
                absence.setAbsences(extractAbsencesFromList(member.getId(), absenceList));
            else
                absence.setAbsences(forecastList);
            hashAbsence.put(key, absence);
        }

        return hashAbsence;
    }

    private boolean isVisibleByGrade(String userGrade, PersonEntity person) {

        String personGrade = normalizePersonGrade(person);

        if (personGrade.equals("E") || personGrade.equals("F")) {
            return userGrade.compareTo(personGrade) >= 0;
        }

        return true;
    }

    @Override
    public File exportForecast(Long groupId, Date init, Date end, int type) {

        List<String> months = Arrays.asList(new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" });
        List<String> partialMonths = new ArrayList();
        Integer[] monthsDays = { 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        Map<String, ForecastDetailDto> absences = getGroupAbsenceByDate(groupId, init, end);
        Map<String, String> personSaga = getGroupSagas(groupId);
        LocalDate initDate = init.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate endDate = end.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Integer initMonth = initDate.getMonthValue();
        Integer merges = 0;

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFCellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setAlignment(HorizontalAlignment.CENTER);

        XSSFCellStyle headerCellStyleVacaciones = workbook.createCellStyle();
        headerCellStyleVacaciones.setFillForegroundColor(new XSSFColor(new java.awt.Color(255, 255, 170)));
        headerCellStyleVacaciones.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFCellStyle headerCellStyleFestivo = workbook.createCellStyle();
        headerCellStyleFestivo.setFillForegroundColor(new XSSFColor(new java.awt.Color(170, 227, 255)));
        headerCellStyleFestivo.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFCellStyle headerCellStyleOtros = workbook.createCellStyle();
        headerCellStyleOtros.setFillForegroundColor(new XSSFColor(new java.awt.Color(247, 189, 70)));
        headerCellStyleOtros.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFCellStyle headerCellStyleWeekend = workbook.createCellStyle();
        headerCellStyleWeekend.setFillForegroundColor(new XSSFColor(new java.awt.Color(218, 218, 218)));
        headerCellStyleWeekend.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        if (type == 2) {

            XSSFSheet sheetTotal = workbook.createSheet("Forecast Summary");

            SortedMap<String, List<Integer>> totals = new TreeMap<>();
            for (Map.Entry<String, ForecastDetailDto> entry : absences.entrySet()) {
                List<Integer> myList = new ArrayList();
                totals.put(entry.getKey(), myList);
            }

            XSSFSheet sheet = workbook.createSheet("Forecast Detail");
            createHeaders(months, monthsDays, initDate, endDate, initMonth, merges, workbook, sheet);
            Map<String, List<Integer>> partialTotals = generateRowsOneTab(absences, initDate, endDate, workbook, headerCellStyleVacaciones, headerCellStyleFestivo, headerCellStyleWeekend, headerCellStyleOtros, sheet, personSaga);

            int month = initDate.getMonthValue();
            int year = initDate.getYear();

            int monthEnd = endDate.getMonthValue();
            int yearEnd = endDate.getYear();

            extractPartialTotals(absences, initDate, endDate, totals, month, year);
            partialMonths.add(months.get(month - 1));
            boolean completed = false;
            do {

                month++;
                if (month > 12) {
                    month = 1;
                    year++;
                }

                extractPartialTotals(absences, initDate, endDate, totals, month, year);
                partialMonths.add(months.get(month - 1));

                if (month == monthEnd && year == yearEnd)
                    completed = true;

            } while (completed == false);

            summarySheet(sheetTotal, totals, partialMonths, workbook, personSaga);
        } else {

            int actualMonth = initMonth;
            int actualYear = initDate.getYear();
            XSSFSheet sheetTotal = workbook.createSheet("Forecast Summary");
            SortedMap<String, List<Integer>> totals = new TreeMap<>();
            for (Map.Entry<String, ForecastDetailDto> entry : absences.entrySet()) {
                List<Integer> myList = new ArrayList();
                totals.put(entry.getKey(), myList);
            }
            int endMonth = endDate.getMonthValue();
            int endYear = endDate.getYear();

            boolean complete = false;

            do {
                Map<String, List<Integer>> partialTotals = new HashMap<String, List<Integer>>();
                String tabName = months.get(actualMonth - 1) + " " + actualYear;

                LocalDate partialInitDate = null;

                if (actualMonth == initMonth && actualYear == initDate.getYear()) {
                    partialInitDate = initDate;
                } else {
                    partialInitDate = LocalDate.of(actualYear, actualMonth, 1);
                }

                LocalDate partialEndDate = null;

                if (actualMonth == endMonth && actualYear == endYear) {
                    partialEndDate = endDate;
                } else {
                    partialEndDate = LocalDate.of(actualYear, actualMonth, 1);
                    partialEndDate = partialEndDate.with(TemporalAdjusters.lastDayOfMonth());
                }

                XSSFSheet sheet = workbook.createSheet(tabName);
                createHeaders(months, monthsDays, partialInitDate, partialEndDate, actualMonth, 0, workbook, sheet);

                partialTotals = generateRowsOneTab(absences, partialInitDate, partialEndDate, workbook, headerCellStyleVacaciones, headerCellStyleFestivo, headerCellStyleWeekend, headerCellStyleOtros, sheet, personSaga);

                for (Map.Entry<String, List<Integer>> entry : totals.entrySet()) {
                    entry.getValue().addAll(partialTotals.get(entry.getKey()));
                }

                if (actualYear == endYear && actualMonth == endMonth)
                    complete = true;
                partialMonths.add(months.get(actualMonth - 1));
                actualMonth++;
                if (actualMonth > 12) {
                    actualMonth = 1;
                    actualYear++;
                }

            } while (complete == false);
            summarySheet(sheetTotal, totals, partialMonths, workbook, personSaga);
        }

        try {
            // Write the workbook in file system
            File file = File.createTempFile("excel_" + System.currentTimeMillis(), "xlsx");
            FileOutputStream outputStream = new FileOutputStream(file);
            workbook.write(outputStream);
            outputStream.close();
            workbook.close();

            return file;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    private void extractPartialTotals(Map<String, ForecastDetailDto> absences, LocalDate initDate, LocalDate endDate, Map<String, List<Integer>> totals, int month, int year) {

        LocalDate partialInitDate = LocalDate.of(year, month, 1);
        if (initDate.isAfter(partialInitDate))
            partialInitDate = initDate;

        LocalDate partialEndDate = LocalDate.of(year, month, 1);
        partialEndDate = partialEndDate.withDayOfMonth(partialEndDate.lengthOfMonth());
        if (endDate.isBefore(partialEndDate))
            partialEndDate = endDate;

        for (Map.Entry<String, ForecastDetailDto> entry : absences.entrySet()) {
            long countVacaciones = countAusenciasOFestivos(true, false, entry.getValue().getAbsences(), partialInitDate, partialEndDate);
            long countOtros = countAusenciasOFestivos(true, true, entry.getValue().getAbsences(), partialInitDate, partialEndDate);
            long countFestivos = countAusenciasOFestivos(false, false, entry.getValue().getAbsences(), partialInitDate, partialEndDate);
            long countLaborales = (countBusinessDaysBetween(partialInitDate, partialEndDate, Optional.empty())) - (countVacaciones + countOtros + countFestivos);

            List<Integer> partial = new ArrayList<Integer>();

            partial.add((int) countLaborales);
            partial.add((int) countFestivos);
            partial.add((int) countVacaciones);
            partial.add((int) countOtros);

            totals.get(entry.getKey()).addAll(partial);
        }
    }

    private void summarySheet(XSSFSheet sheet, Map<String, List<Integer>> totals, List<String> months, XSSFWorkbook workbook, Map<String, String> personSaga) {

        int headerLoop = 2;
        int valuesLoopRow = 2;
        int summsTotal[] = new int[(months.size() * 4) + 4];

        CellStyle style = workbook.createCellStyle();// Create style
        Font font = workbook.createFont();// Create font
        font.setBold(true);// Make font bold
        style.setFont(font);// set it to bold
        style.setAlignment(HorizontalAlignment.CENTER);

        Row upperHeaderRow = sheet.createRow(0);
        Row headerRow = sheet.createRow(1);

        org.apache.poi.ss.usermodel.Cell cell = headerRow.createCell(0);
        cell.setCellValue("Name");

        cell = headerRow.createCell(1);
        cell.setCellValue("Saga");

        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < months.size(); i++) {
                cell = headerRow.createCell(headerLoop);
                cell.setCellValue(months.get(i));
                headerLoop++;
            }
            cell = headerRow.createCell(headerLoop);
            cell.setCellValue("Total");
            headerLoop++;
        }

        for (Map.Entry<String, List<Integer>> entry : totals.entrySet()) {
            int summs[] = new int[4];
            Row valuesRow = sheet.createRow(valuesLoopRow);

            cell = valuesRow.createCell(0);
            cell.setCellValue(entry.getKey());

            cell = valuesRow.createCell(1);
            cell.setCellValue(personSaga.get(entry.getKey()));

            int loop = 0;
            int upperLoop = 0;
            for (int i = 0; i < entry.getValue().size(); i++) {
                if (loop <= 3) {
                    cell = valuesRow.createCell((months.size() * loop) + 2 + upperLoop + loop);
                    cell.setCellValue(entry.getValue().get(i));
                    summsTotal[(months.size() * loop) + upperLoop + loop] += entry.getValue().get(i);
                    summs[loop] += entry.getValue().get(i);
                    if (loop == 3) {
                        upperLoop++;
                        loop = 0;
                    } else
                        loop++;
                }

            }
            cell = valuesRow.createCell((months.size() * 1) + 2);
            cell.setCellValue(summs[0]);
            summsTotal[(months.size() * 1)] += summs[0];
            cell = valuesRow.createCell((months.size() * 2) + 3);
            cell.setCellValue(summs[1]);
            summsTotal[(months.size() * 2) + 1] += summs[1];
            cell = valuesRow.createCell((months.size() * 3) + 4);
            cell.setCellValue(summs[2]);
            summsTotal[(months.size() * 3) + 2] += summs[2];
            cell = valuesRow.createCell((months.size() * 4) + 5);
            cell.setCellValue(summs[3]);
            summsTotal[(months.size() * 4) + 3] += summs[3];
            valuesLoopRow++;
        }
        cell = upperHeaderRow.createCell(2);
        cell.setCellValue("Working Days");
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 2, (months.size() + 2)));
        cell.setCellStyle(style);

        cell = upperHeaderRow.createCell((months.size() + 3));
        cell.setCellValue("Public Holidays");
        sheet.addMergedRegion(new CellRangeAddress(0, 0, (months.size() + 3), ((months.size() * 2) + 3)));
        cell.setCellStyle(style);

        cell = upperHeaderRow.createCell((months.size() * 2) + 4);
        cell.setCellValue("Vacations");
        sheet.addMergedRegion(new CellRangeAddress(0, 0, (months.size() * 2) + 4, (months.size() * 3) + 4));
        cell.setCellStyle(style);

        cell = upperHeaderRow.createCell((months.size() * 3) + 5);
        cell.setCellValue("Others");
        sheet.addMergedRegion(new CellRangeAddress(0, 0, (months.size() * 3) + 5, (months.size() * 4) + 5));
        cell.setCellStyle(style);

        Row totalsRow = sheet.createRow(totals.size() + 2);
        for (int i = 0; i < summsTotal.length; i++) {
            cell = totalsRow.createCell(i + 2);
            cell.setCellValue(summsTotal[i]);
        }
        sheet.setColumnWidth(0, 50 * 256);
    }

    /**
     * @param absences
     * @param headerCellStyleAusencia
     * @param headerCellStyleFestivo
     * @param headerCellStyleWeekend
     * @param sheet
     */
    private void headerCaptionColor(Map<String, ForecastDetailDto> absences, XSSFCellStyle headerCellStyleVacaciones, XSSFCellStyle headerCellStyleFestivo, XSSFCellStyle headerCellStyleWeekend, XSSFCellStyle headerCellStyleOtros,
            XSSFSheet sheet) {

        org.apache.poi.ss.usermodel.Cell cellName;
        Row leyendaRow = sheet.createRow(absences.size() + 4);
        cellName = leyendaRow.createCell(0);
        cellName = leyendaRow.createCell(1);
        cellName = leyendaRow.createCell(2);
        cellName.setCellValue("Weekend");
        cellName = leyendaRow.createCell(3);
        cellName.setCellValue("Public Holidays");
        cellName = leyendaRow.createCell(4);
        cellName.setCellValue("Vacations");
        cellName = leyendaRow.createCell(5);
        cellName.setCellValue("Others");

        Row leyendaRowColor = sheet.createRow(absences.size() + 5);
        cellName = leyendaRowColor.createCell(0);
        cellName = leyendaRowColor.createCell(1);
        cellName = leyendaRowColor.createCell(2);
        cellName.setCellStyle(headerCellStyleWeekend);
        cellName = leyendaRowColor.createCell(3);
        cellName.setCellStyle(headerCellStyleFestivo);
        cellName = leyendaRowColor.createCell(4);
        cellName.setCellStyle(headerCellStyleVacaciones);
        cellName = leyendaRowColor.createCell(5);
        cellName.setCellStyle(headerCellStyleOtros);
    }

    /**
     * @param absences
     * @param initDate
     * @param endDate
     * @param sheet
     * @param headerCellStyleAusencia
     * @param headerCellStyleFestivo
     * @param headerCellStyleWeekend
     */
    private Map<String, List<Integer>> generateRowsOneTab(Map<String, ForecastDetailDto> absences, LocalDate initDate, LocalDate endDate, XSSFWorkbook workbook, XSSFCellStyle headerCellStyleVacaciones, XSSFCellStyle headerCellStyleFestivo,
            XSSFCellStyle headerCellStyleWeekend, XSSFCellStyle headerCellStyleOtros, XSSFSheet sheet, Map<String, String> personSaga) {

        XSSFFont headerFont = workbook.createFont();
        headerFont.setBold(true);

        // Create a CellStyle with the font
        CellStyle totalCellStyle = workbook.createCellStyle();
        totalCellStyle.setFont(headerFont);

        int rowCount = 2;
        Row totalRow = sheet.createRow(absences.size() + 2);
        org.apache.poi.ss.usermodel.Cell cellTotalName = totalRow.createCell(0);
        cellTotalName.setCellValue("Total");
        org.apache.poi.ss.usermodel.Cell cellTotalLab = totalRow.createCell(2);
        cellTotalLab.setCellValue(0);
        org.apache.poi.ss.usermodel.Cell cellTotalFes = totalRow.createCell(3);
        cellTotalFes.setCellValue(0);
        org.apache.poi.ss.usermodel.Cell cellTotalA = totalRow.createCell(4);
        cellTotalA.setCellValue(0);
        org.apache.poi.ss.usermodel.Cell cellTotalO = totalRow.createCell(5);
        cellTotalO.setCellValue(0);

        cellTotalName.setCellStyle(totalCellStyle);
        cellTotalLab.setCellStyle(totalCellStyle);
        cellTotalFes.setCellStyle(totalCellStyle);
        cellTotalA.setCellStyle(totalCellStyle);
        cellTotalO.setCellStyle(totalCellStyle);

        Map<String, List<Integer>> partialTotal = new HashMap<String, List<Integer>>();

        headerCaptionColor(absences, headerCellStyleVacaciones, headerCellStyleFestivo, headerCellStyleWeekend, headerCellStyleOtros, sheet);
        for (Map.Entry<String, ForecastDetailDto> entry : absences.entrySet()) {
            int dateCellCount = 6;
            Row absencesRow = sheet.createRow(rowCount);
            long countVacaciones = countAusenciasOFestivos(true, false, entry.getValue().getAbsences(), initDate, endDate);
            long countOtros = countAusenciasOFestivos(true, true, entry.getValue().getAbsences(), initDate, endDate);
            long countFestivos = countAusenciasOFestivos(false, false, entry.getValue().getAbsences(), initDate, endDate);
            long countLaborales = (countBusinessDaysBetween(initDate, endDate, Optional.empty())) - (countVacaciones + countOtros + countFestivos);

            org.apache.poi.ss.usermodel.Cell cellName = absencesRow.createCell(0);
            cellName.setCellValue(entry.getKey());

            org.apache.poi.ss.usermodel.Cell cellSaga = absencesRow.createCell(1);
            cellSaga.setCellValue(personSaga.get(entry.getKey()));

            org.apache.poi.ss.usermodel.Cell cellLab = absencesRow.createCell(2);
            cellLab.setCellValue(countLaborales);
            org.apache.poi.ss.usermodel.Cell cellFes = absencesRow.createCell(3);
            cellFes.setCellValue(countFestivos);
            org.apache.poi.ss.usermodel.Cell cellA = absencesRow.createCell(4);
            cellA.setCellValue(countVacaciones);
            org.apache.poi.ss.usermodel.Cell cellO = absencesRow.createCell(5);
            cellO.setCellValue(countOtros);

            totalRow = sheet.getRow(absences.size() + 2);

            List<Integer> partial = new ArrayList<Integer>();
            partial.add((int) countLaborales);
            partial.add((int) countFestivos);
            partial.add((int) countVacaciones);
            partial.add((int) countOtros);
            partialTotal.put(entry.getKey(), partial);

            XSSFCellStyle headerCellStyleBlocked = workbook.createCellStyle();
            headerCellStyleBlocked.setFillForegroundColor(new XSSFColor(new java.awt.Color(234, 234, 234)));
            headerCellStyleBlocked.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            cellTotalLab.setCellValue(countLaborales + cellTotalLab.getNumericCellValue());
            cellTotalA.setCellValue(countVacaciones + cellTotalA.getNumericCellValue());
            cellTotalO.setCellValue(countOtros + cellTotalO.getNumericCellValue());
            cellTotalFes.setCellValue(countFestivos + cellTotalFes.getNumericCellValue());

            for (LocalDate date = initDate; date.isAfter(endDate) == false; date = date.plusDays(1)) {
                org.apache.poi.ss.usermodel.Cell dateCell = absencesRow.createCell(dateCellCount);
                String typeDay = typeOfDay(date, entry.getValue().getAbsences(), entry.getValue().isVisible());
                switch (typeDay) {
                case "A":
                    dateCell.setCellStyle(headerCellStyleVacaciones);
                    break;
                case "O":
                    dateCell.setCellStyle(headerCellStyleOtros);
                    break;
                case "P":
                    dateCell.setCellStyle(headerCellStyleVacaciones);
                    break;
                case "F":
                    dateCell.setCellStyle(headerCellStyleFestivo);
                    break;
                case "W":
                    dateCell.setCellStyle(headerCellStyleWeekend);
                    break;
                case "B":
                    dateCell.setCellStyle(headerCellStyleBlocked);
                    break;
                default:
                    // code block
                }
                dateCellCount++;

            }
            rowCount++;

        }
        // dsheet.autoSizeColumn(0);

        return partialTotal;
    }

    private String typeOfDay(LocalDate date, List<ForecastDto> absences, boolean visible) {

        if (!visible)
            return "B";
        DayOfWeek d = date.getDayOfWeek();
        if (d == DayOfWeek.SATURDAY || d == DayOfWeek.SUNDAY)
            return "W";
        for (ForecastDto absence : absences) {
            LocalDate datAbsence = absence.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            String absenceType = absence.getAbsence_type();
            if (datAbsence.equals(date)) {
                if ("OTH".equals(absenceType))
                    return "O";
                else
                    return absence.getType();
            }
        }
        return "L";
    }

    /**
     * @param months
     * @param monthsDays
     * @param initDate
     * @param endDate
     * @param initMonth
     * @param merges
     * @param workbook
     * @param sheet
     */
    private void createHeaders(List<String> months, Integer[] monthsDays, LocalDate initDate, LocalDate endDate, Integer initMonth, Integer merges, XSSFWorkbook workbook, XSSFSheet sheet) {

        List<String> headersUpper = new ArrayList<String>();
        headersUpper.add("Detail");
        List<String> headersLower = new ArrayList<String>();
        headersLower.add("Name");
        headersLower.add("Saga");
        headersLower.add("Working Days");
        headersLower.add("Public Holidays");
        headersLower.add("Vacations");
        headersLower.add("Others");

        XSSFFont headerFont = workbook.createFont();
        headerFont.setBold(true);

        // Create a CellStyle with the font
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);
        headerCellStyle.setAlignment(HorizontalAlignment.CENTER);

        Row headerRowUpper = sheet.createRow(0);
        Row headerRow = sheet.createRow(1);

        monthsDays[initMonth] = initDate.lengthOfMonth() - initDate.getDayOfMonth();
        if (initDate.getMonthValue() == endDate.getMonthValue()) {
            monthsDays[initMonth] = endDate.getDayOfMonth() - initDate.getDayOfMonth();
        }

        headersUpper.add(months.get(initDate.getMonthValue() - 1));

        int month = initMonth;
        for (LocalDate date = initDate; date.isAfter(endDate) == false; date = date.plusDays(1)) {
            if ((month != date.getMonthValue()) && (endDate.getMonthValue() != date.getMonthValue())) {
                month = date.getMonthValue();
                headersUpper.add(months.get(date.getMonthValue() - 1));
                monthsDays[month] = date.lengthOfMonth() - 1;
            }
            headersLower.add(String.valueOf(date.getDayOfMonth()));
        }

        if (initDate.getMonthValue() != endDate.getMonthValue()) {
            monthsDays[endDate.getMonthValue()] = endDate.getDayOfMonth() - 1;
            headersUpper.add(months.get(endDate.getMonthValue() - 1));
        }

        org.apache.poi.ss.usermodel.Cell cell;
        int accumulatedPosition = 2;

        for (int i = 0; i < headersUpper.size(); i++) {

            int actualDays = monthsDays[i];

            if (i > 0) {
                int actualMonth = initMonth + i - 1;
                if (actualMonth > 12)
                    actualMonth -= 12;

                actualDays = monthsDays[actualMonth];
            }

            cell = headerRowUpper.createCell(accumulatedPosition);
            cell.setCellStyle(headerCellStyle);
            cell.setCellValue(headersUpper.get(i));

            int initCol = accumulatedPosition;
            int endCol = accumulatedPosition + actualDays;

            for (int j = initCol + 1; j <= endCol; j++)
                cell = headerRowUpper.createCell(j);

            if (endCol > initCol)
                sheet.addMergedRegion(new CellRangeAddress(0, 0, initCol, endCol));

            accumulatedPosition += actualDays + 1;
        }

        for (int i = 0; i < headersLower.size(); i++) {
            cell = headerRow.createCell(i);
            cell.setCellType(CellType.NUMERIC);
            if (i > 5)
                cell.setCellValue(Integer.parseInt(headersLower.get(i)));
            else
                cell.setCellValue(headersLower.get(i));
            cell.setCellStyle(headerCellStyle);

            if (i >= 6)
                sheet.setColumnWidth(i, 3 * 256);
        }

        sheet.setColumnWidth(0, 50 * 256);
        sheet.setColumnWidth(1, 15 * 256);
        sheet.setColumnWidth(2, 15 * 256);
        sheet.setColumnWidth(3, 15 * 256);
        sheet.setColumnWidth(4, 15 * 256);
        sheet.setColumnWidth(5, 15 * 256);

    }

    private List<ForecastDto> extractAbsencesFromList(Integer integer, List<PersonAbsenceEntity> absenceList) {

        List<ForecastDto> listAbsence = new ArrayList<>();
        Map<Date, ForecastDto> mapAbsences = new HashMap<>();
        for (PersonAbsenceEntity absence : absenceList) {

            if (integer.equals(absence.getPerson().getId()) == false)
                continue;

            int weekDay = absence.getDate().getDay();

            if (weekDay == 0 || weekDay == 6)
                continue;

            String absenceType = absence.getType();
            ForecastDto absenceDto = mapAbsences.get(absence.getDate());
            if (absenceDto == null) {
                absenceDto = new ForecastDto();
                absenceDto.setDate(absence.getDate());
                absenceDto.setMonth(absence.getMonth());
                absenceDto.setYear(absence.getYear());
                absenceDto.setAbsence_type(absence.getAbsence_type());
                absenceDto.setType(absenceType);

                listAbsence.add(absenceDto);
                mapAbsences.put(absence.getDate(), absenceDto);

            }

            String absenceDtoType = absenceDto.getType();

            if (absenceType.equals("F")) {
                absenceDto.setType(absenceType);
            } //
            else if (absenceType.equals("P") || absenceType.equals("A")) {
                if (absenceDtoType.equals("P") || absenceDtoType.equals("A")) {
                    absenceDto.setType(absenceType);
                }
            }

            if (absenceDto.getAbsence_type() != null)
                absenceDto.setAbsence_type(absence.getAbsence_type());
        }

        return listAbsence;
    }

    private long countBusinessDaysBetween(LocalDate startDate, LocalDate endDate, Optional<List<LocalDate>> holidays) {

        if (startDate == null || endDate == null || holidays == null) {
            throw new IllegalArgumentException("Invalid method argument(s) to countBusinessDaysBetween(" + startDate + "," + endDate + "," + holidays + ")");
        }

        Predicate<LocalDate> isHoliday = date -> holidays.isPresent() ? holidays.get().contains(date) : false;

        Predicate<LocalDate> isWeekend = date -> date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY;

        long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);

        long businessDays = Stream.iterate(startDate, date -> date.plusDays(1)).limit(daysBetween + 1).filter(isHoliday.or(isWeekend).negate()).count();
        return businessDays;
    }

    /**
     * @param groupId
     */
    private List<Integer> extractListPersonId(List<PersonEntity> groupMembersList) {

        List<Integer> personIds = new ArrayList<>();

        for (PersonEntity groupMember : groupMembersList) {
            personIds.add(groupMember.getId());
        }

        return personIds;
    }

    private int countAusenciasOFestivos(boolean countAbsences, boolean typeO, List<ForecastDto> absences, LocalDate initLocalDate, LocalDate endLocalDate) {

        int count = 0;
        int countO = 0;

        Date initDate = Date.from(initLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date endDate = Date.from(endLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        for (ForecastDto absence : absences) {

            if (absence.getDate().before(initDate))
                continue;
            if (absence.getDate().after(endDate))
                continue;

            if (countAbsences) {
                if ((absence.getType().equals("A") || absence.getType().equals("P"))) {
                    if (absence.getAbsence_type() != null) {
                        if (absence.getAbsence_type().equals("VAC")) {
                            count++;
                        } else if (absence.getAbsence_type().equals("OTH")) {
                            countO++;
                        }
                    } else {
                        count++;
                    }
                }
            } else {
                if ((absence.getType().equals("F")))
                    count++;
            }
        }

        if (typeO)
            return countO;
        else
            return count;
    }
}
