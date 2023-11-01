package com.example.wetro.service;

import com.example.wetro.dto.Station;
import com.example.wetro.repository.ExcelRead;
import lombok.AllArgsConstructor;
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
    public void searchFromStation(Integer from) {
        for(Station s : getStations()){
            if(s.getFrom().equals(from))
                System.out.println("s.toString() = " + s.toString());
        }

    }

    public void searchToStation(Integer to) {
        System.out.println("------------searchToStation메서드-----------");
        for(Station s : getStations()){
            if(s.getFrom().equals(to))
                System.out.println("s.toString() = " + s.toString());
        }
    }

    public void isStation() {
    }
}
