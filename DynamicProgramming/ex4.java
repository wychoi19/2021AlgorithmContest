//package AlgorithmContest2021.DynamicProgramming;

import java.util.Scanner;

/*
https://level.goorm.io/exam/43155/%EC%9D%B4%ED%95%AD-%EA%B3%84%EC%88%98-binomial-coefficient/quiz/1
*/

public class ex4 {
    //동적계획법을 이용한 이항계수 구하기 (중복없이 n개 꺼내기)
    //memorization 사용하기
    static int[][] dp ;

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);

        int N = scan.nextInt();
        int K = scan.nextInt();

        dp = new int[N + 1][K + 1];
 

        System.out.println(BC(N, K)); 

        scan.close();
    }

    static int BC(int N, int K) {
 
        // 이미 풀었던 sub문제일 경우 값을 재활용
        if(dp[N][K] > 0) {
            return dp[N][K];
        }
     
        // 2번 성질
        if(N == K || K == 0) {
            return dp[N][K] = 1;
        }
        // 1번 성질
        return dp[N][K] = BC(N - 1, K - 1) + BC(N - 1, K);
    }
}
