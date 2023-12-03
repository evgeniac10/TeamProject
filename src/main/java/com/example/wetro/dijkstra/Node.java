package com.example.wetro.dijkstra;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.example.wetro.dijkstra.NodeT.calculateMinTransfer;

@Slf4j
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
        initNodeList();
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
        node1.addAdjacentNode(node2, 200,200);
        node1.addAdjacentNode(node24, 1000,300);
        node1.addAdjacentNode(node23, 480,200);
        node1.addAdjacentNode(node51, 480,200);

        node2.addAdjacentNode(node1, 200,200);
        node2.addAdjacentNode(node3, 300,300);
//103
        node3.addAdjacentNode(node2, 300,300);
        node3.addAdjacentNode(node4, 1000,500);
        node3.addAdjacentNode(node70, 1000,500);
//104
        node4.addAdjacentNode(node3, 1000,500);
        node4.addAdjacentNode(node5, 500,340);
        node4.addAdjacentNode(node53, 1000,650);
//105
        node5.addAdjacentNode(node4, 500,340);
        node5.addAdjacentNode(node6, 150,450);
//106
        node6.addAdjacentNode(node5, 150,450);
        node6.addAdjacentNode(node7, 320,120);
        node6.addAdjacentNode(node52, 320,120);
//107
        node7.addAdjacentNode(node6, 320,120);
        node7.addAdjacentNode(node8, 400,650);
        node7.addAdjacentNode(node49, 400,120);
//108
        node8.addAdjacentNode(node7, 400,650);//108 ->
        node8.addAdjacentNode(node9, 800,200);//108 ->
        node8.addAdjacentNode(node52, 400,650);//108 ->
        node8.addAdjacentNode(node84, 800,200);//108 ->
//109
        node9.addAdjacentNode(node8,  800,200);//109 ->
        node9.addAdjacentNode(node10, 900,430);//109 ->
        node9.addAdjacentNode(node80, 1000,540);//109 ->
//110
        node10.addAdjacentNode(node9, 900,430);//110 ->
        node10.addAdjacentNode(node11, 500,120);//110 ->
//111
        node11.addAdjacentNode(node10, 500,120);//111 ->
        node11.addAdjacentNode(node12, 1000,890);//111 ->
        node11.addAdjacentNode(node139, 1000,890);//111 ->
//112
        node12.addAdjacentNode(node11, 1000,890);//112 ->
        node12.addAdjacentNode(node13, 2000,800);//112 ->
        node12.addAdjacentNode(node146, 2000,800);//112 ->
//113
        node13.addAdjacentNode(node12, 2000,800);//113 ->
        node13.addAdjacentNode(node14, 500,700);//113 ->
        node13.addAdjacentNode(node124, 600,430);//113 ->
//114
        node14.addAdjacentNode(node13, 500,700);//114 ->
        node14.addAdjacentNode(node15, 220,540);//114 ->
        node14.addAdjacentNode(node146, 500,700);//114 ->
        node14.addAdjacentNode(node72, 220,540);//114 ->
//115
        node15.addAdjacentNode(node14, 220,540);//115 ->
        node15.addAdjacentNode(node16, 230,330);//115 ->
        node15.addAdjacentNode(node59, 320,330);//115 ->
        node15.addAdjacentNode(node60, 480,280);//115 ->
        node15.addAdjacentNode(node108,  230,330);//115 ->
//116
        node16.addAdjacentNode(node15,230,330);//116 ->
        node16.addAdjacentNode(node17, 300,280);//116 ->
        node16.addAdjacentNode(node90, 320,650);//116 ->
        node16.addAdjacentNode(node91, 250,440);//116 ->
        node16.addAdjacentNode(node72, 230,330);//116 ->
//117
        node17.addAdjacentNode(node16, 300,280);//117 ->
        node17.addAdjacentNode(node18, 500,800);//117 ->
        node17.addAdjacentNode(node108, 300,280);//117 ->
//118
        node18.addAdjacentNode(node17, 500,800);//118 ->
        node18.addAdjacentNode(node19, 480,1000);//118 ->
        node18.addAdjacentNode(node142, 480,1000);//118 ->
//119
        node19.addAdjacentNode(node18, 480,1000);//119 ->
        node19.addAdjacentNode(node20, 500,2000);//119 ->
        node19.addAdjacentNode(node136, 430,800);//119 ->
        node19.addAdjacentNode(node137, 1000,1000);//119 ->
//120
        node20.addAdjacentNode(node19, 500,2000);//120 ->
        node20.addAdjacentNode(node21, 400,700);//120 ->
        node20.addAdjacentNode(node142, 500,2000);//120 ->
        node20.addAdjacentNode(node107, 400,700);//120 ->
//121
        node21.addAdjacentNode(node20, 400,700);//121 ->
        node21.addAdjacentNode(node22, 900,650);//121 ->
        node21.addAdjacentNode(node86, 700,280);//121 ->
        node21.addAdjacentNode(node87, 500,800);//121 ->
        node21.addAdjacentNode(node82, 900,650);//121 ->
