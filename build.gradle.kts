plugins {
    java
    id("org.springframework.boot") version "4.0.3"
    id("io.spring.dependency-management") version "1.1.7"
    id("com.diffplug.spotless") version "7.0.2"
    id("org.openapi.generator") version "7.12.0"
}

spotless {
    java {
        targetExclude("build/generated/**")
        googleJavaFormat()
        removeUnusedImports()
    }
    sql {
        target("src/main/resources/db/migration/*.sql")
        dbeaver()
    }
    kotlinGradle {
        ktlint()
    }
}

group = "io.github.felixzailskas"
version = "0.0.1-SNAPSHOT"
description = "Payment Settlement Demo"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-flyway")
    implementation("org.springframework.boot:spring-boot-starter-webmvc")
    implementation("org.flywaydb:flyway-database-postgresql")
    implementation("io.swagger.core.v3:swagger-annotations:2.2.28")
    implementation("jakarta.validation:jakarta.validation-api")
    implementation("org.openapitools:jackson-databind-nullable:0.2.6")
    developmentOnly("org.springframework.boot:spring-boot-docker-compose")
    runtimeOnly("org.postgresql:postgresql")
    testImplementation("org.springframework.boot:spring-boot-starter-actuator-test")
    testImplementation("org.springframework.boot:spring-boot-starter-data-jpa-test")
    testImplementation("org.springframework.boot:spring-boot-starter-flyway-test")
    testImplementation("org.springframework.boot:spring-boot-starter-webmvc-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation("org.springframework.boot:spring-boot-testcontainers")
    testImplementation("org.testcontainers:junit-jupiter:1.20.4")
    testImplementation("org.testcontainers:postgresql:1.20.4")
}

openApiGenerate {
    generatorName.set("spring")
    inputSpec.set("$projectDir/src/main/resources/openapi/api.yaml")
    outputDir.set("${layout.buildDirectory.get()}/generated")
    apiPackage.set("io.github.felixzailskas.settlingmesoftly.api")
    modelPackage.set("io.github.felixzailskas.settlingmesoftly.model")
    configOptions.set(
        mapOf(
            "interfaceOnly" to "true",
            "useSpringBoot3" to "true",
            "useTags" to "true",
            "skipDefaultInterface" to "false",
        ),
    )
}

sourceSets {
    main {
        java {
            srcDir("${layout.buildDirectory.get()}/generated/src/main/java")
        }
    }
}

tasks.named("compileJava") {
    dependsOn("openApiGenerate")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
