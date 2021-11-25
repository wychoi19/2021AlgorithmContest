import java.io.*;
import java.util.Scanner;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
public class ex6 {
    static int total = 0;
	public static void main(String[] args) throws Exception {
		Scanner scan = new Scanner(System.in);
		int cnt = scan.nextInt();

        scan.nextLine();
        String data = scan.nextLine().replaceAll(" ", "");
        
		int arr[] = new int[cnt];
		char op[] = new char[cnt-2];
		for(int i=0;i<cnt;i++){
			arr[i] = Character.getNumericValue(data.charAt(i));
		}
		//시작 opCode를 3개를 넘겨서 호출한다.

		DP(arr, op, -1);

        System.out.println(total);
	}
	
	private static void DP(int[] arrData, char[] arrOp, int index){
		//현재 인덱스
        index ++;
        if(index > arrOp.length-1){
            int result = arrData[0];
            for(int i=0;i<arrOp.length;i++){
                switch(arrOp[i]){
                    case '+': 
                    result += arrData[i+1];
                    break;
                    case '-': 
                    result -= arrData[i+1];
                    break;
                }
                if(result < 0 || result >20) {
                    return;
                }
            }

            /*int i=0;
            for(i=0;i<arrData.length-2;i++){
                System.out.print(arrData[i]);
                System.out.print(arrOp[i]);
            }
            System.out.println(arrData[i]+"="+arrData[i+1]);*/
            if(result == arrData[arrData.length-1]){
                total++;
            }

            return;
        }
        arrOp[index] = '+';
        DP(arrData, arrOp.clone(), index);
        arrOp[index] = '-';
        DP(arrData, arrOp.clone(), index);
		
	}
}