//122
        node22.addAdjacentNode(node21, 900,650);//122 ->
        node22.addAdjacentNode(node23, 300,440);//122 ->
        node22.addAdjacentNode(node77, 320,430);//122 ->
        node22.addAdjacentNode(node78, 480,120);//122 ->
        node22.addAdjacentNode(node107, 900,650);//122 ->
        node22.addAdjacentNode(node51, 300,440);//122 ->
//123
        node23.addAdjacentNode(node22, 300,440);//123 ->
        node23.addAdjacentNode(node1, 480,200);//123 ->
        node23.addAdjacentNode(node45, 250,200);//123 ->
        node23.addAdjacentNode(node46, 300,300);//123 ->
        node23.addAdjacentNode(node41, 480,200);//123 ->
        node23.addAdjacentNode(node82, 300,440);//123 ->

        // 2호선
        node24.addAdjacentNode(node1, 1000,300);//201 ->
        node24.addAdjacentNode(node25, 250,500);//201 ->
        node24.addAdjacentNode(node118, 250,500);//201 ->
        node24.addAdjacentNode(node41, 1000,300);//201 ->

        node25.addAdjacentNode(node24, 250,500);//202 ->
        node25.addAdjacentNode(node26, 480,340);//202 ->
        node25.addAdjacentNode(node44, 1000,2000);//202 ->
        node25.addAdjacentNode(node119, 1000,2000);//202 ->

        node26.addAdjacentNode(node25, 480,340);//203 ->
        node26.addAdjacentNode(node27, 400,450);//203 ->
        node26.addAdjacentNode(node118, 480,340);//203 ->

        node27.addAdjacentNode(node26, 400,450);//204 ->
        node27.addAdjacentNode(node28, 250,120);//204 ->

        node28.addAdjacentNode(node27, 250,120);//205 ->
        node28.addAdjacentNode(node29, 500,650);//205 ->

        node29.addAdjacentNode(node28, 500,650);//206 ->
        node29.addAdjacentNode(node30, 320,200);
        node29.addAdjacentNode(node50, 320,200);

        node30.addAdjacentNode(node29, 320,200);//207 ->
        node30.addAdjacentNode(node31, 250,430);
        node30.addAdjacentNode(node42, 300,2000);

        node31.addAdjacentNode(node30, 250,430);//208 ->
        node31.addAdjacentNode(node32, 300,120);
        node31.addAdjacentNode(node50, 250,430);
        node31.addAdjacentNode(node81, 300,120);

        node32.addAdjacentNode(node31, 300,120);//209 ->
        node32.addAdjacentNode(node33, 150,890);
        node32.addAdjacentNode(node74, 320,450);

        node33.addAdjacentNode(node32, 150,890);//210 ->
        node33.addAdjacentNode(node34, 900,800);
        node33.addAdjacentNode(node81, 150,890);
        node33.addAdjacentNode(node145, 900,800);

        node34.addAdjacentNode(node33, 900,800);//211 ->
        node34.addAdjacentNode(node35, 320,700);
        node34.addAdjacentNode(node105, 300,440);
        node34.addAdjacentNode(node144, 300,440);

        node35.addAdjacentNode(node34, 320,700);
        node35.addAdjacentNode(node36, 150,540);
        node35.addAdjacentNode(node145, 320,700);

        node36.addAdjacentNode(node35, 150,540);
        node36.addAdjacentNode(node37, 500,330);
        node36.addAdjacentNode(node134, 500,330);

        node37.addAdjacentNode(node36, 500,330);
        node37.addAdjacentNode(node38, 210,280);
        node37.addAdjacentNode(node102, 700,2000);
        node37.addAdjacentNode(node133, 700,2000);

        node38.addAdjacentNode(node37, 210,280);
        node38.addAdjacentNode(node39, 150,800);
        node38.addAdjacentNode(node134, 210,280);
        node38.addAdjacentNode(node73, 150,800);

        node39.addAdjacentNode(node38, 150,800);
        node39.addAdjacentNode(node40, 500,1000);
        node39.addAdjacentNode(node69, 900,340);
        node39.addAdjacentNode(node110, 900,340);

        node40.addAdjacentNode(node39, 500,1000);
        node40.addAdjacentNode(node73, 500,1000);

        node41.addAdjacentNode(node2, 200,200);
        node41.addAdjacentNode(node24, 1000,300);
        node41.addAdjacentNode(node23, 480,200);
        node41.addAdjacentNode(node51, 480,200);

