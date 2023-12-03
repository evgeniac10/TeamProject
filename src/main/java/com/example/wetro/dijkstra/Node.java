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
    private Integer cost = Integer.MAX_VALUE;
    private Integer time = Integer.MAX_VALUE;
    //최단 경로 저장, 수정 용이하게 위해 링크드리스트
    private List<Node> shortestPath = new LinkedList<>();
    //해당 노드의 인접한 노드와 그 사이의 가중치를 저장
    private Map<Node, List<Integer>> adjacentNodes = new HashMap<>();
    //환승 횟수
    private int transferCount = 0;

    //일반역 리스트
    private static List<Node> nodes = new ArrayList<>();
    //환승역 리스트
    private static List<Node> transNodes = new ArrayList<>();

    public Node(String Line, String name){
        this.line = Line;
        this.name = name;
    }

    public static void init(){
        Node node1 = new Node("1", "101");
        Node node2 = new Node("1", "102");
        Node node3 = new Node("1", "103");
        Node node4 = new Node("1", "104");
        Node node5 = new Node("1", "105");
        Node node6 = new Node("1", "106");
        Node node7 = new Node("1", "107");
        Node node8 = new Node("1", "108");
        Node node9 = new Node("1", "109");
        Node node10 = new Node("1", "110");
        Node node11 = new Node("1", "111");
        Node node12 = new Node("1", "112");
        Node node13 = new Node("1", "113");
        Node node14 = new Node("1", "114");
        Node node15 = new Node("1", "115");
        Node node16 = new Node("1", "116");
        Node node17 = new Node("1", "117");
        Node node18 = new Node("1", "118");
        Node node19 = new Node("1", "119");
        Node node20 = new Node("1", "120");
        Node node21 = new Node("1", "121");
        Node node22 = new Node("1", "122");
        Node node23 = new Node("1", "123");

        Node node24 = new Node("2", "201");
        Node node25 = new Node("2", "202");
        Node node26 = new Node("2", "203");
        Node node27 = new Node("2", "204");
        Node node28 = new Node("2", "205");
        Node node29 = new Node("2", "206");
        Node node30 = new Node("2", "207");
        Node node31 = new Node("2", "208");
        Node node32 = new Node("2", "209");
        Node node33 = new Node("2", "210");
        Node node34 = new Node("2", "211");
        Node node35 = new Node("2", "212");
        Node node36 = new Node("2", "213");
        Node node37 = new Node("2", "214");
        Node node38 = new Node("2", "215");
        Node node39 = new Node("2", "216");
        Node node40 = new Node("2", "217");
        Node node41 = new Node("2", "101");

        Node node42 = new Node("3", "301");
        Node node43 = new Node("3", "302");
        Node node44 = new Node("3", "303");
        Node node45 = new Node("3", "304");
        Node node46 = new Node("3", "305");
        Node node47 = new Node("3", "306");
        Node node48 = new Node("3", "307");
        Node node49 = new Node("3", "308");
        Node node50 = new Node("3", "207");
        Node node51 = new Node("3", "123");
        Node node52 = new Node("3", "107");

        Node node53 = new Node("4", "401");
        Node node54 = new Node("4", "402");
        Node node55 = new Node("4", "403");
        Node node56 = new Node("4", "404");
        Node node57 = new Node("4", "405");
        Node node58 = new Node("4", "406");
        Node node59 = new Node("4", "407");
        Node node60 = new Node("4", "408");
        Node node61 = new Node("4", "409");
        Node node62 = new Node("4", "410");
        Node node63 = new Node("4", "411");
        Node node64 = new Node("4", "412");
        Node node65 = new Node("4", "413");
        Node node66 = new Node("4", "414");
        Node node67 = new Node("4", "415");
        Node node68 = new Node("4", "416");
        Node node69 = new Node("4", "417");
        Node node70 = new Node("4", "104");
        Node node71 = new Node("4", "307");
        Node node72 = new Node("4", "115");
        Node node73 = new Node("4", "216");

        Node node74 = new Node("5", "501");
        Node node75 = new Node("5", "502");
        Node node76 = new Node("5", "503");
        Node node77 = new Node("5", "504");
        Node node78 = new Node("5", "505");
        Node node79 = new Node("5", "506");
        Node node80 = new Node("5", "507");
        Node node81 = new Node("5", "209");
        Node node82 = new Node("5", "122");
        Node node83 = new Node("5", "403");
        Node node84 = new Node("5", "109");

        Node node85 = new Node("6", "601");
        Node node86 = new Node("6", "602");
        Node node87 = new Node("6", "603");
        Node node88 = new Node("6", "604");
        Node node89 = new Node("6", "605");
        Node node90 = new Node("6", "606");
        Node node91 = new Node("6", "607");
        Node node92 = new Node("6", "608");
        Node node93 = new Node("6", "609");
        Node node94 = new Node("6", "610");
        Node node95 = new Node("6", "611");
        Node node96 = new Node("6", "612");
        Node node97 = new Node("6", "613");
        Node node98 = new Node("6", "614");
        Node node99 = new Node("6", "615");
        Node node100 = new Node("6", "616");
        Node node101 = new Node("6", "617");
        Node node102 = new Node("6", "618");
        Node node103 = new Node("6", "619");
        Node node104 = new Node("6", "620");
        Node node105 = new Node("6", "621");
        Node node106 = new Node("6", "622");
        Node node107 = new Node("6", "121");
        Node node108 = new Node("6", "116");
        Node node109 = new Node("6", "412");
        Node node110 = new Node("6", "417");

        Node node111 = new Node("7", "701");
        Node node112 = new Node("7", "702");
        Node node113 = new Node("7", "703");
        Node node114 = new Node("7", "704");
        Node node115 = new Node("7", "705");
        Node node116 = new Node("7", "706");
        Node node117 = new Node("7", "707");
        Node node118 = new Node("7", "202");
        Node node119 = new Node("7", "303");
        Node node120 = new Node("7", "503");
        Node node121 = new Node("7", "601");
        Node node122 = new Node("7", "416");
        Node node123 = new Node("7", "614");

        Node node124 = new Node("8", "801");
        Node node125 = new Node("8", "802");
        Node node126 = new Node("8", "803");
        Node node127 = new Node("8", "804");
        Node node128 = new Node("8", "805");
        Node node129 = new Node("8", "806");
        Node node130 = new Node("8", "409");
        Node node131 = new Node("8", "608");
        Node node132 = new Node("8", "705");
        Node node133 = new Node("8", "618");
        Node node134 = new Node("8", "214");
        Node node146 = new Node("8", "113");

        Node node135 = new Node("9", "901");
        Node node136 = new Node("9", "902");
        Node node137 = new Node("9", "903");
        Node node138 = new Node("9", "904");
        Node node139 = new Node("9", "112");
        Node node140 = new Node("9", "406");
        Node node141 = new Node("9", "605");
        Node node142 = new Node("9", "119");
        Node node143 = new Node("9", "702");
        Node node144 = new Node("9", "621");
        Node node145 = new Node("9", "211");

        Node[] allNodes = {
                node1, node2, node3, node4, node5, node6, node7, node8, node9, node10,
                node11, node12, node13, node14, node15, node16, node17, node18, node19, node20,
                node21, node22, node23, node24, node25, node26, node27, node28, node29, node30,
                node31, node32, node33, node34, node35, node36, node37, node38, node39, node40,
                node41, node42, node43, node44, node45, node46, node47, node48, node49, node50,
                node51, node52, node53, node54, node55, node56, node57, node58, node59, node60,
                node61, node62, node63, node64, node65, node66, node67, node68, node69, node70,
                node71, node72, node73, node74, node75, node76, node77, node78, node79, node80,
                node81, node82, node83, node84, node85, node86, node87, node88, node89, node90,
                node91, node92, node93, node94, node95, node96, node97, node98, node99, node100,
                node101, node102, node103, node104, node105, node106, node107, node108, node109, node110,
                node111, node112, node113, node114, node115, node116, node117, node118, node119, node120,
                node121, node122, node123, node124, node125, node126, node127, node128, node129, node130,
                node131, node132, node133, node134, node135, node136, node137, node138, node139, node140,
                node141, node142, node143, node144, node145, node146
        };

        Map<String, Integer> nameCount = new HashMap<>();
        for (Node node : allNodes) {
            nameCount.put(node.getName(), nameCount.getOrDefault(node.getName(), 0) + 1);
        }

        for (Node node : allNodes) {
            if (nameCount.get(node.getName()) == 1) {
                nodes.add(node);
            } else {
                transNodes.add(node);
            }
        }
        // 1호선
        node1.addAdjacentNode(node2, 100);
        node1.addAdjacentNode(node24, 100);
        node1.addAdjacentNode(node23, 100);
        node1.addAdjacentNode(node51, 100);

        node2.addAdjacentNode(node1, 100);
        node2.addAdjacentNode(node3, 100);

        node3.addAdjacentNode(node2, 100);
        node3.addAdjacentNode(node4, 100);
        node3.addAdjacentNode(node70, 100);

        node4.addAdjacentNode(node3, 100);
        node4.addAdjacentNode(node5, 100);
        node4.addAdjacentNode(node53, 100);

        node5.addAdjacentNode(node4, 100);
        node5.addAdjacentNode(node6, 100);

        node6.addAdjacentNode(node5, 100);
        node6.addAdjacentNode(node7, 100);
        node6.addAdjacentNode(node52, 100);

        node7.addAdjacentNode(node6, 100);
        node7.addAdjacentNode(node8, 100);
        node7.addAdjacentNode(node49, 100);

        node8.addAdjacentNode(node7, 100);
        node8.addAdjacentNode(node9, 100);
        node8.addAdjacentNode(node52, 100);
        node8.addAdjacentNode(node84, 100);

        node9.addAdjacentNode(node8, 100);
        node9.addAdjacentNode(node10, 100);
        node9.addAdjacentNode(node80, 100);

        node10.addAdjacentNode(node9, 100);
        node10.addAdjacentNode(node11, 100);

        node11.addAdjacentNode(node10, 100);
        node11.addAdjacentNode(node12, 100);
        node11.addAdjacentNode(node139, 100);

        node12.addAdjacentNode(node11, 100);
        node12.addAdjacentNode(node13, 100);
        node12.addAdjacentNode(node146, 100);

        node13.addAdjacentNode(node12, 100);
        node13.addAdjacentNode(node14, 100);
        node13.addAdjacentNode(node124, 100);

        node14.addAdjacentNode(node13, 100);
        node14.addAdjacentNode(node15, 100);
        node14.addAdjacentNode(node146, 100);
        node14.addAdjacentNode(node72, 100);

        node15.addAdjacentNode(node14, 100);
        node15.addAdjacentNode(node16, 100);
        node15.addAdjacentNode(node59, 100);
        node15.addAdjacentNode(node60, 100);
        node15.addAdjacentNode(node108, 100);

        node16.addAdjacentNode(node15, 100);
        node16.addAdjacentNode(node17, 100);
        node16.addAdjacentNode(node90, 100);
        node16.addAdjacentNode(node91, 100);
        node16.addAdjacentNode(node72, 100);

        node17.addAdjacentNode(node16, 100);
        node17.addAdjacentNode(node18, 100);
        node17.addAdjacentNode(node108, 100);

        node18.addAdjacentNode(node17, 100);
        node18.addAdjacentNode(node19, 100);
        node18.addAdjacentNode(node142, 100);

        node19.addAdjacentNode(node18, 100);
        node19.addAdjacentNode(node20, 100);
        node19.addAdjacentNode(node136, 100);
        node19.addAdjacentNode(node137, 100);

        node20.addAdjacentNode(node19, 100);
        node20.addAdjacentNode(node21, 100);
        node20.addAdjacentNode(node142, 100);
        node20.addAdjacentNode(node107, 100);

        node21.addAdjacentNode(node20, 100);
        node21.addAdjacentNode(node22, 100);
        node21.addAdjacentNode(node86, 100);
        node21.addAdjacentNode(node87, 100);
        node21.addAdjacentNode(node82, 100);

        node22.addAdjacentNode(node21, 100);
        node22.addAdjacentNode(node23, 100);
        node22.addAdjacentNode(node77, 100);
        node22.addAdjacentNode(node78, 100);
        node22.addAdjacentNode(node107, 100);
        node22.addAdjacentNode(node51, 100);

        node23.addAdjacentNode(node22, 100);
        node23.addAdjacentNode(node1, 100);
        node23.addAdjacentNode(node45, 100);
        node23.addAdjacentNode(node46, 100);
        node23.addAdjacentNode(node41, 100);
        node23.addAdjacentNode(node82, 100);

        // 2호선
        node24.addAdjacentNode(node1, 100);
        node24.addAdjacentNode(node25, 100);
        node24.addAdjacentNode(node118, 100);
        node24.addAdjacentNode(node41, 100);

        node25.addAdjacentNode(node24, 100);
        node25.addAdjacentNode(node26, 100);
        node25.addAdjacentNode(node44, 100);
        node25.addAdjacentNode(node119, 100);

        node26.addAdjacentNode(node25, 100);
        node26.addAdjacentNode(node27, 100);
        node26.addAdjacentNode(node118, 100);

        node27.addAdjacentNode(node26, 100);
        node27.addAdjacentNode(node28, 100);

        node28.addAdjacentNode(node27, 100);
        node28.addAdjacentNode(node29, 100);

        node29.addAdjacentNode(node28, 100);
        node29.addAdjacentNode(node30, 100);
        node29.addAdjacentNode(node50, 100);

        node30.addAdjacentNode(node29, 100);
        node30.addAdjacentNode(node31, 100);
        node30.addAdjacentNode(node42, 100);

        node31.addAdjacentNode(node30, 100);
        node31.addAdjacentNode(node32, 100);
        node31.addAdjacentNode(node50, 100);
        node31.addAdjacentNode(node81, 100);

        node32.addAdjacentNode(node31, 100);
        node32.addAdjacentNode(node33, 100);
        node32.addAdjacentNode(node74, 100);

        node33.addAdjacentNode(node32, 100);
        node33.addAdjacentNode(node34, 100);
        node33.addAdjacentNode(node81, 100);
        node33.addAdjacentNode(node145, 100);

        node34.addAdjacentNode(node33, 100);
        node34.addAdjacentNode(node35, 100);
        node34.addAdjacentNode(node105, 100);
        node34.addAdjacentNode(node144, 100);

        node35.addAdjacentNode(node34, 100);
        node35.addAdjacentNode(node36, 100);
        node35.addAdjacentNode(node145, 100);

        node36.addAdjacentNode(node35, 100);
        node36.addAdjacentNode(node37, 100);
        node36.addAdjacentNode(node134, 100);

        node37.addAdjacentNode(node36, 100);
        node37.addAdjacentNode(node38, 100);
        node37.addAdjacentNode(node102, 100);
        node37.addAdjacentNode(node133, 100);

        node38.addAdjacentNode(node37, 100);
        node38.addAdjacentNode(node39, 100);
        node38.addAdjacentNode(node134, 100);
        node38.addAdjacentNode(node73, 100);

        node39.addAdjacentNode(node38, 100);
        node39.addAdjacentNode(node40, 100);
        node39.addAdjacentNode(node69, 100);
        node39.addAdjacentNode(node110, 100);

        node40.addAdjacentNode(node39, 100);
        node40.addAdjacentNode(node73, 100);

        node41.addAdjacentNode(node2, 100);
        node41.addAdjacentNode(node24, 100);
        node41.addAdjacentNode(node23, 100);
        node41.addAdjacentNode(node51, 100);

        // 3호선
        node42.addAdjacentNode(node43, 100);
        node42.addAdjacentNode(node30, 100);
        node42.addAdjacentNode(node50, 100);

        node43.addAdjacentNode(node42, 100);
        node43.addAdjacentNode(node44, 100);
        node43.addAdjacentNode(node119, 100);

        node44.addAdjacentNode(node43, 100);
        node44.addAdjacentNode(node45, 100);
        node44.addAdjacentNode(node25, 100);
        node44.addAdjacentNode(node118, 100);
        node44.addAdjacentNode(node76, 100);
        node44.addAdjacentNode(node120, 100);

        node45.addAdjacentNode(node44, 100);
        node45.addAdjacentNode(node119, 100);
        node45.addAdjacentNode(node51, 100);
        node45.addAdjacentNode(node23, 100);

        node46.addAdjacentNode(node23, 100);
        node46.addAdjacentNode(node51, 100);
        node46.addAdjacentNode(node47, 100);

        node47.addAdjacentNode(node46, 100);
        node47.addAdjacentNode(node71, 100);
        node47.addAdjacentNode(node48, 100);

        node48.addAdjacentNode(node47, 100);
        node48.addAdjacentNode(node49, 100);
        node48.addAdjacentNode(node54, 100);
        node48.addAdjacentNode(node53, 100);

        node49.addAdjacentNode(node48, 100);
        node49.addAdjacentNode(node52, 100);
        node49.addAdjacentNode(node71, 100);
        node49.addAdjacentNode(node7, 100);

        node50.addAdjacentNode(node29, 100);
        node50.addAdjacentNode(node31, 100);
        node50.addAdjacentNode(node42, 100);

        node51.addAdjacentNode(node45, 100);
        node51.addAdjacentNode(node46, 100);
        node51.addAdjacentNode(node1, 100);
        node51.addAdjacentNode(node41, 100);
        node51.addAdjacentNode(node22, 100);
        node51.addAdjacentNode(node82, 100);

        node52.addAdjacentNode(node6, 100);
        node52.addAdjacentNode(node8, 100);
        node52.addAdjacentNode(node49, 100);

        // 4호선
        node53.addAdjacentNode(node4, 100);
        node53.addAdjacentNode(node70, 100);
        node53.addAdjacentNode(node71, 100);
        node53.addAdjacentNode(node48, 100);

        node54.addAdjacentNode(node48, 100);
        node54.addAdjacentNode(node71, 100);
        node54.addAdjacentNode(node55, 100);
        node54.addAdjacentNode(node83, 100);

        node55.addAdjacentNode(node54, 100);
        node55.addAdjacentNode(node56, 100);

        node56.addAdjacentNode(node55, 100);
        node56.addAdjacentNode(node57, 100);
        node56.addAdjacentNode(node83, 100);

        node57.addAdjacentNode(node56, 100);
        node57.addAdjacentNode(node58, 100);
        node57.addAdjacentNode(node140, 100);

        node58.addAdjacentNode(node57, 100);
        node58.addAdjacentNode(node59, 100);
        node58.addAdjacentNode(node135, 100);
        node58.addAdjacentNode(node141, 100);
        node58.addAdjacentNode(node89, 100);

        node59.addAdjacentNode(node58, 100);
        node59.addAdjacentNode(node140, 100);
        node59.addAdjacentNode(node15, 100);
        node59.addAdjacentNode(node72, 100);

        node60.addAdjacentNode(node72, 100);
        node60.addAdjacentNode(node15, 100);
        node60.addAdjacentNode(node61, 100);
        node60.addAdjacentNode(node130, 100);

        node61.addAdjacentNode(node60, 100);
        node61.addAdjacentNode(node62, 100);

        node62.addAdjacentNode(node61, 100);
        node62.addAdjacentNode(node63, 100);
        node62.addAdjacentNode(node130, 100);

        node63.addAdjacentNode(node62, 100);
        node63.addAdjacentNode(node64, 100);

        node64.addAdjacentNode(node63, 100);
        node64.addAdjacentNode(node65, 100);
        node64.addAdjacentNode(node93, 100);
        node64.addAdjacentNode(node94, 100);

        node65.addAdjacentNode(node64, 100);
        node65.addAdjacentNode(node66, 100);
        node65.addAdjacentNode(node109, 100);

        node66.addAdjacentNode(node65, 100);
        node66.addAdjacentNode(node67, 100);

        node67.addAdjacentNode(node66, 100);
        node67.addAdjacentNode(node68, 100);
        node67.addAdjacentNode(node122, 100);

        node68.addAdjacentNode(node67, 100);
        node68.addAdjacentNode(node69, 100);
        node68.addAdjacentNode(node110, 100);
        node68.addAdjacentNode(node116, 100);
        node68.addAdjacentNode(node117, 100);

        node69.addAdjacentNode(node68, 100);
        node69.addAdjacentNode(node122, 100);
        node69.addAdjacentNode(node101, 100);
        node69.addAdjacentNode(node100, 100);
        node69.addAdjacentNode(node73, 100);
        node69.addAdjacentNode(node39, 100);

        node70.addAdjacentNode(node3, 100);
        node70.addAdjacentNode(node5, 100);
        node70.addAdjacentNode(node53, 100);

        node71.addAdjacentNode(node53, 100);
        node71.addAdjacentNode(node54, 100);
        node71.addAdjacentNode(node47, 100);
        node71.addAdjacentNode(node49, 100);

        node72.addAdjacentNode(node59, 100);
        node72.addAdjacentNode(node60, 100);
        node72.addAdjacentNode(node14, 100);
        node72.addAdjacentNode(node16, 100);
        node72.addAdjacentNode(node108, 100);

        node73.addAdjacentNode(node38, 100);
        node73.addAdjacentNode(node40, 100);
        node73.addAdjacentNode(node69, 100);
        node73.addAdjacentNode(node110, 100);

        // 5호선
        node74.addAdjacentNode(node75, 100);
        node74.addAdjacentNode(node81, 100);
        node74.addAdjacentNode(node32, 100);

        node75.addAdjacentNode(node74, 100);
        node75.addAdjacentNode(node76, 100);
        node75.addAdjacentNode(node120, 100);

        node76.addAdjacentNode(node75, 100);
        node76.addAdjacentNode(node77, 100);
        node76.addAdjacentNode(node119, 100);
        node76.addAdjacentNode(node44, 100);
        node76.addAdjacentNode(node85, 100);
        node76.addAdjacentNode(node111, 100);

        node77.addAdjacentNode(node76, 100);
        node77.addAdjacentNode(node120, 100);
        node77.addAdjacentNode(node82, 100);
        node77.addAdjacentNode(node22, 100);

        node78.addAdjacentNode(node79, 100);
        node78.addAdjacentNode(node22, 100);
        node78.addAdjacentNode(node82, 100);

        node79.addAdjacentNode(node78, 100);
        node79.addAdjacentNode(node55, 100);
        node79.addAdjacentNode(node83, 100);

        node80.addAdjacentNode(node83, 100);
        node80.addAdjacentNode(node55, 100);
        node80.addAdjacentNode(node9, 100);
        node80.addAdjacentNode(node84, 100);

        node81.addAdjacentNode(node31, 100);
        node81.addAdjacentNode(node33, 100);
        node81.addAdjacentNode(node74, 100);

        node82.addAdjacentNode(node77, 100);
        node82.addAdjacentNode(node78, 100);
        node82.addAdjacentNode(node21, 100);
        node82.addAdjacentNode(node23, 100);
        node82.addAdjacentNode(node51, 100);
        node82.addAdjacentNode(node107, 100);

        node83.addAdjacentNode(node54, 100);
        node83.addAdjacentNode(node56, 100);
        node83.addAdjacentNode(node79, 100);
        node83.addAdjacentNode(node80, 100);

        node84.addAdjacentNode(node8, 100);
        node84.addAdjacentNode(node10, 100);
        node84.addAdjacentNode(node80, 100);

        // 6호선
        node85.addAdjacentNode(node86, 100);
        node85.addAdjacentNode(node106, 100);
        node85.addAdjacentNode(node76, 100);
        node85.addAdjacentNode(node120, 100);
        node85.addAdjacentNode(node111, 100);

        node86.addAdjacentNode(node85, 100);
        node86.addAdjacentNode(node121, 100);
        node86.addAdjacentNode(node107, 100);
        node86.addAdjacentNode(node21, 100);

        node87.addAdjacentNode(node88, 100);
        node87.addAdjacentNode(node107, 100);
        node87.addAdjacentNode(node21, 100);

        node88.addAdjacentNode(node87, 100);
        node88.addAdjacentNode(node89, 100);
        node88.addAdjacentNode(node141, 100);

        node89.addAdjacentNode(node88, 100);
        node89.addAdjacentNode(node90, 100);
        node89.addAdjacentNode(node136, 100);
        node89.addAdjacentNode(node140, 100);
        node89.addAdjacentNode(node58, 100);

        node90.addAdjacentNode(node89, 100);
        node90.addAdjacentNode(node141, 100);
        node90.addAdjacentNode(node108, 100);
        node90.addAdjacentNode(node16, 100);

        node91.addAdjacentNode(node16, 100);
        node91.addAdjacentNode(node108, 100);
        node91.addAdjacentNode(node92, 100);
        node91.addAdjacentNode(node131, 100);

        node92.addAdjacentNode(node91, 100);
        node92.addAdjacentNode(node93, 100);
        node92.addAdjacentNode(node127, 100);
        node92.addAdjacentNode(node130, 100);
        node92.addAdjacentNode(node61, 100);

        node93.addAdjacentNode(node92, 100);
        node93.addAdjacentNode(node131, 100);
        node93.addAdjacentNode(node109, 100);
        node93.addAdjacentNode(node64, 100);

        node94.addAdjacentNode(node64, 100);
        node94.addAdjacentNode(node109, 100);
        node94.addAdjacentNode(node95, 100);

        node95.addAdjacentNode(node94, 100);
        node95.addAdjacentNode(node96, 100);

        node96.addAdjacentNode(node95, 100);
        node96.addAdjacentNode(node97, 100);

        node97.addAdjacentNode(node96, 100);
        node97.addAdjacentNode(node98, 100);
        node97.addAdjacentNode(node123, 100);

        node98.addAdjacentNode(node97, 100);
        node98.addAdjacentNode(node99, 100);
        node98.addAdjacentNode(node117, 100);

        node99.addAdjacentNode(node98, 100);
        node99.addAdjacentNode(node100, 100);
        node99.addAdjacentNode(node123, 100);

        node100.addAdjacentNode(node99, 100);
        node100.addAdjacentNode(node110, 100);
        node100.addAdjacentNode(node69, 100);

        node101.addAdjacentNode(node69, 100);
        node101.addAdjacentNode(node133, 100);
        node101.addAdjacentNode(node102, 100);
        node101.addAdjacentNode(node110, 100);

        node102.addAdjacentNode(node37, 100);
        node102.addAdjacentNode(node101, 100);
        node102.addAdjacentNode(node103, 100);
        node102.addAdjacentNode(node115, 100);
        node102.addAdjacentNode(node132, 100);
        node102.addAdjacentNode(node134, 100);

        node103.addAdjacentNode(node133, 100);
        node103.addAdjacentNode(node102, 100);
        node103.addAdjacentNode(node104, 100);

        node104.addAdjacentNode(node144, 100);
        node104.addAdjacentNode(node103, 100);
        node104.addAdjacentNode(node105, 100);

        node105.addAdjacentNode(node138, 100);
        node105.addAdjacentNode(node145, 100);
        node105.addAdjacentNode(node34, 100);
        node105.addAdjacentNode(node104, 100);
        node105.addAdjacentNode(node106, 100);

        node106.addAdjacentNode(node144, 100);
        node106.addAdjacentNode(node121, 100);
        node106.addAdjacentNode(node85, 100);
        node106.addAdjacentNode(node105, 100);

        node107.addAdjacentNode(node20, 100);
        node107.addAdjacentNode(node22, 100);
        node107.addAdjacentNode(node86, 100);
        node107.addAdjacentNode(node87, 100);
        node107.addAdjacentNode(node82, 100);

        node108.addAdjacentNode(node15, 100);
        node108.addAdjacentNode(node17, 100);
        node108.addAdjacentNode(node90, 100);
        node108.addAdjacentNode(node91, 100);
        node108.addAdjacentNode(node72, 100);

        node109.addAdjacentNode(node63, 100);
        node109.addAdjacentNode(node65, 100);
        node109.addAdjacentNode(node93, 100);
        node109.addAdjacentNode(node94, 100);

        node110.addAdjacentNode(node68, 100);
        node110.addAdjacentNode(node122, 100);
        node110.addAdjacentNode(node101, 100);
        node110.addAdjacentNode(node100, 100);
        node110.addAdjacentNode(node73, 100);
        node110.addAdjacentNode(node39, 100);


        // 7호선
        node111.addAdjacentNode(node85, 100);
        node111.addAdjacentNode(node143, 100);
        node111.addAdjacentNode(node112, 100);
        node111.addAdjacentNode(node121, 100);

        node112.addAdjacentNode(node137, 100);
        node112.addAdjacentNode(node138, 100);
        node112.addAdjacentNode(node111, 100);
        node112.addAdjacentNode(node113, 100);

        node113.addAdjacentNode(node143, 100);
        node113.addAdjacentNode(node112, 100);
        node113.addAdjacentNode(node114, 100);

        node114.addAdjacentNode(node132, 100);
        node114.addAdjacentNode(node113, 100);
        node114.addAdjacentNode(node115, 100);

        node115.addAdjacentNode(node102, 100);
        node115.addAdjacentNode(node129, 100);
        node115.addAdjacentNode(node133, 100);
        node115.addAdjacentNode(node114, 100);
        node115.addAdjacentNode(node116, 100);

        node116.addAdjacentNode(node68, 100);
        node116.addAdjacentNode(node132, 100);
        node116.addAdjacentNode(node115, 100);
        node116.addAdjacentNode(node122, 100);

        node117.addAdjacentNode(node68, 100);
        node117.addAdjacentNode(node98, 100);
        node117.addAdjacentNode(node122, 100);
        node117.addAdjacentNode(node123, 100);

        node118.addAdjacentNode(node24, 100);
        node118.addAdjacentNode(node26, 100);
        node118.addAdjacentNode(node44, 100);
        node118.addAdjacentNode(node119, 100);

        node119.addAdjacentNode(node25, 100);
        node119.addAdjacentNode(node43, 100);
        node119.addAdjacentNode(node45, 100);
        node119.addAdjacentNode(node76, 100);
        node119.addAdjacentNode(node118, 100);
        node119.addAdjacentNode(node120, 100);

        node120.addAdjacentNode(node44, 100);
        node120.addAdjacentNode(node75, 100);
        node120.addAdjacentNode(node77, 100);
        node120.addAdjacentNode(node85, 100);
        node120.addAdjacentNode(node119, 100);
        node120.addAdjacentNode(node121, 100);

        node121.addAdjacentNode(node76, 100);
        node121.addAdjacentNode(node86, 100);
        node121.addAdjacentNode(node106, 100);
        node121.addAdjacentNode(node111, 100);
        node121.addAdjacentNode(node120, 100);

        node122.addAdjacentNode(node67, 100);
        node122.addAdjacentNode(node69, 100);
        node122.addAdjacentNode(node110, 100);
        node122.addAdjacentNode(node116, 100);
        node122.addAdjacentNode(node117, 100);

        node123.addAdjacentNode(node97, 100);
        node123.addAdjacentNode(node99, 100);
        node123.addAdjacentNode(node117, 100);


        // 8호선
        node124.addAdjacentNode(node125, 100);
        node124.addAdjacentNode(node146, 100);
        node124.addAdjacentNode(node13, 100);

        node125.addAdjacentNode(node124, 100);
        node125.addAdjacentNode(node126, 100);

        node126.addAdjacentNode(node125, 100);
        node126.addAdjacentNode(node130, 100);
        node126.addAdjacentNode(node61, 100);

        node127.addAdjacentNode(node128, 100);
        node127.addAdjacentNode(node131, 100);
        node127.addAdjacentNode(node92, 100);

        node128.addAdjacentNode(node127, 100);
        node128.addAdjacentNode(node129, 100);

        node129.addAdjacentNode(node128, 100);
        node129.addAdjacentNode(node132, 100);
        node129.addAdjacentNode(node115, 100);

        node130.addAdjacentNode(node60, 100);
        node130.addAdjacentNode(node62, 100);
        node130.addAdjacentNode(node92, 100);
        node130.addAdjacentNode(node126, 100);
        node130.addAdjacentNode(node131, 100);

        node131.addAdjacentNode(node61, 100);
        node131.addAdjacentNode(node91, 100);
        node131.addAdjacentNode(node93, 100);
        node131.addAdjacentNode(node127, 100);
        node131.addAdjacentNode(node130, 100);

        node132.addAdjacentNode(node102, 100);
        node132.addAdjacentNode(node114, 100);
        node132.addAdjacentNode(node116, 100);
        node132.addAdjacentNode(node129, 100);
        node132.addAdjacentNode(node133, 100);

        node133.addAdjacentNode(node37, 100);
        node133.addAdjacentNode(node101, 100);
        node133.addAdjacentNode(node103, 100);
        node133.addAdjacentNode(node115, 100);
        node133.addAdjacentNode(node132, 100);
        node133.addAdjacentNode(node134, 100);

        node134.addAdjacentNode(node36, 100);
        node134.addAdjacentNode(node38, 100);
        node134.addAdjacentNode(node102, 100);
        node134.addAdjacentNode(node133, 100);

        node146.addAdjacentNode(node12, 100);
        node146.addAdjacentNode(node14, 100);
        node146.addAdjacentNode(node139, 100);
        node146.addAdjacentNode(node124, 100);


        // 9호선
        node135.addAdjacentNode(node12, 100);
        node135.addAdjacentNode(node58, 100);
        node135.addAdjacentNode(node139, 100);
        node135.addAdjacentNode(node140, 100);

        node136.addAdjacentNode(node19, 100);
        node136.addAdjacentNode(node89, 100);
        node136.addAdjacentNode(node141, 100);
        node136.addAdjacentNode(node142, 100);

        node137.addAdjacentNode(node19, 100);
        node137.addAdjacentNode(node112, 100);
        node137.addAdjacentNode(node142, 100);
        node137.addAdjacentNode(node143, 100);

        node138.addAdjacentNode(node105, 100);
        node138.addAdjacentNode(node112, 100);
        node138.addAdjacentNode(node143, 100);
        node138.addAdjacentNode(node144, 100);

        node139.addAdjacentNode(node135, 100);
        node139.addAdjacentNode(node11, 100);
        node139.addAdjacentNode(node13, 100);
        node139.addAdjacentNode(node146, 100);

        node140.addAdjacentNode(node135, 100);
        node140.addAdjacentNode(node58, 100);
        node140.addAdjacentNode(node141, 100);
        node140.addAdjacentNode(node57, 100);
        node140.addAdjacentNode(node59, 100);

        node141.addAdjacentNode(node136, 100);
        node141.addAdjacentNode(node140, 100);
        node141.addAdjacentNode(node58, 100);
        node141.addAdjacentNode(node88, 100);
        node141.addAdjacentNode(node90, 100);

        node142.addAdjacentNode(node136, 100);
        node142.addAdjacentNode(node137, 100);
        node142.addAdjacentNode(node18, 100);
        node142.addAdjacentNode(node19, 100);

        node143.addAdjacentNode(node137, 100);
        node143.addAdjacentNode(node138, 100);
        node143.addAdjacentNode(node111, 100);
        node143.addAdjacentNode(node113, 100);

        node144.addAdjacentNode(node138, 100);
        node144.addAdjacentNode(node145, 100);
        node144.addAdjacentNode(node34, 100);
        node144.addAdjacentNode(node104, 100);
        node144.addAdjacentNode(node106, 100);

        node145.addAdjacentNode(node144, 100);
        node145.addAdjacentNode(node33, 100);
        node145.addAdjacentNode(node35, 100);


    }

    public static void initT(){
        Node node1 = new Node("1", "101");
        Node node2 = new Node("1", "102");
        Node node3 = new Node("1", "103");
        Node node4 = new Node("1", "104");
        Node node5 = new Node("1", "105");
        Node node6 = new Node("1", "106");
        Node node7 = new Node("1", "107");
        Node node8 = new Node("1", "108");
        Node node9 = new Node("1", "109");
        Node node10 = new Node("1", "110");
        Node node11 = new Node("1", "111");
        Node node12 = new Node("1", "112");
        Node node13 = new Node("1", "113");
        Node node14 = new Node("1", "114");
        Node node15 = new Node("1", "115");
        Node node16 = new Node("1", "116");
        Node node17 = new Node("1", "117");
        Node node18 = new Node("1", "118");
        Node node19 = new Node("1", "119");
        Node node20 = new Node("1", "120");
        Node node21 = new Node("1", "121");
        Node node22 = new Node("1", "122");
        Node node23 = new Node("1", "123");

        Node node24 = new Node("2", "201");
        Node node25 = new Node("2", "202");
        Node node26 = new Node("2", "203");
        Node node27 = new Node("2", "204");
        Node node28 = new Node("2", "205");
        Node node29 = new Node("2", "206");
        Node node30 = new Node("2", "207");
        Node node31 = new Node("2", "208");
        Node node32 = new Node("2", "209");
        Node node33 = new Node("2", "210");
        Node node34 = new Node("2", "211");
        Node node35 = new Node("2", "212");
        Node node36 = new Node("2", "213");
        Node node37 = new Node("2", "214");
        Node node38 = new Node("2", "215");
        Node node39 = new Node("2", "216");
        Node node40 = new Node("2", "217");
        Node node41 = new Node("2", "101");

        Node node42 = new Node("3", "301");
        Node node43 = new Node("3", "302");
        Node node44 = new Node("3", "303");
        Node node45 = new Node("3", "304");
        Node node46 = new Node("3", "305");
        Node node47 = new Node("3", "306");
        Node node48 = new Node("3", "307");
        Node node49 = new Node("3", "308");
        Node node50 = new Node("3", "207");
        Node node51 = new Node("3", "123");
        Node node52 = new Node("3", "107");

        Node node53 = new Node("4", "401");
        Node node54 = new Node("4", "402");
        Node node55 = new Node("4", "403");
        Node node56 = new Node("4", "404");
        Node node57 = new Node("4", "405");
        Node node58 = new Node("4", "406");
        Node node59 = new Node("4", "407");
        Node node60 = new Node("4", "408");
        Node node61 = new Node("4", "409");
        Node node62 = new Node("4", "410");
        Node node63 = new Node("4", "411");
        Node node64 = new Node("4", "412");
        Node node65 = new Node("4", "413");
        Node node66 = new Node("4", "414");
        Node node67 = new Node("4", "415");
        Node node68 = new Node("4", "416");
        Node node69 = new Node("4", "417");
        Node node70 = new Node("4", "104");
        Node node71 = new Node("4", "307");
        Node node72 = new Node("4", "115");
        Node node73 = new Node("4", "216");

        Node node74 = new Node("5", "501");
        Node node75 = new Node("5", "502");
        Node node76 = new Node("5", "503");
        Node node77 = new Node("5", "504");
        Node node78 = new Node("5", "505");
        Node node79 = new Node("5", "506");
        Node node80 = new Node("5", "507");
        Node node81 = new Node("5", "209");
        Node node82 = new Node("5", "122");
        Node node83 = new Node("5", "403");
        Node node84 = new Node("5", "109");

        Node node85 = new Node("6", "601");
        Node node86 = new Node("6", "602");
        Node node87 = new Node("6", "603");
        Node node88 = new Node("6", "604");
        Node node89 = new Node("6", "605");
        Node node90 = new Node("6", "606");
        Node node91 = new Node("6", "607");
        Node node92 = new Node("6", "608");
        Node node93 = new Node("6", "609");
        Node node94 = new Node("6", "610");
        Node node95 = new Node("6", "611");
        Node node96 = new Node("6", "612");
        Node node97 = new Node("6", "613");
        Node node98 = new Node("6", "614");
        Node node99 = new Node("6", "615");
        Node node100 = new Node("6", "616");
        Node node101 = new Node("6", "617");
        Node node102 = new Node("6", "618");
        Node node103 = new Node("6", "619");
        Node node104 = new Node("6", "620");
        Node node105 = new Node("6", "621");
        Node node106 = new Node("6", "622");
        Node node107 = new Node("6", "121");
        Node node108 = new Node("6", "116");
        Node node109 = new Node("6", "412");
        Node node110 = new Node("6", "417");

        Node node111 = new Node("7", "701");
        Node node112 = new Node("7", "702");
        Node node113 = new Node("7", "703");
        Node node114 = new Node("7", "704");
        Node node115 = new Node("7", "705");
        Node node116 = new Node("7", "706");
        Node node117 = new Node("7", "707");
        Node node118 = new Node("7", "202");
        Node node119 = new Node("7", "303");
        Node node120 = new Node("7", "503");
        Node node121 = new Node("7", "601");
        Node node122 = new Node("7", "416");
        Node node123 = new Node("7", "614");

        Node node124 = new Node("8", "801");
        Node node125 = new Node("8", "802");
        Node node126 = new Node("8", "803");
        Node node127 = new Node("8", "804");
        Node node128 = new Node("8", "805");
        Node node129 = new Node("8", "806");
        Node node130 = new Node("8", "409");
        Node node131 = new Node("8", "608");
        Node node132 = new Node("8", "705");
        Node node133 = new Node("8", "618");
        Node node134 = new Node("8", "214");
        Node node146 = new Node("8", "113");

        Node node135 = new Node("9", "901");
        Node node136 = new Node("9", "902");
        Node node137 = new Node("9", "903");
        Node node138 = new Node("9", "904");
        Node node139 = new Node("9", "112");
        Node node140 = new Node("9", "406");
        Node node141 = new Node("9", "605");
        Node node142 = new Node("9", "119");
        Node node143 = new Node("9", "702");
        Node node144 = new Node("9", "621");
        Node node145 = new Node("9", "211");

        Node[] allNodes = {
                node1, node2, node3, node4, node5, node6, node7, node8, node9, node10,
                node11, node12, node13, node14, node15, node16, node17, node18, node19, node20,
                node21, node22, node23, node24, node25, node26, node27, node28, node29, node30,
                node31, node32, node33, node34, node35, node36, node37, node38, node39, node40,
                node41, node42, node43, node44, node45, node46, node47, node48, node49, node50,
                node51, node52, node53, node54, node55, node56, node57, node58, node59, node60,
                node61, node62, node63, node64, node65, node66, node67, node68, node69, node70,
                node71, node72, node73, node74, node75, node76, node77, node78, node79, node80,
                node81, node82, node83, node84, node85, node86, node87, node88, node89, node90,
                node91, node92, node93, node94, node95, node96, node97, node98, node99, node100,
                node101, node102, node103, node104, node105, node106, node107, node108, node109, node110,
                node111, node112, node113, node114, node115, node116, node117, node118, node119, node120,
                node121, node122, node123, node124, node125, node126, node127, node128, node129, node130,
                node131, node132, node133, node134, node135, node136, node137, node138, node139, node140,
                node141, node142, node143, node144, node145, node146
        };

        Map<String, Integer> nameCount = new HashMap<>();
        for (Node node : allNodes) {
            nameCount.put(node.getName(), nameCount.getOrDefault(node.getName(), 0) + 1);
        }

        for (Node node : allNodes) {
            if (nameCount.get(node.getName()) == 1) {
                nodes.add(node);
            } else {
                transNodes.add(node);
            }
        }
        // 1호선
        node1.addAdjacentNodeT(node2, 100);
        node1.addAdjacentNodeT(node24, 100);
        node1.addAdjacentNodeT(node23, 100);
        node1.addAdjacentNodeT(node51, 100);

        node2.addAdjacentNodeT(node1, 100);
        node2.addAdjacentNodeT(node3, 100);

        node3.addAdjacentNodeT(node2, 100);
        node3.addAdjacentNodeT(node4, 100);
        node3.addAdjacentNodeT(node70, 100);

        node4.addAdjacentNodeT(node3, 100);
        node4.addAdjacentNodeT(node5, 100);
        node4.addAdjacentNodeT(node53, 100);

        node5.addAdjacentNodeT(node4, 100);
        node5.addAdjacentNodeT(node6, 100);

        node6.addAdjacentNodeT(node5, 100);
        node6.addAdjacentNodeT(node7, 100);
        node6.addAdjacentNodeT(node52, 100);

        node7.addAdjacentNodeT(node6, 100);
        node7.addAdjacentNodeT(node8, 100);
        node7.addAdjacentNodeT(node49, 100);

        node8.addAdjacentNodeT(node7, 100);
        node8.addAdjacentNodeT(node9, 100);
        node8.addAdjacentNodeT(node52, 100);
        node8.addAdjacentNodeT(node84, 100);

        node9.addAdjacentNodeT(node8, 100);
        node9.addAdjacentNodeT(node10, 100);
        node9.addAdjacentNodeT(node80, 100);

        node10.addAdjacentNodeT(node9, 100);
        node10.addAdjacentNodeT(node11, 100);

        node11.addAdjacentNodeT(node10, 100);
        node11.addAdjacentNodeT(node12, 100);
        node11.addAdjacentNodeT(node139, 100);

        node12.addAdjacentNodeT(node11, 100);
        node12.addAdjacentNodeT(node13, 100);
        node12.addAdjacentNodeT(node146, 100);

        node13.addAdjacentNodeT(node12, 100);
        node13.addAdjacentNodeT(node14, 100);
        node13.addAdjacentNodeT(node124, 100);

        node14.addAdjacentNodeT(node13, 100);
        node14.addAdjacentNodeT(node15, 100);
        node14.addAdjacentNodeT(node146, 100);
        node14.addAdjacentNodeT(node72, 100);

        node15.addAdjacentNodeT(node14, 100);
        node15.addAdjacentNodeT(node16, 100);
        node15.addAdjacentNodeT(node59, 100);
        node15.addAdjacentNodeT(node60, 100);
        node15.addAdjacentNodeT(node108, 100);

        node16.addAdjacentNodeT(node15, 100);
        node16.addAdjacentNodeT(node17, 100);
        node16.addAdjacentNodeT(node90, 100);
        node16.addAdjacentNodeT(node91, 100);
        node16.addAdjacentNodeT(node72, 100);

        node17.addAdjacentNodeT(node16, 100);
        node17.addAdjacentNodeT(node18, 100);
        node17.addAdjacentNodeT(node108, 100);

        node18.addAdjacentNodeT(node17, 100);
        node18.addAdjacentNodeT(node19, 100);
        node18.addAdjacentNodeT(node142, 100);

        node19.addAdjacentNodeT(node18, 100);
        node19.addAdjacentNodeT(node20, 100);
        node19.addAdjacentNodeT(node136, 100);
        node19.addAdjacentNodeT(node137, 100);

        node20.addAdjacentNodeT(node19, 100);
        node20.addAdjacentNodeT(node21, 100);
        node20.addAdjacentNodeT(node142, 100);
        node20.addAdjacentNodeT(node107, 100);

        node21.addAdjacentNodeT(node20, 100);
        node21.addAdjacentNodeT(node22, 100);
        node21.addAdjacentNodeT(node86, 100);
        node21.addAdjacentNodeT(node87, 100);
        node21.addAdjacentNodeT(node82, 100);

        node22.addAdjacentNodeT(node21, 100);
        node22.addAdjacentNodeT(node23, 100);
        node22.addAdjacentNodeT(node77, 100);
        node22.addAdjacentNodeT(node78, 100);
        node22.addAdjacentNodeT(node107, 100);
        node22.addAdjacentNodeT(node51, 100);

        node23.addAdjacentNodeT(node22, 100);
        node23.addAdjacentNodeT(node1, 100);
        node23.addAdjacentNodeT(node45, 100);
        node23.addAdjacentNodeT(node46, 100);
        node23.addAdjacentNodeT(node41, 100);
        node23.addAdjacentNodeT(node82, 100);

// 2호선
        node24.addAdjacentNodeT(node1, 100);
        node24.addAdjacentNodeT(node25, 100);
        node24.addAdjacentNodeT(node118, 100);
        node24.addAdjacentNodeT(node41, 100);

        node25.addAdjacentNodeT(node24, 100);
        node25.addAdjacentNodeT(node26, 100);
        node25.addAdjacentNodeT(node44, 100);
        node25.addAdjacentNodeT(node119, 100);

        node26.addAdjacentNodeT(node25, 100);
        node26.addAdjacentNodeT(node27, 100);
        node26.addAdjacentNodeT(node118, 100);

        node27.addAdjacentNodeT(node26, 100);
        node27.addAdjacentNodeT(node28, 100);

        node28.addAdjacentNodeT(node27, 100);
        node28.addAdjacentNodeT(node29, 100);

        node29.addAdjacentNodeT(node28, 100);
        node29.addAdjacentNodeT(node30, 100);
        node29.addAdjacentNodeT(node50, 100);

        node30.addAdjacentNodeT(node29, 100);
        node30.addAdjacentNodeT(node31, 100);
        node30.addAdjacentNodeT(node42, 100);

        node31.addAdjacentNodeT(node30, 100);
        node31.addAdjacentNodeT(node32, 100);
        node31.addAdjacentNodeT(node50, 100);
        node31.addAdjacentNodeT(node81, 100);

        node32.addAdjacentNodeT(node31, 100);
        node32.addAdjacentNodeT(node33, 100);
        node32.addAdjacentNodeT(node74, 100);

        node33.addAdjacentNodeT(node32, 100);
        node33.addAdjacentNodeT(node34, 100);
        node33.addAdjacentNodeT(node81, 100);
        node33.addAdjacentNodeT(node145, 100);

        node34.addAdjacentNodeT(node33, 100);
        node34.addAdjacentNodeT(node35, 100);
        node34.addAdjacentNodeT(node105, 100);
        node34.addAdjacentNodeT(node144, 100);

        node35.addAdjacentNodeT(node34, 100);
        node35.addAdjacentNodeT(node36, 100);
        node35.addAdjacentNodeT(node145, 100);

        node36.addAdjacentNodeT(node35, 100);
        node36.addAdjacentNodeT(node37, 100);
        node36.addAdjacentNodeT(node134, 100);

        node37.addAdjacentNodeT(node36, 100);
        node37.addAdjacentNodeT(node38, 100);
        node37.addAdjacentNodeT(node102, 100);
        node37.addAdjacentNodeT(node133, 100);

        node38.addAdjacentNodeT(node37, 100);
        node38.addAdjacentNodeT(node39, 100);
        node38.addAdjacentNodeT(node134, 100);
        node38.addAdjacentNodeT(node73, 100);

        node39.addAdjacentNodeT(node38, 100);
        node39.addAdjacentNodeT(node40, 100);
        node39.addAdjacentNodeT(node69, 100);
        node39.addAdjacentNodeT(node110, 100);

        node40.addAdjacentNodeT(node39, 100);
        node40.addAdjacentNodeT(node73, 100);

        node41.addAdjacentNodeT(node2, 100);
        node41.addAdjacentNodeT(node24, 100);
        node41.addAdjacentNodeT(node23, 100);
        node41.addAdjacentNodeT(node51, 100);

        // 3호선
        node42.addAdjacentNodeT(node43, 100);
        node42.addAdjacentNodeT(node30, 100);
        node42.addAdjacentNodeT(node50, 100);

        node43.addAdjacentNodeT(node42, 100);
        node43.addAdjacentNodeT(node44, 100);
        node43.addAdjacentNodeT(node119, 100);

        node44.addAdjacentNodeT(node43, 100);
        node44.addAdjacentNodeT(node45, 100);
        node44.addAdjacentNodeT(node25, 100);
        node44.addAdjacentNodeT(node118, 100);
        node44.addAdjacentNodeT(node76, 100);
        node44.addAdjacentNodeT(node120, 100);

        node45.addAdjacentNodeT(node44, 100);
        node45.addAdjacentNodeT(node119, 100);
        node45.addAdjacentNodeT(node51, 100);
        node45.addAdjacentNodeT(node23, 100);

        node46.addAdjacentNodeT(node23, 100);
        node46.addAdjacentNodeT(node51, 100);
        node46.addAdjacentNodeT(node47, 100);

        node47.addAdjacentNodeT(node46, 100);
        node47.addAdjacentNodeT(node71, 100);
        node47.addAdjacentNodeT(node48, 100);

        node48.addAdjacentNodeT(node47, 100);
        node48.addAdjacentNodeT(node49, 100);
        node48.addAdjacentNodeT(node54, 100);
        node48.addAdjacentNodeT(node53, 100);

        node49.addAdjacentNodeT(node48, 100);
        node49.addAdjacentNodeT(node52, 100);
        node49.addAdjacentNodeT(node71, 100);
        node49.addAdjacentNodeT(node7, 100);

        node50.addAdjacentNodeT(node29, 100);
        node50.addAdjacentNodeT(node31, 100);
        node50.addAdjacentNodeT(node42, 100);

        node51.addAdjacentNodeT(node45, 100);
        node51.addAdjacentNodeT(node46, 100);
        node51.addAdjacentNodeT(node1, 100);
        node51.addAdjacentNodeT(node41, 100);
        node51.addAdjacentNodeT(node22, 100);
        node51.addAdjacentNodeT(node82, 100);

        node52.addAdjacentNodeT(node6, 100);
        node52.addAdjacentNodeT(node8, 100);
        node52.addAdjacentNodeT(node49, 100);

        // 4호선
        node53.addAdjacentNodeT(node4, 100);
        node53.addAdjacentNodeT(node70, 100);
        node53.addAdjacentNodeT(node71, 100);
        node53.addAdjacentNodeT(node48, 100);

        node54.addAdjacentNodeT(node48, 100);
        node54.addAdjacentNodeT(node71, 100);
        node54.addAdjacentNodeT(node55, 100);
        node54.addAdjacentNodeT(node83, 100);

        node55.addAdjacentNodeT(node54, 100);
        node55.addAdjacentNodeT(node56, 100);

        node56.addAdjacentNodeT(node55, 100);
        node56.addAdjacentNodeT(node57, 100);
        node56.addAdjacentNodeT(node83, 100);

        node57.addAdjacentNodeT(node56, 100);
        node57.addAdjacentNodeT(node58, 100);
        node57.addAdjacentNodeT(node140, 100);

        node58.addAdjacentNodeT(node57, 100);
        node58.addAdjacentNodeT(node59, 100);
        node58.addAdjacentNodeT(node135, 100);
        node58.addAdjacentNodeT(node141, 100);
        node58.addAdjacentNodeT(node89, 100);

        node59.addAdjacentNodeT(node58, 100);
        node59.addAdjacentNodeT(node140, 100);
        node59.addAdjacentNodeT(node15, 100);
        node59.addAdjacentNodeT(node72, 100);

        node60.addAdjacentNodeT(node72, 100);
        node60.addAdjacentNodeT(node15, 100);
        node60.addAdjacentNodeT(node61, 100);
        node60.addAdjacentNodeT(node130, 100);

        node61.addAdjacentNodeT(node60, 100);
        node61.addAdjacentNodeT(node62, 100);

        node62.addAdjacentNodeT(node61, 100);
        node62.addAdjacentNodeT(node63, 100);
        node62.addAdjacentNodeT(node130, 100);

        node63.addAdjacentNodeT(node62, 100);
        node63.addAdjacentNodeT(node64, 100);

        node64.addAdjacentNodeT(node63, 100);
        node64.addAdjacentNodeT(node65, 100);
        node64.addAdjacentNodeT(node93, 100);
        node64.addAdjacentNodeT(node94, 100);

        node65.addAdjacentNodeT(node64, 100);
        node65.addAdjacentNodeT(node66, 100);
        node65.addAdjacentNodeT(node109, 100);

        node66.addAdjacentNodeT(node65, 100);
        node66.addAdjacentNodeT(node67, 100);

        node67.addAdjacentNodeT(node66, 100);
        node67.addAdjacentNodeT(node68, 100);
        node67.addAdjacentNodeT(node122, 100);

        node68.addAdjacentNodeT(node67, 100);
        node68.addAdjacentNodeT(node69, 100);
        node68.addAdjacentNodeT(node110, 100);
        node68.addAdjacentNodeT(node116, 100);
        node68.addAdjacentNodeT(node117, 100);

        node69.addAdjacentNodeT(node68, 100);
        node69.addAdjacentNodeT(node122, 100);
        node69.addAdjacentNodeT(node101, 100);
        node69.addAdjacentNodeT(node100, 100);
        node69.addAdjacentNodeT(node73, 100);
        node69.addAdjacentNodeT(node39, 100);

        node70.addAdjacentNodeT(node3, 100);
        node70.addAdjacentNodeT(node5, 100);
        node70.addAdjacentNodeT(node53, 100);

        node71.addAdjacentNodeT(node53, 100);
        node71.addAdjacentNodeT(node54, 100);
        node71.addAdjacentNodeT(node47, 100);
        node71.addAdjacentNodeT(node49, 100);

        node72.addAdjacentNodeT(node59, 100);
        node72.addAdjacentNodeT(node60, 100);
        node72.addAdjacentNodeT(node14, 100);
        node72.addAdjacentNodeT(node16, 100);
        node72.addAdjacentNodeT(node108, 100);

        node73.addAdjacentNodeT(node38, 100);
        node73.addAdjacentNodeT(node40, 100);
        node73.addAdjacentNodeT(node69, 100);
        node73.addAdjacentNodeT(node110, 100);

// 5호선
        node74.addAdjacentNodeT(node75, 100);
        node74.addAdjacentNodeT(node81, 100);
        node74.addAdjacentNodeT(node32, 100);

        node75.addAdjacentNodeT(node74, 100);
        node75.addAdjacentNodeT(node76, 100);
        node75.addAdjacentNodeT(node120, 100);

        node76.addAdjacentNodeT(node75, 100);
        node76.addAdjacentNodeT(node77, 100);
        node76.addAdjacentNodeT(node119, 100);
        node76.addAdjacentNodeT(node44, 100);
        node76.addAdjacentNodeT(node85, 100);
        node76.addAdjacentNodeT(node111, 100);

        node77.addAdjacentNodeT(node76, 100);
        node77.addAdjacentNodeT(node120, 100);
        node77.addAdjacentNodeT(node82, 100);
        node77.addAdjacentNodeT(node22, 100);

        node78.addAdjacentNodeT(node79, 100);
        node78.addAdjacentNodeT(node22, 100);
        node78.addAdjacentNodeT(node82, 100);

        node79.addAdjacentNodeT(node78, 100);
        node79.addAdjacentNodeT(node55, 100);
        node79.addAdjacentNodeT(node83, 100);

        node80.addAdjacentNodeT(node83, 100);
        node80.addAdjacentNodeT(node55, 100);
        node80.addAdjacentNodeT(node9, 100);
        node80.addAdjacentNodeT(node84, 100);

        node81.addAdjacentNodeT(node31, 100);
        node81.addAdjacentNodeT(node33, 100);
        node81.addAdjacentNodeT(node74, 100);

        node82.addAdjacentNodeT(node77, 100);
        node82.addAdjacentNodeT(node78, 100);
        node82.addAdjacentNodeT(node21, 100);
        node82.addAdjacentNodeT(node23, 100);
        node82.addAdjacentNodeT(node51, 100);
        node82.addAdjacentNodeT(node107, 100);

        node83.addAdjacentNodeT(node54, 100);
        node83.addAdjacentNodeT(node56, 100);
        node83.addAdjacentNodeT(node79, 100);
        node83.addAdjacentNodeT(node80, 100);

        node84.addAdjacentNodeT(node8, 100);
        node84.addAdjacentNodeT(node10, 100);
        node84.addAdjacentNodeT(node80, 100);

// 6호선
        node85.addAdjacentNodeT(node86, 100);
        node85.addAdjacentNodeT(node106, 100);
        node85.addAdjacentNodeT(node76, 100);
        node85.addAdjacentNodeT(node120, 100);
        node85.addAdjacentNodeT(node111, 100);

        node86.addAdjacentNodeT(node85, 100);
        node86.addAdjacentNodeT(node121, 100);
        node86.addAdjacentNodeT(node107, 100);
        node86.addAdjacentNodeT(node21, 100);

        node87.addAdjacentNodeT(node88, 100);
        node87.addAdjacentNodeT(node107, 100);
        node87.addAdjacentNodeT(node21, 100);

        node88.addAdjacentNodeT(node87, 100);
        node88.addAdjacentNodeT(node89, 100);
        node88.addAdjacentNodeT(node141, 100);

        node89.addAdjacentNodeT(node88, 100);
        node89.addAdjacentNodeT(node90, 100);
        node89.addAdjacentNodeT(node136, 100);
        node89.addAdjacentNodeT(node140, 100);
        node89.addAdjacentNodeT(node58, 100);

        node90.addAdjacentNodeT(node89, 100);
        node90.addAdjacentNodeT(node141, 100);
        node90.addAdjacentNodeT(node108, 100);
        node90.addAdjacentNodeT(node16, 100);

        node91.addAdjacentNodeT(node16, 100);
        node91.addAdjacentNodeT(node108, 100);
        node91.addAdjacentNodeT(node92, 100);
        node91.addAdjacentNodeT(node131, 100);

        node92.addAdjacentNodeT(node91, 100);
        node92.addAdjacentNodeT(node93, 100);
        node92.addAdjacentNodeT(node127, 100);
        node92.addAdjacentNodeT(node130, 100);
        node92.addAdjacentNodeT(node61, 100);

        node93.addAdjacentNodeT(node92, 100);
        node93.addAdjacentNodeT(node131, 100);
        node93.addAdjacentNodeT(node109, 100);
        node93.addAdjacentNodeT(node64, 100);

        node94.addAdjacentNodeT(node64, 100);
        node94.addAdjacentNodeT(node109, 100);
        node94.addAdjacentNodeT(node95, 100);

        node95.addAdjacentNodeT(node94, 100);
        node95.addAdjacentNodeT(node96, 100);

        node96.addAdjacentNodeT(node95, 100);
        node96.addAdjacentNodeT(node97, 100);

        node97.addAdjacentNodeT(node96, 100);
        node97.addAdjacentNodeT(node98, 100);
        node97.addAdjacentNodeT(node123, 100);

        node98.addAdjacentNodeT(node97, 100);
        node98.addAdjacentNodeT(node99, 100);
        node98.addAdjacentNodeT(node117, 100);

        node99.addAdjacentNodeT(node98, 100);
        node99.addAdjacentNodeT(node100, 100);
        node99.addAdjacentNodeT(node123, 100);

        node100.addAdjacentNodeT(node99, 100);
        node100.addAdjacentNodeT(node110, 100);
        node100.addAdjacentNodeT(node69, 100);

        node101.addAdjacentNodeT(node69, 100);
        node101.addAdjacentNodeT(node133, 100);
        node101.addAdjacentNodeT(node102, 100);
        node101.addAdjacentNodeT(node110, 100);

        node102.addAdjacentNodeT(node37, 100);
        node102.addAdjacentNodeT(node101, 100);
        node102.addAdjacentNodeT(node103, 100);
        node102.addAdjacentNodeT(node115, 100);
        node102.addAdjacentNodeT(node132, 100);
        node102.addAdjacentNodeT(node134, 100);

        node103.addAdjacentNodeT(node133, 100);
        node103.addAdjacentNodeT(node102, 100);
        node103.addAdjacentNodeT(node104, 100);

        node104.addAdjacentNodeT(node144, 100);
        node104.addAdjacentNodeT(node103, 100);
        node104.addAdjacentNodeT(node105, 100);

        node105.addAdjacentNodeT(node138, 100);
        node105.addAdjacentNodeT(node145, 100);
        node105.addAdjacentNodeT(node34, 100);
        node105.addAdjacentNodeT(node104, 100);
        node105.addAdjacentNodeT(node106, 100);

        node106.addAdjacentNodeT(node144, 100);
        node106.addAdjacentNodeT(node121, 100);
        node106.addAdjacentNodeT(node85, 100);
        node106.addAdjacentNodeT(node105, 100);

        node107.addAdjacentNodeT(node20, 100);
        node107.addAdjacentNodeT(node22, 100);
        node107.addAdjacentNodeT(node86, 100);
        node107.addAdjacentNodeT(node87, 100);
        node107.addAdjacentNodeT(node82, 100);

        node108.addAdjacentNodeT(node15, 100);
        node108.addAdjacentNodeT(node17, 100);
        node108.addAdjacentNodeT(node90, 100);
        node108.addAdjacentNodeT(node91, 100);
        node108.addAdjacentNodeT(node72, 100);

        node109.addAdjacentNodeT(node63, 100);
        node109.addAdjacentNodeT(node65, 100);
        node109.addAdjacentNodeT(node93, 100);
        node109.addAdjacentNodeT(node94, 100);

        node110.addAdjacentNodeT(node68, 100);
        node110.addAdjacentNodeT(node122, 100);
        node110.addAdjacentNodeT(node101, 100);
        node110.addAdjacentNodeT(node100, 100);
        node110.addAdjacentNodeT(node73, 100);
        node110.addAdjacentNodeT(node39, 100);

        // 7호선
        node111.addAdjacentNodeT(node85, 100);
        node111.addAdjacentNodeT(node143, 100);
        node111.addAdjacentNodeT(node112, 100);
        node111.addAdjacentNodeT(node121, 100);

        node112.addAdjacentNodeT(node137, 100);
        node112.addAdjacentNodeT(node138, 100);
        node112.addAdjacentNodeT(node111, 100);
        node112.addAdjacentNodeT(node113, 100);

        node113.addAdjacentNodeT(node143, 100);
        node113.addAdjacentNodeT(node112, 100);
        node113.addAdjacentNodeT(node114, 100);

        node114.addAdjacentNodeT(node132, 100);
        node114.addAdjacentNodeT(node113, 100);
        node114.addAdjacentNodeT(node115, 100);

        node115.addAdjacentNodeT(node102, 100);
        node115.addAdjacentNodeT(node129, 100);
        node115.addAdjacentNodeT(node133, 100);
        node115.addAdjacentNodeT(node114, 100);
        node115.addAdjacentNodeT(node116, 100);

        node116.addAdjacentNodeT(node68, 100);
        node116.addAdjacentNodeT(node132, 100);
        node116.addAdjacentNodeT(node115, 100);
        node116.addAdjacentNodeT(node122, 100);

        node117.addAdjacentNodeT(node68, 100);
        node117.addAdjacentNodeT(node98, 100);
        node117.addAdjacentNodeT(node122, 100);
        node117.addAdjacentNodeT(node123, 100);

        node118.addAdjacentNodeT(node24, 100);
        node118.addAdjacentNodeT(node26, 100);
        node118.addAdjacentNodeT(node44, 100);
        node118.addAdjacentNodeT(node119, 100);

        node119.addAdjacentNodeT(node25, 100);
        node119.addAdjacentNodeT(node43, 100);
        node119.addAdjacentNodeT(node45, 100);
        node119.addAdjacentNodeT(node76, 100);
        node119.addAdjacentNodeT(node118, 100);
        node119.addAdjacentNodeT(node120, 100);

        node121.addAdjacentNodeT(node76, 100);
        node121.addAdjacentNodeT(node86, 100);
        node121.addAdjacentNodeT(node106, 100);
        node121.addAdjacentNodeT(node111, 100);
        node121.addAdjacentNodeT(node120, 100);

        node122.addAdjacentNodeT(node67, 100);
        node122.addAdjacentNodeT(node69, 100);
        node122.addAdjacentNodeT(node110, 100);
        node122.addAdjacentNodeT(node116, 100);
        node122.addAdjacentNodeT(node117, 100);

        node123.addAdjacentNodeT(node97, 100);
        node123.addAdjacentNodeT(node99, 100);
        node123.addAdjacentNodeT(node117, 100);

// 8호선
        node124.addAdjacentNodeT(node125, 100);
        node124.addAdjacentNodeT(node146, 100);
        node124.addAdjacentNodeT(node13, 100);

        node125.addAdjacentNodeT(node124, 100);
        node125.addAdjacentNodeT(node126, 100);

        node126.addAdjacentNodeT(node125, 100);
        node126.addAdjacentNodeT(node130, 100);
        node126.addAdjacentNodeT(node61, 100);

        node127.addAdjacentNodeT(node128, 100);
        node127.addAdjacentNodeT(node131, 100);
        node127.addAdjacentNodeT(node92, 100);

        node128.addAdjacentNodeT(node127, 100);
        node128.addAdjacentNodeT(node129, 100);

        node129.addAdjacentNodeT(node128, 100);
        node129.addAdjacentNodeT(node132, 100);
        node129.addAdjacentNodeT(node115, 100);

        node130.addAdjacentNodeT(node60, 100);
        node130.addAdjacentNodeT(node62, 100);
        node130.addAdjacentNodeT(node92, 100);
        node130.addAdjacentNodeT(node126, 100);
        node130.addAdjacentNodeT(node131, 100);


        node131.addAdjacentNodeT(node61, 100);
        node131.addAdjacentNodeT(node91, 100);
        node131.addAdjacentNodeT(node93, 100);
        node131.addAdjacentNodeT(node127, 100);
        node131.addAdjacentNodeT(node130, 100);

        node132.addAdjacentNodeT(node102, 100);
        node132.addAdjacentNodeT(node114, 100);
        node132.addAdjacentNodeT(node116, 100);
        node132.addAdjacentNodeT(node129, 100);
        node132.addAdjacentNodeT(node133, 100);

        node133.addAdjacentNodeT(node37, 100);
        node133.addAdjacentNodeT(node101, 100);
        node133.addAdjacentNodeT(node103, 100);
        node133.addAdjacentNodeT(node115, 100);
        node133.addAdjacentNodeT(node132, 100);
        node133.addAdjacentNodeT(node134, 100);

        node134.addAdjacentNodeT(node36, 100);
        node134.addAdjacentNodeT(node38, 100);
        node134.addAdjacentNodeT(node102, 100);
        node134.addAdjacentNodeT(node133, 100);

        node146.addAdjacentNodeT(node12, 100);
        node146.addAdjacentNodeT(node14, 100);
        node146.addAdjacentNodeT(node139, 100);
        node146.addAdjacentNodeT(node124, 100);

// 9호선
        node135.addAdjacentNodeT(node12, 100);
        node135.addAdjacentNodeT(node58, 100);
        node135.addAdjacentNodeT(node139, 100);
        node135.addAdjacentNodeT(node140, 100);

        node136.addAdjacentNodeT(node19, 100);
        node136.addAdjacentNodeT(node89, 100);
        node136.addAdjacentNodeT(node141, 100);
        node136.addAdjacentNodeT(node142, 100);

        node137.addAdjacentNodeT(node19, 100);
        node137.addAdjacentNodeT(node112, 100);
        node137.addAdjacentNodeT(node142, 100);
        node137.addAdjacentNodeT(node143, 100);

        node138.addAdjacentNodeT(node105, 100);
        node138.addAdjacentNodeT(node112, 100);
        node138.addAdjacentNodeT(node143, 100);
        node138.addAdjacentNodeT(node144, 100);

        node139.addAdjacentNodeT(node135, 100);
        node139.addAdjacentNodeT(node11, 100);
        node139.addAdjacentNodeT(node13, 100);
        node139.addAdjacentNodeT(node146, 100);

        node140.addAdjacentNodeT(node135, 100);
        node140.addAdjacentNodeT(node58, 100);
        node140.addAdjacentNodeT(node141, 100);
        node140.addAdjacentNodeT(node57, 100);
        node140.addAdjacentNodeT(node59, 100);

        node141.addAdjacentNodeT(node136, 100);
        node141.addAdjacentNodeT(node140, 100);
        node141.addAdjacentNodeT(node58, 100);
        node141.addAdjacentNodeT(node88, 100);
        node141.addAdjacentNodeT(node90, 100);

        node142.addAdjacentNodeT(node136, 100);
        node142.addAdjacentNodeT(node137, 100);
        node142.addAdjacentNodeT(node18, 100);
        node142.addAdjacentNodeT(node19, 100);

        node143.addAdjacentNodeT(node137, 100);
        node143.addAdjacentNodeT(node138, 100);
        node143.addAdjacentNodeT(node111, 100);
        node143.addAdjacentNodeT(node113, 100);

        node144.addAdjacentNodeT(node138, 100);
        node144.addAdjacentNodeT(node145, 100);
        node144.addAdjacentNodeT(node34, 100);
        node144.addAdjacentNodeT(node104, 100);
        node144.addAdjacentNodeT(node106, 100);

        node145.addAdjacentNodeT(node144, 100);
        node145.addAdjacentNodeT(node33, 100);
        node145.addAdjacentNodeT(node35, 100);
    }
    public static void initNodeList(){
        nodes = new ArrayList<>();
        transNodes = new ArrayList<>();
    }

    //노드 초기화
    public static void initializeNodes() {
        for (Node node : nodes) {
            node.setCost(Integer.MAX_VALUE);
            node.setShortestPath(new LinkedList<>());
        }
        for (Node node : transNodes) {
            node.setCost(Integer.MAX_VALUE);
            node.setShortestPath(new LinkedList<>());
        }
    }


    //노드에 인접노드, 가중치 추가
    public void addAdjacentNode(Node node, int time, int cost){
        List<Integer> arr = new ArrayList<>();
        arr.add(0, time);
        arr.add(1, cost);
        adjacentNodes.put(node,arr);
    }


    @Override
    public int compareTo(Node node){
        return Integer.compare(this.cost, node.getCost());
    }


    //string으로 이름받아서
    public static result calculateShortestTime(String start, String end){
        Node source = null;
        Node destination = null;
        init();
        //루프돌려서 이름에 맞는 노드 배정
        for (Node node:nodes) {
            if(start.equals(node.getName())){
                source = node;
            }
            if (end.equals(node.getName())) {
                destination = node;
            }
        }
        for (Node node:transNodes) {
            if(start.equals(node.getName())){
                source = node;
            }
            if (end.equals(node.getName())) {
                destination = node;
            }
        }

        return timeCalcLogic(source, destination);
    }

    //다익스트라 계산 로직
    public static result timeCalcLogic(Node source, Node destination){
        initializeNodes();

        source.setTime(0);
        source.setCost(0);

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
                        evaluateTime(entry.getKey(), entry.getValue().get(0), currentNode);
                        unsettledNodes.add(entry.getKey());
                    });

            settleNodes.add(currentNode);
        }
        String resultPath = destination.printTImePath(destination);

        return new result(destination.getTime(), destination.getCost(), resultPath);
    }

    //주어진 인접노드와의 최단경로 평가하고 업데이트
    private static void evaluateTime(Node adjacentNode, Integer edgeWeight, Node sourceNode){
        Integer newTime = sourceNode.getTime() + edgeWeight;
        //새 가중치가 기존 가중치보다 작으면
        if(newTime < adjacentNode.getTime()){
            //가중치 초기화
            adjacentNode.setTime(newTime);
            //ShortestPath에 추가
            List<Node> newPath = Stream.concat(sourceNode.getShortestPath().stream(), Stream.of(sourceNode))
                    .collect(Collectors.toList());
            adjacentNode.setShortestPath(newPath);

            adjacentNode.setCost(adjacentNode.getCost() + edgeWeight);
        }
    }

    //경로 + 최소 가중치
    private String printTImePath(Node destination) {
        String path = destination.getShortestPath().stream()
                .map(Node::getName)
                .collect(Collectors.joining(" -> "));
        String resultPath = path.isBlank()
                ? String.format("%s ", destination.getName())
                : String.format("%s -> %s : ", path, destination.getName());
        return resultPath;
    }
