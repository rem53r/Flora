plugins {
    kotlin("jvm") version "2.1.21"
    `maven-publish`
}

group = "uwu.evaware"
version = "1.2"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(23)
}

publishing {
    publications {
        create<MavenPublication>("gpr") {
            from(components["java"])
            artifactId = "flora"

            pom {
                name.set("Flora")
                description.set("Event bus for kotlin")
                url.set("https://github.com/rem53r/Flora")
            }
        }
    }

    repositories {
        maven("https://maven.pkg.github.com/rem53r/Flora") {
            name = "GitHubPackages"
            credentials {
                username = project.findProperty("systemProp.gpr.user") as String?
                password = project.findProperty("systemProp.gpr.token") as String?
            }
        }
    }
}
