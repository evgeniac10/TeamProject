package com.example.wetro.repository;

import com.example.wetro.dto.Station;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ExcelRead {
        List<Station> stations = new ArrayList<>();

        @Autowired
        public ExcelRead() {
                try {
                        File file = new File("./stations.xlsx");
                        FileInputStream fis = new FileInputStream(file);
                        XSSFWorkbook sheets = new XSSFWorkbook(fis);
                        XSSFSheet sheet = sheets.getSheetAt(0);//엑셀 파일의 시트들중에서 0번째 시트 가져오기

                        int rowindex =0;//엑셀 파일에 읽는 행의 번호
                        int colindex =0;//엑셀 파일에 읽는 열의 번호
                        int row = sheet.getPhysicalNumberOfRows();//현재 저장되어있는 엑셀 파일의 행의 개수를 반환해주는 메서드

                        for(rowindex =1 ; rowindex<row; rowindex++){
                                XSSFRow row1 = sheet.getRow(rowindex);//엑셀 시트에서 rowindex에 해당하는 행을 읽고
                                if(row1 != null){// 잘 읽었다면
                                        Station station = new Station();
                                        station.setFrom(String.valueOf(row1.getCell(0)));
                                        station.setTo(String.valueOf(row1.getCell(1)));
                                        station.setTime((int) row1.getCell(2).getNumericCellValue());
                                        station.setDistance((int) row1.getCell(3).getNumericCellValue());
                                        station.setCost((int) row1.getCell(4).getNumericCellValue());

                                        stations.add(station);
                                        }
                                }
                }catch (NumberFormatException e) {
                        // 숫자로 변환할 수 없는 경우의 예외 처리 코드
                        e.printStackTrace(); // 또는 로깅 등을 활용하여 예외를 기록
                        // 필요에 따라 기본값이나 다른 처리를 수행할 수 있음
                } catch (FileNotFoundException e) {
                        // FileNotFoundException 처리 코드
                        e.printStackTrace();
                } catch (IOException e) {
                        // IOException 처리 코드
                        e.printStackTrace();
                }
        }
        public List<Station> getStations() {
                return stations;
        }
}
