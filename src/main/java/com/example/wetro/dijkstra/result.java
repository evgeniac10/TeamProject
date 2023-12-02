package com.example.wetro.dijkstra;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@Component
public class result {
    private List<Node> shortestPath;
    private Integer distance;

    public result(List<Node> shortestPath, Integer distance) {
        this.shortestPath = shortestPath;
        this.distance = distance;
    }
}
