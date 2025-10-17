package org.shark.appboard.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing // spring data jpa의 auditing 기능 활성화 (@CreatedDate, @LastModifiedDate 동작)
public class JpaConfig {

}