/////////////////////////////////////////////////////////////////////////////////////////

    //string으로 이름받아서
    public static result calculateShortestCost(String start, String end){
        Node source = null;
        Node destination = null;
        init();
        //루프돌려서 이름에 맞는 노드 배정
        for (Node node:nodes) {
            if(start.equals(node.getName())){
                source = node;
            }
            if (end.equals(node.getName())) {
                destination = node;
            }
        }
        for (Node node:transNodes) {
            if(start.equals(node.getName())){
                source = node;
            }
            if (end.equals(node.getName())) {
                destination = node;
            }
        }

        return costCalcLogic(source, destination);
    }

    //다익스트라 계산 로직
    public static result costCalcLogic(Node source, Node destination){
        initializeNodes();

        source.setTime(0);
        source.setCost(0);

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
                        evaluateCost(entry.getKey(), entry.getValue().get(1), currentNode);
                        unsettledNodes.add(entry.getKey());
                    });

            settleNodes.add(currentNode);
        }
        String resultPath = destination.printCostPath(destination);

        return new result(destination.getTime(), destination.getCost(), resultPath);
    }

    //주어진 인접노드와의 최단경로 평가하고 업데이트
    private static void evaluateCost(Node adjacentNode, Integer edgeWeight, Node sourceNode){
        Integer newCost = sourceNode.getCost() + edgeWeight;
        //새 가중치가 기존 가중치보다 작으면
        if(newCost < adjacentNode.getCost()){
            //가중치 초기화
            adjacentNode.setCost(newCost);
            //ShortestPath에 추가
            List<Node> newPath = Stream.concat(sourceNode.getShortestPath().stream(), Stream.of(sourceNode))
                    .collect(Collectors.toList());
            adjacentNode.setShortestPath(newPath);

            adjacentNode.setTime(adjacentNode.getTime() + edgeWeight);
        }
    }

    //경로 + 최소 가중치
    private String printCostPath(Node destination) {
        String path = destination.getShortestPath().stream()
                .map(Node::getName)
                .collect(Collectors.joining(" -> "));
        String resultPath = path.isBlank()
                ? String.format("%s ", destination.getName())
                : String.format("%s -> %s : ", path, destination.getName());
        return resultPath;
    }
