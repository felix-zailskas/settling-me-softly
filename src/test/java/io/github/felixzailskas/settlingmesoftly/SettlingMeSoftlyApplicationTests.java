package io.github.felixzailskas.settlingmesoftly;

import io.github.felixzailskas.settlingmesoftly.config.TestcontainersConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import(TestcontainersConfig.class)
class SettlingMeSoftlyApplicationTests {

  @Test
  void contextLoads() {}
}
