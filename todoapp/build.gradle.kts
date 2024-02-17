//import org.springframework.boot.gradle.tasks.bundling.BootJar

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.2.2"
    id("io.spring.dependency-management") version "1.1.4"
    kotlin("jvm") version "1.9.22"
//	id("java")
    kotlin("plugin.spring") version "1.9.22"
    kotlin("plugin.jpa") version "1.9.22"
    id("org.liquibase.gradle") version "2.2.1"
}

group = "com.tommannson"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-actuator")

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-jooq")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.liquibase:liquibase-core")

    liquibaseRuntime("org.liquibase:liquibase-core:3.6.2")
    liquibaseRuntime("org.liquibase:liquibase-groovy-dsl:2.0.1")
    liquibaseRuntime("org.liquibase.ext:liquibase-hibernate5:3.6")


    developmentOnly("org.springframework.boot:spring-boot-devtools")
    runtimeOnly("org.postgresql:postgresql")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

//liquibase {
////    mainClassName = "com.tommannson.todoapp.StartApp"
//    activities.register("main") {
//        this.arguments = mapOf(
//            "driver" to "org.postgresql.Driver",
//            "url" to "jdbc:postgresql://localhost:5432/todo",
//            "username" to "admin1",
//            "password" to "pass1",
//            "changeLogFile" to "./src/main/resources/db/changelog/db.changelog-0.1.yml",
//			"classpath" to "$rootDir"
//        )
//    }
////    runList = "main"
//}


tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
}

//tasks.withType<BootJar> {
//	mainClass = "com.tommannson.todoapp.StartApplication"
//}


tasks.withType<Test> {
    useJUnitPlatform()
}
