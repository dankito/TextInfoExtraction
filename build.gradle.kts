buildscript {
    val kotlinVersion: String by extra

    repositories {
        mavenCentral()
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
    }
}


plugins {
    kotlin("jvm")

    // So after executing publish staged repository can be closed and released by executing closeAndReleaseRepository
    id("io.codearte.nexus-staging") version "0.21.2"
}


group = "net.dankito.text.extraction"
version = "1.0.3-SNAPSHOT"


kotlin {
    jvmToolchain(8)
}


repositories {
    mavenCentral()
}


val junitVersion: String by project

dependencies {
    implementation("org.slf4j:slf4j-api:1.7.36")


    testImplementation("org.junit.jupiter:junit-jupiter:$junitVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitVersion")

    testImplementation("org.assertj:assertj-core:3.22.0")

    testImplementation("org.mockito:mockito-core:4.3.1")

    testImplementation("ch.qos.logback:logback-classic:1.2.11")

}


ext.apply {
    set("artifactName", "text-info-extractor")

    set("sourceCodeRepositoryBaseUrl", "github.com/dankito/TextInfoExtraction")
    set("projectDescription", "Contains classes for extracting informations like dates, amounts of money, IBANs, BICs from texts.")

    set("licenseName", "The Apache License, Version 2.0")
    set("licenseUrl", "http://www.apache.org/licenses/LICENSE-2.0.txt")
}

val commonScriptsFile = File(File(project.gradle.gradleUserHomeDir, "scripts"), "commonScripts.gradle")
if (commonScriptsFile.exists()) {
    apply(from = commonScriptsFile)
}
