package javabasics;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;

import com.smoothstack.javabasics.weekend.Assignment4;

public class Assignment4Test {
	@Test
	public void noXTest() {
		Assignment4 base = new Assignment4();
		
		List<String> l1 = List.of("ax","bb","cx");
		List<String> l2 = List.of("xxax","xbxbx","xxcx");
		List<String> l3 = List.of("x");
		List<String> l4 = List.of();
		List<String> l5 = List.of("This","is","a","list","of","strings");
		List<String> l6 = List.of("Xx","xX","xXx","XxX");
		
		assertArrayEquals(List.of("a","bb","c").toArray(),base.noX(l1).toArray());
		assertArrayEquals(List.of("a","bb","c").toArray(),base.noX(l2).toArray());
		assertArrayEquals(List.of().toArray(),base.noX(l3).toArray());
		assertArrayEquals(List.of().toArray(),base.noX(l4).toArray());
		assertArrayEquals(List.of("This","is","a","list","of","strings").toArray(),base.noX(l5).toArray());
		assertArrayEquals(List.of("X","X","X","XX").toArray(),base.noX(l6).toArray());
		
		try {
			base.noX(null);
			fail("Did not except at null");
		} catch(Exception e) {
			
		}
	}
}
