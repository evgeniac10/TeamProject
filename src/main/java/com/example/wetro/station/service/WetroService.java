package com.example.wetro.station.service;

import com.example.wetro.station.dto.Station;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WetroService {
    private final ExcelRead excelRead;

    @Autowired
    public WetroService(ExcelRead excelRead) {
        this.excelRead = excelRead;
    }

    public List<Station> getStations() {
        return excelRead.getStations();
    }
    public Integer searchFromStation(Integer from) {
        for(Station s : getStations()){
            if(s.getFrom().equals(from)){//사용자가 입력한 출발역이 엑셀파일에 있어야 실행되는 조건문
            }
        }
        return from;
    }

    public Integer searchToStation(Integer to) {
        for(Station s : getStations()){
            if(s.getFrom().equals(to))
                System.out.println("s.toString() = " + s.toString());
        }
        return to;
    }

    public void isStation() {
    }

//    public List<Station> shortTime(Integer depart , Integer arrive) {
//        List<Station> results = new ArrayList<>();
//        results.add((Station) depart);
//        results.add((Station) arrive);
//        return results;
//    }
}
