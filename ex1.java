package AlgorithmContest2021;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class ex1 {

    public static void main(String[] args){
        String [][] data = {{"ICN", "B"}, {"B", "ICN"}, {"ICN", "A"}, {"A", "D"}, {"D", "A"}};
        solution(data);
    }

    public static String[] solution(String[][] tickets) {
        String[] answer = {};

        //해당 티켓을 사용했는지에 대한 정보
        HashMap<HashMap<String, String>, Boolean> usedTickets = new HashMap<HashMap<String, String>, Boolean>();

        //정점끼리의 연결 정보를 담고 있다.
        HashMap<String, ArrayList<String>> airportList = new HashMap<String, ArrayList<String>>();

        for(String[] ticket : tickets){
            String from = ticket[0];
            String to = ticket[1];

            //현재 인천-도쿄 구간을 넣고 있다고 가정할 때
            //이미 인천-상하이 구간이 존재한다면 인천 상하이를 map에서 꺼낸 후 
            //도쿄를 추가한 후 다시 넣어준다. (경로가 중복되는 경우 추가하지 않는다.)
            //그렇지 않다면 도쿄 구간만 추가해준다.

            System.out.println(airportList);
            if (airportList.containsKey(from)) {
                ArrayList<String> fromTickets = airportList.get(from);
                if(!fromTickets.contains(to))
                    fromTickets.add(to);
            } else {
                ArrayList<String> fromTickets = new ArrayList<>();
                fromTickets.add(to);
                airportList.put(from, fromTickets);
            }
        }

        //list의 모든 시작점(키)를 조회해서 각 항목들에대해 정렬을 한다.
        for(String key : airportList.keySet()){
                ArrayList<String> ticketList = airportList.get(key);
                Collections.sort(ticketList);
        };

        System.out.println(airportList);
        //모든 시작은 인천공항이다.
        answer = BFS("ICN", airportList, usedTickets);
        return answer;
    }

    /**
     * BFS 알고리즘을 이용
     * @param to
     * @param airportList
     * @param visitedArr
     */
    public static String[] BFS(String to, HashMap<String, ArrayList<String>> airportList, HashMap<HashMap<String, String>, Boolean> usedTickets){
        //시작 공항인 인천공항을 queue에 넣는다.
        //queue에는 다음 방문할 공항에 대한 정보를 넣어두었다가 방문 시 꺼내사용한다.
        Queue<String> queue = new LinkedList<String>();
        ArrayList<String> resultList=new ArrayList<>();
        int resIndex = 0;

        //인천공항이 시작공항이다.
        queue.add(to);
        //visitedArr.put(to, true); 

        //queue에는 다음 방문지가 들어있는데 queue가 비었다는 것은 더이상 방문할 곳이 없다는 의미이다.
        while(!queue.isEmpty()){
            //다음 방문지를 큐에서 꺼내온다.
            String airport = queue.poll();
            resultList.add(airport);

            //현재 출발공항이 ICN이라면 ICN에서 출발하는 티켓들을 조회한다.
            if(airportList.containsKey(airport)){
                Iterator<String> iter = airportList.get(airport).listIterator();
                if(iter.hasNext()){
                    String nextAirport = iter.next();
                    //해당 티켓을 사용한 경우는 방문하지 않는다.
                    HashMap<String, String> usedTicket = new HashMap<String, String>();
                    usedTicket.put(airport, nextAirport);
                    System.out.println(usedTicket);
                    //해당 경로의 티켓이 존재하고, 티켓이 미사용 상태일 경우 공항 방문 예정처리를 한다(큐에 넣는다.)
                    if(!usedTickets.containsKey(usedTicket)){
                        usedTickets.put(usedTicket, true);
                        queue.add(nextAirport);
                       
                        //방문했던 지역은 삭제한다.
                        ArrayList<String> fromTickets = airportList.get(airport);
                        fromTickets.remove(nextAirport);
                    } else {
                        System.out.println(usedTicket+"은 방문했다.");
                    }
                }
            }
        }
        
        String[] result = new String[resultList.size()];
        for(String data : resultList){
            result[resIndex++] = data;
            System.out.print(data+" ");
        }
        return result;
    }
}
