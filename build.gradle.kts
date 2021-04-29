import com.adarshr.gradle.testlogger.theme.ThemeType
import org.gradle.jvm.tasks.Jar
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jfrog.gradle.plugin.artifactory.dsl.PublisherConfig

plugins {
    kotlin("jvm") version "1.4.32"
    id("com.github.ben-manes.versions") version "0.38.0"
    id("jacoco")
    id("com.adarshr.test-logger") version "3.0.0"
    id("io.qameta.allure") version "2.8.1"
    id("com.jfrog.artifactory") version "4.21.0"
    id("maven-publish") apply true
}

val allureVersion = "2.13.9"
val mavenPubName = "mavenJava"

group = "no.elhub.common"
description = "A simple dynamic configuration library for Kotlin/JVM projects."

repositories {
    maven("https://artifactory.elhub.cloud/artifactory/elhub-mvn/")
}

dependencies {
    implementation(platform("no.elhub.common:common-bom:1.0-SNAPSHOT"))
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    // Kotlin Platform
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    // Test Dependencies
    testImplementation("io.kotest:kotest-runner-junit5:4.4.3")
    testImplementation("io.kotest:kotest-extensions-allure-jvm:4.4.3")
    testImplementation("org.slf4j:slf4j-simple:1.7.30")
}

/*
 * Compile setup
 */
java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
        javaParameters = true
    }
}

/*
 * Test setup
 */
tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
        showStandardStreams = true
    }
}

jacoco {
    toolVersion = "0.8.4" // Has to be the same as TeamCity
}

tasks.test {
    finalizedBy(tasks.jacocoTestReport) // report is always generated after tests run
}

tasks.jacocoTestReport {
    dependsOn(tasks.test) // tests are required to run before generating the report
}

testlogger {
    theme = ThemeType.MOCHA
}

allure {
    version = allureVersion
    autoconfigure = false
    aspectjweaver = true
    useJUnit5 {
        version = allureVersion
    }
    downloadLink = "https://repo.maven.apache.org/maven2/io/qameta/allure/allure-commandline/" +
            "$allureVersion/allure-commandline-$allureVersion.zip"
}

/*
 * Publishing
 */
publishing {
    publications {
        create<MavenPublication>(mavenPubName) {
            from(components["java"])
        }
    }
}

artifactory {
    setContextUrl("https://jfrog.elhub.cloud/artifactory")
    publish(delegateClosureOf<PublisherConfig> {
        repository(delegateClosureOf<groovy.lang.GroovyObject> {
            setProperty("repoKey", project.findProperty("mavenrepo") ?: "elhub-mvn-test-local")
            setProperty("username", project.findProperty("mavenuser") ?: "nouser")
            setProperty("password", project.findProperty("mavenpass") ?: "nopass")
        })
        defaults(delegateClosureOf<groovy.lang.GroovyObject> {
            invokeMethod("publications", mavenPubName)
            setProperty("publishArtifacts", true)
            setProperty("publishPom", true)
        })
    })
    resolve(delegateClosureOf<org.jfrog.gradle.plugin.artifactory.dsl.ResolverConfig> {
        setProperty("repoKey", "repo")
    })
}

tasks["artifactoryPublish"].dependsOn(tasks["assemble"])

tasks["publish"].dependsOn(tasks["artifactoryPublish"])