// 3호선
        node42.addAdjacentNode(node43, 300,700);
        node42.addAdjacentNode(node30, 300,2000);
        node42.addAdjacentNode(node50, 300,2000);

        node43.addAdjacentNode(node42, 300,700);
        node43.addAdjacentNode(node44, 480,650);
        node43.addAdjacentNode(node119, 480,650);

        node44.addAdjacentNode(node43, 480,650);
        node44.addAdjacentNode(node45, 400,440);
        node44.addAdjacentNode(node25, 1000,2000);
        node44.addAdjacentNode(node118, 1000,2000);
        node44.addAdjacentNode(node76, 700,700);
        node44.addAdjacentNode(node120, 700,700);

        node45.addAdjacentNode(node44, 400,440);
        node45.addAdjacentNode(node119, 400,440);
        node45.addAdjacentNode(node51, 250,200);
        node45.addAdjacentNode(node23, 250,200);

        node46.addAdjacentNode(node23, 300,300);
        node46.addAdjacentNode(node51, 300,300);
        node46.addAdjacentNode(node47, 250,500);

        node47.addAdjacentNode(node46, 250,500);
        node47.addAdjacentNode(node71, 900,340);
        node47.addAdjacentNode(node48, 900,340);

        node48.addAdjacentNode(node47, 900,340);
        node48.addAdjacentNode(node49, 480,450);
        node48.addAdjacentNode(node54, 300,430);
        node48.addAdjacentNode(node53, 150,200);

        node49.addAdjacentNode(node48, 480,450);
        node49.addAdjacentNode(node52, 400,120);
        node49.addAdjacentNode(node71, 480,450);
        node49.addAdjacentNode(node7, 400,120);

        node50.addAdjacentNode(node29, 320,200);
        node50.addAdjacentNode(node31, 250,430);
        node50.addAdjacentNode(node42, 300,2000);

        node51.addAdjacentNode(node45, 250,200);
        node51.addAdjacentNode(node46, 300,300);
        node51.addAdjacentNode(node1, 480,200);
        node51.addAdjacentNode(node41, 480,200);
        node51.addAdjacentNode(node22, 300,440);
        node51.addAdjacentNode(node82, 300,440);

        node52.addAdjacentNode(node6, 320,120);
        node52.addAdjacentNode(node8, 400,650);
        node52.addAdjacentNode(node49, 400,120);

        // 4호선
        node53.addAdjacentNode(node4, 1000,650);
        node53.addAdjacentNode(node70, 1000,650);
        node53.addAdjacentNode(node71, 150,200);
        node53.addAdjacentNode(node48, 150,200);

        node54.addAdjacentNode(node48, 300,430);
        node54.addAdjacentNode(node71, 300,430);
        node54.addAdjacentNode(node55, 210,120);
        node54.addAdjacentNode(node83, 210,120);

        node55.addAdjacentNode(node54, 210,120);
        node55.addAdjacentNode(node56, 320,890);

        node56.addAdjacentNode(node55, 320,890);
        node56.addAdjacentNode(node57, 210,800);
        node56.addAdjacentNode(node83, 320,890);

        node57.addAdjacentNode(node56, 210,800);
        node57.addAdjacentNode(node58, 500,700);
        node57.addAdjacentNode(node140, 500,700);

        node58.addAdjacentNode(node57, 500,700);
        node58.addAdjacentNode(node59, 300,540);
        node58.addAdjacentNode(node135, 300,650);
        node58.addAdjacentNode(node141, 210,440);
        node58.addAdjacentNode(node89, 210,440);

        node59.addAdjacentNode(node58, 300,540);
        node59.addAdjacentNode(node140, 300,540);
        node59.addAdjacentNode(node15, 320,330);
        node59.addAdjacentNode(node72, 320,330);

        node60.addAdjacentNode(node72, 480,280);
        node60.addAdjacentNode(node15, 480,280);
        node60.addAdjacentNode(node61, 300,800);
        node60.addAdjacentNode(node130, 300,800);

        node61.addAdjacentNode(node60, 300,800);
        node61.addAdjacentNode(node62, 480,1000);

        node62.addAdjacentNode(node61, 480,1000);
        node62.addAdjacentNode(node63, 300,2000);
        node62.addAdjacentNode(node130, 480,1000);

        node63.addAdjacentNode(node62, 300,2000);
        node63.addAdjacentNode(node64, 900,700);

        node64.addAdjacentNode(node63, 900,700);
        node64.addAdjacentNode(node65, 400,650);
        node64.addAdjacentNode(node93, 320,500);
        node64.addAdjacentNode(node94, 1000,340);

        node65.addAdjacentNode(node64, 400,650);
        node65.addAdjacentNode(node66, 430,440);
        node65.addAdjacentNode(node109, 400,650);

        node66.addAdjacentNode(node65, 430,440);
        node66.addAdjacentNode(node67, 150,200);

        node67.addAdjacentNode(node66, 150,200);
        node67.addAdjacentNode(node68, 1000,300);
        node67.addAdjacentNode(node122, 1000,300);

        node68.addAdjacentNode(node67, 1000,300);
        node68.addAdjacentNode(node69, 500,500);
        node68.addAdjacentNode(node110, 500,500);
        node68.addAdjacentNode(node116, 300,120);
        node68.addAdjacentNode(node117, 430,650);

        node69.addAdjacentNode(node68, 500,500);
        node69.addAdjacentNode(node122, 500,500);
        node69.addAdjacentNode(node101, 320,800);
        node69.addAdjacentNode(node100, 480,890);
        node69.addAdjacentNode(node73, 900,340);
        node69.addAdjacentNode(node39, 900,340);

        node70.addAdjacentNode(node3, 1000,500);
        node70.addAdjacentNode(node5, 500,340);
        node70.addAdjacentNode(node53, 1000,650);

        node71.addAdjacentNode(node53, 150,200);
        node71.addAdjacentNode(node54, 300,430);
        node71.addAdjacentNode(node47, 900,340);
        node71.addAdjacentNode(node49, 480,450);

        node72.addAdjacentNode(node59, 320,330);
        node72.addAdjacentNode(node60, 480,280);
        node72.addAdjacentNode(node14, 220,540);
        node72.addAdjacentNode(node16, 230,330);
        node72.addAdjacentNode(node108, 230,330);

        node73.addAdjacentNode(node38, 150,800);
        node73.addAdjacentNode(node40, 500,1000);
        node73.addAdjacentNode(node69, 900,340);
        node73.addAdjacentNode(node110, 900,340);

        // 5호선
        node74.addAdjacentNode(node75, 320,120);
        node74.addAdjacentNode(node81, 320,450);
        node74.addAdjacentNode(node32, 320,450);

        node75.addAdjacentNode(node74, 320,120);
        node75.addAdjacentNode(node76, 430,650);
        node75.addAdjacentNode(node120, 430,650);

        node76.addAdjacentNode(node75, 430,650);
        node76.addAdjacentNode(node77, 210,200);
        node76.addAdjacentNode(node119, 700,700);
        node76.addAdjacentNode(node44, 700,700);
        node76.addAdjacentNode(node85, 500,650);
        node76.addAdjacentNode(node121, 500,650);

        node77.addAdjacentNode(node76, 210,200);
        node77.addAdjacentNode(node120, 210,200);
        node77.addAdjacentNode(node82, 320,430);
        node77.addAdjacentNode(node22, 320,430);

        node78.addAdjacentNode(node79, 300,890);
        node78.addAdjacentNode(node22, 480,120);
        node78.addAdjacentNode(node82, 480,120);

        node79.addAdjacentNode(node78, 300,890);
        node79.addAdjacentNode(node55, 320,800);
        node79.addAdjacentNode(node83, 320,800);

        node80.addAdjacentNode(node83, 300,700);
        node80.addAdjacentNode(node55, 300,700);
        node80.addAdjacentNode(node9, 1000,540);
        node80.addAdjacentNode(node84, 1000,540);

        node81.addAdjacentNode(node31, 300, 120);
        node81.addAdjacentNode(node33, 150, 890);
        node81.addAdjacentNode(node74, 320, 450);

        node82.addAdjacentNode(node77, 320, 430);
        node82.addAdjacentNode(node78, 480, 120);
        node82.addAdjacentNode(node21, 900, 650);
        node82.addAdjacentNode(node23, 300, 440);
        node82.addAdjacentNode(node51, 300, 440);
        node82.addAdjacentNode(node107, 900, 650);

        node83.addAdjacentNode(node54, 210, 120);
        node83.addAdjacentNode(node56, 320, 890);
        node83.addAdjacentNode(node79, 320, 800);
        node83.addAdjacentNode(node80, 300, 700);

        node84.addAdjacentNode(node8, 800, 200);
        node84.addAdjacentNode(node10, 900, 430);
        node84.addAdjacentNode(node80, 1000, 540);

        // 6호선
        node85.addAdjacentNode(node86, 150,330);
        node85.addAdjacentNode(node106, 150,1000);
        node85.addAdjacentNode(node76, 500, 650);
        node85.addAdjacentNode(node120, 500, 650);
        node85.addAdjacentNode(node111, 430, 440);

        node86.addAdjacentNode(node85, 150, 330);
        node86.addAdjacentNode(node121, 150,330);
        node86.addAdjacentNode(node107, 70,280);
        node86.addAdjacentNode(node21, 700, 280);

        node87.addAdjacentNode(node88, 300, 1000);
        node87.addAdjacentNode(node107, 650, 800);
        node87.addAdjacentNode(node21, 650,800);

        node88.addAdjacentNode(node87, 300, 1000);
        node88.addAdjacentNode(node89, 430,2000);
        node88.addAdjacentNode(node141, 430, 2000);

        node89.addAdjacentNode(node88, 430,2000);
        node89.addAdjacentNode(node90, 480,700);
        node89.addAdjacentNode(node136, 480, 280);
        node89.addAdjacentNode(node140, 210, 440);
        node89.addAdjacentNode(node58, 210,440);

        node90.addAdjacentNode(node89, 480,700);
        node90.addAdjacentNode(node141, 480,700);
        node90.addAdjacentNode(node108, 320,650);
        node90.addAdjacentNode(node16, 320,650);

        node91.addAdjacentNode(node16, 250,440);
        node91.addAdjacentNode(node108, 250,440);
        node91.addAdjacentNode(node92, 500,200);
        node91.addAdjacentNode(node131, 500,200);

        node92.addAdjacentNode(node91, 500,200);
        node92.addAdjacentNode(node93, 700,300);
        node92.addAdjacentNode(node127, 700,540);
        node92.addAdjacentNode(node130, 500,700);
        node92.addAdjacentNode(node61, 500,700);

        node93.addAdjacentNode(node92, 700,300);
        node93.addAdjacentNode(node131, 700,300);
        node93.addAdjacentNode(node109, 320,500);
        node93.addAdjacentNode(node64, 320,500);

        node94.addAdjacentNode(node64, 1000,340);
        node94.addAdjacentNode(node109, 1000, 340);
        node94.addAdjacentNode(node95, 700,450);

        node95.addAdjacentNode(node94, 700,450);
        node95.addAdjacentNode(node96, 700,120);

        node96.addAdjacentNode(node95, 700,120);
        node96.addAdjacentNode(node97, 150,650);

        node97.addAdjacentNode(node96, 150,650);
        node97.addAdjacentNode(node98, 430,200);
        node97.addAdjacentNode(node123, 430,200);

        node98.addAdjacentNode(node97, 430,200);
        node98.addAdjacentNode(node99, 500,430);
        node98.addAdjacentNode(node117, 480,200);

        node99.addAdjacentNode(node98, 500,430);
        node99.addAdjacentNode(node100, 700,120);
        node99.addAdjacentNode(node123, 500,430);

        node100.addAdjacentNode(node99, 700,120);
        node100.addAdjacentNode(node110, 480,890);
        node100.addAdjacentNode(node69, 480,890);

        node101.addAdjacentNode(node69, 320,800);
        node101.addAdjacentNode(node133, 300,700);
        node101.addAdjacentNode(node102, 300,700);
        node101.addAdjacentNode(node110, 320,800);

        node102.addAdjacentNode(node37, 700,2000);
        node102.addAdjacentNode(node101, 300,700);
        node102.addAdjacentNode(node103, 250,540);
        node102.addAdjacentNode(node115, 250,1000);
        node102.addAdjacentNode(node132, 250,1000);
        node102.addAdjacentNode(node134, 700,2000);

        node103.addAdjacentNode(node133, 250,540);
        node103.addAdjacentNode(node102, 250,540);
        node103.addAdjacentNode(node104, 700,330);

        node104.addAdjacentNode(node144, 320,280);
        node104.addAdjacentNode(node103, 700,330);
        node104.addAdjacentNode(node105, 320,280);

        node105.addAdjacentNode(node138, 250,650);
        node105.addAdjacentNode(node145, 300,440);
        node105.addAdjacentNode(node34, 300,440);
        node105.addAdjacentNode(node104, 320,280);
        node105.addAdjacentNode(node106, 480,800);

        node106.addAdjacentNode(node144, 480,800);
        node106.addAdjacentNode(node121, 150,1000);
        node106.addAdjacentNode(node85, 150,1000);
        node106.addAdjacentNode(node105, 480,800);

        node107.addAdjacentNode(node20, 400,700);
        node107.addAdjacentNode(node22, 900,650);
        node107.addAdjacentNode(node86, 700,280);
        node107.addAdjacentNode(node87, 500,800);
        node107.addAdjacentNode(node82, 900,650);

        node108.addAdjacentNode(node15, 230,330);
        node108.addAdjacentNode(node17, 300,280);
        node108.addAdjacentNode(node90, 320,660);
        node108.addAdjacentNode(node91, 250,440);
        node108.addAdjacentNode(node72, 230,330);

        node109.addAdjacentNode(node63, 900,700);
        node109.addAdjacentNode(node65, 400,650);
        node109.addAdjacentNode(node93, 320,500);
        node109.addAdjacentNode(node94, 1000,340);

        node110.addAdjacentNode(node68, 500,500);
        node110.addAdjacentNode(node122, 500,500);
        node110.addAdjacentNode(node101, 320,800);
        node110.addAdjacentNode(node100, 480,890);
        node110.addAdjacentNode(node73, 900,340);
        node110.addAdjacentNode(node39, 900,340);


        // 7호선
        node111.addAdjacentNode(node85, 430,440);
        node111.addAdjacentNode(node143, 150,200);
        node111.addAdjacentNode(node112, 150,200);
        node111.addAdjacentNode(node121, 430,440);

        node112.addAdjacentNode(node137, 150,2000);
        node112.addAdjacentNode(node138, 500,700);
        node112.addAdjacentNode(node111, 150,200);
        node112.addAdjacentNode(node113, 600,300);

        node113.addAdjacentNode(node143, 600,300);
        node113.addAdjacentNode(node112, 600,300);
        node113.addAdjacentNode(node114, 700,500);

        node114.addAdjacentNode(node132, 250,340);
        node114.addAdjacentNode(node113, 700,500);
        node114.addAdjacentNode(node115, 250,340);

        node115.addAdjacentNode(node102, 250,1000);
        node115.addAdjacentNode(node129, 600,800);
        node115.addAdjacentNode(node133, 250,1000);
        node115.addAdjacentNode(node114, 250,340);
        node115.addAdjacentNode(node116, 600,450);

        node116.addAdjacentNode(node68, 300,120);
        node116.addAdjacentNode(node132, 600,450);
        node116.addAdjacentNode(node115, 600,450);
        node116.addAdjacentNode(node122, 300,120);

        node117.addAdjacentNode(node68, 430,650);
        node117.addAdjacentNode(node98, 480,200);
        node117.addAdjacentNode(node122, 430,650);
        node117.addAdjacentNode(node123, 480,200);

        node118.addAdjacentNode(node24, 250,500);
        node118.addAdjacentNode(node26, 480,340);
        node118.addAdjacentNode(node44, 1000,2000);
        node118.addAdjacentNode(node119, 1000,2000);

        node119.addAdjacentNode(node25, 1000,2000);
        node119.addAdjacentNode(node43, 480,650);
        node119.addAdjacentNode(node45, 400,440);
        node119.addAdjacentNode(node76, 700,700);
        node119.addAdjacentNode(node118, 1000,2000);
        node119.addAdjacentNode(node120, 700,700);

        node120.addAdjacentNode(node44, 700,700);
        node120.addAdjacentNode(node75, 430,650);
        node120.addAdjacentNode(node77, 210,200);
        node120.addAdjacentNode(node85, 500,650);
        node120.addAdjacentNode(node119, 700,700);
        node120.addAdjacentNode(node121, 500,650);

        node121.addAdjacentNode(node76, 500,650);
        node121.addAdjacentNode(node86, 150,330);
        node121.addAdjacentNode(node106, 150,1000);
        node121.addAdjacentNode(node111, 430,440);
        node121.addAdjacentNode(node120, 500,650);

        node122.addAdjacentNode(node67, 1000,300);
        node122.addAdjacentNode(node69, 500,500);
        node122.addAdjacentNode(node110, 500,500);
        node122.addAdjacentNode(node116, 300,120);
        node122.addAdjacentNode(node117, 430,650);

        node123.addAdjacentNode(node97, 430,200);
        node123.addAdjacentNode(node99, 500,430);
        node123.addAdjacentNode(node117, 480,200);

