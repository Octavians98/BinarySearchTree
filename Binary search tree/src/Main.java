import java.util.Random;

import javax.swing.JFrame;

public class Main {

	public static void main(String args[]){
		BinarySearchTree<Integer> bst;
		
		Random r = new Random();
		//ex3
		//adding random numbers from 100 to 2000
		for(int i=1; i<=20; ++i){
			bst = new BinarySearchTree<Integer>();
			for(int j=1; j<=i*100; ++j){
				bst.add(r.nextInt(100000));
			}
			//printing the results to put them in excel
			System.out.println(bst.averageDepth() +" "+ i*100);
		}
		
		//ex5
		bst = new BinarySearchTree<Integer>();
		for(int j=1; j<=50; ++j){
			bst.add(r.nextInt(100));
		}
		
		
	}
}
