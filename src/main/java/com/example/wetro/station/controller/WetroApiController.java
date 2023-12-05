package com.example.wetro.station.controller;

import com.example.wetro.response.stationResponse;
import com.example.wetro.dijkstra.*;
import com.example.wetro.station.dto.Infolists;
import com.example.wetro.station.dto.StationDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.example.wetro.response.Message.*;
import static com.example.wetro.response.stationResponse.*;

@Slf4j
@RestController
@RequestMapping("/wetro")
@RequiredArgsConstructor
public class WetroApiController {



    @PostMapping(value = "/search")
    @PreAuthorize("hasAuthority('USER')")
    @ResponseBody
    public stationResponse Info(@RequestBody StationDto stationDto) {

        log.info("입력한 출발역 = {}",stationDto.getFrom());
        log.info("입력한 경유지 = {}",stationDto.getLayover());
        log.info("입력한 도착역 = {}",stationDto.getTo());
        if(stationDto.getLayover() == null) {//경유지가 없다면
            log.info("경유지가 없다면 ");
            Node.result result1 = Node.calculateShortestTime(stationDto.getFrom(), stationDto.getTo());//최소시간
            Node.result result2 = Node.calculateShortestCost(stationDto.getFrom(), stationDto.getTo());//최소비용
            NodeT.resultT resultT = NodeT.calculateMinTransfer(stationDto.getFrom(), stationDto.getTo());//최소환승

            Integer time1 = result1.getTime();
            Integer cost1 = result1.getCost();
            Integer transfer1 = result1.getTransferCount();
            List<Node> transNode1 = result1.getTransInPath();
            List<String> stations1 = new ArrayList<>();
            for (Node node : transNode1) {
                stations1.add(node.getName());
            }
            Infolists minTimeInfo = new Infolists(time1, cost1, transfer1,stations1);

            Integer time2 = result2.getTime();
            Integer cost2 = result2.getCost();
            Integer transfer2 = result2.getTransferCount();
            List<Node> transNode2 = result2.getTransInPath();
            List<String> stations2 = new ArrayList<>();
            for (Node node : transNode2) {
                stations2.add(node.getName());
            }
            Infolists minCostInfo = new Infolists(time2, cost2, transfer2,stations2);

            Integer time3 = resultT.getTime();
            Integer cost3 = resultT.getCost();
            int transfer3 = resultT.getTransferCount();
            List<NodeT> transNodeT = resultT.getTransInPath();
            List<String> stationsT = new ArrayList<>();
            for (NodeT node : transNodeT) {
                stationsT.add(node.getName());
            }
            Infolists minTransInfo = new Infolists(time3, cost3, transfer3,stationsT);

            List<Infolists> infolists = new ArrayList<>();
            infolists.add(minTimeInfo);
            infolists.add(minCostInfo);
            infolists.add(minTransInfo);


            log.info("\n최소 시간 결과 = \n 시간 {} \n 비용 {} \n 환승 횟수 {}\n 경로 {}"
                    , result1.getTime()
                    , result1.getCost()
                    ,result1.getTransferCount()-1
                    ,result1.getPath());

            log.info("\n최소 비용 결과 = \n 시간 {} \n 비용 {} \n 환승 횟수 {}\n 경로 {}\n"
                    , result2.getTime()
                    , result2.getCost()
                    ,result2.getTransferCount()-1
                    ,result2.getPath());
            log.info("\n최소 환승 결과 =\n 시간 {} \n 비용 {} \n 환승 횟수 {}\n 경로 {}\n"
                    , resultT.getTime()
                    , resultT.getCost()
                    ,resultT.getTransferCount()
                    ,resultT.getPath());


            return success(SUCCESS_TO_INFO,infolists);
        }else{
            log.info("경유지가 있다면 ");
            Node.result result1 = Node.calculateShortestTime(stationDto.getFrom(), stationDto.getLayover());//최소시간
            Node.result result2 = Node.calculateShortestCost(stationDto.getFrom(), stationDto.getLayover());//최소비용
            NodeT.resultT resultT = NodeT.calculateMinTransfer(stationDto.getFrom(), stationDto.getLayover());//최소환승

            Node.result layResult1 = Node.calculateShortestTime(stationDto.getLayover(), stationDto.getTo());//최소시간
            Node.result layResult2 = Node.calculateShortestCost(stationDto.getLayover(), stationDto.getTo());//최소비용
            NodeT.resultT layResultT = NodeT.calculateMinTransfer(stationDto.getLayover(), stationDto.getTo());//최소환승

        Integer time1 = result1.getTime()+layResult1.getTime();
        Integer cost1 = result1.getCost()+layResult1.getCost();
        Integer transfer1 = result1.getTransferCount()+layResult1.getTransferCount();
            List<Node> transNodes1 = result1.getTransInPath();
            List<Node> transNode1 = layResult1.getTransInPath();
            transNodes1.addAll(transNode1);
            List<String> stations1 = new ArrayList<>();
            for (Node node : transNodes1) {
                stations1.add(node.getName());
            }
            Infolists minTimeInfo = new Infolists(time1, cost1, transfer1,stations1);

        Integer time2 = result2.getTime()+layResult2.getTime();
        Integer cost2 = result2.getCost()+layResult2.getCost();
        Integer transfer2 = result2.getTransferCount()+layResult2.getTransferCount();
            List<Node> transNodes2 = result2.getTransInPath();
            List<Node> transNode2 = layResult2.getTransInPath();
            transNodes2.addAll(transNode2);
            List<String> stations2 = new ArrayList<>();
            for (Node node : transNodes2) {
                stations2.add(node.getName());
            }
            Infolists minCostInfo = new Infolists(time1, cost1, transfer1,stations2);

        Integer time3 = resultT.getTime()+layResultT.getTime();
        Integer cost3 = resultT.getCost()+layResultT.getCost();
        int transfer3 = resultT.getTransferCount()+layResultT.getTransferCount();
            List<NodeT> transInPath1 = resultT.getTransInPath();
            List<NodeT> transInPath = layResultT.getTransInPath();
            transInPath1.addAll(transInPath);
            List<String> stationsT = new ArrayList<>();
            for (NodeT node : transInPath1) {
                stationsT.add(node.getName());
            }
            Infolists minTransInfo = new Infolists(time3, cost3, transfer3,stationsT);

        List<Infolists> infolists = new ArrayList<>();
        infolists.add(minTimeInfo);
        infolists.add(minCostInfo);
        infolists.add(minTransInfo);


        log.info("\n최소 시간 결과 = \n 시간 {} \n 비용 {} \n 환승 횟수 {}\n 경로 {}\n"
                , time1
                , cost1
                ,transfer1
                ,result1.getPath()+layResult1.getPath());
        log.info("\n최소 비용 결과 = \n 시간 {} \n 비용 {} \n 환승 횟수 {}\n 경로 {}\n"
                , time2
                , cost2
                ,transfer2
                ,result2.getPath()+layResult2.getPath());
        log.info("\n최소 환승 결과 =\n 시간 {} \n 비용 {} \n 환승 횟수 {}\n 경로 {}\n "
                , time3
                , cost3
                ,transfer3
                ,resultT.getPath()+layResultT.getPath());


        return success(SUCCESS_TO_INFO,infolists);
        }
    }
}