// 8호선
        node124.addAdjacentNode(node125, 1000, 120);
        node124.addAdjacentNode(node146, 600, 430);
        node124.addAdjacentNode(node13, 600, 430);

        node125.addAdjacentNode(node124, 1000, 120);
        node125.addAdjacentNode(node126, 700, 890);

        node126.addAdjacentNode(node125, 700, 890);
        node126.addAdjacentNode(node130, 600, 800);
        node126.addAdjacentNode(node61, 600, 800);

        node127.addAdjacentNode(node128, 150, 330);
        node127.addAdjacentNode(node131, 700, 540);
        node127.addAdjacentNode(node92, 700, 540);

        node128.addAdjacentNode(node127, 150, 330);
        node128.addAdjacentNode(node129, 210, 280);

        node129.addAdjacentNode(node128, 210, 280);
        node129.addAdjacentNode(node132, 600, 800);
        node129.addAdjacentNode(node115, 600, 800);

        node130.addAdjacentNode(node60, 300, 800);
        node130.addAdjacentNode(node62, 480, 1000);
        node130.addAdjacentNode(node92, 500, 700);
        node130.addAdjacentNode(node126, 600, 800);
        node130.addAdjacentNode(node131, 500, 700);

        node131.addAdjacentNode(node61, 500, 700);
        node131.addAdjacentNode(node91, 500, 200);
        node131.addAdjacentNode(node93, 700, 300);
        node131.addAdjacentNode(node127, 700, 540);
        node131.addAdjacentNode(node130, 500, 700);

        node132.addAdjacentNode(node102, 250, 1000);
        node132.addAdjacentNode(node114, 250, 340);
        node132.addAdjacentNode(node116, 600, 450);
        node132.addAdjacentNode(node129, 600, 800);
        node132.addAdjacentNode(node133, 250, 1000);

        node133.addAdjacentNode(node37, 700, 2000);
        node133.addAdjacentNode(node101, 300, 700);
        node133.addAdjacentNode(node103, 250, 540);
        node133.addAdjacentNode(node115, 250, 1000);
        node133.addAdjacentNode(node132, 250, 1000);
        node133.addAdjacentNode(node134, 700, 2000);

        node134.addAdjacentNode(node36, 500, 330);
        node134.addAdjacentNode(node38, 210, 280);
        node134.addAdjacentNode(node102, 700, 2000);
        node134.addAdjacentNode(node133, 700, 2000);

        node146.addAdjacentNode(node12, 2000, 800);
        node146.addAdjacentNode(node14, 500, 700);
        node146.addAdjacentNode(node139, 2000, 800);
        node146.addAdjacentNode(node124, 600, 430);


        // 9호선
        node135.addAdjacentNode(node12, 600, 700);
        node135.addAdjacentNode(node58, 300, 650);
        node135.addAdjacentNode(node139, 600, 700);
        node135.addAdjacentNode(node140, 300, 650);

        node136.addAdjacentNode(node19, 430, 800);
        node136.addAdjacentNode(node89, 480, 280);
        node136.addAdjacentNode(node141, 480, 280);
        node136.addAdjacentNode(node142, 430, 800);

        node137.addAdjacentNode(node19, 1000, 1000);
        node137.addAdjacentNode(node112, 150, 2000);
        node137.addAdjacentNode(node142, 1000, 1000);
        node137.addAdjacentNode(node143, 150, 2000);

        node138.addAdjacentNode(node105, 250, 650);
        node138.addAdjacentNode(node112, 500, 700);
        node138.addAdjacentNode(node143, 500, 700);
        node138.addAdjacentNode(node144, 250, 650);

        node139.addAdjacentNode(node135, 600, 700);
        node139.addAdjacentNode(node11, 1000, 890);
        node139.addAdjacentNode(node13, 2000, 800);
        node139.addAdjacentNode(node146, 2000, 800);

        node140.addAdjacentNode(node135, 300, 650);
        node140.addAdjacentNode(node89, 210, 440);
        node140.addAdjacentNode(node141, 210, 440);
        node140.addAdjacentNode(node57, 500, 700);
        node140.addAdjacentNode(node59, 300, 540);

        node141.addAdjacentNode(node136, 480, 280);
        node141.addAdjacentNode(node140, 210, 440);
        node141.addAdjacentNode(node58, 210, 440);
        node141.addAdjacentNode(node88, 430, 2000);
        node141.addAdjacentNode(node90, 480, 700);

        node142.addAdjacentNode(node136, 430, 800);
        node142.addAdjacentNode(node137, 1000, 1000);
        node142.addAdjacentNode(node18, 480, 1000);
        node142.addAdjacentNode(node20, 500, 2000);

        node143.addAdjacentNode(node137, 150, 2000);
        node143.addAdjacentNode(node138, 500, 700);
        node143.addAdjacentNode(node111, 150, 200);
        node143.addAdjacentNode(node113, 600, 300);

        node144.addAdjacentNode(node138, 250, 650);
        node144.addAdjacentNode(node145, 300, 440);
        node144.addAdjacentNode(node34, 300, 440);
        node144.addAdjacentNode(node104, 320, 280);
        node144.addAdjacentNode(node106, 480, 800);

        node145.addAdjacentNode(node144, 300, 440);
        node145.addAdjacentNode(node33, 900, 800);
        node145.addAdjacentNode(node35, 320, 700);

    }

    public static void initNodeList(){
        nodes = new ArrayList<>();
        transNodes = new ArrayList<>();
    }

    //노드 초기화
    public static void initializeNodes() {
        for (Node node : nodes) {
            node.setCost(Integer.MAX_VALUE);
            node.setTime(Integer.MAX_VALUE);
            node.setShortestPath(new LinkedList<>());
            node.transferCount = 0;
        }
        for (Node node : transNodes) {
            node.setCost(Integer.MAX_VALUE);
            node.setTime(Integer.MAX_VALUE);
            node.setShortestPath(new LinkedList<>());
            node.transferCount = 0;
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
                        evaluateTime(entry.getKey(), entry.getValue().get(0), entry.getValue().get(1), currentNode);
                        unsettledNodes.add(entry.getKey());
                    });

            settleNodes.add(currentNode);
        }

        String resultPath = destination.printTImePath(destination);

        if(destination.getTransferCount() == 0){
            return new result(destination.getTime(), destination.getCost(), 0, resultPath);
        }
        else{
            return new result(destination.getTime(), destination.getCost(), destination.getTransferCount()-1, resultPath);
        }
    }

    //주어진 인접노드와의 최단경로 평가하고 업데이트
    private static void evaluateTime(Node adjacentNode, Integer time, Integer cost,Node sourceNode){
        Integer newTime = sourceNode.getTime() + time;
        Integer newCost = sourceNode.getCost() + cost;
        //새 가중치가 기존 가중치보다 작으면
        if (newTime < adjacentNode.getTime()
                || (newTime.equals(adjacentNode.getTime()) && sourceNode.getTransferCount() < adjacentNode.getTransferCount())) {

            //가중치 초기화
            adjacentNode.setTime(newTime);
            adjacentNode.setCost(newCost);
            //ShortestPath에 추가
            List<Node> newPath = Stream.concat(sourceNode.getShortestPath().stream(), Stream.of(sourceNode))
                    .collect(Collectors.toList());
            adjacentNode.setShortestPath(newPath);

            if (!sourceNode.getLine().equals(adjacentNode.getLine())) {
                adjacentNode.setTransferCount(sourceNode.getTransferCount() + 1);
            } else {
                adjacentNode.setTransferCount(sourceNode.getTransferCount());
            }
        }
    }
