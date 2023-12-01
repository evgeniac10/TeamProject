package com.example.wetro.dijkstra;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@Setter
@RequiredArgsConstructor
@Component
public class Node implements Comparable<Node>{
    //호선
    private String line;
    //노드 이름
    private String name;
    //현재까지의 최단 거리 초기값 무한
    private Integer distance = Integer.MAX_VALUE;
    //최단 경로 저장, 수정 용이하게 위해 링크드리스트
    private List<Node> shortestPath = new LinkedList<>();
    //해당 노드의 인접한 노드와 그 사이의 가중치를 저장
    private Map<Node, Integer> adjacentNodes = new HashMap<>();
    //환승 횟수
    private int transferCount = 0;

    public Node(String Line, String name){
        this.line = Line;
        this.name = name;
    }

    //노드 초기화
    public static void initializeNodes(Node... nodes) {
        for (Node node : nodes) {
            node.setDistance(Integer.MAX_VALUE);
            node.setShortestPath(new LinkedList<>());
        }
    }


    //노드에 인접노드, 가중치 추가
    public void addAdjacentNode(Node node, int weight){
        adjacentNodes.put(node, weight);
    }


    @Override
    public int compareTo(Node node){
        return Integer.compare(this.distance, node.getDistance());
    }


    //최소 가중치 계산 메서드
    public static void calculateShortestPath(Node source, Node destination){
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
        //경로, 거리 출력
        source.printPath(destination);
    }



    //주어진 인접노드와의 최단경로 평가하고 업데이트
    private static void evaluateDistanceAndPath(Node adjacentNode, Integer edgeWeight, Node sourceNode){
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


    //경로 + 최소 가중치
    private void printPath(Node destination) {
        String path = destination.getShortestPath().stream()
                .map(Node::getName)
                .collect(Collectors.joining(" -> "));

        System.out.println(path.isBlank()
                ? String.format("%s : %s", destination.getName(), destination.getDistance())
                : String.format("%s -> %s : %s", path, destination.getName(), destination.getDistance()));
    }
//최소환승 메서드들

    public static void initializeNodesT(Node... nodes) {
        for (Node node : nodes) {
            node.setDistance(Integer.MAX_VALUE);
            node.setShortestPath(new LinkedList<>());
            node.setTransferCount(0);
        }
    }
    public void addAdjacentNodeT(Node node, int weight){
        if (!this.line.equals(node.getLine())) {
            weight += 2000; // 환승하면 가중치에 추가
        }
        adjacentNodes.put(node, weight);
    }
    public static void calculateMinTransfer(Node source, Node destination){
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
                        evaluateTransferAndPath(entry.getKey(), entry.getValue(), currentNode);
                        unsettledNodes.add(entry.getKey());
                    });

