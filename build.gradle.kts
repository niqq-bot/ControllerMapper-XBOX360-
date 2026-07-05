plugins {
    java
    application
}

group = "com.controllermapper"
version = "1.0"

repositories {
    mavenCentral()
}

val lwjglVersion = "3.4.1"

dependencies {
    implementation("de.gurkenlabs:input4j:1.3.1")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.20.0")

    implementation(platform("org.lwjgl:lwjgl-bom:$lwjglVersion"))

    implementation("org.lwjgl:lwjgl")
    implementation("org.lwjgl:lwjgl-glfw")
    implementation("org.lwjgl:lwjgl-opengl")

    implementation("io.github.spair:imgui-java-app:1.92.0")

    runtimeOnly("org.lwjgl:lwjgl::natives-windows")
    runtimeOnly("org.lwjgl:lwjgl-glfw::natives-windows")
    runtimeOnly("org.lwjgl:lwjgl-opengl::natives-windows")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(25))
    }
}

application {
    mainClass.set("com.controllermapper.ControllerMapper")
}