//    private static void evaluateTime(Node adjacentNode, Integer time, Integer cost, Node sourceNode){
//        Integer newTime = sourceNode.getTime() + time;
//        Integer newCost = sourceNode.getCost() + cost;
//
//        if (newTime < adjacentNode.getTime()
//                || (newTime.equals(adjacentNode.getTime()) && sourceNode.getTransferCount() < adjacentNode.getTransferCount())) {
//
//            adjacentNode.setTime(newTime);
//            adjacentNode.setCost(newCost);
//
//            List<Node> newPath = Stream.concat(sourceNode.getShortestPath().stream(), Stream.of(sourceNode))
//                    .collect(Collectors.toList());
//            adjacentNode.setShortestPath(newPath);
//
//            if (!sourceNode.getLine().equals(adjacentNode.getLine())) {
//                adjacentNode.setTransferCount(sourceNode.getTransferCount() + 1);
//            } else {
//                adjacentNode.setTransferCount(sourceNode.getTransferCount());
//            }
//        } else if (newTime.equals(adjacentNode.getTime()) && newCost.equals(adjacentNode.getCost()) && sourceNode.getTransferCount() <= adjacentNode.getTransferCount()) {
//            adjacentNode.setTransferCount(sourceNode.getTransferCount());
//        }
//    }





    //경로 + 최소 가중치
    private String printTImePath(Node destination) {
        String path = destination.getShortestPath().stream()
                .map(Node::getName)
                .collect(Collectors.joining(" -> "));
        String resultPath = path.isBlank()
                ? String.format("%s ", destination.getName())
                : String.format("%s -> %s ", path, destination.getName());
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
                        evaluateCost(entry.getKey(), entry.getValue().get(1), entry.getValue().get(0), currentNode);
                        unsettledNodes.add(entry.getKey());
                    });

            settleNodes.add(currentNode);
        }
        String resultPath = destination.printCostPath(destination);

        if(destination.getTransferCount() == 0){
            return new result(destination.getTime(), destination.getCost(), 0, resultPath);
        }
        else{
            return new result(destination.getTime(), destination.getCost(), destination.getTransferCount() -1, resultPath);
        }
    }

