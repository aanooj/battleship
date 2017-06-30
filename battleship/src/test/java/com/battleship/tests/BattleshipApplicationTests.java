package com.battleship.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.battleship.BattleshipApplication;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {BattleshipApplication.class})
@SpringBootTest
public class BattleshipApplicationTests {

	@Test
	public void contextLoads() {
	
	}

}