            settleNodes.add(currentNode);
        }
        //경로, 거리 출력
        source.printMinTransfer(destination);
    }
    private static void evaluateTransferAndPath(Node adjacentNode, Integer edgeWeight, Node sourceNode){
        Integer newDistance = sourceNode.getDistance() + edgeWeight;
        if(newDistance < adjacentNode.getDistance()){
            adjacentNode.setDistance(newDistance);
            List<Node> newPath = Stream.concat(sourceNode.getShortestPath().stream(), Stream.of(sourceNode))
                    .collect(Collectors.toList());
            adjacentNode.setShortestPath(newPath);

            // 환승 횟수 업데이트
            if (!sourceNode.getLine().equals(adjacentNode.getLine())) {
                adjacentNode.setTransferCount(sourceNode.getTransferCount() + 1);
            } else {
                adjacentNode.setTransferCount(sourceNode.getTransferCount());
            }
        }
    }
    private void printMinTransfer(Node destination) {
        String path = destination.getShortestPath().stream()
                .map(Node::getName)
                .collect(Collectors.joining(" -> "));

        System.out.println(path.isBlank()
                ? String.format("%s", destination.getName())
                : String.format("%s -> %s, 환승 횟수: %d", path, destination.getName(), destination.getTransferCount())); // 거리 출력 제거
    }
    public static void main(String[] args) {
        //노드 추가
        Node node1_1 = new Node("1","123");
        Node node1_3 = new Node("3","123");
        Node node2_1 = new Node("1","122");
        Node node2_5 = new Node("5","122");
        Node node3 = new Node("3","304");
        Node node4 = new Node("5","504");
        Node node5_3 = new Node("3","303");
        Node node5_7 = new Node("7","303");
        Node node6_5 = new Node("5","503");
        Node node6_7 = new Node("7","503");
        Node node7 = new Node("1","101");
        Node node8_6 = new Node("6","601");
        Node node8_7 = new Node("7","601");
        Node node9 = new Node("6","602");
        Node node10_1 = new Node("1","121");
        Node node10_6 = new Node("6","121");

        //모든 노드 배열
        Node[] allNodes = {node1_1, node1_3, node2_1,node2_5 ,node3, node4, node5_3,
                node5_7, node6_5,node6_7, node7, node8_6, node8_7, node9, node10_1, node10_6};

        //각 노드에 인접한 노드와 가중치 추가
        node1_1.addAdjacentNode(node2_1, 1);
        node1_1.addAdjacentNode(node2_5, 1);
        node1_1.addAdjacentNode(node3, 2);
        node1_1.addAdjacentNode(node7, 5);

        node1_3.addAdjacentNode(node2_1, 1);
        node1_3.addAdjacentNode(node2_5, 1);
        node1_3.addAdjacentNode(node3, 2);
        node1_3.addAdjacentNode(node7, 5);

        node2_1.addAdjacentNode(node4, 5);
        node2_1.addAdjacentNode(node1_1, 1);
        node2_1.addAdjacentNode(node1_3, 1);
        node2_1.addAdjacentNode(node10_1, 4);
        node2_1.addAdjacentNode(node10_6, 4);

        node2_5.addAdjacentNode(node4, 5);
        node2_5.addAdjacentNode(node1_1, 1);
        node2_5.addAdjacentNode(node1_3, 1);
        node2_5.addAdjacentNode(node10_1, 4);
        node2_5.addAdjacentNode(node10_6, 4);

        node3.addAdjacentNode(node5_3, 5);
        node3.addAdjacentNode(node5_7, 5);
        node3.addAdjacentNode(node1_1, 2);
        node3.addAdjacentNode(node1_3, 2);

        node4.addAdjacentNode(node6_5, 4);
        node4.addAdjacentNode(node6_7, 4);
        node4.addAdjacentNode(node2_1, 5);
        node4.addAdjacentNode(node2_5, 5);

        node5_3.addAdjacentNode(node6_5, 9);
        node5_3.addAdjacentNode(node6_7, 9);
        node5_3.addAdjacentNode(node3, 5);

        node5_7.addAdjacentNode(node6_5, 9);
        node5_7.addAdjacentNode(node6_7, 9);
        node5_7.addAdjacentNode(node3, 5);

        node6_5.addAdjacentNode(node5_3, 9);
        node6_5.addAdjacentNode(node5_7, 9);
        node6_5.addAdjacentNode(node4, 4);
        node6_5.addAdjacentNode(node8_7, 10);
        node6_5.addAdjacentNode(node8_6, 10);

        node6_7.addAdjacentNode(node5_3, 9);
        node6_7.addAdjacentNode(node5_7, 9);
        node6_7.addAdjacentNode(node4, 4);
        node6_7.addAdjacentNode(node8_7, 10);
        node6_7.addAdjacentNode(node8_6, 10);


        node7.addAdjacentNode(node1_1, 5);
        node7.addAdjacentNode(node1_3, 5);

        node8_6.addAdjacentNode(node6_7, 10);
        node8_6.addAdjacentNode(node6_5, 10);
        node8_6.addAdjacentNode(node9, 34);

        node8_7.addAdjacentNode(node6_7, 10);
        node8_7.addAdjacentNode(node6_5, 10);
        node8_7.addAdjacentNode(node9, 34);

        node9.addAdjacentNode(node8_6, 34);
        node9.addAdjacentNode(node8_7, 34);
        node9.addAdjacentNode(node10_1, 4);
        node9.addAdjacentNode(node10_6, 4);

        node10_1.addAdjacentNode(node9, 4);
        node10_1.addAdjacentNode(node2_1, 4);
        node10_1.addAdjacentNode(node2_5, 4);

        node10_6.addAdjacentNode(node9, 4);
        node10_6.addAdjacentNode(node2_1, 4);
        node10_6.addAdjacentNode(node2_5, 4);

        //한번 최단경로 계산하면 초기화 해야함
        calculateShortestPath(node7, node8_6);
        initializeNodes(allNodes);
        calculateShortestPath(node7, node8_7);
        initializeNodes(allNodes);

        calculateShortestPath(node1_1, node8_6);
        initializeNodes(allNodes);
        calculateShortestPath(node1_1, node8_7);
        initializeNodes(allNodes);
        calculateShortestPath(node1_3, node8_6);
        initializeNodes(allNodes);
        calculateShortestPath(node1_3, node8_7);
        initializeNodes(allNodes);

        //최소환승일때
        node1_1.addAdjacentNodeT(node2_1, 1);
        node1_1.addAdjacentNodeT(node2_5, 1);
        node1_1.addAdjacentNodeT(node3, 2);
        node1_1.addAdjacentNodeT(node7, 5);

        node1_3.addAdjacentNodeT(node2_1, 1);
        node1_3.addAdjacentNodeT(node2_5, 1);
        node1_3.addAdjacentNodeT(node3, 2);
        node1_3.addAdjacentNodeT(node7, 5);

        node2_1.addAdjacentNodeT(node4, 5);
        node2_1.addAdjacentNodeT(node1_1, 1);
        node2_1.addAdjacentNodeT(node1_3, 1);
        node2_1.addAdjacentNodeT(node10_1, 4);
        node2_1.addAdjacentNodeT(node10_6, 4);

        node2_5.addAdjacentNodeT(node4, 5);
        node2_5.addAdjacentNodeT(node1_1, 1);
        node2_5.addAdjacentNodeT(node1_3, 1);
        node2_5.addAdjacentNodeT(node10_1, 4);
        node2_5.addAdjacentNodeT(node10_6, 4);

        node3.addAdjacentNodeT(node5_3, 5);
        node3.addAdjacentNodeT(node5_7, 5);
        node3.addAdjacentNodeT(node1_1, 2);
        node3.addAdjacentNodeT(node1_3, 2);

        node4.addAdjacentNodeT(node6_5, 4);
        node4.addAdjacentNodeT(node6_7, 4);
        node4.addAdjacentNodeT(node2_1, 5);
        node4.addAdjacentNodeT(node2_5, 5);

        node5_3.addAdjacentNodeT(node6_5, 9);
        node5_3.addAdjacentNodeT(node6_7, 9);
        node5_3.addAdjacentNodeT(node3, 5);

        node5_7.addAdjacentNodeT(node6_5, 9);
        node5_7.addAdjacentNodeT(node6_7, 9);
        node5_7.addAdjacentNodeT(node3, 5);

        node6_5.addAdjacentNodeT(node5_3, 9);
        node6_5.addAdjacentNodeT(node5_7, 9);
        node6_5.addAdjacentNodeT(node4, 4);
        node6_5.addAdjacentNodeT(node8_7, 10);
        node6_5.addAdjacentNodeT(node8_6, 10);

        node6_7.addAdjacentNodeT(node5_3, 9);
        node6_7.addAdjacentNodeT(node5_7, 9);
        node6_7.addAdjacentNodeT(node4, 4);
        node6_7.addAdjacentNodeT(node8_7, 10);
        node6_7.addAdjacentNodeT(node8_6, 10);


        node7.addAdjacentNodeT(node1_1, 5);
        node7.addAdjacentNodeT(node1_3, 5);

        node8_6.addAdjacentNodeT(node6_7, 10);
        node8_6.addAdjacentNodeT(node6_5, 10);
        node8_6.addAdjacentNodeT(node9, 34);

        node8_7.addAdjacentNodeT(node6_7, 10);
        node8_7.addAdjacentNodeT(node6_5, 10);
        node8_7.addAdjacentNodeT(node9, 34);

        node9.addAdjacentNodeT(node8_6, 34);
        node9.addAdjacentNodeT(node8_7, 34);
        node9.addAdjacentNodeT(node10_1, 4);
        node9.addAdjacentNodeT(node10_6, 4);

        node10_1.addAdjacentNodeT(node9, 4);
        node10_1.addAdjacentNodeT(node2_1, 4);
        node10_1.addAdjacentNodeT(node2_5, 4);

        node10_6.addAdjacentNodeT(node9, 4);
        node10_6.addAdjacentNodeT(node2_1, 4);
        node10_6.addAdjacentNodeT(node2_5, 4);

        initializeNodesT(allNodes);
        calculateMinTransfer(node7, node8_6);
        initializeNodesT(allNodes);
        calculateMinTransfer(node7, node8_7);

        initializeNodesT(allNodes);
        calculateMinTransfer(node1_1, node8_6);
        initializeNodesT(allNodes);
        calculateMinTransfer(node1_1, node8_7);
        initializeNodesT(allNodes);
        calculateMinTransfer(node1_3, node8_6);
        initializeNodesT(allNodes);
        calculateMinTransfer(node1_3, node8_7);
    }
}