//    주어진 인접노드와의 최단경로 평가하고 업데이트
    private static void evaluateCost(Node adjacentNode, Integer cost, Integer time, Node sourceNode){
        Integer newCost = sourceNode.getCost() + cost;
        Integer newTime = sourceNode.getTime() + time;

        //새 가중치가 기존 가중치보다 작으면
        if (newCost < adjacentNode.getCost()
                || (newCost.equals(adjacentNode.getCost()) && sourceNode.getTransferCount() < adjacentNode.getTransferCount())) {
            //가중치 초기화
            adjacentNode.setCost(newCost);
            adjacentNode.setTime(newTime);
            //ShortestPath에 추가
            List<Node> newPath = Stream.concat(sourceNode.getShortestPath().stream(), Stream.of(sourceNode))
                    .collect(Collectors.toList());
            adjacentNode.setShortestPath(newPath);

            if (!sourceNode.getLine().equals(adjacentNode.getLine())) {
                adjacentNode.setTransferCount(sourceNode.getTransferCount() + 1);
            } else {
                adjacentNode.setTransferCount(sourceNode.getTransferCount());
            }

        }
    }
//    private static void evaluateCost(Node adjacentNode, Integer cost, Integer time, Node sourceNode){
//        Integer newCost = sourceNode.getCost() + cost;
//        Integer newTime = sourceNode.getTime() + time;
//
//        if (newCost < adjacentNode.getCost()
//                || (newCost.equals(adjacentNode.getCost()) && sourceNode.getTransferCount() < adjacentNode.getTransferCount())) {
//
//            adjacentNode.setCost(newCost);
//            adjacentNode.setTime(newTime);
//
//            List<Node> newPath = Stream.concat(sourceNode.getShortestPath().stream(), Stream.of(sourceNode))
//                    .collect(Collectors.toList());
//            adjacentNode.setShortestPath(newPath);
//
//            if (!sourceNode.getLine().equals(adjacentNode.getLine())) {
//                adjacentNode.setTransferCount(sourceNode.getTransferCount() + 1);
//            } else {
//                adjacentNode.setTransferCount(sourceNode.getTransferCount());
//            }
//        } else if (newCost.equals(adjacentNode.getCost()) && newTime.equals(adjacentNode.getTime()) && sourceNode.getTransferCount() <= adjacentNode.getTransferCount()) {
//            adjacentNode.setTransferCount(sourceNode.getTransferCount());
//        }
//    }



    //경로 + 최소 가중치
    private String printCostPath(Node destination) {
        String path = destination.getShortestPath().stream()
                .map(Node::getName)
                .collect(Collectors.joining(" -> "));
        String resultPath = path.isBlank()
                ? String.format("%s ", destination.getName())
                : String.format("%s -> %s ", path, destination.getName());
        return resultPath;
    }

    @Getter
    @Setter
    @RequiredArgsConstructor
    public static class result{
        private int transferCount;
        private Integer cost;
        private Integer time;
        private String path;
        public result(Integer time, Integer cost, int transferCount,String path) {
            this.time = time;
            this.cost = cost;
            this.path = path;
            this.transferCount = transferCount;
        }
    }

    public static void main(String[] args) {
        System.out.println(        calculateShortestCost("307", "615").getCost());
        System.out.println(        calculateShortestCost("307", "615").getTime());
        System.out.println(        calculateShortestCost("307", "615").getPath());
        System.out.println(        calculateShortestCost("307", "615").getTransferCount());

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~307~~~~615~~~~~~~~~~~~~~~~~~~~~");

        System.out.println(        calculateShortestTime("307", "615").getCost());
        System.out.println(        calculateShortestTime("307", "615").getTime());
        System.out.println(        calculateShortestTime("307", "615").getPath());
        System.out.println(        calculateShortestTime("307", "615").getTransferCount());
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~215~~~~~~~~~~~~~~~~~~~~~");

        System.out.println(        calculateMinTransfer("307",  "615").getCost());
        System.out.println(        calculateMinTransfer("307",  "615").getTime());
        System.out.println(        calculateMinTransfer("307",  "615").getPath());
        System.out.println(        calculateMinTransfer("307",  "615").getTransferCount());
    }
}

