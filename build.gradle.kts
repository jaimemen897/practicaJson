
plugins {
    id("java")
    id("io.freefair.lombok") version "8.3"
    application
}

application {
    mainClass.set("Main")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.2")
    implementation ("com.google.code.gson:gson:2.8.9")
    testImplementation ("org.mockito:mockito-core:2.1.0")
    testImplementation ("org.junit.jupiter:junit-jupiter-engine:5.10.0")
    testImplementation("com.h2database:h2:2.2.224")
    implementation("org.slf4j:slf4j-api:2.0.9")
    implementation("ch.qos.logback:logback-classic:1.4.11")
    implementation("org.mybatis:mybatis:3.5.13")
    implementation("com.opencsv:opencsv:5.8")
    implementation("com.h2database:h2:2.2.224")
}


tasks.register<Jar>("executableJar") {
    dependsOn(tasks.named("jar"))
    manifest {
        attributes(
            "Main-Class" to "Main"
        )
    }
    from(sourceSets.main.get().output)
    archiveFileName.set("${project.name}-${project.version}-executable.jar")
}

tasks.test {
    useJUnitPlatform()
}
