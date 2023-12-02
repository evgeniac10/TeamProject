package com.example.wetro.station.service;
import com.example.wetro.station.repository.ExcelRead;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.*;

class Station {
    int id;
    int time;
    int distance;
    int cost;

    public Station(int id, int time, int distance, int cost) {
        this.id = id;
        this.time = time;
        this.distance = distance;
        this.cost = cost;
    }
}

@RequiredArgsConstructor
public class DijkstraExample {
    private static final int INFINITY = Integer.MAX_VALUE;
    private static ExcelRead excelRead;

    public static void main(String[] args) {
        // 그래프 구성
        Map<Integer, List<Station>> graph = buildGraph();

        // 출발역
        int startStation = 101;

        // 최소 비용 계산
        Map<Integer, Integer> minCosts = dijkstra(graph, startStation, "cost");
        System.out.println("최소 비용: " + minCosts);

        // 최소 시간 계산
        Map<Integer, Integer> minTimes = dijkstra(graph, startStation, "time");
        System.out.println("최소 시간: " + minTimes);
    }

    private static Map<Integer, List<Station>> buildGraph() {
        // 데이터 입력
        // (출발역, 도착역) -> 역 정보
        Map<Integer, List<Station>> graph = new HashMap<>();
        // TODO: 데이터를 이용하여 그래프를 구성하는 코드 작성
        return graph;
    }

    private static Map<Integer, Integer> dijkstra(Map<Integer, List<Station>> graph, int start, String criteria) {
        // 출발역에서 각 역까지의 최소 비용 또는 최소 시간 저장
        Map<Integer, Integer> result = new HashMap<>();
        // 방문한 역 저장
        Set<Integer> visited = new HashSet<>();

        // PriorityQueue를 이용하여 최소 비용 또는 최소 시간의 역을 선택
        PriorityQueue<Station> priorityQueue = new PriorityQueue<>(
                Comparator.comparingInt(s -> criteria.equals("cost") ? s.cost : s.time)
                );
        priorityQueue.add(new Station(start, 0, 0, 0));

        while (!priorityQueue.isEmpty()) {
            Station currentStation = priorityQueue.poll();

            if (visited.contains(currentStation.id)) {//방문했던 역은 통과
                continue;
            }

            visited.add(currentStation.id);//방문하지 않았던 역은 HashSet에 추가하여 방문처리하기
            result.put(currentStation.id, criteria.equals("cost") ? currentStation.cost : currentStation.time);

            // 연결된 역 확인
            List<Station> neighbors = graph.get(currentStation.id);
            if (neighbors != null) {//연결된 역이 없을 수 있으니 ex) 종착역
                for (Station neighbor : neighbors) {//연결된 역마다 반복문으로
                    if (!visited.contains(neighbor.id)) {
                        int total = criteria.equals("cost") ?
                                currentStation.cost + neighbor.cost : currentStation.time + neighbor.time;
                        priorityQueue.add(new Station(neighbor.id, neighbor.time, neighbor.distance, total));
                    }
                }
            }
        }

        return result;
    }
}
