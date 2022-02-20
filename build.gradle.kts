import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.10"
    id("com.vanniktech.maven.publish") version "0.18.0"
    application
}

repositories {
    mavenCentral()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}

application {
    mainClass.set("MainKt")
}

mavenPublish {
    sonatypeHost = com.vanniktech.maven.publish.SonatypeHost.S01
}
publishing {
    repositories {
        maven {
            url = if(version.toString().endsWith("SNAPSHOT")) {
                uri("$buildDir/repos/snapshots")
            } else {
                uri("$buildDir/repos/releases")
            }
        }
    }
}