package com.example.wetro.repository;

import com.example.wetro.dto.Station;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelRead {
        public ExcelRead() {
                try {
                        File file = new File("./stations.xlsx");
                        FileInputStream fis = new FileInputStream(file);
                        XSSFWorkbook sheets = new XSSFWorkbook(fis);
                        XSSFSheet sheet = sheets.getSheetAt(0);//엑셀 파일의 시트들중에서 0번째 시트 가져오기

                        List<Station> stations = new ArrayList<>();

                        int rowindex =0;//엑셀 파일에 읽는 행의 번호
                        int colindex =0;//엑셀 파일에 읽는 열의 번호
                        int row = sheet.getPhysicalNumberOfRows();//현재 저장되어있는 엑셀 파일의 행의 개수를 반환해주는 메서드

                        for(rowindex =1 ; rowindex<row; rowindex++){
                                XSSFRow row1 = sheet.getRow(rowindex);//엑셀 시트에서 rowindex에 해당하는 행을 읽고
                                if(row1 != null){// 잘 읽었다면
                                        Station station = new Station();
                                        station.setStart(String.valueOf(row1.getCell(0)));
                                        station.setFinish(String.valueOf(row1.getCell(1)));
                                        station.setTime(Integer.parseInt(String.valueOf(row1.getCell(2))));
                                        station.setDistance(Integer.parseInt(String.valueOf((row1.getCell(3)))));
                                        station.setCost(Integer.parseInt(String.valueOf(row1.getCell(4))));

                                        stations.add(station);
                                        }
                                }
                } catch (FileNotFoundException e) {
                        // FileNotFoundException 처리 코드
                        e.printStackTrace();
                } catch (IOException e) {
                        // IOException 처리 코드
                        e.printStackTrace();
                }
        }
}
