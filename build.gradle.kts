plugins {
    id("application")
    id("org.openjfx.javafxplugin") version "0.1.0"
    id("com.adarshr.test-logger") version "4.0.0"
}

group = "org.pinte"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

javafx {
    version = "22"
    modules("javafx.controls", "javafx.fxml")
}

application {
    mainClass.set("org.pinte.Application")
}

tasks.test {
    useJUnitPlatform()
}