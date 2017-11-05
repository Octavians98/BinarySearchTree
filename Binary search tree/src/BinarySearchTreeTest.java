import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Test;

//ex 1
public class BinarySearchTreeTest {

	@Test
	public void testAdd() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();

		for(int i=1; i<=50; ++i){
			assertTrue("Should return true when adding an elemnt", bst.add(i));
			//the size is affected in the add method, so we test that too
			assertEquals("Size is not correct", i, bst.size());
		}
		
		for(int i=1; i<=50; ++i){
			assertFalse("Should return false when adding an element that already is in the tree", bst.add(i));
		}
	}

	@Test
	public void testRemove(){
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		for(int i=1; i<=50; ++i){
			bst.add(i);
		}
		
		for(int i=50; i>0; --i){
			assertTrue("Succesful remove should return true", bst.remove(i));
			//again, the size is affected, so we test that too
			assertEquals("Size is not correct", i-1, bst.size());
		}
		
		for(int i=51; i<=100; ++i){
			assertFalse("Removing an element that is not in the tree should return false", bst.remove(i));
		}

	}
	
	@Test
	public void testContains(){
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		for(int i=1; i<=50; ++i){
			bst.add(i);
		}
		
		for(int i=1; i<=50; ++i)
			assertTrue("Querying an element that is in the tree should return true", bst.contains(i));
		
		for(int i = 51; i<=100; ++i)
			assertFalse("Querying an element that is not in the tree should return false", bst.contains(i));
	}
	
	@Test
	public void testIterator(){
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		for(int i=1; i<=50; ++i){
			bst.add(i);
		}
		
		Iterator it = bst.iterator();
		
		//should have ascending order
		int i=1;
		while(it.hasNext()){
			assertEquals("Elements are not in ascending order", i, it.next());
			++i;
		}
		
		boolean exceptionThrown=false;
		try{
			it.next();
		}catch(NoSuchElementException e){
			exceptionThrown=true;
		}
		assertTrue("Exception not thrown", exceptionThrown);
	}

}
