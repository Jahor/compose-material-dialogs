import java.util.Date

plugins {
    id("com.android.library")
    id("common-library")
    id("org.jmailen.kotlinter") version "3.2.0"
}

android {
    compileOptions {
        isCoreLibraryDesugaringEnabled = true
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    api(project(":core"))
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:1.0.10")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().all {
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = listOf(
            "-Xallow-jvm-ir-dependencies",
            "-Xskip-prerelease-check",
            "-Xopt-in=kotlin.Experimental"
        )
    }
}

val artifactName = "datetime"
val artifactGroup = "com.vanpra.compose-material-dialogs"
val artifactVersion = Versions.library

val sourcesJar by tasks.creating(Jar::class) {
    from(android.sourceSets.getByName("main").java.srcDirs)
    archiveClassifier.set("sources")
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                from(components.getByName("release"))
                artifact(sourcesJar)

                groupId = artifactGroup
                artifactId = artifactName
                version = artifactVersion
            }
        }
    }

    bintray {
        user = project.findProperty("bintrayUser").toString()
        key = project.findProperty("bintrayKey").toString()
        publish = true
        override = true

        setPublications("release")

        pkg.apply {
            repo = "maven"
            name = "compose-material-dialogs:$artifactName"

            version.apply {
                name = artifactVersion
                released = Date().toString()
                vcsTag = artifactVersion
            }
        }
    }
}