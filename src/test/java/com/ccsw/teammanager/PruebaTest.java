package com.ccsw.teammanager;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ccsw.teammanager.utils.UtilsService;

@SpringBootTest
public class PruebaTest {

  @Autowired
  UtilsService utilsService;

  @Test
  public void Prueba() {

    System.out.println(utilsService);
    assertThat("?").isEqualTo(utilsService.getVersion());
  }
}
