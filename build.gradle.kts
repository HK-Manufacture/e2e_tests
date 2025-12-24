plugins {
    id("java")
}

group = "io.testomate"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.2")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    testImplementation ("com.codeborne:selenide:7.6.1")

    implementation("io.github.cdimascio:java-dotenv:5.2.2")
}


tasks.test {
    useJUnitPlatform()
}