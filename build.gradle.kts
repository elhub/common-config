group = "no.elhub.common"
description = "A simple dynamic configuration library for Kotlin/JVM projects."

dependencies {
    implementation(platform("no.elhub.common:common-bom:0.1.0"))
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    // Kotlin Platform
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    // Test Dependencies
    testImplementation("io.kotest:kotest-runner-junit5")
    testImplementation("io.kotest:kotest-extensions-allure-jvm")
    testImplementation("org.slf4j:slf4j-simple")
}
