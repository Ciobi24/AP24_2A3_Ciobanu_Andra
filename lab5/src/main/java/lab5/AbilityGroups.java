package lab5;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class AbilityGroups {
    public static void main(String[] args) throws IOException {
        Map<String, Set<String>> abilityToPersons = new HashMap<>();
        FileInputStream fis = new FileInputStream(new File("src/repo/abilities.xlsx"));

        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            String person = row.getCell(0).getStringCellValue();
            String ability = row.getCell(1).getStringCellValue();

            if (!abilityToPersons.containsKey(ability)) {
                abilityToPersons.put(ability, new HashSet<>());
            }
            abilityToPersons.get(ability).add(person);
        }

        fis.close();

        Set<Set<String>> groups = new HashSet<>();
        for (Set<String> persons : abilityToPersons.values()) {
            groups.add(persons);
        }

        for (Set<String> group : groups) {
            System.out.println("Group: " + group);
        }
    }
}