//최소환승 메서드들

    public static void initializeNodesT() {
        for (Node node : nodes) {
            node.setCost(Integer.MAX_VALUE);
            node.setShortestPath(new LinkedList<>());
            node.setTransferCount(0);
        }
        for (Node node : transNodes) {
            node.setCost(Integer.MAX_VALUE);
            node.setShortestPath(new LinkedList<>());
            node.setTransferCount(0);
        }
    }
    public void addAdjacentNodeT(Node node, int time, int cost){
        if (!this.line.equals(node.getLine())) {
            time += 2000; // 환승하면 가중치에 추가
        }
        List<Integer> arr = new ArrayList<>();
        arr.add(0, time);
        arr.add(1, cost);
        adjacentNodes.put(node, arr);
    }

    //이름받아서
    public static resultT calculateMinTransfer(String start, String end){
        Node source = null;
        Node source2 = null;
        Node destination = null;
        Node destination2 = null;
        initT();

        //이름 맞는거 찾아서 노드 배정
        for (Node node:nodes) {
            if(start.equals(node.getName())){
                source = node;
            }
            if (end.equals(node.getName())) {
                destination = node;
            }
        }

        //출발 or 도착이 환승역이면 source2, destination2도 배정
        for (Node node:transNodes) {
            if(source != null){
                break;
            }
            if(start.equals(node.getName())){
                source = node;
            }
        }

        for (Node node:transNodes) {
            if(start.equals(node.getName())){
                source2 = node;
            }
        }
        for (Node node:transNodes) {
            if(destination != null){
                break;
            }
            if(end.equals(node.getName())){
                destination = node;
            }
        }
        for (Node node:transNodes) {
            if(end.equals(node.getName())){
                destination2 = node;
            }
        }

        resultT result1 = calcLogicT(source, destination);

        if(source2 == null && destination2 == null){
            return result1;
        }
        //출발만 환승역
        if(source2 != null && destination2 == null){
            resultT result2 = calcLogicT(source2, destination);
            return result1.compareTo(result2) <= 0 ? result1 : result2;
        }
        //도착만 환승역
        if(source2 == null && destination2 != null){
            resultT result3 = calcLogicT(source, destination2);
            return result1.compareTo(result3) <= 0 ? result1 : result3;
        }
        //둘다 환승역
        if(source2 != null && destination2 != null){
            resultT result4 =calcLogicT(source2, destination);
            resultT result5 =calcLogicT(source2, destination2);
            resultT result6 = calcLogicT(source, destination2);

            resultT minResult = findMinimumResult(result1,result4,result5,result6);
            return minResult;
        }
        return result1;
    }

    public static resultT findMinimumResult(resultT... results) {
        resultT minResult = results[0];
        for (int i = 1; i < results.length; i++) {
            if (minResult.compareTo(results[i]) > 0) {
                minResult = results[i];
            }
        }
        return minResult;
    }
    public static resultT calcLogicT(Node source, Node destination){
        initializeNodesT();

        source.setTime(0);
        source.setCost(0);
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
                        evaluateTransferAndPath(entry.getKey(), entry.getValue().get(0), currentNode);
                        unsettledNodes.add(entry.getKey());
                    });

            settleNodes.add(currentNode);
        }
        destination.setCost(destination.getCost()-2000* destination.getTransferCount());
        destination.setTime(destination.getCost()-2000* destination.getTransferCount());

        String resultPath = destination.printMinTransfer(destination);

        return new resultT(destination.getTime(), destination.getCost(), destination.getTransferCount(), resultPath);
    }
    private static void evaluateTransferAndPath(Node adjacentNode, Integer edgeWeight, Node sourceNode){
        Integer newDistance = sourceNode.getCost() + edgeWeight;
        if(newDistance < adjacentNode.getCost()){
            adjacentNode.setCost(newDistance);
            List<Node> newPath = Stream.concat(sourceNode.getShortestPath().stream(), Stream.of(sourceNode))
                    .collect(Collectors.toList());
            adjacentNode.setShortestPath(newPath);

            // 환승 횟수 업데이트
            if (!sourceNode.getLine().equals(adjacentNode.getLine())) {
                adjacentNode.setTransferCount(sourceNode.getTransferCount() + 1);
            } else {
                adjacentNode.setTransferCount(sourceNode.getTransferCount());
            }
            adjacentNode.setCost(adjacentNode.getCost() + edgeWeight);
            adjacentNode.setTime(adjacentNode.getTime() + edgeWeight);
        }
    }
    private String printMinTransfer(Node destination) {
        String path = destination.getShortestPath().stream()
                .map(Node::getName)
                .collect(Collectors.joining(" -> "));
        String resultPath = path.isBlank()
                ? String.format("%s", destination.getName())
                : String.format("%s -> %s", path, destination.getName()); // 거리 출력 제거
        return resultPath;
    }
    @Getter
    @Setter
    @Component
    static
    class resultT implements Comparable<resultT>{

        private int transferCount = 100;
        private Integer cost;
        private Integer time;
        private String path;
        @Override
        public int compareTo(resultT other) {
            int compare = Integer.compare(this.transferCount, other.transferCount);
            if (compare == 0) {
                compare = Integer.compare(this.time, other.time);
            }
            return compare;
        }

        public resultT(Integer time, Integer cost, int transferCount, String path) {
            this.time = time;
            this.cost = cost;
            this.transferCount = transferCount;
            this.path = path;
        }
    }
    @Getter
    @Setter
    @RequiredArgsConstructor
    @Component

    public static class result{

        private Integer cost;
        private Integer time;
        private String path;
        public result(Integer time, Integer cost, String path) {
            this.time = time;
            this.cost = cost;
            this.path = path;
        }
    }
    public static void main(String[] args) {

    }
}
