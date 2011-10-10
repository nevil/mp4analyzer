package jp.hasselqvist.MP4Analyzer.tests;

import static org.junit.Assert.assertTrue;
import jp.hasselqvist.MP4Analyzer.BoxTree;
import jp.hasselqvist.MP4Analyzer.BoxTree.BoxTreeArray;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class BoxTreeTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	/* Test if the isRoot identifier is set correctly */
	@Test
	public void testIsRoot() {
		BoxTree root = new BoxTree();
		assertTrue(root.isRoot());
	}


	/* Test simple tree with one leaf
	 * +
	 * +-(1)
	 */
	@Test
	public void testOneLeaf() {
		BoxTree root = new BoxTree();
		assertTrue(root.isRoot());

		BoxDummy box = new BoxDummy("1");
		root.addLeaf(box);
		assertTrue(root.getBox() == null);

		BoxTreeArray leafs = root.getLeafs();
		assertTrue(leafs.size() == 1);

		assertTrue("1" == ((BoxDummy)leafs.get(0).getBox()).getId());
	}

	/* Test complex tree with multiple leafs
	 * +
	 * +-(1)-+-(1.1)
	 * |     +-(1.2)
	 * |
	 * +-(2)-+-(2.1)
	 *       +-(2.2)
	 */

	@Test
	public void testComplex() {
		BoxTree root = new BoxTree();
		assertTrue(root.isRoot());

		// Add node 1 to the root
		BoxDummy box = new BoxDummy("1");
		BoxTree leaf = root.addLeaf(box);

		// Add node 1.1 and 1.2 to node 1
		box = new BoxDummy("1.1");
		leaf.addLeaf(box);
		box = new BoxDummy("1.2");
		leaf.addLeaf(box);

		// Add node 2 to the root
		box = new BoxDummy("2");
		leaf = root.addLeaf(box);

		// Add node 2.1 and 2.2 to node 2
		box = new BoxDummy("2.1");
		leaf.addLeaf(box);
		box = new BoxDummy("2.2");
		leaf.addLeaf(box);

		BoxTreeArray leafs = root.getLeafs();
		assertTrue(leafs.size() == 2);
		assertTrue("1" == ((BoxDummy)leafs.get(0).getBox()).getId());
		assertTrue("2" == ((BoxDummy)leafs.get(1).getBox()).getId());

		leafs = leafs.get(0).getLeafs();
		assertTrue("1.1" == ((BoxDummy)leafs.get(0).getBox()).getId());
		assertTrue("1.2" == ((BoxDummy)leafs.get(1).getBox()).getId());
		assertTrue(leafs.get(0).getLeafs().size() == 0);
		assertTrue(leafs.get(1).getLeafs().size() == 0);

		leafs = root.getLeafs().get(1).getLeafs();
		assertTrue("2.1" == ((BoxDummy)leafs.get(0).getBox()).getId());
		assertTrue("2.2" == ((BoxDummy)leafs.get(1).getBox()).getId());
		assertTrue(leafs.get(0).getLeafs().size() == 0);
		assertTrue(leafs.get(1).getLeafs().size() == 0);
	}

	/* Test appending of two trees
	 * +                         +
	 * +-(1)-+-(1.1)             +-(1)-+-(1.1)
	 *       +-(1.2)             |     +-(1.2)
	 * +                --->     |
	 * +-(2)-+-(2.1)             +-(2)-+-(2.1)
	 *       +-(2.2)                   +-(2.2)
	 */

	@Test
	public void testAppendTree() {
		BoxTree root1 = new BoxTree();

		// Add node 1 to root1
		BoxDummy box = new BoxDummy("1");
		BoxTree leaf = root1.addLeaf(box);

		// Add node 1.1 and 1.2 to node 1
		box = new BoxDummy("1.1");
		leaf.addLeaf(box);
		box = new BoxDummy("1.2");
		leaf.addLeaf(box);

		BoxTree root2 = new BoxTree();
		// Add node 2 to root2
		box = new BoxDummy("2");
		leaf = root2.addLeaf(box);

		// Add node 2.1 and 2.2 to node 2
		box = new BoxDummy("2.1");
		leaf.addLeaf(box);
		box = new BoxDummy("2.2");
		leaf.addLeaf(box);

		root1.appendTree(root2);

		BoxTreeArray leafs = root1.getLeafs();
		assertTrue(leafs.size() == 2);
		assertTrue("1" == ((BoxDummy)leafs.get(0).getBox()).getId());
		assertTrue("2" == ((BoxDummy)leafs.get(1).getBox()).getId());
		assertTrue(leafs.get(0).isRoot() == false);
		assertTrue(leafs.get(1).isRoot() == false);

		leafs = leafs.get(0).getLeafs();
		assertTrue("1.1" == ((BoxDummy)leafs.get(0).getBox()).getId());
		assertTrue("1.2" == ((BoxDummy)leafs.get(1).getBox()).getId());
		assertTrue(leafs.get(0).getLeafs().size() == 0);
		assertTrue(leafs.get(1).getLeafs().size() == 0);

		leafs = root1.getLeafs().get(1).getLeafs();
		assertTrue("2.1" == ((BoxDummy)leafs.get(0).getBox()).getId());
		assertTrue("2.2" == ((BoxDummy)leafs.get(1).getBox()).getId());
		assertTrue(leafs.get(0).getLeafs().size() == 0);
		assertTrue(leafs.get(1).getLeafs().size() == 0);
	}
}
