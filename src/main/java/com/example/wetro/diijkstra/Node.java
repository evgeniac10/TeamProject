package com.example.wetro.diijkstra;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@Setter
@RequiredArgsConstructor
public class Node implements Comparable<Node>{
    //노드 이름
    private final String name;
    //현재까지의 최단 거리 초기값 무한
    private Integer distance = Integer.MAX_VALUE;
    //최단 경로 저장, 수정 용이하게 위해 링크드리스트
    private List<Node> shortestPath = new LinkedList<>();
    //해당 노드의 인접한 노드와 그 사이의 가중치를 저장
    private Map<Node, Integer> adjacentNodes = new HashMap<>();

    //노드에 인접노드, 가중치 추가
    public void addAdjacentNode(Node node, int weight){
        adjacentNodes.put(node, weight);
    }

    @Override
    public int compareTo(Node node){
        return Integer.compare(this.distance, node.getDistance());
    }


    public void calculateShortesPath(Node source, Node destination){
        source.setDistance(0);
        //최단거리 확정된 경로
        Set<Node> settleNodes = new HashSet<>();
        //최단거리 미확정 경로, 미확정 노드들 중 최소 거리를 가진 노드 먼저 처리하려고 우선순위큐 사용
        Queue<Node> unsettledNodes = new PriorityQueue<>(Collections.singleton(source));

        //
        while(!unsettledNodes.isEmpty()){
            Node currentNode = unsettledNodes.poll();

            if(currentNode.equals(destination)){
                break;
            }

            currentNode.getAdjacentNodes()
                    .entrySet().stream()
                    .filter(entry -> !settleNodes.contains(entry.getKey()))
                    .forEach(entry -> {
                        evaluateDistanceAndPath(entry.getKey(), entry.getValue(), currentNode);
                        unsettledNodes.add(entry.getKey());
                    });

            settleNodes.add(currentNode);
        }
    }

    //주어진 인접노드와의 최단경로 평가하고 업데이트
    private void evaluateDistanceAndPath(Node adjacentNode, Integer edgeWeight, Node sourceNode){
        Integer newDistance = sourceNode.getDistance() + edgeWeight;
        //새 가중치가 기존 가중치보다 작으면
        if(newDistance < adjacentNode.getDistance()){
            //가중치 초기화
            adjacentNode.setDistance(newDistance);
            //ShortestPath에 추가
            List<Node> newPath = Stream.concat(sourceNode.getShortestPath().stream(), Stream.of(sourceNode))
                    .collect(Collectors.toList());
            adjacentNode.setShortestPath(newPath);
        }
    }

    //출발역에서 도착역까지의 경로와 최소 가중치 출력
    private static void printPath(Node source, Node destination) {
        String path = destination.getShortestPath().stream()
                .map(Node::getName)
                .collect(Collectors.joining(" -> "));

        System.out.println(path.isBlank()
                ? String.format("%s : %s", destination.getName(), destination.getDistance())
                : String.format("%s -> %s : %s", path, destination.getName(), destination.getDistance()));
    }

    public static void main(String[] args) {
        Node node1 = new Node("123");
        Node node2 = new Node("122");
        Node node3 = new Node("304");
        Node node4 = new Node("504");
        Node node5 = new Node("303");
        Node node6 = new Node("503");

        node1.addAdjacentNode(node2, 1);
        node1.addAdjacentNode(node3, 2);

        node2.addAdjacentNode(node4, 5);

        node3.addAdjacentNode(node5, 5);

        node4.addAdjacentNode(node6, 4);

        node5.addAdjacentNode(node6, 9);

        node1.calculateShortesPath(node1, node6);
        printPath(node1, node6);
    }
}
