import sun.jvmstat.monitor.MonitoredVmUtil.mainClass

plugins {
    java
    application
}

group = "com.controllermapper"
version = "1.0"

repositories {
    mavenLocal()
    mavenCentral()
}

val lwjglVersion = "3.3.6"

dependencies {
    implementation("de.gurkenlabs:input4j:1.3.1")

    implementation("com.fasterxml.jackson.core:jackson-databind:2.20.0")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(25))
    }
}

application {
    mainClass.set("com.controllermapper.ControllerMapper")
}