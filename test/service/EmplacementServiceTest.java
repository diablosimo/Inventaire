/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author cneree
 */
public class EmplacementServiceTest {
    
    public EmplacementServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of initDB method, of class EmplacementService.
     */
    @Test
    public void testInitDB() {
        System.out.println("initDB");
        EmplacementService instance = new EmplacementService();
        instance.initDB();
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of findEmName method, of class EmplacementService.
     */
   // @Test
//    public void testFindEmName() {
//        System.out.println("findEmName");
//        EmplacementService instance = new EmplacementService();
//        List<String> expResult = null;
//        List<String> result = instance.findEmName();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of creerEmplacement method, of class EmplacementService.
     */
//    @Test
//    public void testCreerEmplacement() {
//        System.out.println("creerEmplacement");
//        Long id = null;
//        String nomEmplacement = "";
//        EmplacementService instance = new EmplacementService();
//        instance.creerEmplacement(id, nomEmplacement);
//        // TODO review the generated test code and remove the default call to fail.
//    }
//    
}
