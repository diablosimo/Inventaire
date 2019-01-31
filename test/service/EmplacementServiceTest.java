/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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
     * Test of initDb method, of class EmplacementService.
     */
    @Test
    public void testInitDb() {
        System.out.println("initDb");
        EmplacementService instance = new EmplacementService();
        instance.initDb();
        // TODO review the generated test code and remove the default call to fail.
    }
    
}
