package net.lecigne.dev.excel;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

@Slf4j
public class ExcelFileUtils {

  public static <T> List<T> sheetToPOJO(InputStream is, Class<T> beanClass) throws IOException {
    Workbook workbook = WorkbookFactory.create(is);
    Sheet sheet = workbook.getSheetAt(0);
    Map<Integer, String> colHeadersByColIdx = getColHeadersByCoIndex(sheet);
    Map<String, Field> beanFieldsByExlColName = beanFieldsByExlColName(beanClass);
    return IntStream.range(sheet.getFirstRowNum() + 1, sheet.getLastRowNum())
        .parallel()
        .mapToObj(rowNum -> {
          T bean;
          try {
            bean = beanClass.getDeclaredConstructor().newInstance();
            Row currentRow = sheet.getRow(rowNum);
            if (Objects.isNull(currentRow)) {
              currentRow = sheet.createRow(rowNum);
            }
            Row finalCurrentRow = currentRow;
            T finalBean = bean;
            colHeadersByColIdx.keySet()
                .parallelStream()
                .forEach(colIdx -> {
                  String colName = colHeadersByColIdx.get(colIdx);
                  Cell cell = finalCurrentRow.getCell(colIdx);
                  if (Objects.isNull(cell)) {
                    cell = finalCurrentRow.createCell(colIdx);
                  }
                  String cellValue = cell.getStringCellValue();
                  Field fieldForColName = beanFieldsByExlColName.get(colName);
                  fieldForColName.setAccessible(true);
                  try {
                    if (fieldForColName.getType() == String.class) {
                      fieldForColName.set(finalBean, cellValue);
                    }
                    if (fieldForColName.getType() == Double.class) {
                      fieldForColName.set(finalBean, cell.getNumericCellValue());
                    }
                    if (fieldForColName.getType() == Set.class) {
                      fieldForColName.set(finalBean, Arrays.stream(cellValue.split(",")).collect(Collectors.toSet()));
                    }
                  } catch (IllegalAccessException ex) {
                    throw new RuntimeException(ex);
                  }

                });
          } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                   NoSuchMethodException e) {
            throw new RuntimeException(e);
          }
          return bean;
        }).collect(Collectors.toList());
  }

  private static <T> Map<String, Field> beanFieldsByExlColName(Class<T> beanClass) {
    Map<String, Field> beanFieldsByExlColName = new HashMap<>();
    Arrays.stream(beanClass.getDeclaredFields())
        .parallel()
        .filter(field -> field.isAnnotationPresent(ExcelColumn.class))
        .forEach(field -> {
          ExcelColumn ec = field.getAnnotation(ExcelColumn.class);
          beanFieldsByExlColName.put(ec.name(), field);
        });
    return beanFieldsByExlColName;
  }

  private static Map<Integer, String> getColHeadersByCoIndex(Sheet sheet) {
    Map<Integer, String> colHeadersByColIdx = new HashMap<>();
    Row row = sheet.getRow(sheet.getFirstRowNum());
    for (Cell cell : row) {
      int colIdx = cell.getColumnIndex();
      if (!cell.getStringCellValue().isBlank()) {
        colHeadersByColIdx.put(colIdx, cell.getStringCellValue());
      }
    }
    return colHeadersByColIdx;
  }

}
