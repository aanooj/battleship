package com.battleship.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.battleship.BattleshipApplication;
import com.battleship.domain.model.ship.Ship;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {BattleshipApplication.class})
public class ShipDomainUnitTests {

	private Ship newShip;
	
	@Before
	public void setUp() throws Exception {
		
		newShip = new Ship();		
	}

	@Test
	public void testShipCreation() {
		
		if(newShip != null){
			assertTrue(newShip.getShipID() > 0);			
		}
		else
			assertFalse(true);		
	}
	
}
