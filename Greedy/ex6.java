package AlgorithmContest2021.Greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/*
https://level.goorm.io/exam/43082/%EC%B5%9C%EB%8B%A8-%EA%B1%B0%EB%A6%AC-%EA%B5%AC%ED%95%98%EA%B8%B0/quiz/1
*/
public class ex6 {

    static int cnt = 0;
    static int[][] data;
        public static void main(String[] args){

        Scanner scan = new Scanner(System.in); 
        cnt = scan.nextInt();
        data = new int[cnt][3];
    
        for(int i=0 ; i<cnt ; i++){
            data[i][0] = scan.nextInt();
            data[i][1] = scan.nextInt();
            data[i][2] = 0;
        }

        greedy(0);

        int res = 0;
        for(int i=0 ; i<cnt ; i++){
            if(data[i][2] == 1){
                //System.out.println(data[i][0]+" ~ "+data[i][1]);
                res++;
            }
        }
        System.out.println(res);
    }
    //시작시간을 입력받아서 가장 짧게 소요되는것을 선택한다.
    public static int greedy(int startTime){
        //가장 일찍 끝나는 것을 선택한다, 단 시작시간은 현재 시간 이후여야한다.
        int minIndex = -1;
        int minTime = 1000;
        //System.out.println("startTime : "+startTime);
        for(int i=0 ; i<cnt ; i++){
            if(data[i][0] < startTime) {
                //System.out.println("회의시작시간 : "+data[i][0]);
                continue;
            } else {
                //회의시간이 더 짧은게 존재한다면 (회의는 미사용 상태여야한다.)
                if(data[i][1] - startTime < minTime && data[i][2] == 0){
                    minTime = data[i][1] - startTime;
                    minIndex = i;
                }
            } 

        }
        //System.out.println(minIndex);

        //해당 회의는 사용이 완료된 처리를 한다.
        if(minIndex != -1){
            data[minIndex][2] = 1;
            greedy(startTime+minTime);
        }

        return 0;
    }
}
