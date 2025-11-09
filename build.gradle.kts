plugins {

    java
    id("fabric-loom") version "1.6-SNAPSHOT"
    `maven-publish`
    signing
}

group = "com.github.Tr4nter"
version = "1.0-SNAPSHOT"

java {
    // Loom will automatically attach sourcesJar to a RemapSourcesJar task and to the "build" task
    // if it is present.
    // If you remove this line, sources will not be generated.

    withSourcesJar()
    withJavadocJar()


    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
    maven("https://maven.fabricmc.net/")

}
publishing {
    publications {
        // Make sure you use the lambda syntax with 'create'
        create<MavenPublication>("mavenJava") {
            from(components["java"])



            pom {
                name.set("MyLib")
                description.set("Reusable Fabric library utilities")
                url.set("https://github.com/YourUsername/mylib")

                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://opensource.org/licenses/MIT")
                    }
                }

                developers {
                    developer {
                        id.set("Tr4nter")
                        name.set("Tr4nter")
                    }
                }

                scm {
                    connection.set("scm:git:git://github.com/Tr4nter/SimpleConfig.git")
                    developerConnection.set("scm:git:git://github.com/Tr4nter/SimpleConfig.git")
                    url.set("https://github.com/Tr4nter/SimpleConfig")
                }
            }
        }
    }

    repositories {
        maven {
            name = "OSSRH"
            url = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
            credentials {
                username = findProperty("ossrhUsername") as String? ?: ""
                password = findProperty("ossrhPassword") as String? ?: ""
            }
        }
    }
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("com.google.code.gson:gson:2.10.1")

    minecraft("com.mojang:minecraft:1.21.1")
    mappings("net.fabricmc:yarn:1.21.1+build.3:v2")
    modImplementation("net.fabricmc:fabric-loader:0.15.11")

    // Fabric loader + API — compileOnly because runtime comes from the actual mod
    compileOnly("net.fabricmc:fabric-loader:0.15.11")
    compileOnly("net.fabricmc.fabric-api:fabric-api:0.104.0+1.21.1")
}

tasks.test {
    useJUnitPlatform()
}