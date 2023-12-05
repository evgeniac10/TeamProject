package com.example.wetro.station.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Infolists {
    Integer time;
    Integer cost;
    Integer transfer;
    String path;
}
