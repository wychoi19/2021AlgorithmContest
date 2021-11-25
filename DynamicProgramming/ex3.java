//package AlgorithmContest2021.DynamicProgramming;

import java.util.ArrayList;
import java.util.Scanner;

//https://www.acmicpc.net/problem/9095
//입력받은 숫자를 1 2 3의 합으로 계산하는 경우의 수를 출력한다.
public class ex3 {
    static int cnt = 0;
    public static void main(String [] args){
        Scanner scan = new Scanner(System.in);
        ArrayList<Integer> result = new ArrayList<>();
        int numCnt = scan.nextInt();

        for(int i=0;i<numCnt;i++){
            int data = scan.nextInt();
            cnt = 0;
            SUM(1, new ArrayList<Integer>(), data);
            SUM(2, new ArrayList<Integer>(), data);
            SUM(3, new ArrayList<Integer>(), data);
            result.add(cnt);
        }
        for(int i=0;i<numCnt;i++){
            System.out.println(result.get(i));
        }
    }

    public static void SUM(int num, ArrayList<Integer> nums, int res){
        //합이 완성된 경우 재귀함수를 빠져나간다.
        nums.add(num);
        //System.out.println(nums);
        int sum = 0;
        for(int tmpNum : nums){
            sum += tmpNum;
        }
        if(sum == res) {
            cnt++;
            return;
        }
        if(sum > res) return;
        //다음 호출에서 합계가 넘어가는 경우 호출하지 않는다.
        SUM(1, new ArrayList<Integer>(nums), res);
        SUM(2, new ArrayList<Integer>(nums), res);
        SUM(3, new ArrayList<Integer>(nums), res);

    }
}
