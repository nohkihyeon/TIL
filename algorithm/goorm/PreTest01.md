```java
package sol;

import java.util.Scanner;

import java.util.PriorityQueue;


class Main {
	private static int[]dx = {-1, 0, 1, 0};
	private static int[]dy = {0, 1, 0, -1};
	private static int count =1;
	private static int size;
	
	private static PriorityQueue<Integer> canUseNumber = new PriorityQueue<Integer>();
	private static PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
	
	private static void solution(int numOfOperation, Operation[] operations) {
		for(int i=2; i<100_001; i++)
			canUseNumber.add(i);
		StringBuilder sb = new StringBuilder();
		pq.add(1);
		
		for(int i=0; i< numOfOperation; i++) {
			if (OperationType.merge == operations[i].type) {
				canUseNumber.add(operations[i].value);
				pq.remove(operations[i].value);
			}
			
			if (OperationType.branch == operations[i].type) {
				int num = canUseNumber.poll();
				pq.add(num);
			}
		}
		
		while(!pq.isEmpty()) {
			sb.append(pq.poll() + " ");
		}
		
		System.out.println(sb);

	}
	
	
	
	private static class InputData{
		int numOfOperation;
		Operation[] operations;
	}
	
	private static class Operation{
		OperationType type;
		Integer value;
		
		public Operation(OperationType type, Integer value) {
			this.type = type;
			this.value = value;
		}
	}
	
	private static enum OperationType{
		branch,
		merge;
	}
	
	private static InputData processStdin( ) {
		InputData inputData = new InputData();
		
		try(Scanner scanner = new Scanner(System.in)){
			inputData.numOfOperation = Integer.parseInt(scanner.nextLine());
			inputData.operations = new Operation[inputData.numOfOperation];
			
			for(int i=0; i< inputData.numOfOperation; i++) {
				String[] temp = scanner.nextLine().split(" ");
				Integer value = null;
				OperationType operationType = OperationType.valueOf(temp[0]);
				if (OperationType.merge == operationType) {
					value = Integer.valueOf(temp[1]);
				}
				
				inputData.operations[i] = new Operation(operationType, value);
				
			}
		} catch(Exception e) {
			throw e;
		}
		return inputData;
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InputData inputdata = processStdin();
		
		solution(inputdata.numOfOperation, inputdata.operations);
	}

}

```



2

```java
import java.util.Scanner;
import java.util.Set;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;


class Main {
	private static ArrayList<Integer> ansList = new ArrayList<Integer>();

	private static void solution(int numOfRegion, int numOfAttackableFrequency, int[][] frequencies) {
		int ans = 0;
		
	    ArrayList<Integer> list = new ArrayList<Integer>();
	    for(int i=0; i< numOfRegion; i++) { 
	    	int numOfFrequnecy = frequencies[i].length;
			for(int j=0; j < numOfFrequnecy; j++) {
				 list.add(frequencies[i][j]);
			}
		}
	    Set<Integer> set = new HashSet<>(list);
	    for(Integer i : set){
            ansList.add(Collections.frequency(list, i));
        }
	    Collections.sort(ansList, Collections.reverseOrder());
	    
	    for(int i=0; i<numOfAttackableFrequency; i++) {
	    	ans += ansList.get(i);
	    }
	    
		System.out.println(ans);

	}
	
	
	
	private static class InputData{
		int numOfRegion;
		int numOfAttackableFrequency;
		int[][] frequencies ;
	}
	
	private static InputData processStdin( ) {
		InputData inputData = new InputData();
		
		try(Scanner scanner = new Scanner(System.in)){
			String[] temp = scanner.nextLine().split(" ");
			inputData.numOfRegion = Integer.parseInt(temp[0]);
			inputData.numOfAttackableFrequency = Integer.parseInt(temp[1]);
			inputData.frequencies = new int[inputData.numOfRegion][];
			
			for(int i=0; i< inputData.numOfRegion; i++) { 
				temp = scanner.nextLine().split(" ");
			
				int numOfFrequnecy = Integer.valueOf(temp[0]);
				inputData.frequencies[i] = new int[numOfFrequnecy];
				for(int j=0; j < numOfFrequnecy; j++) {
					inputData.frequencies[i][j] = Integer.parseInt(temp[j + 1]);
				}
			}
		} catch(Exception e) {
			throw e;
		}
		return inputData;
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InputData inputdata = processStdin();
		
		solution(inputdata.numOfRegion, inputdata.numOfAttackableFrequency, inputdata.frequencies);
	}

}




```
