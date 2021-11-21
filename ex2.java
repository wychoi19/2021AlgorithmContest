package AlgorithmContest2021;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class ex2 {

    static int resCntSheep=0;
    static int resCntWolf=0;
    static int cntSheep=0;
    static int cntWolf=0;

    public static void main(String[] args){

        //값 입력받는 부분
        Scanner scan = new Scanner(System.in);

        int row = scan.nextInt();
        int cal = scan.nextInt();
        char data[][]=new char[row][cal];

        for(int j=0;j<row;j++){
            String str = scan.next();
            for(int i=0;i<cal;i++){
                data[j][i] = str.charAt(i);
            }
        }

        for(int i=0;i<row;i++){
            for(int j=0;j<cal;j++){
                //각 배열 원소들마다 연결된 정점을 이용해서 양과 늑대의 마리수를 계산한다.
                if(data[i][j]!= '#'){
                    cntSheep=0;
                    cntWolf=0;
                    BFS(data, i, j, cal, row);
                    //System.out.println("1회 결과 : "+cntSheep+", "+cntWolf);
                    //양이 늑대보다 많아야 양이 살아남는다.
                    if(cntSheep>cntWolf){
                        resCntSheep+=cntSheep;
                    } else {
                        resCntWolf+=cntWolf;
                    }
                }
            }
        }
        System.out.println(resCntSheep+" "+resCntWolf);
    }

    /**
     * 
     * @param data
     * cal, row : 배열의 크기
     * i, j : 탐색할 행 열 값
     */
    public static void BFS(char [][]data, int i, int j, int cal, int row){
        //다음 방문지를 저장해두는 큐, 사용완료시 제거한다.
        Queue<Integer> queue = new LinkedList<>();
        //모든 방문지를 저장해두는 큐, 방문 완료시 #로 변경하여 다시 탐색하지 않게 한다.
        Queue<Integer> visitedQueue = new LinkedList<>();

        queue.add(i*cal+j);
        visitedQueue.add(i*cal+j);

        while(!queue.isEmpty()){
            //현재 방문할 곳
            int nowVisit = queue.poll();
            //System.out.println("nowVisit : "+nowVisit);
            int nowRow = nowVisit/cal;
            int nowCal = nowVisit%cal;
                
            //인덱스가 범위내에 존재하는지 체크
            if(nowRow < row && nowRow >=0 && nowCal < cal && nowCal >=0){
                //System.out.println("현재 방문지 : ("+nowRow+", "+nowCal+"), "+data[nowRow][nowCal]);
                //#은 울타리이므로 범위가 잘린다.
                if(data[nowRow][nowCal] != '#'){
                    if(data[nowRow][nowCal] == 'o') cntSheep++;
                    if(data[nowRow][nowCal] == 'v') cntWolf++;
                    data[nowRow][nowCal] = '#';

                    if(nowCal < cal-1)
                        queue.add((nowRow)*cal+(nowCal+1));
                    if(nowRow > 0)
                        queue.add((nowRow-1)*cal+(nowCal));
                    if(nowRow < row-1)
                        queue.add((nowRow+1)*cal+(nowCal));
                    if(nowCal > 0)
                        queue.add((nowRow)*cal+(nowCal-1));
                }
            }
            //System.out.println();
        }

        
        /*for(int j1=0;j1<row;j1++){
            for(int i1=0;i1<cal;i1++){
                System.out.print(data[j1][i1]);
            }
            System.out.println();
        }*/
    }
    
}
