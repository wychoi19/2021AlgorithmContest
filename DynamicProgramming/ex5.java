//package AlgorithmContest2021.DynamicProgramming;

import java.util.Scanner;
import java.util.Stack;

/*
https://level.goorm.io/exam/43119/%EA%B4%84%ED%98%B8-%EC%A7%9D-%EB%A7%9E%EC%B6%94%EA%B8%B0/quiz/1
괄호 짝이 맞는지 확인하는 프로그램
여는 괄호는 스택에 넣고, 닫는 괄호가 나오면 스택에서 꺼내서 확인하기
*/
public class ex5 {
    static Stack<Character> stack = new Stack<>();

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        String data = scan.next();
        System.out.println((chkBrackets(data, 0)==true?"True":"False"));
    }   
    
    public static boolean chkBrackets(String data, int idx){
        if(idx >= data.length()) return true;
        if(startBrackets(data.charAt(idx))){
            //괄호의 시작부분이라면 스택에 넣는다.
            stack.push(data.charAt(idx));
        } else {
            char popData = stack.pop();
            if(sameBrackets(popData, data.charAt(idx))){
                //둘이 동일하다면 패스.
            } else {
                //둘이 다르면 괄호가 올바르지 않은 것.
                return false;
            }
        }
        return chkBrackets(data, idx+1);
    }

    public static boolean startBrackets(char data){
        if(data == '[' || data == '{' || data == '(') return true;
        else return false;
    }

    public static boolean sameBrackets(char data1, char data2){
        if(data1 == '(' && data2 == ')') return true;
        else if(data1 == '{' && data2 == '}') return true;
        else if(data1 == '[' && data2 == ']') return true;
        else return false;
    }
}
