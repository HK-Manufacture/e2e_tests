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

    implementation ("com.codeborne:selenide:7.6.1")

    implementation("io.github.cdimascio:java-dotenv:5.2.2")
}

dependencies {
    testImplementation ("org.slf4j:slf4j-simple:2.0.9")
}


tasks.test {
    useJUnitPlatform()
}