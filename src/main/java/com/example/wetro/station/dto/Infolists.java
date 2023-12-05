package com.example.wetro.station.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Infolists {
    Integer time;
    Integer cost;
    Integer transfer;
    List<?> transStation;
}
