plugins {
    id("java")
    id("io.freefair.lombok") version "8.3"
    application
}
group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.2")
    testImplementation("org.mockito:mockito-core:5.2.0")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.10.0")

    implementation("org.mybatis:mybatis:3.5.13")
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("com.h2database:h2:2.2.224")
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "Main"
    }
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
}
