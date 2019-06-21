package com.company.jersey01.services;

import com.company.jersey01.models.CharityEntity;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author Jeff Risberg
 * @since 11/03/17
 */
public class CharityServiceTest {

  private CharityService charityService;

  @Before
  public void setUp() throws Exception {
    charityService = new CharityService();
  }

  @Test
  public void testGetOne() {
    CharityEntity charityEntity = charityService.getCharity(1);

    assertEquals("Red Cross", charityEntity.getName());
  }

  @Test
  public void testGetList() {
    List<CharityEntity> charityEntityList = charityService.getCharities(50, 0, null);

    assertNotNull(charityEntityList);
    assertEquals(8, charityEntityList.size());
  }
}
