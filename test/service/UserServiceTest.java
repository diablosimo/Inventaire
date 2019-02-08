/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.User;
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
public class UserServiceTest {
    
public UserServiceTest() {
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
     * Test of initDb method, of class UserService.
     */
    @Test
    public void testInitDb() {
        System.out.println("initDb");
        UserService instance = new UserService();
        instance.initDb();
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of seConnecter method, of class UserService.
     */
//    @Test
//    public void testSeConnecter() {
//        System.out.println("seConnecter");
//        User user = null;
//        UserService instance = new UserService();
//        int expResult = 0;
//        int result = instance.seConnecter(user);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of createUser method, of class UserService.
//     */
//    @Test
//    public void testCreateUser() {
//        System.out.println("createUser");
//        User user = null;
//        UserService instance = new UserService();
//        int expResult = 0;
//        int result = instance.createUser(user);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of findUsername method, of class UserService.
//     */
//    @Test
//    public void testFindUsername() {
//        System.out.println("findUsername");
//        String email = "";
//        UserService instance = new UserService();
//        String expResult = "";
//        String result = instance.findUsername(email);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of findUser method, of class UserService.
//     */
//    @Test
//    public void testFindUser() {
//        System.out.println("findUser");
//        String email = "";
//        UserService instance = new UserService();
//        Integer expResult = null;
//        Integer result = instance.findUser(email);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
}
